package project;

import java.io.IOException;
import java.util.LinkedList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RealEstate 
{
	@FXML
	private TextField nameField;
	@FXML
	private TextField rentField;
	@FXML
	private TextField addressArea;
	@FXML
	private TextField addressStreet;
	@FXML
	private TextField addressCity;
	@FXML
	private TextField addressProvince;
	@FXML
	private TextField addressCountry;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField cnicNumber;
	@FXML
	private RadioButton button1;
	@FXML
	private RadioButton button2;
	@FXML
	private RadioButton button3;
	@FXML
	private RadioButton button4;
	@FXML
	private RadioButton button5;
	@FXML
	private RadioButton button6;
	@FXML
	private TextField emailField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField floorField;

	@FXML
	private TextField roomField;
	@FXML
	private Label popup1;
	@FXML
	private Label popup2;
	@FXML
	private Label popup3;
	@FXML
	private Label popup4;
	@FXML
	private Text text1;
	@FXML
	private Text text2;
	@FXML
	private ChoiceBox<String> properties;
	@FXML
	private ChoiceBox<String> cities;
	@FXML
	private ChoiceBox<String> type;
	@FXML
	private ChoiceBox<String> status;
	private static LinkedList<String> data;
	private static String loggedEmail = "";
	private static String id = "";
	static String Ptype = "";
	static String Pstatus = "";
	static String price = "";
	static String floors = "";
	static String rooms = "";
	static String area = "";
	static String street = "";
	static String city = "";
	static String province = "";
	static String country = "";
	
	@FXML
	private void showSaleProperties() throws IOException
	{
		long price = Long.valueOf(priceField.getText().toString());
		String floors = floorField.getText().toString();
		String rooms = roomField.getText().toString();
		String type = "";
		if(button1.isSelected())
		{
			type = "Plot";
		}
		else if(button2.isSelected())
		{
			type = "House";
		}
		else if(button3.isSelected())
		{
			type = "Apartment";
		}
		else if(button4.isSelected())
		{
			type = "Office";
		}
		String city = cities.getValue();
		DatabaseHandler db = new DatabaseHandler();
		data = db.getSaleProperties(price,type,city,rooms,floors);
		showSaleListScreen();
	}
	@FXML
	private void showSaleListScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("SaleListScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewClientLoginScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("ClientLoginScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void showOwnerDetails()
	{
		DatabaseHandler db = new DatabaseHandler();

		System.out.println(id);
		String data = db.getOwnerDetails(id);
		System.out.println(data);
		int count  = 0;
		String name = "";
		String phoneNum = "";
		String email = "";
		for(int i=0;i<data.length();i++)
		{
			if(data.charAt(i) == '|')
			{
				count++;
				i++;
				
			}
			
			if(count == 0)
			{
				name += data.charAt(i);
				continue;	
			}
			if(count == 1)
			{
				phoneNum += data.charAt(i);
				continue;
			}
			if(count == 2)
			{
				email += data.charAt(i);
				continue;
			}

		}
		nameField.setText(name);
		phoneNumber.setText(phoneNum);
		emailField.setText(email);
		System.out.println(name);
		System.out.println(phoneNum);
		System.out.println(email);
	}
	@FXML
	private void contactOwner() throws IOException
	{
		String temp = properties.getValue();
		id = "";
		for(int i=0;temp.charAt(i)!='|';i++)
			id += temp.charAt(i);
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("OwnerPage.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}	
	@FXML
	private void showCities()
	{
		DatabaseHandler db = new DatabaseHandler();
		LinkedList<String> city = new LinkedList<String>();  
		city = db.getCities();
		cities.getItems().addAll(city);
	}
	@FXML
	private void showRentalProperties() throws IOException
	{
		long rent = Long.valueOf(rentField.getText().toString());
		String type = "";
		if(button1.isSelected())
		{
			type = "Plot";
		}
		else if(button2.isSelected())
		{
			type = "House";
		}
		else if(button3.isSelected())
		{
			type = "Apartment";
		}
		else if(button4.isSelected())
		{
			type = "Office";
		}
		String city = cities.getValue();
		DatabaseHandler db = new DatabaseHandler();
		data = db.getRentalProperties(rent,type,city);
		for(int i=0;i<data.size();i++)
		{
			System.out.println(data.get(i));
		}
		showRentalListScreen();
	}
	@FXML
	private void showSaleListing() throws IOException
	{
		for(int i=0;i<data.size();i++)
		{
			System.out.println(data.get(i));
		}
		properties.getItems().addAll(data);
	}
	
	@FXML
	private void showRentalListing() throws IOException
	{

		properties.getItems().addAll(data);
	}
	@FXML
	private void showRentalListScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("RentalListing.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewAdminLoginScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("AdminLoginScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewClientLandingScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("ClientLandingScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewManagePropertyScreen() throws IOException

	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("ManagePropertyScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewBuyPropertyScreen() throws IOException

	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("BuyPropertyScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewAreaDetailScreen() throws IOException

	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("AreaDetailScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewRentPropertyScreen() throws IOException

	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("RentPropertyScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewAddPropertyScreen() throws IOException

	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("AddPropertyScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewHomeScreen() throws IOException
	{
		/*
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	    */
		ScreenThread t = new ScreenThread();
		t.run("HomeScreen.fxml");
	}
	@FXML
	private void viewUpdatePropertyScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("UpdatePropertyScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewUpdatePropertyDetailScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("UpdatePropertyDetailScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewDeletePropertyScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("DeletePropertyScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void viewPropertyDeletedScreen() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("PropertyDeleted.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}

	@FXML
	
	private void viewSignUpScreen() throws IOException
	{
		ScreenThread t = new ScreenThread();
		t.run("SignUpScreen.fxml");
	}
	@FXML
	private void viewSetClientCredentials() throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource("SetClientCredentialsScreen.fxml"));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
	@FXML
	private void selectPlot()
	{
		text1.setVisible(false);
		text2.setVisible(false);
		roomField.setText("0");
		floorField.setText("0");
		roomField.setVisible(false);
		floorField.setVisible(false);
	}
	@FXML
	private void unselectPlot()
	{
		text1.setVisible(true);
		text2.setVisible(true);
		roomField.setText("0");
		floorField.setText("0");
		roomField.setVisible(true);
		floorField.setVisible(true);
	}
	@FXML
	private void SignUp() throws IOException 
	{
		String name = nameField.getText().toString();
		String area = addressArea.getText().toString();
		String street = addressStreet.getText().toString();
		String city = addressCity.getText().toString();
		String province = addressProvince.getText().toString();
		String country = addressCountry.getText().toString();
		
		long number = Long.valueOf(phoneNumber.getText().toString());
		long cnic = Long.valueOf(cnicNumber.getText().toString());
		String gender = "";
		if(button1.isSelected())
		{
			gender = "Male";
		}
		else if(button2.isSelected())
		{
			gender = "Female";
		}
		else if(button3.isSelected())
		{
			gender = "Other";
		}
		DatabaseHandler db = new DatabaseHandler();
		db.saveClient(name,gender,number,cnic,area,street,city,province,country);
		viewSetClientCredentials();
	}
	@FXML
	private void checkClientDetials() throws Exception
	{
		boolean nameCheck = true;
		String name = nameField.getText().toString();
		try
		{
			
			if(name.contentEquals(""))
				throw new EmptyFieldException();
		}
		catch(EmptyFieldException e1) 
		{
			nameCheck = false;
		}
		
		boolean addressCheck = true;
		String area = addressArea.getText().toString();
		String street = addressStreet.getText().toString();
		String city = addressCity.getText().toString();
		String province = addressProvince.getText().toString();
		String country = addressCountry.getText().toString(); 
		try
		{
			
			if(area.contentEquals("") || street.contentEquals("") || city.contentEquals("") || province.contentEquals("") || country.contentEquals(""))
				throw new EmptyFieldException();
		}
		catch(EmptyFieldException e1) 
		{
			addressCheck = false;
		}
		

		long number = 0;
		long cnic = 0;
	    boolean phoneCheck = true;
	    boolean cnicCheck = true;
	    
	    try 
	    {
	    	number = Long.valueOf(phoneNumber.getText().toString());
	    	if(phoneNumber.getText().toString().length() != 11)
	    		phoneCheck = false;
	    	
	    } 
	    catch (NumberFormatException e) 
	    {
	    	phoneCheck = false;
	    }
	    
	    try 
	    {
	    	cnic = Long.valueOf(cnicNumber.getText().toString());
	    	if(cnicNumber.getText().toString().length() != 13)
	    		cnicCheck = false;
	    } 
	    catch (NumberFormatException e) 
	    {
	    	cnicCheck = false;
	    }
	    
	    
	    if(!phoneCheck)
	    {
	    	popup1.setVisible(true);
	    }
	    else
	    {
	    	popup1.setVisible(false);
	    }
	    if(!cnicCheck)
	    {
	    	popup2.setVisible(true);
	    }
	    else
	    {
	    	popup2.setVisible(false);
	    }
	    if(!nameCheck)
	    {
	    	popup3.setVisible(true);
	    }
	    else
	    {
	    	popup3.setVisible(false);
	    }
	    if(!addressCheck)
	    {
	    	popup4.setVisible(true);
	    }
	    else
	    {
	    	popup4.setVisible(false);
	    }
	    
		String gender = "";
		if(button1.isSelected())
		{
			gender = "Male";
		}
		else if(button2.isSelected())
		{
			gender = "Female";
		}
		else if(button3.isSelected())
		{
			gender = "Other";
		}
		if(phoneCheck && cnicCheck && nameCheck && addressCheck)
		{
			DatabaseHandler db = new DatabaseHandler();
			db.saveClient(name,gender,number,cnic,area,street,city,province,country);
			viewSetClientCredentials();
		}

		
	    
	}
	@FXML
	private void checkCredentials() throws IOException
	{
		String email = emailField.getText().toString();
		String password = passwordField.getText().toString();
		DatabaseHandler db = new DatabaseHandler();
		boolean emailCheck = true;
		boolean passwordCheck = true;
		if(db.checkEmail(email) || email.contentEquals("") || email.length()<10)
		{
			emailCheck = false;
			popup1.setVisible(true);
			popup2.setVisible(true);
			
		}
		else
		{
			emailCheck = true;
			popup1.setVisible(false);
			popup2.setVisible(false);
		}
		if(password.contentEquals("") || password.length()<3)
		{
			popup3.setVisible(true);
			popup4.setVisible(true);
			passwordCheck = false;
		}
		else
		{
			popup3.setVisible(false);
			popup4.setVisible(false);
			passwordCheck = true;
		}
		if(emailCheck && passwordCheck)
		{
			System.out.println("Credentials Saved");
			setCredentials(email,password);
		}
		System.out.println("EMAIL: "+email);
	}
	private void setCredentials(String email,String password) throws IOException
	{
		DatabaseHandler db = new DatabaseHandler();
		db.saveClientCredentials(email,password);
		viewHomeScreen();
	}
	@FXML
	private void login() throws IOException
	{
		String email = emailField.getText().toString();
		String password = passwordField.getText().toString();
		DatabaseHandler db = new DatabaseHandler();
		boolean emailCheck;
		if(email.contentEquals(" "))
		{
			
		}
		if(db.checkEmail(email))
		{
			emailCheck = true;
			popup1.setVisible(false);
			popup2.setVisible(false);
			if(db.checkCredentials(email,password))
			{
				loggedEmail = email;
				viewClientLandingScreen();
			}
			else 
			{
				popup1.setVisible(true);
				popup2.setVisible(true);
				popup3.setVisible(true);
			}
		}
		else
		{
			emailCheck = false;
			popup1.setVisible(true);
			popup2.setVisible(true);
		}
		if(password.contentEquals(""))
		{
			
		}
	}
	@FXML
	private void listProperty() throws IOException

	{
		String area = addressArea.getText().toString();
		String street = addressStreet.getText().toString();
		String city = addressCity.getText().toString();
		String province = addressProvince.getText().toString();
		String country = addressCountry.getText().toString();
		Double price =  Double.parseDouble(priceField.getText().toString());
		int floors = Integer.parseInt(floorField.getText().toString());
		int rooms = Integer.parseInt(roomField.getText().toString());
		String type = "";
		String status = "";
		if(button1.isSelected())
		{
			type = "Plot";
		}
		else if(button2.isSelected())
		{
			type = "House";
		}
		else if(button3.isSelected())
		{
			type = "Office";
		}
		else if(button4.isSelected())
		{
			type = "Apartment";
		}
		if(button5.isSelected())
		{
			status = "For Sale";
		}
		else if(button6.isSelected())
		{
			status = "For Rent";
		}
		
		DatabaseHandler db = new DatabaseHandler();
		db.listProperty(loggedEmail,price,status,type,floors,rooms,area,street,city,province,country);
		FileHandler file = new FileHandler();
		file.listProperty(loggedEmail,price,status,type,floors,rooms,area,street,city,province,country);
		viewClientLandingScreen();
	}
	@FXML
	private void showOwnedProperties()
	{
		LinkedList<String> data = new LinkedList<String>();
		popup1.setVisible(true);
		DatabaseHandler db = new DatabaseHandler();
		data = db.getOwnedProperties(loggedEmail);
		properties.getItems().addAll(data);
	}
	@FXML
	private void showTypeAndStatus()
	{
		LinkedList<String> Ptype = new LinkedList<String>();
		LinkedList<String> Pstatus = new LinkedList<String>();
		Pstatus.add("For Sale");
		Pstatus.add("For Rent");
		Ptype.add("Plot");
		Ptype.add("Office");
		Ptype.add("House");
		Ptype.add("Apartment");
		type.getItems().addAll(Ptype);
		status.getItems().addAll(Pstatus);
	}
	@FXML
	private void showPropertyDescription()
	{
		addressArea.setText(area);
		addressStreet.setText(street);
		addressCity.setText(city);
		addressProvince.setText(province);
		addressCountry.setText(country);
		priceField.setText(price);
		floorField.setText(floors);
		roomField.setText(rooms);
		
		System.out.println(area);
		System.out.println(street);
		System.out.println(city);
		System.out.println(province);
		System.out.println(country);
		System.out.println(price);
		System.out.println(floors);
		System.out.println(rooms);
	}
	@FXML
	private void deleteProperty() throws IOException
	{
		String propertyDetails = properties.getValue();
		String pid = "";
		for(int i=0;propertyDetails.charAt(i)!='|';i++)
		{
			pid += propertyDetails.charAt(i);
		}
		DatabaseHandler db = new DatabaseHandler();
		db.deleteProperty(pid);
		viewPropertyDeletedScreen();
	}
	@FXML
	private void updatePropertyDetails() throws IOException
	{
		String t = type.getValue();
		String s = status.getValue();
		String area = addressArea.getText().toString();
		String street = addressStreet.getText().toString();
		String city = addressCity.getText().toString();
		String province = addressProvince.getText().toString();
		String country = addressCountry.getText().toString();
		String price = priceField.getText().toString();
		String floors = floorField.getText().toString();
		String room = roomField.getText().toString();
		DatabaseHandler db = new DatabaseHandler();
		db.updatePropertyDetails(id,t,s,area,street,city,province,country,price,floors,room);
		System.out.println("Area" + area);
		FileHandler file = new FileHandler();
		file.updatePropertyDetails(id,t,s,area,street,city,province,country,price,floors,room);
		System.out.println("Area" + area);
		viewManagePropertyScreen();
	}

	@FXML
	private void showPropertyDetails() throws IOException 
	{
		viewUpdatePropertyDetailScreen();
		String propertyDetails = properties.getValue();
		String pid = "";
		for(int i=0;propertyDetails.charAt(i)!='|';i++)
		{
			pid += propertyDetails.charAt(i);
		}
		DatabaseHandler db = new DatabaseHandler();
		String property = db.getProperty(pid);
		int count = 0;
		id = pid;
		

		
		for(int i=0;i<property.length();i++)
		{
			if(property.charAt(i) == '|')
			{
				count++;
				i++;
			}
			
			if(count == 0)
			{
				Ptype += property.charAt(i);
				continue;	
			}
			if(count == 1)
			{
				Pstatus += property.charAt(i);
				continue;
			}
			if(count == 2)
			{
				price += property.charAt(i);
				continue;
			}
			if(count == 3)
			{
				floors += property.charAt(i);
				continue;
			}
			if(count == 4)
			{
				rooms += property.charAt(i);
				continue;
			}
			if(count == 5)
			{
				area += property.charAt(i);
				continue;
			}
			if(count == 6)
			{
				street += property.charAt(i);
				continue;
			}
			if(count == 7)
			{
				city += property.charAt(i);
				continue;
			}
			if(count == 8)
			{
				province += property.charAt(i);
				continue;
			}
			if(count == 9)
			{
				country += property.charAt(i);
				continue;
			}
		}
		System.out.println(area);
		System.out.println(street);
		System.out.println(city);
		System.out.println(province);
		System.out.println(country);
		System.out.println(price);
		System.out.println(floors);
		System.out.println(rooms);
		

		

	}
	
}
