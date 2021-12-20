package project;


public abstract class User 
{
	private String name;
	private String gender;
	private Address address;
	private int phoneNumber;
	private int cnic;
	private Credentials login;
	//Class Methods
	public User(String n,String g,Address a,int p,Credentials l)
	{
		name = n;
		gender = g;
		address = a;
		phoneNumber = p;
		login = l;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String n) 
	{
		name = n;
	}
	public String getGender() 
	{
		return gender;
	}
	public void setGender(String g) 
	{
		gender = g;
	}
	public Address getAddress()
	{
		return address;
	}
	public void setAddress(Address a)
	{
		address = a;
	}
	public int getPhoneNumber()
	{
		return phoneNumber;
	}
	public void setPhoneNumber(int p)
	{
		phoneNumber = p;
	}
	public int getCnic()
	{
		return cnic;
	}
	public void setCnic(int c)
	{
		cnic = c;
	}
	public Credentials getLogin()
	{
		return login;
	}
	public void setCredentials(Credentials c)
	{
		login = c;
	}

}
