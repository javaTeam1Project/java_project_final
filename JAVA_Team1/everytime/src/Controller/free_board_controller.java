package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.free_board;
import java.time.LocalDate;
import java.time.ZoneId;
public class free_board_controller {
    Connection conn = null;
    ResultSet rs = null; 
    Statement st = null; 

    public free_board_controller() { 
        try {
              conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_team1?serverTimezone=UTC", "root",
	                    "0000");
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    // 게시글 추가
    public void insert_free_board(free_board free_board) {
        try {
            st = conn.createStatement(); 
            int stmt = st.executeUpdate(
                    "insert into free_board values ('" + free_board.getId() + "', '" + free_board.getUser() + "', '"
            + free_board.getTitle() + "', '" + free_board.getContent() + "', '" + free_board.getDate() + "');");
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
        
//    public class CurrentDateTime {
//        public static void main(String[] args) {
//     
//            // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
//            LocalDate now = LocalDate.now();
//            // 현재 날짜 구하기(서울)
//            LocalDate SeoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));
//            // 결과 출력
//            System.out.println(now);      
//            System.out.println(SeoulNow);
//        }
//    }
    
    

    // 모든 게시글 목록 출력
    public ArrayList<free_board> read_free_board() {
        ArrayList<free_board> arr = new ArrayList<free_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from free_board;");
            while (rs.next()) {
               System.out.println(rs.getInt(1));
                arr.add(new free_board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5))); 
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
    
    // 자기 아이디에 따라 게시글 목록 출력(수정,삭제할때)
    public ArrayList<free_board> read_free_board1(String idstr) { //매개변수 -> 아이디값 넘겨 받고
        ArrayList<free_board> arr = new ArrayList<free_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from free_board where free_board_user ='"+idstr+"';"); //아이디값 변수 설정 //아이디값 변수 설정
            while (rs.next()) {
               System.out.println(rs.getInt(1));
                arr.add(new free_board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5))); 
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
    
    // 게시글 수정  
    public void update_free_board(int id, String title, String content) {
        try {
            st = conn.createStatement();
            int stmt = st
                    .executeUpdate("update free_board set free_board_title = '" + title + "', free_board_content = '" + content + "' where free_board_id = '" + id + "';");
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
    // 자기의 게시글 갯수를 출력하는 메소드
    public int Content_List(String free_board_user) { 
       int number = 0;
       try {
            st = conn.createStatement();
            rs = st.executeQuery("select count(*) from free_board where free_board_user = '" + free_board_user + "';");
            while (rs.next()) {
               number = rs.getInt(1);
               System.out.println(number);
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
       return number;
    }

    // 게시글 삭제
    public void delete_free_board(int id) {
        try {
            st = conn.createStatement();
            int stmt = st.executeUpdate("delete from free_board where free_board_id = '" + id + "';");
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

    //게시판 타이틀로 검색
    public ArrayList<free_board> search_free_board1(String title, String idstr) {
        ArrayList<free_board> arr = new ArrayList<free_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from free_board where free_board_user ='"+idstr+"' && free_board_title like '%"+title+"%';");
            while (rs.next()) {
               arr.add(new free_board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5))); 
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
    public ArrayList<free_board> search_free_board(String search, String title, String idstr) {
        ArrayList<free_board> arr = new ArrayList<free_board>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from free_board where free_board_user ='"+idstr+"' &&"+ search +" like '%" + title + "%';");
            while (rs.next()) {
               arr.add(new free_board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5))); 
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
    	String user = "free_board_user";
    	String title = "free_board_title";
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
    
   