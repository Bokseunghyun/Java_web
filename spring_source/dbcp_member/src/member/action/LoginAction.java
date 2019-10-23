package member.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.domain.MemberVO;
import member.persistence.MemberDAO;

import static member.persistence.JDBCUtill.*;//메소드들이 static으로 구성되어있기때문에 import를 static으로 만들면 JDBCUtill를 안붙이고 getConnection을 부를수 있음

public class LoginAction implements Action{
	
	private String path;
	
	public LoginAction(String path) {
		this.path=path;//view/loginForm.jsp
	}
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 비즈니스 로직 작성
		// loginForm.jsp 에서 값 가져오기
		String userid = req.getParameter("userid");
		String password = req.getParameter("current_password");
		
		//DB 작업
		Connection conn = getConnection();
		MemberDAO dao = new MemberDAO(conn);
		MemberVO vo= dao.isLogin(userid, password);
		
		
		// vo가 null이 아니면
		// 세션에 값을 담은 후 이동
		
		if(vo!=null) {
			HttpSession session=req.getSession();
			session.setAttribute("loginVO", vo);
		}
		close(conn);
		// vo가 null이면
		// 이전페이지로 돌아가기
		
		//path : 사용자에게 보여줄 페이지
		//redirect : True(sendRedirect),False(forward)
		return new ActionForward(path, true);
	}

}
