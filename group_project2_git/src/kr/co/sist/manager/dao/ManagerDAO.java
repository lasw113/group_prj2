package kr.co.sist.manager.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.co.sist.client.vo.HistoryVO;
import kr.co.sist.manager.vo.LoginVO;
import kr.co.sist.manager.vo.ResMgrVO;
import kr.co.sist.manager.vo.SalesVO;
import kr.co.sist.manager.vo.UserVO;

public class ManagerDAO {

	private static ManagerDAO m_dao;


	private ManagerDAO() {

	}// ClientDAO

	public static ManagerDAO getInstance() {
		if (m_dao == null) {
			m_dao = new ManagerDAO();
		} // end if
		return m_dao;
	}

	private Connection getConn() throws SQLException {
		Connection con = null;
		Properties prop = new Properties();
		String path = System.getProperty("user.dir");
		try {
			prop.load(new FileReader(path + "/src/kr/co/sist/client/dao/database.properties"));

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
		///////////////////// 김태영/////////////////////////////

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

			String select_login = "select m_id, m_pass from manager where m_id=? and m_pass=?";

			pstmt = con.prepareStatement(select_login);
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPass());
			// 4.
			rs = pstmt.executeQuery();

			if (rs.next()) {// 아이디 비번이 맞으면
				result = true;
			} // end if

		} finally {
			// 5.
			dbClose(con, pstmt, null);
		}
		return result;
	} // selectLogin
		///////////////////// 김태영/////////////////////////////

	///////////////////// 김진석/////////////////////////////
	// 데이터베이스에서 원하는 정보정보 가져오기
		public List<ResMgrVO> selectAll() throws SQLException {
			List<ResMgrVO> list = new ArrayList<ResMgrVO>();

			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				// 1. 드라이버 로딩
				// 2. Connection 얻기
				con = getConn();
				// 3. 쿼리문 생성객체 얻기
				stmt = con.createStatement();
				// 4. 쿼리문 수행후 결과 얻기
				String selectQuery = "select  ri.room_id, rr.p_cnt, resin.res_name, rr.in_time, rr.out_time, resin.request, rr.res_id, rr.checkin, rr.id, resin.use_mile from room_info ri, room_res rr, res_info resin where  (ri.room_id = rr.room_id) and (rr.RES_ID = resin.RES_ID) and ((checkin='y') or (rr.checkin is null)) and (rr.res_date=to_char(  sysdate, 'yyyy-mm-dd')) order by res_id";

				rs = stmt.executeQuery(selectQuery);

				ResMgrVO rmvv = null;
				// String room_id, String res_num, String res_name, String in_time, String
				// out_time,String request, int p_cnt
				while (rs.next()) {
					rmvv = new ResMgrVO(rs.getString("room_id"), rs.getInt("p_cnt"), rs.getString("res_name"),
							rs.getString("in_time"), rs.getString("out_time"), rs.getString("request"),
							rs.getString("res_id"), rs.getString("checkin"), rs.getString("id"), rs.getShort("use_mile"));
					list.add(rmvv);
				} // end while
			} finally {
				// 5. 연결 끊기
				if (rs != null) {
					rs.close();
				} // end if
				if (stmt != null) {
					stmt.close();
				} // end if
				if (con != null) {
					con.close();
				} // end if
			} // end try
			return list;
		}

		// 카드, momey 업데이터
		public String opt_pay(String how_pay, String res_ids) throws SQLException {
			Connection con = null;
			Statement stmt = null;

			String msg = how_pay + " 변경되었습니다.";
			try {
				con = getConn();
				stmt = con.createStatement();
				StringBuilder updateQuery = new StringBuilder();
				System.out.println(res_ids);

				// res_ids = res_ids.substring(0, 7);
				// update room_res set pay_opt = '현금' where res_id ='Res_047';

				updateQuery.append("update room_res set pay_opt = '").append(how_pay).append("'").append(" where res_id ='")
						.append(res_ids).append("'");
				System.out.println(updateQuery);

				stmt.executeUpdate(updateQuery.toString());
			} finally {
				// 5. 연결 끊기
				if (stmt != null) {
					stmt.close();
				} // end if

				if (con != null) {
					con.close();
				} // end if
			} // end finally

			return msg;
		}

		// 룸에 들어온 것을 체크
		public void in_Rooming(String res_ids) throws SQLException {
			Connection con = null;
			Statement stmt = null;

			String msg = "'y'로 처리";
			System.out.println(msg);
			try {
				// res_ids = res_ids.substring(0, 7);
				con = getConn();
				stmt = con.createStatement();
				StringBuilder updateQuery = new StringBuilder();
				System.out.println(res_ids + " 예약번호를 가진 사용자의 입실여부를 y로 바꾸기");

				updateQuery.append("update room_res set checkin='y' ").append(" where res_id ='").append(res_ids)
						.append("'");
				System.out.println(updateQuery);
				stmt.executeUpdate(updateQuery.toString());

			} finally {
				// 5. 연결 끊기
				if (stmt != null) {
					stmt.close();
				} // end if

				if (con != null) {
					con.close();
				} // end if
			} // end finally
		}

		// 룸에 없다는 것을 체크
		public void out_Rooming(String res_ids) throws SQLException {
			Connection con = null;
			Statement stmt = null;

			String msg = "'n'로 처리";
			System.out.println(msg);
			try {
				// res_ids = res_ids.substring(0, 7);
				con = getConn();
				stmt = con.createStatement();
				StringBuilder updateQuery = new StringBuilder();
				System.out.println(res_ids + " 예약번호를 가진 사용자의 입실여부를 y로 바꾸기");

				updateQuery.append("update room_res set checkin='n' ").append(" where res_id ='").append(res_ids)
						.append("'");
				System.out.println(updateQuery);
				stmt.executeUpdate(updateQuery.toString());

			} finally {
				// 5. 연결 끊기
				if (stmt != null) {
					stmt.close();
				} // end if

				if (con != null) {
					con.close();
				} // end if
			} // end finally
		}

		// 시간을 추가하는 쿼리
		public void plus_time(int plus_time, String res_ids) throws SQLException {
			Connection con = null;
			Statement stmt = null;

			System.out.println("추가할시간 : " + plus_time + " / 예약아이디 : " + res_ids);

			try {
				con = getConn();
				stmt = con.createStatement();
				StringBuilder updateQuery = new StringBuilder();
				System.out.println(res_ids);

				// res_ids = res_ids.substring(0, 9);
				// update room_res set pay_opt = '현금' where res_id ='Res_047';

				updateQuery.append("update room_res set out_time = to_number(out_time,99)+").append(plus_time)
						.append(" where res_id ='").append(res_ids).append("'");
				System.out.println(updateQuery);

				stmt.executeUpdate(updateQuery.toString());

			} finally {
				// 5. 연결 끊기
				if (stmt != null) {
					stmt.close();
				} // end if

				if (con != null) {
					con.close();
				} // end if
			} // end finally
		}

		// 예약취소하는 쿼리
		public void delete_res(String res_ids) throws SQLException {
			Connection con = null;
			Statement stmt = null;
			System.out.println(res_ids);
			try {
				con = getConn();
				stmt = con.createStatement();
				StringBuilder deleteQuery = new StringBuilder();

				deleteQuery.append("delete from res_info where res_id='").append(res_ids).append("'");
				stmt.executeUpdate(deleteQuery.toString());
				System.out.println("res_info 데이터지우기");
				System.out.println(deleteQuery);

				deleteQuery = new StringBuilder("");
				System.out.println("============================");
				deleteQuery.append("delete from room_res where res_id='").append(res_ids).append("'");
				stmt.executeUpdate(deleteQuery.toString());
				System.out.println("res_info 데이터지우기");
				System.out.println(deleteQuery);

			} finally {
				// 5. 연결 끊기
				if (stmt != null) {
					stmt.close();
				} // end if

				if (con != null) {
					con.close();
				} // end if
			} // end finally
		}

		//마일리지를 사용하는 쿼리문!
		public void use_mile(String res_id, String id) throws SQLException {
			Connection con = null;
			Statement stmt = null;

			try {
				con = getConn();
				stmt = con.createStatement();
				StringBuilder updateQuery = new StringBuilder();

				// res_ids = res_ids.substring(0, 7);
				// update room_res set pay_opt = '현금' where res_id ='Res_047';

				updateQuery.append("update member set MILEAGE = MILEAGE + (select use_mile from res_info where res_id='").append(res_id).append("') where id ='").append(id).append("'");
						
				
				System.out.println("마일리지 사용하는 쿼리 - " + updateQuery);

				stmt.executeUpdate(updateQuery.toString());
			} finally {
				// 5. 연결 끊기
				if (stmt != null) {
					stmt.close();
				} // end if

				if (con != null) {
					con.close();
				} // end if
			} // end finally
		}//end use_mile
	/////////// 김진석/////////////////////////

	////////// 함민이//////////////////////////
	// 전체 회원을 조회하는 VO
	public List<UserVO> setUser() throws SQLException {
		List<UserVO> listMem = new ArrayList<UserVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder selectMem = new StringBuilder();
			selectMem.append("select id, name, phone, mileage from member");
			pstmt = con.prepareStatement(selectMem.toString());

			rs = pstmt.executeQuery();

			UserVO uvo = null;
			while (rs.next()) {
				uvo = new UserVO(rs.getString("id"), rs.getString("name"), rs.getString("phone"), rs.getInt("mileage"));
				listMem.add(uvo);
			} // end while
		} finally {
			dbClose(con, pstmt, rs);
		} // end finally
		return listMem;
	}// ResChk

	// 아이디로 회원 조회
	// 아이디로 회원 조회
	public List<UserVO> searchUser(String id) throws SQLException {
		List<UserVO> listMem = new ArrayList<UserVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder selectMem = new StringBuilder();
			selectMem.append("select id, name, phone, mileage from member where id=?");
			pstmt = con.prepareStatement(selectMem.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			UserVO uvo = null;
			while (rs.next()) {
				uvo = new UserVO(rs.getString("id"), rs.getString("name"), rs.getString("phone"), rs.getInt("mileage"));
				listMem.add(uvo);
			} // end while
		} finally {
			dbClose(con, pstmt, rs);
		} // end finally
		return listMem;

	}// end searchUser()

	// 회원 예약내역 조회
	public List<HistoryVO> searchHis(String id) throws SQLException {
		List<HistoryVO> listHis = new ArrayList<HistoryVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder selectRes = new StringBuilder();
			selectRes.append(
					"select to_char(rr.res_date,'yyyy-mm-dd')res_date,res_i.res_name,ri.room_id,rr.p_cnt,ri.price*rr.p_cnt-res_i.use_mile price,rr.in_time,rr.out_time ")
					.append("from room_info ri, room_res rr, member mem , res_info res_i ")
					.append("where rr.res_id=res_i.res_id and ri.room_id=rr.room_id and mem.id= rr.id and mem.id=? and to_char(sysdate,'yyyymmdd')>to_char(res_date,'yyyymmdd') and (rr.pay_opt is not null)");
			pstmt = con.prepareStatement(selectRes.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			HistoryVO hvo = null;// ?
			while (rs.next()) {
				hvo = new HistoryVO(rs.getString("res_name"), rs.getString("room_id"), rs.getString("in_time"),
						rs.getString("out_time"), rs.getString("res_date"), rs.getInt("p_cnt"), rs.getInt("price"));
				listHis.add(hvo);
			} // end while
		} finally {
			dbClose(con, pstmt, rs);
		} // end finally
		return listHis;
	}// searchHis

	////////// 함민이//////////////////////////

	/////////// 김수정//////////////////////////
	public SalesVO selectSales() throws SQLException {

		SalesVO s_vo = new SalesVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;

		try {
			con = getConn();

			String selectCard = "select (card-nvl(useMile,0)) cardsales " + " from("
					+ "	select sum(ri.price * (to_number(rr.out_time)-to_number(rr.in_time))* rr.p_cnt)card, sum(rei.use_mile) useMile "
					+ "	from  room_info ri, room_res rr, res_info rei"
					+ "	where (ri.room_id = rr.room_id)and (rr.res_id = rei.res_id) and(rr.pay_opt='카드')and(rr.checkin is not null)and (rr.res_date= to_char(sysdate,'yyyy-mm-dd'))"
					+ "	)";
			pstmt = con.prepareStatement(selectCard);

			rs = pstmt.executeQuery();
			rs.next();
			s_vo.setCard(rs.getInt(1));
			total += rs.getInt("cardsales");
			System.out.println(rs.getInt(1));
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			String selectCash = "select (cash-nvl(useMile,0)) cashsales " + " from("
					+ "	select sum(ri.price * (to_number(rr.out_time)-to_number(rr.in_time))* rr.p_cnt)cash, sum(rei.use_mile) useMile "
					+ "	from  room_info ri, room_res rr, res_info rei"
					+ "	where (ri.room_id = rr.room_id)and (rr.res_id = rei.res_id) and(rr.pay_opt='현금')and(rr.checkin is not null)and (rr.res_date= to_char(sysdate,'yyyy-mm-dd'))"
					+ "	)";
			pstmt = con.prepareStatement(selectCash);

			rs = pstmt.executeQuery();
			rs.next();
			s_vo.setCash(rs.getInt(1));
			total += rs.getInt(1);
			System.out.println(rs.getInt(1));

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			String selectMile = "select  sum(rei.use_mile) milesales " + " from    room_res rr,res_info rei "
					+ " where  (rr.res_id = rei.res_id) and(rr.res_date= to_char(sysdate,'yyyy-mm-dd'))and (rr.checkin is not null)";
			pstmt = con.prepareStatement(selectMile);

			rs = pstmt.executeQuery();
			rs.next();
			s_vo.setMilleage(rs.getInt(1));
			total += rs.getInt(1);
			System.out.println(rs.getInt(1));

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			String countClient = "select  sum(rr.p_cnt) countclient " + " from    room_res rr "
					+ " where   (rr.res_date= to_char(sysdate,'yyyy-mm-dd'))and (rr.checkin is not null)";
			pstmt = con.prepareStatement(countClient);
			rs = pstmt.executeQuery();
			rs.next();
			s_vo.setT_cnt(rs.getInt("countclient"));
			System.out.println(rs.getInt("countclient"));

		} finally {
			dbClose(con, pstmt, rs);
		} // end finally
		s_vo.setT_sales(total);
		return s_vo;
	}// selectSales

	/////////// 김수정//////////////////////////
}
