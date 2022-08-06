import java.time.LocalDate;

public class Seating {
	Movie m;
	LocalDate date;
	int time_slot;
	String seat[][];

	public Seating(Movie m, LocalDate date, int time_slot)
	{
		this.m=m;
		this.date=date;
		this.time_slot=time_slot;

		char col_start='A';
		char row_start='1';
		String seat[][] = new String[7][7];
		seat[0][0]=" ";
		for (int j=1;j<7;j++)
			seat[0][j]=String.valueOf(row_start++);
		for (int i=1;i<7;i++)
			seat[i][0]=String.valueOf(col_start++);
		for (int i=1;i<7;i++)
		{
			for (int j=1;j<7;j++)
				if(i==6)
					seat[i][j]="[P]";
				else
					seat[i][j]="[S]";
		}
		this.seat=seat;
	}

	public void printSeatMatrix()
	{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\t\t\t    SCREEN");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		for (int i=0;i<7;i++)
		{
			for (int j=0;j<7;j++)
			{
				if (i==0)
					System.out.print(" " + seat[i][j] + "\t");
				else
					System.out.print(seat[i][j] + "\t");
			}
			System.out.println("");
		}
	}

}
