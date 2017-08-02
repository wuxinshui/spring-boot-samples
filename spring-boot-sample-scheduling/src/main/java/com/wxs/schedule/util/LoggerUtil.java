package com.wxs.schedule.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
	
   /**
   *Trace等级日志，小于debug<br>
   *由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void trace(String format, Object... arguments) {
      trace(innerGet(),format, arguments);
  }
 

  /**
   *Trace等级日志，小于Debug
   *@param log 日志对象
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void trace(Logger log, String format, Object... arguments) {
      log.trace(format,arguments);
  }


  //------------------------debug

  /**
   *Debug等级日志，小于Info<br>
   *由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void debug(String format, Object... arguments) {
      debug(innerGet(),format, arguments);
  }


  /**
   *Debug等级日志，小于Info
   *@param log 日志对象
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void debug(Logger log, String format, Object... arguments) {
      log.debug(format,arguments);
  }  

  //------------------------info

  /**
   *Info等级日志，小于Warn<br>
   *由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void info(String format, Object... arguments) {
      info(innerGet(),format, arguments);
  }
  

  /**
   *Info等级日志，小于Warn
   *@param log 日志对象
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void info(Logger log, String format, Object... arguments) {
      log.info(format,arguments);
  }
 

  //------------------------warn
  /**
   *Warn等级日志，小于Error<br>
   *由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void warn(String format, Object... arguments) {
      warn(innerGet(),format, arguments);

  }

   

  /**
   *Warn等级日志，小于Error
   *@param log 日志对象
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void warn(Logger log, String format, Object... arguments) {
      log.warn(format,arguments);
  }

   

  /**
   *Warn等级日志，小于Error<br>
   *由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
   *@param e 需在日志中堆栈打印的异常
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void warn(Throwable e, String format, Object... arguments) {
      warn(innerGet(),e, format(format, arguments));
  }

   

  /**
   *Warn等级日志，小于Error
   *@param log 日志对象
   *@param e 需在日志中堆栈打印的异常
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void warn(Logger log, Throwable e, String format, Object... arguments) {
      log.warn(format(format,arguments), e);
  }

   

  //------------------------error

  /**
   *Error等级日志<br>
   *由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void error(String format, Object... arguments) {
      error(innerGet(),format, arguments);
  }

   

  /**
   *Error等级日志<br>
   *@param log 日志对象
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void error(Logger log, String format, Object... arguments) {
      log.error(format,arguments);
  }

   

  /**
   *Error等级日志<br>
   *由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
   *@param e 需在日志中堆栈打印的异常
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void error(Throwable e, String format, Object... arguments) {
      error(innerGet(),e, format(format, arguments));
  }


  /**
   *Error等级日志<br>
   *由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
   *@param log 日志对象
   *@param e 需在日志中堆栈打印的异常
   *@param format 格式文本，{} 代表变量
   *@param arguments 变量对应的参数
   */
  public static void error(Logger log, Throwable e, String format, Object... arguments) {
      log.error(format(format,arguments), e);
  }

  //-----------------------------------------------------------Logger method end
   

  /**
   *格式化文本
   *@param template 文本模板，被替换的部分用 {} 表示
   *@param values 参数值
   *@return 格式化后的文本
   */
  private static String format(String template, Object... values) {
      return String.format(template.replace("{}","%s"),values);
  }

   

  /**
   *@return 获得日志，自动判定日志发出者
   */
  private static Logger innerGet() {
      StackTraceElement[]stackTrace = Thread.currentThread().getStackTrace();
      return LoggerFactory.getLogger(stackTrace[3].getClassName());
  }
}
