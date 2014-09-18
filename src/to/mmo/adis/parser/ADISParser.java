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
import to.mmo.adis.EntityValue;
import to.mmo.adis.handler.EntityHandler;

public class ADISParser extends Thread {
	// ADIS = Agricultural Data Interchange Syntax

	private InputStream input;
	private OutputStream output;
	private ParserStates status;
	private HashMap<ADIS.LineType, Pattern> patterns;
	private long lineCnt;
	private ArrayList<EntityValue> parsedEntities;
	private FinishCondition condition;
	private ADIS.LineType currentLineState;
	private ConcurrentHashMap<String, EntityHandler> entityHandlers;

	public static enum ParserStates {
		HEADER, DATA, END, FAILURE
	};

	public static interface FinishCondition {
		boolean getCondition(ADIS.LineType state);
	}

	public ADISParser() {
		entityHandlers = new ConcurrentHashMap<String, EntityHandler>();
		System.out.println("constructing parser");
		status = ParserStates.HEADER;
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
		for (ADIS.LineType ls : patterns.keySet()) {
			if (patterns.get(ls).matcher(line).find()) {
				currentLineState = ls;
			}
		}
		EntityItemParser eiParser = null;
		if (currentLineState == ADIS.LineType.D) {
			eiParser = new EntityItemParser();
			if (eiParser.parseDefinition(line) == null) {
				throw new ADISParseException("", 0, "");
			}
		}
		if (currentLineState == ADIS.LineType.V) {
			EntityValue ev = eiParser.parse(line);
			EntityHandler h;
			if ((h = entityHandlers.get(ev.getEntity())) != null) {
				h.handle(ev);
			}
		}
		if (currentLineState == ADIS.LineType.S
				|| currentLineState == ADIS.LineType.R) {
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
