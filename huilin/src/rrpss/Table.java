package rrpss;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


/**
 * This class is to set table number, table status, table 
 * capacity
 * @author - eimoh
 *
 */
public class Table {
	private int tableNum;
	private String tableStatus;
	private int tableCap;
	
    private ArrayList<Reservation> reservation = new ArrayList<Reservation>();
    
	/**
	 * This add all the infos of reservation into 
	 * the reservation object 
	 */
    
    public void addReservation(Reservation reservation) {
        this.reservation.add(reservation);
    }
    
    /**
     * This returns array list of reservation
     * @return reservation
     */
    public  ArrayList<Reservation> getReservation() {
        return reservation;
    }
    
    /**
     * This returns a reservation that is created successfully 
     * @param Rid id of reservation
     * @param rPax number of person for reservation
     * @param reservationPeriod date and time details
     */
    
    public void createReservation(int Rid, int rPax , Calendar reservationPeriod) {
        Scanner sc1 = new Scanner(System.in);
        
        System.out.println("Please enter contact number");
        int contactNum;
        //input1=sc1.nextLine();     
        //int contactNum=Integer.parseInt(input1);
        
        String flagint = "false";
        while(flagint.equals("false")){
			//System.out.println("while loop");
			while(!sc1.hasNextInt() ){

				System.out.println("That's not a number!");
				System.out.println("Please enter contact number");
				sc1.next(); // this is important!
				flagint = "true";
			}	
			flagint = "true";
		}
        contactNum = sc1.nextInt();
        
        
        
        
        System.out.println("Please enter your name");
        String name = sc1.nextLine();

        Reservation reservation = new Reservation();
        reservation.setRPhone(contactNum);
        reservation.setRCustName(name);
        reservation.setStatus(false);	//set reservation status not expired
        reservation.setRPax(rPax);
        reservation.setCalendar(reservationPeriod);
        reservation.setrId(Rid);
        
        //come back check again.
        //if (tableStatus.equals("vacated"))
        //	tableStatus = "reserved";
        
        this.reservation.add(reservation);
    }
		
	/**
	 * 	This is a getter method to get the table number
	 * when it is needed to be updated or use it to
	 * solve commands
	 * @return table number
	 */
    
	public int getTableNum() {
		return tableNum;
	}
	
	/**
	 * This is a setter method to assign table
	 * when table number is inserted
	 * @param tableNum the table number
	 */

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	
	/**
	 * This is a getter method to get the table status
	 * when it is needed to be updated or use it to
	 * solve commands
	 * @return table status 
	 * "expired", "vacated", "occupied" or "reserved"
	 */
	

	public String getTableStatus() {
		return tableStatus;
	}
	
	/**
	 * This is a setter method to show table status
	 * when it is asked. Possible statuses would be
	 * "expired", "vacated", "occupied" or "reserved"
	 * @param tableStatus is status of table
	 */

	public void setTableStatus(String tableStatus) {
		
		this.tableStatus = tableStatus;
	}
	
	/**
	 * This is a getter method to get the table capacity
	 * when it is needed to be updated or use it to
	 * solve commands
	 * @return number of people that is sitting or going
	 * to sit at the table
	 */

	public int getTableCap() {
		return tableCap;
	}
	
	/**
	 * This is a setter method to set the table capacity
	 * when it is asked.
	 * @param tableCap number of people at the table
	 */

	public void setTableCap(int tableCap) {
		this.tableCap = tableCap;
	}
	
	/**
	 * This method prints the details of the table
	 * that includes table number, table status
	 * and table capacity. 
	 * This method will mostly be used to print the
	 * available tables at the period of time.(current time)
	 */
	
	public void printTable(){
		
		System.out.println("Table number : " + tableNum + "\tStatus : " + tableStatus + "\tCapacity : " + tableCap);
	}

}