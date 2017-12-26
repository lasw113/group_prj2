package kr.co.sist.manager.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import kr.co.sist.manager.vo.LoginVO;

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
		try {
			prop.load(new FileReader(
					"C:/Users/a/git/group_prj2/group_project2_git/src/kr/co/sist/client/dao/database.properties"));

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

}
