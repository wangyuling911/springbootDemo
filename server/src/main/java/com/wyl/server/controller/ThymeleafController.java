package com.wyl.server.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {

    @RequestMapping("hello")
    public String hello(Map<String,Object> map) {
        map.put("msg", "Hello Thymeleaf");
        return "hello";
    }
    @RequestMapping("Upfild")
    public void Upfild(HttpServletRequest request, HttpServletResponse response) {
        try {
        // 1. 创建工厂对象
        FileItemFactory factory = new DiskFileItemFactory();
        // 2. 文件上传核心工具类
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置大小限制参数
        upload.setFileSizeMax(10*1024*1024); // 单个文件大小限制
        upload.setSizeMax(50*1024*1024); // 总文件大小限制
        upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理
        // 判断
        if (upload.isMultipartContent(request)) {
            // 3. 把请求数据转换为list集合
            List<FileItem> list = upload.parseRequest(request);
            // 遍历
            for (FileItem item : list){
                // 判断：普通文本数据
                if (item.isFormField()){
                    // 获取名称
                    String name = item.getFieldName();
                    // 获取值
                    String value = item.getString();
                    System.out.println(value);
                }
                // 文件表单项
                else {
                    /******** 文件上传 ***********/
                    // a. 获取文件名称
                    String name = item.getName();
                    // ----处理上传文件名重名问题----
                    // a1. 先得到唯一标记
                    String id = UUID.randomUUID().toString();
                    // a2. 拼接文件名
                    name = id + "#" + name;
                    // b. 得到上传目录
                    String basePath = request.getServletContext().getRealPath("/upload");
                    // c. 创建要上传的文件对象
                    File file = new File("D://tmp",name);
                    // d. 上传
                    item.write(file);
                    item.delete();  // 删除组件运行时产生的临时文件
                }
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

