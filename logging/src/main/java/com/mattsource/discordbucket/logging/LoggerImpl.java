package com.mattsource.discordbucket.logging;

import static org.slf4j.LoggerFactory.getLogger;

class LoggerImpl implements Logger {

    private final org.slf4j.Logger log;

    LoggerImpl(Class clazz) {
        log = getLogger(clazz);
    }

    public String getName() {
        return log.getName();
    }

    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    public void trace(String var1) {
        log.trace(var1);
    }

    public void trace(String var1, Object var2) {
        log.trace(var1, var2);
    }

    public void trace(String var1, Object var2, Object var3) {
        log.trace(var1, var2, var3);
    }

    public void trace(String var1, Object... var2) {
        log.trace(var1, var2);
    }

    public void trace(String var1, Throwable var2) {
        log.trace(var1, var2);
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public void debug(String var1) {
        log.debug(var1);
    }

    public void debug(String var1, Object var2) {
        log.debug(var1, var2);
    }

    public void debug(String var1, Object var2, Object var3) {
        log.debug(var1, var2, var3);
    }

    public void debug(String var1, Object... var2) {
        log.debug(var1, var2);
    }

    public void debug(String var1, Throwable var2) {
        log.debug(var1, var2);
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public void info(String var1) {
        log.info(var1);
    }

    public void info(String var1, Object var2) {
        log.info(var1, var2);
    }

    public void info(String var1, Object var2, Object var3) {
        log.info(var1, var2, var3);
    }

    public void info(String var1, Object... var2) {
        log.info(var1, var2);
    }

    public void info(String var1, Throwable var2) {
        log.info(var1, var2);
    }

    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    public void warn(String var1) {
        log.warn(var1);
    }

    public void warn(String var1, Object var2) {
        log.warn(var1, var2);
    }

    public void warn(String var1, Object... var2) {
        log.warn(var1, var2);
    }

    public void warn(String var1, Object var2, Object var3) {
        log.warn(var1, var2, var3);
    }

    public void warn(String var1, Throwable var2) {
        log.warn(var1, var2);
    }

    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    public void error(String var1) {
        log.error(var1);
    }

    public void error(String var1, Object var2) {
        log.error(var1, var2);
    }

    public void error(String var1, Object var2, Object var3) {
        log.error(var1, var2, var3);
    }

    public void error(String var1, Object... var2) {
        log.error(var1, var2);
    }

    public void error(String var1, Throwable var2) {
        log.error(var1, var2);
    }
}
