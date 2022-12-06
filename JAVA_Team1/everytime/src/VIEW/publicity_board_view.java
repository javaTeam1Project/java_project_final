package VIEW;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.free_board_controller;
import Controller.publicity_board_controller;
import Controller.team_board_controller;
import DTO.LoginUser;
import DTO.free_board;
import DTO.publicity_board;

//팀원 모집 게시판에 홍보 게시판 테이블의 기본키를 넘겨주기 위해서 아이디값도 가져와서 출력하는 방식으로 수정

public class publicity_board_view extends JFrame implements MouseListener {
	DefaultTableModel model;
	JTable table = new JTable();
	publicity_board_controller dao = new publicity_board_controller();
	ArrayList<publicity_board> output = new ArrayList<publicity_board>();
	String idstr = LoginUser.getInstance().getId();

	public publicity_board_view(String idstr) { // 매개변수로 로그인된 아이디 받아옴
		setTitle("홍보게시판");

		String columnNames[] = { "No.", "작성자", "제목", "내용", "작성일" };

		output = dao.read_publicity_board();
		int size = output.size();
		String data[][] = new String[size][5];

		for (int i = 0; i < output.size(); i++) {
			int id = output.get(i).getId();
			String user = output.get(i).getUser();
			String title = output.get(i).getTitle();
			String content = output.get(i).getContent();
			String date = output.get(i).getDate();
			data[i][0] = Integer.toString(id);
			data[i][1] = user;
			data[i][2] = title;
			data[i][3] = content;
			data[i][4] = date;
		}
		model = new DefaultTableModel(data, columnNames);
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ기본설정ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x 누르면 프로세스 종료
		setSize(950, 600);
		setVisible(true); // 메인프레임 보이게 하기(true)

		Container free_CP = getContentPane();
		free_CP.setLayout(new BorderLayout());

		centerWindow(this);
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ북쪽ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		JPanel North = new JPanel();

		ImageIcon markload = new ImageIcon("./everytime/image/동브리타임마크.png");
		Image img = markload.getImage();
		Image changeImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel mark = new JLabel(changeIcon);
		JLabel EveryDongEui = new JLabel("홍보게시판");
		EveryDongEui.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		North.add(mark);
		North.add(EveryDongEui);
		free_CP.add(North, BorderLayout.NORTH);

		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ센터(게시판)ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		table = new JTable(model);
		JScrollPane tableSC = new JScrollPane(table);

		startEvent();

		JPanel Center = new JPanel();
		Center.setLayout(new BorderLayout());
		Center.add(tableSC);
		free_CP.add(Center, BorderLayout.CENTER);

		// 셀의 너비를 조정하고, 글자 정렬!!
		DefaultTableCellRenderer cellCenter = new DefaultTableCellRenderer();
		cellCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer cellRight = new DefaultTableCellRenderer();
		cellRight.setHorizontalAlignment(JLabel.RIGHT);

		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ서쪽(사이드 메뉴)ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		JButton West1 = new JButton(new ImageIcon("./everytime/image/공지사항버튼.png"));
		West1.setPreferredSize(new Dimension(110, 80));
		JButton West2 = new JButton(new ImageIcon("./everytime/image/메인화면버튼.png"));
		West2.setPreferredSize(new Dimension(110, 80));
		JButton West3 = new JButton(new ImageIcon("./everytime/image/문의하기버튼.png"));
		West3.setPreferredSize(new Dimension(110, 80));
		JButton West4 = new JButton(new ImageIcon("./everytime/image/자유게시판버튼.png"));
		West4.setPreferredSize(new Dimension(110, 80));
		JButton West5 = new JButton(new ImageIcon("./everytime/image/홍보게시판버튼.png"));
		West5.setPreferredSize(new Dimension(110, 80));
		JPanel West = new JPanel();

		West.add(West1);
		West.add(West2);
		West.add(West3);
		West.add(West4);
		West.add(West5);
		West.setPreferredSize(new Dimension(120, 80));
		free_CP.add(West, BorderLayout.WEST);

		// 공지사항 화면 넘어가기
		West1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new notice_board_view(idstr);
				setVisible(false);
			}
		});

		// 메인화면 화면 넘어가기
		West2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new main_view();
				setVisible(false);
			}
		});

		// 문의하기 화면 넘어가기
		West3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new inquire_view(idstr);
				setVisible(false);
			}
		});

		// 자유게시판 화면 넘어가기
		West4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new free_board_view(idstr);
				setVisible(false);
			}
		});

		// 홍보게시판 화면 넘어가기
		West5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new publicity_board_view(idstr);
				setVisible(false);
			}
		});

		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ남쪽(검색 메뉴)ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		JPanel South = new JPanel();
		JTextField search = new JTextField(35);
		search.setPreferredSize(new Dimension(120, 30)); // setsize로 해결이 잘안되서 다른 예약어로 찾아서했음
		String[] ch2_list = { "작성자", "제목" }; // 초이스 책에있는 JCOMBOBOX랑 비슷함
		JComboBox ch2 = new JComboBox(ch2_list);

		JButton bt_search = new JButton("검색");
		JButton bt_write = new JButton("글쓰기");
		JButton bt_update = new JButton("수정");
		South.add(ch2);
		South.add(search);
		South.add(bt_search);
		South.add(bt_write);
		South.add(bt_update);
		Center.add(South, BorderLayout.SOUTH);

		bt_search.setBackground(new Color(050, 200, 100));
		bt_search.setPreferredSize(new Dimension(90, 30));
		bt_write.setBackground(new Color(255, 255, 255));
		bt_write.setFont(new Font("굴림", Font.PLAIN, 15));
		bt_write.setPreferredSize(new Dimension(90, 30));
		bt_update.setBackground(new Color(255, 255, 255));
		bt_update.setFont(new Font("굴림", Font.PLAIN, 15));
		bt_update.setPreferredSize(new Dimension(90, 30));
		ch2.setPreferredSize(new Dimension(125, 40));
		South.setPreferredSize(new Dimension(800, 50));
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ사이즈ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		// 스크롤 사이즈 설정
		tableSC.setPreferredSize(new Dimension(750, 440));
		// table 사이즈 설정
		table.setPreferredSize(new Dimension(700, 447));
		// Center Panel의 사이즈결정
		Center.setPreferredSize(new Dimension(600, 450));
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		West2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new main_view();
				setVisible(false);
			}
		});

		// 콤보박스 메뉴 별로 검색하기
		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = search.getText();
				String checksearch = ch2.getSelectedItem().toString();
				checksearch = dao.checksearch(checksearch);
				output = dao.search_publicity_board(checksearch, title);
				int size = output.size();
				String data[][] = new String[size][5];
				for (int i = 0; i < output.size(); i++) {
					String Id = Integer.toString(output.get(i).getId());
					String user = output.get(i).getUser();
					String title1 = output.get(i).getTitle();
					String content1 = output.get(i).getContent();
					String date1 = output.get(i).getDate();

					data[i][0] = Id;
					data[i][1] = user;
					data[i][2] = title1;
					data[i][3] = content1;
					data[i][4] = date1;
				}

				model = new DefaultTableModel(data, columnNames);
				table.setModel(model);
			}
		});

		// 홍보보게시판 쓰기버튼 처리
		bt_write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new publicity_board_write(idstr);
				setVisible(false);
			}
		});
		// 홍보게시판 수정버튼 처리
		bt_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicity_board_update a = new publicity_board_update(); // 로그인된 아이디 매개변수 넘겨줌
				a.frame.setVisible(true);
				setVisible(false);
			}
		});
	}

	// 모니터 중간에 프레임 출력
	public static void centerWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	public void startEvent() {
		table.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		new team_board_view(Integer.parseInt(table.getModel().getValueAt(row, 0).toString()));
		System.out.println("팀보드"+(Integer.parseInt(table.getModel().getValueAt(row, 0).toString())));
		
		setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}