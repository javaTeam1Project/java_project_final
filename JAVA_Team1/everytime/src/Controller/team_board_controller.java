package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.publicity_board;
import DTO.team_board;

public class team_board_controller {
    Connection conn = null;
    ResultSet rs = null; //저장된 값을 한 행 단위로 불러올 수 있다
    Statement st = null; //query 작업을 실행하기 위한 객체

    public team_board_controller() { //클래스 생성하면서 MySQL이랑 연동
        try {
        	//여기서 'java_team1'은 스키마 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_team1?serverTimezone=UTC", "root",
                    "0000");
        } catch (Exception e) { //예외처리
            e.printStackTrace();
        }
    }


    // 팀원 모집 게시글 추가
    public void insert_team_board(team_board team_board) {
        try {
            st = conn.createStatement(); //Statement 객체 생성(생성 전에 connection 연결 필수)
            int stmt = st.executeUpdate(
                    "insert into team_board values ('" + team_board.getId() + "', '" + team_board.getUser() + "', '" + team_board.getPost() + 
                    "', '" + team_board.getTitle() + "', '" + team_board.getContent() + "', '" + team_board.getDate() + "');");
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

    // 팀원 모집 게시글 목록 출력
    public ArrayList<team_board> read_team_board() {
        ArrayList<team_board> arr = new ArrayList<team_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from team_board;");
            while (rs.next()) {
            	// select문으로 출력한 team_board 리스트를 반복문을 통해 배열에 저장
                arr.add(new team_board(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6))); 
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

    // 팀원 모집 게시글 수정
    // 제목과 내용만 수정 가능
    public void update_team_board(int id, String title, String content) {
        try {
            st = conn.createStatement();
            int stmt = st
                    .executeUpdate("update team_board set team_board_title = '" + title + "', team_board_content = '" + content + "' + where team_board_id = '" + id + "';");
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

    // 팀원 모집 게시글 삭제
    public void delete_team_board(int id) {
        try {
            st = conn.createStatement();
            int stmt = st.executeUpdate("delete from team_board where team_board_id = '" + id + "';");
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
    
    // 자기 아이디에 따라 게시글 목록 출력(수정,삭제할때)
    public ArrayList<team_board> read_team_board1(String idstr) { //매개변수 -> 아이디값 넘겨 받고
        ArrayList<team_board> arr = new ArrayList<team_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from publicity_board where publicity_board_user = '"+idstr+"';"); //아이디값 변수 설정
            while (rs.next()) {
               System.out.println(rs.getInt(1));
                arr.add(new team_board(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6))); 
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
    
    
    
    // 팀원 모집 게시글 검색
    // 제목으로만 검색 가능
    public ArrayList<team_board> search_team_board(String title) {
        ArrayList<team_board> arr = new ArrayList<team_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from team_board where team_board_title like '%" + title + "%';");
            while (rs.next()) {
            	arr.add(new team_board(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6))); 
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
    
    // 특정 공모전/대외활동과 관련된 팀원 모집 게시글 출력
    public ArrayList<team_board> search_team_post(int post_id) {
        ArrayList<team_board> arr = new ArrayList<team_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from team_board where team_recruit_post like '%" + post_id + "%';");
            while (rs.next()) {
            	arr.add(new team_board(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6))); 
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
    
    //게시판 타이틀로 검색 (구분)
    public ArrayList<team_board> search_team_board(String search, String title) {
        ArrayList<team_board> arr = new ArrayList<team_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from team_board where "+ search +" like '%" + title + "%';");
            while (rs.next()) {
            	arr.add(new team_board(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6))); 
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
    
    //구분 확인하는 메서드
    public String checksearch(String aa) {
    	String user = "team_board_user";
    	String title = "team_board_title";
    	String blank = "";
    	if(aa.equals("작성자")) {
    		return user;
    	}
    	else if(aa.equals("제목")) {
    		return title;
    	}
    	else return blank;
    }
}