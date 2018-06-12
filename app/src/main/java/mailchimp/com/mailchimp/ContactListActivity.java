package mailchimp.com.mailchimp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import mailchimp.com.mailchimp.AdapterPackage.MailChimpAdapter;
import mailchimp.com.mailchimp.AdapterPackage.MailChimpContactAdapter;
import mailchimp.com.mailchimp.ContactsGSON.AllContactsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private MailChimpContactAdapter adapter;
    private KProgressHUD wait;

    public static final String MEM_ID="id";
    public static final String KEYID="keyid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        wait = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Hold on")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .show();

        recycler = (RecyclerView) findViewById(R.id.recycleViewContact);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MailChimpContactAdapter();
        adapter.notifyDataSetChanged();

        Intent in = getIntent();
        String id = in.getStringExtra(MEM_ID);
        String key = in.getStringExtra(KEYID);

        MailChimpServiceBuilder.build().getContacts("bearer "+key,id).enqueue(new Callback<AllContactsResponse>() {
            @Override
            public void onResponse(Call<AllContactsResponse> call, Response<AllContactsResponse> response) {
                MailChimpContactAdapter.response = response;
                wait.dismiss();
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<AllContactsResponse> call, Throwable t) {
                Log.d("olahu","Can't get the response...");
            }
        });
    }
}
