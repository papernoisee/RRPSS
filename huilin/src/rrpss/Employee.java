package rrpss;
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

}