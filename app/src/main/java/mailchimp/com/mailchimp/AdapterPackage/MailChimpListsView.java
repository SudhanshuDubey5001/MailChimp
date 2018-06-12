package mailchimp.com.mailchimp.AdapterPackage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mailchimp.com.mailchimp.AllListResponse;
import mailchimp.com.mailchimp.ContactListActivity;
import mailchimp.com.mailchimp.ContactsGSON.ContactList;
import mailchimp.com.mailchimp.MainActivity;
import mailchimp.com.mailchimp.R;
import retrofit2.Response;

public class MailChimpListsView extends RecyclerView.ViewHolder{

    public static AllListResponse response;
    public TextView listName;
    public int postion;
    Context context;

    public MailChimpListsView(View itemView, Context c) {
        super(itemView);
        context =c;
        listName= itemView.findViewById(R.id.listname);
        listName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, ContactListActivity.class);
                in.putExtra(ContactListActivity.MEM_ID,response.lists.get(postion).id);
                in.putExtra(ContactListActivity.KEYID, MainActivity.key);
                context.startActivity(in);
            }
        });
    }
}
