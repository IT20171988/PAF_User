package com.user.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class userModel {
	
	
	//Creating the DB connection
		public Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powergrid", "root", "Dilani@23");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		}
		
		public String insertUser(String user_no, String name, String username, String password, String email, String phoneno )
		{
			String output = "";
			try
			 {
			 Connection con = connect();
			 if (con == null)
			 	{return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 
			 
			 String query = " insert into users (UserID,`UserNO`,`CusName`,`Username`,`Password`, `Email`, `Phoneno`)"+" values ( ?, ?, ?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, user_no);
			 preparedStmt.setString(3, name);
			 preparedStmt.setString(4, username);
			 preparedStmt.setString(5, password);
			 preparedStmt.setString(6, email);
			 preparedStmt.setString(7, phoneno);
			 
			 // execute the statement
			 
			 preparedStmt.execute();
			 con.close();
			 output = "User Added successfully";
			 }
			
			catch (Exception e)
			{
				output = "Error occur during inserting";
				System.err.println(e.getMessage());
			}
			return output;
		}

		
//		read user details
		
		
		public String readUser() {
			
			String output = "";
			
			try {
				
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
				// Prepare the HTML table to be displayed
				output = "<table border='1'>"
						+ "<tr><th>User ID</th>" 
						+ "<th>User NO</th>" 
						+ "<th>Cus_Name</th>" 
						+ "<th>Username</th>" 
						+ "<th>Password</th>" 
						+"<th>Email</th>"
						+"<th>Phoneno</th>"
						+"<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from users";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while(rs.next()) {
					
					String UserID = Integer.toString(rs.getInt("UserID"));
					String UserNO = rs.getString("UserNO");
					String CusName = rs.getString("CusName");
					String Username = rs.getString("Username");
					String Password = rs.getString("Password");
					String Email = rs.getString("Email");
					String Phoneno = rs.getString("Phoneno");
					
					// Add into the HTML table
					
					output += "<tr><td>" + UserID + "</td>";
					output += "<td>" + UserNO + "</td>";
					output += "<td>" + CusName + "</td>";
					output += "<td>" + Username + "</td>";
					output += "<td>" + Password + "</td>";
					output += "<td>" + Email + "</td>";
					output += "<td>" + Phoneno + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
					+ "<td><form method='post' action=''>"
					+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
					+ "<input name='cus_id' type='hidden' value='" + UserID
					+ "'>" + "</form></td></tr>";
					
				}
				 con.close();
				 // Complete the HTML table
				 
				 output += "</table>";
				
			}catch(Exception e) {
				
				output = "Error while reading the user details of users";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		

		
		// Update Users 
		
		public String updateUser(String ID, String user_no, String name, String username, String password, String email, String phoneno ) {
			
			String output = "";
			
			try {
				
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for updating."; 
				}
				
				
				
				// create a prepared statement
				String query = "UPDATE users SET UserNO=?,CusName=?,Username=?,Password=?,Email=?,Phoneno=? WHERE userID=? ";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				
				// binding values
				preparedStmt.setString(1, user_no);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, username);
				preparedStmt.setString(4, password);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, phoneno);
				preparedStmt.setInt(7,Integer.parseInt(ID));
				
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated User Details successfully";
				   
			}catch(Exception e) {
				
				output = "Error while updating the user details.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		// Deleted User 
		
		public String deleteuser(String UserID){
			
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for deleting."; 
				}
				// create a prepared statement
				String query = "delete from users where UserID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1,  Integer.parseInt(UserID));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted User details successfully";
				
			}
			catch (Exception e)
			{
				output = "Error while deleting the user";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}

}
