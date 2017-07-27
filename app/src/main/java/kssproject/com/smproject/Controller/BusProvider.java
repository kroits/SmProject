package kssproject.com.smproject.Controller;

import com.squareup.otto.Bus;

/**
 * Created by b3216 on 2017-07-27.
 */

public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    private BusProvider(){

    }
}
