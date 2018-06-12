package mailchimp.com.mailchimp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MailChimpList {

    @Expose
    @SerializedName("id")
    public String id;

    @Expose
    @SerializedName("name")
    public String name;
}
