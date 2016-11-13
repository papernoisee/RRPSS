package rrpss;

import java.util.ArrayList;

/**
 * This class is created 
 * @author yangzhen
 *
 */
public class SetPackage {
	private int setPackId;
	private String setPackName;
	private double setPackPrice;
	private String setPackDesc; //added description
	private String setPackLunchDinner;  //added lunch and dinner
	
	private int maincourseId;
	private int appetizerId;
	private int beverageId;

	ArrayList<MenuItem> menuItemArr = new ArrayList<MenuItem>();
	/**
	 * a constructor used to show that when the set package is created, the menu items inside this package are also created.
	 * menu items will then be added into the menu items array 
	 */
	public SetPackage(){
		MenuItem mainCourse = new MenuItem();
		MenuItem appetizer = new MenuItem();
		MenuItem beverage = new MenuItem();
		menuItemArr.add(mainCourse);
		menuItemArr.add(appetizer);
		menuItemArr.add(beverage);
		
		
	}
	/**
	 * 
	 * @return the id of food item which is a main course of the menu
	 */
	public int getmaincourseId(){
		return maincourseId;
	}
	/**
	 * 
	 * @param maincourseId this is the main course id of the food item which is in the menu
	 */
	public void setmaincourseId(int maincourseId){
		this.maincourseId = maincourseId;
	}
	/**
	 * getter method to retrieve the id of the appetizer in the menu
	 * @return the id of the appetizer 
	 */
	public int getappetizerId(){
		return appetizerId;
	}
	
	/**
	 * setter method to assign id of the appetizer to the variable 'appetizerid'
	 * @param appetizerId this is the id of the appetizer
	 */
	public void setappetizerId(int appetizerId){
		this.appetizerId = appetizerId;
	}
	/**
	 * getter method to retrieve the id of the beverage in the menu
	 * @return the id of the beverage 
	 */
	public int getbeverageId(){
		return beverageId;
	}
	/**
	 * setter method to assign id of the beverage to the variable 'beverageid'
	 * @param beverageId this is the id of beverage
	 */
	public void setbeverageId(int beverageId){
		this.beverageId = beverageId;
	}
	
	/**
	 * getter method to retrieve the id of the set package stored in the database 
	 * @return id of the set package in the menu
	 */
	public int getSetPackId(){
		return setPackId;
	}
	/**
	 * 
	 * @param setPackId this is the id of the set package 
	 */
	public void setSetPackId(int setPackId){
		this.setPackId = setPackId;
	}
	/**
	 * getter method to retrieve the name of the set package in the menu
	 * @return the name of the set package
	 */
	public String getSetPackName(){
		return setPackName;
	}
	/**
	 * setter method to create the name of the set package in the menu
	 * @param setPackName this is the name of the set package
	 */
	public void setSetPackName(String setPackName){
		this.setPackName = setPackName;
	}
	/**
	 * setter method to retrieve the price of the set package 
	 * @return the price of the set package 
	 */
	public double getSetPackPrice(){
		return setPackPrice;
	}
	/**
	 * setter method to create a price for the promotional set package 
	 * @param promoPrice this is the price of the promotional set package
	 */
	public void setSetPackPrice(double promoPrice){  //int change to double
		this.setPackPrice = promoPrice;
	}
	/**
	 * this getter method returns the 
	 * @return the description of the set packages
	 */
	public String getSetPackDesc() { //added description
		return setPackDesc;
	}
	/**
	 * setter method to set a description for the promotional set packages
	 * @param promoDesc this refers to the description of the promotional set packages
	 */
	public void setSetPackDesc(String promoDesc) {
		this.setPackDesc = promoDesc;
	}
	/**
	 * getter method retrieves the set package which is available for both lunch and dinner
	 * @return set package which is available for both lunch and dinner 
	 */
	public String getSetPackLunchDinner() {  //added lunch and dinner
		return setPackLunchDinner;
	}
	/**
	 * setter method which creates the promotional set package for lunch and dinner 
	 * @param promolunchDinner this is the promotional set package which is available for both lunch and dinner
	 */
	public void setSetPackLunchDinner(String promolunchDinner) {
		this.setPackLunchDinner = promolunchDinner;
	}

		
}