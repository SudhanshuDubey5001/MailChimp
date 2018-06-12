package mailchimp.com.mailchimp.AdapterPackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mailchimp.com.mailchimp.ContactsGSON.AllContactsResponse;
import mailchimp.com.mailchimp.R;
import retrofit2.Response;

public class MailChimpContactAdapter extends RecyclerView.Adapter<ContactView>{

    public static Response<AllContactsResponse> response;

    @Override
    public ContactView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_showcontact,parent,false);
        return new ContactView(view);
    }

    @Override
    public void onBindViewHolder(ContactView holder, int position) {
        String Fname=response.body().members.get(position).memberName.F_name;
        String Lname=response.body().members.get(position).memberName.L_name;
        String Email=response.body().members.get(position).eEmail;

        holder.memberName.setText(Fname+" "+Lname);
        holder.memberEmail.setText(Email);
    }

    @Override
    public int getItemCount() {
        return response.body().membersCount;
    }
}
