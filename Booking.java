import java.time.LocalDate;
import java.util.Arrays;

public class Booking  {
	//booking_ID
	private Movie m;
	private LocalDate d;
	private int slot;
	private int p_count;
	private int s_count;
	private String p_seats[];
	private String s_seats[];
	
	public Booking(Movie m, LocalDate d, int slot, int p_count, int s_count, String p_seats[], String s_seats[])
	{
		this.m=m;
		this.d=d;
		this.slot=slot;
		this.p_count=p_count;
		this.s_count=s_count;
		this.p_seats = p_seats;
		this.s_seats = s_seats;

	}
	
	public Movie getMovie()
	{
		return this.m;
	}
	
	public LocalDate getDate()
	{
		return this.d;
	}
	
	public int getSlot()
	{
		return this.slot;
	}
	
	public int get_s_count()
	{
		return this.s_count;
	}
	
	public int get_p_count()
	{
		return this.p_count;
	}
	
	public String[] get_p_seats()
	{
		Arrays.sort(this.p_seats);
		return this.p_seats;
	}
	
	public String[] get_s_seats()
	{
		Arrays.sort(this.s_seats);
		return this.s_seats;
	}
	
	public String toString()
	{ 
		String slots[]= {"Starts at 9am","Starts at 4pm","Starts at 10pm"};
		
		return "Movie Name: " + this.getMovie().getTitle() + "\nGenre: " + this.getMovie().getGenre() + "\nLanguage: " + this.getMovie().getLanguage() + 
				"\nDate: " + this.getDate().getDayOfMonth()+"-"+this.getDate().getMonthValue()+"-"+this.getDate().getYear() + "\nSlot: "+slots[this.getSlot()-1] + "\nStandard Seats: "+ Arrays.toString(this.get_s_seats()) + "\nPremium Seats: " + Arrays.toString(this.get_p_seats());
	}
}