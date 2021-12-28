package com.jy.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-22 10:43
 * @info JDK Logger 日志
 */
public class Logger_ {
    public static Logger logger=Logger.getLogger(Logger_.class.getName());
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    public static void main(String[] args) {
        //级别 ：all->final->finer->fine->config->info->warning->severe->off
        //级别依次升高，越往后日志级别会越高，打印的日志会越少，后面的日志级别会屏蔽前面的日志级别
        //将日志级别设置成all所有的日志信息都会看到
        logger.setLevel(Level.ALL);
        logger.finest("finest log..");
        logger.finer("finer log..");
        logger.fine("fine log..");
        logger.config("config log..");
        logger.info("info log..");
        logger.warning("warning log..");
        logger.severe("severe log..");

    }
}
