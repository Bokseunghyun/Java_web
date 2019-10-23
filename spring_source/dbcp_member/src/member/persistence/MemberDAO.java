package member.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import member.domain.MemberVO;
import static member.persistence.JDBCUtill.*;

public class MemberDAO {
	private Connection conn;
	
	public MemberDAO(Connection conn) {
		super();
		this.conn=conn;
	}
	
	public MemberVO isLogin(String userid, String password) {
		//userid와 password가 일치하면
		//userid와 name을 담아 리턴하기
		MemberVO vo = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql="select userid,name from member where userid=? and password=?";
		try {
			pstm=conn.prepareStatement(sql);
				pstm.setString(1, userid);
				pstm.setString(2, password);
				rs=pstm.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setUserid(rs.getString(1));
				vo.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		
		return vo;
	}
	
	public int registerMember(MemberVO vo) {
		//회원가입(아이디,비밀번호,이름,성별,이메일)
		int result=0;
		PreparedStatement pstm = null;
		try {
			
			String sql = "insert into member values(?,?,?,?,?)";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,vo.getUserid());
			pstm.setString(2,vo.getPassword());
			pstm.setString(3,vo.getName());
			pstm.setString(4,vo.getGender());
			pstm.setString(5,vo.getEmail());
			result=pstm.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			close(pstm);
		}
		return result;
		
	}
	public int leaveMember(MemberVO vo) {
		//회원탈퇴
		int result=0;
		PreparedStatement pstm = null;
		try {
			
			String sql = "delete from member where userid=? and password=?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,vo.getUserid());
			pstm.setString(2, vo.getPassword());
			result=pstm.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			close(pstm);
		}
		return result;
		
	}
	public int modifyMember(String userid, String password) {
		//회원탈퇴
		int result=0;
		PreparedStatement pstm = null;
		try {
			
			String sql = "update member set password=? where userid=?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,password);
			pstm.setString(2,userid);
			result=pstm.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			close(pstm);
		}
		return result;
		
	}
	
	//중복 아이디 검사
	
	public boolean checkId(String userid) {
		boolean flag = false;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		//rs.next() 있다면 flag를 true 변경하기
		//중복된 아이디가 있다는 의미
		String sql = "select userid from member where userid=?";
		try {
			pstm= conn.prepareStatement(sql);
			pstm.setString(1, userid);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				flag=true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		
		
		
		return flag;
	}
	
}
