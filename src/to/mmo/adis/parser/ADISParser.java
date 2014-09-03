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
import java.util.regex.Pattern;

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

	private Object parseLine(String line) {
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
				return null;
			}
		}
		if (currentLineState == LineState.V) {
			return eiParser.parse(line);
		}
		return null;
	}

	public ArrayList<EntityValue> getParsedEntities() {
		return parsedEntities;
	}
}
