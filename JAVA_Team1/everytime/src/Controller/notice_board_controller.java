package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.inquire;
import DTO.notice_board;

public class notice_board_controller {

	Connection conn = null;
	ResultSet rs = null; // 저장된 값을 한 행 단위로 불러올 수 있다
	Statement st = null; // query 작업을 실행하기 위한 객체

	public notice_board_controller() { // 클래스 생성하면서 MySQL이랑 연동
		try {
			// 여기서 'java_team1'은 스키마
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_team1?serverTimezone=UTC", "root",
					"0000");
		} catch (Exception e) { // 예외처리
			e.printStackTrace();
		}
	}

	// 공지 게시글 추가
	public void insert_publicity_board(notice_board publicity_board) {
		try {
			st = conn.createStatement(); // Statement 객체 생성(생성 전에 connection 연결 필수)
			int stmt = st.executeUpdate("insert into publicity_board values ('" + publicity_board.getId() + "', '"
					+ publicity_board.getTitle() + "', '" + publicity_board.getContent() + "', '"
					+ publicity_board.getDate() + "');");
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

	// 공지 게시글 목록 출력
	public ArrayList<notice_board> read_notice_board() {
		ArrayList<notice_board> arr = new ArrayList<notice_board>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from notice_board;");
			while (rs.next()) {
				// select문으로 출력한 team_board 리스트를 반복문을 통해 배열에 저장
				arr.add(new notice_board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
	// 공지게시글 수정

	public void update_notice_board(int id, String title, String content) {
		try {
			st = conn.createStatement();
			int stmt = st.executeUpdate("update notice_board set notice_board_title = '" + title
					+ "', notice_board_content = '" + content + "' + where notice_board_id = '" + id + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 공지게시글 삭제
	public void delete_notice_board() {
		try {
			st = conn.createStatement();
			int stmt = st.executeUpdate("delete from notice_board where notice_board_id = 'kkdd13';");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 공지 게시물 검색
	public ArrayList<notice_board> search_notice_board(String title) {
		ArrayList<notice_board> arr = new ArrayList<notice_board>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from notice_board where notice_board_title like '%" + title + "%';");
			while (rs.next()) {
				arr.add(new notice_board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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

	// 문의사항 메뉴별로 검색 (구분)
	public ArrayList<notice_board> search_notice_board(String search, String title) {
		ArrayList<notice_board> arr = new ArrayList<notice_board>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from notice_board where " + search + " like '%" + title + "%';");
			while (rs.next()) {
				arr.add(new notice_board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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

	// 구분 확인하는 메서드
	public String checksearch(String aa) {
		String user = "notice_board_id";
		String title = "notice_board_title";
		String blank = "";
		if (aa.equals("글번호")) {
			return user;
		} else if (aa.equals("제목")) {
			return title;
		} else
			return blank;
	}

}
