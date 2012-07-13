package eu.orthanc.jisoagrinet.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ISOagriNetParser extends Thread {

	private InputStream input;
	private OutputStream output;
	private Pattern lineDPattern;
	private Pattern lineZPattern;
	private Pattern lineTPattern;
	private Pattern lineSPattern;
	private Pattern lineRPattern;
	private Pattern lineCPattern;
	private Pattern lineVPattern;
	private Pattern definistionPattern;

	public ISOagriNetParser() {
	}

	// recfunction(string,pattern.first()) {
	// suche pattern;
	// if erfolg patternmatch++ else patternmatch--
	// orderpattern()

	public ISOagriNetParser(InputStream in, OutputStream out) {
		this.input = in;
		this.output = out;

		definistionPattern = Pattern
				.compile("^(D)(.)(\\d{6})(00(\\d{6})(\\d{2})(\\d))*");
		lineDPattern = Pattern.compile("^D.*");
		lineVPattern = Pattern.compile("^V.*");
		lineCPattern = Pattern.compile("^C.*");
		lineRPattern = Pattern.compile("^R.*");
		lineSPattern = Pattern.compile("^S.*");
		lineTPattern = Pattern.compile("^T.");
		lineZPattern = Pattern.compile("^Z.");
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line = null;
			Matcher m = null;
			while ((line = reader.readLine()) != null) {
				m = lineVPattern.matcher(line);
				if (m.find()) {
					processValue(line);
					continue;
				}
				m = lineDPattern.matcher(line);
				if (m.find()) {
					processDefinition(line);
					continue;
				}
				m = lineTPattern.matcher(line);
				if (m.find()) {
					processTermination(line);
					continue;
				}
				m = lineZPattern.matcher(line);
				if (m.find()) {
					processEnd(line);
					continue;
				}
				m = lineCPattern.matcher(line);
				if (m.find()) {
					processComment(line);
					continue;
				}
				m = lineSPattern.matcher(line);
				if (m.find()) {
					processSearch(line);
					continue;
				}
				m = lineRPattern.matcher(line);
				if (m.find()) {
					processRequest(line);
				}
			}
		} catch (Exception e) {

		}
	}

	private void processSearch(String line) {

	}

	private void processRequest(String line) {

	}

	private void processEnd(String line) {

	}

	private void processTermination(String line) {

	}

	private void processComment(String line) {

	}

	private void processValue(String line) {

	}

	private void processDefinition(String line) {

	}

}
