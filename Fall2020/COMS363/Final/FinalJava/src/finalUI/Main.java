package finalUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
					int k = Integer.parseInt(JOptionPane.showInputDialog("Enter a 'k' value:"));
					int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year"));
					
					sqlQuery = "SELECT COUNT(DISTINCT users.state) AS numStates, group_concat(DISTINCT users.state) as States, hashtags.name\r\n" + 
							"FROM hashtags, tweets, users\r\n" + 
							"WHERE users.state != \"\" and users.state != \"na\" and year(tweets.createdTime) = " + year + " AND tweets.tid = hashtags.tid\r\n" + 
							"AND users.screenName = tweets.userScreenName \r\n" + 
							"GROUP BY hashtags.name\r\n" + 
							"ORDER BY numStates DESC\r\n" + 
							"LIMIT " + k + ";";
					
					ResultSet rs = stmt.executeQuery(sqlQuery);
					
					while(rs.next()) {
						System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
					}
					
					
				}

				else if (option.equals("Q7")) {
					
					int k = Integer.parseInt(JOptionPane.showInputDialog("Enter a 'k' value:"));
					String hashtag = JOptionPane.showInputDialog("Enter a hashtag:");
					String state = JOptionPane.showInputDialog("Enter a state:");
					int month = Integer.parseInt(JOptionPane.showInputDialog("Enter a month:"));
					int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year:"));
					
					
					sqlQuery = "SELECT count(tweets.tid), users.screenName, users.category\r\n" + 
							"FROM users\r\n" + 
							"join tweets on tweets.userScreenName = users.screenName\r\n" + 
							"join hashtags on hashtags.tid = tweets.tid\r\n" + 
							"where hashtags.name = '" + hashtag + "'\r\n" + 
							"and users.state = '" + state + "'\r\n" + 
							"and month(tweets.createdTime) = " + month + "\r\n" + 
							"and year(tweets.createdTime) = " + year + "\r\n" + 
							"group by users.screenName\r\n" + 
							"order by count(tweets.tid) DESC\r\n" + 
							"limit " + k + ";";
					
					ResultSet rs = stmt.executeQuery(sqlQuery);
					
					while(rs.next()) {
						System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
					}
					
				}

				else if (option.equals("Q9")) {
					
					int k = Integer.parseInt(JOptionPane.showInputDialog("Enter a 'k' value:"));
					String subCategory = JOptionPane.showInputDialog("Enter a sub-category (i.e., 'GOP', 'Democrat'");
					
					sqlQuery = "SELECT users.screenName, users.subCategory, users.numFollowers\r\n" + 
							"FROM users\r\n" + 
							"WHERE users.subcategory = '" + subCategory + "'\r\n" + 
							"ORDER BY users.numFollowers DESC\r\n" + 
							"LIMIT " + k + ";";
					
					ResultSet rs = stmt.executeQuery(sqlQuery);
					
					while(rs.next()) {
						System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
					}
					
				}
				
				else if (option.equals("Q16")) {
					
					int k = Integer.parseInt(JOptionPane.showInputDialog("Enter a 'k' value:"));
					int month = Integer.parseInt(JOptionPane.showInputDialog("Enter a month"));
					int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year"));
					sqlQuery = "SELECT users.screenName, users.category, tweets.text, tweets.retweetCt, urls.address\r\n" + 
							"From users\r\n" + 
							"Join tweets on tweets.userScreenName = users.screenName\r\n" + 
							"Join urls on urls.tid = tweets.tid\r\n" + 
							"Where month(tweets.createdTime) = " + month + "\r\n" + 
							"And year(tweets.createdTime) = " + year + "\r\n" + 
							"Order by tweets.retweetCt desc\r\n" + 
							"limit " + k + ";";
					
					ResultSet rs = stmt.executeQuery(sqlQuery);
					
					while(rs.next()) {
						System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + ", " + rs.getString(5));
					}
					
				}
				
				//Q18
				else if (option.equals("Q18")) {
					int k = Integer.parseInt(JOptionPane.showInputDialog("Enter a 'k' value:"));
					String subCategory = JOptionPane.showInputDialog("Enter a sub-category (i.e., 'GOP', 'Democrat'");
					int month = Integer.parseInt(JOptionPane.showInputDialog("Enter a month"));
					int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year"));
					
					stmt.executeUpdate("CREATE INDEX screenNameInd\r\n" + 
										"ON users (screenName);");
					sqlQuery = "SELECT COUNT(U1.tid), mentioned.screenName AS user_name, mentioned.state AS mentionedUserState, GROUP_CONCAT(DISTINCT posters.screenName) as postingUsers\r\n" + 
							"FROM users as mentioned\r\n" + 
							"JOIN usertweets as U1 on U1.screenName = mentioned.screenName\r\n" + 
							"JOIN tweets ON tweets.tid = U1.tid AND month(tweets.createdTime) = " + month + " AND year(tweets.createdTime) = " + year + "\r\n" + 
							"JOIN users as posters on posters.screenName = tweets.userScreenName\r\n" + 
							"WHERE posters.subcategory in('" + subCategory + "')  \r\n" + 
							"GROUP BY mentioned.screenName\r\n" + 
							"ORDER BY COUNT(U1.tid) DESC\r\n" + 
							"LIMIT " + k + ";";
					stmt.executeUpdate("ALTER TABLE users\r\n" + 
										"DROP INDEX screenNameInd;");
					ResultSet rs = stmt.executeQuery(sqlQuery);
					
					while(rs.next()) {
						System.out.println(rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));

					}
				}
				
				//Q23
				else if (option.equals("Q23")) {
					int k = Integer.parseInt(JOptionPane.showInputDialog("Enter a 'k' value:"));
					String subCategory = JOptionPane.showInputDialog("Enter a sub-category (i.e., 'GOP', 'Democrat'");
					String months = JOptionPane.showInputDialog("Enter a list of months");
					int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year"));
					
					
					//Grab Integers from list input
					String monthsSQL = "";		
					boolean first = false;
					for(int i = 0; i < months.length(); i++)
					{
						if(Character.isDigit(months.charAt(i)))
						{
							if(i == 0)
							{
								monthsSQL += "month(tweets.createdTime) = " + months.charAt(i); 
								first = true;
							}
							else
								monthsSQL += " OR month(tweets.createdTime) = " + months.charAt(i); 
						}
						else
						{
							
						}
					}
					
					sqlQuery = "SELECT hashtags.name, COUNT(tweets.tid) as num_uses\r\n" + 
							"FROM hashtags\r\n" + 
							"JOIN tweets ON tweets.tid = hashtags.tid\r\n" + 
							"JOIN users ON users.screenName = tweets.userScreenName \r\n" + 
							"WHERE users.subCategory in('" + subCategory + "') AND (" + monthsSQL + ") AND year(tweets.createdTime) = " + year + "\r\n" + 
							"GROUP BY hashtags.name\r\n" + 
							"ORDER BY num_uses\r\n" + 
							"DESC LIMIT " + k + ";";
					ResultSet rs = stmt.executeQuery(sqlQuery);
					
					while(rs.next()) {
						System.out.println(rs.getString(1) + ", " + rs.getString(2));
					}
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
