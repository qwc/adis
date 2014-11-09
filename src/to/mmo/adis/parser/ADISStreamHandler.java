/* 
 * Project: jisoagrinet
 * File: ISOagriNetParser.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.adis.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import to.mmo.adis.ADIS;
import to.mmo.adis.ADIS.LineType;
import to.mmo.adis.ADISException;
import to.mmo.adis.CommentValue;
import to.mmo.adis.EntityValue;
import to.mmo.adis.RequestValue;
import to.mmo.adis.SearchValue;
import to.mmo.adis.composer.ADISComposeException;
import to.mmo.adis.composer.EntityComposer;
import to.mmo.adis.handler.EntityHandler;
import to.mmo.adis.handler.RequestHandler;

public class ADISStreamHandler implements Runnable {
	// ADIS = Agricultural Data Interchange Syntax

	private InputStream input;
	private OutputStream output;
	private HashMap<ADIS.LineType, Pattern> patterns;
	private ArrayList<EntityValue> parsedEntities;
	private FinishCondition condition;
	private ADIS.LineType currentLineState;
	private ConcurrentHashMap<String, EntityHandler> entityHandlers;
	private SearchParser searchParser;
	private RequestParser requestParser;
	private ConcurrentHashMap<String, SearchValue> openSearches;
	private ADIS.LineType lastLineState;
	private ArrayList<RequestHandler> requestHandlers;
	private EntityItemParser eiParser;

	public static enum ParserStates {
		HEADER, DATA, END, FAILURE
	};

	public static interface FinishCondition {
		boolean getCondition(ADIS.LineType state);
	}

	public ADISStreamHandler() {
		entityHandlers = new ConcurrentHashMap<String, EntityHandler>();
		System.out.println("constructing parser");
		parsedEntities = new ArrayList<EntityValue>();
		patterns = new HashMap<ADIS.LineType, Pattern>();
		patterns.put(ADIS.LineType.D, Pattern.compile("^D(.)(.*)"));
		patterns.put(ADIS.LineType.V, Pattern.compile("^V(.)(.*)"));
		patterns.put(ADIS.LineType.T, Pattern.compile("^T(.)"));
		patterns.put(ADIS.LineType.C, Pattern.compile("^C(.)(.*)"));
		patterns.put(ADIS.LineType.R, Pattern.compile("^R(.)(.*)"));
		patterns.put(ADIS.LineType.S, Pattern.compile("^S(.)(.*)"));
		patterns.put(ADIS.LineType.Z, Pattern.compile("^Z(.)"));

		// Default constraint running until a 'EOF' in adis appears
		condition = new FinishCondition() {
			@Override
			public boolean getCondition(ADIS.LineType state) {
				if (state != ADIS.LineType.Z)
					return true;
				return false;
			}
		};
		searchParser = new SearchParser();
		requestParser = new RequestParser();
		requestHandlers = new ArrayList<RequestHandler>();
	}

	public ADISStreamHandler(InputStream in, OutputStream out) {
		this();
		this.input = in;
		this.output = out;
	}

	public ADISStreamHandler(InputStream in, OutputStream out,
			FinishCondition constraint) {
		this();
		this.input = in;
		this.output = out;
		this.condition = constraint;
	}

	@Override
	public void run() {
		System.out.println("running parser");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line = null;
			while (condition.getCondition(currentLineState)) {
				if ((line = reader.readLine()) != null) {
					System.out.println("got line: " + line);
					parse(line);
				} else {
					Thread.sleep(10);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parse(String line) throws ADISException {
		// TODO: throw parse exception with error information in adis/aded
		// syntax also put that on the output stream.
		// Need to think about the return objects...
		currentLineState = null;
		for (ADIS.LineType ls : patterns.keySet()) {
			if (patterns.get(ls).matcher(line).find()) {
				currentLineState = ls;
			}
		}
		if (lastLineState == LineType.S && currentLineState != LineType.R) {
			RequestValue rVal = new RequestValue();
			for (SearchValue s : openSearches.values()) {
				rVal.addSearchValue(s);
			}
			rVal.setError(true);
			this.compose(rVal, null);
			throw new ADISParseException(line, 0,
					"Search lines need to have a follow up request line.");
		}
		if (currentLineState == ADIS.LineType.D) {
			eiParser = new EntityItemParser();
			if (eiParser.parseDefinition(line) == null) {
				throw new ADISParseException(line, 0, "");
			}
			lastLineState = LineType.D;
		}
		if (currentLineState == ADIS.LineType.V && eiParser != null) {
			EntityValue ev = eiParser.parse(line);
			EntityHandler h;
			if ((h = entityHandlers.get(ev.getEntity())) != null) {
				h.handle(ev);
			}
		} else {
			this.compose(line, true);
			CommentValue cf = new CommentValue("Non existent definition line.",
					true);
			this.compose(cf);
			throw new ADISParseException(line, 0,
					"Parse error. No existent definition line.");
		}
		if (currentLineState == ADIS.LineType.S
				|| currentLineState == ADIS.LineType.R) {
			if (currentLineState == ADIS.LineType.S) {
				SearchValue sVal = searchParser.parse(line);
				openSearches.put(sVal.getEntity().getEntity(), sVal);
				lastLineState = LineType.S;
			}
			if (currentLineState == ADIS.LineType.R) {
				RequestValue rVal = requestParser.parse(line);
				for (SearchValue s : openSearches.values()) {
					rVal.addSearchValue(s);
				}
				for (RequestHandler r : requestHandlers) {
					r.handle(rVal);
				}
			}
		}

	}

	public ArrayList<EntityValue> getParsedEntities() {
		return parsedEntities;
	}

	public void addRequestHandler(RequestHandler handler) {
		requestHandlers.add(handler);
	}

	public void removeRequestHandler(RequestHandler handler) {
		requestHandlers.remove(handler);
	}

	public void addEntityHandler(EntityHandler handler) {
		entityHandlers.put(handler.entity(), handler);
	}

	public void removeEntityHandler(EntityHandler handler) {
		entityHandlers.remove(handler.entity());
	}

	public void compose(EntityValue[] entity) throws ADISComposeException {
		EntityComposer.compose(entity);

	}

	public void compose(RequestValue request, EntityValue[] response) {

	}

	public void compose(CommentValue comment) {

	}

	public void compose(String line, boolean error) {

	}
}
