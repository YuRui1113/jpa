/*
 * File: src\main\java\com\taylor\jpa\JpaApplication.java
 * Project: jpa
 * Created Date: Wednesday, October 18th 2023, 3:48:47 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 20th October 2023 4:41:52 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Friday, October 20th 2023	Rui Yu		Initial version
 */

package com.taylor.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.taylor.jpa.orm.entities.Student;
import com.taylor.jpa.orm.repositories.StudentRepository;

@SpringBootApplication
public class JpaApplication {

	private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner test(StudentRepository repository) {
		return args -> {
			// Initialize test data
			if (repository.findAll().size() < 1) {
				log.info("Initialize test data");
				initializeTestData(repository);
			}

			// Fetch all students
			logHeader("Students found with findAll():");
			logStudents(repository.findAll());

			// Fetch student by name
			logStudentWithName(repository, "Taylor");

			// Fetch students by sex
			logHeader("Students found with findBySex(false):");
			logStudents(repository.findBySex(false));

			// Fetch all students order by age
			logHeader("Students found with findAll(age ASC):");
			logStudents(repository.findAll(Sort.by(Sort.Direction.ASC, "age")));

			// Fetch students in page 1 when page size = 3
			logHeader("Students found with findAllWithPagination(page 1,size 3):");
			Pageable pageable = PageRequest.of(0, 3);
			logStudents(repository.findAllWithPagination(pageable).getContent());

			// Fetch students by indexed query parameters
			logHeader("Students found with findBySexAndAge(true, 45):");
			logStudents(repository.findBySexAndAge(true, 45));

			// Fetch students by name with collection parameters
			logHeader("Students found with findByNameList({\"Taylor\",\"Thomas\"}):");
			Set<String> names = new HashSet<String>();
			names.add("Taylor");
			names.add("Thomas");
			logStudents(repository.findByNameList(names));

			// Add new student
			logHeader("Add a new student with addNewStudent(\"Rob\"...):");
			repository.addNewStudent("Rob", true, 50, "I am Rob");
			logStudentWithName(repository, "Rob");

			// Update student name = Rob with score = 100
			logHeader("Update student with updateScore(\"Rob\", 100):");
			repository.updateScore("Rob", 100);
			logStudentWithName(repository, "Rob");

			// Delete student name = Rob
			logHeader("Delete student with deleteStudent(\"Rob\"):");
			repository.deleteStudent("Rob");
			logStudentWithName(repository, "Rob");

			// Fetch students by dynamic query
			logHeader("Students found with findLikeNames({\"om\",\"ry\"):");
			Set<String> likeNames = new HashSet<String>();
			likeNames.add("om");
			likeNames.add("ry");
			logStudents(repository.findLikeNames(likeNames));
		};
	}

	private void initializeTestData(StudentRepository repository) {
		repository.save(new Student("Taylor", true, 44, "I am Taylor"));
		repository.save(new Student("Tommy", true, 45, "I am Tommy"));
		repository.save(new Student("Marisa", false, 40, "I am Marisa"));
		repository.save(new Student("Henry", true, 47, "I am Henry"));
		repository.save(new Student("Jerry", true, 45, "I am Jerry"));
		repository.save(new Student("Wavel", false, 44, "I am Wavel"));
		repository.save(new Student("Ray", true, 47, "I am Ray"));
		repository.save(new Student("Thomas", true, 43, "I am Thomas"));
	}

	private void logStudentWithName(StudentRepository repository, String name) {
		logHeader("Student found with findByName(" + name + "):");
		Optional<Student> foundStudent = repository.findByName(name);
		log.info(foundStudent.isPresent() ? foundStudent.get().toString() : "Student with name " + name + " not found");
	}

	private void logHeader(String headerText) {
		log.info("");
		log.info("--------------------------------------------------------------------------");
		log.info(headerText);
		log.info("--------------------------------------------------------------------------");
	}

	private void logStudents(List<Student> students) {
		for (Student student : students) {
			log.info(student.toString());
		}
	}
}
