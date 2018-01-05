package kr.co.sist.manager.view;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
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
	private ImageIcon backgroundImg;
	
	public SalesView() {
		setLayout(null);
		
		URL url1=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/backGroundSV.png");
		URL url2=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/renew.png");
//		String path = System.getProperty("user.dir");	
//		backgroundImg= new ImageIcon(path+"/src/kr/co/sist/studyroom/img/backGroundSV.png");
		backgroundImg= new ImageIcon(url1);
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		lblDate= new JLabel(today);
//		btnRe = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/renew.png"));
		btnRe = new JButton(new ImageIcon(url2));
		lblSalesName = new JLabel[5];
		lblSalesValue = new JLabel[5];
		
		for(int i=0;i<5;i++) {
			lblSalesName[i]=new JLabel();
			lblSalesValue[i]=new JLabel();
			lblSalesName[i].setBounds(131, 230+(50*i), 140, 50);
			lblSalesValue[i].setBounds(400,230+(50*i), 140, 50);
			lblSalesName[i].setFont(new Font("Dialog",Font.BOLD,20));
			lblSalesValue[i].setFont(new Font("Dialog",Font.BOLD,20));
			add(lblSalesName[i]);
			add(lblSalesValue[i]);
		}
		
		btnRe.setBorderPainted(false);// 외곽선 제거
		btnRe.setContentAreaFilled(false); // 내용영역 채우기 x
		
		lblSalesName[0].setText(" 현        금 ");
		lblSalesName[1].setText(" 카        드 ");
		lblSalesName[2].setText(" 마 일 리 지 ");
		lblSalesName[3].setText(" 고   객   수 ");
		lblSalesName[4].setText(" 총 매 출 액 ");
		
		lblDate.setFont(new Font("Dialog",Font.BOLD,25));
		btnRe.setFont(new Font("Dialog",Font.BOLD,15));
		
		lblDate.setBounds(410,110,140,33);
		btnRe.setBounds(750	,110,140,35);
		add(lblDate);
		add(btnRe);
		
		SalesViewEvt  sve= new SalesViewEvt(this);
		btnRe.addActionListener(sve);
	}
	
	protected void paintComponent(Graphics g) {
		 g.drawImage(backgroundImg.getImage(), 0, 0, null);
		 setOpaque(false);
		 super.paintComponent(g);  

	    Graphics2D g2=(Graphics2D)g;
	 
	    // CAP_ROUND를 사용한 직선 그리기
	 
	    g2.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,0));
	    g2.draw(new Line2D.Double(120,280,720,280));
	 
	    g2.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,0));
	    g2.draw(new Line2D.Double(120,330,720,330));

	    g2.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,0));
	    g2.draw(new Line2D.Double(120,380,720,380));

	    g2.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,0));
	    g2.draw(new Line2D.Double(120,430,720,430));
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
