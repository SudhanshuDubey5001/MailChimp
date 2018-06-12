package mailchimp.com.mailchimp.AdapterPackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mailchimp.com.mailchimp.AllListResponse;
import mailchimp.com.mailchimp.R;
import retrofit2.Response;

public class MailChimpAdapter extends RecyclerView.Adapter<MailChimpListsView>{

    public static AllListResponse response;

    @Override
    public MailChimpListsView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_recycle,parent,false);
        return new MailChimpListsView(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(MailChimpListsView holder, int position) {
        holder.listName.setText(response.lists.get(position).name);
        Log.d("olahu","in adapter: "+response.lists.get(position).name);
        holder.postion=position;
    }

    @Override
    public int getItemCount() {
        return response.listCount;
    }
}
