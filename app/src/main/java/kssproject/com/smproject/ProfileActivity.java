package kssproject.com.smproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kssproject.com.smproject.DataPackage.DateToday;
import kssproject.com.smproject.DataPackage.FirebaseDTO;
import kssproject.com.smproject.FireBase.StoreDb;

/**
 * Created by b3216 on 2017-05-28.
 */

public class ProfileActivity extends AppCompatActivity{

    private EditText nameText, ageText, heightText, weightText, goalWeightText;
    //private
    private Button nextButton;
    private CheckBox checkMale,checkFemale;
    private String sex;
    private SharedPreferences sp;
    private FirebaseDatabase firebasedatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebasedatabase.getReference();
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_layout);

        sp = getSharedPreferences("profile",MODE_PRIVATE);


        Intent sintent = getIntent();
        nextButton = (Button)findViewById(R.id.nextButton);



        nameText = (EditText)findViewById(R.id.editName);
        checkMale = (CheckBox)findViewById(R.id.checkMale);
        checkFemale = (CheckBox)findViewById(R.id.checkFemale);
        ageText = (EditText)findViewById(R.id.editAge);
        heightText = (EditText)findViewById(R.id.editHeight);
        weightText = (EditText)findViewById(R.id.editWeight);
        goalWeightText = (EditText)findViewById(R.id.editGoalWeight);

        checkMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkMale.isChecked()){
                    checkFemale.setChecked(false);
                    sex="male";
                }
            }
        });

        checkFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkFemale.isChecked()){
                    checkMale.setChecked(false);
                    sex="female";
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Name", nameText.getText().toString());
                editor.putInt("Year",DateToday.getInstance().getDate().getYear());
                editor.putInt("Age", Integer.parseInt(ageText.getText().toString()));
                editor.putFloat("Height", Float.parseFloat(heightText.getText().toString()));
                editor.putFloat("Weight", Float.parseFloat(weightText.getText().toString()));
                editor.putFloat("GoalWeight", Float.parseFloat(goalWeightText.getText().toString()));
                editor.putString("Sex",sex);
                editor.putString("StrDate", DateToday.getInstance().getStrDate());
                editor.putInt("Calorie",0);
                editor.putString("UserKey",key = databaseReference.push().getKey());
                FirebaseDTO firebaseDTO = new FirebaseDTO(0.0,0);
                StoreDb.getInstance().DataSave(key,0,0);
                editor.commit();

//                saveSettingItem(getApplicationContext(), "Name", nameText.getText().toString());
//                saveSettingintItem(getApplicationContext(), "Age", Integer.parseInt(ageText.getText().toString()));
//                saveSettingfloatItem(getApplicationContext(), "Height", Float.parseFloat(heightText.getText().toString()));
//                saveSettingfloatItem(getApplicationContext(), "Weight", Float.parseFloat(weightText.getText().toString()));
//                saveSettingfloatItem(getApplicationContext(), "GoalWeight", Float.parseFloat(goalWeightText.getText().toString()));
//                saveSettingItem(getApplicationContext(),"Sex",sex);

                Intent intent1 = new Intent(ProfileActivity.this,WaitActivity.class);
                startActivity(intent1);
                finish();
            }
        });


    }

}
