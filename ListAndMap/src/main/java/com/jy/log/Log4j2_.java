package com.jy.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-22 15:18
 * log4j2  是log4j的升级 性能显著提升
 * 需要在resource路径下配置文件log4j2.xml
 */
public class Log4j2_ {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        logger.trace("trace log..");
        logger.debug("debug log..");
        logger.info("info log..");
        logger.warn("warn log..");
        logger.error("error log..");
        logger.fatal("fatal log..");
    }
}
