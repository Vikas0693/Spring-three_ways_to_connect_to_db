package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
//either use primary annotation here to tell spring to load this implementation of EmployeeDAO or use @Qualifier where we are injecting EmployeeDAO
//so we are using @Qualifier in 
//@Primary
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	//constructor injection
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		Query query = entityManager.createQuery("from Employee");
		List<Employee> list = query.getResultList();
		return list;
	}

	@Override
	public Employee findById(int id) {
		return entityManager.find(Employee.class, id);
	}

	@Override
	public void save(Employee employee) {
		//if id is 0 then merge persist the object else it updates
		Employee emp = entityManager.merge(employee);
		//setting id so that we can return id in rest response
		employee.setId(emp.getId());
	}

	@Override
	public void deleteById(int id) {
		entityManager.createQuery("delete from Employee where id=:employeeId").setParameter("employeeId", id)
		.executeUpdate();
	}

}