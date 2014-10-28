package to.mmo.adis.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import to.mmo.adis.EntityValue;
import to.mmo.adis.ItemValue;

public class EntityItemParser implements IParser{

	private Pattern pattern;
	private Pattern valuePattern;
	private Pattern itemsPattern;
	private EntityValue entity;

	public EntityItemParser() {
		pattern = Pattern.compile("^D(H|N|S|F|D)(\\d{6})(\\d+)$");
		itemsPattern = Pattern.compile("00(\\d{6})(\\d{2})(\\d)");
		entity = new EntityValue();
	}

	public Pattern parseDefinition(String line) {
		Matcher matcher = pattern.matcher(line);
		System.out.println(matcher.groupCount());
		if (matcher.find()) {
			for (int i = 0; i <= matcher.groupCount(); ++i) {
				System.out.println(matcher.group(i));
			}
			entity.setEntity(matcher.group(2));
			if (matcher.group(1).equals("H")) {
				entity.setHeader(true);
			} else {
				entity.setHeader(false);
			}
			Matcher m = itemsPattern.matcher(matcher.group(3));
			while (m.find()) {
				System.out.println(m.groupCount());
				for (int j = 0; j <= m.groupCount(); ++j)
					System.out.println(j + " " + m.group(j));
				entity.addValue(new ItemValue(m.group(1), Integer.parseInt(m
						.group(2)), Integer.parseInt(m.group(3))));
			}
			// generate pattern for value parsing
			String p = "^V.\\d{6}";
			for (ItemValue iv : entity.getValues()) {
				p += "(.{" + iv.getLength() + "})";
			}
			p += "$";
			System.out.println(p);
			valuePattern = Pattern.compile(p);
			return valuePattern;
		}
		return null;
	}

	public EntityValue parse(String line) {
		Matcher m = valuePattern.matcher(line);
		if (m.find()) {
			EntityValue value = new EntityValue(entity.getEntity());
			value.setHeader(entity.isHeader());
			System.out.println(m.groupCount());
			for (int i = 0; i < m.groupCount(); ++i) {
				value.addValue(new ItemValue(entity.getValues().get(i)
						.getItem(), entity.getValues().get(i).getLength(),
						entity.getValues().get(i).getResolution(), m
								.group(i + 1)));
			}
			return value;
		}
		return null;
	}

	public void reset() {
		valuePattern = null;
		entity = new EntityValue();
	}

}
