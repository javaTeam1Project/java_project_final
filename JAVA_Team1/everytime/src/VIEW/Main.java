package VIEW;

import DTO.LoginUser;
import VIEW.main_view;

public class Main {
	String idstr = LoginUser.getInstance().getId();
	public static void main(String[] args) {
		
		new login_view();

		// 제일 처음에 main_view 말고 login_view 생성
		// login controller에서 로그인된 아이디 리턴->main_view 생성하면서 매개변수로 아이디값 넘겨줌
	}
}
