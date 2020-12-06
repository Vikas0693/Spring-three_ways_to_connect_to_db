1)Based on properties config spring boot automatically creates Datasource and EntityManager beans
2)EntityManager is from JPA api.
3)Hibernate and eclipseLink are implementations of JPA
4)Hibernate is default implementation of JPA


Ways to connect to DB
1)In first commit(c67fc04c2c77) we are connecting DB using Hibernate as we are using entityManager in EmployeeService.
2)In second commit we will be using standard JPA api.
	2.a)Benefits of using JPA Api -> if we decide to shift to eclipseLink from hibernate bcoz hibernate is not supported anymore then we will have to make minimal changes. Also we are using EntityManager instead of Session.
	2.b)We have persist,merge,find,remove methods in JPA corresponsing to save,update,get/load,delete methods of hibernate.
	2.c)Remember we are using Query from javax.persistence instead of org.hibernate which we used in first commit.This way we are not dependent on hibernate
	2.d)We also learned how to use @Primary and @Qualifier to tell spring to load specific implementation of EmployeeDAO at startup
3)In third commit we will use spring DATA JPA.