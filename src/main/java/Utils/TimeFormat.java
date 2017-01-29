package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * Created by Administrator on 2016/10/5.
 */
public class TimeFormat {
    public static String format(Long date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static String format2yyyy_MM_dd(Long date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static Long data2Timestamp(String yyyy_MM_dd){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(yyyy_MM_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
    public static Long data2Timestamp(int year,int month,int day){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        String time = year+"-"+month+"-"+day;
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static int getThisYear(Long null_ts) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return Integer.parseInt(df.format(null_ts==null?System.currentTimeMillis():null_ts));
    }

    public static int getThisMonth(Long null_ts) {
        SimpleDateFormat df = new SimpleDateFormat("MM");
        return Integer.parseInt(df.format(null_ts==null?System.currentTimeMillis():null_ts));
    }

    public static int getThisDay(Long null_ts) {
        SimpleDateFormat df = new SimpleDateFormat("dd");
        return Integer.parseInt(df.format(null_ts==null?System.currentTimeMillis():null_ts));
    }


}
