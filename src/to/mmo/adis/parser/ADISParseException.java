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
		return "";
	}

}
