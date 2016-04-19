package com.shredder.utils;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LogSplitter {
    public enum LEVEL {VERBOSE, DEBUG, INFO, WARNING, ERROR}

    private static int MAX_MSG_LENGTH = 2048;
    private final String tag;
    private LEVEL minimumLogLevel = LEVEL.VERBOSE;

    public void setMinimumLogLevel(LEVEL newLogLevel){
        minimumLogLevel = newLogLevel;
    }

    public LogSplitter(Class classId) {
        this.tag = classId.getSimpleName();
    }

    public void v(String s) {
        verbose(s);
    }

    public void verbose(String msg) {
        log(LEVEL.VERBOSE, msg);
    }

    public void d(String s) {
        debug(s);
    }

    public void debug(String msg) {
        log(LEVEL.DEBUG, msg);
    }

    public void i(String s) {
        info(s);
    }

    public void info(String msg) {
        log(LEVEL.INFO, msg);
    }

    public void w(String s) {
        warning(s);
    }

    public void warning(String msg) {
        log(LEVEL.WARNING, msg);
    }

    public void e(String s) {
        error(s);
    }

    public void error(String msg) {
        log(LEVEL.ERROR, msg);
    }

    public void ex(Exception s) {
        exception(s);
    }

    public void exception(Exception e) {
        exception(null, e);
    }

    public void exception(String msg, Exception e) {
        log(LEVEL.VERBOSE, msg);
        if (e != null) {
            e.printStackTrace();
        }
    }

    protected void log(LEVEL level, String msg) {
        if (!shouldLog(level)) {
            return;
        }
        for (String s : split(msg)) {
            switch (level){
                case VERBOSE:
                    Log.v(tag, s);
                    break;
                case DEBUG:
                    Log.d(tag, s);
                    break;
                case INFO:
                    Log.i(tag, s);
                    break;
                case WARNING:
                    Log.w(tag, s);
                    break;
                case ERROR:
                    Log.e(tag, s);
                    break;
            }
        }
    }

    private boolean shouldLog(LEVEL requestedLogLevel) {
        return requestedLogLevel.ordinal() >= minimumLogLevel.ordinal();
    }

    private List<String> split(String msg) {
        List<String> fragments = new ArrayList<>();
        int start = 0;
        int end;
        boolean cont;
        String current;
        do {
            end = Math.min(MAX_MSG_LENGTH, msg.length() - start);
            current = msg.substring(start, start + end);
            if (!StringUtils.isEmpty(current)) {
                fragments.add(current);
            }
            start = start + end;
            cont = start < msg.length() - 1;
        } while (cont);
        return fragments;
    }
}
