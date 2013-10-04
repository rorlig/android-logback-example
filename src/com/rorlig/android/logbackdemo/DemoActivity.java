package com.rorlig.android.logbackdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    Logger LOG;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // SLF4J
//        configureLogbackDirectly();

        LOG = LoggerFactory.getLogger(DemoActivity.class);


        for (int i=0;  i<100; ++i)
            LOG.info("hello world");
    }

//    private void configureLogbackDirectly() {
//        // reset the default context (which may already have been initialized)
//        // since we want to reconfigure it
//        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
//        lc.reset();
//
//        // setup FileAppender
//        PatternLayoutEncoder encoder1 = new PatternLayoutEncoder();
//        encoder1.setContext(lc);
//        encoder1.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
//        encoder1.start();
//
//        FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
//        fileAppender.setContext(lc);
////        fileAppender.setFile(this.getFileStreamPath("app.log").getAbsolutePath());
//        fileAppender.setFile("/mnt/sdcard/log-test/app.log");
//
//        fileAppender.setEncoder(encoder1);
//        fileAppender.start();
//
//
//        // setup LogcatAppender
//        PatternLayoutEncoder encoder2 = new PatternLayoutEncoder();
//        encoder2.setContext(lc);
//        encoder2.setPattern("[%thread] %msg%n");
//        encoder2.start();
//
//        LogcatAppender logcatAppender = new LogcatAppender();
//        logcatAppender.setContext(lc);
//        logcatAppender.setEncoder(encoder2);
//        logcatAppender.start();
//
//        // add the newly created appenders to the root logger;
//        // qualify Logger to disambiguate from org.slf4j.Logger
//        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
//        root.addAppender(fileAppender);
//        root.addAppender(logcatAppender);
//
//
////        LOG.info("filelocation is " + fileAppender.getFile());
//    }


    public void sendAllLogs(View v) {
        LOG.info("Enter sendALLLogs");
        AppUtils.sendAllLogs(this,"guptgaurav@gmail.com", "", "Application Logs", "All Logs from the device", false);
    }

    public void sendWeeklyLogs(View v) {
        LOG.info("Enter sendWeeklyLogs");
        Toast.makeText(getApplicationContext(), "Todo", Toast.LENGTH_LONG).show();
//        AppUtils.sendAllLogs(this,"guptgaurav@gmail.com", "", "Application Logs", "All Logs from the device", false);
    }

    public void sendDailyLogs(View v) {
        //todo not implemented...
        LOG.info("Enter sendDailyLogs");
        Toast.makeText(getApplicationContext(), "Todo", Toast.LENGTH_LONG).show();

//        AppUtils.sendAllLogs(this,"guptgaurav@gmail.com", "", "Application Logs", "All Logs from the device", false);
    }

    public void deleteAllLogs(View v) {
        LOG.info("Enter deleteAllLogs");
        AppUtils.deleteLogs(this);

    }

}
