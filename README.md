## Spring JPA
### Master Hibernate and JPA with Spring Boot in 100 steps
https://www.udemy.com/course/hibernate-jpa-tutorial-for-beginners-in-100-steps/

23/12/06  
JPA and Hibernate - Entities, Annotations   
files: database-demo

JUnit - Assertion Functions, LifeCycle  
files: junit-in-5-steps

23/12/07  
Approach to querying data using JPA and Hibernate - JPQL, Native Queries  
files: jpa-hibernate(Course, CourseRepository, JPQLTest, NativeQueriesTest, CourseRepositoryTest, data.sql)  

23/12/08  
JPA and Hibernate Relationships in depth - One to One  
files: jpa-hibernate(Passport, Review, Student, StudentRepository, StudentRepositoryTest)

23/12/09  
JPA and Hibernate Relationships in depth - Many to One and Many to Many    
files: jpa-hibernate(Course, Review, Student, CourseRepository, CourseRepositoryTest, StudentRepository, StudentRepositoryTest)

23/12/10  
JPA and Hibernate Relationships in depth - Inheritance Mappings  
files: jpa-inheritance

Approach to querying data using JPA and Hibernate - Criteria  
files: jpa-hibernate(CriteriaQueryTest)

23/12/11  
Spring Data JPA and Spring Data REST  
files: spring-data-jpa(Course, CourseSpringDataRepository, CourseSpringRepositoryTest)

23/12/12  
Caching - First Level Cache and Second Level Cache with EhCache  
files: spring-data-jpa(application.properties, Course, CourseSpringDataRepositoryTest)  

23/12/13  
Hibernate & JPA Tips - Soft Delete, Embedded, Enumerated, Paging  
files: spring-data-jpa(Course, Student, Address, StudentRepositoryTest, CourseSpringDataRepositoryTest), jpa-hibernate(Review, ReviewRating, CourseRepository, data.sql)  

23/12/14  
Tuning your JPA application with Hibernate - Solve N+1 Queries Issue       
files: spring-data-jpa(Course, PerformanceTuningTest)  

### Practice Project: Building REST API with Spring Boot and JPA
### Title: spring-jpa-user

23/12/16 - 초기 설정
Spring Starter Project 생성: Spring Web, Spring Data JPA, MySQL Driver, Spring Data JDBC, H2 Database, Lombok, Spring Boot DevTools 추가  
pom.xml: openapi, jackson-core, commons-dbcp2, commons-fileupload, aspectjweaver, aspectjrt 추가 
src/main/resources: application.properties-> MySQL dataSource 설정  
src/test/resources 폴더(소스폴더X) 생성: application-test.properties 생성-> H2 dataSource 설정 
entity, dto, controller, service, repository 생성
getUserList, registUser, getUserbyName, deleteUser 메서드 openapi 테스트

23/12/17 - QueryDSL  
pom.xml: querydsl-jpa, querydsl-apt 추가  
config > QueryDslConfig 생성  
repository > user > UserRepositoryCustom, UserRepositoryCustomImpl(QueryDslConfig 의존성 주입)  
repository > user > UserRepository에 UserRepositoryCustom 상속 추가  
updateUser 메서드 테스트  

23/12/18 - 인라인 DB로 단위테스트  
src/test/java: repository > UserRepositoryTest 생성(properties profile 설정)   
src/test/resources: data.sql(초기 데이터 생성)  
saveUser 메서드 Persistent Context 테스트    

23/12/19 - Cache
pom.xml: ehcache, hibernate-jcache 추가  
application-test.properties: second-level cache, log-level 설정  
entity에 @Cacheable 추가  
findById 메서드 테스트  

    
