package mailchimp.com.mailchimp.Welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mailchimp.com.mailchimp.MainActivity;
import mailchimp.com.mailchimp.R;
import mailchimp.com.mailchimp.SharedPrefs;

public class GetApiKeyActivity extends AppCompatActivity {

    public static String API_KEY;
    EditText getapikey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        getapikey = (EditText) findViewById(R.id.getApiKey);
        Button b = (Button) findViewById(R.id.save);
        Button add = (Button)findViewById(R.id.addContact);
        Button show = (Button)findViewById(R.id.showContacts);

        add.setVisibility(View.INVISIBLE);
        show.setVisibility(View.INVISIBLE);

        if (SharedPrefs.getPrefs().getString(SharedPrefs.API_KEY, "").equals("")) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    API_KEY = getapikey.getText().toString();
                    SharedPrefs.setApikey(API_KEY);
                    Intent in = new Intent(GetApiKeyActivity.this, MainActivity.class);
                    in.putExtra("KEY",API_KEY);
                    startActivity(in);
                }
            });
        } else {
            API_KEY = SharedPrefs.getPrefs().getString(SharedPrefs.API_KEY, "");
            getapikey.setText(API_KEY);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(GetApiKeyActivity.this, MainActivity.class);
                    in.putExtra("KEY",API_KEY);
                    startActivity(in);
                }
            });
        }
    }
}
