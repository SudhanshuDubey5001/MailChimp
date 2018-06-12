package mailchimp.com.mailchimp.Error;


import com.google.gson.Gson;

import java.io.IOException;

import mailchimp.com.mailchimp.SharedPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class onResponseCallback<T> implements Callback<T> {

    //When we send a wrong info to server, even if something's wrong, we get some response in JSON
//    so always onRespnse will run, but we don't want that, we want that if somethings goes wrong
//    we need to be aware of that mistake, so we need to make some sort of way to get that message
//    error...you know. So make this class which implements Callback. So that we can pass this class
//    object in enqueue, bcz it implements Callback.

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
//            if successful we call the onSuccess method, so wherever it is implemented will get the ar
//            argument response.body(), straight away, T will take any class name and return its object
            onSuccess(response.body());
        } else {
//            if we get an error-->
            Gson gson = new Gson();
            try {
//                study the JSON of error message, take out the title and error code out of it by usign
//                GSON classes. here we made ApiKey class.
                ApiError error = gson.fromJson(response.errorBody().string(), ApiError.class);
//                Now in response.errorBody contains JSON data of error message, which we parse to
//                objects of java with the help of GSON class ApiError

//                we made a constructor to set the message and status code and check for different codes
//                and handle them accourdingly here by using if else ladder.
                if(error.status==401){
                    SharedPrefs.setApikey(null);
                }
//                then in the end pass the object created of apikey to onFail, so that user can handle
//                or show the error messages wherever he wants. bcz in case of error reponse we need
//                call onFail, and we can get the argumet as ApiError object and we already loaded it
//                with our title and state, so just use it wherever you want.
                onFail(error);
            } catch (IOException e) {
//                if some else error came, then call the onFail with creating a new object of ApiError
//                by passing it state and message like this-->
                onFail(new ApiError(500,"Unknown error"));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
//        if seriously something wrong like internet is not working then show the error like that
//        by calling again the onFail method we created and passing the error message from Throwable
//        object.
        onFail(new ApiError(500,t.getMessage()));
    }


//    these are unimplemented methods we have created so that we can call them when something's up
//    and it will show effect or get called in those class where they are overidden, like in those
//    anonymous class we used....cool huh?
    abstract public void onSuccess(T response);

    abstract public void onFail(ApiError error);

}
