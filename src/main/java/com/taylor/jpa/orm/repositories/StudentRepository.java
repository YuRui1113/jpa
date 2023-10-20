/*
 * File: src\main\java\com\taylor\jpa\orm\repositories\StudentRepository.java
 * Project: jpa
 * Created Date: Wednesday, October 18th 2023, 4:24:34 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 20th October 2023 4:40:52 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Friday, October 20th 2023	Rui Yu		Initial version
 */

package com.taylor.jpa.orm.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taylor.jpa.orm.entities.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {

        Optional<Student> findByName(String name);

        @Query("SELECT s FROM Student s WHERE s.sex = :sex")
        List<Student> findBySex(@Param("sex") Boolean sex);

        @Query(value = "SELECT * FROM students s WHERE s.age = :age", nativeQuery = true)
        List<Student> findByAge(@Param("age") int age);

        @Query("SELECT s FROM Student s")
        List<Student> findAllWithSort(Sort sort);

        @Query("SELECT s FROM Student s ORDER BY id")
        Page<Student> findAllWithPagination(Pageable pageable);

        @Query(value = "SELECT * FROM students ORDER BY id", countQuery = "SELECT count(*) FROM students", nativeQuery = true)
        Page<Student> findAllWithPaginationNative(Pageable pageable);

        @Query("SELECT s FROM Student s WHERE s.sex = ?1 and s.age = ?2")
        List<Student> findBySexAndAge(Boolean sex, Integer age);

        @Query(value = "SELECT s FROM Student s WHERE s.name IN :names")
        List<Student> findByNameList(@Param("names") Collection<String> names);

        @Transactional
        @Modifying
        @Query("INSERT INTO Student (name, sex, age, description) VALUES (:name, :sex, :age, :description)")
        void addNewStudent(@Param("name") String name,
                        @Param("sex") Boolean sex,
                        @Param("age") Integer age,
                        @Param("description") String description);

        @Transactional
        @Modifying
        @Query(value = "INSERT INTO students (name, sex, age, description) VALUES (:name, :sex, :age, :description)", nativeQuery = true)
        void addNewStudentNative(@Param("name") String name,
                        @Param("sex") Boolean sex,
                        @Param("age") Integer age,
                        @Param("description") String description);

        @Transactional
        @Modifying
        @Query("UPDATE Student s SET s.score = :score WHERE name = :name")
        void updateScore(@Param("name") String name,
                        @Param("score") Integer score);

        @Transactional
        @Modifying
        @Query(value = "UPDATE students s SET s.score = :score WHERE name = :name", nativeQuery = true)
        void updateScoreNative(@Param("name") String name,
                        @Param("score") Integer score);

        @Transactional
        @Modifying
        @Query("DELETE Student WHERE name = :name")
        void deleteStudent(@Param("name") String name);

        @Transactional
        @Modifying
        @Query(value = "DELETE students WHERE name = :name", nativeQuery = true)
        void deleteStudentNative(@Param("name") String name);
}