package com.jy.springbootTest.controller;

import com.jy.springbootTest.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-28 16:48
 */
@RestController
public class TestIdeaHttp {

    @Autowired
    private CameraService cameraService;

    @GetMapping("/getString")
    public String getString(){


        String msg = cameraService.takePictures();
        return "<div><span>" + msg + "</span></div><div>" +
                "<span><a href=\"/ll?d=/picture\" >点击查看所有照片</a></span>" +
                "<span style=\"margin-left: 20px;\"><a href=\"/\" >返回首页</a></span></div>";

    }
}
