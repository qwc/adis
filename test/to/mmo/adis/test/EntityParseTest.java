package to.mmo.adis.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import to.mmo.adis.EntityValue;
import to.mmo.adis.handler.EntityHandler;
import to.mmo.adis.parser.ADISStreamHandler;

public class EntityParseTest {

	private BufferedInputStream in;
	private EntityValue ev;

	@Before
	public void setup() {
		byte[] buf = new String("DN190002001900010800019000306000190052180\r\n"
				+ "VN1900021996    990001??????????????????\r\n" +
				// "VN1900021996    990002Animal            \r\n" +
				// "VN1900021996    990003Individual milking\r\n"+
				// "VN1900021996    990004Lactation         \r\n"
				"ZN\r\n").getBytes();
		in = new BufferedInputStream(new ByteArrayInputStream(buf));
	}

	@Test
	public void testParser() {
		ADISStreamHandler handler = new ADISStreamHandler(in, null);

		handler.addEntityHandler(new EntityHandler() {
			@Override
			public void handle(EntityValue evh) {
				ev = evh;
			}

			@Override
			public String entity() {
				return "190002";
			}
		});

		handler.run();

		if (ev != null) {
			assertEquals("Value has to be 1996", "1996    ", ev.getValues()
					.get(0).getValue());
			assertEquals("Value has to be 990001", "990001", ev.getValues()
					.get(1).getValue());
			assertEquals("Value has to be all '?'", null, ev.getValues().get(2)
					.getValue());
		} else
			fail();
	}
}
