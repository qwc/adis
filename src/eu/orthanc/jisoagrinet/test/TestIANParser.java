package eu.orthanc.jisoagrinet.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

import eu.orthanc.jisoagrinet.network.ISOagriNetParser;

public class TestIANParser {

	@Test
	public void testParser() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
		try {
			writer.write("DN190001001900010800083009108000830092080\r\n");
			writer.write("VN190001AGRO201320130101????????\r\n");
			writer.write("TN\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedInputStream in = new BufferedInputStream(
				new ByteArrayInputStream(out.toByteArray()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		ISOagriNetParser parser = new ISOagriNetParser(in, System.out);

		parser.run();

	}

}
