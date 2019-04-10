@SuppressWarnings("serial")
public class TadoException extends Exception {
	private String code;
	private String title;

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public TadoException(String code, String title) {
		super(code + ": " + title);
		this.code = code;
		this.title = title;
	}
}