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
            startModel.checkAge(sp);
            if (startModel.checkUserKey(sp)) {
                view.loadData();

            } else {
                view.startProfile(ProfileActivity.class);
            }
        }
    }

    @Override
    public void loading() {
        view.startMain(MainActivity.class);
    }

    @Override
    public void checkRegist() {
        if (sp.getString("UserKey", null) == null) {
            view.visibleButton();

        } else {
            if (view != null) {
                startClicked();
            }
        }
    }

}
