package com.jy.springbootTest.utils;

import com.jy.springbootTest.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2022-01-11 10:07
 */
@Configuration
@EnableScheduling
public class PictureTask {

    @Autowired
    private CameraService cameraService;

//    @Scheduled(cron = "0/5 * * * * ?")
    public void cutPicture(){
        String s = cameraService.takePictures();
        System.out.println(s);
    }
}
