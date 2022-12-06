package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.LoginUser;


public class login_controller {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null; // 저장된 값을 한 행 단위로 불러올 수 있다
	Statement st = null; // query 작업을 실행하기 위한 객체
	boolean flag = false;

	public login_controller() { // 클래스 생성하면서 MySQL이랑 연동
		try {
			// 여기서 'java_team1'은 스키마
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_team1?serverTimezone=UTC", "root",
					"0000");
		} catch (Exception e) { // 예외처리
			e.printStackTrace();
		}
	}
	
	public void login_user(String id) {
		//LoginUser user = new LoginUser로 선언하면 날라감..
		LoginUser.getInstance().setId(id);
		System.out.println(".."+LoginUser.getInstance().getId());
	}
	
	// 회원가입 추가
    public void insert_user(LoginUser user) {
        try {
            st = conn.createStatement(); //Statement 객체 생성(생성 전에 connection 연결 필수)
            int stmt = st.executeUpdate(
                    "insert into user values ('" + user.getId() + "', '" +
                    		user.getName()  + "', '" + user.getBirth() + "', '" + user.getPassword() + "', '" + user.getPhone() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	// 유저 테이블 뽑아오는 메서드
	public ArrayList<LoginUser> read_user() {
		ArrayList<LoginUser> arr = new ArrayList<LoginUser>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from user;");
			while (rs.next()) {
				
				arr.add(new LoginUser(rs.getString(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
	
	// 회원가입 아이디 중복 체크하는 메서드
		public boolean isIDCheck(String id) {
			ArrayList<LoginUser> a = read_user();
			int count = 0;
			boolean check = false;
			for (int i = 0; i < a.size(); i++) {
				if (a.get(i).getId().equals(id)) {
					count++;
					if (count == 1) {
						check = true;
					} else {
						check = false;
					}
				}
			}
			return check;
		}
	
	
	// 로그인 아이디 비번 체크하는 메서드
	public boolean isLoginCheck(String id, String password) {
		ArrayList<LoginUser> a = read_user();
		int count = 0;
		boolean check = false;

		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).getId().equals(id)) {
				count++;
				if (a.get(i).getPassword().equals(password)) {
					count++;
					if (count == 2) {
						check = true;
					} else {
						check = false;
					}
				}
			}

		}
		return check;
	}
	
	


	// 로그아웃 (미완)
	boolean logoutCheck() {
		if (flag == true) {
			System.out.println("로그아웃 합니다.");
			flag = false;
		}
		return flag;
	}
}
