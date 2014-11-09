package to.mmo.adis;

@SuppressWarnings("serial")
public class ADISException extends Exception {

	public ADISException() {
		super();
	}

	public ADISException(String message) {
		super(message);
	}

	public ADISException(Throwable cause) {
		super(cause);
	}

	public ADISException(String message, Throwable cause) {
		super(message, cause);
	}

	public ADISException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}