package to.mmo.adis.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import to.mmo.adis.EntityValue;
import to.mmo.adis.ItemValue;
import to.mmo.adis.ItemValue.ItemRestriction;
import to.mmo.adis.SearchValue;

public class SearchParser implements IParser {

	private Pattern pattern;
	private Pattern itemPattern;

	public SearchParser() {
		pattern = Pattern.compile("^S(N)(\\d{6})(.+)([0-9 ]{6})$");
		itemPattern = Pattern.compile("(\\d{8})(\\d{2})(\\d)");
	}

	@Override
	public SearchValue parse(String line) {
		SearchValue search = new SearchValue();
		EntityValue entity = new EntityValue();
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			entity.setEntity(matcher.group(2));
			search.setMaxEntries(Integer.parseInt(matcher.group(4)));

			Matcher m = itemPattern.matcher(matcher.group(3));
			String rest = matcher.group(3);
			int start = 0;
			while (m.find(start)) {
				ItemValue v = new ItemValue(m.group(1), Integer.parseInt(m
						.group(2)), Integer.parseInt(m.group(3)));
				ItemRestriction ir = new ItemRestriction();
				ir.min = rest.substring(m.end(3), m.end(3) + v.getLength());
				ir.max = rest.substring(m.end(3) + v.getLength(), m.end(3) + 2
						* v.getLength());
				v.setRestriction(ir);
				entity.addValue(v);
				start = m.end(3)+2*v.getLength();
			}
			search.setEntity(entity);
			return search;
		}
		return null;
	}

}
