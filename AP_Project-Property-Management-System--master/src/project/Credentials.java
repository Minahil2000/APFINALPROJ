package project;


public class Credentials 
{
	String userName;
	String password;
	
	public Credentials(String n,String p)
	{
		userName = n;
		password = p;
	}
	public String getName()
	{
		return userName;
	}
	public void setName(String n)
	{
		userName = n;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String p)
	{
		password = p;
	}
}
