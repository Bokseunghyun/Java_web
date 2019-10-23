package member.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.domain.MemberVO;
import member.persistence.MemberDAO;

import static member.persistence.JDBCUtill.*;
public class ModifyAction implements Action{
	private String path;
	
	public ModifyAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//modifyForm.jsp에서 넘긴 값 가져오기
		//current_password, new_password, confirm_password
		HttpSession session = req.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("loginVO");
		
		String userid = vo.getUserid();
		String password = req.getParameter("current_password");
		String new_password = req.getParameter("new_password");
		String confirm_password = req.getParameter("confirm_password");
		
		//DB작업
		//userid와 current_password를 이용해서 현재 비밀번호가 맞게 들어왔는지 확인 -> isLogin
		Connection conn = getConnection();
		MemberDAO dao = new MemberDAO(conn);
		MemberVO vo1 = dao.isLogin(userid, password);
		
		if(vo1!=null) {
			//비밀번호가 맞다면 수정 작업
			//비밀번호 수정 성공: commit, session 해제
			int result = dao.modifyMember(userid, confirm_password);
			
			if(result>0) {
				conn.commit();
				session.removeAttribute("loginVO");
			}else {
				//비밀번호 수정 실패 : rollback, path(modifyForm.jsp)
				conn.rollback();
				path="view/modifyForm.jsp";
			}
		}else {
			path="view/modifyForm.jsp";
		}
		
		
		return new ActionForward(path, true);
	}

}
