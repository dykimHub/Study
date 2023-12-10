package com.in28minutes.jpa.inheritance.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.inheritance.entity.Employee;
import com.in28minutes.jpa.inheritance.entity.FullTimeEmployee;
import com.in28minutes.jpa.inheritance.entity.PartTimeEmployee;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public void insert(Employee employee) {
		em.persist(employee);
	}

//  mappedsuperclass이면 employee가 entity가 아니라서 em을 쓸 수 없음 그래서 밑에 2개처럼 따로따로 불러줘야 함
//	public List<Employee> retrieveAllEmployees() {
//		return em.createQuery("select e from Employee e", Employee.class).getResultList();
//	}

	public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
		return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}

	public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
		return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}

}
