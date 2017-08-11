package kssproject.com.smproject.Presenter.Start;

import android.app.Activity;
import android.content.SharedPreferences;

import kssproject.com.smproject.Model.StartModel;
import kssproject.com.smproject.View.MainActivity;
import kssproject.com.smproject.View.ProfileActivity;

/**
 * Created by b3216 on 2017-07-30.
 */

public class StartPresenterImpl implements StartPresenter {
    private Activity activity;
    private StartPresenter.View view;
    private StartModel startModel;
    private SharedPreferences sp;

    public StartPresenterImpl(Activity activity, SharedPreferences sp) {
        this.activity = activity;
        this.startModel = new StartModel();
        this.sp = sp;
        startModel.loadData(sp);
    }

    @Override
    public void setView(StartPresenter.View view) {
        this.view = view;
    }

    @Override
    public void startClicked() {
        if (view != null) {

            //UserKey를 가지고 있으면 (등록된 상태면) 데이터를 읽고 main으로 이동
            if (startModel.checkUserKey(sp)) {

                // 나이의 변화가 필요한지 확인
                startModel.checkAge(sp);
                view.loadData();

            //미등록 상태일 경우 profile로 이동
            } else {
                view.startProfile(ProfileActivity.class);
            }
        }
    }

    @Override
    public void loading() {
        startModel.DataSet();
        startModel.calorieSet();
        view.startMain(MainActivity.class);
    }

    @Override
    public void checkRegist() {
        if (sp.getString("UserKey", null) == null) {
            //등록 안된 상태
            view.visibleButton();
        } else {
            //등록 된 상태
            if (view != null) {
                startClicked();
            }
        }
    }

}
