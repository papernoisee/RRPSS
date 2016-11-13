package rrpss;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author User josephine
 * this class is to classify the sale revenue reports by month and print them out
 */
public class SaleRevReport {
	private int month;
	private double totalRevenue;
		
	private ArrayList<Order> saleRevOrderArrListMenuItem = new ArrayList<Order>();
	private ArrayList<Order> saleRevOrderArrListSetPackage = new ArrayList<Order>();

	/**
	 * getter method to get the month of the sale revenue report
	 * @return month
	 */
	public int  getMonth() {
		return month;
	}

	/**
	 * setter method to set the month of the sale revenue report
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * getter method to get the total revenue for that month 
	 * @return total revenue
	 */
	public double getTotalRevenue() {
		return totalRevenue;
	}

	/**
	 * getter method to get the menu items for that month for the sale revenue report
	 * @return saleRevOrderArrListMenuItem
	 */
	public ArrayList<Order> getSaleRevOrderArrListMenuItem(){
		return saleRevOrderArrListMenuItem;
	}
	
	/**
	 * getter method to get the set packages for that month for the sale revenue report
	 * @return saleRevOrderArrListSetPackage
	 */
	public ArrayList<Order> getSaleRevOrderArrListSetPackage(){
		return saleRevOrderArrListSetPackage;
	}
	
	/**
	 * print method to output the details of the restaurant, sale revenue report for the month
	 * and all the set packages, menu items, their qty, values (qty X price)
	 * and lastly, the total revenue
	 * 
	 * @param month
	 * @param saleRevReportArrList
	 */
	public void printSaleRevReport(int month, List<SaleRevReport> saleRevReportArrList){
		double menuItemValue = 0;
		double totalPrice = 0;
		double menuItemPrice = 0;
		double setPackValue = 0;
		double setPackPrice = 0;

		System.out.println();
		System.out.println("	           CRESCENDO RESTAURANT");
		System.out.println("	       NTU NORTH SPINE NS4 #05-105");
		System.out.println("      HP:6790 0000  Mob: 9790 0000  Fax: 8283 6264");
		System.out.println();

		System.out.println("		    SALE REVENUE REPORT");
		System.out.println();
		
		if (month == 1){
			System.out.println("MONTH: JANUARY");
		}
		else if (month == 2){
			System.out.println("MONTH: FEBURARY");
		}
		else if (month == 3){
			System.out.println("MONTH: MARCH");
		}
		else if (month == 4){
			System.out.println("MONTH: APRIL");
		}
		else if (month == 5){
			System.out.println("MONTH: MAY");
		}
		else if (month == 6){
			System.out.println("MONTH: JUNE");
		}
		else if (month == 7){
			System.out.println("MONTH: JULY");
		}
		else if (month == 8){
			System.out.println("MONTH: AUGUST");
		}
		else if (month == 9){
			System.out.println("MONTH: SEPTEMBER");
		}
		else if (month == 10){
			System.out.println("MONTH: OCTOBER");
		}
		else if (month == 11){
			System.out.println("MONTH: NOVEMBER");
		}
		else if (month == 12){
			System.out.println("MONTH: DECEMBER");
		}
		
		System.out.println(	);



		//print details of sale revenue report for alacarte
		String title = "%1s%-30s%5s%10s%10s%2s%n";
		String format = "%1s%-30s%5s%1s%10.2f%10.2f%1s%n";
		
		System.out.println("+--------------------------------------------------------+");
		System.out.format(title, "|", "MENU ITEM", "|QTY", "|PRICE", "|VALUE","|");
		System.out.println("+--------------------------------------------------------+");



		//to print item name and its quantity

		//print menu item
		ArrayList<MenuItem> menuItemArrTemp = new ArrayList<MenuItem>();

		int qty = 0;

		for(int s = 0; s<saleRevOrderArrListMenuItem.size(); s++){
			for (int j = 0; j<saleRevOrderArrListMenuItem.get(s).getMenuItemArr().size(); j++)
			{
				menuItemArrTemp.add(saleRevOrderArrListMenuItem.get(s).getMenuItemArr().get(j));
			}
		}

		while (menuItemArrTemp.size() > 0){

			qty = 0;
			String menuItemName = null;
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
				
//				
				menuItemArrTemp.clear();
			}

			else 
			{
				for (int j = 0; j < menuItemArrTemp.size(); j++)
				{
					// get qty of the specific itemId	
					if (menuItemArrTemp.get(j).getItemId() == menuItemId)
					{
						menuItemName = menuItemArrTemp.get(j).getItemName();
						menuItemPrice = menuItemArrTemp.get(j).getItemPrice();
						menuItemArrTemp.remove(j);
						qty++;
						j--; //this ensures that the first element in temp will always have i = 0
						menuItemValue = qty * menuItemPrice;

					}
				}
				totalPrice = menuItemValue + totalPrice;
				System.out.format(format, "|", menuItemName, qty, " ", menuItemPrice, menuItemValue, "|", "\n");
				System.out.println("+--------------------------------------------------------+");
			}
		}



		//print details of sale revenue report for SET PACKAGE
		//System.out.println();
		System.out.println("+--------------------------------------------------------+");
		System.out.format(title, "|", "SET PACKAGE", "|QTY", "|PRICE", "|VALUE","|");
		System.out.println("+--------------------------------------------------------+");
		
		//print set package
		ArrayList<SetPackage> setPackageArrTemp = new ArrayList<SetPackage>();
		qty = 0;

		for(int s = 0; s<saleRevOrderArrListSetPackage.size(); s++){
			for (int j = 0; j<saleRevOrderArrListSetPackage.get(s).getSetPackageArr().size(); j++)
			{

				setPackageArrTemp.add(saleRevOrderArrListSetPackage.get(s).getSetPackageArr().get(j));
			}
		}
		while (setPackageArrTemp.size() > 0){

			qty = 0;
			String setPackName = null;
			int setPackId = setPackageArrTemp.get(0).getSetPackId();

			// if there is only 1 item in the order, print without looping
			if (setPackageArrTemp.size() == 1)
			{
				qty++;
				setPackValue = qty * setPackageArrTemp.get(0).getSetPackPrice();
				totalPrice = setPackValue + totalPrice;
				System.out.format(format, "|", setPackageArrTemp.get(0).getSetPackName(), qty, " ", 
						setPackageArrTemp.get(0).getSetPackPrice(), setPackValue, "|", "\n" );
				System.out.println("+--------------------------------------------------------+");
				setPackageArrTemp.clear();
			}

			else 
			{
				for (int i= 0; i < setPackageArrTemp.size(); i++)
				{
					// get qty of the specific itemId	
					if (setPackageArrTemp.get(i).getSetPackId() == setPackId)
					{
						setPackName = setPackageArrTemp.get(i).getSetPackName();
						setPackPrice = setPackageArrTemp.get(i).getSetPackPrice();
						setPackageArrTemp.remove(i);
						qty++;
						i--; //this ensures that the first element in temp will always have i = 0
						setPackValue = qty * setPackPrice;
					}
				}
				totalPrice = setPackValue + totalPrice;
				System.out.format(format, "|", setPackName, qty, " ", setPackPrice, setPackValue, "|", "\n");
				System.out.println("+--------------------------------------------------------+");
			}
		}

		System.out.println();
		System.out.println();
		
		String tax = "%47s%-3s%7.2f%n";

		System.out.println();
		System.out.format(tax,"TOTAL REVENUE: " ,"  $", totalPrice);
		System.out.println();
	}

}