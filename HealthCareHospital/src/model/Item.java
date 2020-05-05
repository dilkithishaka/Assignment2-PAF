package model; 
 
import java.sql.*; 

 
public class Item { 
	
	private Connection connection() {
		Connection con = null; 
		
		try   {     
			Class.forName("com.mysql.jdbc.Driver");         
			//Provide the correct details: DBServer/DBName, username, password     
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "root");   
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				 				 
			}
		
		 return con; 
		}
	
	public String insertItem(String code, String name, String price, String desc) {
		String output = ""; 
		
		try {
			Connection con = connection(); 
			 
			   if (con == null)    {
				   return "Error while connecting to the database for inserting."; 
				   } 
			 
			   // create a prepared statement    
			   String query = " insert into items           "
			   		+ "(`itemId`,`itemCode`,`itemName`,`itemPrice`,`itemDes`)"     
					   + " values (?, ?, ?, ?, ?)"; 
			 
			   PreparedStatement preparedStmt = con.prepareStatement(query); 
			   
			// binding values    
			   preparedStmt.setInt(1, 0);    
			   preparedStmt.setString(2, code);   
			   preparedStmt.setString(3, name);    
			   preparedStmt.setDouble(4, Double.parseDouble(price));    
			   preparedStmt.setString(5, desc); 
			   
			   preparedStmt.execute();    con.close(); 
			   
			   output = "Inserted successfully"; 
			   
		}catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting the item.";    
			System.err.println(e.getMessage());   
		}
		
		return output;
	}
	
	public String readItems() {
		
		String output = ""; 
		
		try {
			Connection con = connection(); 
			 
			   if (con == null)    {
				   return "Error while connecting to the database for reading."; 
				   } 
			 
			   // Prepare the html table to be displayed    
			   output = "<table border=\"1\"><tr><th>Item Code</th><th>Item Name</th><th>Item Price</th><th>Item Description</th><th>Update</th><th>Remove</th></tr>"; 
			 
			   String query = "select * from items";    
			   Statement stmt = con.createStatement();    
			   ResultSet rs = stmt.executeQuery(query); 
			   
			   while (rs.next())    {     
				   String itemId = Integer.toString(rs.getInt("itemId"));     
				   String itemCode = rs.getString("itemCode");     
				   String itemName = rs.getString("itemName");     
				   String itemPrice = Double.toString(rs.getDouble("itemPrice"));     
				   String itemDes = rs.getString("itemDes"); 
			  
			   
			    // Add into the html table     
			   output += "<tr><td><input id=\"hidItemIDUpdate\" name=\"hidItemIDUpdate\"     "
			   		+ "type=\"hidden\" value=\"" + itemId + "\">"     
					   + itemCode + "</td>";   
			   output += "<td>" + itemName + "</td>";     
			   output += "<td>" + itemPrice + "</td>";     
			   output += "<td>" + itemDes + "</td>"; 
			 
			    // buttons     
			   output += "<td><input name=\"btnUpdate\"type=\"button\" value=\"Update\"class=\" btnUpdate btn btn-secondary\"></td> "
			   		+ "<td><form method=\"post\" action=\"items.jsp\"><input name=\"btnRemove\" type=\"submit\"value=\"Remove\" class=\"btn btn-danger\"><input name=\"hidItemIDDelete\" type=\"hidden\"value=\"" + itemId + "\">" + "</form></td></tr>";     
			  
		}
			   con.close(); 
			   
			   // Complete the html table    
			   output += "</table>"; 
			 
			 
			
		}catch (Exception e) {
			// TODO: handle exception
			output = "Error while reading the items.";    
			System.err.println(e.getMessage());   
		}
		
		return output;
	}
	
	public String updateItem(String ID, String code, String name, String price, String desc)  {   
		String output = ""; 
		
	try{
		Connection con = connection(); 
		 
		   if (con == null)    {
			   return "Error while connecting to the database for updating."; 
			   } 
		 
		   // create a prepared statement    
		   String query = "UPDATE items SET itemCode=?,itemName=?,itemPrice=?,itemDes=? WHERE itemId=?"; 
		 
		   PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		   // binding values   
		   preparedStmt.setString(1, code);   
		   preparedStmt.setString(2, name);    
		   preparedStmt.setDouble(3, Double.parseDouble(price));   
		   preparedStmt.setString(4, desc);   
		   preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 
		   // execute the statement   
		   preparedStmt.execute();    
		   con.close(); 
		 
		   output = "Updated successfully"; 
	}catch (Exception e) {
		output = "Error while updating the item.";    
		System.err.println(e.getMessage());   
	}
	
	return output;
}
	
	public String deleteItem(String itemId) {
		String output = ""; 
		
		try {
			Connection con = connection(); 
			 
			   if (con == null)    {
				   return "Error while connecting to the database for deleting."; 
				   } 
			 
			   // create a prepared statement    
			   String query = "delete from items where itemID=?"; 
			 
			   
			   PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			   // binding values    
			   preparedStmt.setInt(1, Integer.parseInt(itemId)); 
			 
			   // execute the statement    
			   preparedStmt.execute();    
			   con.close(); 
			 
			   output = "Deleted successfully"; 
			   
		}catch (Exception e) {
			output = "Error while deleting the item.";    
			System.err.println(e.getMessage()); 
		}
		return output;
	}
	 
	 
	
}