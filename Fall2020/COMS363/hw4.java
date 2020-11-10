package coms363c;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class JDBCTransactionTester {
	public static String[] loginDialog() {
		String result[] = new String[2];
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		JLabel lbUsername = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbUsername, cs);

		JTextField tfUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfUsername, cs);

		JLabel lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);

		JPasswordField pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		String[] options = new String[] { "OK", "Cancel" };
		int ioption = JOptionPane.showOptionDialog(null, panel, "Login", JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (ioption == 0) // pressing OK button
		{
			result[0] = tfUsername.getText();
			result[1] = new String(pfPassword.getPassword());
		}
		return result;
	}

	/**
	 * @param stmt
	 * @param sqlQuery
	 * @throws SQLException
	 */
	private static void runQuery(Statement stmt, String sqlQuery) throws SQLException {
		ResultSet rs;
		ResultSetMetaData rsMetaData;
		String toShow;
		rs = stmt.executeQuery(sqlQuery);
		rsMetaData = rs.getMetaData();
		System.out.println(sqlQuery);
		toShow = "";
		while (rs.next()) {
			for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
				toShow += rs.getString(i + 1) + ", ";
			}
			toShow += "\n";
		}
		JOptionPane.showMessageDialog(null, toShow);
	}
	
	/**
	 * Show an example of an insert statement
	 * @param conn Valid database connection
	 * 		  fname: Name of a food to check
	 */
	private static void insertFood(Connection conn, String fname, String lname) {
		
		if (conn==null || fname==null || lname==null) throw new NullPointerException();
		try {
			
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			ResultSet rs;
			int id=0;
			
			// get the maximum id from the food table
			rs = stmt.executeQuery("select max(actor_id) from actor");
			while (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			
			
			PreparedStatement inststmt = conn.prepareStatement(
	                " insert into actor (actor_id,first_name,last_name) values(?,?,?) "); //two parameters are needed, and we set them in line 106 and 108
			
			// first column has the new food id that is unique
			inststmt.setInt(1, id+1);
			// second ? has the food name
			inststmt.setString(2, fname);
			inststmt.setString(3, lname);
			
			int rowcount = inststmt.executeUpdate();
			
			System.out.println("Number of rows updated:" + rowcount);
			inststmt.close();
			// confirm that these are the changes you want to make
			conn.commit();
			// if other parts of the program needs commit per SQL statement
			// conn.setAutoCommit(true);
		} catch (SQLException e) {}

	}
	
	/* this example shows how to use ? to give a different value 
	 to a query
	 @param conn: Valid connection to a dbms
	        iname: the name of the ingredient to check
	*/
	
	private static void checkIngredient(Connection conn, String iname) {
		
		if (conn==null || iname==null) throw new NullPointerException();
		try {
			
			ResultSet rs =null;
			String toShow ="";
			
			PreparedStatement lstmt = conn.prepareStatement(
	           "select count(*) from ingredient where iname= ?");  //? is the part should be filled, we set it in line 142
			
			// clear previous parameter values
			lstmt.clearParameters();
			// first column has the new food id that is unique
			lstmt.setString(1, iname);
			
			// execute the query
			rs=lstmt.executeQuery();
			// advance the cursor to the first record
			
			rs.next();
			int count = rs.getInt(1);
			lstmt.close();
			
			System.out.println("count=" + count);
			
			if (count > 0) {
				toShow = "The ingredient " + iname + " exists";
			}
			else toShow = "The ingredient " + iname + " does not exist";
			
			JOptionPane.showMessageDialog(null, toShow);
			
		} catch (SQLException e) {}

	}

	public static void main(String[] args) {
		String dbServer = "jdbc:mysql://127.0.0.1:3306/sakila?allowPublicKeyRetrieval=true&useSSL=false";
		// For compliance with existing applications not using SSL the verifyServerCertificate property is set to ‘false’,
		String userName = "";
		String password = "";

		String result[] = loginDialog();
		userName = result[0];
		password = result[1];

		Connection conn;
		Statement stmt;
		if (result[0]==null || result[1]==null) {
			System.out.println("Terminating: No username nor password is given");
			return;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbServer, userName, password);
			stmt = conn.createStatement();
			String sqlQuery = "";

			String option = "";
			String instruction = "Enter a: Insert an actor" + "\n"
					+ "Enter b: Delete customer"
					+ "\n" + "Enter c: Report toal sales." + "\n"
					+ "Enter e: Quit Program";

			while (true) {
				option = JOptionPane.showInputDialog(instruction);
				//DONE
				if (option.equals("a")) {
					String fname=JOptionPane.showInputDialog("Enter first name:");
					String lname=JOptionPane.showInputDialog("Enter last name:");
					insertFood(conn, fname, lname);	
					conn.setAutoCommit(false);
					conn.commit();
				}
				
				//TODO
				else if (option.equalsIgnoreCase("b")) {
					String customerID=JOptionPane.showInputDialog("Enter customer id:");
					conn.setAutoCommit(false);
					conn.commit();
					sqlQuery = "DELETE FROM payment WHERE customer_id = " + customerID + ";";
					stmt.executeUpdate(sqlQuery);
					sqlQuery = "DELETE FROM rental WHERE customer_id = " + customerID + ";";
					stmt.executeUpdate(sqlQuery);
					sqlQuery = "DELETE FROM customer WHERE customer_id = " + customerID + ";";
					stmt.executeUpdate(sqlQuery);
				} 
				
				//TODO
				else if (option.equals("c")) {
                    
                    String num=JOptionPane.showInputDialog("Enter 1-12 for the month:");
                    
                    conn.setAutoCommit(false);
                    conn.commit();
                    sqlQuery = 
                            "SELECT sum(p.amount)" +   
                            " FROM payment p" + 
                            " INNER JOIN rental AS r ON p.rental_id = r.rental_id" + 
                            " WHERE month(r.rental_date)=" + num + ";" ;
                    runQuery(stmt, sqlQuery);
                } 
                
                else if(option.equals("e")) {
                    break;
                } 
				
			}

			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Program terminates due to errors");
			e.printStackTrace(); // for debugging
		}
	}

}