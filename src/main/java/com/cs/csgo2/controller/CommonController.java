package com.cs.csgo2.controller;


import com.cs.csgo2.common.PythonUtils;
import com.cs.csgo2.common.Res;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonController {
    String basePath = System.getProperty("user.dir") + "/src/main/resources/images/avatar/";
    @PostMapping("/uploadAvatar")
    public Res<String> uploadAvatar(MultipartFile file) throws IOException {
        String oriName=file.getOriginalFilename();
        String suffix = oriName.substring(oriName.lastIndexOf("."));
        String fileName= UUID.randomUUID().toString();
        file.transferTo(new File(basePath+fileName+suffix));
        return Res.success(fileName+suffix);
    }
    @PostMapping("/usePython")
    public Res<String> usePython(String source,String goodsId){
        PythonUtils.getHistory(source,goodsId);
        return Res.success("获取成功");
    }
//    @PostMapping("/deleteAvatar")
//    public Res<String> deleteAvatar(String filename) {
//        if(FileSystemUtils.deleteRecursively(new File(basePath+filename)))
//            return Res.success("删除成功");
//        else
//            return Res.error("删除失败");
//    }
}
