package to.mmo.adis.composer;

import to.mmo.aded.IItem.Format;
import to.mmo.adis.CommentValue;
import to.mmo.adis.EntityValue;
import to.mmo.adis.ItemValue;
import to.mmo.adis.ItemValue.ItemRestriction;
import to.mmo.adis.RequestValue;
import to.mmo.adis.SearchValue;

public class Composer {

	public static String compose(RequestValue value, boolean response) {
		if (response && value.isError()) {
			// TODO: all searchvalues or just the faulty? minimal: RF line with
			// the faulty request
			return "";
		} else {
			// tinker a real request together

		}
		return null;
	}

	private static String compose(SearchValue val) {
		StringBuilder b = new StringBuilder();
		b.append("S");
		if (val.isError()) {
			b.append("F");
		} else {
			b.append("N");
		}
		for (ItemValue v : val.getEntity().getValues()) {
			b.append(expandTo(v.getItem(), 8));
			b.append(expandTo("" + v.getLength(), 2));
			b.append(v.getResolution());
			ItemRestriction restriction = v.getRestriction();
			if (restriction.max == null) {
				for (int i = 0; i < v.getLength(); ++i) {
					b.append("?");
				}
			} else {
				b.append(expandTo(restriction.max, v.getLength(), v.getFormat()));
			}
			if (restriction.min == null) {
				for (int i = 0; i < v.getLength(); ++i) {
					b.append("?");
				}
			} else {
				b.append(expandTo(restriction.min, v.getLength(), v.getFormat()));
			}
		}
		b.append(expandTo(val.getMaxEntries() + "", 6, Format.N));
		b.append("\r\n");
		return b.toString();
	}

	public static String compose(EntityValue[] values)
			throws ADISComposeException {
		String entitystr = null;
		// check if all values are of the same entity
		for (EntityValue e : values) {
			if (entitystr == null)
				entitystr = e.getEntity();
			if (entitystr.compareTo(e.getEntity()) != 0) {
				throw new ADISComposeException(
						"More than one entity type within entity composer, aborting.");
			}
			for (ItemValue i : e.getValues()) {
				if (i.getFormat() == null) {
					throw new ADISComposeException(
							"A item Format is not set! Item: " + i.getItem()
									+ "");
				}
			}
		}
		EntityValue entity = values[0];
		// compose definition line
		StringBuilder b = new StringBuilder();
		b.append("D");
		if (entity.getError().isError) {
			b.append("F");
		} else {
			b.append("N");
		}
		b.append(entity.getEntity());
		for (ItemValue v : entity.getValues()) {
			b.append(expandTo(v.getItem(), 8));
			b.append(expandTo("" + v.getLength(), 2));
			b.append(v.getResolution());
		}
		b.append("\r\n");
		if (entity.getError().isError) {
			b.append("CF");
			for (int i = 0; i < entity.getError().position; ++i) {
				for (int j = 0; j < 11; ++j)
					b.append(" ");
			}
			b.append("^\r\n");
		}
		// compose value lines
		for (EntityValue e : values) {
			b.append("V");
			if (e.getItemError().isError) {
				b.append("F");
			} else {
				b.append("N");
			}
			b.append(e.getEntity());
			for (ItemValue v : e.getValues()) {
				b.append(expandTo(v.getValue(), v.getResolution(),
						v.getFormat()));
			}
			b.append("\r\n");
			if (e.getItemError().isError) {
				b.append("CF");
				for (ItemValue v : e.getValues()) {
					if (!v.getError().isError)
						for (int i = 0; i < v.getLength(); ++i)
							b.append(" ");
					else
						break;
				}
				b.append("^\r\n");
			}
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

	public static String compose(CommentValue comment) {
		StringBuilder b = new StringBuilder();
		b.append("C");
		if (comment.isError()) {
			b.append("F");
		} else {
			b.append("N");
		}
		b.append(comment.getComment()).append("\r\n");
		return b.toString();
	}
}
