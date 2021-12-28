package com.jy.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-22 14:30
 * slf4j配合log4j使用
 * 需要在resource下配置log4j.xml
 */
public class Slf4j_ {
   static Logger logger = LoggerFactory.getLogger(Slf4j_.class);

    public static void main(String[] args) {
        logger.error("error log..");
        logger.warn("warn log..");
        logger.debug("debug log..");
        logger.info("info log..");
        logger.trace("trace log..");
    }
}
