package mailchimp.com.mailchimp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mailchimp.com.mailchimp.MyApllication;

public class DatabaseHelper extends SQLiteOpenHelper{

    //To use the database, we need to make a class a subclass of SQLIteOpenHelper.

    public DatabaseHelper() {
        super(MyApllication.getContext(), "db_name", null, 1);
    }

    //when user creates the database for first time, onCreate is called, and we set the version no. as 1
//    as we upgrade the database further, we don't create the database on user's phone next time, so
//    onCreate is not called, just onUpgrade is called.
    //it calls when getReadable or getWritable is called.
    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("----SQL TEXT-----");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //structural changes....
    }
}
