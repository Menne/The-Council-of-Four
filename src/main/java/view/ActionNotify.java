package view;

public class ActionNotify implements ViewNotify {

	private String message;

	public ActionNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(CLI view) {
		System.out.println(message);
	}

}
