package kr.co.sist.manager.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.manager.controller.SalesViewEvt;

@SuppressWarnings("serial")
public class SalesView extends JPanel {
	private JLabel lblDate;
	private JLabel[] lblSalesName;
	private JLabel[] lblSalesValue;
	private JButton btnRe;
	
	public SalesView() {
		setLayout(null);
		this.setBackground(Color.white);
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		System.out.println(today);
		lblDate= new JLabel(today);
		btnRe = new JButton(" 갱 신 하 기 ");
		lblSalesName = new JLabel[5];
		lblSalesValue = new JLabel[5];
		
		for(int i=0;i<5;i++) {
			lblSalesName[i]=new JLabel();
			lblSalesValue[i]=new JLabel();
			lblSalesName[i].setBounds(250, 100+(75*i), 140, 50);
			lblSalesValue[i].setBounds(650,100+(75*i), 140, 50);
			lblSalesName[i].setFont(new Font("Dialog",Font.BOLD,25));
			lblSalesValue[i].setFont(new Font("Dialog",Font.BOLD,25));
			add(lblSalesName[i]);
			add(lblSalesValue[i]);
		}
		
		lblSalesName[0].setText(" 현        금 ");
		lblSalesName[1].setText(" 카        드 ");
		lblSalesName[2].setText(" 마 일 리 지 ");
		lblSalesName[3].setText(" 고   객   수 ");
		lblSalesName[4].setText(" 총 매 출 액 ");
		
		lblDate.setFont(new Font("Dialog",Font.BOLD,25));
		btnRe.setFont(new Font("Dialog",Font.BOLD,15));
		
		lblDate.setBounds(30,30,130,40);
		btnRe.setBounds(780	,30,140,40);
		add(lblDate);
		add(btnRe);
		
		SalesViewEvt  sve= new SalesViewEvt(this);
		btnRe.addActionListener(sve);
	}
	
	protected void paintComponent(Graphics g) {
		 super.paintComponent(g);  

	    Graphics2D g2=(Graphics2D)g;
	 
	    // CAP_ROUND를 사용한 직선 그리기
	 
	    g2.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,0));
	    g2.draw(new Line2D.Double(100,160,850,160));
	 
	    g2.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,0));
	    g2.draw(new Line2D.Double(100,230,850,230));

	    g2.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,0));
	    g2.draw(new Line2D.Double(100,300,850,300));

	    g2.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,0));
	    g2.draw(new Line2D.Double(100,380,850,380));
	 }

	public JLabel[] getLblSalesName() {
		return lblSalesName;
	}

	public void setLblSalesName(JLabel[] lblSalesName) {
		this.lblSalesName = lblSalesName;
	}

	public JLabel[] getLblSalesValue() {
		return lblSalesValue;
	}

	public void setLblSalesValue(JLabel[] lblSalesValue) {
		this.lblSalesValue = lblSalesValue;
	}

	public JButton getBtnRe() {
		return btnRe;
	}

	public void setBtnRe(JButton btnRe) {
		this.btnRe = btnRe;
	}
	
}
