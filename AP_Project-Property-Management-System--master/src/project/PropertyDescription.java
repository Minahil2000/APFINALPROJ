package project;


public class PropertyDescription
{
	private Client owner;
	private String type;
	private Address address;
	private Double price;
	private String status;
	private int floors;
	private int rooms;
	
	public PropertyDescription(Client o,String t,Address a,Double p,String s,int f, int r)
	{
		owner = o;
		type = t;
		address = a;
		price = p;
		status = s;
		floors = f;
		rooms = r;
	}
	public Client getOwner()
	{
		return owner;
	}
	public void setOwner(Client c)
	{
		owner = c;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String t)
	{
		type = t;
	}
	public Address getAddress()
	{
		return address;
	}
	public void setAddress(Address a)
	{
		address = a; 
	}
	public Double getPrice()
	{
		return price;
	}
	public void setPrice(Double p)
	{
		price = p;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String s)
	{
		status = s;
	}
	public int getFloors()
	{
		return floors;
	}
	public void setFloors(int f)
	{
		floors = f;
	}
	public int getRooms()
	{
		return rooms;
	}
	public void setRooms(int r)
	{
		rooms = r;
	}
}

