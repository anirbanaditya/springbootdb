package com.techcloudmicro.restapi.springbootdb.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techcloudmicro.restapi.springbootdb.repository.StudentJpaRepository;
import com.techcloudmicro.restapi.springbootdb.student.Student;

@RestController
public class StudentController {
	
	@Autowired
	private StudentJpaRepository studentJpaRepository;
	
	/*
	 * Retrieve all Todos for a user -> GET /users/username/todos
	 */
	@GetMapping("/jpa/students/{name}")
	public List<Student> getAllStudents(@PathVariable String name){
		return studentJpaRepository.findByName(name);
	}
	
	@GetMapping("/jpa/students")
	public List<Student> getAllStudents1(){
		return studentJpaRepository.findAll();
	}
	
	/*
	 * delete a Todo of a user  -> DELETE /users/username/todos/{id}
	 */
	
	
	@DeleteMapping("/jpa/students/{name}/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable String name , @PathVariable long id){
		//Todo todo=  todoService.deleteById(id);
		studentJpaRepository.deleteById(id);
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/jpa/students/{name}/{id}")
	public Student getTodos(@PathVariable String name, @PathVariable long id){
		return studentJpaRepository.findById(id).get();
	}
	
	/*
	 * Edit a Todo --> PUT /users/username/todos/{id}
	 */
	
	@PutMapping("/jpa/students/{name}/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable String name , @PathVariable long id, @RequestBody Student student){
		//Todo updatedTodo=todoService.save(todo);
		Student updatedStudent=studentJpaRepository.save(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	/*
	 * add a Todo -> POST /users/username/todos
	 */
	
	@PostMapping("/jpa/students/{name}")
	public ResponseEntity<Void> createStudent(@PathVariable String name , @RequestBody Student student){
		//Todo createdTodo=todoService.save(todo);
		student.setName(name);
		Student createdStudent=studentJpaRepository.save(student);
		//location ,  get current resource url ,  /users/{username}/todos/{id}
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdStudent.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
