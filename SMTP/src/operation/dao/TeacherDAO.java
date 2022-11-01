package operation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import academy.Room;
import academy.Student;
import academy.Teacher;

public class TeacherDAO {
	String driver = "oracle.jdbc.driver.OracleDriver"; // 6�� ~ 9�� �����ͺ��̽� ������ ���� 4���� ������ String ������ ����.
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";

	public TeacherDAO() {
		try {
			Class.forName(driver); // ����̹��� �ε��ϴ� �ʱ�ȭ �۾��� �����ڿ��� �����Ѵ�.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Teacher> select(String query) {
		HashMap<String, Teacher> list = new HashMap<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Teacher dto = new Teacher(rs.getString("ID"), rs.getString("PWD"), rs.getString("NAME"),new Room(Integer.parseInt(rs.getString("MAJORROOM"))),new Room(Integer.parseInt(rs.getString("SUBROOM"))));
				list.put(dto.getId(), dto);
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

	public int update(int id, String name, int roomNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			con = DriverManager.getConnection(url, userid, passwd);

			String query = "UPDATE STUDENT SET NAME = ? ,ROOM = ? WHERE ID = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(3, id);
			pstmt.setString(1, name);
			pstmt.setInt(2, roomNo);

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
	public int delete(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			con = DriverManager.getConnection(url, userid, passwd);

			String query = "DELETE FROM STUDENT WHERE ID = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);


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