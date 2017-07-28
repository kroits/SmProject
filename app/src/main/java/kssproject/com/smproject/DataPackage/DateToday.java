package kssproject.com.smproject.DataPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by b3216 on 2017-07-28.
 */

public class DateToday {
    private static DateToday mDateToday = new DateToday();

    private static Date date = new Date();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static String strDate = dateFormat.format(date);


    public static DateToday getInstance(){return mDateToday;}
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
}
