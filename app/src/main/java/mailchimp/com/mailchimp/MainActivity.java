package mailchimp.com.mailchimp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import mailchimp.com.mailchimp.Database.DatabaseHelper;
import mailchimp.com.mailchimp.Database.TodoCursor;
import mailchimp.com.mailchimp.Error.ApiError;
import mailchimp.com.mailchimp.Error.onResponseCallback;
import mailchimp.com.mailchimp.Requests.AddContacts;
import mailchimp.com.mailchimp.Responses.AddContactsResponse;
import mailchimp.com.mailchimp.Welcome.GetApiKeyActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import mailchimp.com.mailchimp.AdapterPackage.*;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //Retrofit gson converter: data fetched from retrofit is passed directly to
    // gson and give java objects

    private RecyclerView recycler;
    private TextView textView;
    private MailChimpAdapter adapter;
    private KProgressHUD wait;

    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://usll.api.mailchimp.com/3.0")
//                                      .addConverterFactory(GsonConverterfactory.create()).build();
//
//        MailChimpService service = retrofit.create(MailChimpService.class);
//        //create do all the stuff like url, openconnection,all that..and retrun the object
//        Call<gsonClass> r = service.getAllLists("bearer "+ api_key);
//        //will request data to server with api_key and return gsonClass object
//
//        Response<gsonClass> c = r.execute();
//        gsonClass g = c.body();
//        //body() will
//
//        //put all the above crap in a class inside a static method so that you can use it at any class
//        or: gsonClass g1 = r.execute().body();
//
////        execute() method immediately fetched data on main thread and puts it in gsonClass
////        keys variables, but we don't use this bcz its in main thread calling the rest APIs. SO we use not execute but
////        enquene() method.
////        enquene method is like it completes the task in another thread and then call a method with
////        the help of listener.
//
////        So we will not use execute,
//        gsonClass g2 = r.enqueue(this);
//
//
//NOW WRITING EVERYTHING UP IN A MANNERED WAY--->

//        First we need to send the request to mailchimp server with a specific api_key to access
//        someone's lists and contacts, so we need to set up the url, pass key, and all that crap..
//        So these jobs are done by our Retrofit library which will send the request on baseURl and
//        then use Gson converter factory library to send the data recieved in JSON to gson which will
//        convert that into java objects and we'll get our object loaded with baseUrl and
//        GsonconverterFactory. We'll make a class to do that for us to use it many times

//        Then we will use the returned object of interface MailChimpService to call our getAllList()
//        method and then it will return a object of "Call" class on which we will call our enquene method
//        which will finally send the request and takes a Callback interface as a listener in the
//        argument to get the response object of AlllistResponse class(Callback<AllListResponse>
//        ,so we will create a anonymous class to set the listener.


        DatabaseHelper helper = new DatabaseHelper();
        //to read and write
        SQLiteDatabase database = helper.getWritableDatabase();

        //To insert, delete, upgrade
        ContentValues values = new ContentValues();
        values.put("title", "YOYO");
        values.put("class", "sup");

        //to insert the values
        database.insert("todo", null, values);
        //todo is the table name
//        2nd argument is usually null, only pass row name which is null all along..not possible usually
//        values is the ContentValues object which is holding the data

//        To fetch the data-->
        Cursor cursor = database.query("todo", null, null, null, null, null, null, null);
//        2: String array of column name to select, null means *
//        3. selection clause: where _id=2, --> _id=?
//        4. selectionargs: ? values is defined here, a string array of what will be in the 'where' clause
//        5,6,7,8. you know all that...

//        Now to show the data-->
        TodoCursor c = new TodoCursor(cursor);
        while (cursor.moveToNext()) {
//            int id= cursor.getInt(0);//pass column index in getInt()
            int id = cursor.getInt(cursor.getColumnIndex("_id"));//or do this
//            or do this-->
            int id1 = c.getIdToDo();   //most efficient way
            String title = cursor.getString(1);//pass column index in getInt()
            Log.d("olahu", "id: " + id + "title: " + title);
        }

//        to delelte the coloumn
        String[] ids = {"1"};
        database.delete("todo", "_id=?", ids);

//        to update-->
        ContentValues v = new ContentValues();
        v.put("title", "hero");
        String[] t = {"sup"};
        database.update("todo", values, "title=?", t);
        database.close();

        textView = (TextView) findViewById(R.id.waitText);
        textView.setVisibility(View.GONE);
        wait = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please Wait")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .show();

        recycler = (RecyclerView) findViewById(R.id.recycleView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MailChimpAdapter();
        adapter.notifyDataSetChanged();

        Intent in = getIntent();
        key = in.getStringExtra("KEY");
        Log.d("olahu", key);
        MailChimpServiceBuilder.build().getAllLists("bearer " + key).enqueue(new onResponseCallback<AllListResponse>() {
            @Override
            public void onSuccess(AllListResponse response) {
                MailChimpAdapter.response = response;
                MailChimpListsView.response=response;

//                textView.setVisibility(View.GONE);
                wait.dismiss();
                recycler.setAdapter(adapter);

                Log.d("olahu", response.lists.get(0).name);
                Log.d("olahu", response.lists.get(0).id);
                Log.d("olahu", String.valueOf(response.listCount));
            }

            @Override
            public void onFail(ApiError error) {
                Log.d("olahu",error.title);
                Log.d("olahu",""+error.status);
            }
        });


        //to send post requests--->
        AddContacts add = new AddContacts("abc@gmail.com","subscribed");
        Gson g = new Gson();
        String json = g.toJson(add);
        //now json contains json string of AddContacts GSON class.
        Log.d("olahu",json);
        //all this work id done by retrofit itself-->

        MailChimpServiceBuilder.build().addContact("id",add).enqueue(new onResponseCallback<AddContactsResponse>() {
            @Override
            public void onSuccess(AddContactsResponse response) {

            }

            @Override
            public void onFail(ApiError error) {

            }
        });
    }
}
