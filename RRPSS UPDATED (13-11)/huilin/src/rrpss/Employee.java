package rrpss;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is to register employees, update employee details as well as remove employees from database
 * @author yangzhen
 *
 */

public class Employee {
	private int employeeId;
	private String employeeName;
	private String employeeGender;
	private String jobTitle; 

	/**
	 * This returns a unique ID of the employeee
	 * @return this employee id 
	 */
public int getEmployeeId(){
	return employeeId;
}
/**
 * This is a setter method to set the employee id when eployee data is inputed
 * @param employeeId sets the id of the employee
 */
public void setEmployeeId(int employeeId){
	this.employeeId = employeeId;
}
/**
 * a getter method to get employee's name
 * @return the employee name
 */
public String getEmployeeName(){
	return employeeName;
}
/**
 * This is a setter method to set the employee name when employee data is inputed 
 * @param employeeName the name of the employee
 */
public void setEmployeeName(String employeeName){
	this.employeeName = employeeName;
}

/**
 * This is a getter method to sget the employee gender when employee data is needed to be updated
 * @return the gender of the employee
 */
public String getEmployeeGender() {
	return employeeGender;
}
/**
 * This is a setter method to set the employee gender when employee data is inputed
 * @param employeeGender the gender of the employee
 */
public void setEmployeeGender(String employeeGender){
	this.employeeGender = employeeGender;
}
/**
 * This is a getter method to get the employee job title when employee data is updated
 * @return the job title of employee
 */
public String getJobTitle(){
	return jobTitle;
}
/**
 * This is a setter method to set the employee job title when employee data is inputed
 * @param jobTitle this is the employee's job title 
 */
public void setJobTitle(String jobTitle){
	this.jobTitle = jobTitle;
 }


/**
 * this is a print method to print out all the staff information working in our restaurant
 * 
 * @param employeeArrList stores all the details of the employee
 */
public void printEmployeeInfo(List<Employee> employeeArrList){

	String title = "%1s%-5s%-25s%-10s%-15s%2s%n";
	String format = "%1s%-5d%1s%-24s%1s%4s%-5s%1s%-14s%2s%n";

	//print restaurant details//
	System.out.println();
	System.out.println("	           CRESCENDO RESTAURANT");
	System.out.println("	       NTU NORTH SPINE NS4 #05-105");
	System.out.println("      HP:6790 0000  Mob: 9790 0000  Fax: 8283 6264");
	System.out.println();
	System.out.println("		    STAFF INFORMATION");
	System.out.println();


	//print order invoice
	System.out.println("+--------------------------------------------------------+");
	System.out.format(title, "|", "ID", "|NAME", "|GENDER", "|JOB TITLE","|");
	System.out.println("+--------------------------------------------------------+");

	for(int i = 0; i< employeeArrList.size(); i++){			
		//System.out.println(employeeArrList.get(i).getJobTitle());
		System.out.format(format, "|", employeeArrList.get(i).getEmployeeId(), "|",employeeArrList.get(i).getEmployeeName(),
				"|", " ", employeeArrList.get(i).getEmployeeGender(), "|", employeeArrList.get(i).getJobTitle(), "|", "\n" );
			}
	System.out.println("+--------------------------------------------------------+");
	System.out.println();

}

}