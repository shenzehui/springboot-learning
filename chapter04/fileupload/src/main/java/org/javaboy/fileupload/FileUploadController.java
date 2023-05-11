package org.javaboy.fileupload;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author szh
 */
@RestController
public class FileUploadController {

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    /**
     * 单文件上传
     *
     * @param file
     * @param req
     * @return
     */
    @PostMapping("/upload")
    // 注意：这里 file 要与 index.html 中 name 属性值一致
    public String upload(MultipartFile file, HttpServletRequest req) {
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/img") + format;
        File folder = new File(realPath);
        // 不存在则创建
        if (!folder.exists()) {
            // 创建多级目录 区别：mkdir()一级目录
            folder.mkdirs();
        }
        // 获取旧文件名 目的是获取文件后缀
        String oldName = file.getOriginalFilename();
        // 获取新的文件名
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            // 保存文件
            file.transferTo(new File(folder, newName));
            // req.getScheme 获取协议
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + format + newName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 多文件上传
     *
     * @param files 文件数组
     * @param req
     * @return
     */
    @PostMapping("/uploads")
    public String uploads(MultipartFile[] files, HttpServletRequest req) {
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/img") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (MultipartFile file : files) {
            // 获取旧文件名
            String oldName = file.getOriginalFilename();
            // 获取新的文件名
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
            try {
                file.transferTo(new File(folder, newName));
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + format + newName;
                System.out.println(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    /**
     * 不同类型文件上传
     */
    @PostMapping("/upload3")
    public String upload3(MultipartFile file1, MultipartFile file2, HttpServletRequest req) {
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/img") + format;
        File folder = new File(realPath);
        // 不存在则创建
        if (!folder.exists()) {
            // 创建多级目录 区别：mkdir()一级目录*
            folder.mkdirs();
        }
        try {
            // 获取旧文件名，目的是获取文件后缀
            String oldName1 = file1.getOriginalFilename();
            // 获取新的文件名
            String newName1 = UUID.randomUUID().toString() + oldName1.substring(oldName1.lastIndexOf("."));

            file1.transferTo(new File(folder, newName1));
            // req.getScheme 获取协议
            String url1 = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + format + newName1;

            String oldName2 = file2.getOriginalFilename();
            // 获取新的文件名
            String newName2 = UUID.randomUUID().toString() + oldName2.substring(oldName2.lastIndexOf("."));

            file2.transferTo(new File(folder, newName2));
            // req.getScheme 获取协议
            String url2 = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + format + newName2;
            System.out.println(url1);
            System.out.println(url2);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
