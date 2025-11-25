package com.Dao;

import java.sql.*;
import java.util.ArrayList;

import com.Model.Transactions;

public class TransactionsOperations {
	public static int insertTransactionDetails(Transactions t){
		int check = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "insert into Transactions(tId, tTotalAmount, tNoOfItems, tCId, tPId) values(?, ?, ?, ?, ?)";

			pst = con.prepareStatement(sql);
			pst.setInt(1,  t.getTransactionId());
			pst.setDouble(2, t.getTotalAmount());
			pst.setInt(3, t.getNoOfItems());
			pst.setInt(4, t.getCustomerId());
			pst.setInt(5, t.getProductId());

			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return check;
	}

	public static int updateTransactionDetails(Transactions t) {

		int check=0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "update Transactions set tNoOfItems=?, tTotalAmount=? where tId=696969";

			pst = con.prepareStatement(sql);

			pst.setInt(1, t.getNoOfItems());
			pst.setDouble(2, t.getTotalAmount());


			check = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

	public static boolean checkForProductIdForeignKey(int id) {
		boolean isFound = false;
		int check=0;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			System.out.println("TransactionsOperations.java -> Finding ProductId in Product");
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Product where pId=?";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);


			ResultSet rs = pst.executeQuery();

			if ( rs.next() ) {
			    check=1;
			}


			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if( check > 0 )
		{
			isFound = true;
		}

		return isFound;
	}

	public static ArrayList<Transactions> getAllTransactionsDetails(){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Transactions> li = new ArrayList<Transactions>();

		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:derby:C:/Users/hp/MyDB;create=true");
			String sql = "select * from Transactions";

			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();

			while(rs.next()) {
				Transactions t1 = new Transactions();
				t1.setTransactionId(rs.getInt(1));
				t1.setTotalAmount(rs.getDouble(2));
				t1.setNoOfItems(rs.getInt(3));
				t1.setCustomerId(rs.getInt(4));
				t1.setProductId(rs.getInt(5));

				li.add(t1);
			}

			pst.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return li;
	}
}