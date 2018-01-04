package kr.co.sist.client.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.co.sist.client.vo.ChangePassVO;
import kr.co.sist.client.vo.DeleteVO;
import kr.co.sist.client.vo.FindIDVO;
import kr.co.sist.client.vo.FindPassVO;
import kr.co.sist.client.vo.InfoChangeVO;
import kr.co.sist.client.vo.JoinVO;
import kr.co.sist.client.vo.LoginVO;
import kr.co.sist.client.vo.MyInfoVO;
import kr.co.sist.client.vo.SelectUserVO;

/**
 * �̱��� Ŭ����
 * 
 * @author user
 *
 */
public class ClientDAO {
	private static ClientDAO c_dao;

	private ClientDAO() {

	}// ClientDAO

	public static ClientDAO getInstance() {
		if (c_dao == null) {
			c_dao = new ClientDAO();
		} // end if
		return c_dao;
	}

	/**
	 * Ŀ�ؼ�
	 * @return
	 * @throws SQLException
	 */
	private Connection getConn() throws SQLException {
		Connection con = null;
		Properties prop = new Properties();
		String path = System.getProperty("user.dir");
		try {
			prop.load(new FileReader(
					path+"/db/database.properties"));

			Class.forName(prop.getProperty("driverClass"));

			con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("id"),
					prop.getProperty("pass"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // end catch

		return con;

	}// getConn

	private void dbClose(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (con != null) {
			con.close();
		}
	}// dbClose

	/**
	 * �α��ΰ˻�
	 * @param lv
	 * @return result
	 * @throws SQLException
	 */
	public boolean selectLogin(LoginVO lv) throws SQLException {
		boolean result = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.
			// 2.
			con = getConn();
			// 3.

			String select_login = "select id,pass from member where id=? and pass=?";

			pstmt = con.prepareStatement(select_login);
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPassword());
			// 4.
			rs = pstmt.executeQuery();

			if(rs.next()){//���̵� ����� ������
				result=true;
			}//end if

		} finally {
			// 5.
			dbClose(con, pstmt, null);
		}
		return result;
	} //selectLogin

	/**
	 * ������ ��ȸ �� ��й�ȣ �˻�
	 * @param lv
	 * @return result
	 * @throws SQLException
	 */
	public String myInfoLogin(String id) throws SQLException {
		String sc_pass ="";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.
			// 2.
			con = getConn();
			// 3.
			
			String info_login = "select pass from member where id=?";
			
			pstmt = con.prepareStatement(info_login);
			pstmt.setString(1, id);
			// 4.
			rs = pstmt.executeQuery();
			
			if(rs.next()){ 
				 sc_pass=rs.getString("pass");
			}//end if
			
		} finally {
			// 5.
			dbClose(con, pstmt, null);
		}
		return  sc_pass;
	} //selectLogin

	/**
	 * ���̵� �ߺ�Ȯ��
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean dupleId(String id) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean result=false;
		
		try{
			//1.
			//2.
				con=getConn();
			//3.
				String checkId="select id from member where id=?";
				pstmt=con.prepareStatement(checkId);
				
				pstmt.setString(1, id);
			//4.
				rs=pstmt.executeQuery();
				//�ߺ� ���̵� �ִٸ� flag���� true�� ��´�.
				if(rs.next()){
					result=true;
				}//end if
			}finally{
				// 5.
				dbClose(con, pstmt, null);
			}//end finally
			return result;
		
	}//dupleId
	
	
	
	/**
	 * ȸ������
	 */
	public void joinClient(JoinVO jv)  throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
		//1
		//2
			con=getConn();
		//3
			//ȸ�����Խ� ������ member ���̺� �߰��ϴ� ������  
			String insertMember=
					"insert into member(id,pass,name,birth,email,mileage,phone,pass_ans,rsg_date,pass_index) "
					+ "values (?,?,?,?,?,0,?,?,to_char(sysdate,'yyyymmdd'),?)";
			pstmt=con.prepareStatement(insertMember);
		//4.
			System.out.println(insertMember);
			pstmt.setString(1, jv.getId());
			pstmt.setString(2, jv.getPass());
			pstmt.setString(3, jv.getName());
			pstmt.setString(4, jv.getBirth());
			pstmt.setString(5, jv.getEmail());
			pstmt.setString(6, jv.getPhone());
			pstmt.setString(7, jv.getPassAns());
			pstmt.setString(8, jv.getPassIndex());
			
			pstmt.executeUpdate();
		}finally{
			// 5.
			dbClose(con, pstmt, null);
		}//end finally
	}//insertMember
	
	/**
	 * ���̵� ã��
	 * @param fiv
	 * @return
	 * @throws SQLException
	 */
	public String findID(FindIDVO fiv) throws SQLException {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String schId ="";
		try{
		//1
		//2
			con=getConn();
		//3
			//���̵� ã��
			String idchk = "select id from member where name=? and birth=? and phone=?";
			pstmt=con.prepareStatement(idchk);
		//4.
			
			pstmt.setString(1, fiv.getName());
			pstmt.setString(2, fiv.getBirth());
			pstmt.setString(3, fiv.getPhone());
			
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				schId=rs.getString("id");
				
			}
		}finally{
			// 5.
			dbClose(con, pstmt, null);
		}//end finally
		return schId; 
	
	}//findID
	
	/**
	 * ��й�ȣ ��Ʈ �̾ƿ��� �޼ҵ�
	 * @return
	 * @throws SQLException
	 */
	public List<String>passHint() throws SQLException{//��й�ȣ ��Ʈ �̾ƿ� �޼���
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1 .
			// 2 .
			con = getConn();
			// 3.
			String selectPassHint = "select p_question from pass_q order by pass_index";
			pstmt = con.prepareStatement(selectPassHint);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("p_question"));
			} // end if
		} finally {
			// 5.
			dbClose(con, pstmt, null);
		} // end try
		return list;
		
	}//passHint
	
	/**
	 * ��й�ȣ ã��
	 * @param fpv
	 * @return
	 * @throws SQLException
	 */
	public String findPass(FindPassVO fpv) throws SQLException {//��й�ȣ ã�� �޼ҵ�
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String schPass ="";
		try{
		//1
		//2
			con=getConn();
		//3
			//��й�ȣ ã��
			String passchk = "select pass from member where id=? and birth=? and pass_ans=? and pass_index=?";
			
			pstmt=con.prepareStatement(passchk);
		//4.
			
			pstmt.setString(1, fpv.getId());
			pstmt.setString(2, fpv.getBrith());
			pstmt.setString(3, fpv.getPassAns());
			pstmt.setString(4, fpv.getPassQ());
			
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				schPass=rs.getString("pass");
				System.out.println(schPass);
			}
		}finally{
			// 5.
			dbClose(con, pstmt, null);
		}//end finally
		return schPass;
	}//findPass
	
	/**
	 * ȸ������ ��ȸ
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public MyInfoVO selectMyInfo(String id) throws SQLException {// �������� ��ȸ
		MyInfoVO miv=new MyInfoVO();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
		//1 
		//2. 
			con=getConn();
		//3. 
			String selectMember=
					"select id,name,birth,email,mileage,phone,pass_ans,pass_index from member where id=?";
			pstmt=con.prepareStatement(selectMember);
			pstmt.setString(1, id);
		//4. 
			rs=pstmt.executeQuery();
			while( rs.next() ){
				miv.setId(rs.getString("id").toString());
				miv.setName(rs.getString("name").toString());
				miv.setBirth(rs.getString("birth").toString());
				miv.setMileage(rs.getInt("mileage"));
				miv.setEmail(rs.getString("email").toString());
				miv.setPhone(rs.getString("phone").toString());
				miv.setPass_ans(rs.getString("pass_ans").toString());
				miv.setPass_index(rs.getString("pass_index").toString());
			}//end while
		}finally{
			//5. 
			dbClose(con, pstmt, null);
		}//end finally
		return miv;
		
	}//selectMyInfo
	
	/**
	 * ȸ������ ����
	 * @param miv
	 * @throws SQLException
	 */
	public boolean updateMyInfo(InfoChangeVO icv) throws SQLException {// �������� ����
		Connection con=null;
		PreparedStatement pstmt=null;
		int cnt=0;
		boolean result=false;
		//�̸�,�������,�̸���,�޴�����ȣ,��й�ȣ��Ʈ,��й�ȣ ��Ʈ ��
		
		try{
		//1 
		//2. 
			con=getConn();
		//3. 
			String updatequery=
					"update member set name=?, birth=?, email=?, phone=?, pass_ans=?, pass_index=? where id=?";
			pstmt=con.prepareStatement(updatequery);
			
			pstmt.setString(1, icv.getName());
			pstmt.setString(2, icv.getBirth());
			pstmt.setString(3, icv.getEmail());
			pstmt.setString(4, icv.getPhone());
			pstmt.setString(5, icv.getPass_ans());
			pstmt.setString(6, icv.getPass_index());
			pstmt.setString(7, icv.getId());
		
		//4. 
			 cnt=pstmt.executeUpdate();
			  if(cnt!=0){ 
                  result=true;
			   }else{
				   result=false;
			   }//end if		
			
		
		}finally{
			//5. 
			dbClose(con, pstmt, null);
		}//end finally
		return result;
	}//updateMyInfo
	
	/**
	 * ��й�ȣ ����
	 * @param cpv
	 * @throws SQLException 
	 */
	public boolean changePass(ChangePassVO cpv) throws SQLException {//��й�ȣ ����
		Connection con=null;
		PreparedStatement pstmt=null;
		int cnt=0;
		boolean result=false;
		//�̸�,�������,�̸���,�޴�����ȣ,��й�ȣ��Ʈ,��й�ȣ ��Ʈ ��
		
		try{
		//1 
		//2. 
			con=getConn();
		//3. 
			String updatequery=
					"update member set pass=? where id=?";
			pstmt=con.prepareStatement(updatequery);
			
			pstmt.setString(1, cpv.getPass());
			pstmt.setString(2, cpv.getId());
		
		//4. 
			 cnt=pstmt.executeUpdate();
			  if(cnt!=0){ 
                  result=true;
			   }else{
				   result=false;
			   }//end if		
			
		
		}finally{
			//5. 
			dbClose(con, pstmt, null);
		}//end finally
		return result;
	
	}//changePass
	
	/**
	 * ȸ��Ż��
	 * @param dv
	 * @throws SQLException
	 */
	public void dropMyInfo(DeleteVO dv) throws SQLException { //ȸ��Ż��(���̵� ������ ���� ""���� update)
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConn();
			String deletequery="update member set pass=?, name=?, birth=?, email=?, mileage=?, phone=?, pass_ans=?, pass_index=? "
					+ "where id=?";
			
			pstmt = con.prepareStatement(deletequery);
			
			pstmt.setString(1, dv.getPass());
			pstmt.setString(2, dv.getName());
			pstmt.setString(3, dv.getBirth());
			pstmt.setString(4, dv.getEmail());
			pstmt.setInt(5, dv.getMileage());
			pstmt.setString(6, dv.getPhone());
			pstmt.setString(7, dv.getPass_ans());
			pstmt.setString(8, dv.getPass_index());
			pstmt.setString(9, dv.getId());
			
			pstmt.executeUpdate();
		} finally {
			//5. 
			dbClose(con, pstmt, null);
		} // end finally

		
	}//dropMyInfo
	
	
 /***********************************************************/
	public int rightUser(String id,String room_id) throws SQLException{
		int cnt=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
		con=getConn();

		String right_User ="select count(*) cnt" + 
				" from room_res " + 
				" where id=? and checkin='y' and res_date=to_char(sysdate,'yyyy-mm-dd') " + 
				" and (to_char(sysdate,'hh24')>=in_time and to_char(sysdate,'hh24')< out_time)and room_id=? ";
		//������ ���� ���� �ִ�. 9�ô� �ȵ� ���ڿ����� �񱳶� 10�ÿ��� 9�ÿ���Ȱ� ����
				
		pstmt=con.prepareStatement(right_User);
		pstmt.setString(1, id);
		pstmt.setString(2, room_id);
		
		rs=pstmt.executeQuery();
		System.out.println(right_User);
		rs.next();
			System.out.println(rs.getInt(1));
			cnt=rs.getInt(1);
		}finally {
			dbClose(con,pstmt,rs);
		}
		return cnt;
	}
	
	//////////////////////////////////////////���¿�////////////////////////////////////////

	public SelectUserVO selectUserInfo(String id, String room_id) throws SQLException {
		SelectUserVO su_vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();

			String selectUser = "select name,phone, email, MILEAGE, r.price from member m, room_info r where (m.id=?)and (r.room_id=?)";
			pstmt = con.prepareStatement(selectUser);
			pstmt.setString(1, id);
			pstmt.setString(2, room_id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				su_vo = new SelectUserVO(rs.getString("name"), rs.getString("phone"), rs.getString("email"),
						rs.getString("MILEAGE"), rs.getString("price"));
			} // end if

		} finally {
			dbClose(con, pstmt, rs);
		} // end finally
		return su_vo;
	}// selectUserInfo
	//////////////////////////////////////////���¿�////////////////////////////////////////

}// class
