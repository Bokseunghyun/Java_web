package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{
	private String path;
	
	public LogoutAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//세션 해제
		HttpSession session = req.getSession();
		session.removeAttribute("loginVO"); //특정세션만 해제
		//session.invalidate(); //모든 세션 해제
		
		return new ActionForward(path, true);
	}

}
