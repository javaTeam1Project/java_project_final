package VIEW;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import DTO.LoginUser;
import chatting.ClientGui;

public class main_view extends JFrame {
   
   ImageIcon i = new ImageIcon("./everytime/image/메인화면뒷배경.png");
    Image im=i.getImage();
    
   String idstr = LoginUser.getInstance().getId();
   
   public main_view() {
      setTitle("메인화면");
      setLocation(700, 250);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x 누르면 프로세스 종료
      System.out.println("메인"+idstr);
      this.setLayout(null); // 화면구성 자유롭게 세팅
      setResizable(false); // 창 크기 수정불가
      //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ백그라운드 이미지ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
      myPanel1 panel = new myPanel1();
      panel.setSize(510, 720);
        add(new JButton("Hello"));
      
      this.add(panel);
      this.setSize(510, 720);
        this.setVisible(true);
        panel.setLayout(null); // 화면구성 자유롭게 세팅
      //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ백그라운드 이미지ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
      JButton btn1 = new JButton(new ImageIcon("./everytime/image/main_공지사항.png"));
      btn1.setBounds(85, 208, 130, 42);
      panel.add(btn1);
      
      JButton btn2 = new JButton(new ImageIcon("./everytime/image/main_자유게시판.png"));
      btn2.setBounds(84, 277, 130, 42);

      panel.add(btn2);
      
      JButton btn3 = new JButton(new ImageIcon("./everytime/image/main_홍보게시판.png"));
      btn3.setBounds(84, 341, 130, 42);

      panel.add(btn3);
      
      JButton btn4 = new JButton(new ImageIcon("./everytime/image/main_문의사항.png"));
      btn4.setBounds(85, 407, 130, 42);
      //btn4.setBorderPainted(false);
      //btn4.setContentAreaFilled(false);
      //btn4.setFocusPainted(false);
      panel.add(btn4);
      
      JButton btn5 = new JButton(new ImageIcon("./everytime/image/main_지도.png"));
      btn5.setBounds(85, 480, 130, 42);
      //btn5.setBorderPainted(false);
      //btn5.setContentAreaFilled(false);
      //btn5.setFocusPainted(false);
      panel.add(btn5);
      
      JButton btn6 = new JButton(new ImageIcon("./everytime/image/main_채팅.png"));
      btn6.setBounds(85, 546, 130, 42);
      //btn6.setBorderPainted(false);
      //btn6.setContentAreaFilled(false);
      //btn6.setFocusPainted(false);
      panel.add(btn6);
      
      // 공지사항 게시판 버튼 처리
      btn1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new notice_board_view(idstr); // 매개변수로 받은 아이디값 다시 매개변수로 넘겨줌
            setVisible(false);
         }
      });

      // 자유게시판 버튼 처리
      btn2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new free_board_view(idstr); // 매개변수로 받은 아이디값 다시 매개변수로 넘겨줌
            setVisible(false);
         }
      });

      // 홍보게시판 버튼 처리
      btn3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new publicity_board_view(idstr); // 매개변수로 받은 아이디값 다시 매개변수로 넘겨줌
            setVisible(false);
         }
      });

      // 지도 버튼 처리
      btn5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new map_view(); // 매개변수로 받은 아이디값 다시 매개변수로 넘겨줌
            setVisible(false);
         }
      });

      // 채팅 버튼 처리
      btn6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // ChatServer server = new ChatServer();
            // server.giveAndTake();
            try {
               InetAddress ia = InetAddress.getLocalHost();
               String ip_str = ia.toString();
               String ip = ip_str.substring(ip_str.indexOf("/") + 1);
               new ClientGui(ip, 5420);
            } catch (UnknownHostException error) {
               error.printStackTrace();
            }
         }
      });

      // 문의사항 버튼 처리
      btn4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new inquire_view(idstr);
            setVisible(false);
         }
      });
      this.setVisible(true); // 메인프레임 보이게 하기(true)
   }

   class myPanel1 extends JPanel {
      public void paint(Graphics g) {
         super.paintComponent(g);
         g.drawImage(im,0,0,null);
      }
   }
}