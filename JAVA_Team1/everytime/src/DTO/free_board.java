package DTO;

public class free_board {
	int id;
	String user;
	String title;
	String content;
	String date;
	String count = "1";
	
	public free_board(int id, String user, String title, String content, String date) {
	        this.id = id;
	        this.user = user;
	        this.title = title;
	        this.content = content;
	        this.date = date;
	        this.count = count;
	}


	public int getId() {
		return id;
	}

	
	public String getUser() {
		return user;
	}
	
	public String getTitle() {
		return title;
	}
	

	public String getContent() {
		return content;
	}
	
	
	public String getDate() {
		return date;
	}

        public void setId(int id) {
		this.id = id;
	}

        public void setUser(String user) {
		this.user = user;
	}

       public void setTitle(String title) {
		this.title = title;
	}

       public void setContent(String content) {
		this.content = content;
	}

	
	public void setDate(String date) {
		this.date = date;
	}


	public String getCount() {
		
		return count;
	}


//	public static String CurrentDateTime() {
//		if(date == null) { 
//	         date =new String();
//	      }
//	      return date;
//	}
	
	
}