package VIEW;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.login_controller;
import DTO.LoginUser;


public class join_view extends JFrame {

   private JPanel contentPane;
   private JButton lblJoin;
   private JButton joinCompleteBtn;
   private JButton duplicateBtn;
   private JTextField tfUserID;
   private JTextField tfUsername;
   private JTextField tfPassword;
   private JTextField tfBirth;
   private JTextField tfPhone;

   login_controller dao = new login_controller();

   // 실행
   public class Joinrun {
      public Joinrun() {
         EventQueue.invokeLater(new Runnable() {
            public void run() {
               try {
                  join_view frame = new join_view();

               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         });
      }
   }

   // 회원가입 창
   public join_view() {
      super("회원가입");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(450, 500);
      setLocationRelativeTo(null);
      contentPane = new JPanel();

      setContentPane(contentPane);
      contentPane.setLayout(null);

      lblJoin = new JButton(new ImageIcon("./everytime/image/에브리동의.png"));
      lblJoin.setBounds(120, 20, 200, 50);
      lblJoin.setBorderPainted(false);
      lblJoin.setContentAreaFilled(false);
      lblJoin.setFocusPainted(false);
      contentPane.add(lblJoin);

      JLabel lblUsername = new JLabel("ID");
      lblUsername.setBounds(69, 113, 69, 20);
      contentPane.add(lblUsername);

      JLabel lblPassword = new JLabel("Password");
      lblPassword.setBounds(69, 163, 69, 20);
      contentPane.add(lblPassword);

      JLabel lblName = new JLabel("이름");
      lblName.setBounds(69, 213, 52, 15);
      contentPane.add(lblName);

      JLabel lblBirth = new JLabel("생년월일");
      lblBirth.setBounds(69, 267, 69, 20);
      contentPane.add(lblBirth);

      JLabel lblPhone = new JLabel("전화번호");
      lblPhone.setBounds(69, 322, 69, 20);
      contentPane.add(lblPhone);

      tfUserID = new JTextField();
      tfUserID.setColumns(10);
      tfUserID.setBounds(159, 106, 186, 35);
      contentPane.add(tfUserID);
      
      tfPassword = new JTextField();
      tfPassword.setColumns(10);
      tfPassword.setBounds(159, 156, 186, 35);
      contentPane.add(tfPassword);

      tfUsername = new JTextField();
      tfUsername.setColumns(10);
      tfUsername.setBounds(159, 203, 186, 35);
      contentPane.add(tfUsername);

      tfBirth = new JTextField();
      tfBirth.setColumns(10);
      tfBirth.setBounds(159, 257, 186, 35);
      contentPane.add(tfBirth);

      tfPhone = new JTextField();
      tfPhone.setColumns(10);
      tfPhone.setBounds(159, 312, 186, 35);
      contentPane.add(tfPhone);

      joinCompleteBtn = new JButton(new ImageIcon("./everytime/image/가입완료.png"));
      joinCompleteBtn.setBounds(298, 407, 100, 30);
      contentPane.add(joinCompleteBtn);

      duplicateBtn = new JButton(new ImageIcon("./everytime/image/중복확인.png"));
      duplicateBtn.setBounds(350, 108, 80, 30);
      contentPane.add(duplicateBtn);
      contentPane.setBackground(Color.WHITE);
      duplicateBtn.addActionListener(new ActionListener() {

         ArrayList<LoginUser> b = dao.read_user();

         @Override
         public void actionPerformed(ActionEvent e) {
            String idstr = tfUserID.getText();
            if (dao.isIDCheck(idstr) == true) {
            	 JOptionPane.showMessageDialog(null, "중복이 있습니다.. 다시입력하세요!");
               // new main_view();
               // setvisible(false);
            } else {
               JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다!");
            }

         }
      });

      joinCompleteBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            LoginUser user = new LoginUser(tfUserID.getText(), tfPassword.getText(), tfUsername.getText(), tfBirth.getText(),
                  tfPhone.getText());
            String idstr = tfUserID.getText();
            String Namestr = tfUsername.getText();
            String passtr = tfPassword.getText();
            String Birthstr = tfBirth.getText();
            String Phonestr = tfPhone.getText();

            dao.insert_user(user);

            JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
            dispose();

         }
      });
      setVisible(true);

   }
}