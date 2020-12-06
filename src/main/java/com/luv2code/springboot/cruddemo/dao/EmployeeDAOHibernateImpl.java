package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

	private EntityManager entityManager;
	
	//constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}
	@Override
	public List<Employee> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query<Employee> theQuery = 
				session.createQuery("from Employee",Employee.class);
		
		List<Employee> employees = theQuery.getResultList();
		return employees;
	}
	
	@Override
	public Employee findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Employee emp = session.get(Employee.class, id);
		return emp;
	}
	
	@Override
	public void save(Employee employee) {
		Session session = entityManager.unwrap(Session.class);
		//if id is 0 then it saves else it updates
		session.saveOrUpdate(employee);
		
	}
	
	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Employee where id =: employeeId").setParameter("employeeId", id);
		query.executeUpdate();
	}

}
