package kssproject.com.smproject.MidStore;

import java.util.HashMap;

/**
 * Created by b3216 on 2017-07-17.
 */

public class DataStore {
    private static DataStore mDataStore = new DataStore();
    private String key;
    private HashMap<String,HashMap<String,Number>> DataMap = new HashMap<String,HashMap<String,Number>>();
    private DataStore(){}

    public static DataStore getInstance(){return mDataStore;}
}
