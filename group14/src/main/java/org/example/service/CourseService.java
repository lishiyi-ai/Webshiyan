package org.example.service;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Course;
import org.example.pojo.School;

import java.util.List;

public interface CourseService {
    public Course findById(Integer id);
    public List<Course> findAllBySchoolname(String schools);
    public int update(Course course);
    public int insert(Course course);
    public List<Course> selectAll();
    public Course findByName(String name);
    int delete(Integer id);
    public List<Course> select(Course course);
    public int update1(Integer id);
    List<School> allSchool();
}
