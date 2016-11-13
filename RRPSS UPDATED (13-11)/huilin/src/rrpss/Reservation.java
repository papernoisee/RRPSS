package rrpss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * This class helps the employee to create, check as well as reserve bookings for the customer in the restaurant. 
 * @author yangzhen, josephine
 *
 */
public class Reservation {
	private int rId;
	private String rCustName;
	private int rPhone;
	private int rPax;
	private int tableNum;	//become String
	private Calendar reservationPeriod;
	private boolean status;	//true = expired , false = not expired
	SimpleDateFormat SDFFULL = new SimpleDateFormat("dd-MM-yyyy HH:mm");


	/**
	 * getter method to get the date and time of the reservation 
	 * @return the reservation period
	 */
	public Calendar getCalendar(){	
		return reservationPeriod;
	}

	/**
	 * setter method to set the date and time of the reservation 
	 * @param reservationPeriod
	 */
	public void setCalendar(Calendar reservationPeriod){
		this.reservationPeriod=reservationPeriod;
	}

	/**
	 * getter method to get the status of the reservation 
	 * if true means reservation has expired; false means reservation has not expired
	 * @return status
	 */
	public boolean getStatus(){
		return status;
	}

	/**
	 * setter method to set the status of the reservation
	 * @param status
	 */
	public void setStatus(boolean status){
		this.status=status;
	}
	//

	/**
	 * getter method to retrieve the id of the reservation booking
	 * @return the reservation id of the booking 
	 */
	public int getrId(){
		return rId;
	}
	/**
	 * setter method to assign id of the reservation id to the variable 'rId'
	 * @param rId
	 */
	public void setrId(int rId){
		this.rId = rId;
	}

	/**
	 * getter method to retrieve the phone number of the reservation booking 
	 * @return the phone number which reserved the booking 
	 */
	public int getRPhone(){
		return rPhone;
	}

	/**
	 * setter method to assign phone number of customer who reserved a booking to the variable 'rPhone'
	 * @param rPhone
	 */
	public void setRPhone(int rPhone){
		this.rPhone = rPhone;
	}

	/**
	 * getter method to retrieve the reserved table number 
	 * @return the table number which is reserved 
	 */
	public int getTableNum(){
		return tableNum;
	}

	/**
	 * setter method to table number of the reservation booking to the variable 'tableNum'
	 * @param tableNum this 
	 */
	public void setTableNum(int tableNum){
		this.tableNum = tableNum;
	}

	/** 
	 * getter method to retrieve the pax( number of customers) for each reservation booking
	 * @return the number of customers arriving to restaurant for each reservation
	 */
	public int getRPax(){
		return rPax;
	}

	/**
	 * setter method to assign the number of customers per reservation booking to the variable 'rPax'
	 * @param rPax the number of people arriving to restaurant for each reservation
	 */
	public void setRPax(int rPax){
		this.rPax = rPax;
	}


	/**
	 * getter method to retrieve the name of the customer who reserved the booking in the restaurant 
	 * @return the customer name who has reserved the booking 
	 */
	public String getRCustName(){
		return rCustName;
	}

	/**
	 * setter method to assign customer name who has reserved a booking to the variable 'rCustName'
	 * @param rCustName
	 */
	public void setRCustName(String rCustName){
		this.rCustName = rCustName;
	}


	/**
	 * a method to check if the date is valid
	 * invalid: when the current date has passed the reservation date 
	 * invalid: make reservation beyond 30 days
	 * valid: within 30 days 
	 * @param check_in_date
	 * @return result of whether the reservation date is valid
	 */
	public static boolean isValidCheckInDate(Calendar check_in_date){
		boolean result = true;
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (check_in_date.before(c))
		{
			result = false;
			System.out.println("Invalid date!");
		}
		return result;
	}


	/**
	 * a method to check whether a reservation has expired (given an allowance time of 30 min)
	 * 
	 * @return a boolean to decide if reservation has expired
	 * if true, it means that the reservation time has passed 30 min; expired
	 * if false, it means reservation has not expired
	 * 
	 */
	public boolean isExpired(){
		Calendar add30Minutes= (Calendar)reservationPeriod.clone();
		add30Minutes.add(Calendar.MINUTE, 30);
		if (add30Minutes.before(Calendar.getInstance())) //reservation time of 30minutes is up
			return true;

		return false;
	}

	/**
	 * a method to change the status of the reservation to expired after checking it through
	 */
	public void updateExpireStatus(){
		Calendar current_time=Calendar.getInstance();
		if(isExpired()){
			status = true;
		}
	}

	/**
	 * a method to print the details of reservation
	 * which includes customer name, phone number, pax, date, table num and status
	 */
	public void printReservation(){
		Calendar reservationPeriod = Calendar.getInstance();
		Date date =  reservationPeriod.getTime();

		System.out.println("\n******** Details of reservation ********");
		System.out.println("Name: " + rCustName);
		System.out.println("Phone Number: " + rPhone);
		System.out.println("Number of person: " + rPax);
		System.out.println("Date: " + SDFFULL.format(date));		
		System.out.println("Table Number: " + tableNum);

		if (status)
			System.out.println("Status: expired");
		else
			System.out.println("Yet to come");

	}


	//checks if reservation is available for the stated time

	//if user input calendar is before current date , return false
	//if user input calendar is after 30 days of current date, return false

	//if user input calendar == reservation file calendar same day month, need check am slot or pm slot available
	//clone reservation file's calendar and set same day and month on a new calendar object, set static time 11:00.
	//clone reservation file's calendar and set same day and month on a new calendar object, set static time 15:00.
	//if user input calendar fall under am(after 11:00 , before 15:00) && reservation file's calendar fall under am, return false
	//if user input calendar fall under pm && reservation file's calendar full under pm, return false

	//else return true (no conflicted dates or session)

	
	/**
	 * a method to only allow reservation booking to happen 
	 * between 11am - 3pm ; 6pm - 10pm
	 * as this is the restaurant's opening hours
	 * 
	 * @param reservationDateTime
	 * 
	 * @return a boolean to confirm whether reservation is available
	 */
	Boolean isAvailableForReservation(Calendar reservationDateTime){

		//Calendar input = Calendar.getInstance(); // need get this calendar input from table's arraylist reservation (instantiate from reservation object)
		Calendar input = reservationPeriod;
		//System.out.println("entered isavailableforreservation function in reservation class");

		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

		Calendar add30Days= Calendar.getInstance();
		add30Days.set(Calendar.HOUR_OF_DAY, reservationDateTime.get(Calendar.HOUR_OF_DAY));
		add30Days.set(Calendar.MINUTE, reservationDateTime.get(Calendar.MINUTE));
		add30Days.add(Calendar.DATE, 30);	//30 days later from current day

		boolean isSameDay = input.get(Calendar.YEAR) == reservationDateTime.get(Calendar.YEAR) &&
				input.get(Calendar.DAY_OF_YEAR) == reservationDateTime.get(Calendar.DAY_OF_YEAR);

		if (reservationDateTime.before(Calendar.getInstance())){
			System.out.println("Cannot enter a reservation in the past.");
			return false;
		}
		if (reservationDateTime.after(add30Days)){
			System.out.println("Cannot make a reservation more than 30 days in advance");
			return false;
		}

		Calendar reservationDate11AM = (Calendar)reservationDateTime.clone();
		Calendar reservationDate3PM= (Calendar)reservationDateTime.clone();
		Calendar reservationDate6PM= (Calendar)reservationDateTime.clone();
		Calendar reservationDate10PM= (Calendar)reservationDateTime.clone();

		//System.out.println("user input reservation hour : " + reservationDateTime.get(Calendar.HOUR_OF_DAY));

		reservationDate11AM.set(Calendar.HOUR_OF_DAY, 10);
		reservationDate11AM.set(Calendar.MINUTE, 59);
		//System.out.println("11am hour of day : " + reservationDate11AM.get(Calendar.HOUR_OF_DAY));

		reservationDate3PM.set(Calendar.HOUR_OF_DAY, 15);
		reservationDate3PM.set(Calendar.MINUTE, 01);
		//System.out.println("3pm hour of day : " + reservationDate3PM.get(Calendar.HOUR_OF_DAY));

		reservationDate6PM.set(Calendar.HOUR_OF_DAY, 17);
		reservationDate6PM.set(Calendar.MINUTE, 59);

		reservationDate10PM.set(Calendar.HOUR_OF_DAY, 22);
		reservationDate10PM.set(Calendar.MINUTE, 01);

		if(!( (reservationDateTime.after(reservationDate11AM) && reservationDateTime.before(reservationDate3PM)) ||
				(reservationDateTime.after(reservationDate6PM) && reservationDateTime.before(reservationDate10PM)) )){
			System.out.println("out of restaurant's operating hours");
			return false; //if time is not within restaurant opening hours.
		}

		if(isSameDay){	//if is on same day
			if ( (reservationDateTime.after(reservationDate11AM) && reservationDateTime.before(reservationDate3PM)) 
					&& (input.after(reservationDate11AM) && input.before(reservationDate3PM)) ){
				//System.out.println("Morning Session is already reserved.");
				return false; // belong to same AM session				
			}

			else if ( (reservationDateTime.after(reservationDate6PM) && reservationDateTime.before(reservationDate10PM)) 
					&& (input.after(reservationDate6PM) && input.before(reservationDate10PM)) ){
				//System.out.println("Afternoon Session is already reserved.");
				return false; // belong to same PM session
			}
		}

		System.out.println("Successfully reserved");
		return true;
	}


	/**
	 * a method to check if the time the customer chose to reserve is valid
	 * the time cannot be later than the current time in the real world
	 * the time cannot be further than 30 days ; cannot make a reservation more than 30 days in advance
	 * 
	 * @param dateInput
	 * @return a boolean to decide if the reservation time is valid
	 */
	public static Boolean DateValidator(Calendar dateInput){

		//Calendar input = Calendar.getInstance(); // need get this calendar input from table's arraylist reservation (instantiate from reservation object)
		Calendar add30Days= Calendar.getInstance();
		add30Days.set(Calendar.HOUR_OF_DAY, dateInput.get(Calendar.HOUR_OF_DAY));
		add30Days.set(Calendar.MINUTE, dateInput.get(Calendar.MINUTE));
		add30Days.add(Calendar.DATE, 30);	//30 days later from current day

		if (dateInput.before(Calendar.getInstance())){
			System.out.println("You Cannot enter a reservation in the past.");
			return false;
		}
		if (dateInput.after(add30Days)){
			System.out.println("You Cannot make a reservation more than 30 days in advance");
			return false;
		}

		Calendar reservationDate11AM = (Calendar)dateInput.clone();
		Calendar reservationDate3PM= (Calendar)dateInput.clone();
		Calendar reservationDate6PM= (Calendar)dateInput.clone();
		Calendar reservationDate10PM= (Calendar)dateInput.clone();

		reservationDate11AM.set(Calendar.HOUR_OF_DAY, 10);
		reservationDate11AM.set(Calendar.MINUTE, 59);

		reservationDate3PM.set(Calendar.HOUR_OF_DAY, 15);
		reservationDate3PM.set(Calendar.MINUTE, 01);

		reservationDate6PM.set(Calendar.HOUR_OF_DAY, 17);
		reservationDate6PM.set(Calendar.MINUTE, 59);

		reservationDate10PM.set(Calendar.HOUR_OF_DAY, 22);
		reservationDate10PM.set(Calendar.MINUTE, 01);

		if(!( (dateInput.after(reservationDate11AM) && dateInput.before(reservationDate3PM)) ||
				(dateInput.after(reservationDate6PM) && dateInput.before(reservationDate10PM)) )){
			System.out.println("your input is out of restaurant's operating hours");
			return false; //if time is not within restaurant opening hours.
		}

		//System.out.println("Available reservation slot!");
		return true;
	}
}
