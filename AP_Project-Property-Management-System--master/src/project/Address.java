package project;


public class Address 
{
	private String area;
	private String street;
	private String city;
	private String province;
	private String country;
	
	//Class Methods
	public Address(String a,String s,String ct,String p,String c)
	{
		area = a;
		street = s;
		city = ct;
		province = a;
		country = c;
	}
	public String getArea()
	{
		return area;
	}
	public void setArea(String a)
	{
		area = a;
	}
	public String getStreet()
	{
		return street;
	}
	public void setStreet(String s)
	{
		street = s;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String c)
	{
		city = c;
	}
	public String getProvince()
	{
		return province;
	}
	public void setProvince(String p)
	{
		province = p;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String c)
	{
		country = c;
	}
	
}

