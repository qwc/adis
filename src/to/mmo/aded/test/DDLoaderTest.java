package to.mmo.aded.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.net.telnet.TelnetClient;

public class DDLoaderTest {

	private static BufferedReader r;
	private static Socket socket;

	public static void main(String[] args) {
		try {

			// ?AN:BENUTZER=GAST;PASSWORT=GAST;PROJEKT=lkv;LESEDB=LKVDB.ADISINT;ADISDB=LKVDB.ADISINT;
			// DH990001000000000800090000208000900003080009000040600090000624000900009080
			// VH990001DD 1997 20000418093453LKV Westfalen-Lippe e.V lkv
			// QN19000100888889150DDictionaryN 00190001080ADR2003
			// ZN
			//
			System.out.println("starting...");
			TelnetClient tc = new TelnetClient();
			tc.setReaderThread(true);
			tc.connect("213.23.26.243", 1502);
			InputStream in = tc.getInputStream();
			OutputStream out = tc.getOutputStream();

			r = new BufferedReader(new InputStreamReader(in));

			String l = null;
			out.write("?AN:BENUTZER=GAST;PASSWORT=GAST;PROJEKT=lkv;LESEDB=LKVDB.ADISINT;ADISDB=LKVDB.ADISINT;\r\n"
					.getBytes());
			out.write("DH990001000000000800090000208000900003080009000040600090000624000900009080\r\n"
					.getBytes());
			out.write("VH990001DD 1997 20000418093453LKV Westfalen-Lippe e.V lkv\r\n"
					.getBytes());
			out.write("QN19000100888889150DDictionaryN 00190001080ADR2013\r\n"
					.getBytes());
			out.write("ZN\r\n".getBytes());
			out.write("\r\n".getBytes());
			out.flush();
			int i = 0;
			while (true) {
				l = r.readLine();
				++i;
				if (l != null)
					System.out.println(i + ":" + l);
			}
			// socket.close();
			// tc.disconnect();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
