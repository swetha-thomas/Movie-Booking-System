
public class User {

	private String name, username, password, phone_no;
	private int age;
	private String gender;

	public User(String name,String username,String password, int age, String gender, String phone_no)
	{
		this.name=name;
		this.username=username;
		this.password=password;
		this.age=age;
		this.phone_no=phone_no;
		this.gender=gender;
	}

	public String getName()
	{
		return this.name;
	}
	public String getUsername()
	{
		return this.username;
	}
	public String getPassword()
	{
		return this.password;
	}
	public int getAge()
	{
		return this.age;
	}
	public String getPhone_num()
	{
		return this.phone_no;
	}
	public String getGender()
	{
		return this.gender;
	}
	public String toString()
	{
		return "Name: " + this.getName() + "\nAge: " + this.getAge() + "\nGender: " + this.getGender() + "\nPhone number: " + this.getPhone_num();

	}
}
