package to.mmo.adis.parser;

import to.mmo.adis.ADISException;

@SuppressWarnings("serial")
public class ADISParseException extends ADISException {

	private String failedData;
	private int position;

	public ADISParseException(String failedData, int position, String why) {
		super(why);
		this.failedData = failedData;
		this.position = position;
	}

	public ADISParseException(String why) {
		super(why);
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
		if (this.getMessage() != null)
			ret = "Why: " + this.getMessage() + "\n";
		return ret;
	}
}
