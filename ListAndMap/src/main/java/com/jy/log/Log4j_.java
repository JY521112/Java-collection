package com.jy.log;

import org.apache.log4j.Logger;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-22 11:16
 *
 * @info Log4j的日志类
 * 需要在resource下添加log4j.properties文件配置或者.xml配置
 */
public class Log4j_ {
    private static Logger log = Logger.getLogger(Log4j_.class);

    public static void main(String[] args) {
        //日志级别trace->debug->info->warn->error
        log.trace("trace log..");
        log.debug("debug log...");
        log.info("info log...");
        log.warn("warn log...");
        log.error("error log...");
        log.fatal("fatal log...");
    }
}
