package VIEW;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.free_board_controller;
import Controller.publicity_board_controller;
import DTO.LoginUser;
import DTO.free_board;
import DTO.publicity_board;

public class publicity_board_write extends JFrame {
	static int board_count = 28; // 게시글 아이디 값은 자바에서 자동으로 증가시켜서 데이터베이스에 삽입
	// 프로그램 새로 실행할때마다 초기화되기 때문에 기존의 데이터베이스 값과 아이디가 겹칠 수 있음
	// 기존의 데이터베이스 값에 추가된 아이디 값(마지막) 이후로 매번 새로 설정
	String idstr = LoginUser.getInstance().getId();
	LocalDate SeoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));
	LocalDate date =  LocalDate.now();
	private JTextField tf = new JTextField(20);
	private JTextArea ta = new JTextArea(25, 60);
	JButton btEnter, btReset, btExit, btdel, btupdate;

	public publicity_board_write(String idstr) {
		super("홍보게시글등록");

		Container cp = getContentPane();

		cp.setLayout(new FlowLayout());

		cp.add(new JLabel("게시글 작성"));
		cp.add(tf);
		cp.add(new JScrollPane(ta));
		cp.add(btEnter = new JButton("등록"));
		cp.add(btReset = new JButton("초기화"));
		cp.add(btExit = new JButton("종료"));

		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField) e.getSource();
				ta.append(t.getText() + "\n");
				t.setText("");
				t.getText();

			}
		});

		// 게시글 등록 -> 홍보게시판
		btEnter.addActionListener((e) -> {
			publicity_board_controller dao = new publicity_board_controller(); // 데이터베이스 연동을 위해 컨트롤러 생성 필수
			String title = tf.getText();
			String content = ta.getText();
			String datestr = date.toString();
			dao.insert_publicity_board(new publicity_board(board_count, idstr, title, content, datestr));
			board_count += 1;
			setVisible(false);
			new publicity_board_view(idstr);

		});
		// 게시글 작성중 초기화
		btReset.addActionListener((e) -> {
			tf.setText("");
			ta.setText("");
			tf.requestFocus(); // 컴포넌트가 이벤트를 독점할수있는 권한
		});
		// btExit 버튼 이벤트 처리
		btExit.addActionListener((e) -> {

			setVisible(false);
			new publicity_board_view(idstr);
		});

		setSize(600, 600);
		setVisible(true);
	}

}