/*
 * File: src\main\java\com\taylor\jpa\services\StudentService.java
 * Project: jpa
 * Created Date: Wednesday, October 18th 2023, 4:39:35 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 20th October 2023 4:41:37 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Friday, October 20th 2023	Rui Yu		Initial version
 */

package com.taylor.jpa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taylor.jpa.orm.entities.Student;
import com.taylor.jpa.orm.repositories.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}