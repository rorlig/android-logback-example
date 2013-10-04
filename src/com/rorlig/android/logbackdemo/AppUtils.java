package com.rorlig.android.logbackdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 10/2/13
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppUtils {

    //path of the log directory
    private static final String LOG_DIRECTORY  = "/data/data/com.rorlig.android.logbackdemo/files/log/";
    private static Logger LOG = LoggerFactory.getLogger(AppUtils.class);


    public static void sendLogs(Context context, String emailTo, String emailCC,
                             String subject, String emailText, List<String> filePaths)
    {
        //need to "send multiple" to get more than one attachment
        LOG.debug("AppUtils:sendLogs");
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("text/xml");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[]{emailTo});
        emailIntent.putExtra(android.content.Intent.EXTRA_CC,
                new String[]{emailCC});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
        //has to be an ArrayList
        ArrayList<Uri> uris = new ArrayList<Uri>();
        //convert from paths to Android friendly Parcelable Uri's
        for (String file : filePaths)
        {
            File fileIn = new File(file);
            Uri u = Uri.fromFile(fileIn);
            uris.add(u);
        }
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
//        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(emailIntent);
    }

    public static void sendLogsAndDelete(Context context, String emailTo, String emailCC,
                                 String subject, String emailText, List<String> filePaths)
    {
        //need to "send multiple" to get more than one attachment
        sendLogs(context, emailTo, emailCC,
                subject, emailText, filePaths);
    }

    /*
    *
    */
    //todo make sure that latest file is not deleted completely - logback does not regenerate the file...
    //may be recreate the empty file...
    public static void deleteLogs(Context context){

        File f = new File(LOG_DIRECTORY);
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        String todayString =   dateFormat.format(today);
        LOG.debug("date today is " + todayString);

        for (File file :f.listFiles() ){
            if (file.exists()) {
                if (file.delete()) {
//                    System.out.println("file Deleted :" + filePath);
                } else {
//                    System.out.println("file not Deleted :" + filePath);
                }
            }
        }
        File newFile = new File(LOG_DIRECTORY + "logFile." + dateFormat.format(today) + ".log");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Toast.makeText(context, "Deleted All Logs", Toast.LENGTH_LONG).show();

    }

    /*
     * Sends all the logs as attachment via email...
     * @param boolean delete ; if true delete the files..
     */
    public static void sendAllLogs(Context context, String emailTo, String emailCC,
                                   String subject, String emailText, boolean delete) {
        //build list of files


        File f = new File(LOG_DIRECTORY);
        File file[] = f.listFiles();
        List<String> filePaths = new ArrayList<String>();
//        Log.d("Files", "Size: "+ file.length);
        for (int i=0; i < file.length; i++)
        {
             filePaths.add(file[i].getAbsolutePath());
//            Log.d("Files", "FileName:" + file[i].getName());
        }
        sendLogs(context, emailTo, emailCC, subject, emailText, filePaths);
//        if (delete) {
//            deleteLogs(context, filePaths);
//        }
        //if true - delete...

    }

    /*
     * Send the logs from the last week
     * @param boolean delete ; if true delete the files..
     */

    public static void sendWeeklyLogs(Context context, String emailTo, String emailCC,
                                      String subject, String emailText, boolean delete){

    }

     /*
     * Send the logs from the last month
     * @param boolean delete ; if true delete the files..
     */

    public static void sendMonthlyLogs(Context context, String emailTo, String emailCC,
                                       String subject, String emailText, boolean delete){

    }


}
