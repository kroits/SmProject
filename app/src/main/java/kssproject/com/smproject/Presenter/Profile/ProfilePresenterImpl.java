package kssproject.com.smproject.Presenter.Profile;

import android.app.Activity;
import android.content.SharedPreferences;

import kssproject.com.smproject.Model.ProfileModel;
import kssproject.com.smproject.View.MainActivity;

/**
 * Created by b3216 on 2017-07-30.
 */

public class ProfilePresenterImpl implements ProfilePresenter {
    private Activity activity;
    private ProfilePresenter.View view;
    private ProfileModel profileModel;
    private SharedPreferences sp;


    public ProfilePresenterImpl(Activity activity,SharedPreferences sp){
        this.activity=activity;
        this.profileModel = new ProfileModel();
        this.sp = sp;
    }

    @Override
    public void setView(ProfilePresenter.View view) {
        this.view = view;
    }

    @Override
    public void onConfirm(String name, Integer age, Float height, Float weight, Float goalweight,String sex) {
        profileModel.storeProfile(name, age, height, weight, goalweight,sex,sp);
        view.storeData();
    }

    @Override
    public void start() {
        view.startActivity(MainActivity.class);
    }

}
