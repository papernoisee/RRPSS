package rrpss;

import java.util.ArrayList;
/**
 * This class implements an application that creates, updates and removes the food items in the restaurant menu.
 * 
 * @author yangzhen
 *
 */
public class MenuItem {
	private int itemId;
	private String itemName;
	private String itemType;
	private String itemDesc;
	private double itemPrice;
	private boolean itemRecom;
	private String lunchDinner;
	private int setPackId;
	
	/**
	 * A constructor for items in the menu to initialize an object that doesn't need parameters in that initialization process.
	 */
	public MenuItem() {
	}

	/**
	 * This getter method allows the id of a menu item to be accessed and retrieved for creating/updating items in the menu
	 * @return returns the id of the menu item 
	 */
	public int getItemId(){
		return itemId;
	}
	/**
	 * helps to identify the items to be added into ala carte and/or promotional and/or set package food items
	 * @param itemId refers to the id of the menu item 
	 */
	public void setItemId(int itemId){
		this.itemId = itemId;
	}
	
	/**
	 * this is a getter method to access and retrieve the name of the item 
	 * @return the name of the item
	 */
	public String getItemName(){
		return itemName;
	}
	

	/** 
	 * this is a setter method to create and/or modify the name of the item 
	 * @param itemName the name of the menu item
	 */
	public void setItemName(String itemName){
		this.itemName = itemName;
	}
	
	/**
	 * this is a getter method to access the menu item type
	 * @return the category of the menu item ( e.g beverage, appetizer, main course etc) 
	 */
	public String getItemType(){
		return itemType;
	}
	
	/**
	 *  this is a setter method to for the user to select the type in which the item created falls into
	 * @param itemType this refers to the category in which the item is in the menu 
	 */
	public void setItemType(String itemType){
		this.itemType = itemType;
	}
	
	
	/**
	 * This getter method  retrieves the item description of the menu 
	 * @return the description of the menu item 
	 */
	public String getItemDesc(){
		return itemDesc;
	}
	
	/**
	 * This setter method is used to create/update the item description for the menu from the user's inputs
	 * @param itemDesc 
	 */
	public void setItemDesc(String itemDesc){
		this.itemDesc = itemDesc;
	}
	/**
	 * this getter method gets the price of the menu item for the receipt 
	 * @return the price of the menu item 
	 */
	public double getItemPrice(){
		return itemPrice;
	}
	
	/**
	 * setter method to create/ update item price in menu
	 * @param itemPrice refers to the price of the item in menu
	 */
	
	public void setItemPrice(double itemPrice){
		this.itemPrice = itemPrice;
	}
	
	/**
	 * getter method to find out which menu item is the cehf's recommendations 
	 * @return the menu item which is recommended by chef
	 */
	public boolean getItemRecom(){
		return itemRecom;
	}
	
	/**
	 * setter method to select the menu items which is recommended by the chef, store's specialty
	 * @param itemRecom this is the item recommended by the chef
	 */
	public void setItemRecom(boolean itemRecom){
		this.itemRecom = itemRecom;
	}
	
	/**
	 * getter method to access and/or modify menu items that are available for both lunch and dinner
	 * @return if the menu item is available for both lunch and dinner 
	 */
	public String getLunchDinner() {
		return lunchDinner;
	}
	/**
	 * 
	 * @param lunchDinner this refers to the menu item which is available for both lunch and dinner
	 */
	public void setLunchDinner(String lunchDinner) {
		this.lunchDinner = lunchDinner;
	}
	
	/**
	 * getter method retrieves the id of the set package to 
	 * @return the id of the menu set package 
	 */
	public int getSetPackId(){
		return setPackId;
	}
	
	/**
	 * setter method to create a set package id of the menu item 
	 * @param setPackId this is the id number of the set package from the menu
	 */
	public void setSetPackId(int setPackId){
		this.setPackId = setPackId;
	}
}