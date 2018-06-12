package mailchimp.com.mailchimp;

import android.util.Log;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MailChimpServiceBuilder {

    public static final String baseURL = "https://us16.api.mailchimp.com/3.0/";
    public static MailChimpService build(){
        Retrofit retro = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retro.create(MailChimpService.class);
    }
}
