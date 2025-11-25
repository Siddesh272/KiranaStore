package com.Dao;

import java.sql.*;

import com.Model.Admin;

public class AdminOperations {
	public static Admin getAdminDetailsById(int id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Admin a1 = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Admin where aId=?";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			while(rs.next()) {
				a1 = new Admin();
				a1.setAdminId(rs.getInt(1));
				a1.setAdminPassword(rs.getString(2));
			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a1;
	}

	public static int insertAdminDetails(Admin a1) {
		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "insert into Admin(aId, aPassword) values(?, ?)";

			pst = con.prepareStatement(sql);
			pst.setInt(1,  a1.getAdminId());
			pst.setString(2,  a1.getAdminPassword());

			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}
}