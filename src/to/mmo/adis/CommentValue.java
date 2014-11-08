package to.mmo.adis;

public class CommentValue {
	private String comment;
	private boolean error;

	public CommentValue() {

	}

	public CommentValue(String comment, boolean error) {
		this.comment = comment;
		this.error = error;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
