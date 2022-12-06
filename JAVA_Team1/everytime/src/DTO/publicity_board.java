package DTO;

public class publicity_board {
	int id;
	String user;
	String title;
	String content;
	String date;
	
	 public publicity_board(int id, String user, String title, String content, String date) {
	        this.id = id;
	        this.user = user;
	        this.title = title;
	        this.content = content;
	        this.date = date;
	}
	 
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
		
}