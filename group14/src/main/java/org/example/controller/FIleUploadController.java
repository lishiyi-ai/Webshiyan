package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping("File")
@Controller
public class FIleUploadController {
    @RequestMapping("fileload")
    public Result fileLoad(MultipartFile[] files) throws Exception {
        //设置上传的文件所存放的路径
        String path = "E:\\IDEA-pro\\springst\\group14\\src\\main\\webapp\\image/";
//        System.out.println(path);
        ObjectMapper mapper = new ObjectMapper();
        if (files==null){
            System.out.println("null");
        }
        if (files != null && files.length > 0) {
            //循环获取上传的文件
            for (MultipartFile file : files) {
                //获取上传文件的名称
                String filename = file.getOriginalFilename();
//                ArrayList<Resource> list = new ArrayList<>();
                //读取files.json文件中的文件名称
//                String json = JSONFileUtils.readFile(path + "/files.json");
//                if (json.length() != 0) {
//                    //将files.json的内容转为集合
//                    list = mapper.readValue(json,
//                            new TypeReference<List<Resource>>() {
//                            });
//                    for (Resource resource : list) {
//                        //如果上传的文件在files.json文件中有同名文件，将当前上传的文件重命名，以避免重名
//                        if (filename.equals(resource.getName())) {
//                            String[] split = filename.split("\\.");
//                            filename = split[0] + "(1)." + split[1];
//                        }
//                    }
//                }
                // 文件保存的全路径
                String filePath = path + filename;
                // 保存上传的文件
                file.transferTo(new File(filePath));
//                list.add(new Resource(filename));
//                json = mapper.writeValueAsString(list); //将集合中转换成json
                //将上传文件的名称保存在files.json文件中
//                JSONFileUtils.writeFile(json, path + "/files.json");
            }
//            request.setAttribute("msg", "(上传成功)");
            return Result.success();
        }
//        request.setAttribute("msg", "(上传失败)");
        return Result.error("上传失败");
    }
    @GetMapping("/{image}.{suffix}")
    @ResponseBody
    public Result image(@PathVariable String image,
                        @PathVariable String suffix){
        System.out.println(image+"."+suffix);
        String path = "E:\\IDEA-pro\\springst\\group14\\src\\main\\webapp\\image/";
        File file=new File(path+image+"."+suffix);
        System.out.println(file);
        return Result.success("你好");
    }
}
