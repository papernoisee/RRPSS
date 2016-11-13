package rrpss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.ParseException;

/**
 * 
 * @author Josephine Chua
 * 
 * this is our main code for the RRPSS program where the staff can choose from 12 options to explore
 * (1) Create/Update/Remove menu item
 * (2) Create/Update/Remove promotion
 * (3) Create order
 * (4) View order
 * (5) Add/Remove order item(s) to/from order
 * (6) Create reservation booking
 * (7) Check/Remove reservation booking
 * (8) Check table availablity
 * (9) Print order invoice
 * (10) Print sale revenue report by period
 * (11) Register/Update/Remove employee
 * (12) Exit
 *
 */
public class Main {
	System.out.println("Hello, world");

	static List<Table> table1ArrList = new ArrayList<Table>();

	static List<Reservation> reservationArrList = new ArrayList<Reservation>(); //added
	//static List<Tableforreservation> tableforresArrList = new ArrayList<Tableforreservation>(); //added
	static List<Table> tableArrList = new ArrayList<Table>(); //added
	static SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
	static Reservation reservation2 = new Reservation();
	static DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

	static Date date = new Date();

	static 		String currentdate = dateFormat.format(date); //string to date

	/**
	 * this is our main code for RRPSS
	 * where we will ask the user to choose between 12 options (1-12)
	 * each option has its own functions
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		//ArrayList<MenuItem> menuItemArr = new ArrayList<MenuItem>(); //array of menuItem objects consisting of all the att inside

		List<MenuItem> menuItemArrList = new ArrayList<MenuItem>();
		List<SetPackage> setPackageArrList = new ArrayList<SetPackage>(); //added
		List<Order> orderArrList = new ArrayList<Order>(); //added
		//List<Reservation> reservationArrList = new ArrayList<Reservation>(); //added
		List<Table> tableArrList = new ArrayList<Table>(); //added
		List<Table> availableTableArrList = new ArrayList<Table>();
		List<SaleRevReport> saleRevReportArrList = new ArrayList<SaleRevReport>();
		List<Employee> employeeArrList = new ArrayList<Employee>();

		int choice = -1;
		int idCount = 0;

		int setPackCount = 0;
		int ReservationCount = 0;

		int setpackidCount = 0; //added
		int orderidCount = 0; //added
		int reservationidCount = 0; //added

		int j = 0;
		String menuItemArr [] = null;
		String setPackageArr [] = null; //added
		String orderArr [] = null; //added
		String reservationArr [] = null; //added
		String tableArr [] = null; //added
		String table1Arr[] = null;
		String reportArr[] = null;
		String employeeArr[] = null;

		Scanner sc = new Scanner(System.in);

		do{
			System.out.println();
			System.out.println("(1) Create/Update/Remove menu item");
			System.out.println("(2) Create/Update/Remove promotion");
			System.out.println("(3) Create order");
			System.out.println("(4) View order");
			System.out.println("(5) Add/Remove order item(s) to/from order");
			System.out.println("(6) Create reservation booking");
			System.out.println("(7) Check/Remove reservation booking");
			System.out.println("(8) Check table availablity");
			System.out.println("(9) Print order invoice");
			System.out.println("(10) Print sale revenue report by period");
			System.out.println("(11) Register/Update/Remove employee");
			System.out.println("(12) Exit");
			System.out.println();

			System.out.println("Enter your choice: ");
			choice = sc.nextInt();


			switch(choice){

			//########################################### CASE 1 Create/Update/Remove menu item ###################################################
			/**
			 * this is our code for case 1 
			 * where we will ask the user whether he wants to create, update or remove any menu item
			 */
			case 1: 

				//System.out.println("ID | Name | Type | Description | Price | Recommendation | Lunch and/or Dinner");
				//when printing the menu items available, have to call the file reader again to update arraylist
				//can doc this later 


				idCount = 0;
				//to prevent it from reading multiple times and resulting in duplicate results in the file
				menuItemArrList.clear();

				//read file to get the menu items stored in the file and to update the array list as well as idcount
				/**
				 * read the menu item file first to prevent collision of menu item ID
				 * and also to update the menu item array list with those menu items that were already created before
				 */
				idCount = readMenuItemFile(menuItemArr, menuItemArrList, idCount);

				System.out.println("(1) Create menu item");
				System.out.println("(2) Update menu item");
				System.out.println("(3) Remove menu item");

				choice = sc.nextInt();

				////////////////////////////////////////////////////////CREATE MENU ITEM//////////////////////////////////////////////////////////////
				/**
				 * if the user chose "1", he will be able to create a new menu item
				 * when creating a new menu item, he gets to decide 6 attributes
				 * (the item name, type, description, price, whether the item is a chef recommendation and if
				 * the item is served during lunch/dinner or in both lunch and dinner)
				 */

				if (choice == 1){


					String itemName;
					String itemDesc;
					double itemPrice;


					MenuItem menuItem = new MenuItem(); //create obj

					//set item id
					menuItem.setItemId(idCount + 1);

					//set menu item name
					System.out.println("Input new menu item name: ");

					String dummy = sc.nextLine();

					itemName = sc.nextLine(); //let user input new item name
					menuItem.setItemName(itemName); // to call setter mtd from MenuItem class

					/**
					 * the user gets to choose between 4 types
					 * whether he wants the dish to be a main course, appetizer, dessert or a beverage
					 * and he can only input 1, 2, 3 or 4
					 */
					//set menu item type
					System.out.println("Input " + menuItem.getItemName() + " type"); 
					System.out.println("(1) Main Course");
					System.out.println("(2) Appetizer");
					System.out.println("(3) Dessert");
					System.out.println("(4) Beverage");

					choice = sc.nextInt();

					do {
						switch(choice){

						case 1: menuItem.setItemType("Main Course");
						break;

						case 2: menuItem.setItemType("Appetizer");
						break;

						case 3: menuItem.setItemType("Dessert");
						break;

						case 4: menuItem.setItemType("Beverage");
						break;

						default: System.out.println("Invalid choice, please select again. \n");
						System.out.println("Input " + menuItem.getItemName() + " type"); 
						System.out.println("(1) Main Course");
						System.out.println("(2) Appetizer");
						System.out.println("(3) Dessert");
						System.out.println("(4) Beverage");
						choice = sc.nextInt();
						}

						if (menuItem.getItemType() != null){
							choice = -1;
						}
					}
					while(choice != -1);


					//set item description
					System.out.println("Input " + menuItem.getItemName() + " description");

					dummy = sc.nextLine();

					itemDesc = sc.nextLine();
					menuItem.setItemDesc(itemDesc);

					//set item price

					System.out.println("Input " + menuItem.getItemName() + " price");
					itemPrice = sc.nextDouble();
					menuItem.setItemPrice(itemPrice);

					//set item recom (if recommended = true, else false)
					System.out.println("Is " + menuItem.getItemName() + " a chef's recommendation?");

					System.out.println("(1) Recommended");
					System.out.println("(2) Not recommended");


					choice = sc.nextInt();
					int flag = 0;

					do{

						//choose recommended or not
						switch(choice){
						case 1: menuItem.setItemRecom(true);
						flag = 1;
						break;

						case 2: menuItem.setItemRecom(false);
						flag = 1;
						break;

						default: System.out.println("Invalid choice, please select again. \n" );
						System.out.println("Is " + menuItem.getItemName() + " a chef's recommendation?");
						System.out.println("(1) Recommended");
						System.out.println("(2) Not recommended");
						choice = sc.nextInt();
						}	

						if (flag == 1){
							choice = -1;
						}

					}while(choice != -1);


					//set item is lunch or dinner 
					System.out.println("Is " + menuItem.getItemName() + " available during Lunch, Dinner or both?");

					System.out.println("(1) Lunch");
					System.out.println("(2) Dinner");
					System.out.println("(3) Lunch and Dinner");


					choice = sc.nextInt();

					do{
						switch(choice){
						case 1: menuItem.setLunchDinner("Lunch");
						break;	

						case 2: menuItem.setLunchDinner("Dinner");
						break;	

						case 3: menuItem.setLunchDinner("Lunch and Dinner");
						break;

						default: System.out.println("Invalid choice, please select again. \n");
						System.out.println("Is " + menuItem.getItemName() + " available during Lunch, Dinner or both?");
						System.out.println("(1) Lunch");
						System.out.println("(2) Dinner");
						System.out.println("(3) Lunch and Dinner");
						choice = sc.nextInt();
						}

						if (menuItem.getLunchDinner() != null){
							choice = -1;
						}	

					}while(choice != -1);

					//add new menu item into array list
					menuItemArrList.add(menuItem);

					//to write into the file the details of the menu items
					/**
					 * store the new menu item that we created into a file
					 */
					writeMenuItemFileForAppend(menuItem);

					System.out.println("Menu item ID " + menuItem.getItemId() + ", '" + menuItem.getItemName() + "' created.");
				}

				////////////////////////////////////////////////////////UPDATE MENU ITEM//////////////////////////////////////////////////////////////			
				//update menu item
				/**
				 * if the user chose "2", he will be able to update a menu item he created by inputting the ID
				 * when updating a menu item, he gets to update all 6 attributes
				 * (the item name, type, description, price, whether the item is a chef recommendation and if
				 * the item is served during lunch/dinner or in both lunch and dinner)
				 */
				else if(choice == 2){

					System.out.println();
					System.out.println("Current list of menu items: ");
					for(int i = 0; i< menuItemArrList.size(); i++){										
						System.out.println(menuItemArrList.get(i).getItemId() + " : "+ menuItemArrList.get(i).getItemName());
					}
					System.out.println();
					
					System.out.println("Enter ID of menu item you would like to update: ");
					int itemId = sc.nextInt();


					//check if the item id in each MenuItem object of the arraylist is the same as the item id inputed 
					//allow update only if item id is equal

					MenuItem menuItem = new MenuItem();

					for(int i=0; i < menuItemArrList.size(); i++){
						if(menuItemArrList.get(i).getItemId() == itemId){

							menuItem = menuItemArrList.get(i);
						}
					}
					//System.out.println("ITEM NAME + ITEM ID" + " " + menuItem.getItemName() + " + " + menuItem.getItemId()); 

					System.out.println("Which section would you like to update?");
					System.out.println("(1)Update name");
					System.out.println("(2)Update type");
					System.out.println("(3)Update description");
					System.out.println("(4)Update price");
					System.out.println("(5)Update recommendation");
					System.out.println("(6)Update availability (L/D/L&D)");
					System.out.println("(7)Exit");

					//choose what to update
					choice = sc.nextInt();

					while(choice != 7){
						String dummy = sc.nextLine();
						switch(choice){

						//update name
						case 1: System.out.println("Update name for menu item ID " + itemId + " : " );
						String newName = sc.nextLine();
						menuItem.setItemName(newName);
						System.out.println("Updated menu item name: " + menuItem.getItemName() + ".");

						writeMenuItemFileForOverwrite(menuItemArrList);

						break;	

						//update type
						case 2: System.out.println("Update type for menu item ID " + itemId + " : " );
						System.out.println("(1) Main Course");
						System.out.println("(2) Appetizer");
						System.out.println("(3) Dessert");
						System.out.println("(4) Beverage");
						int newType = sc.nextInt(); //switch

						do {
							switch(newType){

							case 1: menuItem.setItemType("Main Course");
							choice = -1;
							break;

							case 2: menuItem.setItemType("Appetizer");
							choice = -1;
							break;

							case 3: menuItem.setItemType("Dessert");
							choice = -1;
							break;

							case 4: menuItem.setItemType("Beverage");
							choice = -1;
							break;

							default: System.out.println("Invalid choice, please select again. \n");
							System.out.println("Update type for menu item id " + itemId + " : " );
							System.out.println("(1) Main Course");
							System.out.println("(2) Appetizer");
							System.out.println("(3) Dessert");
							System.out.println("(4) Beverage");
							choice = sc.nextInt();
							}


						}while(choice != -1);

						System.out.println("Updated menu item type: " +  menuItem.getItemType()+ ".");

						writeMenuItemFileForOverwrite(menuItemArrList);

						break;	

						//update description
						case 3: System.out.println("Update description for menu item ID " + itemId + " : ");
						String newDesc = sc.nextLine();
						menuItem.setItemDesc(newDesc);
						System.out.println("Updated menu item description: " + menuItem.getItemDesc()+ ".");

						writeMenuItemFileForOverwrite(menuItemArrList);

						break;

						case 4: //update price
							System.out.println("Update price for menu item ID " + itemId + " : ");		
							double newPrice = sc.nextDouble();
							menuItem.setItemPrice(newPrice);
							System.out.println("Updated menu item price: " + menuItem.getItemPrice()+ ".");

							writeMenuItemFileForOverwrite(menuItemArrList);

							break;


							//update recommendation
						case 5: System.out.println("Update recommendation for menu item ID " + itemId + " : ");
						if(menuItem.getItemRecom() == true){
							menuItem.setItemRecom(false);
							System.out.println("Not recommended updated.");
						}

						else{
							menuItem.setItemRecom(true);
							System.out.println("Recommended updated.");
						}


						writeMenuItemFileForOverwrite(menuItemArrList);

						break;


						//update availability in lunch/dinner/lunch and dinner
						case 6:	System.out.println("Update lunch/dinner/lunch and dinner for menu item ID " + itemId + " : ");
						System.out.println("(1) Lunch");
						System.out.println("(2) Dinner");
						System.out.println("(3) Lunch and Dinner");

						int newLunchDinner = sc.nextInt(); //switch

						do {
							switch(newLunchDinner){

							case 1: menuItem.setLunchDinner("Lunch");
							choice = -1;
							break;

							case 2: menuItem.setLunchDinner("Dinner");
							choice = -1;
							break;

							case 3: menuItem.setLunchDinner("Lunch and Dinner");
							choice = -1;
							break;

							default: System.out.println("Invalid choice, please select again. \n");
							System.out.println("Update type for menu item id " + itemId + " : " );
							System.out.println("(1) Lunch");
							System.out.println("(2) Dinner");
							System.out.println("(3) Lunch and Dinner");

							choice = sc.nextInt();
							}

						}while(choice != -1);
						System.out.println("Updated menu item availability: " + menuItem.getLunchDinner()+ ".");

						/**
						 * overwrite the menu item file to update it
						 */
						writeMenuItemFileForOverwrite(menuItemArrList);


						break;

						case 7: break;


						default: System.out.println();
						System.out.println("Invalid choice, please select again. \n");

						System.out.println("(1)Update name");
						System.out.println("(2)Update type");
						System.out.println("(3)Update description");
						System.out.println("(4)Update price");
						System.out.println("(5)Update recommendation");
						System.out.println("(6)Update availability (L/D/L&D)");
						System.out.println("(7)Exit");

						choice = sc.nextInt();
						}
						System.out.println();
						System.out.println("Which section would you like to update?");
						System.out.println("(1)Update name");
						System.out.println("(2)Update type");
						System.out.println("(3)Update description");
						System.out.println("(4)Update price");
						System.out.println("(5)Update recommendation");
						System.out.println("(6)Update availability (L/D/L&D)");
						System.out.println("(7)Exit");

						//choose what to update
						choice = sc.nextInt();							
					}
				}
				////////////////////////////////////////////////////////REMOVE MENU ITEM//////////////////////////////////////////////////////////////	
				//remove menu item
				/**
				 * if the user chose "3", he will be able to remove a new menu item
				 */
				else if(choice == 3){

					System.out.println();
					System.out.println("Current list of menu items: ");
					for(int i = 0; i< menuItemArrList.size(); i++){										
						System.out.println(menuItemArrList.get(i).getItemId() + " : "+ menuItemArrList.get(i).getItemName());
					}

					System.out.println();

					System.out.println("Input menu item ID to be removed: ");
					int itemId = sc.nextInt();

					//check if the item id in each MenuItem object of the arraylist is the same as the item id inputed 
					//allow delete only if item id is equal

					for(int i=0; i < menuItemArrList.size(); i++){
						if(menuItemArrList.get(i).getItemId() == itemId){

							//remove according to index of menuItemArrList

							System.out.println("Menu item ID " + itemId +", " + menuItemArrList.get(i).getItemName() +  " removed.");
							menuItemArrList.remove(i);
						}
					}

					/**
					 * overwrite the menu item file to remove it
					 */
					writeMenuItemFileForOverwrite(menuItemArrList);
				}

				//case 1 break
				break;



				//########################################### CASE 2 Create/Update/Remove promotion ###################################################

				// Create SetPackage object
				// Display existing SetPackage (read from file)
				// Create a new SetPackage
				// SetPackage constructor will create 2 menuitems and add it into the arr
				// user will set the 2 menuitems which is already added into the arr.
				// system will ask if they want to add another menu item.
				// call method to add a new menuitem into the arr.
				/**
				 * this is our code for case 2 
				 * where we will ask the user whether he wants to create, update or remove any promotion (set packages)
				 */

			case 2:

				idCount = 0;
				setpackidCount = 0;

				//prevent it from reading multiple times resulting in duplicates in file
				setPackageArrList.clear();
				menuItemArrList.clear();

				//read menuItems file
				/**
				 * read the menu item file to get the menu items to be inserted into the set package
				 * returns idCount
				 */
				idCount = readMenuItemFile(menuItemArr, menuItemArrList, idCount);

				//read setpackage file
				/**
				 * read the set package file first to prevent collision of set package ID
				 * and also to update the set package array list with those set package that were already created
				 */
				setpackidCount = readSetPackageFile(setPackageArr, setPackageArrList, setpackidCount);

				System.out.println("(1) Create Promotion");
				System.out.println("(2) Update Promotion");
				System.out.println("(3) Remove Promotion");

				choice = sc.nextInt();
				////////////////////////////////////////////////////////CREATE PROMOTION//////////////////////////////////////////////////////////////
				/**
				 * if the user choice "1", he will be able to update a set package he created 
				 * when creating a new menu item, he gets to update all 6 attributes
				 * (the item name, description, price and if the item is served during lunch/dinner or in both lunch and dinner)
				 */	
				if (choice == 1){
					String setpackName;
					String setpackDesc;
					double setpackPrice;

					//create obj 
					SetPackage setPackage = new SetPackage(); 

					//set setpack id
					setPackage.setSetPackId(setpackidCount + 1);

					//set setpack name
					System.out.println("Input new Set Package name: ");
					String dummy = sc.nextLine();

					setpackName = sc.nextLine(); //let user input new item name
					setPackage.setSetPackName(setpackName); // to call setter mtd from SetPackage class

					//set promotion price
					System.out.println("Input " + setPackage.getSetPackName() + " price");
					setpackPrice = sc.nextDouble();
					setPackage.setSetPackPrice(setpackPrice);

					//set setpackage description (can say promote from what period to what period eg Halloween promotion)
					System.out.println("Input " + setPackage.getSetPackName() + " description");

					dummy = sc.nextLine();

					setpackDesc = sc.nextLine();
					setPackage.setSetPackDesc(setpackDesc);

					//set promotion is lunch or dinner 
					System.out.println("Is " + setPackage.getSetPackName() + " available during Lunch, Dinner or both?");

					System.out.println("(1) Lunch");
					System.out.println("(2) Dinner");
					System.out.println("(3) Lunch and Dinner");

					choice = sc.nextInt();
					do{
						switch(choice){
						case 1: setPackage.setSetPackLunchDinner("Lunch");
						break;		

						case 2: setPackage.setSetPackLunchDinner("Dinner");
						break;	

						case 3: setPackage.setSetPackLunchDinner("Lunch and Dinner");
						break;

						default: System.out.println("Invalid choice, please select again. \n");
						System.out.println("Is " + setPackage.getSetPackName() + " available during Lunch, Dinner or both?");
						System.out.println("(1) Lunch");
						System.out.println("(2) Dinner");
						System.out.println("(3) Lunch and Dinner");
						choice = sc.nextInt();
						}

						if (setPackage.getSetPackLunchDinner() != null){
							choice = -1;
						}	

					}while(choice != -1);


					//adding menu items into setpackage(promotion)
					System.out.println();
					System.out.println("List of Main Course: ");

					for(int i = 0; i< menuItemArrList.size(); i++){		
						if (menuItemArrList.get(i).getItemType().equals("Main Course")){
							System.out.println(menuItemArrList.get(i).getItemId() + " : " + menuItemArrList.get(i).getItemName());
						}
					}

					System.out.println();

					System.out.println("Input ID of Main Course for " + setPackage.getSetPackName());
					int mainCourseId = sc.nextInt();
					for(int i =  0; i < menuItemArrList.size(); i++){
						if(menuItemArrList.get(i).getItemId() == mainCourseId){ 
							setPackage.menuItemArr.get(0).setItemId(mainCourseId);
						}
					}

					System.out.println();
					System.out.println("List of Appetizers: ");

					for(int i = 0; i< menuItemArrList.size(); i++){		
						if (menuItemArrList.get(i).getItemType().equals("Appetizer")){
							System.out.println(menuItemArrList.get(i).getItemId() + " : " + menuItemArrList.get(i).getItemName());
						}
					}

					System.out.println();

					System.out.println("Input ID for Appetizer for " + setPackage.getSetPackName());
					int appetizerId = sc.nextInt();
					for(int i =  0; i < menuItemArrList.size(); i++){
						if(menuItemArrList.get(i).getItemId() == appetizerId){ 
							setPackage.menuItemArr.get(1).setItemId(appetizerId);

						}
					}

					System.out.println();
					System.out.println("List of Beverages: ");

					for(int i = 0; i< menuItemArrList.size(); i++){		
						if (menuItemArrList.get(i).getItemType().equals("Beverage")){
							System.out.println(menuItemArrList.get(i).getItemId() + " : " + menuItemArrList.get(i).getItemName());
						}
					}

					System.out.println();

					System.out.println("Input ID for Beverage for " + setPackage.getSetPackName());
					int beverageId = sc.nextInt();
					for(int i =  0; i < menuItemArrList.size(); i++){
						if(menuItemArrList.get(i).getItemId() == beverageId){ 
							setPackage.menuItemArr.get(2).setItemId(beverageId);
						}
					}

					System.out.println("Set Package ID " + setPackage.getSetPackId() + ", '" + setPackage.getSetPackName() + "' created.");


					//add new set package into array list
					setPackageArrList.add(setPackage);

					/**
					 * write into the setpackage file our new setpackage that was just created
					 */
					//write into setpackage file
					writeSetPackageFileForAppend(setPackage);
				}

				////////////////////////////////////////////////////////UPDATE PROMOTION//////////////////////////////////////////////////////////////
				/**
				 * if the user chose "2", he will be able to update a set package he created by inputting the ID
				 * when updating a set package, he gets to update the item name, description, price, 
				 * whether the item is served during lunch/dinner or in both lunch and dinner
				 * or he can choose to update the set package's type (main course, appetizer or beverage respectively)
				 */
				else if (choice == 2){

					System.out.println();
					System.out.println("Current list of promotional items: ");
					for(int i = 0; i< setPackageArrList.size(); i++){										
						System.out.println(setPackageArrList.get(i).getSetPackId() + " : "+ setPackageArrList.get(i).getSetPackName());
					}

					System.out.println();

					System.out.println("Enter ID of Set Package you would like to update: ");

					SetPackage setPack = new SetPackage();
					int setpackid = sc.nextInt();
					for(int i =  0; i < setPackageArrList.size(); i++){	

						if(setPackageArrList.get(i).getSetPackId() == (setpackid)){ 

							setPack = setPackageArrList.get(i);
						}
					}

					System.out.println("Which section would you like to update?");
					System.out.println("(1) Update name");
					System.out.println("(2) Update price");
					System.out.println("(3) Update description");
					System.out.println("(4) Update availability (L/D/L&D)");
					System.out.println("(5) Update Set Package's Main Course");
					System.out.println("(6) Update Set Package's Appetizer");
					System.out.println("(7) Update Set Package's Beverage");
					System.out.println("(8) Exit");

					int choiceforupdate = sc.nextInt();


					while (choiceforupdate != 8){

						switch(choiceforupdate){

						case 1: //update name
							System.out.println("Update name for Set Package ID " + setpackid + " : ");

							String dummy = sc.nextLine();
							String newsetpackname = sc.nextLine();

							setPackageArrList.get(setpackid-1).setSetPackName(newsetpackname); //overwite the arraylist with object
							System.out.println("Updated Set Package name: " + newsetpackname + ".");

							//write into setpackage file
							writeSetPackageFileForOverwrite(setPackageArrList);


							break;

						case 2: 
							System.out.println("Update price for Set Package ID " + setpackid + " : ");

							//double dummy1 = sc.nextDouble();
							double newsetpackprice = sc.nextDouble();

							//need to update both
							setPackageArrList.get(setpackid-1).setSetPackPrice(newsetpackprice); //overwite the arraylist with object

							System.out.println("Updated Set Package price: " + newsetpackprice + ".");

							//write into setpackage file
							writeSetPackageFileForOverwrite(setPackageArrList);

							break;


						case 3: 
							System.out.println("Update description for Set Package ID " + setpackid + " : ");

							String dummy1 = sc.nextLine();
							String newsetpackdesc = sc.nextLine();

							//need to update both
							setPackageArrList.get(setpackid-1).setSetPackDesc(newsetpackdesc); //overwite the arraylist with object

							System.out.println("Updated Set Package description: " + newsetpackdesc + ".");

							//write into setpackage file
							writeSetPackageFileForOverwrite(setPackageArrList);


							break;

						case 4: 
							System.out.println("Update availability for Set Package ID " + setpackid + " : ");
							System.out.println("Is " + setPack.getSetPackName() + " available during Lunch, Dinner or both?");

							System.out.println("(1) Lunch");
							System.out.println("(2) Dinner");
							System.out.println("(3) Lunch and Dinner");

							choice = sc.nextInt();
							do{
								switch(choice){
								case 1: setPack.setSetPackLunchDinner("Lunch");
								choice = -1;
								break;		

								case 2: setPack.setSetPackLunchDinner("Dinner");
								choice = -1;
								break;	

								case 3: setPack.setSetPackLunchDinner("Lunch and Dinner");
								choice = -1;
								break;

								default: System.out.println("Invalid choice, please select again. \n");
								System.out.println("Is " + setPack.getSetPackName() + " available during Lunch, Dinner or both?");
								System.out.println("(1) Lunch");
								System.out.println("(2) Dinner");
								System.out.println("(3) Lunch and Dinner");
								choice = sc.nextInt();
								}	

							}while(choice != -1);

							System.out.println("Updated Set Package availability: " + setPack.getSetPackLunchDinner() + ".");

							//write into setpackage file
							writeSetPackageFileForOverwrite(setPackageArrList);



							break;

						case 5:	
							System.out.println("Update Main Course ID for " + setPack.getSetPackName() + ": ");


							String dummy2 = sc.nextLine();
							int newmainCourseId = sc.nextInt();

							setPackageArrList.get(setpackid-1).setmaincourseId(newmainCourseId); //overwite the arraylist with object

							System.out.println("Updated Main Course: ID " + newmainCourseId + ", " + menuItemArrList.get(newmainCourseId-1).getItemName() + ".");

							//write into setpackage file
							writeSetPackageFileForOverwrite(setPackageArrList);



							break;

						case 6:
							System.out.println("Update Appetizer ID for " + setPack.getSetPackName() + ": ");
							String dummy3 = sc.nextLine();
							int newappetizerId = sc.nextInt();

							setPackageArrList.get(setpackid-1).setappetizerId(newappetizerId); //overwite the arraylist with object

							System.out.println("Updated Appetizer: ID " + newappetizerId + ", " + menuItemArrList.get(newappetizerId-1).getItemName() + ".");

							//write into setpackage file
							writeSetPackageFileForOverwrite(setPackageArrList);

							break;

						case 7:
							System.out.println("Update Beverage ID for " + setPack.getSetPackName() + ": ");
							String dummy4 = sc.nextLine();
							int newbeverageId = sc.nextInt();

							setPackageArrList.get(setpackid-1).setbeverageId(newbeverageId); //overwite the arraylist with object

							System.out.println("Updated Appetizer: ID " + newbeverageId + ", " + menuItemArrList.get(newbeverageId-1).getItemName() + ".");

							//write into setpackage file
							writeSetPackageFileForOverwrite(setPackageArrList);


							break;

						case 8: break;

						default: System.out.println("Invalid choice, please select again. \n");
						System.out.println("Which section would you like to update?");
						System.out.println("(1) Update name");
						System.out.println("(2) Update price");
						System.out.println("(3) Update description");
						System.out.println("(4) Update availability (L/D/L&D)");
						System.out.println("(5) Update Set Package's Main Course");
						System.out.println("(6) Update Set Package's Appetizer");
						System.out.println("(7) Update Set Package's Beverage");
						System.out.println("(8) Exit");
						System.out.println();

						choiceforupdate = sc.nextInt();
						}

						System.out.println();
						System.out.println("Which section would you like to update?");
						System.out.println("(1) Update name");
						System.out.println("(2) Update price");
						System.out.println("(3) Update description");
						System.out.println("(4) Update availability (L/D/L&D)");
						System.out.println("(5) Update Set Package's Main Course");
						System.out.println("(6) Update Set Package's Appetizer");
						System.out.println("(7) Update Set Package's Beverage");
						System.out.println("(8) Exit");
						System.out.println();

						choiceforupdate = sc.nextInt();
					}
				}

				////////////////////////////////////////////////////////REMOVE PROMOTION//////////////////////////////////////////////////////////////
				/**
				 * if user chose "3", he will be able to remove a set package by inputting the ID
				 */
				else if (choice == 3){	

					System.out.println();
					System.out.println("Current list of promotional items: ");
					for(int i = 0; i< setPackageArrList.size(); i++){										
						System.out.println(setPackageArrList.get(i).getSetPackId() + " : "+ setPackageArrList.get(i).getSetPackName());
					}

					System.out.println("Enter ID of Set Package you would like to remove: ");
					int removesetpackid = sc.nextInt();

					for(int i =  0; i < setPackageArrList.size(); i++){	
						if(setPackageArrList.get(i).getSetPackId() == (removesetpackid)){ 

							System.out.println("Set Package id " + removesetpackid +", " + setPackageArrList.get(i).getSetPackName() +  " removed.");
							setPackageArrList.remove(i);

						}
					}

					/**
					 * update the setpackage file 
					 */
					//write into setpackage file
					writeSetPackageFileForOverwrite(setPackageArrList);
				}

				break;

				//########################################### CASE 3 Create Order ###################################################	
				/**
				 * this is our code for case 3 where a staff from our restaurant can create a new order
				 * a new order is created by first asking for the employee id and table number
				 * before adding in either alacarte or promotional items and their quantity into the order
				 */
			case 3:
				//employee will type in the order; prompting the employee to add the ordered items into an array to keep track of the order
				//using menuitemID or setpackID
				//employee can insert the item name and quantity
				//order should automatically include order_id, employee_id (prompt employee to type in his id at the start) and table number.
				//1. ask employee for id and table no.
				//2. do switch (1: ala carte, 2: set, 3: exit)
				//3. if choose 1/2, ask for itemId or setPackId
				//4. then ask for quantity
				//5. go back to switch (1: ala carte, 2: set, 3: exit)

				int tableNumber;
				int employeeId;
				int orderChoice = -1;
				int quantity = 0;
				int itemId = 0, promotionId = 0;


				//to prevent it from reading multiple times and resulting in duplicate results in the file
				menuItemArrList.clear();
				setPackageArrList.clear();
				orderArrList.clear();
				table1ArrList.clear();
				employeeArrList.clear();

				/**
				 * read these files first as menu items, set packages and table are needed to create an order
				 */
				//to get the updated menu items and set packages
				readMenuItemFile(menuItemArr, menuItemArrList, reservationidCount);
				readSetPackageFile(setPackageArr, setPackageArrList, setpackidCount);
				readTableFile(table1Arr, setpackidCount);
				readEmployeeFile(employeeArr, employeeArrList, reservationidCount);


				Order order = new Order();
				Date date = new Date();
				//					Employee employee = new Employee(); //create obj for employee
				//					Table table = new Table(); //create obj for table

				orderidCount = 0;

				//read order file
				orderidCount = readOrderFile(orderArr, orderArrList, orderidCount, menuItemArrList, setPackageArrList);

				//set order id
				order.setOrderId(orderidCount + 1);

				//print out available employees
				System.out.println();
				System.out.println("Available Employees: ");
				for(int i = 0; i< employeeArrList.size(); i++){			
					if (employeeArrList.get(i).getJobTitle().equals("Manager") || employeeArrList.get(i).getJobTitle().equals("Server")){
						System.out.println("ID: " + employeeArrList.get(i).getEmployeeId() + " (" + employeeArrList.get(i).getEmployeeName() + ")");
					}
				}
				System.out.println();

				
				//set employee ID
				System.out.println("Enter employee ID: ");
				employeeId = sc.nextInt();
				order.setEmployeeId(employeeId);

				//set Table number
				System.out.println("Enter table number (1-30): ");
				String dummy = sc.nextLine();
				tableNumber = sc.nextInt();
				order.setTableNum(tableNumber);

				//to get month of order
				DateFormat dateFormat = new SimpleDateFormat("MM");
				//					SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

				String currentDate = dateFormat.format(date); //string to date

				//set timestamp
				order.setMonth(Integer.parseInt(currentDate));

				do {

					System.out.println("Please select a choice: ");
					System.out.println("(1) Create ala carte order.");
					System.out.println("(2) Create promotional order.");
					System.out.println("(3) Complete order.");

					orderChoice = sc.nextInt();

					switch(orderChoice) {

					/**
					 * to input an ala-carte item into the order,
					 * first, get the ID of the ala-carte item (menuitem ID) then input quantity 
					 * and the menu items and its quantity will be added into an array list, menuItemArrList
					 */
					case 1:
						System.out.println();
						System.out.println("List of ala-carte: ");
						for(int i = 0; i< menuItemArrList.size(); i++){										
							System.out.println(menuItemArrList.get(i).getItemId() + " : "+ menuItemArrList.get(i).getItemName());
						}

						System.out.println();

						System.out.println("Please input ala-carte item ID:");
						itemId = sc.nextInt();

						System.out.println("Input quantity of ala-carte item ID " + itemId + " :");
						quantity = sc.nextInt();

						//add menu items that are ordered into arraylist
						for(int i = 0; i < menuItemArrList.size(); i++){

							if(menuItemArrList.get(i).getItemId() == itemId){

								//add the number of menu items according to quantity 
								for(int k = 0; k < quantity; k++){
									order.addMenuItem(menuItemArrList.get(i));

								}
							}
							if(menuItemArrList.size() == 1){
								System.out.println(quantity + " quantity of ala-carte item ID " 
										+ itemId + ", '" + menuItemArrList.get(i).getItemName() + "' " + " is inserted into order.\n");
							}
						}

						break;

						/**
						 * to input a promotional item into the order,
						 * first, get the ID of the promotional item (set package ID) then input quantity 
						 * and the promotional item and its quantity will be added into an array list, setPackageArrList
						 */
					case 2:
						System.out.println();
						System.out.println("List of promotions: ");
						for(int i = 0; i< setPackageArrList.size(); i++){										
							System.out.println(setPackageArrList.get(i).getSetPackId() + " : "+ setPackageArrList.get(i).getSetPackName());
						}

						System.out.println();

						System.out.println("Please input promotional item ID:");
						promotionId = sc.nextInt();

						System.out.println("Input quantity of promotional item ID " + promotionId + " :");
						quantity = sc.nextInt();

						//add set package that are ordered into arraylist
						for(int i = 0; i < setPackageArrList.size(); i++){
							if(setPackageArrList.get(i).getSetPackId() == promotionId){

								//add the number of set package according to quantity 
								for(int k = 0; k < quantity; k++){
									order.addSetPackage(setPackageArrList.get(i));
								}
								System.out.println(quantity + " quantity of promotional item ID " 
										+ promotionId + ", '" + setPackageArrList.get(i).getSetPackName() + "' " + " is inserted into order.\n");
							}
						}

						break;

						/**
						 * get out of the switch case
						 */
					case 3:
						System.out.println("Order completed. \n");
						orderChoice = -1;
						break;

					default:
						System.out.println("Invalid choice, please select again!");
						System.out.println("Please select a choice: ");
						System.out.println("(1) Create ala carte order.");
						System.out.println("(2) Create promotional order.");
						System.out.println("(3) Complete order.");
						System.out.println();

						orderChoice = sc.nextInt();
					}
				} while(orderChoice != -1);

				//print qty after complete order
				/**
				 * print the quantity of the items in the order
				 */
				order.printAlacarteQty();
				order.printPromotionalQty();

				/**
				 * if the table number the staff input at the start is equals to the table number available
				 * then the status of the table will be set to occupied
				 */
				//set table status to occupied
				for(int i = 0; i<table1ArrList.size(); i++){
					if(table1ArrList.get(i).getTableNum() == order.getTableNum()){
						table1ArrList.get(i).setTableStatus("occupied");
					}
				}

				/**
				 * write to the table file to update the table status
				 */
				//write to update file
				writeTableFileForOverwrite();

				//add new order into array list
				orderArrList.add(order);

				/**
				 * write to the order file to add the new order
				 */
				//to write into file the details of the order items
				writeOrderFileForAppend(order);	

				break;

				//########################################### CASE 4 View Order ##################################################################
				/**
				 * this is our code for case 4 where our staff can view the order he created in case 3
				 * to view the order, he have to input the ID of the order he wants to view
				 * the order will be printed in the format
				 * "ID:		(Qty)Name:	"
				 */
			case 4:
				//to prevent it from reading multiple times and resulting in duplicate results in the file
				menuItemArrList.clear();
				setPackageArrList.clear();
				orderArrList.clear();


				//to get the updated menu items, set packages and orders by reading files
				readMenuItemFile(menuItemArr, menuItemArrList, reservationidCount);
				readSetPackageFile(setPackageArr, setPackageArrList, setpackidCount);
				readOrderFile(orderArr, orderArrList, orderidCount, menuItemArrList, setPackageArrList);

				///print all the orders to let the staff know how many orders there are
				System.out.println();
				System.out.println("List of orders: ");
				for(int i = 0; i< orderArrList.size(); i++){										
					System.out.println("ORDER ID: " + orderArrList.get(i).getOrderId());
				}

				System.out.println();

				System.out.println("Enter Order ID to view the order: ");
				int orderId = sc.nextInt();

				System.out.println();

				//print out all the menu items in the order 
				for(int i = 0; i< orderArrList.size(); i++){										
					if(orderArrList.get(i).getOrderId() == orderId){

						// to print in "ID:		(Qty)Name:	" format
						orderArrList.get(i).printAlacarteQty();
					}
				}

				System.out.println();

				//print out all the set package in the order 

				for(int i = 0; i< orderArrList.size(); i++){										
					if(orderArrList.get(i).getOrderId() == orderId){

						// to print in "ID:		(Qty)Name:	" format
						orderArrList.get(i).printPromotionalQty();
					}
				}

				break;
				//########################################### CASE 5 Add/Remove order item(s) to/from order ######################################

				/**
				 * this is our code for case 5 where the staff can add or remove any order item to or from the order 
				 * in case a customer changes his mind
				 * we will start by asking for the menuitemId/ setpackageId that the customer wants to add in to the order
				 * for remove case, we will first print out the current order items in the order
				 * so that it is easy to choose which order item to remove 
				 */
			case 5:	
				//to prevent it from reading multiple times and resulting in duplicate results in the file
				menuItemArrList.clear();
				setPackageArrList.clear();
				orderArrList.clear();

				//to get the updated menu items, set packages and orders by reading files
				readMenuItemFile(menuItemArr, menuItemArrList, reservationidCount);
				readSetPackageFile(setPackageArr, setPackageArrList, setpackidCount);
				readOrderFile(orderArr, orderArrList, orderidCount, menuItemArrList, setPackageArrList);



				int option = -1;

				do{
					System.out.println("Please select a choice: ");
					System.out.println("(1) Add order item");
					System.out.println("(2) Remove order item");

					option = sc.nextInt();

					switch(option){


					//add order item	
					/**
					 * to add an item into the order, start by entering the ID of the order that needs to be append
					 * then the staff can choose to input either a menuitem or a setpackage
					 * and input the ID of the item into the order
					 */
					case 1: 

						System.out.println();
						System.out.println("List of orders: ");
						for(int i = 0; i< orderArrList.size(); i++){										
							System.out.println("ORDER ID: " + orderArrList.get(i).getOrderId());
						}

						System.out.println();

						System.out.println("Enter ID of Order you would like to add: ");
						orderId = sc.nextInt();

						System.out.println("What order item do you want to add?");

						System.out.println("(1) Menu Item");
						System.out.println("(2) Set Package");

						int orderItem = sc.nextInt();

						if(orderItem == 1){
							System.out.println();
							System.out.println("List of menu items: ");
							for(int i = 0; i< menuItemArrList.size(); i++){										
								System.out.println(menuItemArrList.get(i).getItemId() + " : "+ menuItemArrList.get(i).getItemName());
							}

							System.out.println();
							System.out.println("Input order item ID to add: ");
							int orderItemId = sc.nextInt();	

							//get the correct order to insert the item		
							for(int k = 0; k< orderArrList.size(); k++){										
								if(orderArrList.get(k).getOrderId() == orderId){

									//get the item from menuItem to add in to order	
									for(int i = 0; i < menuItemArrList.size(); i++){	
										if(menuItemArrList.get(i).getItemId() == orderItemId){ 
											orderArrList.get(k).getMenuItemArr().add(menuItemArrList.get(i));
											System.out.println("Menu Item ID" + orderItemId + " (" + 
													menuItemArrList.get(i).getItemName() + ")" + " is added into order.");
										}
									}
								}
							}

							//write to update order file
							/**
							 * update the order which includes the new menu item in the order array list 
							 */
							writeOrderFileForOverwrite(orderArrList);
						}

						else if(orderItem == 2){

							System.out.println();
							System.out.println("List of Set Packages: ");
							for(int i = 0; i< setPackageArrList.size(); i++){										
								System.out.println(setPackageArrList.get(i).getSetPackId() + " : "+ setPackageArrList.get(i).getSetPackName());
							}

							System.out.println("Input order item ID to add: ");
							int orderItemId = sc.nextInt();	

							//get the correct order to insert the item		
							for(int k = 0; k< orderArrList.size(); k++){										
								if(orderArrList.get(k).getOrderId() == orderId){

									//get the item from set package to add in to order	
									for(int i = 0; i < setPackageArrList.size(); i++){	
										if(setPackageArrList.get(i).getSetPackId() == orderItemId){ 
											orderArrList.get(k).getSetPackageArr().add(setPackageArrList.get(i));
											System.out.println("Set Package ID" + orderItemId + " (" + 
													setPackageArrList.get(i).getSetPackName() + ")" + " is added into order.");
										}
									}
								}
							}
							/**
							 * update the order which includes the new set package in the order array list
							 */
							//write to update order file
							writeOrderFileForOverwrite(orderArrList);
						}

						else{
							System.out.println("Invalid choice! Please select again.");
							System.out.println("What order item do you want to remove?");

							System.out.println("(1) Menu Item");
							System.out.println("(2) Set Package");
							System.out.println();

						}

						option = -1;
						break;


						//remove order item	(menu item or set package)	
						/**
						 * to remove an item in the order, start by entering the ID of the order that needs to be append
						 * and prints out all the menu item that was in the order first
						 * so that the staff will not make a mistake in removing items that were not in the order
						 * then the staff can choose to input either a menuitem or a setpackage to be removed
						 * and input the ID of the item that he wants to remove
						 */
					case 2: 

						System.out.println();
						System.out.println("List of orders: ");
						for(int i = 0; i< orderArrList.size(); i++){										
							System.out.println("ORDER ID: " + orderArrList.get(i).getOrderId());
						}

						System.out.println();

						System.out.println("Enter ID of Order you would like to remove: ");
						orderId = sc.nextInt();

						System.out.println("What order item do you want to remove?");

						System.out.println("(1) Menu Item");
						System.out.println("(2) Set Package");

						orderItem = sc.nextInt();

						//remove menu item
						if(orderItem == 1){

							MenuItem menuItem = new MenuItem();
							int indexMenuItemArr = 0;
							int indexOrderArrList = 0;

							//print out all the menu items in the order 
							for(int i = 0; i< orderArrList.size(); i++){										
								if(orderArrList.get(i).getOrderId() == orderId){

									// to print in " ID: NAME: QTY: " format
									orderArrList.get(i).printAlacarteQty();
								}
							}

							System.out.println("Input order item ID to be removed: ");
							int orderItemId = sc.nextInt();

							for(int i = 0; i< orderArrList.size(); i++){
								if(orderArrList.get(i).getOrderId() == orderId){
									for(int k = 0 ; k < orderArrList.get(i).getMenuItemArr().size(); k++){
										if(orderArrList.get(i).getMenuItemArr().get(k).getItemId() == orderItemId){ 

											menuItem = orderArrList.get(i).getMenuItemArr().get(k);
											indexMenuItemArr = k;
											indexOrderArrList = i;
										}
									}
								}

							}

							System.out.println("Menu Item ID " + orderItemId + " (" + menuItem.getItemName() +
									")"+ " is removed.");	
							orderArrList.get(indexOrderArrList).getMenuItemArr().remove(indexMenuItemArr);

							/**
							 * update the order which has already removed a menu item in the order array list
							 */
							//write to update order file
							writeOrderFileForOverwrite(orderArrList);
						}

						//remove set package
						else if(orderItem == 2){

							SetPackage setPackage = new SetPackage();
							int indexSetPackageArr = 0;
							int indexOrderArrList = 0;

							//print out all the set package in the order 

							for(int i = 0; i< orderArrList.size(); i++){										
								if(orderArrList.get(i).getOrderId() == orderId){

									// to print in " ID: NAME: QTY: " format
									orderArrList.get(i).printPromotionalQty();
								}
							}

							System.out.println("Input order item ID to be removed: ");
							int orderItemId = sc.nextInt();


							for(int i = 0; i< orderArrList.size(); i++){
								if(orderArrList.get(i).getOrderId() == orderId){
									for(int k = 0 ; k < orderArrList.get(i).getSetPackageArr().size(); k++){
										if(orderArrList.get(i).getSetPackageArr().get(k).getSetPackId() == orderItemId){ 

											setPackage = orderArrList.get(i).getSetPackageArr().get(k);
											indexSetPackageArr = k;
											indexOrderArrList = i;
										}
									}
								}
							}

							System.out.println("Set Package ID " + orderItemId + " (" + setPackage.getSetPackName() +
									")"+ " is removed.");
							orderArrList.get(indexOrderArrList).getSetPackageArr().remove(indexSetPackageArr);

							/**
							 * update the order which has already removed a promotional item in the order array list
							 */
							//write to update order file
							writeOrderFileForOverwrite(orderArrList);

						}

						else{
							System.out.println("Invalid choice! Please select again.");
							System.out.println("What order item do you want to remove?");

							System.out.println("(1) Menu Item");
							System.out.println("(2) Set Package");
							System.out.println();

						}

						option = -1;
						break;

					default:System.out.println("Invalid choice! Please select again.");
					System.out.println("Please select a choice: ");
					System.out.println("(1) Add order item");
					System.out.println("(2) Remove order item");

					option = sc.nextInt();
					}	

				} while(option != -1);

				break;


				//########################################### CASE 6 Create Reservation Booking ##############################################
				//assumptions: only can create reservation 2 days in advance
				//			if the table is reserved for the day, it cant be reserved on the same day despite time difference
				/**
				 *this is our code for case 6 
				 *when a customer calls in to book a table at our restaurant, he needs to provide details such as the number
				 *of people coming, reservation date, time, customer's contact number and name
				 *a reservation will be identified by the customer's contact number
				 *once a reservation is confirmed, a table is assigned to the customer on the day of reservation
				 *the first table that is available will be assigned to the customer
				 *our assumptions: -only can create reservation 2 days in advance
				 *					-if the table is reserved for the day, it cannot be reserved on the same day despite time difference
				 */

			case 6:
				
				/**
				 * read the table file to update table
				 */
				readTableFile(table1Arr, setpackidCount);

				SimpleDateFormat SDFFULL = new SimpleDateFormat("dd-MM-yyyy HH:mm");


				/**
				 * read reservation file to update reservation
				 */
				readReservationFile(reservationArr, reservationidCount, SDFFULL);

				updateTableStatus();

				System.out.println("\nCreate reservation booking");
				int Pax;
				Reservation reservation = new Reservation(); 

				//set reservation id
				reservation.setrId(reservationidCount +1);

				System.out.println("Input number of people coming: ");

				String flagint = "false";

				while(flagint.equals("false")){
					while(!sc.hasNextInt() ){

						//if(!sc.hasNextInt()){
						System.out.println("That's not a number!");
						System.out.println("Input number of people coming: ");
						sc.next(); // this is important!
						flagint = "true";
						
					}
					while(sc.nextInt()>10){
						System.out.println("Please do a separate reservation for pax above 10");
						System.out.println("Input number of people coming: ");
						sc.next();
						flagint = "true";
					}
			
					flagint = "true";

				}
				Pax = sc.nextInt();
				Calendar fullReservationCalendar = Calendar.getInstance();


				try{
					do{
						String input,str1="",reservationTime;
						System.out.println("Please enter reservation date: (DD-MM-YYYY)");
						sc.nextLine();
						input = sc.nextLine();	
						
	
						while (!(input.matches("\\d{1,2}-\\d{1,2}-\\d{4}"))){						
							str1="Please enter in correct format";	
							System.out.println(str1);
							System.out.println("Please enter reservation date: (DD-MM-YYYY)"); 
							input = sc.nextLine();
						}
						
						System.out.println("Please enter reservation time: (HH:MM)"); 
						reservationTime = sc.next();

						while (!(reservationTime.matches("\\d{1,2}:\\d{1,2}"))){
							str1="Please enter in correct format";
							System.out.println(str1);
							System.out.println("Please enter reservation time: (HH:MM)");  						
							reservationTime = sc.next();
						}

						String fullString = input + " " + reservationTime; //SDFFULL
						Date fullDate = SDFFULL.parse(fullString); //change string into date object
						fullReservationCalendar.setTime(fullDate); //change into calender



					}while(!Reservation.DateValidator(fullReservationCalendar));

					//update reservation status first
					for (int b=0; b<table1ArrList.size(); b++){		
						for (int i = 0; i < table1ArrList.get(b).getReservation().size(); i++) {
							table1ArrList.get(b).getReservation().get(i).updateExpireStatus();
						}
					}



					//search for available tables and assign to a new arraylist -> availableTableArrList
					availableTableArrList = searchAvailableTable(Pax , fullReservationCalendar); //pax and fullreservationcalender is user input

					//print to see
					if (availableTableArrList.size()!= 0){


						int tableNum = availableTableArrList.get(0).getTableNum();

						System.out.println("Table number " + tableNum + " is selected");
						searchTable(tableNum).createReservation(reservationidCount+1, Pax, fullReservationCalendar);	  
						System.out.println("Reservation created for table number " + tableNum);

					}

					else
						System.out.println("Restaurant fully booked at this time and date");

				}
				catch (ParseException e) {
					// Invalid date was entered
				}

				//create new res
				/**
				 * write into reservation and table file to update the new reservation 
				 */
				writeReservationFileForOverwrite(SDFFULL);
				writeTableFileForOverwrite();


				break;


				//########################################### CASE 7 Check/Remove reservation booking ##############################################	

				/**
				 * this is our code for case 7 where the staff can either check or remove the reservation booking
				 * 
				 * when he choose to check booking, details of the customer (Name, Phone Number, Number of person coming,
				 * Date of reservation, Table Number reserved, and whether the customer have arrived) will be shown when
				 * he inputs the contact number
				 * 
				 * whereas if he choose to remove reservation booking, then the list of reservations with its details will be printed
				 * and the staff can input the phone number to remove the reservation
				 * and a new list of reservations will be printed out
				 */
				
			case 7:
				
				//BufferedReader reservationbr;
				//read table file must be before read resv file
				//bec reservation obj is inside table
				readTableFile(table1Arr, setpackidCount);
				
				SDFFULL = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				readReservationFile(reservationArr,reservationidCount,SDFFULL);


				System.out.println("(1) Check Reservation");
				System.out.println("(2) Remove reservation booking");

				choice = sc.nextInt();

				if (choice == 1){

					System.out.println("Enter reservation contact number : ");
					int contactNum = sc.nextInt();
					
					
					//print out current reservation lists
					System.out.println();
					System.out.println("Current reservations: ");
					for(int i = 0; i< reservationArrList.size(); i++){	
						System.out.println(reservationArrList.size());
						if (employeeArrList.get(i).getJobTitle().equals("Manager") || employeeArrList.get(i).getJobTitle().equals("Server")){
							System.out.println("ID: " + employeeArrList.get(i).getEmployeeId() + " (" + employeeArrList.get(i).getEmployeeName() + ")");
						}
					}
					System.out.println();

					//update reservation status first
					for (int b=0; b<table1ArrList.size(); b++){		
						for (int i = 0; i < table1ArrList.get(b).getReservation().size(); i++) {
							table1ArrList.get(b).getReservation().get(i).updateExpireStatus();
						}
					}

					for (int b=0; b<table1ArrList.size(); b++){		
						for (int i = 0; i < table1ArrList.get(b).getReservation().size(); i++) {
							if (contactNum == table1ArrList.get(b).getReservation().get(i).getRPhone()){
								table1ArrList.get(b).getReservation().get(i).printReservation();
								break;
							}
						}
					}
				}
				
				boolean flag = false;

				if(choice == 2){

					System.out.println("List of reservations(before):");
					for (int b=0; b<table1ArrList.size(); b++){		
						for (int i = 0; i < table1ArrList.get(b).getReservation().size(); i++) {
							table1ArrList.get(b).getReservation().get(i).printReservation();
						}
					}

					System.out.println("Enter reservation contact number : ");
					int contactNum = sc.nextInt();

					for (int b=0; b<table1ArrList.size(); b++){		
						for (int i = 0; i < table1ArrList.get(b).getReservation().size(); i++) {
							if (contactNum == table1ArrList.get(b).getReservation().get(i).getRPhone()){
								table1ArrList.get(b).getReservation().remove(i);
								flag = true;
								break;
							}
						}
					}

					System.out.println();
					System.out.println("----------------------------");
					System.out.println("List of reservations(after):");
					for (int b=0; b<table1ArrList.size(); b++){		
						for (int i = 0; i < table1ArrList.get(b).getReservation().size(); i++) {
							table1ArrList.get(b).getReservation().get(i).printReservation();
						}
					}

					if (flag)
						System.out.println("Reservation successfully removed!");
					else
						System.out.println("this contact number is not found in reservation");

				}

				//create new res
				/**
				 * write to the reservation file to update the removed reservations
				 */
				writeReservationFileForOverwrite(SDFFULL);


				break;


				//##############################################case 8: check table availability############################################

				/**
				 * this is our code for case 8 where we will check the table availability 
				 * by printing out the table details in table1ArrList for all 30 tables
				 * after reading the table and reservation files 
				 */

			case 8:

				SDFFULL = new SimpleDateFormat("dd-MM-yyyy HH:mm");

				/**
				 * read to update the table and reservation files 
				 */
				readTableFile(table1Arr, setpackidCount);
				readReservationFile(reservationArr,reservationidCount,SDFFULL);

				System.out.println("\n********************** Table Availability **********************");
				for (int k = 0; k < table1ArrList.size(); k++) {
					table1ArrList.get(k).printTable();

				}
				break;

				//##############################################case 9: print order invoice############################################		
				/**
				 * this is our code for case 9 where the staff can print an invoice of an order by inputting the order ID 
				 * the invoice will include the restaurant details, ID of employee who created the order, table number, 
				 * the items the customer ordered, GST and service charges and total price
				 * also once an invoice is printed, the table is set to "vacated"
				 */
			case 9:

				Order orderInvoice = null;

				//to prevent it from reading multiple times and resulting in duplicate results in the file
				menuItemArrList.clear();
				setPackageArrList.clear();
				orderArrList.clear();
				table1ArrList.clear();

				//to get the updated menu items, set packages and orders by reading files
				readMenuItemFile(menuItemArr, menuItemArrList, reservationidCount);
				readSetPackageFile(setPackageArr, setPackageArrList, setpackidCount);
				readOrderFile(orderArr, orderArrList, orderidCount, menuItemArrList, setPackageArrList);
				readTableFile(table1Arr, setpackidCount);


				//print out all the orders for the employee to choose
				System.out.println();
				System.out.println("List of orders: ");
				for(int i = 0; i< orderArrList.size(); i++){										
					System.out.println("ORDER ID: " + orderArrList.get(i).getOrderId());
				}
				System.out.println();

				System.out.println("Enter Order ID to print order invoice:  ");
				int order_id = sc.nextInt();

				//print the invoice for the order 
				for(int i=0;i<orderArrList.size();i++){
					if(orderArrList.get(i).getOrderId() == order_id){
						int index = i;
						orderArrList.get(i).printOrderInvoice(orderArrList, index);
						orderInvoice = orderArrList.get(i);
					}
				}

				//set table status to vacated

				for(int i = 0; i<table1ArrList.size(); i++){
					if(table1ArrList.get(i).getTableNum() == orderInvoice.getTableNum()){
						table1ArrList.get(i).setTableStatus("vacated");
					}
				}
				//write to update file
				writeTableFileForOverwrite();


				break;


				//############################################## CASE 10: Print sale revenue report by period ############################################		

				/**
				 * this is our code for case 10 where we will read the menuitemfile, setpackage file, sale revenue report array file to get the updated menu itmes,
				 * set packages and orders
				 * then we will ask the user which month of sales revenue report he wants to print by inputting the month 
				 * and it will print the sales revenue for that month which includes 
				 * menuitemID, qty, name of the item for that month
				 * setpackageID, qty, name of the item for that month
				 * and value of the items where value = price of item x qty
				 * and lastly, it will print the total revenue as well
				 */
			case 10:

				date = null;

				// to prevent it from reading multiple times and resulting in
				// duplicate results in the file
				menuItemArrList.clear();
				setPackageArrList.clear();
				orderArrList.clear();
				saleRevReportArrList.clear();

				// to get the updated menu items, set packages and orders by
				// reading files
				readMenuItemFile(menuItemArr, menuItemArrList, reservationidCount);
				readSetPackageFile(setPackageArr, setPackageArrList, setpackidCount);
				readOrderFile(orderArr, orderArrList, orderidCount, menuItemArrList, setPackageArrList);

				//
				writeSaleRevReport(orderArrList);

				System.out.println("Please input the month of the sales revenue report (eg.input 11 for month of November): ");
				int month = sc.nextInt();

				readSaleRevReport(reportArr, saleRevReportArrList, menuItemArrList, setPackageArrList);

				//print sale revenue report

				for(int i = 0; i<saleRevReportArrList.size(); i++){
					if(saleRevReportArrList.get(i).getMonth() == month){

						saleRevReportArrList.get(i).printSaleRevReport(month, saleRevReportArrList);
					}

				}
				break;


				///////////////////////////CASE 11: REGISTER/UPDATE/REMOVE EMPLOYEE/////////////////////////////////////////
			case 11:

				//System.out.println(" employee name | employee id | gender | job title ;
				//when printing the menu items available, have to call the file reader again to update arraylist
				//can doc this later 

				idCount = 0;
				//to prevent it from reading multiple times and resulting in duplicate results in the file
				employeeArrList.clear();

				//read file to get the menu items stored in the file and to update the array list as well as idcount
				idCount = readEmployeeFile(employeeArr, employeeArrList, idCount);

				System.out.println("(1) Register employee");
				System.out.println("(2) Update employee details");
				System.out.println("(3) Remove employee details");

				choice = sc.nextInt();

				////////////////////////////////////////////////////////REGISTER EMPLOYEE  //////////////////////////////////////////////////////////////


				/**
				 * Choice 1 indicates if employee is new and chooses to register himself int the system 
				 */
				if (choice == 1){

					String employeeName;
					String employeeGender;
					String jobTitle;					


					Employee employee = new Employee(); //create obj

					//set employee id
					employee.setEmployeeId(idCount + 1);

					//set name
					System.out.println("Input employee name: ");

					dummy = sc.nextLine();

					employeeName = sc.nextLine(); //takes in user's employee name input
					employee.setEmployeeName(employeeName); // setter method set's employee's name 

					//set employee's gender 
					System.out.println("Please input your gender (M/F): ");
					employeeGender = sc.nextLine();
					employee.setEmployeeGender(employeeGender.toUpperCase());

					System.out.println("Please input your job title: ");
					System.out.println("(1) Manager");
					System.out.println("(2) Server");
					System.out.println("(3) Chef");
					System.out.println("(4) Cashier");
					System.out.println("(5) Janitor");
					choice = sc.nextInt();

					do {
						switch(choice){

						case 1: employee.setJobTitle("Manager");
						choice = -1;

						break;

						case 2: employee.setJobTitle("Server");
						choice = -1;
						break;

						case 3: employee.setJobTitle("Chef");
						choice = -1;
						break;

						case 4: employee.setJobTitle("Cashier");
						choice = -1;
						break;

						case 5: employee.setJobTitle("Janitor");
						choice = -1;
						break;

						default: System.out.println("Invalid choice, please select again. \n");

						}

						if (employee.getEmployeeName() != null){
							choice = -1;
						}
					}

					while(choice != -1);

					//add Employee into array list
					employeeArrList.add(employee);

					//to write into the file the details of the employee
					writeEmployeeFileForAppend(employee);

					System.out.println("New employee " + employee.getEmployeeName() + " with ID no: " + employee.getEmployeeId() + " is registered.");
				}

				////////////////////////////////////////////////////////UPDATE EMPLOYEE DETAILS //////////////////////////////////////////////////////////////			
				//update employee details
				//creating but overwrite the whole thing 

				/**
				 * Choice 2 indicates if the employee chooses to update the employee details 
				 */
				else if(choice == 2){


					System.out.println("Please enter your employee ID to update your details: ");
					employeeId = sc.nextInt();


					//check if the employee id in each Employee object of the array list is the same as the employee id inputed 
					//allow update only if employee id is equal

					Employee employee = new Employee(); //creates a employee 

					for(int i=0; i < employeeArrList.size(); i++){ // iterates through the entire employees list 
						if(employeeArrList.get(i).getEmployeeId() == employeeId){ // if employee id is found inside the list 

							employee = employeeArrList.get(i); //let the employee equal to the one in the list 
						}
					}
					//System.out.println("ITEM NAME + ITEM ID" + " " + menuItem.getItemName() + " + " + menuItem.getItemId()); 

					System.out.println("Which detail would you like to update for " + employee.getEmployeeName() + "? ");
					System.out.println("(1)Update Name");
					System.out.println("(2)Update Gender");
					System.out.println("(3)Update Job Title");
					System.out.println("(4)Exit");

					//choose what to update
					choice = sc.nextInt();

					while(choice != 4){
						dummy = sc.nextLine();
						switch(choice){

						//update name
						case 1: System.out.println("Update new name " + employee.getEmployeeName() + " : ");
						String newName = sc.nextLine();
						employee.setEmployeeName(newName);
						System.out.println("Updated employee name: " + newName);

						writeEmployeeFileForOverwrite(employeeArrList);

						break;	

						//update type
						case 2: 
							System.out.println("Please enter the gender in which you would like to update to (M/F): ");
							String newGender = sc.nextLine();
							employee.setEmployeeGender(newGender);;
							System.out.println("Updated employee gender:" + newGender.toUpperCase());

							writeEmployeeFileForOverwrite(employeeArrList);

							break;
							//update description
						case 3: 
							System.out.println("Please input your new job title for " + employee.getEmployeeName() + " : ");
							System.out.println("(1) Manager");
							System.out.println("(2) Server");
							System.out.println("(3) Chef");
							System.out.println("(4) Cashier");
							System.out.println("(5) Janitor");
							choice = sc.nextInt();

							do {
								switch(choice){

								case 1: employee.setJobTitle("Manager");
								choice = -1;

								break;

								case 2: employee.setJobTitle("Server");
								choice = -1;
								break;

								case 3: employee.setJobTitle("Chef");
								choice = -1;
								break;

								case 4: employee.setJobTitle("Cashier");
								choice = -1;
								break;

								case 5: employee.setJobTitle("Janitor");
								choice = -1;
								break;

								default: System.out.println("Invalid choice, please select again. \n");

								}

								if (employee.getEmployeeName() != null){
									choice = -1;
								}
							}while(choice != -1);

							System.out.println("Job title for " + employee.getEmployeeName() + " updated to: " + employee.getJobTitle());
							writeEmployeeFileForOverwrite(employeeArrList);

							break;

						case 4: 
							break;


						default: System.out.println();
						System.out.println("Invalid choice, please select again. \n");

						System.out.println("(1)Update name");
						System.out.println("(2)Update type");
						System.out.println("(3)Update description");
						System.out.println("(4)Update price");
						System.out.println("(5)Update recommendation");
						System.out.println("(6)Update availability (L/D/L&D)");
						System.out.println("(7)Exit");

						choice = sc.nextInt();
						}
						System.out.println();
						System.out.println("Which employee detail would you like to update?");
						System.out.println("(1)Update Name");
						System.out.println("(2)Update Gender");
						System.out.println("(3)Update Job Title");
						System.out.println("(4)Exit");

						//choose what to update
						choice = sc.nextInt();							
					}
				}
				////////////////////////////////////////////////////////REMOVE EMPLOYEE DETAILS //////////////////////////////////////////////////////////////	
				/**
				 * choice 3 indicates when employee data is being removed from database due to end of contract, retirement etc
				 */


				else if(choice == 3){				

					//print out all current employees
					System.out.println();
					System.out.println("List of employees: ");
					for(int i = 0; i< employeeArrList.size(); i++){			
						System.out.println("ID: " + employeeArrList.get(i).getEmployeeId() + " (" + employeeArrList.get(i).getEmployeeName() + ")");
					}
				
				System.out.println();

				System.out.println("Input the employee ID to be removed: ");
				employeeId = sc.nextInt();

				//check if the employee id in each MenuItem object of the arraylist is the same as the item id inputed 
				//allow delete only if item id is equal

				for(int i=0; i < employeeArrList.size(); i++){
					if(employeeArrList.get(i).getEmployeeId() == employeeId){

						//remove according to index of menuItemArrList
						System.out.println("Employee ID no." + employeeArrList.get(i).getEmployeeId() +  " has been removed.");
						employeeArrList.remove(i);		
					}
				}
				writeEmployeeFileForOverwrite(employeeArrList);
			}

			break;


			/////////////////////////////////////////////(12) EXIT/////////////////////////////////////////////
				
			/**
			 * break out of switch case	
			 */
			case 12:
				System.out.println("Thank you for using RRPSS! Bye!");
				break;

			default:
				System.out.println("Invalid choice! Please select again.");
				System.out.println();
				System.out.println("(1) Create/Update/Remove menu item");
				System.out.println("(2) Create/Update/Remove promotion");
				System.out.println("(3) Create order");
				System.out.println("(4) View order");
				System.out.println("(5) Add/Remove order item(s) to/from order");
				System.out.println("(6) Create reservation booking");
				System.out.println("(7) Check/Remove reservation booking");
				System.out.println("(8) Check table availablity");
				System.out.println("(9) Print order invoice");
				System.out.println("(10) Print sale revenue report by period");
				System.out.println("(11) Register/Update/Remove employee");
				System.out.println("(12) Exit");
				System.out.println();

				System.out.println("Enter your choice: ");
				choice = sc.nextInt();


			}//switch closing for 12 cases


		}while(choice != 12);//looping the switch cases if wrong insertion

	}//closing for declaring main class


////////////////////////////////////////////////////////WRITE FILES//////////////////////////////////////////////////////////////////////
	
	/**
	 * this method is used to write a file; to write in all the attributes of menuitems
	 * (Id, name, type, desc, price, recommendation, whether its lunch or dinner)
	 * "menuItems" is used to store the items in the file
	 * 
	 * @param menuItem  	newly created menu item object
	 */
	private static void writeMenuItemFileForAppend(MenuItem menuItem)
	{
		//to write into the file the details of the menu items
		try(FileWriter fw = new FileWriter("menuItems", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){

			out.print(menuItem.getItemId() + "|");
			out.print(menuItem.getItemName() + "|");
			out.print(menuItem.getItemType() + "|");
			out.print(menuItem.getItemDesc() + "|");
			out.printf("%.2f%1s", menuItem.getItemPrice() , "|");
			out.print(menuItem.getItemRecom() + "|");
			out.print(menuItem.getLunchDinner());
			out.println();

			out.close();
			bw.close();
			fw.close();
		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}
	}

	/**
	 * this method is used to overwrite the menu items that were stored before 
	 * the overwrite items are stored in "menuItems"
	 *  
	 * @param menuItemArrList	is used to store the different menuItems object
	 */
	private static void writeMenuItemFileForOverwrite(List<MenuItem> menuItemArrList)
	{
		//to write into the file the details of the menu items
		//append to file: true, overwrite file: false
		try(FileWriter fw = new FileWriter("menuItems", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){
			for(int k = 0; k<menuItemArrList.size(); k++){
				out.print(menuItemArrList.get(k).getItemId() + "|");
				out.print(menuItemArrList.get(k).getItemName() + "|");
				out.print(menuItemArrList.get(k).getItemType() + "|");
				out.print(menuItemArrList.get(k).getItemDesc() + "|");
				out.printf("%.2f%1s", menuItemArrList.get(k).getItemPrice() , "|");
				out.print(menuItemArrList.get(k).getItemRecom() + "|");
				out.print(menuItemArrList.get(k).getLunchDinner());
				out.println();
			} 	
			out.close();
			bw.close();
			fw.close();
		}
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}
		finally{
			System.out.println("MenuItems has successfully updated!");
		}
	}

	/**
	 * 
	 * this method is used to write a file; to write in all the attributes of setpackage
	 * (Id, name, type, desc, price, whether its lunch or dinner)
	 * "setpackage" is used to store the items in the file
	 * 
	 * @param setPackage	is a newly created setPackage object
	 */

	private static void writeSetPackageFileForAppend(SetPackage setPackage)
	{
		//to write into the file the details of the set package
		//append to file: true, overwrite file: false
		try(FileWriter fw = new FileWriter("setpackage", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){

			out.print(setPackage.getSetPackId() + "|");
			out.print(setPackage.getSetPackName() + "|");
			out.printf("%.2f%1s", setPackage.getSetPackPrice() , "|");
			out.print(setPackage.getSetPackDesc() + "|");
			out.print(setPackage.getSetPackLunchDinner() + "|");

			//maincourse id
			out.print(setPackage.menuItemArr.get(0).getItemId() + "|");
			//appetizer id
			out.print(setPackage.menuItemArr.get(1).getItemId() + "|");
			//beverage id
			out.print(setPackage.menuItemArr.get(2).getItemId());
			out.println();

			out.close();
			bw.close();
			fw.close();
		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}
	}

	/**
	 * this method is used to overwrite the set packages that were stored in "setpackage" before
	 * 
	 * @param setPackageArrList is an array list used to store all the set packages objects
	 */

	private static void writeSetPackageFileForOverwrite(List<SetPackage> setPackageArrList){
		//to write into the file the details of the set package
		//append to file: true, overwrite file: false
		try(FileWriter fw = new FileWriter("setpackage", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){
			for(int i = 0; i<setPackageArrList.size(); i++){
				out.print(setPackageArrList.get(i).getSetPackId() + "|");
				out.print(setPackageArrList.get(i).getSetPackName() + "|");
				out.printf("%.2f%1s", setPackageArrList.get(i).getSetPackPrice() , "|");
				out.print(setPackageArrList.get(i).getSetPackDesc() + "|");
				out.print(setPackageArrList.get(i).getSetPackLunchDinner() + "|");
				out.print(setPackageArrList.get(i).getmaincourseId() + "|");
				out.print(setPackageArrList.get(i).getappetizerId() + "|");
				out.print(setPackageArrList.get(i).getbeverageId());
				out.println();
			}
			out.close();
			bw.close();
			fw.close();
		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}
	}

	/**
	 * this method is used to write a file; to write in all the orders object that were created
	 * "order" is used to store the orders in the file
	 * 
	 * in an order, there can have menu item object, set package object or both
	 * hence, there is a need to create 2 temp arrays to store the menu item and set package respectively
	 * 
	 * @param order 	is a newly created order object
	 */
	private static void writeOrderFileForAppend(Order order){

		//should look like this (orderId|employeeId|tableNum|alacarte|id|qty|id|qty|promotional|id|qty)
		//have to write qty to know how many MenuItem/SetPackage objects to add into arraylist when reading file

		ArrayList<MenuItem> menuItemArrTemp = new ArrayList<MenuItem>();
		ArrayList<SetPackage>setPackageArrTemp = new ArrayList<SetPackage>();

		try(FileWriter fw = new FileWriter("order", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){

			out.print(order.getOrderId() + "|");
			out.print(order.getEmployeeId()+ "|");
			out.print(order.getTableNum() + "|");
			out.print(order.getMonth() + "|");


			//write alacarte items
			out.print("alacarte|");

			for (int i = 0; i<order.getMenuItemArr().size(); i++)
			{
				menuItemArrTemp.add(order.getMenuItemArr().get(i));
			}

			while (menuItemArrTemp.size() > 0){

				int qty = 0;
				int menuItemId = menuItemArrTemp.get(0).getItemId();

				// if there is only 1 item in the order, print without looping (last id and qty to write to file)
				if (menuItemArrTemp.size() == 1)
				{
					qty++;

					//write into file ( id | qty |  id | qty)
					out.print(menuItemId + "|");
					out.print(qty + "|");

					menuItemArrTemp.clear();
				}

				else 
				{
					for (int i = 0; i < menuItemArrTemp.size(); i++)
					{
						// get qty of the specific itemId	
						if (menuItemArrTemp.get(i).getItemId() == menuItemId)
						{
							menuItemArrTemp.remove(i);
							qty++;
							i--; //this ensures that the first element in temp will always have i = 0
						}
					}

					//write into file ( id | qty |  id | qty)
					out.print(menuItemId + "|");
					out.print(qty + "|");

				}
			}

			//write promotional items
			out.print("promotional|");

			for (int i = 0; i<order.getSetPackageArr().size(); i++)
			{
				setPackageArrTemp.add(order.getSetPackageArr().get(i));
			}

			while (setPackageArrTemp.size() > 0){

				int qty = 0;
				int setPackId = setPackageArrTemp.get(0).getSetPackId();

				// if there is only 1 item in the order, print without looping
				if (setPackageArrTemp.size() == 1)
				{
					qty++;

					//write into file ( id | qty |  id | qty)
					out.print(setPackId + "|");
					out.print(qty + "|");	

					setPackageArrTemp.clear();
				}

				else 
				{
					for (int i = 0; i < setPackageArrTemp.size(); i++)
					{
						// get qty of the specific itemId	
						if (setPackageArrTemp.get(i).getSetPackId() == setPackId)
						{
							setPackageArrTemp.remove(i);
							qty++;
							i--; //this ensures that the first element in temp will always have i = 0
						}
					}
					//write into file ( id | qty |  id | qty)
					out.print(setPackId + "|");
					out.print(qty + "|");
				}
			}

			out.println();
			out.close();
			bw.close();
			fw.close();
		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		} 

	}

	/**
	 * 
	 * this method is used to overwrite the orders that were stored before in "order"
	 * 
	 * in an order, there can have menu item object, set package object or both
	 * hence, there is a need to create 2 temp arrays to store the menu item and set package respectively
	 * 
	 * @param orderArrList	is used to store all the order objects
	 */
	private static void writeOrderFileForOverwrite(List<Order> orderArrList){

		//should look like this (orderId|employeeId|tableNum|alacarte|id|qty|id|qty|promotional|id|qty)
		//have to write qty to know how many MenuItem/SetPackage objects to add into arraylist when reading file

		ArrayList<MenuItem> menuItemArrTemp = new ArrayList<MenuItem>();
		ArrayList<SetPackage>setPackageArrTemp = new ArrayList<SetPackage>();

		try(FileWriter fw = new FileWriter("order", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){

			for(int s = 0; s<orderArrList.size(); s++){

				out.print(orderArrList.get(s).getOrderId() + "|");
				out.print(orderArrList.get(s).getEmployeeId()+ "|");
				out.print(orderArrList.get(s).getTableNum() + "|");
				out.print(orderArrList.get(s).getMonth() + "|");


				//write alacarte items
				out.print("alacarte|");

				for (int i = 0; i<orderArrList.get(s).getMenuItemArr().size(); i++)
				{
					menuItemArrTemp.add(orderArrList.get(s).getMenuItemArr().get(i));
				}

				while (menuItemArrTemp.size() > 0){

					int qty = 0;
					int menuItemId = menuItemArrTemp.get(0).getItemId();

					// if there is only 1 item in the order, print without looping (last id and qty to write to file)
					if (menuItemArrTemp.size() == 1)
					{
						qty++;

						//write into file ( id | qty |  id | qty)
						out.print(menuItemId + "|");
						out.print(qty + "|");

						menuItemArrTemp.clear();
					}

					else 
					{
						for (int i = 0; i < menuItemArrTemp.size(); i++)
						{
							// get qty of the specific itemId	
							if (menuItemArrTemp.get(i).getItemId() == menuItemId)
							{
								menuItemArrTemp.remove(i);
								qty++;
								i--; //this ensures that the first element in temp will always have i = 0
							}
						}

						//write into file ( id | qty |  id | qty)
						out.print(menuItemId + "|");
						out.print(qty + "|");

					}
				}

				//write promotional items
				out.print("promotional|");

				for (int i = 0; i<orderArrList.get(s).getSetPackageArr().size(); i++)
				{
					setPackageArrTemp.add(orderArrList.get(s).getSetPackageArr().get(i));
				}

				while (setPackageArrTemp.size() > 0){

					int qty = 0;
					int setPackId = setPackageArrTemp.get(0).getSetPackId();

					// if there is only 1 item in the order, print without looping
					if (setPackageArrTemp.size() == 1)
					{
						qty++;

						//write into file ( id | qty |  id | qty)
						out.print(setPackId + "|");
						out.print(qty + "|");	

						setPackageArrTemp.clear();
					}

					else 
					{
						for (int i = 0; i < setPackageArrTemp.size(); i++)
						{
							// get qty of the specific itemId	
							if (setPackageArrTemp.get(i).getSetPackId() == setPackId)
							{
								setPackageArrTemp.remove(i);
								qty++;
								i--; //this ensures that the first element in temp will always have i = 0
							}
						}
						//write into file ( id | qty |  id | qty)
						out.print(setPackId + "|");
						out.print(qty + "|");
					}
				}

				out.println();
			}

			out.close();
			bw.close();
			fw.close();

		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		} 

	}

	/**
	 * this is a write file to write the sale revenue reports into "saleRevReport"
	 * 
	 * a sale revenue reports need to include multiple order objects and in an order, there are
	 * menu item and set package involved. hence, 3 array list are used (orderArrList, menuItemArrList, setPackageArrList)
	 * 
	 * @param orderArrList		is the array list used to store all the order objects
	 * @param menuItemArrList	is the array list used to store all the menu item objects
	 * @param setPackageArrList	is the array list used to store all the set Package objects
	 */
	private static void writeSaleRevReport(List<Order> orderArrList, List<MenuItem> menuItemArrList,
			List<SetPackage> setPackageArrList) {

		ArrayList<MenuItem> menuItemArrTemp = new ArrayList<MenuItem>();
		ArrayList<SetPackage> setPackageArrTemp = new ArrayList<SetPackage>();
		ArrayList<Integer> idTemp = new ArrayList<Integer>();
		ArrayList<Integer> qtyTemp = new ArrayList<Integer>();
		ArrayList<Integer> idTemp2 = new ArrayList<Integer>();
		ArrayList<Integer> qtyTemp2 = new ArrayList<Integer>();

		int month = 1;

		try (FileWriter fw = new FileWriter("saleRevReport", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			// need to edit codes

			while (month < 13) {

				for (int q = 0; q < orderArrList.size(); q++) {

					if (orderArrList.get(q).getMonth() == month) {

						// for menu item

						for (int i = 0; i < orderArrList.get(q).getMenuItemArr().size(); i++) {
							menuItemArrTemp.add(orderArrList.get(q).getMenuItemArr().get(i));
						}

						int qty = 0;
						int menuItemId = menuItemArrTemp.get(0).getItemId();

						if (menuItemArrTemp.size() == 1 && !idTemp.contains(menuItemId)) {
							qty++;

							// add id and qty into temp arr
							idTemp.add(menuItemId);
							qtyTemp.add(qty);

							menuItemArrTemp.clear();
						}

						else {
							for (int i = 0; i < menuItemArrTemp.size(); i++) {
								// get qty of the specific itemId
								if (menuItemArrTemp.get(i).getItemId() == menuItemId) {
									menuItemArrTemp.remove(i);
									qty++;
									i--; // this ensures that the first element
									// in temp will always have i = 0
								}
							}

							// store id and qty in temp array list
							if (!idTemp.contains(menuItemId)) {
								System.out.println("EEEEEE " + menuItemId);
								idTemp.add(menuItemId);
								qtyTemp.add(qty);
							} else {
								int index = idTemp.indexOf(menuItemId);
								int existingQty = qtyTemp.get(index);
								existingQty += qty;
								qtyTemp.set(index, existingQty);
							}
						}
					}
				}

				month++;
			}

			month = 1;

			while (month < 13) {

				for (int q = 0; q < orderArrList.size(); q++) {

					if (orderArrList.get(q).getMonth() == month) {

						// for set package

						for (int i = 0; i < orderArrList.get(q).getSetPackageArr().size(); i++) {
							setPackageArrTemp.add(orderArrList.get(q).getSetPackageArr().get(i));
						}

						int qty = 0;
						int setPackId = setPackageArrTemp.get(0).getSetPackId();

						if (setPackageArrTemp.size() == 1 && !idTemp2.contains(setPackId)) {
							qty++;

							// add id and qty into temp arr
							idTemp2.add(setPackId);
							qtyTemp2.add(qty);

							setPackageArrTemp.clear();
						}

						else {
							for (int i = 0; i < setPackageArrTemp.size(); i++) {
								// get qty of the specific itemId
								if (setPackageArrTemp.get(i).getSetPackId() == setPackId) {
									setPackageArrTemp.remove(i);
									qty++;
									i--; // this ensures that the first element
									// in temp will always have i = 0
								}
							}

							if (!idTemp2.contains(setPackId)) {
								idTemp2.add(setPackId);
								qtyTemp2.add(qty);
							} else {
								int index = idTemp2.indexOf(setPackId);
								int existingQty = qtyTemp2.get(index);
								existingQty += qty;
								qtyTemp2.set(index, existingQty);
							}
						}
					}
				}
				month++;
			}

			// write menu items with its qty
			out.print("alacarte|");

			for (int n = 0; n < idTemp.size(); n++) {
				out.print(idTemp.get(n) + "|");
				out.print(qtyTemp.get(n) + "|");
			}

			out.print("promotional|");

			for (int n = 0; n < idTemp2.size(); n++) {
				out.print(idTemp2.get(n) + "|");
				out.print(qtyTemp2.get(n) + "|");
			}

			out.close();
			bw.close();
			fw.close();

		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}


	/**
	 * this is a write file to overwrite the reservations into "reservation"
	 * where the attributes (customer name, phone number, pax, table num, reservation time, and 
	 * reservation status) will be stored using getter methods
	 * @param SDFFULL 
	 * 
	 * @param SDFFULL is the date and time format used to store
	 */
	private static void writeReservationFileForOverwrite(DateFormat SDFFULL){
		try(FileWriter fw = new FileWriter("reservation", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){

			for (int b = 0; b < table1ArrList.size(); b++)
				for (int i = 0; i < table1ArrList.get(b).getReservation().size(); i++) {

					out.print(table1ArrList.get(b).getReservation().get(i).getRCustName() + "|");
					out.print(table1ArrList.get(b).getReservation().get(i).getRPhone() + "|");
					out.print(table1ArrList.get(b).getReservation().get(i).getRPax() + "|");
					out.print(table1ArrList.get(b).getTableNum() + "|");
					out.print(SDFFULL.format(table1ArrList.get(b).getReservation().get(i).getCalendar().getTime())  + "|");//need change
					out.print(table1ArrList.get(b).getReservation().get(i).getStatus());
					out.println();
				}
		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}

	}

	/**
	 * this is a write file to overwrite the table into "table"
	 * where the attributes table number, table status and table cap will be stored using getter methods
	 *  
	 */
	private static void writeTableFileForOverwrite(){

		try(FileWriter fw = new FileWriter("table", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){

			for (int i = 0; i < table1ArrList.size(); i++){
				out.print(table1ArrList.get(i).getTableNum() + "|");
				out.print(table1ArrList.get(i).getTableStatus() + "|");
				out.print(table1ArrList.get(i).getTableCap() + "|");
				out.println();
			}
		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}

	}

	/**
	 * This write method ensures that after a creation/ removal of employee information, that data is either appended to the file or removed respectively
	 * @param employee the employee which we want to register/ update / remove details about 
	 */
	private static void writeEmployeeFileForAppend(Employee employee)
	{
		//to write into the file the details of the set package
		//append to file: true, overwrite file: false
		try(FileWriter fw = new FileWriter("employee", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){

			out.print(employee.getEmployeeId() + "|");
			out.print(employee.getEmployeeName() + "|");
			out.print(employee.getEmployeeGender() + "|");
			out.print(employee.getJobTitle());
			out.println();

			out.close();
			bw.close();
			fw.close();
		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}
	}


	/**
	 * 
	 * This method writes to the employee file to overwrite/append their information
	 * if the employee chooses to append to file, change the 2nd parameter to true, 
	 * else to overwrite employee file, change 2nd parameter to false
	 * 
	 **/
	private static void writeEmployeeFileForOverwrite(List<Employee> employeeArrList){
		//to write into the file the details of the set package
		//append to file: true, overwrite file: false
		try(FileWriter fw = new FileWriter("employee", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){
			for(int i = 0; i<employeeArrList.size(); i++){
				out.print(employeeArrList.get(i).getEmployeeId() + "|");
				out.print(employeeArrList.get(i).getEmployeeName() + "|");
				out.print(employeeArrList.get(i).getEmployeeGender() + "|");
				out.print(employeeArrList.get(i).getJobTitle());

				out.println();
			}
			out.close();
			bw.close();
			fw.close();
		} 
		catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}
	}

	/**
	 * this method writes to the sale revenue report file to append the information
	 * first, we will check if month is less than 13 as (Jan-Dec)
	 * if its not, then we will get the all the orders from the month 
	 * and print all the menu items/ promotional items and their qty
	 * 
	 * @param orderArrList where the order array list contains all the orders
	 */
	private static void writeSaleRevReport(List<Order> orderArrList) {

		ArrayList<MenuItem> menuItemArrTemp = new ArrayList<MenuItem>();
		ArrayList<SetPackage> setPackageArrTemp = new ArrayList<SetPackage>();
		ArrayList<Integer> idTemp = new ArrayList<Integer>();
		ArrayList<Integer> qtyTemp = new ArrayList<Integer>();
		ArrayList<Integer> idTemp2 = new ArrayList<Integer>();
		ArrayList<Integer> qtyTemp2 = new ArrayList<Integer>();

		int month = 1;

		try (FileWriter fw = new FileWriter("saleRevReport", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			

			while (month < 13) {

				for (int q = 0; q < orderArrList.size(); q++) {

					if (orderArrList.get(q).getMonth() == month) {
		
						//getting menu item id and its qty in all orders

						if (orderArrList.get(q).getMenuItemArr().size() != 0) {

							for (int i = 0; i < orderArrList.get(q).getMenuItemArr().size(); i++) {

								menuItemArrTemp.add(orderArrList.get(q).getMenuItemArr().get(i));
							}

							int qty = 0;
							int menuItemId = menuItemArrTemp.get(0).getItemId();

							if (menuItemArrTemp.size() == 1 && !idTemp.contains(menuItemId)) {
								qty++;

								// add id and qty into temp arr
								idTemp.add(menuItemId);
								qtyTemp.add(qty);

								menuItemArrTemp.clear();
							}

							else {
								for (int i = 0; i < menuItemArrTemp.size(); i++) {
									// get qty of the specific itemId
									if (menuItemArrTemp.get(i).getItemId() == menuItemId) {
										menuItemArrTemp.remove(i);
										qty++;
										i--; // this ensures that the first element in temp will always have i = 0
									}
								}

								// store id and qty in temp array list
								if (!idTemp.contains(menuItemId)) {
									idTemp.add(menuItemId);
									qtyTemp.add(qty);
								} else {
									int index = idTemp.indexOf(menuItemId);
									int existingQty = qtyTemp.get(index);
									existingQty += qty;
									qtyTemp.set(index, existingQty);
								}
							}
						}
						
						//getting set package id and its qty in all orders

						if (orderArrList.get(q).getSetPackageArr().size() != 0) {

							for (int i = 0; i < orderArrList.get(q).getSetPackageArr().size(); i++) {								
								setPackageArrTemp.add(orderArrList.get(q).getSetPackageArr().get(i));
							}
							int qty = 0;
							int setPackId = setPackageArrTemp.get(0).getSetPackId();

							if (setPackageArrTemp.size() == 1 && !idTemp2.contains(setPackId)) {
								qty++;

								// add id and qty into temp arr
								idTemp2.add(setPackId);
								qtyTemp2.add(qty);

								setPackageArrTemp.clear();
							}

							else {
								for (int i = 0; i < setPackageArrTemp.size(); i++) {
									// get qty of the specific itemId
									if (setPackageArrTemp.get(i).getSetPackId() == setPackId) {
										setPackageArrTemp.remove(i);
										qty++;
										i--; // this ensures that the first element in temp will always have i = 0
									}
								}

								if (!idTemp2.contains(setPackId)) {
									idTemp2.add(setPackId);
									qtyTemp2.add(qty);
								} else {
									int index = idTemp2.indexOf(setPackId);
									int existingQty = qtyTemp2.get(index);
									existingQty += qty;
									qtyTemp2.set(index, existingQty);
								}
							}
						}
					}
				}
				
				if(idTemp.size() != 0 || idTemp2.size() != 0){
					
					out.print(month + "|");
					
					// write menu items with its qty
					out.print("alacarte|");
	
					for (int n = 0; n < idTemp.size(); n++) {
						out.print(idTemp.get(n) + "|");
						out.print(qtyTemp.get(n) + "|");
					}
	
					out.print("promotional|");
	
					for (int n = 0; n < idTemp2.size(); n++) {
						out.print(idTemp2.get(n) + "|");
						out.print(qtyTemp2.get(n) + "|");
						
					}
					
					out.println();
					
					//clear arraylist to store details of the following month	
					idTemp.clear();
					qtyTemp.clear();
					idTemp2.clear();
					qtyTemp2.clear();
				}
				
				month++;	
			}
			out.close();
			bw.close();
			fw.close();

		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}

	////////////////////////////READ FILES////////////////////////////////////////////////////////////////

	/**
	 * 
	 * This method reads the number of menu items there are in the menu and creates the menu for the restaurant.
	 * This file returns the set package id, name, menu item type, item description, price, whether it is in chef's recommendation and the time it is available
	 * first a new menu item object is created
	 * when i =0, sets the menu item id 
	 * i =1, sets the menu item name
	 * i=2, sets the menu item type( main course/appetizer/beverage)
	 * i=3, sets the menu item description
	 * i =4, sets the menu item price
	 * i=5, sets the whether menu item is in chef's recommendation
	 * i=6, sets the time in which the menu item is available
	 * after everything is inputed, the menu item is ready to be placed into the menu item array list 
	 * 
	 */
	private static int readMenuItemFile(String[] menuItemArr, List<MenuItem> menuItemArrList, int idCount)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader("menuItems"));
			try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
				String data;
				while ( (data = br.readLine()) != null ) {

					menuItemArr = data.split("\\|", -1);

					idCount += 1; //to keep track of id

					MenuItem menuItem = new MenuItem();

					for (int i = 0; i < menuItemArr.length; i++ ){

						//System.out.println("$$$ " + i + " index " + menuItemArr[i]);

						if(i == 0){
							//parseInt convert String to int because setItemId takes in int but it is String in the array
							menuItem.setItemId(Integer.parseInt(menuItemArr[i]));
						}

						else if(i == 1){
							menuItem.setItemName(menuItemArr[i]);
						}

						else if(i == 2){
							menuItem.setItemType(menuItemArr[i]);
						}
						else if(i == 3){
							menuItem.setItemDesc(menuItemArr[i]);
						}
						else if(i == 4){
							//parseDouble convert String to double
							menuItem.setItemPrice(Double.parseDouble(menuItemArr[i]));
						}
						else if(i == 5){
							//parseDouble convert String to boolean
							menuItem.setItemRecom(Boolean.parseBoolean(menuItemArr[i]));
						}
						else if(i == 6){
							menuItem.setLunchDinner(menuItemArr[i]);
						}
					}
					menuItemArrList.add(menuItem);
				}
				br.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return idCount;
	}


	/**
	 * This method reads the number of set packages there are in the menu and creates set packages for the restaurant.
	 * This file returns the set package id, name, price, description, the time it is available, and the menu item id of  set packages' id for 1) main course 2) appetizer 3) beverage.
	 * first a new set package object is created
	 * i =0, sets the set package id 
	 * i =1, sets the set package name
	 * i=2, sets the set package price
	 * i=3, sets the set package description
	 * i =4, sets the time this set package is available( lunch/dinner/lunch and dinner)
	 * i=5, sets the main course for this set package id
	 * i=6, sets the appetizer for this set package id
	 * i= 7, sets teh beverage for this set package id
	 * after everything is inputed, the set package is ready to be placed into the set package menu
	 */
	private static int readSetPackageFile(String[] setPackageArr, List<SetPackage> setPackageArrList, int setpackidCount)
	{		
		try {
			BufferedReader promobr = new BufferedReader(new FileReader("setpackage"));
			try                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
			{
				String data;
				while ( (data = promobr.readLine()) != null ) {
					setPackageArr = data.split("\\|", -1);  //become array setPackageArr = [1,Halloween Special,14.0,Trick or Treat!,Lunch,1,2,3]
					setpackidCount++; //to keep track of id

					SetPackage setpackage = new SetPackage();

					//add each setPackage object into array list (array list of menuItem obj with its own set of att)
					// i is the index
					for (int i = 0; i < setPackageArr.length; i++ ){
						if(i == 0){
							//parseInt convert String to int because setItemId takes in int but it is String in the array
							setpackage.setSetPackId(Integer.parseInt(setPackageArr[i]));
						}
						else if(i == 1){
							setpackage.setSetPackName(setPackageArr[i]);
						}
						else if(i == 2){
							//parseDouble convert String to double
							setpackage.setSetPackPrice(Double.parseDouble(setPackageArr[i]));
						}
						else if(i == 3){ 
							setpackage.setSetPackDesc(setPackageArr[i]);
						}
						else if(i == 4){
							setpackage.setSetPackLunchDinner(setPackageArr[i]);									
						}
						else if(i == 5){
							setpackage.setmaincourseId(Integer.parseInt(setPackageArr[i]));
						}
						else if(i == 6){
							setpackage.setappetizerId(Integer.parseInt(setPackageArr[i]));
						}
						else if(i == 7){
							setpackage.setbeverageId(Integer.parseInt(setPackageArr[i]));
						}
					} 
					setPackageArrList.add(setpackage);

				} 
				promobr.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return setpackidCount;
	}


	/**
	 * this method iterates through the order file according to the options selected by the customer and inputed by the employee.
	 * the employee is asked to input the menu item which he/she would like to select 
	 * i = 1 sets the id of the employee who is taking the order 
	 * i = 2 sets the table number of the order
	 * i =3 sets the month in which the order was taken 
	 * at this point time, the user is able to choose from either alacarte items or promotional(set package) items or both 
	 * if the customer chooses only to eat alacarte items, the employee will only input the ala carte item's id and quantity, skipping promotional items, and vice versa. 
	 * if both promotional items and alacarte items are chosen,both the quantity and id of the items will be recorded accordingly to process the order
	 * 
	 */
	private static int readOrderFile(String[] orderArr, List<Order> orderArrList, int orderidCount, List<MenuItem> menuItemArrList, List<SetPackage> setPackageArrList){		

		try ( BufferedReader br = new BufferedReader(new FileReader("order")); ){

			String data;
			while ( (data = br.readLine()) != null ) {

				orderArr = data.split("\\|", -1);  
				orderidCount++; //to keep track of id

				Order order = new Order();

				//	            	for(int i = 0; i<orderArr.length; i++)
				//	            		System.out.println(orderArr[i]);

				//add each order object into array list (array list of order obj with its own set of att)
				// i is the index
				for (int i = 0; i < orderArr.length; i++ ){


					if(i == 0){
						//parseInt convert String to int because setItemId takes in int but it is String in the array
						order.setOrderId(Integer.parseInt(orderArr[i]));
					}

					else if(i == 1){
						order.setEmployeeId(Integer.parseInt(orderArr[i]));
					}

					else if(i == 2){
						order.setTableNum(Integer.parseInt(orderArr[i]));
					}

					else if(i == 3){
						order.setMonth(Integer.parseInt(orderArr[i]));
					}

					else if(orderArr[i].equals("alacarte")){
						i++;

						while(!orderArr[i].equals("promotional")){

							//orderArr[i+1] represents qty
							//add number of menu item according to qty
							for(int k = 0; k < (Integer.parseInt(orderArr[i+1])); k++){
								order.addMenuItem(menuItemArrList.get(Integer.parseInt(orderArr[i])-1) );
							}
							//to get the next id to add to arraylist
							i = i+2;
						}

						if(orderArr[i].equals("promotional")){
							i++;

							if(!orderArr[i].isEmpty()){

								while(!orderArr[i].isEmpty()){ 

									//orderArr[i+1] represents qty
									//add number of set package according to qty
									for(int k = 0; k < (Integer.parseInt(orderArr[i+1])); k++){
										order.addSetPackage(setPackageArrList.get(Integer.parseInt(orderArr[i])-1) );

									}
									//to get the next id to add to arraylist
									i = i+2;
								}
							}
						}
					}
				}
				orderArrList.add(order);
			} 

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return orderidCount;
	}

	/**
	 * this method iterates through the table file and records the table number/id(1-30), current table status, and the table capacity for that table id
	 * following that, the recorded table is added into the table array list 
	 */

	public static void readTableFile(String[] table1Arr, int setpackidCount){

		try {
			BufferedReader tablebr1 = new BufferedReader(new FileReader("table"));
			try                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
			{
				String data;
				List<Table> tableTempArrList = new ArrayList<Table>(); //added
				while ( (data = tablebr1.readLine()) != null ) {
					table1Arr = data.split("\\|", -1);  
					setpackidCount++; //to keep track of id

					Table table1 = new Table();
					table1.setTableNum(Integer.parseInt(table1Arr[0]));
					table1.setTableStatus(table1Arr[1]);
					table1.setTableCap(Integer.parseInt(table1Arr[2]));

					//table1ArrList.add(table1);
					tableTempArrList.add(table1);

				} 

				table1ArrList = tableTempArrList;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * this method iterates through the Reservation file and records the following:
	 * Customer name, customer's phone number, the pax per reservation, table number assiociated to that reservation,
	 * as well as the status of the tabke beign reserved
	 * following that, the recorded table is added into the table array list 
	 */
	public static void readReservationFile(String[] reservationArr, int reservationidCount, DateFormat SDFFULL){
		try {
			BufferedReader reservationbr = new BufferedReader(new FileReader("reservation"));
			try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
				String data;
				while ( (data = reservationbr.readLine()) != null ) { //before the line ends

					reservationArr = data.split("\\|", -1);  //become array setPackageArr = [1, creamychicken, desc, 8.90....]
					reservationidCount++; //to keep track of id

					Reservation reservation = new Reservation();
					String datestring=null;
					Table table=null;

					reservation.setRCustName(reservationArr[0]);	               		
					reservation.setRPhone(Integer.parseInt(reservationArr[1]));
					reservation.setRPax(Integer.parseInt(reservationArr[2]));
					reservation.setTableNum(Integer.parseInt(reservationArr[3]));							
					datestring = reservationArr[4];
					reservation.setStatus(Boolean.parseBoolean(reservationArr[5]));
					table = searchTable(reservation.getTableNum());

					try{
						Calendar resCalendar = Calendar.getInstance();   	
						Date date1 = SDFFULL.parse(datestring);
						resCalendar.setTime(date1);
						reservation.setCalendar(resCalendar);
						table.addReservation(reservation);
					}
					catch (ParseException e) {
						// Invalid date was entered
					}
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * this method read the sales revenue report file, creating and adding each sales revenue report into the sales reveue report array 
	 */
	private static void readSaleRevReport(String[] reportArr, List<SaleRevReport> saleRevReportArrList)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader("saleRevReport"));
			try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
				String data;
				while ( (data = br.readLine()) != null ) {

					reportArr = data.split("\\|");


					SaleRevReport saleRevReport = new SaleRevReport();

					//add each menuItem object into array list (array list of menuItem obj with its own set of att)
					for (int i = 0; i < reportArr.length; i++ ){

						//System.out.println("$$$ " + i + " index " + menuItemArr[i]);

						if(i == 0){
							//parseInt convert String to int because setItemId takes in int but it is String in the array
							saleRevReport.setMonth(Integer.parseInt(reportArr[i]));
						}
						//
						//						else if(i == 1){
						//							menuItem.setItemName(menuItemArr[i]);
						//						}


					}
					saleRevReportArrList.add(saleRevReport);
				}
				br.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}


	public static Table searchTable(int tableNum){
		for (int i = 0; i < table1ArrList.size(); i++) {
			if (table1ArrList.get(i).getTableNum() == tableNum)
				return table1ArrList.get(i);
		}
		return null;
	}

	/**
	 * 
	 * This method reads the employee information, creating new employee information if necessary for the restaurant.
	 * This file returns the employee id, name, gender and job title
	 * first an employee object is created
	 * when i =0, sets the employee id 
	 * i =1, sets the employee name
	 * i=2, sets the employee gender( male or female)
	 * i=3, sets the employee job title( cashier, manager, server, janitor, cleaner)
	 * after everything is inputed, the employee object is ready to be placed into the employee array list 
	 * 
	 */

	private static int readEmployeeFile(String[] employeeArr, List<Employee> employeeArrList, int idCount)
	{		
		try {
			BufferedReader br = new BufferedReader(new FileReader("employee"));
			try                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
			{
				String data;
				while ( (data = br.readLine()) != null ) {
					employeeArr = data.split("\\|", -1);  //become array setPackageArr = [1,John, M, Cashier etc...]
					idCount++; //to keep track of id

					Employee employee = new Employee();

					for (int i = 0; i < employeeArr.length; i++ ){
						if(i == 0){
							//parseInt convert String to int because setEmployeeId takes in int but it is String in the array
							employee.setEmployeeId(Integer.parseInt(employeeArr[i]));
						}
						else if(i == 1){
							employee.setEmployeeName(employeeArr[i]);
						}
						else if(i == 2){
							employee.setEmployeeGender(employeeArr[i]);
						}
						else if(i == 3){ 
							employee.setJobTitle(employeeArr[i]);
						}
					} 
					employeeArrList.add(employee);

				} 
				br.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return idCount;
	}


	/**
	 * returns an array list of available tables based on the time 
	 * if the unoccupied tables can fit the max cap of people for the reservation, add this table into a table array list
	 * find a reservation which has not already expired, set this reservation to a particular unoccupied table
	 * else if reservation is expired, do not assign any unoccupied tables to it 
	 * if the table connected to a particular reservation has expired, set the table to 'vacant' by putting the table back into available table array list 
	 */
	public static List<Table> searchAvailableTable(int Pax, Calendar time){
		String vacated = "vacated";
		String reserved = "reserved";
		String occupied = "occupied";
		int countExpired=0;
		List<Table> tempTableArrList;
		tempTableArrList = null;


		if (Reservation.DateValidator(time)){
			tempTableArrList = new ArrayList<Table>();
			for (int i=0; i<table1ArrList.size(); i++){		
				countExpired=0;
				if (table1ArrList.get(i).getTableCap() >= Pax){
					//capacity is enough
					if (!table1ArrList.get(i).getTableStatus().equals(occupied)){ //if not occupied
						if (table1ArrList.get(i).getReservation().size() == 0)
							tempTableArrList.add(table1ArrList.get(i));//table available, add into available table array list

						for (int k=0; k<table1ArrList.get(i).getReservation().size(); k++){ //get the reservation array list for this room
							if(!table1ArrList.get(i).getReservation().get(k).getStatus()){ //check reservation only if it is not expired.
								if(table1ArrList.get(i).getReservation().get(k).isAvailableForReservation(time)){ //check if reservation array got slot
									System.out.println(table1ArrList.get(i).getTableNum() + " is available for reservation even with previous reservations made");
									tempTableArrList.add(table1ArrList.get(i));
									break;//no conflicts
								}
							}
							//if it is expired, dont compare
							else{
								countExpired++;
							}
						}
						if (countExpired!=0 && countExpired == table1ArrList.get(i).getReservation().size()){	//if all reservation expired{
							tempTableArrList.add(table1ArrList.get(i));//table available, add into available table arraylist
							//	System.out.println(table1ArrList.get(i).getTableNum() + " all reservation expired");
							//	System.out.println("countExpired : " + countExpired);
							//	System.out.println("ArrayList size of table : " + table1ArrList.get(i).getReservation().size());
						}
						//else{
						//	System.out.println(table1ArrList.get(i).getTableNum() + " not all reservation expired");
						//	System.out.println("countExpired : " + countExpired);
						//	System.out.println("ArrayList size of table : " + table1ArrList.get(i).getReservation().size());
						//}
					}			
				}
			}
		}
		return tempTableArrList;
	}  


	/**
	 * this functions updates the table status to "reserved" if the current date and time is same as the reservation date and time. 
	 * This is done by comparing the current date and time when the function is called, with all the created reservation date and time that are not expired.
	 */
	public static void updateTableStatus(){
		Calendar reservationDate =  Calendar.getInstance();
		Calendar currentTime = Calendar.getInstance();

		for (int i=0; i<table1ArrList.size(); i++){
			if (!table1ArrList.get(i).getTableStatus().equals("occupied"))	//don't compare occupied tables
				for (int b = 0; b < table1ArrList.get(i).getReservation().size(); b++) {
					if(!table1ArrList.get(i).getReservation().get(b).getStatus()){ // don't compare expired reservations
						reservationDate = table1ArrList.get(i).getReservation().get(b).getCalendar();				
						Calendar currentDate11AM = (Calendar)currentTime.clone();
						Calendar currentDate3PM= (Calendar)currentTime.clone();
						Calendar currentDate6PM= (Calendar)currentTime.clone();
						Calendar currentDate10PM= (Calendar)currentTime.clone();

						currentDate11AM.set(Calendar.HOUR_OF_DAY, 10);
						currentDate11AM.set(Calendar.MINUTE, 59);

						currentDate3PM.set(Calendar.HOUR_OF_DAY, 15);
						currentDate3PM.set(Calendar.MINUTE, 01);

						currentDate6PM.set(Calendar.HOUR_OF_DAY, 17);
						currentDate6PM.set(Calendar.MINUTE, 59);

						currentDate10PM.set(Calendar.HOUR_OF_DAY, 22);
						currentDate10PM.set(Calendar.MINUTE, 01);

						//can use this for testing, set hour of day and minute according to your test time for today.
						/*
 currentTime.set(Calendar.HOUR_OF_DAY, 18);
 currentTime.set(Calendar.MINUTE, 00);
						 */

						boolean isSameDay = currentTime.get(Calendar.YEAR) == reservationDate.get(Calendar.YEAR) &&
								currentTime.get(Calendar.DAY_OF_YEAR) == reservationDate.get(Calendar.DAY_OF_YEAR);

						//if reservation time within restaurant opening hours AM/PM session respectively, set table status = reserved
						if (isSameDay){
							if (currentTime.after(currentDate11AM) && currentTime.before(currentDate3PM)){
								if (reservationDate.after(currentDate11AM) && reservationDate.before(currentDate3PM))
									table1ArrList.get(i).setTableStatus("reserved");
							}

							if (currentTime.after(currentDate6PM) && currentTime.before(currentDate10PM)){
								if (reservationDate.after(currentDate6PM) && reservationDate.before(currentDate10PM))
									table1ArrList.get(i).setTableStatus("reserved");
							}
						}
						else
							table1ArrList.get(i).setTableStatus("vacated");

					}
				}
		}
	}

/**
 * this method reads the sale revenue report file and update it
 * it uses "|" to separate from "month | alacarte | alacarteitemID | alarcarteitemQty | promotional| promotionalitemID | promotionalitemQty"
 * 
 * @param reportArr
 * @param saleRevReportArrList
 * @param menuItemArrList
 * @param setPackageArrList
 */
	private static void readSaleRevReport(String[] reportArr, List<SaleRevReport> saleRevReportArrList,
			List<MenuItem> menuItemArrList, List<SetPackage> setPackageArrList) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("saleRevReport"));
			try {
				String data;
				while ((data = br.readLine()) != null) {

					reportArr = data.split("\\|");

					SaleRevReport saleRevReport = new SaleRevReport();
					Order order = new Order();
					
					saleRevReport.getSaleRevOrderArrListMenuItem().add(order);
					saleRevReport.getSaleRevOrderArrListSetPackage().add(order);

					// add each menuItem object into array list (array list of
					// menuItem obj with its own set of att)
					for (int i = 0; i < reportArr.length; i++) {

						if (i == 0) {
							// parseInt convert String to int because setItemId
							// takes in int but it is String in the array
							
							saleRevReport.setMonth(Integer.parseInt(reportArr[i]));

						}

						else if (reportArr[i].equals("alacarte")) {
							i++;

							while (!reportArr[i].equals("promotional")) {
								
								// reportArr[i+1] represents qty
								// add number of menu item according to qty
								for (int k = 0; k < (Integer.parseInt(reportArr[i + 1])); k++) {
									order.addMenuItem(menuItemArrList.get(Integer.parseInt(reportArr[i]) - 1));

								}
								// to get the next id to add to arraylist
								i = i + 2;
							}

							i--;
						}
						

						else if (reportArr[i].equals("promotional")) {
							

							for (int c = i; c < reportArr.length - 1; c++) {
								c++;

								// reportArr[i+1] represents qty
								// add number of set package according to qty
								for (int k = 0; k < (Integer.parseInt(reportArr[c + 1])); k++) {
									order.addSetPackage(setPackageArrList.get(Integer.parseInt(reportArr[c]) - 1));	
								}
							}
						}
					}
					saleRevReportArrList.add(saleRevReport);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}


}//closing for Main


