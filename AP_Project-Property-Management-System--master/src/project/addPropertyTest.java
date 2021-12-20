package project;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.jupiter.api.Test;




class addPropertyTest 
{
	
	Address addr;
	Credentials login;
	Client owner;
	PropertyDescription desc;
	Property property;
	
	
	@Before
	public void setup()
	{
		
		addr = new Address("10-C Peoples Colony No.1","Khawaja Islam Road","Faisalabad","Punjab","Pakistan");
		login = new Credentials("ibrahim@nu.edu.pk","123");
		owner = new Client("Ibrahim","Male",addr,0303777123,login);
		desc = new PropertyDescription(owner,"House",addr,15000000.0,"For Sale",1,3);
		property = new Property(desc);
	}
	@Test
	public void addProperty() 
	{
		System.out.println("----- ADD PROPERTY TEST -----");
		DatabaseHandler db = new DatabaseHandler();
		db.listProperty(owner.getLogin().getName(),desc.getPrice(),desc.getStatus(),desc.getType(),desc.getFloors(),desc.getRooms(),addr.getArea(),addr.getStreet(),addr.getCity(),addr.getProvince(),addr.getCountry());
		LinkedList<String> data = db.getOwnedProperties(owner.getLogin().getName());
		System.out.println(data.get(0));
		if(data.size() == 0)
		{
			
			System.out.println(data.get(0));
		}
		
			
		
		
	}
	
}
