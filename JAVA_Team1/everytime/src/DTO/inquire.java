package DTO;

public class inquire {
	int num;
	String title;
	String content;
	String user;
	String date;
	
	public inquire(int num, String title, String content, String user, String date) {
        this.num = num;
        this.title = title;
        this.content = content;
        this.user = user;
        this.date = date;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}