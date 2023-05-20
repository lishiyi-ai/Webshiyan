package org.example.service.impl;

import org.example.mapper.CourseMapper;
import org.example.pojo.Course;
import org.example.pojo.School;
import org.example.utils.codeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.service.CourseService;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Service
@Transactional
public
class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public Course findById(Integer id){
        Course course=courseMapper.findById(id);
        return course;
    }
    public List<Course> findAllBySchoolname(String schools){
        List<Course> courses=courseMapper.findAllBySchoolname(schools);
        return courses;
    }
    public int update(Course course){
        System.out.println(course);
        int rs=courseMapper.update(course);
        return rs;
    }
    public int insert(Course course){
        Course course1=courseMapper.findByName(course.getName());
        System.out.println(course1);
        if(course1!=null){
            return 0; //课程名存在
        }
        School school=courseMapper.selectSchool(course.getSid());
        if(school==null){
            return -1; //学院编号不存在
        }
        int rs=courseMapper.insert(course);
        return rs;
    }
    public List<Course> selectAll(){
        List<Course> courses=courseMapper.selectAll();
        return courses;
    }
    public List<Course> select(Course course){
        String name;
        name=course.getName();
        // 编码转换
        name= codeUtils.encoding(name);
        course.setName(name);
        System.out.println(course);
        List<Course> courses=courseMapper.select(course);
        System.out.println(courses);
        return courses;
    }
    public Course findByName(String name){
        Course course=courseMapper.findByName(name);
        return course;
    }
    public int delete(Integer id){
        int rs=courseMapper.delete(id);
        return rs;
    }
    public int update1(Integer id){
        int rs=courseMapper.update1(id);
        return rs;
    }
    public List<School> allSchool(){
        List<School> schools=courseMapper.allSchool();
        return  schools;
    }
}
