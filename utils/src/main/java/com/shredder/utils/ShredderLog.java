package com.shredder.utils;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

public class ShredderLog {
    private static int MAX_MSG_LENGTH = 2048;
    private final String tag;
    private boolean showVerboseMsgs = true;
    private boolean showDebugMsgs = true;
    private boolean showInfoMsgs = true;
    private final boolean showWarningMsgs = true;
    private final boolean showErrorMsgs = true;

    @SuppressWarnings("rawtypes")
    public ShredderLog(Class classId) {
        this.tag = classId.getSimpleName();
    }

    @SuppressWarnings("rawtypes")
    public ShredderLog(Class classId, boolean showVerbose, boolean showDebug, boolean showInfo) {
        this(classId);
        this.showVerboseMsgs = showVerbose;
        this.showDebugMsgs = showDebug;
        this.showInfoMsgs = showInfo;
    }

    public boolean showVerbose() {
        return showVerboseMsgs;
    }

    public boolean showDebug() {
        return showDebugMsgs;
    }

    public boolean showInfo() {
        return showInfoMsgs;
    }

    public boolean showWarning() {
        return showWarningMsgs;
    }

    public boolean showError() {
        return showErrorMsgs;
    }

    public void v(String s) {
        verbose(s);
    }

    public void verbose(String msg) {
        if (showVerboseMsgs) {
            if (msg.length() < MAX_MSG_LENGTH) {
                Log.v(tag, msg);
            } else { // }

                int start = 0;
                int end = 0;
                boolean cont = true;
                String current = msg;
                do {
                    end = Math.min(2048, msg.length() - start);
                    current = msg.substring(start, start + end);
                    if (!StringUtils.isEmpty(current))
                        Log.v(tag, current);
                    start = start + end;
                    cont = start < msg.length() - 1;
                } while (cont);
            }
        }
    }

    public void d(String s) {
        debug(s);
    }

    public void debug(String msg) {
        if (showDebugMsgs) {
            if (msg.length() < MAX_MSG_LENGTH) {
                Log.d(tag, msg);
            } else {
                int start = 0;
                int end = 0;
                boolean cont = true;
                String current = msg;
                do {
                    end = Math.min(2048, msg.length() - start);
                    current = msg.substring(start, start + end);
                    if (!StringUtils.isEmpty(current))
                        Log.d(tag, current);
                    start = start + end;
                    cont = start < msg.length() - 1;
                } while (cont); // }

            }
        }
    }

    public void i(String s) {
        info(s);
    }

    public void info(String msg) {
        if (showInfoMsgs) {
            if (msg.length() < MAX_MSG_LENGTH) {
                Log.i(tag, msg);
            } else {
                int start = 0;
                int end = 0;
                boolean cont = true;
                String current = msg;
                do {
                    end = Math.min(2048, msg.length() - start);
                    current = msg.substring(start, start + end);
                    if (!StringUtils.isEmpty(current))
                        Log.i(tag, current);
                    start = start + end;
                    cont = start < msg.length() - 1;
                } while (cont);
            }
        }
    }

    public void w(String s) {
        warning(s);
    }

    public void warning(String msg) {
        if (showWarningMsgs) {
            if (msg.length() < MAX_MSG_LENGTH) {
                Log.w(tag, msg);
            } else {
                int start = 0;
                int end = 0;
                boolean cont = true;
                String current = msg;
                do {
                    end = Math.min(2048, msg.length() - start);
                    current = msg.substring(start, start + end);
                    if (!StringUtils.isEmpty(current))
                        Log.w(tag, current);
                    start = start + end;
                    cont = start < msg.length() - 1;
                } while (cont);
            }
        }
    }

    public void e(String s) {
        error(s);
    }

    public void error(String msg) {
        if (!StringUtils.isEmpty(msg))
            if (showErrorMsgs) {
                if (msg.length() < MAX_MSG_LENGTH) {
                    Log.e(tag, msg);
                } else {
                    int start = 0;
                    int end = 0;
                    boolean cont = true;
                    String current = msg;
                    do {
                        end = Math.min(2048, msg.length() - start);
                        current = msg.substring(start, start + end);
                        if (!StringUtils.isEmpty(current))
                            Log.e(tag, current);
                        start = start + end;
                        cont = start < msg.length() - 1;
                    } while (cont);
                }
            }
    }

    public void ex(Exception s) {
        exception(s);
    }

    public void exception(Exception e) {
        exception(null, e);
    }

    public void exception(String msg, Exception e) {
        if (!StringUtils.isEmpty(msg))
            Log.e(tag, msg);

        if (e != null)
            e.printStackTrace();
    }
}
