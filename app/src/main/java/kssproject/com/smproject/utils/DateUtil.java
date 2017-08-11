package kssproject.com.smproject.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by b3216 on 2017-07-28.
 */

public class DateUtil {
    private static DateUtil mDateUtil = new DateUtil();

    private static Date date = new Date();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static String strDate = dateFormat.format(date);


    public static DateUtil getInstance(){return mDateUtil;}
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }



    // 앱을 실행한 상태에서 날짜의 변화가 있는지 확인 바뀌었으면 false 리턴.
    public boolean checkDate(){
        Date checkDate = new Date();
        String checkStrDate = dateFormat.format(checkDate);
        return (checkStrDate == strDate);
    }

    // 앱을 실행할때 저장한 날짜를 현재의 날짜로 변경.
    public void reSettingDate(){
        date = new Date();
        strDate = dateFormat.format(date);
    }
}
