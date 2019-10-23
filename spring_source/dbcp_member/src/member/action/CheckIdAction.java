package member.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.persistence.MemberDAO;
import static member.persistence.JDBCUtill.*;
public class CheckIdAction implements Action{
	private String path;
	
	public CheckIdAction(String path) {
		super();
		this.path = path; // view/dupId.jsp
	}

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userid = req.getParameter("userid");
		Connection conn = getConnection();
		MemberDAO dao = new MemberDAO(conn);
		boolean flag=dao.checkId(userid);
		
		//flag가 true 이면 중복아이디인 상황(사용불가)
		if(flag) { //false를 보내줘야 메시지를 보여줌
			req.setAttribute("flag", "false");
		}else {
			req.setAttribute("flag", "true");
		}
		return new ActionForward(path, true);
	}

}
