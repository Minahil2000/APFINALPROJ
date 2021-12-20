package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Vector;

public class DatabaseHandler 
{
	public LinkedList<String> getSaleProperties(long price,String type,String city,String rooms,String floors)
	{
		String query = "select * from property join address on property.address_id=address.id where type='" + type + "' AND price<=" + price + "AND city='" + city +"' AND status='For Sale' AND floors='" + floors +"' AND rooms='" + rooms+"'";
		LinkedList<String> data = new LinkedList<String>();
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			String temp = "";
			while(rs.next()) 
			{
				temp += rs.getString("owner_id");
				temp += "|";
				temp += rs.getString("address_id");
				temp += "|";
				temp += rs.getString("price");
				temp += "|";
				temp += rs.getString("status");
				temp += "|";
				temp += rs.getString("floors");
				temp += "|";
				temp += rs.getString("rooms");
				data.add(temp);
			}
			System.out.println("Connection Established");
    		con.close();
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		
		
		
		
		return data;
		
	}

	public String getOwnerDetails(String id)
	{
		
		String query = "select * from client where id=" +id;
		String data = "";
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) 
			{
				data += rs.getString("name");
				data += "|";
				data += rs.getString("phone_number");
				data += "|";
				data += rs.getString("email");
				System.out.println(data);
			}
			
    		con.close();
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		return data;
	}
	public LinkedList<String> getRentalProperties(long rent,String type,String city)
	{
		LinkedList<String> data = new LinkedList<String>();
		String query = "select * from property join address on property.address_id=address.id where type='" + type + "' AND price<=" + rent + "AND city='" + city+ "' AND status='For Rent'";
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			String temp = "";
			while(rs.next()) 
			{
				temp += rs.getString("owner_id");
				temp += "|";
				temp += rs.getString("address_id");
				temp += "|";
				temp += rs.getString("price");
				temp += "|";
				temp += rs.getString("status");
				temp += "|";
				temp += rs.getString("floors");
				temp += "|";
				temp += rs.getString("rooms");
				data.add(temp);
			}
    		con.close();
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		
		
		
		
		return data;
		
	}
	public LinkedList<String> getCities()
	{
		LinkedList<String> data = new LinkedList<String>();
		String query = "select distinct city from address";
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) 
			{
				String temp = rs.getString("city");
				data.add(temp);
			}
    		con.close();
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		
		
		return data;
	}
	public void saveClient(String name,String gender,long number,long cnic,String area,String street,String city,String province,String country)
	{
		FileHandler file = new FileHandler();
		file.saveClient(name,gender,number,cnic,area,street,city,province,country);
		String query = "INSERT INTO address(area,street,city,province,country) VALUES('"+area+ "','" +street+ "','"+city+"','"+province+"','"+country+"')";
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Address Saved In Database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		
		query = "insert into client(name,gender,address_id,phone_number,cnic) VALUES ('"+ name + "','"+ gender + "',(select id from address where id=any(select max(id) from address))," + number + ","+ cnic +")";
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Client Saved In Database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
	}
	public void updatePropertyDetails(String property_id,String t,String s,String area,String street,String city,String province,String country,String price,String floors,String room)
	{
		String query = "update Property set type ='" + t + "', price = " + price +", floors=" + floors + ",rooms = " + room +" where id= "+property_id;
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Property updated updated In Database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		
		query = "update address set area='"+ area + "', street= '" + street +"', city='" + city + "',province='" + province + "', country='" + country + "' where id=any( select address_id from property where id=" + property_id +")";
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Address updated updated In Database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
	}
	public void saveClientCredentials(String email,String password)
	{
		String query = "UPDATE client SET email = '" + email + "', password = '" + password + "' where id=any(select max(id) from client)";
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Address Saved In Database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
	}
	public void listProperty(String loggedEmail,Double price,String status,String type,int floors,int rooms,String area,String street,String city,String province,String country)
	{

		String query = "SELECT * FROM client WHERE email = " + "'" + loggedEmail + "'";
		String clientID = null;
		String AddressID = null;
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) 
			{
				clientID = rs.getString("id");
			}
			
			//System.out.println("Connection Established");
			
    		con.close();
    		
    		
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
	
		
		query = "INSERT INTO address(area,street,city,province,country) VALUES('"+area+ "','" +street+ "','"+city+"','"+province+"','"+country+"')";
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			//System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Address Saved In Database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		query = "insert into property(type,owner_id,address_id,price,status,floors,rooms) VALUES ('"+ type + "',"+ clientID +",(select id from address where id=any(select max(id) from address)),"+ price + ",'"+status+"',"+floors+"," +rooms+")";
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			//System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Property Saved In Database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
	}
	public boolean checkEmail(String email)
	{
		String query = "select email from client where email= '"+ email +"'";
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) 
			{
				return true;
			}
			System.out.println("Connection Established");
    		con.close();
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		return false;
	}
	public boolean checkCredentials(String email,String password)
	{
		String query = "select password from client where email='"+ email + "' AND password= '" + password +"'";
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) 
			{
				return true;
			}
			System.out.println("Connection Established");
			con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		return false;
	}
	public LinkedList<String> getOwnedProperties(String email)
	{
		LinkedList<String> data = new LinkedList<String>();
		LinkedList<String> propertyDesc = new LinkedList<String>();
		LinkedList<String> address = new LinkedList<String>();
		LinkedList<String> address_ID = new LinkedList<String>();
		String query = "SELECT * FROM client WHERE email = " + "'" + email + "'";
		String clientID = null;
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) 
			{
				clientID = rs.getString("id");
				System.out.println(clientID);
			}
			//System.out.println("Connection Established");
    		con.close();
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		
		query = "select * from property where owner_id =" + clientID;
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) 
			{
				String propertyID = rs.getString("id");
				String type = rs.getString("type");
				String addressID = rs.getString("address_id");
				String price = rs.getString("price");
				String status = rs.getString("status");
				
				String temp = propertyID + " | " + type + " | " + addressID + " | " + price + " | " + status; 
				propertyDesc.add(temp);
			}
			for(int i=0;i<propertyDesc.size();i++)
				//System.out.println(propertyDesc.get(i));
			
			//System.out.println("Connection Established");
    		con.close();
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		query = "select * from address where id= ";
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");

			for(int i=0;i<propertyDesc.size();i++)
			{
				String temp = propertyDesc.get(i);
				String id = ""; 
				int count = 0;
				for(int j=0;j<temp.length();j++)
				{
					if(temp.charAt(j) == '|')
					{
						count++;
						continue;
					}
					if(count == 2)
					{
					
						for(int k=j;temp.charAt(k) != '|';k++)
						{
							id += temp.charAt(k);
						}
						break;
					}
				}
				address_ID.add(id);
			}
			//System.out.println("Address ID");
			//for(int i=0;i<address_ID.size();i++)
				//System.out.println(address_ID.get(i));
			
			for(int i=0;i<address_ID.size();i++)
			{
				
				query = "select * from address where id= ";
				query += address_ID.get(i);
				//System.out.println(query);
				Statement stmt=con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) 
				{

				    String area = rs.getString("area");
				    String street = rs.getString("street");
				    String city = rs.getString("city");
				    String province = rs.getString("province");
				    String country = rs.getString("country");
					String temp2 = area + " " + street + " " + city + " " + province + " " + country; 
					address.add(temp2);
				}
			}
			//System.out.println("Description");
			for(int i=0;i<address.size();i++)
			{
				String temp = propertyDesc.get(i);
				temp += " | ";
				temp += address.get(i);
				data.add(temp);
			}

		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		return data;
	}
	public String getProperty(String id)
	{
		String data = "";
		String query = "select * from property where id = " + id;
		String a_id = "";
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()) 
			{
				a_id = rs.getString("address_id");
				data += rs.getString("type");
				data += "|";
				data += rs.getString("status");
				data += "|";
				data += rs.getString("price");
				data += "|";
				data += rs.getString("floors");
				data += "|";
				data += rs.getString("rooms");
			}
			System.out.println(data);
			//System.out.println("Connection Established");
			
    		con.close();
    		
    		
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		query = "select * from address where id=" + a_id;
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");
			//System.out.println("Connection Established");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			data += "|";
			while(rs.next()) 
			{
				data += rs.getString("area");
				data += "|";
				data += rs.getString("street");
				data += "|";
				data += rs.getString("city");
				data += "|";
				data += rs.getString("province");
				data += "|";
				data += rs.getString("country");
			}
			System.out.println(data);
			//System.out.println("Connection Established");
			
    		con.close();
    		
    		
		}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Diver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		return data;
		
	}
	public void deleteProperty(String property_id)
	{
		
		

		String query = "delete from property where id=" + property_id;
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			//System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Property deleted from database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
		
		query = "delete from address where id = ANY(select address_id from property where id='" + property_id +"')";
		
		try 
    	{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","database");  
			//System.out.println("Connected to the database");
			PreparedStatement pstmt = con.prepareStatement(query);  
			pstmt.execute();
			System.out.println("Property address deleted from database...");
    		con.close();
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.out.println("Driver not loaded..");
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Connection not established...");
    	}
	}
}
