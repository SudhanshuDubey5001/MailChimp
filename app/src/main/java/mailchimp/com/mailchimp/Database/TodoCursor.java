package mailchimp.com.mailchimp.Database;

import android.database.Cursor;
import android.database.CursorWrapper;


public class TodoCursor extends CursorWrapper{

    public TodoCursor(Cursor cursor) {
        super(cursor);
//        CursorWrapper class just takes the cursor and implemnts the method of interface Cursor
    }

//    We can add extra method to this class, like decorating a already made gift..
//    This technique is called Decorator Design Pattern.

    public int getIdToDo(){
        return this.getInt(this.getColumnIndex("_id"));
    }

    //more methods...now just call the method directly and need not to remember the "_id"..kinda
//    stuff..
}
