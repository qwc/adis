package to.mmo.adis.composer;

import to.mmo.aded.IItem.Format;
import to.mmo.adis.EntityValue;
import to.mmo.adis.ItemValue;
import to.mmo.adis.parser.ADISParseException;

public class EntityComposer {

	public static String compose(EntityValue[] values)
			throws ADISParseException {
		String entitystr = null;
		// check if all values are of the same entity
		for (EntityValue e : values) {
			if (entitystr == null)
				entitystr = e.getEntity();
			if (entitystr.compareTo(e.getEntity()) != 0) {
				throw new ADISParseException(null, 0,
						"More than one entity type within entity composer, aborting.");
			}
		}
		EntityValue entity = values[0];
		// compose definition line
		StringBuilder b = new StringBuilder();
		b.append("DN").append(entity.getEntity());
		for (ItemValue v : entity.getValues()) {
			b.append(expandTo(v.getItem(), 8));
			b.append(expandTo("" + v.getLength(), 2));
			b.append(v.getResolution());
		}
		b.append("\r\n");
		for (EntityValue e : values) {
			b.append("VN");
			b.append(e.getEntity());
			for (ItemValue v : e.getValues()) {
				b.append(expandTo(v.getValue(), v.getResolution(),
						v.getFormat()));
			}
			b.append("\r\n");
		}

		return b.toString();
	}

	private static String expandTo(String input, int length) {
		return expandTo(input, length, null);
	}

	private static String expandTo(String input, int length, Format format) {
		String ret = "";
		if (format == null) {
			for (int i = input.length(); i < length; ++i) {
				ret = "0" + ret;
			}
		} else {
			if (format == Format.N) {
				for (int i = input.length(); i < length; ++i) {
					ret = " " + ret;
				}
			} else {
				for (int i = input.length(); i < length; ++i) {
					ret += " ";
				}
			}
		}
		return ret;
	}
}
