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

import to.mmo.adis.EntityValue;
import to.mmo.adis.handler.EntityHandler;

public class ADISParser extends Thread {
	// ADIS = Agricultural Data Interchange Syntax

	private InputStream input;
	private OutputStream output;
	private ParserStates status;
	private HashMap<LineState, Pattern> patterns;
	private long lineCnt;
	private ArrayList<EntityValue> parsedEntities;
	private FinishCondition condition;
	private LineState currentLineState;
	private ConcurrentHashMap<String, EntityHandler> entityHandlers;

	public static enum ParserStates {
		HEADER, DATA, END, FAILURE
	};

	public static enum LineState {
		D, V, T, C, R, S, Z
	};

	public static enum LineSubState {
		H, N
	}

	public static enum RequestStates {

	};

	public static enum SearchStates {

	};

	public static interface FinishCondition {
		boolean getCondition(LineState state);
	}

	public ADISParser() {
		entityHandlers = new ConcurrentHashMap<String, EntityHandler>();
		System.out.println("constructing parser");
		status = ParserStates.HEADER;
		parsedEntities = new ArrayList<EntityValue>();
		patterns = new HashMap<ADISParser.LineState, Pattern>();
		patterns.put(LineState.D, Pattern.compile("^D(.)(.*)"));
		patterns.put(LineState.V, Pattern.compile("^V(.)(.*)"));
		patterns.put(LineState.T, Pattern.compile("^T(.)"));
		patterns.put(LineState.C, Pattern.compile("^C(.)(.*)"));
		patterns.put(LineState.R, Pattern.compile("^R(.)(.*)"));
		patterns.put(LineState.S, Pattern.compile("^S(.)(.*)"));
		patterns.put(LineState.Z, Pattern.compile("^Z(.)"));

		// Default constraint running until a 'EOF' in adis appears
		condition = new FinishCondition() {
			@Override
			public boolean getCondition(LineState state) {
				if (state != LineState.Z)
					return true;
				return false;
			}
		};
	}

	public ADISParser(InputStream in, OutputStream out) {
		this();
		this.input = in;
		this.output = out;
	}

	public ADISParser(InputStream in, OutputStream out,
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
			lineCnt = 0;
			while (condition.getCondition(currentLineState)) {
				if ((line = reader.readLine()) != null) {
					System.out.println("got line: " + line);
					parseLine(line);
				} else {
					Thread.sleep(10);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseLine(String line) throws ADISParseException {
		// TODO: throw parse exception with error information in adis/aded
		// syntax also put that on the output stream.
		// Need to think about the return objects...
		currentLineState = null;
		LineSubState lineSubState = null;
		for (LineState ls : patterns.keySet()) {
			if (patterns.get(ls).matcher(line).find()) {
				currentLineState = ls;
			}
		}
		EntityItemParser eiParser = null;
		if (currentLineState == LineState.D) {
			eiParser = new EntityItemParser();
			if (eiParser.parseDefinition(line) == null) {
				throw new ADISParseException("", 0, "");
			}
		}
		if (currentLineState == LineState.V) {
			EntityValue ev = eiParser.parse(line);
			EntityHandler h;
			if ((h = entityHandlers.get(ev.getEntity())) != null) {
				h.handle(ev);
			}
		}
		if (currentLineState == LineState.S || currentLineState == LineState.R) {
			// TODO read all search lines (for filtering)
			// the request lines
			// a done requirement is needed: either a RO line or a TN line
			// and now give that resulting object thingy to a request handler...
			// o.O
		}
	}

	public ArrayList<EntityValue> getParsedEntities() {
		return parsedEntities;
	}

	public void addEntityHandler(EntityHandler handler) {
		entityHandlers.put(handler.entity(), handler);
	}

	public void removeEntityHandler(EntityHandler handler) {
		entityHandlers.remove(handler.entity());
	}
}
