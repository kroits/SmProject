package kssproject.com.smproject.Controller;

import android.content.Intent;

/**
 * Created by b3216 on 2017-07-27.
 */

public class WaitStart {

    private Intent intent;
    public WaitStart(Intent intent){
        this.intent = intent;
    }

    public Intent getIntent(){
        return intent;
    }
}
