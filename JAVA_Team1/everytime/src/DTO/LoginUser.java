package DTO;

public class LoginUser {
   String id;
   String name;
   String birth;
   String password;
   String phone;
   static LoginUser loginuser; //클래스에 대한 객체
   
   public LoginUser() {
      this.id = id;
      this.name = name;
      this.birth = birth;
      this.password = password;
      this.phone = phone;
   }
   public LoginUser(String id, String password, String name, String birth,  String phone) {
      
      this.id = id;
      this.password = password;
      this.birth = birth;
      this.name = name;
      this.phone = phone;
   }
   public static LoginUser getInstance() {
      if(loginuser == null) { 
         loginuser =new LoginUser();
      }
      return loginuser; //하나라도 값이 있으면 LoginUser의 그값을 리턴 
   }
   
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getBirth() {
      return birth;
   }
   public void setBirth(String birth) {
      this.birth = birth;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   
}
