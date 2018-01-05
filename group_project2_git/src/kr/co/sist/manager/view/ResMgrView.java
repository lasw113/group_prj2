package kr.co.sist.manager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.co.sist.manager.controller.ResMgrViewEvt;
import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.vo.ResMgrVO;

@SuppressWarnings("serial")
public class ResMgrView extends JFrame {

	private JButton btnrenew; // ���� ��ư
	private ArrayList<JButton> list_btn; // btn ����Ʈ
	private ArrayList<JLabel> list_Jbl;
	private ArrayList<JPanel> line_panel;

	// tf���� �ľ�
	private int tf_count = 0;
	// btn�����ľ�
	private int btn_count = 0;

	private static int cnt;

	private JPanel total_line_panel;
	private JPanel totalpanel;
	@SuppressWarnings("unused")
	private List<ResMgrVO> list_count;

	ManagerDAO m_dao;
	
	URL url1=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/null_btn.png");
	URL url2=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/be_btn.png");
	URL url3=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/room_in_btn.png");
	URL url4=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/room_in_color.png");
	URL url5=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/out_btn.png");
	URL url6=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/re_canle.png");
	URL url7=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/change_btn.png");
	URL url8=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/renewal_btn.png");
	URL url9=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/����.png");
	URL url10=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/jul/�α��� �׶��̼�.png");

	@SuppressWarnings("static-access")
	public void admin_line_name(int i, JPanel line_panel, List<ResMgrVO> list) {
		JLabel jbres_num, jbRoom, jbCnt, jbName, jbIn, jbOut, jbnote;// ��, �ο���, �Խ�/��� �ð�, ��� �����ִ� textFiled
		JLabel gap1, gap2, gap3, gap4, gap5;
		JPanel[] wrap = new JPanel[12];

		for (int cnt = 0; cnt < 12; cnt++) {
			wrap[cnt] = new JPanel();
			wrap[cnt].setPreferredSize(new Dimension(50, 60));
			wrap[cnt].setBackground(new Color(255, 255, 255));
		} // end for

		jbres_num = new JLabel("�����ȣ");
		jbRoom = new JLabel("���ȣ");
		jbCnt = new JLabel("�ο���");
		jbName = new JLabel("�����ڸ�");
		jbIn = new JLabel("�Խ�");
		jbOut = new JLabel("���");
		jbnote = new JLabel("���");

		jbres_num.setHorizontalAlignment(jbres_num.CENTER);
		jbRoom.setHorizontalAlignment(jbRoom.CENTER);
		jbCnt.setHorizontalAlignment(jbCnt.CENTER);
		jbName.setHorizontalAlignment(jbName.CENTER);
		jbIn.setHorizontalAlignment(jbIn.CENTER);
		jbOut.setHorizontalAlignment(jbOut.CENTER);
		jbnote.setHorizontalAlignment(jbnote.CENTER);

		gap1 = new JLabel("");
		gap2 = new JLabel("");
		gap3 = new JLabel("");
		gap4 = new JLabel("");
		gap5 = new JLabel("");

		wrap[0].add(jbres_num);
		wrap[1].add(jbRoom);
		wrap[2].add(jbCnt);
		wrap[3].add(jbName);
		wrap[4].add(jbIn);
		wrap[5].add(jbOut);
		wrap[6].add(jbnote);
		wrap[7].add(gap1);
		wrap[8].add(gap2);
		wrap[9].add(gap3);
		wrap[10].add(gap4);
		wrap[11].add(gap5);

		for (int cnt = 0; cnt < wrap.length; cnt++) {
			line_panel.add(wrap[cnt]);
		} // end for

	}// admin_line_name

	// TextFiedl�� list�� �ֱ����� method
	@SuppressWarnings("static-access")
	public void admin_line_tf(int i, JPanel line_panel, List<ResMgrVO> list) {

		ResMgrVO rmvv;

		rmvv = list.get(i);

		// TextField �߰�
		list_Jbl.add(new JLabel(rmvv.getRes_id()));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(rmvv.getRoom_id()));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(String.valueOf(rmvv.getP_cnt()) + "��"));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(rmvv.getRes_name()));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(rmvv.getIn_time() + "��"));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(rmvv.getOut_time() + "��"));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

	}// admin_line_tf

	// Jbutton�� list�� �ֱ����� method
	public void admin_line_btn(int i, JPanel line_panel, List<ResMgrVO> list) {

		// ��ư �߰� 0 5 10 15

//		String path = System.getProperty("user.dir");
//		btnLogin = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/�α��� �׶��̼�.png"));
		// ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/�α��� �׶��̼�.png"));

		ResMgrVO rmvv;
		rmvv = list.get(i);

		// ���� ����
		if (rmvv.getRequest() == null) {
			JButton btn = new JButton(new ImageIcon(url1));
//			JButton btn = new JButton(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/null_btn.png"));
			// btn.setFont(new Font("���", Font.BOLD, 10));
			// btn.setBackground(new Color(196, 255, 122));
			list_btn.add(btn);
			line_panel.add(list_btn.get(btn_count));

			btn_count++;

		} else {
			JButton btn = new JButton(new ImageIcon(url2));
			// btn.setFont(new Font("���", Font.BOLD, 10));
			// btn.setBackground(new Color(196, 255, 122));
			list_btn.add(btn);
			line_panel.add(list_btn.get(btn_count));
			btn_count++;
		}

		JLabel gap1 = new JLabel("");
		line_panel.add(gap1);

		// �Խ�
		if ("y".equals(rmvv.getCheckin())) {
			JButton btton = new JButton(new ImageIcon(url3));
//			JButton btton = new JButton(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/room_in_btn.png"));
			// btton.setBackground(new Color(255, 128, 0));
			// btton.setFont(new Font("���", Font.BOLD, 10));
			list_btn.add(btton);
			line_panel.add(list_btn.get(btn_count));
			btn_count++;
		} else {
			JButton btton = new JButton(new ImageIcon(url4));
//			JButton btton = new JButton(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/room_in_color.png"));
			btton.setFont(new Font("���", Font.BOLD, 10));
			btton.setBackground(new Color(196, 255, 122));
			list_btn.add(btton);
			line_panel.add(list_btn.get(btn_count));
			btn_count++;
		}

		// ���
		JButton btton2 = new JButton(new ImageIcon(url5));
//		JButton btton2 = new JButton(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/out_btn.png"));
		// btton2.setFont(new Font("���", Font.BOLD, 10));
		// btton2.setBackground(new Color(196, 255, 122));
		list_btn.add(btton2);
		line_panel.add(list_btn.get(btn_count));
		btn_count++;

		// �������
		JButton btton3 = new JButton(new ImageIcon(url6));
//		JButton btton3 = new JButton(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/re_canle.png"));
		// btton3.setFont(new Font("���", Font.BOLD, 10));
		// btton3.setBackground(new Color(196, 255, 122));
		list_btn.add(btton3);
		line_panel.add(list_btn.get(btn_count));
		btn_count++;

		// ����
		JButton btton4 = new JButton(new ImageIcon(url7));
		// btton4.setFont(new Font("���", Font.BOLD, 10));
		// btton4.setBackground(new Color(196, 255, 122));
		list_btn.add(btton4);
		line_panel.add(list_btn.get(btn_count));
		btn_count++;

	}// admin_line_btn

	public JPanel ResMgrView(int count, List<ResMgrVO> list) {

		// ��ü �г�
		totalpanel = new JPanel();

		// ���Ź�ư, ��¥��ư�̵� �г��� �ִ� panel
		JPanel panel = new JPanel();

		// ��¥�� ���Ź�ư ���� �г�
		JPanel pan_date = new JPanel();
		JPanel pan_btn = new JPanel();

		pan_date.setBackground(new Color(255, 255, 255));
		pan_btn.setBackground(new Color(255, 255, 255));
		// ������ ��� �г�
		total_line_panel = new JPanel();

		// ��ư�� textfield�� �迭�� �����ϱ����ؼ� ũ�� ����
		list_btn = new ArrayList<>();
		list_Jbl = new ArrayList<>();
		line_panel = new ArrayList<>();

		// ���� ��¥ �޴� ����
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// ��¥�� �����ϱ� ��ư Ŭ��!
		JLabel now_date = new JLabel("  " + dTime + "  ");

		String path = System.getProperty("user.dir");

		btnrenew = new JButton(new ImageIcon(url8));
//		btnrenew = new JButton(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/renewal_btn.png"));
		btnrenew.setSize(70,70);

		// date�� �׵θ�ġ�� �ڵ�
		now_date.setBorder(new TitledBorder(new LineBorder(Color.black)));
		panel.setLayout(new BorderLayout());

		// ������ �гο� ũ�������ϰ�, gridlayout���� ���
		for (int i = 0; i < count; i++) {
			line_panel.add(new JPanel());
			line_panel.get(i).setBorder(new TitledBorder(new LineBorder(Color.black)));
			line_panel.get(i).setPreferredSize(new Dimension(950, 50));
			line_panel.get(i).setLayout(new GridLayout(2, 12));
			line_panel.get(i).setBackground(new Color(255, 255, 255));
		} // end for

		// ũ������
		panel.setPreferredSize(new Dimension(975, 40));
		total_line_panel.setPreferredSize(new Dimension(950, 56 * list.size()));

		// ������
		panel.setBackground(new Color(255, 255, 255));
		total_line_panel.setBackground(new Color(255, 255, 255));

		// panel�� totalpanel�� �ֱ�
		for (int i = 0; i < count; i++) {
			total_line_panel.add(line_panel.get(i));
		} // end for

		// ��, ��ư, �ؽ�Ʈ����� �����ϰ� �ֱ�!
		for (int i = 0; i < count; i++) {
			admin_line_name(i, line_panel.get(i), list);
			admin_line_tf(i, line_panel.get(i), list);
			admin_line_btn(i, line_panel.get(i), list);
		} // end for

		// ��¥�� ���Ź�ư �߰�
		pan_date.add(now_date);
		pan_btn.add(btnrenew);

		// panel�� ��ġ��� �߰�
		panel.add("Center", pan_date);
		panel.add("East", pan_btn);

		totalpanel.setBackground(new Color(255, 255, 255));
		// add�ϴ� �κ�
		totalpanel.add(panel);

		// ��ũ�� �ֱ� ���� ��!
		JScrollPane scrollPane = new JScrollPane(total_line_panel);

		scrollPane.setPreferredSize(new Dimension(980, 480));

		totalpanel.add(scrollPane);
		add(totalpanel);

		// �̺�Ʈ - ���, �Խ�, ���, �������,�߰�/����
		ResMgrViewEvt rnf = new ResMgrViewEvt(this);
		for (int i = 0; i < btn_count; i++) {
			list_btn.get(i).addActionListener(rnf);
		} // end for

		btnrenew.addActionListener(rnf);

		// ������ ũ�⼳��
		setSize(1000, 600);
		// ������ ��ġ����
		setLocationRelativeTo(null);

		// ����ȭ
		setResizable(false);
		// setVisible(true);

		// setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});// windowClosing

		return totalpanel;
	}// ResMgrView

	public JPanel Data_Judgment_Frm() {
		ResMgrView rmv = new ResMgrView();

		List<ResMgrVO> list;
		try {
			list = rmv.resetData();

			// �����Ͱ� �ϳ��� ������
			if (list.size() == 0) {
				// ��ü �г�
				totalpanel = new JPanel();

				// ���Ź�ư, ��¥��ư�̵� �г��� �ִ� panel
				JPanel panel = new JPanel();

				// ��¥�� ���Ź�ư ���� �г�
				JPanel pan_date = new JPanel();
				JPanel pan_btn = new JPanel();

				// ������ ��� �г�
				total_line_panel = new JPanel();

				// ��ư�� textfield�� �迭�� �����ϱ����ؼ� ũ�� ����
				list_btn = new ArrayList<>();
				list_Jbl = new ArrayList<>();
				line_panel = new ArrayList<>();

				// ���� ��¥ �޴� ����
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
				Date currentTime = new Date();
				String dTime = formatter.format(currentTime);

				// ��¥�� �����ϱ� ��ư Ŭ��!
				JLabel now_date = new JLabel("  " + dTime + "  ");
				// ����
				String path = System.getProperty("user.dir");
				btnrenew = new JButton(new ImageIcon(url9));
//				btnrenew = new JButton(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/����.png"));


				// date�� �׵θ�ġ�� �ڵ�
				// now_date.setBorder(new TitledBorder(new LineBorder(Color.black)));
				panel.setLayout(new BorderLayout());

				// ������ �гο� ũ�������ϰ�, gridlayout���� ���

				// ũ������
				panel.setPreferredSize(new Dimension(975, 40));
				total_line_panel.setPreferredSize(new Dimension(950, 56 * list.size()));

				panel.setBackground(new Color(255, 255, 255));

				// ��¥�� ���Ź�ư �߰�
				pan_date.add(now_date);
				pan_btn.add(btnrenew);

				// panel�� ��ġ��� �߰�
				panel.add("Center", pan_date);
				panel.add("East", pan_btn);

				// add�ϴ� �κ�
				totalpanel.add(panel);

				JPanel view = new JPanel();
				JLabel jlbl = new JLabel("�����Ͱ� ����");

				view.add(jlbl);

				totalpanel.setBackground(new Color(255, 255, 255));

				// ��ũ�� �ֱ� ���� ��!
				JScrollPane scrollPane = new JScrollPane(total_line_panel);

				scrollPane.setPreferredSize(new Dimension(980, 480));

				total_line_panel.add(view);
				totalpanel.add(scrollPane);
				add(totalpanel);

				// �̺�Ʈ - ���, �Խ�, ���, �������,�߰�/����
				ResMgrViewEvt rnf = new ResMgrViewEvt(this);

				btnrenew.addActionListener(rnf);

				// ������ ũ�⼳��
				setSize(1000, 600);
				// ������ ��ġ����
				setLocationRelativeTo(null);
				setResizable(false);

				// ����ȭ
				// setVisible(true);

				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						dispose();
					}
				});// windowClosing
			} else {// �ƴϸ� �Ʒ��ڵ� ����

				cnt = list.size();

				// rmv.ResMgrView(cnt, list);
				return rmv.ResMgrView(cnt, list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalpanel;
	}

	// db���� ������ �޾ƿ��� method
	public List<ResMgrVO> resetData() throws SQLException {
		@SuppressWarnings("unused")
		ResMgrView rmv = new ResMgrView();
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;

		list = m_dao.selectAll();
		// �����ϱ��� ������ ������ �ľ��ϱ�!
		return list;
	}

	// ���� ��ư�� ���� �� ���ο� ������ ����!
	public void setResView(List<ResMgrVO> list) {
		ResMgrView rmv = new ResMgrView();
		tf_count = 0;
		btn_count = 0;
		int size = list.size();

		if (size == 0) {
			rmv.Data_Judgment_Frm();
		} else {
			try {
				list = rmv.resetData();
				int count = list.size();
				list_btn = new ArrayList<>();
				list_Jbl = new ArrayList<>();

				// ������ ũ�⼳��
				setSize(999, 600);
				setSize(1000, 600);
				setLocationRelativeTo(null);

				// // ������ �гο� ũ�������ϰ�, gridlayout���� ���
				for (int i = 0; i < count; i++) {
					line_panel.add(new JPanel());
					line_panel.get(i).setBorder(new TitledBorder(new LineBorder(Color.black)));
					line_panel.get(i).setPreferredSize(new Dimension(950, 50));
					line_panel.get(i).setLayout(new GridLayout(2, 12));
					line_panel.get(i).setBackground(new Color(255, 255, 255));
				} // end for

				// panel�� totalpanel�� �ֱ�
				for (int i = 0; i < count; i++) {
					total_line_panel.add(line_panel.get(i));
				} // end for

				// line_panel�� ��ư, ��, �ؽ�Ʈ����� �ֱ�!
				for (int i = 0; i < count; i++) {
					admin_line_name(i, line_panel.get(i), list);
					admin_line_tf(i, line_panel.get(i), list);
					admin_line_btn(i, line_panel.get(i), list);
				} // end for

				// �̺�Ʈ - ���, �Խ�, ���, �������,�߰�/����
				ResMgrViewEvt rnf = new ResMgrViewEvt(this);
				for (int i = 0; i < btn_count; i++) {
					list_btn.get(i).addActionListener(rnf);
				} // end for

				btnrenew.addActionListener(rnf);
				setLocationRelativeTo(null);
				setResizable(false);

			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch
		}

	}

	public JButton getBtnrenew() {
		return btnrenew;
	}

	public void setBtnrenew(JButton btnrenew) {
		this.btnrenew = btnrenew;
	}

	public List<JButton> getList_btn() {
		return list_btn;
	}

	public void setList_btn(ArrayList<JButton> list_btn) {
		this.list_btn = list_btn;
	}

	public List<JLabel> getList_tf() {
		return list_Jbl;
	}

	public void setList_tf(ArrayList<JLabel> list_tf) {
		this.list_Jbl = list_tf;
	}

	public List<JPanel> getLine_panel() {
		return line_panel;
	}

	public void setLine_panel(ArrayList<JPanel> line_panel) {
		this.line_panel = line_panel;
	}

	public int getTf_count() {
		return tf_count;
	}

	public void setTf_count(int tf_count) {
		this.tf_count = tf_count;
	}

	public int getBtn_count() {
		return btn_count;
	}

	public void setBtn_count(int btn_count) {
		this.btn_count = btn_count;
	}

	public static int getCnt() {
		return cnt;
	}

	public static void setCnt(int cnt) {
		ResMgrView.cnt = cnt;
	}

	public JPanel getTotal_line_panel() {
		return total_line_panel;
	}

	public void setTotal_line_panel(JPanel total_line_panel) {
		this.total_line_panel = total_line_panel;
	}

	public JPanel getTotalpanel() {
		return totalpanel;
	}

	public void setTotalpanel(JPanel totalpanel) {
		this.totalpanel = totalpanel;
	}

	public ArrayList<JLabel> getList_Jbl() {
		return list_Jbl;
	}

	public void setList_Jbl(ArrayList<JLabel> list_Jbl) {
		this.list_Jbl = list_Jbl;
	}

}
