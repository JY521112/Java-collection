package com.jy.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-22 10:58
 * @info log4j
 * 此类只是代码演示 测试可以再测试类中演示  因为static执行在Commons_Logging_之前。
 * 需要配置文件resource下的commons-Logging.properties
 */
public class Commons_Logging_ {
    private static Log log = LogFactory.getLog(Commons_Logging_.class);

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
