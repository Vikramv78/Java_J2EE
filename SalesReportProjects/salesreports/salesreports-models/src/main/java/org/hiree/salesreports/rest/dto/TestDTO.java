package org.hiree.salesreports.rest.dto;

import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.springframework.stereotype.Component;
@Component
public class TestDTO implements Payload{
	private static final long serialVersionUID = 8785361725220895121L;
	private int empNumber;
	private String name;
	private int dept;
	
	

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
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	
	
	
}
