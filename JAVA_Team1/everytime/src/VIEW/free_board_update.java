package VIEW;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.free_board_controller;
import DTO.LoginUser;
import DTO.free_board;

public class free_board_update extends JFrame {
	free_board_controller dao = new free_board_controller(); // 데이터베이스 연동을 위해 컨트롤러 생성 필수
	public JFrame frame;
	private JTextArea textArea;
	private JTable table;
	private DefaultTableModel tableModel;
	ArrayList<free_board> output = new ArrayList<free_board>();
	String idstr = LoginUser.getInstance().getId(); //클래스의 객체를 만들어서 클래스에 set된게 아무것도 없으면 return
													//하나라도 있으면 그값을 리턴
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					free_board_update window = new free_board_update();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public free_board_update() {
		initialize(idstr); // 매개변수로 아이디 넘겨줌
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String idstr) { // 매개변수로 받아온 아이디값 활용
		frame = new JFrame();
		frame.setTitle("게시글 수정");
		frame.setBounds(100, 100, 740, 573);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel num = new JLabel("게시글 번호");
		num.setBounds(33, 57, 64, 27);
		frame.getContentPane().add(num);

		JLabel title = new JLabel("제목");
		title.setBounds(33, 107, 61, 27);
		frame.getContentPane().add(title);

		JLabel content = new JLabel("내용");
		content.setBounds(33, 217, 61, 27);
		frame.getContentPane().add(content);

		JLabel date = new JLabel("작성일");
		date.setBounds(33, 160, 61, 27);
		frame.getContentPane().add(date);

		JTextField ta = new JTextField();
		ta.setBounds(120, 58, 64, 24);
		frame.getContentPane().add(ta);
		ta.setEnabled(false);

		JTextField ta2 = new JTextField();
		ta2.setBounds(120, 108, 239, 24);
		frame.getContentPane().add(ta2);
		ta2.setEnabled(false);

		JTextField ta3 = new JTextField();
		ta3.setBounds(120, 161, 106, 24);
		frame.getContentPane().add(ta3);
		ta3.setEnabled(false);

		JTextField ta4 = new JTextField();
		ta4.setBounds(120, 218, 239, 192);
		frame.getContentPane().add(ta4);
		ta4.setEnabled(false);

		// 추가하기
		JButton btadd = new JButton("추가");
		btadd.setBounds(12, 489, 76, 23);
		frame.getContentPane().add(btadd);
		btadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new free_board_write();
				frame.setVisible(false);
			}
		});

		// 삭제기능
		JButton btdel = new JButton("삭제");
		btdel.setBounds(89, 489, 76, 23);
		frame.getContentPane().add(btdel);
		btdel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output = dao.read_free_board1(idstr); // 특정 아이디로 게시글 검색 컨트롤러 호출 이때 매개변수로 아이디값 넘겨줌
				int row = table.getSelectedRow();
				tableModel = (DefaultTableModel) table.getModel();
				tableModel.removeRow(row);
				dao.delete_free_board(output.get(row).getId());
				output.remove(row);

			}
		});

		// 검색기능
		JButton btsearch = new JButton("검색");
		btsearch.setBounds(553, 489, 64, 23);
		frame.getContentPane().add(btsearch);
		btsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = textArea.getText();
				output = dao.search_free_board1(title,idstr);
				int size = output.size();
				String data[][] = new String[size][4];
				String searchColumnNames[] = { "작성자", "제목", "내용", "작성일" };
				for (int i = 0; i < output.size(); i++) {
					String user = output.get(i).getUser();
					String title1 = output.get(i).getTitle();
					String content1 = output.get(i).getContent();
					String date1 = output.get(i).getDate();

					data[i][0] = user;
					data[i][1] = title1;
					data[i][2] = content1;
					data[i][3] = date1;
				}
				
				tableModel = new DefaultTableModel(data, searchColumnNames);
				table.setModel(tableModel);
			}
		});

		// 수정하기
		JButton udbtn = new JButton("수정");
		udbtn.setBounds(169, 489, 76, 23);
		frame.getContentPane().add(udbtn);
		udbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output = dao.read_free_board1(idstr);
				int row = table.getSelectedRow();
				tableModel = (DefaultTableModel) table.getModel();

				if (row >= 0) {
					String title1 = ta2.getText(); // 필요한 데이터 값만 추출
					String content1 = ta4.getText();

					output.get(row).setTitle(title1);
					output.get(row).setContent(content1);

					System.out.println("아이디:" + output.get(row).getId());
					System.out.println("제목:" + output.get(row).getTitle());
					System.out.println("내용:" + output.get(row).getContent());

					dao.update_free_board(output.get(row).getId(), output.get(row).getTitle(),
							output.get(row).getContent());

					new free_board_view(idstr);
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "수정할 게시글을 선택해주세요!");
				}
			}

		});

		// 전체보기
		JButton btAll = new JButton("전체보기");
		btAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String columnNames[] = { "게시글 번호", "제목", "내용", "작성일" };

				output = dao.read_free_board1(idstr);
				int size = output.size();
				String data[][] = new String[size][4];
				for (int i = 0; i < output.size(); i++) {
					String id = Integer.toString(output.get(i).getId());
					String title1 = output.get(i).getTitle();
					String content1 = output.get(i).getContent();
					String date1 = output.get(i).getDate();
					String count = output.get(i).getCount();
					data[i][0] = id;
					data[i][1] = title1;
					data[i][2] = content1;
					data[i][3] = date1;
				}

				tableModel = new DefaultTableModel(data, columnNames);
				table.setModel(tableModel);
			}
		});

		btAll.setBounds(617, 489, 95, 23);
		frame.getContentPane().add(btAll);

		textArea = new JTextArea();
		textArea.setBounds(299, 488, 248, 21);
		frame.getContentPane().add(textArea);
		textArea.setColumns(10);

		// 테이블 전체 처음에 보이는 gui
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(371, 57, 319, 375);
		frame.getContentPane().add(scrollPane);

		String columnNames[] = { "게시글 번호", "제목", "내용", "작성일" };

		output = dao.read_free_board1(idstr);
		int size = output.size();
		String data[][] = new String[size][4];

		for (int i = 0; i < output.size(); i++) {
			String id = Integer.toString(output.get(i).getId());
			String title1 = output.get(i).getTitle();
			String content1 = output.get(i).getContent();
			String date1 = output.get(i).getDate();
			String count = output.get(i).getCount();
			data[i][0] = id;
			data[i][1] = title1;
			data[i][2] = content1;
			data[i][3] = date1;
		}
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JLabel titlelbl = new JLabel("제목");
		titlelbl.setBounds(257, 493, 35, 15);
		frame.getContentPane().add(titlelbl);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int selectedIndex;
				ta2.setEnabled(true);
				ta4.setEnabled(true);
				JTable jt = (JTable) e.getSource();
				selectedIndex = jt.getSelectedRow();
				System.out.println(selectedIndex);

				String id = Integer.toString(output.get(selectedIndex).getId());
				String title1 = output.get(selectedIndex).getTitle();
				String content1 = output.get(selectedIndex).getContent();
				String date1 = output.get(selectedIndex).getDate();

				ta.setText(id);
				ta2.setText(title1);
				ta3.setText(date1);
				ta4.setText(content1);

			}
		});

	}

}