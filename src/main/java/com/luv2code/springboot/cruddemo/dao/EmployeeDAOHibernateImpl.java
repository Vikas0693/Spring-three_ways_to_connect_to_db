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
	//@Transactional handles transaction for use so we dont do start or commit
	@Override
	@Transactional
	public List<Employee> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query<Employee> theQuery = 
				session.createQuery("from Employee",Employee.class);
		
		List<Employee> employees = theQuery.getResultList();
		return employees;
	}

}
