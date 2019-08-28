package org.hiree.salesreports.rest.dto;

import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.springframework.stereotype.Component;
@Component
public class TestTableDTO implements Payload{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1739174920613239405L;
	private int empNumber;
	private String name;
	private String dept;
	
	
	public int getEmpNumber() {
		return empNumber;
	}
	public void setEmpNumber(int empNumber) {
		this.empNumber = empNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
	
	
}
