# Spring JPA
## Master Hibernate and JPA with Spring Boot in 100 steps
https://www.udemy.com/course/hibernate-jpa-tutorial-for-beginners-in-100-steps/
  
#### JPA and Hibernate - Entities, Annotations   
files: database-demo

#### JUnit - Assertion Functions, LifeCycle  
files: junit-in-5-steps

#### Approach to querying data using JPA and Hibernate - JPQL, Native Queries  
files: jpa-hibernate(Course, CourseRepository, JPQLTest, NativeQueriesTest, CourseRepositoryTest, data.sql)  
  
#### JPA and Hibernate Relationships in depth - One to One  
files: jpa-hibernate(Passport, Review, Student, StudentRepository, StudentRepositoryTest)
  
#### JPA and Hibernate Relationships in depth - Many to One and Many to Many    
files: jpa-hibernate(Course, Review, Student, CourseRepository, CourseRepositoryTest, StudentRepository, StudentRepositoryTest)
  
#### JPA and Hibernate Relationships in depth - Inheritance Mappings  
files: jpa-inheritance

#### Approach to querying data using JPA and Hibernate - Criteria  
files: jpa-hibernate(CriteriaQueryTest)
 
#### Spring Data JPA and Spring Data REST  
files: spring-data-jpa(Course, CourseSpringDataRepository, CourseSpringRepositoryTest)

#### Caching - First Level Cache and Second Level Cache with EhCache  
files: spring-data-jpa(application.properties, Course, CourseSpringDataRepositoryTest)  
  
#### Hibernate & JPA Tips - Soft Delete, Embedded, Enumerated, Paging  
files: spring-data-jpa(Course, Student, Address, StudentRepositoryTest, CourseSpringDataRepositoryTest), jpa-hibernate(Review, ReviewRating, CourseRepository, data.sql)  
  
#### Tuning your JPA application with Hibernate - Solve N+1 Queries Issue       
files: spring-data-jpa(Course, PerformanceTuningTest)  

## Practice Project: Building REST API with Spring Boot and JPA
### Title: spring-jpa-user

#### 초기 설정  
Spring Starter Project 생성: Spring Web, Spring Data JPA, MySQL Driver, Spring Data JDBC, H2 Database, Lombok, Spring Boot DevTools 추가  
pom.xml: openapi, jackson-core, commons-dbcp2, commons-fileupload, aspectjweaver, aspectjrt 추가 
src/main/resources: application.properties-> MySQL dataSource 설정  
src/test/resources 폴더(소스폴더X) 생성: application-test.properties 생성-> H2 dataSource 설정  
getUserList, registUser, getUserbyName, deleteUser 메서드 openapi 테스트

#### QueryDSL  
pom.xml: querydsl-jpa, querydsl-apt 추가  
config > QueryDslConfig 생성
target/generated-sources/annotations 소스 폴더로 변경 -> QClass 추가 확인   
repository > user > UserRepositoryCustom, UserRepositoryCustomImpl(QueryDslConfig 의존성 주입)  
repository > user > UserRepository에 UserRepositoryCustom 상속 추가  
updateUser 메서드 테스트      

#### Join  
User, Product @ManyToMany, @JoinTable 형성  
getProductList, registProduct, deleteProduct, updateProduct, getProductByName 테스트  
buyProduct 메서드 테스트; user의 products 변수에 product 추가

#### 순환 참조 방지  
UserDto, ProductDto 추가  
기존에 entity를 직접 전송하거나 반환하던 메서드를 Dto를 통하도록 변경   
getProductByUser, getUserByProduct 메서드 테스트  

#### 인라인 DB로 단위테스트  
src/test/java: repository > UserRepositoryTest 생성(properties profile 설정)   
src/test/resources: data.sql(초기 데이터 생성)  

#### Cache  
pom.xml: ehcache, hibernate-jcache 추가  
application-test.properties: second-level cache, log-level 설정  
entity에 @Cacheable 추가  
findById 메서드 테스트
    
