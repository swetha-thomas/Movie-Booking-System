import java.time.LocalDate;


public class SeatingLibrary {

	Seating seat_lib[];
	
	public SeatingLibrary()
	{
		seat_lib=new Seating[147];
	}
	
	public void buildLibrary(){
		
		int index=0;
		LocalDate startDate= LocalDate.now();
		LocalDate endDate= startDate.plusDays(7);
		 
		MovieLibrary movie_library = new MovieLibrary();
		movie_library.buildLibrary("movies.txt");
		for (int mov=0;mov<7;mov++)
		{
			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
			{
			   for (int slot = 1; slot<=3; slot++)
				   seat_lib[index++] = new Seating(movie_library.movie_lib[mov],date,slot);
			}
		}
	}
}
