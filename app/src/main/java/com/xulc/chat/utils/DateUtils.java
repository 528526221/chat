package com.xulc.chat.utils;

import java.io.IOError;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by acer on 2016/4/27.
 */
public class DateUtils {

    /**
     * 生成报废单号
     * @param timeStamp
     * @return
     */
    public static String ToBF(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyyMMddHHmmss");
        return "BF"+format.format(timeStamp);
    }

    /**
     * 时间戳转化为年月日时分
     * @param timeStamp
     * @return
     */
    public static String ToYMDHM(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return format.format(timeStamp);
    }
    /**
     * 时间戳转化为年月日
     * @param timeStamp
     * @return
     */
    public static String ToYMD(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(timeStamp);
    }

    /**
     * 时间戳转化为月日时分
     * @param timeStamp
     * @return
     */

    public static String ToMDHM(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("MM月dd日HH:mm");
        return format.format(timeStamp);
    }

    /**
     * 时间戳转化为月日
     * @param timeStamp
     * @return
     */
    public static String ToMD(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("MM月dd日");
        return format.format(timeStamp);
    }

    /**
     * 时间戳转化为时分
     * @param timeStamp
     * @return
     */
    public static String ToHM(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("HH:mm");
        return format.format(timeStamp);
    }




    /**
     * time 按照yyyy-mm-dd格式传入日期，返回时间戳
     * @param time
     * @return
     */
    public static long YMDSigeToTimeStamp(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date.getTime();
    }

    public static long YMDToTimeStamp(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date.getTime();
    }


    //获取当前时间日期毫秒数
    public static long GetcurrentTimeMilliseconds(){
        return new Date().getTime();
    }


    public static byte[] int2bytes(int res) {
        byte[] targets = new byte[4];
        try {
            targets[0] = (byte) (res & 0xff);// 最低位
            targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
            targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
            targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        }catch (IOError  e)
        {
            e.printStackTrace();
        }
        return targets;
    }
    public static int bytes2int(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }


}
