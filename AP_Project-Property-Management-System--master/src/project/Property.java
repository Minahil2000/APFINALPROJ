package project;


public class Property 
{
	private static int count = 1000;
	private int propertyID;
	private PropertyDescription description;
	public Property(PropertyDescription desc)
	{
		propertyID = count;
		description = desc;
		count++;
	}
}

