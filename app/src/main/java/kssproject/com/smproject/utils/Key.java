package kssproject.com.smproject.utils;

/**
 * Created by b3216 on 2017-07-31.
 */

public class Key {
    private String key;
    private static Key mKey = new Key();

    public Key() {}

    public static Key getInstance(){return mKey;}


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
