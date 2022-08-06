
public class Movie {
	private String title;
	private String genre;
	private String language;
	private String actors;
	private String release_date;
	private int duration;
	
	public Movie (String title, String genre, String language, String actors, String release_date, int duration)
	{
		this.title = title;
		this.genre = genre;
		this.language = language;
		this.actors = actors;
		this.release_date = release_date;
		this.duration = duration;
	}
	
	public String getTitle(){
		return this.title;
	}
	public String getGenre(){
		return this.genre;
	}
	public String getLanguage(){
		return this.language;
	}
	public String getActors(){
		return this.actors;
	}
	public String getReleaseDate(){
		return this.release_date;
	}
	public int Duration(){
		return this.duration;
	}

	public String toString()
	{
		String s= "Title: " + this.title + "\n\tGenre: " + this.genre + "\n\tActors: " + this.actors + "\n\tRelease Date: " + this.release_date + "\n\tDuration: " + this.duration;
		return s;
	}
	
}


	