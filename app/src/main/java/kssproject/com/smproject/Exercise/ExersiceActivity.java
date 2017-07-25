package kssproject.com.smproject.Exercise;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import kssproject.com.smproject.FireBase.StoreDb;
import kssproject.com.smproject.R;
import kssproject.com.smproject.SharedPreferences;

public class ExersiceActivity extends BlunoLibrary {
    private int cHeart;
    private String cs="0";
    private String cz="0";
    public Vibrator vide;
    private Button buttonScan;
    private Button buttonStart;
    private Button buttonFinish;
    private TextView heartText;
    private TextView stepText;
    private TextView Time;
    private ProgressBar hR;
    private ProgressBar tG;
    private TextView timePercent;
    private TextView carom;


    Exercise exercise=new Exercise();
    ConnectKeep connectKeep=new ConnectKeep();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_main);
        onCreateProcess();                                        //onCreate Process by BlunoLibrary
        serialBegin(115200);
        exercise.setData(SharedPreferences.getSettingintItem(getApplicationContext(),"Age"),SharedPreferences.getSettingfloatItem(getApplicationContext(),"Weight"),SharedPreferences.getSettingItem(getApplicationContext(),"Sex"));
        //SharedPreferences.getSettingfloatItem(getApplicationContext(),"Weight");
        //SharedPreferences.getSettingintItem(getApplicationContext(),"Age");
        //SharedPreferences.getSettingItem(getApplicationContext(),"Sex");
        vide=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);//set the Uart Baudrate on BLE chip to 115200
        buttonStart=(Button)findViewById(R.id.button2);
        heartText=(TextView)findViewById(R.id.textView2);
        stepText=(TextView)findViewById(R.id.textView);
        timePercent=(TextView)findViewById(R.id.textView5);
        Time=(TextView)findViewById(R.id.textView7);
        hR=(ProgressBar)findViewById(R.id.progressBar2);
        tG=(ProgressBar)findViewById(R.id.progressBar_today);
        carom=(TextView)findViewById(R.id.textView4);
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                exercise.exerStart();
                serialSend(cs);
                buttonStart.setVisibility(View.GONE);
                buttonFinish.setVisibility(View.VISIBLE);
                //connectKeep.heartOn();
            }
        });
        buttonFinish=(Button)findViewById(R.id.button3);
        buttonFinish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                exercise.exeTer();
                buttonFinish.setVisibility(View.GONE);
                buttonStart.setVisibility(View.VISIBLE);
            }
        });
        buttonScan = (Button) findViewById(R.id.button);               //initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                buttonScanOnClickProcess();//Alert Dialog for selecting the BLE device
            }
        });
        buttonScan.setVisibility(View.VISIBLE);
        buttonFinish.setVisibility(View.GONE);
        buttonStart.setVisibility(View.GONE);




    }
    protected void onResume(){
        super.onResume();
        System.out.println("BlUNOActivity onResume");
        onResumeProcess();                                        //onResume Process by BlunoLibrary
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResultProcess(requestCode, resultCode, data);                //onActivityResult Process by BlunoLibrary
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();                                         //onPause Process by BlunoLibrary
    }

    protected void onStop() {
        super.onStop();
        onStopProcess();                                          //onStop Process by BlunoLibrary
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyProcess();                                           //onDestroy Process by BlunoLibrary
    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
        switch (theConnectionState) {                                //Four connection state
            case isConnected:
                buttonScan.setText("Connected");
                buttonScan.setVisibility(View.GONE);
                buttonStart.setVisibility(View.VISIBLE);
                break;
            case isConnecting:
                buttonScan.setText("Connecting");

                break;
            case isToScan:
                buttonScan.setText("Scan");

                break;
            case isScanning:
                buttonScan.setText("Scanning");
                break;
            case isDisconnecting:
                buttonScan.setText("isDisconnecting");

                break;
            default:

                break;
        }
    }

    @Override
    public void onSerialReceived(String theString) {
        cHeart=Integer.parseInt(theString);
        heartText.setText(cHeart+"bpm");

        Log.e("heartrate",cHeart+"bpm");
        Log.e("수신데이터",theString);

    }
    public class ConnectKeep {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


            }
        };
        public void heartOn(){
            handler.sendEmptyMessage(0);
        }
        public void heartoff(){
            handler.removeMessages(0);
        }
    }


    public class Exercise{
        String sex="male";
        float weight=80;
        int age=20;

        int calories=0;
        float tc=0;
        long mBaseTime;
        long mNow;
        long mCur=0;
        long tTime=60000;
        Handler handler1=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mNow= SystemClock.elapsedRealtime();
                mCur=mNow-mBaseTime;

                Toast.makeText(ExersiceActivity.this,mCur+"초", Toast.LENGTH_SHORT).show();



                if ((mCur < 20000) && (mCur > 0)) {
                    warmUp();
                    delayCheck();


                } else if ((mCur > 20000) && (mCur < 40000)) {
                    fatJone();
                    delayCheck();


                } else if ((mCur > 40000) && (mCur < 60000)) {
                    coolDown();
                    delayCheck();


                } else if (mCur > 62000) {
                    exeTer();
                }

                //serialSend(cs);
            }
        };
        Handler handler2=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int mPercent=0;
                mNow= SystemClock.elapsedRealtime();
                mCur=mNow-mBaseTime;
                keepGoing();
                long Et;
                //Toast.makeText(ExersiceActivity.this,mCur+"초", Toast.LENGTH_SHORT).show();
                Et=tTime-mCur;
                //Toast.makeText(ExersiceActivity.this,mCur+"경과", Toast.LENGTH_SHORT).show();
                tG.setProgress((int)mCur);
                if (Et>=-2) {
                    Time.setText(String.format("%02d:%02d:%02d", Et / 1000 / 60, (Et / 1000) % 60, (Et % 1000) / 10));
                }
                mPercent=((int)(mCur*100/tTime));
                Log.e("시간",mPercent+"%");
                if(mPercent<=100) {
                    timePercent.setText(mPercent + "%");
                }



            }
        };
        Handler handler3=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(sex.equals("male")){
                    if(cHeart>50) {
                        tc += ((-55.0969 + (0.6309 * cHeart) + (0.1908 * weight) + (0.2017 * age)) / 4.184) * 60 *0.0002777778;
                    }
                    else{
                        tc += ((-55.0969 + (0.6309 * 60) + (0.1908 * weight) + (0.2017 * age)) / 4.184) * 60 *0.0002777778;
                    }
                }
                else if(sex.equals("female")){
                    if(cHeart>50) {
                        tc += ((-20.4022 + (0.4472 * cHeart) - (0.1263 * weight) + (0.0074 * age)) / 4.184) * 60 *0.0002777778;
                    }
                    else{
                        tc += ((-20.4022 + (0.4472 * 60) - (0.1263 * weight) + (0.0074 * age)) / 4.184) * 60 *0.0002777778;
                    }
                }
                calories=((int)(tc/5))*-1;
                carom.setText(calories+"kcal");
            }
        };
        /*
        Et=tTime-mCur;
                Toast.makeText(ExersiceActivity.this,mCur+"경과", Toast.LENGTH_SHORT).show();
                Time.setText(String.format("%02d:%02d:%02d",Et/1000/60,(Et/1000)%60,(Et%1000)/10));
        */
        public void setData(int age,float weight,String sex){
            this.age=age;
            this.weight=weight;
            this.sex=sex;
        }


        public void exerStart(){
            mBaseTime=SystemClock.elapsedRealtime();
            handler1.sendEmptyMessage(0);
            handler2.sendEmptyMessage(0);
            handler3.sendEmptyMessage(0);
            serialSend("0");
            Log.e("stat",this.sex+":"+this.weight+":"+this.age);
        }


        public void keepGoing(){
            handler2.sendEmptyMessageDelayed(0,0);
            handler3.sendEmptyMessageDelayed(0,1000);
        }

        public void delayCheck(){

            handler1.sendEmptyMessageDelayed(0,6000);
            serialSend(cs);
        }
        public void exeTer(){
            handler1.removeMessages(0);
            handler2.removeMessages(0);
            handler3.removeMessages(0);
            serialSend("A");


            tc=0;
            Toast.makeText(ExersiceActivity.this, "강제종료", Toast.LENGTH_SHORT).show();
        }
    }
    public void coolDown(){
        final long[] low={0,500,500,500};
        final long[] proper={0,2000,500,2000};
        final long[] too={0,4000,500,4000};
        stepText.setText("쿨다운");
        Toast.makeText(ExersiceActivity.this, "쿨다운입니다.3km로유지하세요.", Toast.LENGTH_SHORT).show();
        if ( cHeart <90) {
            cs="1";
            vide.vibrate(low, -1);
            tooLOw();
            Toast.makeText(ExersiceActivity.this, "더 빨리뛰세요." + cHeart, Toast.LENGTH_SHORT).show();

        } else if (( cHeart  > 90) &&  cHeart < 100) {
            cs="2";
            vide.vibrate(proper, -1);
            Good();
            Toast.makeText(ExersiceActivity.this, "유지하세요.", Toast.LENGTH_SHORT).show();
        } else {
            cs="3";
            vide.vibrate(too, -1);
            tooHigh();
            Toast.makeText(ExersiceActivity.this, "오버페이스에요", Toast.LENGTH_SHORT).show();
        }


    }
    public void fatJone(){
        final long[] low={0,500,500,500};
        final long[] proper={0,2000,500,2000};
        final long[] too={0,4000,500,4000};
        stepText.setText("지방연소");
        Toast.makeText(ExersiceActivity.this, "지방연소 구간입니다.6km로유지하세요.", Toast.LENGTH_SHORT).show();
        if(cHeart<100){
            cs="4";
            vide.vibrate(low,-1);
            tooLOw();
            Toast.makeText(ExersiceActivity.this, "더 빨리뛰세요.", Toast.LENGTH_SHORT).show();
        }
        else if((cHeart>100)&&cHeart<130){
            cs="5";
            vide.vibrate(proper,-1);
            Good();
            Toast.makeText(ExersiceActivity.this, "유지하세요.", Toast.LENGTH_SHORT).show();
        }
        else{
            cs="6";
            vide.vibrate(too,-1);
            tooHigh();
            Toast.makeText(ExersiceActivity.this, "오버페이스에요", Toast.LENGTH_SHORT).show();
        }

    }
    public void warmUp(){

        final long[] low={0,500,500,500};
        final long[] proper={0,2000,500,2000};
        final long[] too={0,4000,500,4000};
        stepText.setText("워밍업");
        Toast.makeText(ExersiceActivity.this, "워밍업 구간입니다.3km로유지하세요.", Toast.LENGTH_SHORT).show();
        if ( cHeart  < 90) {
            cs="7";
            vide.vibrate(low, -1);
            tooLOw();
            Toast.makeText(ExersiceActivity.this, "더 빨리뛰세요." + cHeart, Toast.LENGTH_SHORT).show();

        } else if (( cHeart >90) &&  cHeart < 100) {
            cs="8";
            vide.vibrate(proper, -1);
            Good();
            Toast.makeText(ExersiceActivity.this, "유지하세요.", Toast.LENGTH_SHORT).show();
        } else {
            cs="9";
            vide.vibrate(too, -1);
            tooHigh();
            Toast.makeText(ExersiceActivity.this, "오버페이스에요", Toast.LENGTH_SHORT).show();
        }

    }
    public void Good(){
        hR.setProgress(100);
    }
    public void tooLOw(){
        hR.setProgress(20);
    }
    public void tooHigh(){
        hR.setProgress(60);
    }

    private void checkDangerousPermissions() {
        String[] permissions = {

                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}