package com.nagwa.filedownloader.utils.crash;

public class LogData {
    Log Log;

    public LogData(LogData.Log log) {
        Log = log;
    }

    static public class Log{
        String Message;

        public Log(String message) {
            Message = message;
        }
    }
}
