/*
 * File: src\main\java\com\taylor\jpa\orm\repositories\StudentRepositoryCustom.java
 * Project: jpa
 * Created Date: Thursday, October 19th 2023, 10:18:53 am
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Friday, 20th October 2023 4:41:08 pm
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

import java.util.List;
import java.util.Set;

import com.taylor.jpa.orm.entities.Student;

public interface StudentRepositoryCustom {

    List<Student> findLikeNames(Set<String> names);
}
