package finalUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Main {
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
		if (ioption == 0) 
		{
			result[0] = tfUsername.getText();
			result[1] = new String(pfPassword.getPassword());
		}
		return result;
	}
	




	public static void main(String[] args) {
		String dbServer = "jdbc:mysql://127.0.0.1:3306/group17?allowPublicKeyRetrieval=true&useSSL=false";

		String userName = "";
		String password = "";

		String result[] = loginDialog();
		userName = result[0];
		password = result[1];

		Connection conn;
		Statement stmt;
		if (result[0] == null || result[1] == null) {
			System.out.println("Terminating: No username nor password is given");
			return;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbServer, userName, password);
			stmt = conn.createStatement();
			String sqlQuery = "";
			String option = "";
			String instruction = "Enter Q3: to preform query Q3 \n" + 
								 "Enter Q7: to preform query Q7 \n" +
								 "Enter Q9: to preform query Q9 \n" +
								 "Enter Q16: to preform query Q16 \n" +
								 "Enter Q18: to preform query Q18 \n" +
								 "Enter Q23: to preform query Q23 \n" +
								 "Enter I to insert data \n" +
								 "Enter D to delete data \n" +
								 "Enter e to exit the program \n";			

			while (true) {
				option = JOptionPane.showInputDialog(instruction);

				if (option.equals("Q3")) {	
				}

				else if (option.equals("Q7")) {
				}

				else if (option.equals("Q9")) {
				}
				
				else if (option.equals("Q16")) {
				}
				
				//TODO
				else if (option.equals("Q18")) {
					int k = Integer.parseInt(JOptionPane.showInputDialog("Enter a 'k' value:"));
					String subCategory = JOptionPane.showInputDialog("Enter a sub-category (i.e., 'GOP', 'Democrat'");
					int month = Integer.parseInt(JOptionPane.showInputDialog("Enter a month"));
					int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year"));
					
					sqlQuery = "";
					stmt.executeQuery(sqlQuery);
				}
				
				//TODO
				else if (option.equals("Q23")) {
					int k = Integer.parseInt(JOptionPane.showInputDialog("Enter a 'k' value:"));
					String subCategory = JOptionPane.showInputDialog("Enter a sub-category (i.e., 'GOP', 'Democrat'");
					String months = JOptionPane.showInputDialog("Enter a list of months");
					int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year"));
					
					sqlQuery = "";
					stmt.executeQuery(sqlQuery);
				}
				
				//TODO
				else if (option.equals("I")) {
				}
				//TODO
				else if (option.equals("D")) {
				}
				
				else if (option.equals("e")) {
					break;
				}
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Program terminates due to errors");
			e.printStackTrace(); 
		}
	}

}
