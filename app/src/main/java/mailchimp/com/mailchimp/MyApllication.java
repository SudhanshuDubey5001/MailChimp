package mailchimp.com.mailchimp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApllication extends Application{

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }
}
