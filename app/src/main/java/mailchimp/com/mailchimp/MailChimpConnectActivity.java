package mailchimp.com.mailchimp;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mailchimp.com.mailchimp.Database.DatabaseHelper;

public class MailChimpConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_chimp_connect);

        SharedPreferences main = getSharedPreferences("main", Context.MODE_PRIVATE);
        //there is also getPrefrence() which doesn't takes file name like "main"
//        it creates a name for each activity (activity name) and stores the data in that file.

        //get data-->
        String key = main.getString("api_key","Delhi NCR");
        Log.d("olahu",key);

        //put data-->
        //first get the editor
        SharedPreferences.Editor editor = main.edit();
        editor.putString("api_key","jo store karna hai");

        //now commit to view changes-->
        editor.commit();
        //commit()--> it saves first then show the changes
        //apply()--> it saves in different thread, and show the changes quickly
        //commit() only takes

        //to clear the values of key-->
        editor.clear();

        //we usually create just one file like "main" here to store all our data so to easy
//        our task, so that we don;t forget the keys and all the crap, we create a class and to
//        fetch the Context to get our data, we create another class.

        //To use the database-->
        DatabaseHelper helper = new DatabaseHelper();
//        helper.getWritableDatabase();
        //will give both permission read and write

        SQLiteDatabase database = helper.getReadableDatabase();
        //getReadble only has permission of read.
        //if user has old version and we have updated our app to newer version then firstly
//        onUpgrade is called so that user's data is secured and database is updated then only
//        we get the object of SQLiteDatabase.

    }
}
