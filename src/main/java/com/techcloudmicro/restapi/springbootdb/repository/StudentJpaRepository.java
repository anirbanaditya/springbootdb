package com.techcloudmicro.restapi.springbootdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techcloudmicro.restapi.springbootdb.student.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
	List<Student> findByName(String name); 
	List<Student> findAll(); 

}
