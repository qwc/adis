package eu.orthanc.jisoagrinet.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ISOagriNetParser extends Thread {

	private InputStream input;
	private OutputStream output;

	public ISOagriNetParser() {
	}

	public ISOagriNetParser(InputStream in, OutputStream out) {
		this.input = in;
		this.output = out;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// header
				if (line.startsWith("D")) {
					processHeader(line);
				}
				// value
				else if (line.startsWith("V")) {
					processValue(line);
				}
				// comment
				else if (line.startsWith("C")) {
					processComment(line);
				}
				// termination
				else if (line.startsWith("T")) {
					processTermination(line);
				}
				// end
				else if (line.startsWith("Z")) {
					processEnd(line);
				}
				// request
				else if (line.startsWith("R")) {
					processRequest(line);
				}
				// search
				else if (line.startsWith("S")) {
					processSearch(line);
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

	private void processHeader(String line) {

	}

}
