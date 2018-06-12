package mailchimp.com.mailchimp.AdapterPackage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mailchimp.com.mailchimp.R;

public class ContactView extends RecyclerView.ViewHolder {

    TextView memberName;
    TextView memberEmail;

    public ContactView(View itemView) {
        super(itemView);
        memberName = itemView.findViewById(R.id.memberName);
        memberEmail = itemView.findViewById(R.id.memberEmail);

    }
}
