package model; 
import java.sql.*; 


public class Inquiry 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "mysql"); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readInquiry() 
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Inquiry ID</th><th>Inquiry Name</th>" + "<th>Inquiry area</th>"
			+ "<th>Phone Number</th>" + "<th>email </th></tr>";
 String query = "select * from inquiry"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
	 String inquiryNumber = Integer.toString(rs.getInt("inquiryNumber"));
		String inquiryName = rs.getString("inquiryame");
		String inquiryArea = rs.getString("inquiryArea");
		String inquiryPnumber = rs.getString("inquiryPnumber");
		String inquiryMail = rs.getString("inquiryMail");
		// Add into the html table
		output += "<tr><td>" + inquiryNumber + "</td>";
		output += "<td>" + inquiryName + "</td>";
		output += "<td>" + inquiryArea + "</td>";
		output += "<td>" + inquiryPnumber + "</td>";
		output += "<td>" + inquiryMail + "</td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the employee."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertEmployee(String inquiryNumber, String inquiryName, String inquiryArea,
		String inquiryPnumber, String inquiryMail) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into inquiry (`inquiryNumber`,`inquiryName`,`inquiryArea`,`inquiryPnumber`,`inquiryMail`) values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(1, inquiryNumber);
		 preparedStmt.setString(2, inquiryName);
		 preparedStmt.setString(3, inquiryArea);
		 preparedStmt.setString(4, inquiryPnumber);
		 preparedStmt.setString(5, inquiryMail); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newInquiry = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newInquiry + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updateInquiry(String idinquiry, String inquiryNumber, String inquiryName, String inquiryArea,
		String inquiryPnumber, String inquiryMail) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE inquiry SET inquiryNumber=?,inquiryName=?,inquiryArea=?,inquiryPnumber=?,inquiryMail=? WHERE idinquiry=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, inquiryNumber);
			preparedStmt.setString(2, inquiryName);
			preparedStmt.setString(3, inquiryArea);
			preparedStmt.setString(4, inquiryPnumber);
			preparedStmt.setString(5, inquiryMail);
			preparedStmt.setInt(6, Integer.parseInt(idinquiry)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newInquiry = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newInquiry + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deleteInquiry(String idinquiry) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from inquiry where idinquiry=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(idinquiry)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newInquiry = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newInquiry + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


