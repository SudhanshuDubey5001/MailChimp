package mailchimp.com.mailchimp;

import mailchimp.com.mailchimp.ContactsGSON.AllContactsResponse;
import mailchimp.com.mailchimp.Requests.AddContacts;
import mailchimp.com.mailchimp.Responses.AddContactsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MailChimpService {

    //THIS CLASS IS USED FOR SENDING REQUESTS TO URL WITH THE HELP OF RETROFIT LIBRARY
//    IT USES CALL INTERFACE TO SEND THE REQUESTS

    //in GET: we can send send data in URL(path and query) and header
    //in others: we can send in body too..


    //get all the lists from mailchimp---->
    @GET("lists")   //get method on "lists" key
    Call<AllListResponse> getAllLists(@Header("Authorization") String apiKey); //calling the method from API
//                     @Query("count") int a,
//                     @Query("offset") int b);
    //This is-->
    //query means: ?(here)
    //GET request on baseURl/lists?count=<a>&offset=<b>

    //to get all the contacts--->
    @GET("lists/{id}/members")
    Call<AllContactsResponse> getContacts(@Header("Authorization") String key,
                                          @Path("id") String memberID);

    //PAth: {a} is replaced by @Path("here")
//    So there are 3 : Header: which is not shown in the url but is send anyways
//                       Query: "?" then whatever you write @Query("here")


//    @POST("lists/{id}/members")
//    Call addContact(@Path("id") String id, @Field("email_address") String email,
//                                 @Field("status") String status);

    //feild is like adding in fields in form-data or ...ou know.

    @POST("lists/{id}/members")
    Call<AddContactsResponse> addContact(@Path("id") String id, @Body AddContacts add);
    //give "body" annotation and give the GOSN class object in that, retrofit will convert it into
//    josn object


}
