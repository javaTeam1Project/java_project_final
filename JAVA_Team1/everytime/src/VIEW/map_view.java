package VIEW;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class map_view extends JFrame{
   ImageIcon i = new ImageIcon("./everytime/image/동의대지도.jpg");
    Image im=i.getImage();
    JButton mapout;
   public map_view() {
      setTitle("지도");
      setLocation(500, 200);
      setSize(880,680);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false); // 창 크기 수정불가
      
      myPanel2 panel = new myPanel2(); 
      panel.setSize(880,680);
      setVisible(true);
      this.add(panel); 
      
      mapout = new JButton(new ImageIcon("./everytime/image/나가기.png"));
      mapout.setBounds(780, 20, 55, 55);
      mapout.setBorderPainted(false);
      //mapout.setContentAreaFilled(false);
      mapout.setFocusPainted(false);
      
      panel.add(mapout);
      panel.setLayout(null);
         // 메인화면 화면 넘어가기
         mapout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new main_view();
               setVisible(false);
            }
         });
   }
   class myPanel2 extends JPanel {
      public void paint(Graphics g) {
         super.paintComponent(g);
         g.drawImage(im,0,0,null);
      }
   }
}
   

