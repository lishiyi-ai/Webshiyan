package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Course;
import org.example.pojo.School;

import java.util.List;

public interface CourseMapper {

    //查询所有课程按照课程的学院进行排名
    @Select("select * from c_course c join s_school s on c.sid = s.id order by c.sid asc;")
    List<Course> selectAll();
    @Select("<script>" +
            "SELECT * FROM c_course join s_school on c_course.sid = s_school.id " +
            "         <where>" +
            "            <if test=\"id!=null\"> AND c_course.id=#{id}</if>" +
            "            <if test=\"name!=null\"> AND name like CONCAT('%', #{name}, '%')</if>" +
            "            <if test=\"hours!=null\"> AND hours=#{hours}</if>" +
            "            <if test=\"sid!=null\"> AND sid=#{sid}</if>" +
            "            order by c_course.id" +
            "        </where>"+
            "</script>")
    //按条件查询4
    List<Course> select(Course course);
    @Select("select * from c_course where id=#{id}")
    Course findById(Integer id);
    @Select("select * from c_course where id!=#{id} and name=#{name} limit 1")
    Course findByIdAndName(Course course);

    @Select("select c_course.* from c_course,s_school" +
            " where c_course.sid=s_school.id " +
            "and s_school.schoolname=#{schools}")
    List<Course> findAllBySchoolname(String schools);
    @Select("select * from c_course where name=#{name}")
    Course findByName(String name);

    int update(Course course);
    @Insert("insert into c_course(name,hours,sid,image) values(#{name},#{hours},#{sid},#{image})")
    int insert(Course course);

    @Update("update c_course set image=null where id=#{id}")
    int update1(@Param("id")Integer id);
    @Delete("delete from c_course where id=#{id}")
    int delete(Integer id);

    @Select("select * from s_school where id=#{id}")
    School selectSchool(Integer id);

    @Select("select * from s_school")
    List<School> allSchool();
}
