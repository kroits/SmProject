package kssproject.com.smproject.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kssproject.com.smproject.FireBase.SelectDb;
import kssproject.com.smproject.Presenter.Profile.ProfilePresenter;
import kssproject.com.smproject.Presenter.Profile.ProfilePresenterImpl;
import kssproject.com.smproject.R;
import kssproject.com.smproject.utils.Key;

/**
 * Created by b3216 on 2017-05-28.
 */

public class ProfileActivity extends AppCompatActivity implements ProfilePresenter.View{

    private ProfilePresenter profilePresenter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private EditText nameText, ageText, heightText, weightText, goalWeightText;
    //private
    private Button nextButton;
    private CheckBox checkMale,checkFemale;
    private Integer age;
    private String name,sex;
    private Float height,weight,goalweight;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_layout);
        sp = getSharedPreferences("profile",MODE_PRIVATE);

        profilePresenter = new ProfilePresenterImpl(ProfileActivity.this,sp);
        profilePresenter.setView(this);

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
                setMale();
            }
        });

        checkFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFemale();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameText.getText().toString();
                age = Integer.parseInt(ageText.getText().toString());
                height = Float.parseFloat(heightText.getText().toString());
                weight = Float.parseFloat(weightText.getText().toString());
                goalweight = Float.parseFloat(goalWeightText.getText().toString());

                profilePresenter.onConfirm(name, age,height,weight,goalweight,sex);
            }
        });

    }

    private void setFemale() {
        checkMale.setChecked(false);
        sex="female";
    }

    private void setMale() {
        checkFemale.setChecked(false);
        sex = "male";
    }

    @Override
    public void completeRegist(Class mainActivity) {
        databaseReference.child(Key.getInstance().getKey()).child("information").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SelectDb.getInstance().SelectData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Intent mainIntent = new Intent(this,mainActivity);
        startActivity(mainIntent);
        finish();
    }
}
