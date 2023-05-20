package org.example.controller;

import org.example.pojo.Course;
import org.example.pojo.Result;
import org.example.pojo.School;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@CrossOrigin //解决跨域问题
@Controller
@RequestMapping("/Course")
public class CourseController implements Serializable {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/findById")
    @ResponseBody
    public Result findById(Integer id){
        Course course=courseService.findById(id);
        String path = "E:\\IDEA-pro\\springst\\group14\\src\\main\\webapp\\image/"+course.getImage();
        File file= new File(path);
        if(!file.exists()){
            course.setImage(null);
            courseService.update1(id);
            course=courseService.findById(id);
            System.out.println("修改后:"+course);
        }
        return Result.success(course);
    }
    @RequestMapping("/findAllBySchoolname")
    @ResponseBody
    public Result findAllBySchoolname(String schools){
        List<Course> courses=courseService.findAllBySchoolname(schools);
        return Result.success(courses);
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Course course){
        System.out.println(course.getId());
        Course course2=courseService.findById(course.getId());
        System.out.println(course2.getName());
        Course course1=courseService.findByName(course.getName());
        if (course1!=null&& !Objects.equals(course2.getName(), course1.getName())){
            System.out.println(course1);
            return Result.error("该课程已存在12");
        }
        int i=courseService.update(course);
        if(i>0) {
            return Result.success();
        }
        return Result.error("修改失败");
    }
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(Course course){
        System.out.println(course);
        Course course1=courseService.findByName(course.getName());
        if (course1!=null){
            return Result.error("该课程已存在");
        }
        int i=courseService.insert(course);
        if(i>0) {
            return Result.success();
        }
        return Result.error("添加失败，学院编号不存在或有其他错误 ");
    }
    @RequestMapping("/selectAll")
    @ResponseBody
    public Result selectAll(){
        List<Course> courses=courseService.selectAll();
        System.out.println(courses);
        return Result.success(courses);
    }
    @RequestMapping("/select")
    @ResponseBody
    public Result select(Course course){
        Course course1=courseService.findById(2);
        System.out.println(course1);
        List<Course> courses=courseService.select(course);
        return Result.success(courses);
    }
    @RequestMapping("/findByName")
    @ResponseBody
    public Result findByName(String name){
        Course course=courseService.findByName(name);
        return Result.success(course);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam(value = "id") Integer id){
//        System.out.println(id);
        int rs=courseService.delete(id);
        if(rs>0) {
            return Result.success();
        }
        return Result.error("删除失败");
    }
    @RequestMapping("/{image}.{suffix}")
    @ResponseBody
    public Result image(@PathVariable String image,
                        @PathVariable String suffix){
        System.out.println(image+"."+suffix);
        String path = "E:\\IDEA-pro\\springst\\group14\\src\\main\\webapp\\image/";
        File file=new File(path+image+"."+suffix);
        System.out.println(file);
        return Result.success("你好");
    }
    @RequestMapping("/allSchool")
    @ResponseBody
     public List<School> allSchool(){
        System.out.println("所有课程");
        List<School> schools=courseService.allSchool();
        System.out.println(schools);
        return schools;
     }
}