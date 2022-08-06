import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MovieLibrary {

	Movie movie_lib[];

	public MovieLibrary()
	{
		movie_lib=new Movie[7];
	}

	public void buildLibrary(String filename){
		File myObj = new File(filename);
		Scanner myReader;
		try {
			int index=0;
			myReader = new Scanner(myObj);
			myReader.nextLine();
			while (myReader.hasNextLine()) {
				String title, genre,language,actors,release_date;
				int duration;
				String data[] = myReader.nextLine().split("\\|");
				title=data[0];
				genre=data[1];
				language=data[2];
				actors=data[3];
				release_date=data[4];
				duration=Integer.parseInt(data[5]);
				movie_lib[index] = new Movie(title,genre,language,actors,release_date,duration);
				index++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}