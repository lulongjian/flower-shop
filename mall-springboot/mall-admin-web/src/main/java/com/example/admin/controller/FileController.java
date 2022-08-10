package com.example.admin.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.domain.ProductDO;
import com.example.mall.domain.vo.ProductVO;
import com.example.mall.service.SysFileService;
import com.example.mall.utils.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("file")
@Slf4j
public class FileController {

    @Autowired
    private SysFileService sysFileService;


    // 获取单个文件base64
    @RequestMapping(value = "/getFileBase64", method = RequestMethod.POST)
    public JsonModel getFileList(String url) {
        JsonModel jsonModel = new JsonModel();
        try {
            // 获取base64
            String base64 = Base64Utils.GetImageStr(url);
            jsonModel.setCode(0);
            jsonModel.setData(base64);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(500);
            jsonModel.setMsg("系统异常，请联系管理员！");
        }
        return jsonModel;
    }

    // 上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonModel upload(@RequestParam("file") MultipartFile file) {
        JsonModel jsonModel = new JsonModel();
        try {
            String root = System.getProperty("user.dir") + "\\files";
            //要存入本地的地址放到path里面
            Path path = Paths.get(root + "/");
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(path);
            }
            //获取最后一个.的位置
            String fileName = file.getOriginalFilename();
            int lastIndexOf = fileName.lastIndexOf(".");

            //获取文件的后缀名
            String suffix = fileName.substring(lastIndexOf);
            String fileAbsPath = String.valueOf(Math.random()) + suffix;
            File newFile = new File(path + "/" + fileAbsPath);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            file.transferTo(newFile);
            jsonModel.setCode(0);
            ProductVO productVO = new ProductVO();
            productVO.setImgUrl(fileAbsPath);
            productVO.setImgUrlBase64(Base64Utils.GetImageStr(fileAbsPath));
            jsonModel.setData(productVO);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(500);
            jsonModel.setMsg("系统异常，请联系管理员！");
        }
        return jsonModel;
    }
}






















