package kssproject.com.smproject.Presenter.Start;

/**
 * Created by b3216 on 2017-07-30.
 */

public interface StartPresenter {
    void setView(StartPresenter.View view);
    
    void startClicked();

    void loading();
    void checkRegist();
    
    public interface  View{
        void startProfile(Class nextActivity);
        void startMain(Class nextActivity);
        void loadData();
        void visibleButton();
    }
}
