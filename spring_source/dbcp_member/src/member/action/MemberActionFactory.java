package member.action;

public class MemberActionFactory {
	private static MemberActionFactory maf;
	
	private MemberActionFactory() {}
	
	public static MemberActionFactory getInstance() {
		if(maf==null)
			maf= new MemberActionFactory();
		return maf;
	}
	private Action action=null;
	//cmd에 의해 알맞은 Action을 생성하고
		//각 Action이 일을 끝낸 후 사용자에게 보여줄 페이지를
		//세팅하도록 한다.
		public Action action(String cmd) {
			
			if(cmd.equals("/login.do")) {			
				action = new LoginAction("view/loginForm.jsp");
			}else if(cmd.equals("/join.do")) {
				action = new JoinAction("view/loginForm.jsp");
			}else if(cmd.equals("/logout.do")) {
				action = new LogoutAction("index.jsp");
			}else if(cmd.equals("/leave.do")) {
				action = new LeaveAction("index.jsp");
			}else if(cmd.equals("/modify.do")) {
				action = new ModifyAction("view/loginForm.jsp");
			}else if(cmd.equals("/dup.do")) {
				action = new CheckIdAction("view/dupId.jsp");
			}
			
			return action;
		}
		
}
