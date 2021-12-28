package com.jy.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-22 14:57
 *
 * logBack配合slf4j实现日志
 * 在resource目录下配置文件logback.xml
 */
public class LogBack_ {
  static Logger logger = LoggerFactory.getLogger(LogBack_.class);

    public static void main(String[] args) {
        logger.error("error log..");
        logger.warn("warn log..");
        logger.debug("debug log..");
        logger.info("info log..");
        logger.trace("trace log..");
    }
}
