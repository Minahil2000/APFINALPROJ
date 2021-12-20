package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileHandler 
{
	public void saveClient(String name,String gender,long number,long cnic,String area,String street,String city,String province,String country)
	{
		try
		{
			File myObj = new File("Clients.txt");
			if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
			} 
			else 
			{
				System.out.println("File already exists.");
			}
		} 
		catch (IOException e) 
		{
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}

		try 
		{
			FileWriter myWriter = new FileWriter("Clients.txt",true);
			String data = ""; 
			data += name;
			data += "|";
			data += gender;
			data += "|";
			data += number;
			data += "|";
			data += cnic;
			data += "|";
			data += area;
			data += "|";
			data += street;
			data += "|";
			data += city;
			data += "|";
			data += province;
			data += "|";
			data += country;
			data += "\n";
			myWriter.write(data);
			myWriter.close();
			System.out.println("Client Saved in File");
			
		} 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	public void listProperty(String loggedEmail,Double price,String status,String type,int floors,int rooms,String area,String street,String city,String province,String country)
	{
		int id = 1;
		
		try
		{
			File myObj = new File("Properties.txt");
			if (myObj.createNewFile()) 
			{
				System.out.println("File created: " + myObj.getName());
			} 
			else 
			{
				System.out.println("File already exists.");
			}
		} 
		catch (IOException e) 
		{
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		try 
		{
		      File myObj = new File("Properties.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine())
		      {
		    	
		        String data = myReader.nextLine();
		        String temp = "";
		        for(int i=0;data.charAt(i) != '|';i++)
		        {
		        	temp += data.charAt(i);
		        }
		        id = Integer.parseInt(temp) + 1;
		      }
		      myReader.close();
		}
		catch (FileNotFoundException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}


		try 
		{
			FileWriter myWriter = new FileWriter("Properties.txt",true);
			String data = "";
			data += id;
			data += "|";
			data += price;
			data += "|";
			data += status;
			data += "|";
			data += type;
			data += "|";
			data += floors;
			data += "|";
			data += rooms;
			data += "|";
			data += area;
			data += "|";
			data += street;
			data += "|";
			data += province;
			data += "|";
			data += country;
			data += "\n";
			myWriter.write(data);
			myWriter.close();
			System.out.println("Client Saved in File");
			
		} 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	public void updatePropertyDetails(String property_id,String t,String s,String area,String street,String city,String province,String country,String price,String floors,String room)
	{
		
		LinkedList<String> lines = new LinkedList<String>();
		try 
		{
			
		      File myObj = new File("Properties.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine())
		      {
		    	  System.out.println(3);
		        String data = myReader.nextLine();
		        String temp = "";
		        for(int i=0;data.charAt(i) != '|';i++)
		        {
		        	temp += data.charAt(i);
		        }
		        String str = "";
		        for(int i=0;i<property_id.length();i++)
		        {
		        	if(property_id.charAt(i) == ' ')
		        		break;
		        	str += property_id.charAt(i);
		        }
		        System.out.println(str + " " + str.length());
		        System.out.println(temp + " " + temp.length());
		        if(Integer.parseInt(str) == Integer.parseInt(temp))
		        {
		        	System.out.println(4);
		        	System.out.println("MATCHED");
		        	data = " ";
					data += temp;
					data += "|";
					data += price;
					data += "|";
					data += s;
					data += "|";
					data += t;
					data += "|";
					data += floors;
					data += "|";
					data += room;
					data += "|";
					data += area;
					data += "|";
					data += street;
					data += "|";
					data += province;
					data += "|";
					data += country;
					data += "\n";
		        	lines.add(data);
		        }
		        else
		        {
		        	lines.add(data);
		        }
		      }
		     
		      myReader.close();
		}
		catch (FileNotFoundException e) 
		{
			
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}

		for(int i=0;i<lines.size();i++)
		{
			
			try 
			{
			
				FileWriter myWriter = new FileWriter("Properties.txt");
				
					String data = lines.get(i);
					System.out.println("WRITING: "+data);
					myWriter.write(data);
				
				myWriter.close();
				System.out.println("Client Saved in File");
				
			} 
			catch (IOException e) 
			{
				System.out.println(9);
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
		}
	}
	
}
