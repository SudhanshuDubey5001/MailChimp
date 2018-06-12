package mailchimp.com.mailchimp.ContactsGSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllContactsResponse {

    @Expose
    @SerializedName("members")
    public ArrayList<ContactList> members;

    @Expose
    @SerializedName("total_items")
    public int membersCount;
}
