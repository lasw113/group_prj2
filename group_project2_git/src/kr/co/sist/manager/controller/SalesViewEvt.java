package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;

import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.SalesView;
import kr.co.sist.manager.vo.SalesVO;

public class SalesViewEvt implements ActionListener{
	private SalesView sv;
	private SalesVO svo;
	private ManagerDAO m_dao;
	
	public SalesViewEvt(SalesView sv){
		this.sv = sv;
		setSales();
	}//SalesViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==sv.getBtnRe()) {
			reSales();
		}//end if
	}//actionPerformed
	private void reSales() {//���� ������ �����ϴ� �޼ҵ�
		svo= new SalesVO();
		m_dao=ManagerDAO.getInstance();
		JLabel[] lblTemp = sv.getLblSalesValue();
		try {
			svo=m_dao.selectSales();
			lblTemp[0].setText(String.valueOf(svo.getCash())+" �� ");
			lblTemp[1].setText(String.valueOf(svo.getCard())+" �� ");
			lblTemp[2].setText(String.valueOf(svo.getMilleage())+" �� ");
			lblTemp[3].setText(String.valueOf(svo.getT_cnt())+" �� ");
			lblTemp[4].setText(String.valueOf(svo.getT_sales())+ " �� ");
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//reSales
	private void setSales() {//���� ������ ��ġ�ϴ� �޼ҵ�
		svo=new SalesVO();
		JLabel[] lblTemp = sv.getLblSalesValue();
		m_dao=ManagerDAO.getInstance();
		try {
			svo=m_dao.selectSales();
			lblTemp[0].setText(String.valueOf(svo.getCash())+" �� ");
			lblTemp[1].setText(String.valueOf(svo.getCard())+" �� ");
			lblTemp[2].setText(String.valueOf(svo.getMilleage())+" �� ");
			lblTemp[3].setText(String.valueOf(svo.getT_cnt())+" �� ");
			lblTemp[4].setText(String.valueOf(svo.getT_sales())+ " �� ");
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//setSales
}//class
