package kr.co.sist.manager.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import kr.co.sist.manager.controller.select_payEvt;

public class select_pay extends JFrame {
	private JButton btn;

	private JRadioButton card, money;
	ButtonGroup group;

	private int number;

	public void select_pay(int cnt) {
		number = cnt;
		System.out.println("�޾ƿ� index " + number);
		JPanel total_panel = new JPanel();
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();

		btn = new JButton("Ȯ��");

		JLabel view = new JLabel("��������� �������ּ���");
		card = new JRadioButton("ī��");
		money = new JRadioButton("����");

		group = new ButtonGroup();

		group.add(card);
		group.add(money);

		panel2.setLayout(new GridLayout(1, 2));

		view.setPreferredSize(new Dimension(150, 50));
		card.setPreferredSize(new Dimension(70, 30));
		money.setPreferredSize(new Dimension(70, 30));
		btn.setPreferredSize(new Dimension(80, 20));

		panel.add(view);
		panel2.add(card);
		panel2.add(money);
		total_panel.add(panel);
		total_panel.add(panel2);
		total_panel.add(btn);

		setSize(300, 150);

		add(total_panel);
		setLocationRelativeTo(null);
		setVisible(true);

		select_payEvt spe = new select_payEvt(this);
		btn.addActionListener(spe);
		card.addItemListener(spe);
		money.addItemListener(spe);

	}// select_pay

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	public JRadioButton getCard() {
		return card;
	}

	public void setCard(JRadioButton card) {
		this.card = card;
	}

	public JRadioButton getMoney() {
		return money;
	}

	public void setMoney(JRadioButton money) {
		this.money = money;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}// select_pay
