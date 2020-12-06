1)Based on properties config spring boot automatically creates Datasource and EntityManager beans
2)EntityManager is from JPA api.
3)Hibernate and eclipseLink are implementations of JPA
4)Hibernate is default implementation of JPA


Ways to connect to DB
1)In first commit(c67fc04c2c77) we are connecting DB using Hibernate as we are using entityManager in EmployeeService.
2)In second commit(44028b2e) we will be using standard JPA api.
	2.a)Benefits of using JPA Api -> if we decide to shift to eclipseLink from hibernate bcoz hibernate is not supported anymore then we will have to make minimal changes. Also we are using EntityManager instead of Session.
	2.b)We have persist,merge,find,remove methods in JPA corresponsing to save,update,get/load,delete methods of hibernate.
	2.c)Remember we are using Query from javax.persistence instead of org.hibernate which we used in first commit.This way we are not dependent on hibernate
	2.d)We also learned how to use @Primary and @Qualifier to tell spring to load specific implementation of EmployeeDAO at startup
3)In third commit(b97c3f281) we will use spring DATA JPA.
	3.a)Different RestController and different Repository created to handle request using spring data jpa.
	3.b)we are not using any entity manager nor we are managing transaction.we removed @Transactional
	3.c)Implementation of EmployeeRepository is managed by spring
	3.4)Benefits includes quick setup of basic DB CRUD operations

Spring Data REST?
1)A Quick way to expose basic crud rest api for our entities
2)Add dependency for data-rest,create JpaRepository for our entity and access crud apis at /employees
3)api endpoint is defined using entity name, just append 's' at entity and api will be created
4)Configuration , Pagination and sorting are also possible
5)Configuration - base-path in properties,@RepositoryRestResource to change endpoint from '/employees' to '/members'
6)Pagination & Sorting - http://localhost:8080/data-rest/members?page=0&size=6&sort=lastName,desc,firstName - where maxElements in a page can be 6 and return page no. 0 with given sorting