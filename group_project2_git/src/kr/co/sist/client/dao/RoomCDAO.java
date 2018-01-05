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

import kr.co.sist.client.vo.CancelResVO;
import kr.co.sist.client.vo.HistoryVO;
import kr.co.sist.client.vo.ModiUserVO;
import kr.co.sist.client.vo.ResChkVO;
import kr.co.sist.client.vo.RoomInfoVO;
import kr.co.sist.client.vo.SelectTimeChkVO;
import kr.co.sist.client.vo.SelectTimeVO;
import kr.co.sist.client.vo.UpdateMileVO;

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

	/////////////////////// 김태영////////////////////////////////
	public void updateMile(UpdateMileVO um_vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConn();

			String useMile = "update member set MILEAGE = MILEAGE - ? where id = ?";
			pstmt = con.prepareStatement(useMile);
			pstmt.setInt(1, um_vo.getUseMile());
			pstmt.setString(2, um_vo.getId());

			pstmt.executeUpdate();

		} finally {
			dbClose(con, pstmt, null);
		} // end finally

	}

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

	public void insertRes(ModiUserVO mu_vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();

			String insertRoom = "insert into room_res(RES_ID, IN_TIME, OUT_TIME, RES_DATE, P_CNT, ID, ROOM_ID) values(concat('RES_',lpad(seq_res.nextval,5,0)),?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insertRoom);
			// 바인드 변수에 값 넣기
			pstmt.setString(1, mu_vo.getSrr_vo().getIn_time());
			pstmt.setString(2, mu_vo.getSrr_vo().getOut_time());
			pstmt.setString(3, mu_vo.getSrr_vo().getDate());
			pstmt.setInt(4, mu_vo.getSrr_vo().getP_cnt());
			pstmt.setString(5, mu_vo.getId());
			pstmt.setString(6, mu_vo.getSrr_vo().getRoom_id());

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
			pstmt.setString(6, mu_vo.getId());

			pstmt.executeUpdate();

		} finally {
			dbClose(con, pstmt, null);
		} // end finally
	}// insertRes
		/////////////////////// 김태영////////////////////////////////

	/////////////////////// 함민이////////////////////////////////
	// 회원의 이름을 가지고 오는 일
	public String searchName(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String name = "";
		try {
			con = getConn();
			String selectName = "select name from member where id=?";
			pstmt = con.prepareStatement(selectName);
			// 바인드 변수에 값 넣기
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("name");
			}

		} finally {
			dbClose(con, pstmt, null);
		} // end finally
		return name;
	}// end searchName

	// 예약을 조회하는 일
	public List<ResChkVO> ResChk(String id) throws SQLException {
		List<ResChkVO> listRes = new ArrayList<ResChkVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder selectRes = new StringBuilder();
			selectRes.append(
					"select rr.res_id, res_i.res_name, ri.room_id, p_cnt, (ri.price*rr.p_cnt*(rr.out_time-rr.in_time))-nvl(res_i.use_mile,0) price, rr.in_time, rr.out_time ,to_char(rr.res_date,'yyyy-mm-dd')res_date ")
					.append("from room_res rr, room_info ri, res_info res_i,member mem ")
					.append("where rr.res_id=res_i.res_id and ri.room_id=rr.room_id and mem.id= rr.id and to_char(sysdate,'yyyymmdd')<=to_char(res_date,'yyyymmdd') and mem.id= ? and rr.checkin is null");
			pstmt = con.prepareStatement(selectRes.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			ResChkVO rcvo = null;
			while (rs.next()) {
				rcvo = new ResChkVO(rs.getString("res_id"), rs.getString("res_name"), rs.getString("room_id"),
						rs.getString("in_time"), rs.getString("out_time"), rs.getString("res_date"), rs.getInt("p_cnt"),
						rs.getInt("price"));
				listRes.add(rcvo);
			} // end while
		} finally {
			dbClose(con, pstmt, rs);
		} // end finally
		return listRes;
	}// ResChk

	public List<HistoryVO> setHistory(String id) throws SQLException {
		List<HistoryVO> listHis = new ArrayList<HistoryVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder selectRes = new StringBuilder();
			selectRes.append(
					"select to_char(rr.res_date,'yyyy-mm-dd')res_date,res_i.res_name,ri.room_id,rr.p_cnt,(ri.price*rr.p_cnt*(rr.out_time-rr.in_time))-nvl(res_i.use_mile,0) price,rr.in_time,rr.out_time ")
					.append("from room_info ri, room_res rr, member mem , res_info res_i ")
					.append("where rr.res_id=res_i.res_id and ri.room_id=rr.room_id and mem.id= rr.id and mem.id=? and to_char(sysdate,'yyyymmdd')>to_char(res_date,'yyyymmdd') and rr.checkin='n'");
			pstmt = con.prepareStatement(selectRes.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			HistoryVO hvo = new HistoryVO();
			while (rs.next()) {
				hvo = new HistoryVO(rs.getString("res_name"), rs.getString("room_id"), rs.getString("in_time"),
						rs.getString("out_time"), rs.getString("res_date"), rs.getInt("p_cnt"), rs.getInt("price"));
				listHis.add(hvo);
			} // end while
		} finally {
			dbClose(con, pstmt, null);
		} // end finally
		return listHis;

	}// setHistory

	// 예약 삭제
	public void cancelRes(CancelResVO cr_vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConn();
			StringBuilder updateRes = new StringBuilder();
			updateRes.append("update member ")
					.append("set mileage = mileage +nvl((select use_mile from res_info where res_id=?),0)")// 마일리지 사용
					.append("where id =?");
			pstmt = con.prepareStatement(updateRes.toString());
			// 바인드 변수에 값 넣기
			pstmt.setString(1, cr_vo.getRes_id());
			pstmt.setString(2, cr_vo.getId());

			pstmt.executeUpdate();

			pstmt.close();
			con.close();

			con = getConn();

			String cancelRes = "delete from res_info where res_id=? ";
			pstmt = con.prepareStatement(cancelRes);
			// 바인드 변수에 값 넣기
			pstmt.setString(1, cr_vo.getRes_id());

			pstmt.executeUpdate();

			pstmt.close();
			con.close();

			con = getConn();

			String cancelRes1 = "delete from room_res where res_id=?";
			pstmt = con.prepareStatement(cancelRes1);
			pstmt.setString(1, cr_vo.getRes_id());

			pstmt.executeUpdate();

		} finally {
			dbClose(con, pstmt, null);
		} // end finally
	}// cancelRes
		/////////////////////// 함민이////////////////////////////////

}// class
