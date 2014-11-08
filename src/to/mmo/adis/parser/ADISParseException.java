package to.mmo.adis.parser;

@SuppressWarnings("serial")
public class ADISParseException extends Exception {

	private String why;
	private String failedData;
	private int position;

	public ADISParseException(String failedData, int position, String why) {
		super(why);
		this.failedData = failedData;
		this.position = position;
		this.why = why;
	}

	public ADISParseException(String why) {
		super(why);
		this.why = why;
	}

	public String toString() {
		String ret = "";
		if (failedData != null) {
			ret = failedData + "\n";
			for (int i = 0; i < position - 1; ++i) {
				ret += " ";
			}
			ret += "^\n";
		}
		if (why != null)
			ret = "Why: " + why + "\n";
		return ret;
	}
}
