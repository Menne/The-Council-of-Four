package view;

public class ErrorNotify implements ViewNotify {

	private String message;

	public ErrorNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp() {
		System.out.println(message);
	}

}
