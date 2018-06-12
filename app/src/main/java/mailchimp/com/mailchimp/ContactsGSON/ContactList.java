package mailchimp.com.mailchimp.ContactsGSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactList {

    @Expose
    @SerializedName("email_address")
    public String eEmail;

    @Expose
    @SerializedName("merge_fields")
    public NameOfMembers memberName;

}
