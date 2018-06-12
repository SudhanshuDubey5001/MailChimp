package mailchimp.com.mailchimp.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddContacts {

    @Expose
    @SerializedName("email_address")
    public String email;

    @Expose
    @SerializedName("status")
    public String status;

    public AddContacts(String email,String status){
        this.email=email;
        this.status=status;
    }
}
