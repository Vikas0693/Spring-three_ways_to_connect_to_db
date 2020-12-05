1)Based on properties config spring boot automatically creates Datasource and EntityManager beans
2)EntityManager is from JPA api.
3)Hibernate and eclipseLink are implementations of JPA
4)Hibernate is default implementation of JPA


Ways to connect to DB
1)In first commit we are connecting DB using Hibernate as we are using entityManager in EmployeeService.
2)In second commit we will be using standard JPA api.
3)In third commit we will use spring DATA JPA.