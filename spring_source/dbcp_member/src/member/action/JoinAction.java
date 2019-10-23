package member.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.domain.MemberVO;
import member.persistence.MemberDAO;

import static member.persistence.JDBCUtill.*;
public class JoinAction implements Action{

	private String path;	
	
	public JoinAction(String path) {
		super();
		this.path = path;
	}
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
			//joinForm.jsp에서 넘어오는 값 가져오기
			MemberVO vo = new MemberVO();
			vo.setUserid(req.getParameter("userid"));
			vo.setPassword(req.getParameter("password"));
			vo.setName(req.getParameter("name"));
			vo.setGender(req.getParameter("gender"));
			vo.setEmail(req.getParameter("email"));
			//DB 작업(회원가입)
			Connection conn = getConnection();
			MemberDAO dao = new MemberDAO(conn);
			int result = dao.registerMember(vo);
			
			//성공여부에따라 페이지 이동
			
			//성공 : path대로 이동
			if(result>0) {
				commit(conn);
			}else {
				//실패  : joinForm.jsp로 이동
				rollback(conn);
				path="view/joinForm.jsp";
			}
			close(conn);
			
		
		
		return new ActionForward(path,true);
	}

}
