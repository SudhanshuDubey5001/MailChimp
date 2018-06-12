package mailchimp.com.mailchimp.Error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiError {

    @Expose
    @SerializedName("title")
    public String title;

    @Expose
    @SerializedName("status")
    public int status;

    public ApiError(int status,String title){
        this.title=title;
        this.status=status;
    }
}
