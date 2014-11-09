package to.mmo.adis.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import to.mmo.adis.ADISException;
import to.mmo.adis.EntityValue;
import to.mmo.adis.ItemValue;
import to.mmo.adis.RequestValue;

public class RequestParser implements IParser {
	private Pattern pattern;
	private Pattern itemPattern;
	private Pattern failPattern;

	public RequestParser() {
		pattern = Pattern.compile("^R(N)(\\d{6})(\\d+)$");
		failPattern = Pattern.compile("^RF(.*)$");
		itemPattern = Pattern.compile("((\\d{8})(\\d{2})(\\d))+");
	}

	@Override
	public RequestValue parse(String line) throws ADISException {
		RequestValue request = new RequestValue();
		EntityValue entity = new EntityValue();
		Matcher matcher = pattern.matcher(line);
		System.out.println(matcher.groupCount());
		if (matcher.find()) {
			entity.setEntity(matcher.group(2));
			Matcher itemMatcher = itemPattern.matcher(matcher.group(3));
			while (itemMatcher.find()) {
				entity.addValue(new ItemValue(itemMatcher.group(1), Integer
						.parseInt(itemMatcher.group(2)), Integer
						.parseInt(itemMatcher.group(3))));
			}
			request.setEntity(entity);
			return request;
		}
		else {
			Matcher m = failPattern.matcher(line);
			if(m.find()) {
				throw new ADISParseException(m.group(1));
			}
		}
		return null;
	}
}
