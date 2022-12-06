package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.free_board;
import DTO.inquire;

public class inquire_controller {

	Connection conn = null;
    ResultSet rs = null; 
    Statement st = null; 
    
    public inquire_controller() { 
        try {
        	
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_team1?serverTimezone=UTC", "root",
                    "0000");
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    //문의사항 등록
    public void insert_inquire(inquire inquire) {
        try {
            st = conn.createStatement(); 
            int stmt = st.executeUpdate(
                    "insert into inquire values ('" + inquire.getNum() + "', '" + inquire.getTitle()  + 
                    "', '" + inquire.getContent() + "', '" + inquire.getUser() + "', '" + inquire.getDate() + "')");
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
    
    //문의사항 메뉴별로 검색 (구분)
    public ArrayList<inquire> search_inquire_board(String search, String title) { //드롭다운 구분(작성자, 제목) - 검색 기준
        ArrayList<inquire> arr = new ArrayList<inquire>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from inquire where "+ search +" like '%" + title + "%';");
            while (rs.next()) {
               arr.add(new inquire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5))); 
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
    	String user = "inquire_user";
    	String title = "inquire_title";
    	String blank = "";
    	if(aa.equals("작성자")) {
    		return user;
    	}
    	else if(aa.equals("제목")) {
    		return title;
    	}
    	else return blank;
    }

    
    //문의사항 출력
    public ArrayList<inquire> read_inquire() {
        ArrayList<inquire> arr = new ArrayList<inquire>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from inquire");
            while (rs.next()) {
                arr.add(new inquire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5))); 
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
}