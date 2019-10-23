package member.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.domain.MemberVO;
import member.persistence.MemberDAO;
import static member.persistence.JDBCUtill.*;
public class LeaveAction implements Action{
	
	private String path;
	
	
	public LeaveAction(String path) {
		super();
		this.path = path;
	}


	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//leaveForm.jsp에서 넘긴 값 가져오기
		//userid, current_password
		MemberVO vo = new MemberVO();
		vo.setUserid(req.getParameter("userid"));
		vo.setPassword(req.getParameter("current_password"));
		
		//DB작업
		Connection conn = getConnection();
		MemberDAO dao = new MemberDAO(conn);
		int result = dao.leaveMember(vo);
		//성공 : commit / session 해제
		HttpSession session = req.getSession();
		
		if(result>0) {
			conn.commit();
			session.invalidate();
			System.out.println("탈퇴 성공");
		}else {
			conn.rollback();
			path="view/leaveForm.jsp";
		}
		
		return new ActionForward(path,true);
	}

}
