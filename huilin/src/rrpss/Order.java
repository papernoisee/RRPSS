package rrpss;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This class 'order' allows the employees to create, view and and/remove order items to/from order 
 * also, an order invoice will be printed based on the final order by the customer after he calls for the bill 
 * @author yangzhen
 *
 */
public class Order {
	private int orderId;
	private int setPackId;
	private int employeeId;
	private int tableNum;
	private int itemId;
	private final double GST = 0.07;
	private final double SVCHARGE = 0.1;
	private int month;

	private ArrayList<MenuItem> menuItemArr = new ArrayList<MenuItem>();
	private ArrayList<SetPackage> setPackageArr = new ArrayList<SetPackage>();


	/**
	 * this is a default constructor for Order
	 */
	public Order(){
	}

	/**
	 * this constructor passes in the local parameter, the id of the employee as the information it needs for an order
	 * @param employeeId the id of the employee in charge of a particular order
	 */
	public Order(int employeeId){
		this.employeeId = employeeId;
	}

	/**
	 * this constructor help the system to keep track of the items ordered by a customer 
	 * by adding each menu item into the mennuItemArr via an order
	 * @param menuItem is the item in the menu
	 */
	// For aggregation between Order and MenuItem and SetPackage
	public void addMenuItem(MenuItem menuItem){
		menuItemArr.add(menuItem);
	}
	/**
	 * this constructor help the system to keep track of the no.of set package ordered by a customer 
	 * by adding each set package into the setPackageArr via an order
	 * @param setPackage this is the set package in the menu
	 */
	public void addSetPackage(SetPackage setPackage){
		setPackageArr.add(setPackage);
	}
	/**
	 * getter method to retrieve the ID of the order for the invoice later 
	 * @return the ID of the order
	 */
	public int getOrderId(){
		return orderId;
	}
	/**
	 *setter method to assign order ID of customer to 'orderId'
	 * @param orderId this refers to the ID of the order 
	 */
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	/**
	 * getter method to retrieve the ID of the employee who has successfully completed an order for the invoice later 
	 * @return the ID of the employee who has successfully completed an order
	 */
	public int getEmployeeId(){
		return employeeId;
		//return this.employee.getEmployeeId;
	}
	/**
	 * getter method to retrieve the table number for each order so that the employees can check against it
	 * @return the table number of each order 
	 */
	public int getTableNum(){
		return tableNum;
	}
	/**
	 * setter method to assign table number of each order to 'tableNum'
	 * @param tableNum this refers to the table number of the order 
	 */
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	/**
	 *getter method to retrieve the ID of the set packages in the order 
	 * @return the ID of the set package 
	 */
	public int getSetPackId() {
		return setPackId;
	}
	/**
	 * setter method to assign the ID of the set package to 'setPackId'
	 * @param setPackId
	 */
	public void setSetPackId(int setPackId) {
		this.setPackId = setPackId;
	}
	/**
	 * setter method to assign employee ID of who is taking an order to 'employeeId'
	 * @param employeeId this refers to the ID of the employee 
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * getter method to retrieve the ID of the menu item in an order 
	 * @return the ID of the menu item
	 */
	public int getItemId(){
		return itemId;
	}

	/**
	 * getter methos to retrieve the month the order was made
	 * @return the month in which the order was created 
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * setter method to assign order ID of customer to 'orderId'
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
	}	

	//	public MenuItem getMenuItem(int i){
	//		return menuItemArr.get(i);
	//	}
	/**
	 * getter method to get the items in the menu item array 
	 * @return the menu item array 
	 */
	public ArrayList<MenuItem> getMenuItemArr(){

		return menuItemArr;
	}
	/**
	 * getter method to get the set packages in the set package array 
	 * @return set package array 
	 */
	public ArrayList<SetPackage> getSetPackageArr(){

		return setPackageArr;
	}
	/**
	 * 
	 */
	public void printAlacarteQty(){
		int qty = 0;
		Formatter fmt = new Formatter();

		ArrayList<MenuItem> menuItemArrTemp = new ArrayList<MenuItem>();
		for (int i = 0; i<menuItemArr.size(); i++)
		{
			menuItemArrTemp.add(menuItemArr.get(i));
		}

		while (menuItemArrTemp.size() > 0){

			qty = 0;
			String menuItemName = null;
			int menuItemId = menuItemArrTemp.get(0).getItemId();

			// if there is only 1 item in the order, print without looping
			if (menuItemArrTemp.size() == 1)
			{
				qty++;

				System.out.println("Menu item ID  : " + menuItemId +  "	(Qty)Name: " + " (" + qty + ") " + menuItemArrTemp.get(0).getItemName());
				menuItemArrTemp.clear();
			}

			else 
			{
				for (int i = 0; i < menuItemArrTemp.size(); i++)
				{
					// get qty of the specific itemId	
					if (menuItemArrTemp.get(i).getItemId() == menuItemId)
					{
						menuItemName = menuItemArrTemp.get(i).getItemName();
						menuItemArrTemp.remove(i);
						qty++;
						i--; //this ensures that the first element in temp will always have i = 0
					}
				}
				System.out.println("Menu item ID  : " + menuItemId +  "	(Qty)Name: " + " (" + qty + ") " + menuItemName);
			}
		}

		fmt.close();
	}
	/**
	 * This method prints the total number of promotional quantity items as well as their corresponding IDs which was ordered by the customer 
	 */
	public void printPromotionalQty(){
		int qty = 0;

		ArrayList<SetPackage> setPackageArrTemp = new ArrayList<SetPackage>();
		for (int i = 0; i<setPackageArr.size(); i++)
		{
			setPackageArrTemp.add(setPackageArr.get(i));
		}

		while (setPackageArrTemp.size() > 0){

			qty = 0;
			String setPackName = null;
			int setPackId = setPackageArrTemp.get(0).getSetPackId();

			// if there is only 1 item in the order, print without looping
			if (setPackageArrTemp.size() == 1)
			{
				qty++;
				System.out.println("Set Package ID: " + setPackId +  "	(Qty)Name: " + " (" + qty + ") " + setPackageArrTemp.get(0).getSetPackName());
				setPackageArrTemp.clear();
			}

			else 
			{
				for (int i = 0; i < setPackageArrTemp.size(); i++)
				{
					// get qty of the specific itemId	
					if (setPackageArrTemp.get(i).getSetPackId() == setPackId)
					{
						setPackName = setPackageArrTemp.get(i).getSetPackName();
						setPackageArrTemp.remove(i);
						qty++;
						i--; //this ensures that the first element in temp will always have i = 0
					}
				}
				System.out.println("Set Package ID: " + setPackId +  "	(Qty)Name: " + " (" + qty + ") " + setPackName);
			}
		}
	}

	/**
	 * This method prints the order invoice for the customer starting with the address of our designed restaurant 
	 * details such as the date and time of visit, name of server, table number are printed
	 * following that, every item, and its quantity, price and value are printed via 
	 * getting every menu item from their menu item array list which was previously put in
	 * subtotal, gst and service charge ( fixed percentage) are included in the invoice
	 * 
	 * @param orderArrList an array of all orders in the restaurant 
	 * @param index of the menu item in the ordered array list 
	 */
	public void printOrderInvoice(List<Order> orderArrList, int index){

		double totalPrice = 0;
		String title = "%1s%-30s%5s%10s%10s%2s%n";
		String format = "%1s%-30s%5s%1s%10.2f%10.2f%1s%n";

		//print restaurant details//
		System.out.println();
		System.out.println("	           CRESCENDO RESTAURANT");
		System.out.println("	       NTU NORTH SPINE NS4 #05-105");
		System.out.println("      HP:6790 0000  Mob: 9790 0000  Fax: 8283 6264");
		System.out.println();
		System.out.println("		      ORDER INVOICE");
		System.out.println();


		//print timestamp, table number and employee ID
		//ASSUMPTION: TIMESTAMP IS REFERRING TO THE TIME WHEN THE ORDER INVOICE IS PRINTED
		Date date = new Date();
		String details = "%-6s%-36s%14s%2s%n";

		//date-MONTH-year //Hour-min
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

		String currentDate = dateFormat.format(date); // string to date
		String currentTime = parser.format(date);

		System.out.format(details, "Date: ", currentDate, "Employee ID:  " , orderArrList.get(index).getEmployeeId());
		System.out.format(details, "Time: ", currentTime, "Table Number: " , orderArrList.get(index).getTableNum());

		System.out.println();


		//print order invoice
		System.out.println("+--------------------------------------------------------+");
		System.out.format(title, "|", "ITEM", "|QTY", "|PRICE", "|VALUE","|");
		System.out.println("+--------------------------------------------------------+");


		//print all the ala-carte items in the order 
		int qty = 0;
		ArrayList<MenuItem> menuItemArrTemp = new ArrayList<MenuItem>();
		for (int i = 0; i<menuItemArr.size(); i++)
		{
			menuItemArrTemp.add(menuItemArr.get(i));
		}

		while (menuItemArrTemp.size() > 0){

			qty = 0;
			String menuItemName = null;
			double menuItemPrice = 0;
			double menuItemValue = 0;
			int menuItemId = menuItemArrTemp.get(0).getItemId();

			// if there is only 1 item in the order, print without looping
			if (menuItemArrTemp.size() == 1)
			{
				qty++;
				menuItemValue = qty * menuItemArrTemp.get(0).getItemPrice();
				totalPrice = menuItemValue + totalPrice;
				System.out.format(format, "|", menuItemArrTemp.get(0).getItemName(), qty, 
						" ", menuItemArrTemp.get(0).getItemPrice(), menuItemValue, "|", "\n" );
				System.out.println("+--------------------------------------------------------+");
				menuItemArrTemp.clear();
			}

			else 
			{
				for (int i = 0; i < menuItemArrTemp.size(); i++)
				{
					// get qty of the specific itemId	
					if (menuItemArrTemp.get(i).getItemId() == menuItemId)
					{
						menuItemName = menuItemArrTemp.get(i).getItemName();
						menuItemPrice = menuItemArrTemp.get(i).getItemPrice();
						menuItemArrTemp.remove(i);
						qty++;
						i--; //this ensures that the first element in temp will always have i = 0
						menuItemValue = qty * menuItemPrice;
					}
				}
				totalPrice = menuItemValue + totalPrice;
				System.out.format(format, "|", menuItemName, qty, " ", menuItemPrice, menuItemValue, "|", "\n");
				System.out.println("+--------------------------------------------------------+");
			}
		}

		//print all the promotional items in the order
		int p_qty = 0;
		ArrayList<SetPackage> setPackageArrTemp = new ArrayList<SetPackage>();
		for (int i = 0; i<setPackageArr.size(); i++)
		{
			setPackageArrTemp.add(setPackageArr.get(i));
		}

		while (setPackageArrTemp.size() > 0){

			p_qty = 0;
			String setPackName = null;
			double setPackPrice = 0;
			double setPackValue = 0;
			int setPackId = setPackageArrTemp.get(0).getSetPackId();

			// if there is only 1 item in the order, print without looping
			if (setPackageArrTemp.size() == 1)
			{
				p_qty++;
				setPackValue = p_qty * setPackageArrTemp.get(0).getSetPackPrice();
				totalPrice = setPackValue + totalPrice;
				System.out.format(format, "|", setPackageArrTemp.get(0).getSetPackName(), p_qty, " ", 
						setPackageArrTemp.get(0).getSetPackPrice(), setPackValue, "|", "\n" );
				System.out.println("+--------------------------------------------------------+");
				setPackageArrTemp.clear();
			}

			else 
			{
				for (int i = 0; i < setPackageArrTemp.size(); i++)
				{
					// get qty of the specific itemId	
					if (setPackageArrTemp.get(i).getSetPackId() == setPackId)
					{
						setPackName = setPackageArrTemp.get(i).getSetPackName();
						setPackPrice = setPackageArrTemp.get(i).getSetPackPrice();
						setPackageArrTemp.remove(i);
						p_qty++;
						i--; //this ensures that the first element in temp will always have i = 0
						setPackValue = p_qty * setPackPrice;
					}
				}
				totalPrice = setPackValue + totalPrice;
				System.out.format(format, "|", setPackName, p_qty, " ", setPackPrice, setPackValue, "|", "\n");
				System.out.println("+--------------------------------------------------------+");
			}
		}


		System.out.println();

		//Print GST & service charge
		String tax = "%50s%8.2f%n";

		double svCharge = SVCHARGE * totalPrice;
		double gst = GST * totalPrice;
		double roundOffGST = Math.round(gst * 100.00) / 100.00;
		double roundOffSVCHARGE = Math.round(svCharge * 100.00) / 100.00;

		System.out.format(tax,"Sub Total" , totalPrice );
		System.out.format(tax,"GST" , roundOffGST );
		System.out.format(tax,"Service Charge", roundOffSVCHARGE );
		System.out.println();

		totalPrice = (totalPrice + svCharge + gst);

		//print total price
		System.out.println();
		System.out.format(tax,"TOTAL PRICE:" , totalPrice );

		//print remarks
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("    **************************************************    ");
		System.out.println("		THANK YOU FOR DINING WITH US!");
		System.out.println("		       SEE YOU AGAIN");
		System.out.println("    **************************************************");
		System.out.println("    **************************************************");
		System.out.println();

	}

}