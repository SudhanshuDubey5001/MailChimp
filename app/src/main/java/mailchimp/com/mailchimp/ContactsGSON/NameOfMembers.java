package mailchimp.com.mailchimp.ContactsGSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameOfMembers {

    @Expose
    @SerializedName("FNAME")
    public String F_name;

    @Expose
    @SerializedName("LNAME")
    public String L_name;

}
