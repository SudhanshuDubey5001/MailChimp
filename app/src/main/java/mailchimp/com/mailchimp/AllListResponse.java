package mailchimp.com.mailchimp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllListResponse {

    @Expose
    @SerializedName("lists")
    public ArrayList<MailChimpList> lists;

    @Expose
    @SerializedName("total_items")
    public int listCount;


}
