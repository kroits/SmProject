package kssproject.com.smproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import static kssproject.com.smproject.SharedPreferences.*;
import static kssproject.com.smproject.SharedPreferences.saveSettingintItem;

/**
 * Created by b3216 on 2017-05-28.
 */

public class ProfileActivity extends AppCompatActivity{

    private EditText nameText, ageText, heightText, weightText, goalWeightText;
    //private
    private Button nextButton;
    private CheckBox checkMale,checkFemale;
    private String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_layout);
        nextButton = (Button)findViewById(R.id.nextButton);
        Intent intent=new Intent(this.getIntent());

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
                saveSettingItem(getApplicationContext(), "Name", nameText.getText().toString());
                saveSettingintItem(getApplicationContext(), "Age", Integer.parseInt(ageText.getText().toString()));
                saveSettingfloatItem(getApplicationContext(), "Height", Float.parseFloat(heightText.getText().toString()));
                saveSettingfloatItem(getApplicationContext(), "Weight", Float.parseFloat(weightText.getText().toString()));
                saveSettingfloatItem(getApplicationContext(), "GoalWeight", Float.parseFloat(goalWeightText.getText().toString()));
                saveSettingItem(getApplicationContext(),"Sex",sex);

                Intent intent1 = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });


    }

}
