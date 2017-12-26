package kr.co.sist.client.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.co.sist.client.vo.ModiUserVO;
import kr.co.sist.client.vo.RoomInfoVO;
import kr.co.sist.client.vo.SelectRoomResVO;
import kr.co.sist.client.vo.SelectTimeChkVO;
import kr.co.sist.client.vo.SelectTimeVO;
import kr.co.sist.client.vo.SelectUserVO;

public class RoomCDAO {
	private static RoomCDAO r_dao;

	private RoomCDAO() {
	}// RoomDAO

	public static RoomCDAO getInstance() {
		if (r_dao == null) {
			r_dao = new RoomCDAO();
		} // end if
		return r_dao;
	}// RoomDAO

	private Connection getConn() throws SQLException {
		Connection con = null;

		Properties prop = new Properties();
		try {
			prop.load(new FileReader("C:/Users/a/git/group_prj2/group_project2_git/src/kr/co/sist/client/dao/database.properties"));

			Class.forName(prop.getProperty("driverClass"));

			con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("id"),
					prop.getProperty("pass"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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

	public RoomInfoVO selectRoomInfo(String room_id) throws SQLException {
		RoomInfoVO ri_vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();

			String selectRoom = "select INFO, IMAGE, P_MIN, P_MAX from room_info where room_id= ? ";
			pstmt = con.prepareStatement(selectRoom);
			pstmt.setString(1, room_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ri_vo = new RoomInfoVO(rs.getString("INFO"), rs.getString("IMAGE"), rs.getString("P_MIN"),
						rs.getString("P_MAX"));
			} // end if

		} finally {
			dbClose(con, pstmt, rs);
		} // end finally

		return ri_vo;
	}// selectRoomInfo

	public List<String> selectEquip(String room_id) throws SQLException {
		List<String> listEquip = new ArrayList<String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();

			StringBuilder selectEquip = new StringBuilder();
			selectEquip.append(" select EQUIP_INFO.type, count(*) ")
					.append(" from room_info, EQUIPMENT, EQUIP_INFO, PRODUCT, ROOM_PRO ")
					.append(" where (room_info.room_id=room_pro.room_id) ")
					.append(" and(room_pro.IDENTY_NUM=PRODUCT.IDENTY_NUM) ")
					.append(" and(PRODUCT.PRO_NUM=EQUIPMENT.PRO_NUM) ")
					.append(" and(EQUIPMENT.EQUIP_NUM=EQUIP_INFO.EQUIP_NUM) ").append(" and(room_info.room_id=?) ")
					.append(" group by EQUIP_INFO.type ");

			pstmt = con.prepareStatement(selectEquip.toString());
			pstmt.setString(1, room_id);

			rs = pstmt.executeQuery();

			String equip = null;
			while (rs.next()) {
				equip = rs.getString("type");
				listEquip.add(equip);
			} // end while

		} finally {
			dbClose(con, pstmt, rs);
		} // end finally
		return listEquip;
	}// selectEquip

	public List<SelectTimeVO> selectTimeChk(SelectTimeChkVO stc_vo) throws SQLException {
		List<SelectTimeVO> listTime = new ArrayList<SelectTimeVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();

			String selectTime = "select in_time, out_time from room_res where (room_id= ?) and to_char(res_date,'yyyy-mm-dd')=?";

			pstmt = con.prepareStatement(selectTime);
			pstmt.setString(1, stc_vo.getRoom_id());
			pstmt.setString(2, stc_vo.getDate());

			rs = pstmt.executeQuery();

			SelectTimeVO st_vo = null;
			while (rs.next()) {
				st_vo = new SelectTimeVO(rs.getString("in_time"), rs.getString("out_time"));
				listTime.add(st_vo);
			} // end while

		} finally {
			dbClose(con, pstmt, rs);
		} // end finally
		return listTime;
	}// selectTimeChk

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

	public void insertRes(ModiUserVO mu_vo, SelectRoomResVO srr_vo, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();

			String insertRoom = "insert into room_res(RES_ID, IN_TIME, OUT_TIME, RES_DATE, P_CNT, ID, ROOM_ID) values(concat('RES_',lpad(seq_res.nextval,5,0)),?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insertRoom);
			// 바인드 변수에 값 넣기
			pstmt.setString(1, srr_vo.getIn_time());
			pstmt.setString(2, srr_vo.getOut_time());
			pstmt.setString(3, srr_vo.getDate());
			pstmt.setInt(4, srr_vo.getP_cnt());
			pstmt.setString(5, id);
			pstmt.setString(6, srr_vo.getRoom_id());

			pstmt.executeUpdate();

			pstmt.close();
			con.close();

			con = getConn();

			String insertUser = "insert into RES_INFO(RES_NAME, RES_PHONE, RES_EMAIL, REQUEST, USE_MILE, RES_ID) values(?, ?, ?, ?, ?,(select res_id from(select rownum r,res_id from(select rownum, res_id  from room_res where id= ? order by res_id desc) where rownum=1)))";
			pstmt = con.prepareStatement(insertUser);
			pstmt.setString(1, mu_vo.getRes_name());
			pstmt.setString(2, mu_vo.getRes_phone());
			pstmt.setString(3, mu_vo.getRes_email());
			pstmt.setString(4, mu_vo.getRequest());
			pstmt.setString(5, mu_vo.getUse_mile());
			pstmt.setString(6, id);

			pstmt.executeUpdate();

		} finally {
			dbClose(con, pstmt, null);
		} // end finally
	}// insertRes

}// class
