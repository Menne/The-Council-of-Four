package view;

public class ActionNotify implements ViewNotify {

	private String message;

	public ActionNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp() {
		System.out.println(message);
	}

}
