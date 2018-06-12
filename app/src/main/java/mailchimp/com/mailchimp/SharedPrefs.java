package mailchimp.com.mailchimp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    public static final String API_KEY="";

    public static SharedPreferences getPrefs(){
        Context c = MyApllication.getContext();
        return c.getSharedPreferences("main",Context.MODE_PRIVATE);
    }

    public static void setApikey(String key){
        getPrefs().edit().putString(API_KEY,key).commit();
    }

    //Now use this class to get api key, once you have it, next time use it from the stored
//    location so that user don't have to type again.


}
