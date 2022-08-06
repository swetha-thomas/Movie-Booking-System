import java.time.LocalDate;
import java.util.Scanner;
import java.util.*;

public class Movie_Booking_System{

	public static User [] u= new User[100]; 
	public static int register_index=0; 
	public static ArrayList<ArrayList<Booking>> booking_obj=new ArrayList<>();
	public static int user_index;

	public static void main(String args[])
	{	
		SeatingLibrary seating_lib = new SeatingLibrary();
		seating_lib.buildLibrary();
		int opt;
		
		do
		{
			Scanner in=new Scanner(System.in);
			System.out.println("\n----------------------------------------------------------------");
			System.out.println("\t\t      WELCOME TO ABC CINEMAS");
			System.out.println("----------------------------------------------------------------");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("\nEnter your choice(1/2/3): ");
			opt=in.nextInt();
			switch(opt)
			{
			case 1:
			{
				register();
				register_index++;
				break;
			}
			case 2:
			{
				login();
				booking(seating_lib);
				break;
			}
			case 3:
			{
				System.exit(0);
				break;
			}
			default:
			{
				System.out.println("\nInvalid option!");
			}
			}
		}while(true);
	}

	public static void register()
	{
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("\t\t\t   REGISTRATION");
		System.out.println("----------------------------------------------------------------");
		Scanner in=new Scanner(System.in);
		String name, username, password,phone_no;
		int age=0;
		String gender;
		boolean err = false;
		
		System.out.print("\nEnter your name: ");
		do {
			name=in.nextLine();
			if (name==null | !name.matches("^[a-zA-Z ]*$") | name.equals("")) 
				System.out.print("Invalid Entry!! Please re-enter your name: ");
		}while(name==null | !name.matches("^[a-zA-Z ]*$") | name.equals(""));
		
		System.out.print("Enter your username: ");
		do {
			err = false;
			username=in.nextLine();
			if (register_index!=0) 
			{
				for (int i=0;i<register_index;i++) {
					if (username.equals(u[i].getUsername())){
						err = true;
						break;
					}
				}
			}
			if (err) 
				System.out.print("Username already Exists!! Please re-enter username: ");
		} while (err == true);
		
		System.out.print("Enter your password: ");
		password=in.nextLine();
		
		System.out.print("Enter your age: ");
		do {
			err = false;
			try{
				age=in.nextInt();
			} 
			catch (Exception e) {
				System.out.print("Invalid Entry!! Please re-enter your age: ");
				err = true;
				in.next();
			}
		} while(err);
		
		System.out.print("Enter your gender: ");
		do {
			gender=in.nextLine();
			if (!gender.matches("^[a-zA-Z]*$")) {
				System.out.print("Invalid Entry!!Please re-enter your gender: ");
			}
		}while(!gender.matches("^[a-zA-Z]*$") | gender.equals(""));
		
		System.out.print("Enter your phone number[+91]: ");
		do {
			phone_no=in.nextLine();
			if (phone_no.length()!=10 | !phone_no.matches("[0-9]+")) {
				System.out.print("Invalid Entry!!Please re-enter your phone number: ");
			}
		}while(phone_no.length()!=10 | !phone_no.matches("[0-9]+"));
		
		u[register_index]= new User(name, username, password,age, gender, phone_no);
		booking_obj.add(new ArrayList());
	}

	public static void login()
	{
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("\t\t\t    LOGIN");
		System.out.println("----------------------------------------------------------------");
		Scanner in=new Scanner(System.in);
		String username, password;
		boolean chk_valid=false;
		
		do
		{
			System.out.print("\nEnter your username: ");
			username=in.nextLine();
			System.out.print("Enter your password: ");
			password=in.nextLine();
			
			for (int i=0;i<register_index;i++)
			{
				if ((u[i].getUsername().equals(username)) && (u[i].getPassword().equals(password)))
				{
					user_index=i;
					chk_valid=true;
					break;
				}
			}			
			if (chk_valid==false)
				System.out.println("\nInvalid username/password! Try Again!");
		}while(chk_valid==false);

	}

	public static void booking(SeatingLibrary seating_lib)
	{
		int opt2;
		do 
		{
			System.out.println("\n----------------------------------------------------------------");
			System.out.println("\t\t   WELCOME " + u[user_index].getName().toUpperCase());
			System.out.println("----------------------------------------------------------------");
			MovieLibrary movie_library = new MovieLibrary();
			movie_library.buildLibrary("movies.txt");
			Scanner in=new Scanner(System.in);
			System.out.println("\n1. Book a ticket");
			System.out.println("2. Delete an existing booking");
			System.out.println("3. Print your existing ticket");
			System.out.println("4. Logout");
			System.out.print("\nEnter your choice(1/2/3/4): ");
			opt2=in.nextInt();

			switch(opt2)
			{
			case 1:
			{
				book_seat(movie_library,seating_lib);
				break;
			}
			case 2:
			{
				delete_booking(seating_lib);
				break;
			}
			case 3:
			{
				print_booking(seating_lib);
				break;
			}
			case 4:
			{
				break;
			}
			default:
			{
				System.out.println("\nInvalid entry!");
			}
			}
		} while (opt2 != 4);
	}

	public static void book_seat(MovieLibrary movie_library,SeatingLibrary seating_lib)
	{
		int seat_mat_id;
		int av_count=0;
		Scanner in=new Scanner(System.in);
		LocalDate date_ch;
		int slot_ch_temp;
		Movie movie_ch;
		
		do 
		{
			System.out.println("----------------------------------------------------------------");
			System.out.println("\t\t\tNOW SHOWING!!");
			System.out.println("----------------------------------------------------------------");
			int movie_ch_temp;
			for (int i=0;i<7;i++)
				System.out.print(i+1 + ". " + movie_library.movie_lib[i].toString() + "\n");
			
			do {
				System.out.print("\nSelect movie: ");
				movie_ch_temp=in.nextInt();
				if (movie_ch_temp<1 | movie_ch_temp>7) {
					System.out.println("\nInvalid Entry! Please select a valid option.");
				}
			} while (movie_ch_temp<1 | movie_ch_temp>7);
			movie_ch=movie_library.movie_lib[movie_ch_temp-1];

			int date_val=1;
			int date_ch_temp;
			System.out.println("\nDates Available:\n");
			LocalDate startDate= LocalDate.now();
			LocalDate endDate= startDate.plusDays(7);
			LocalDate dates[]= new LocalDate[7];
			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
			{   
				dates[date_val-1]=date;
				System.out.print(date_val++ + ". " + date.getDayOfMonth()+"/"+date.getMonthValue()+"/" + date.getYear()+ "\n");
			}
			do {
				System.out.print("\nSelect date: ");
				date_ch_temp = in.nextInt();
				if (date_ch_temp<1 | date_ch_temp>7) {
					System.out.println("\nInvalid Entry! Please enter one of the mentioned Dates");
				}
			} while (date_ch_temp<1 | date_ch_temp>7);
			date_ch=dates[date_ch_temp-1];
			
			System.out.println("\nTime slots Available:\n");
			String slots[] = {"1.Starts at 9 am","2.Starts at 4 pm","3.Starts at 10 pm"};
			for(int i=0;i<3;i++)
				System.out.println(slots[i]);
			do {
				System.out.print("\nSelect your slot: ");
				slot_ch_temp = in.nextInt();
				if (slot_ch_temp<1 | slot_ch_temp>3) {
					System.out.println("\nInvalid Slot! Please choose a valid Slot.");
				}
			} while (slot_ch_temp<1 | slot_ch_temp>3);

			System.out.println("\n----------------------------------------------------------------");
			System.out.println("\t\t\t  SEATS AVAILABLE");
			System.out.println("\t\tS = Standard (Rs.150) , P = Premium (Rs.250)");
			System.out.println("----------------------------------------------------------------");
			for (seat_mat_id=0;seat_mat_id<147;seat_mat_id++)
			{
				boolean a=seating_lib.seat_lib[seat_mat_id].m.getTitle().equals(movie_ch.getTitle());
				boolean b=seating_lib.seat_lib[seat_mat_id].date.equals(date_ch);
				boolean c=seating_lib.seat_lib[seat_mat_id].time_slot==slot_ch_temp;
				if(a && b && c)
				{
					for (int i=1;i<7;i++) {
						for (int j=1;j<7;j++) {
							if (seating_lib.seat_lib[seat_mat_id].seat[i][j] != "[X]") 
								av_count++;
						}
					}
					if (av_count==0) 
					{
						System.out.println("\n----------------------------------------------------------------");
						System.out.println("\t\tThis theatre is full! Please re-enter!");
						System.out.println("----------------------------------------------------------------\n");
						break;
					}
					else 
					{
						seating_lib.seat_lib[seat_mat_id].printSeatMatrix();
						break;
					}
				}
			}
		} while(av_count==0);
		
		int no_seats;
		int p_count=0;
		int s_count=0;
		av_count=0;
		do {
			System.out.print("\nEnter number of seats: ");
			no_seats=in.nextInt();
			for (int i=1;i<7;i++) {
				for (int j=1;j<7;j++) {
					if (seating_lib.seat_lib[seat_mat_id].seat[i][j] != "[X]") {
						av_count++;
					}
				}
			}
			if (no_seats>av_count | no_seats>36) {
				System.out.println("\nBooking exceeds number of seats available! "+ av_count+" seats are available in this theatre!");
				av_count=0;
			}
			if (no_seats<=0) {
				System.out.println("\nInvalid Entry!");
				av_count=0;
			}
		} while (no_seats>av_count | no_seats>36 | no_seats<=0);
		
		String seat_names[]=new String[no_seats];
		String p_seats_temp[]=new String[no_seats];
		String s_seats_temp[]=new String[no_seats];
		System.out.println("Enter seat names:");
		for (int i=0;i<no_seats;)
		{
			do {
				seat_names[i]=in.next();
				seat_names[i] = seat_names[i].toUpperCase();
				if (seat_names[i].charAt(0)<'A' | seat_names[i].charAt(0)>'F' | seat_names[i].charAt(1)<'1' | seat_names[i].charAt(1)>'6') {
					System.out.println("\nInvalid Seat Name! Please enter a valid seat name: ");
				}
			} while (seat_names[i].charAt(0)<'A' | seat_names[i].charAt(0)>'F' | seat_names[i].charAt(1)<'1' | seat_names[i].charAt(1)>'6');

			int row=(int)(seat_names[i].charAt(0))-64;
			int col=Character.getNumericValue(seat_names[i].charAt(1));
			if(seating_lib.seat_lib[seat_mat_id].seat[row][col].equals("[X]"))
			{
				System.out.println("\n"+ seat_names[i] + " is already booked! Please choose another seat:");
				continue;
			}
			else
			{
				if (seat_names[i].charAt(0)=='F')
				{
					p_seats_temp[p_count]=seat_names[i];
					p_count++;

				}
				else
				{
					s_seats_temp[s_count]=seat_names[i];
					s_count++;
				}
				i++;
				seating_lib.seat_lib[seat_mat_id].seat[row][col]="[X]";
			}
		}
		
		String p_seats[]=new String[p_count];
		String s_seats[]=new String[s_count];
		for (int i=0;i<p_count;i++)
			p_seats[i]=p_seats_temp[i];
		for (int i=0;i<s_count;i++)
			s_seats[i]=s_seats_temp[i];

		Booking temp = new Booking(movie_ch,date_ch,slot_ch_temp,p_count,s_count,p_seats,s_seats);
		booking_obj.get(user_index).add(temp);

		payment(p_count,s_count);
	}

	public static void payment(int p_count,int s_count)
	{
		Scanner in=new Scanner(System.in);
		final int p_rate=250;
		final int s_rate=150;
		int p_price=p_rate*p_count;
		int s_price=s_rate*s_count;
		
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("\t\t\t    BILL");
		System.out.println("----------------------------------------------------------------");
		System.out.println("Premium seats: " + p_count + " X 250 = " + p_price );
		System.out.println("Standard seats: " + s_count + " X 150 = " + s_price );
		System.out.println("Total price: " + (int)(s_price+p_price));
		System.out.println("\n------------------------------X---------------------------------");

		boolean chk=true;
		int pay_opt;
		do 
		{
			chk=true;
			System.out.println("\nDo you wish to:");
			System.out.println("1. Pay Online");	
			System.out.println("2. Pay at counter");
			System.out.print("Enter your choice (1/2): ");
			pay_opt=in.nextInt();
			switch(pay_opt)
			{

			case 1:
			{
				String pin;
				String credit_card;
				
				do
				{
					System.out.print("\nEnter your credit card number: ");
					credit_card=in.next();
					if(!credit_card.matches("[0-9]+"))
						System.out.println("\nInvalid credit card number!");
				}while(!credit_card.matches("[0-9]+"));
				
				do
				{
					System.out.print("\nEnter the 4 digit PIN: ");
					pin = in.next();
					if (!pin.matches("[0-9]+") || pin.length()!=4)
						System.out.println("\nInavlid PIN!");
				}while(!pin.matches("[0-9]+") || pin.length()!=4);
				System.out.println("\nPayment Succesful!");
				print_ticket(booking_obj.get(user_index).size()-1);
				System.out.println("\nThank you for choosing ABC cinemas!");
				break;
			}
			
			case 2:
			{
				print_ticket(booking_obj.get(user_index).size()-1);
				System.out.println("\nThank you for choosing ABC cinemas, You may pay at counter. ");
				break;
			}
			
			default:
			{
				System.out.println("\nInvalid input!");
				chk=false;
			}
			}
		}while(chk==false);
	}

	public static void print_ticket(int index)
	{
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("\t\t\t  TICKET");
		System.out.println("----------------------------------------------------------------");

		System.out.println(u[user_index]);
		System.out.println(booking_obj.get(user_index).get(index));
		System.out.println("Total price: "+(booking_obj.get(user_index).get(index).get_s_seats().length*150+booking_obj.get(user_index).get(index).get_p_seats().length*250));
		System.out.println("\n------------------------------X---------------------------------");
	}

	public static void delete_booking(SeatingLibrary seating_lib)
	{
		if (booking_obj.get(user_index).isEmpty()) {
			System.out.println("\n\nNo Previous Bookings!!");
		}
		else {
			Scanner in=new Scanner(System.in);
			String slots[] = {"Starts at 9 am","Starts at 4 pm","Starts at 10 pm"};
			System.out.println("\nS.No \t\t MOVIE \t\t\t DATE \t\t\t\t TIME\n");
			int i;
			for (i =0;i< booking_obj.get(user_index).size();i++)
				System.out.println(i+1 + " \t\t " + booking_obj.get(user_index).get(i).getMovie().getTitle() + " \t\t " + booking_obj.get(user_index).get(i).getDate() + " \t\t " + slots[booking_obj.get(user_index).get(i).getSlot()-1]);
			System.out.print("\nEnter the booking number that you wish to delete: ");
			int s_no = i;
			int del_ch;
			do {
				del_ch=in.nextInt();
				if (del_ch<1 | del_ch>s_no) {
					System.out.print("\nInvalid Entry!!Please re-enter: ");
				}
			}while(del_ch<1 | del_ch>s_no);
			int seat_mat_id;
			String temp_seat[][]= {};
			for (seat_mat_id=0;seat_mat_id<147;seat_mat_id++)
			{
				boolean a=seating_lib.seat_lib[seat_mat_id].m.getTitle().equals(booking_obj.get(user_index).get(del_ch-1).getMovie().getTitle());
				boolean b=seating_lib.seat_lib[seat_mat_id].date.equals(booking_obj.get(user_index).get(del_ch-1).getDate());
				boolean c=seating_lib.seat_lib[seat_mat_id].time_slot==booking_obj.get(user_index).get(del_ch-1).getSlot();
				if(a && b && c)
				{
					temp_seat=seating_lib.seat_lib[seat_mat_id].seat;
					break;
				}
			}
			String s_seats_temp[] = booking_obj.get(user_index).get(del_ch-1).get_s_seats();
			for (i=0;i<booking_obj.get(user_index).get(del_ch-1).get_s_count();i++)
			{
				int row = (int)(s_seats_temp[i].charAt(0))-64;
				int col= s_seats_temp[i].charAt(1)-48;
				temp_seat[row][col] = "[S]"; 
			}
			String p_seats_temp[] = booking_obj.get(user_index).get(del_ch-1).get_p_seats();
			for (i=0;i<booking_obj.get(user_index).get(del_ch-1).get_p_count();i++)
			{
				int row = (int)(p_seats_temp[i].charAt(0))-64;
				int col= p_seats_temp[i].charAt(1)-48;
				temp_seat[row][col] = "[P]"; 
			}
			booking_obj.get(user_index).remove(del_ch-1);
			System.out.println("\nBooking Successfully deleted!\n\n");
		}
	}

	public static void print_booking(SeatingLibrary seating_lib)
	{
		if (booking_obj.get(user_index).isEmpty()) {
			System.out.println("\n\nNo Previous Bookings!!");
		}
		else if (booking_obj.get(user_index).size()==1) {
			print_ticket(0);
		}
		else {
			Scanner in=new Scanner(System.in);
			String slots[] = {"Starts at 9 am","Starts at 4 pm","Starts at 10 pm"};
			System.out.println("\nS.No \t\t MOVIE \t\t\t DATE \t\t\t\t TIME\n");
			int i;
			for (i =0;i< booking_obj.get(user_index).size();i++) {
				System.out.println(i+1 + " \t\t " + booking_obj.get(user_index).get(i).getMovie().getTitle() + " \t\t " + booking_obj.get(user_index).get(i).getDate() + " \t\t " + slots[booking_obj.get(user_index).get(i).getSlot()-1]);
			}
			int s_no = i;
			System.out.print("\nEnter the booking number that you wish to print the ticket for: ");
			int print_ch;
			do {
				print_ch=in.nextInt();
				if (print_ch<1 | print_ch>s_no) {
					System.out.print("\nInvalid Entry!!Please re-enter: ");
				}
			}while(print_ch<1 | print_ch>s_no);
			print_ticket(print_ch-1);
		}
	}

}