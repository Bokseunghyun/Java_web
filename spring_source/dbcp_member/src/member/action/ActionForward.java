package member.action;

public class ActionForward {
	//경로와 이동방법을 가지고 있는 클래스
	//forward : request 속성에 값을 담는 경우
	//sendRedirect : session 속성에 값을 담는 경우
	
	private String path; //action 일을 끝낸 후 이동할 경로
	private boolean redirect; //true(sendRedirect) false(forward)
	
	public ActionForward() {
		super();
	}

	public ActionForward(String path, boolean redirect) {
		super();
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	
}
