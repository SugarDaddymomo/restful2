package com.tothenew.sharda.EmployeeStuff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
	
	private static List<Employee> employeeList = new ArrayList<>();
	private static int employeeCount = 3;
	
	static {
		employeeList.add(new Employee(1, "James", 21));
		employeeList.add(new Employee(2, "John", 25));
		employeeList.add(new Employee(3, "Sharda", 23));
		employeeList.add(new Employee(4, "Lolu", 24));
	}
	
	public List<Employee> findAll() {
		return employeeList;
	}
	
	public Employee save(Employee employee) {
		if(employee.getId() == null) {
			employee.setId(++employeeCount);
		}
		employeeList.add(employee);
		return employee;
	}
	
	public Employee findOne(Integer id) {
		for (Employee employee : employeeList) {
			if(employee.getId() == id) {
				return employee;
			}
		}
		return null;
	}
	
	public Employee deleteById(Integer id) {
		Iterator<Employee> iterator = employeeList.iterator();
		while (iterator.hasNext()) {
			Employee employee = iterator.next();
			if(employee.getId() == id) {
				iterator.remove();
				return employee;
			}
		}
		return null;
	}
	
	public void updateById(Integer id, Employee employee) {
		for (int i=0; i<employeeList.size(); i++) {
			Employee curr = employeeList.get(i);
			if (curr.getId() == id) {
				employeeList.set(i, employee);
				return;
			}
		}
	}
}