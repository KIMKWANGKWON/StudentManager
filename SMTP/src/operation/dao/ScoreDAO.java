package operation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import academy.Room;
import academy.Score;
import academy.Student;

public class ScoreDAO {
	String driver = "oracle.jdbc.driver.OracleDriver"; // 6�� ~ 9�� �����ͺ��̽� ������ ���� 4���� ������ String ������ ����.
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";

	public ScoreDAO() {
		try {
			Class.forName(driver); // ����̹��� �ε��ϴ� �ʱ�ȭ �۾��� �����ڿ��� �����Ѵ�.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public HashSet<Score> select(String query) {
		HashSet<Score> list = new HashSet<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);

//		 query = "SELECT NO,NAME,ROOM FROM STUDENT"; 
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Score dto = new Score(rs.getString("SUBJECTNAME"), rs.getInt("STUDENTID"), rs.getInt("POINT"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int update(String subjectName, int studentId, int point) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			con = DriverManager.getConnection(url, userid, passwd);

			String query = "UPDATE SCORE SET subjectName = ? ,point = ? WHERE studentId = ? AND subjectName = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, subjectName);
			pstmt.setInt(2, point);
			pstmt.setInt(3, studentId);
			pstmt.setString(4, subjectName);

			i = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	public int insert(int id, String name, int roomNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			con = DriverManager.getConnection(url, userid, passwd);

			String query = "INSERT INTO STUDENT VALUES(?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, roomNo);

			i = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	public int delete(int studentId,String subjectName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			con = DriverManager.getConnection(url, userid, passwd);

			String query = "DELETE FROM SCORE WHERE STUDENTID = ? AND SUBJECTNAME = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, studentId);
			pstmt.setString(2, subjectName);


			i = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
}