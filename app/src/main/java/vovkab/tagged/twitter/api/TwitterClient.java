package vovkab.tagged.twitter.api;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.mime.TypedInput;
import rx.Observable;
import vovkab.tagged.twitter.api.response.Oauth2TokenResponse;
import vovkab.tagged.twitter.api.response.TweetsResponse;

public interface TwitterClient {

    public static final String CONSUMER_KEY = "PFBlZmWl6TZYJen5xnuBKcuGZ"; // api key
    public static final String CONSUMER_SECRET_KEY = "wdP9umFvil9wACccj39CxGAF9WDBq7r35ayf2SosQpScvPCIqU";
    public static final String ACCESS_TOKEN = "8049352-ilpoIucR9eAeRjbXCOm4yY7XtfnmlCgyUls8MJEEbc";
    public static final String ACCESS_TOKEN_SECRET_KEY = "Pnc38LAhtlcHnsCqvLV9YyngOSX4tyuBMCWK6tAN8khxN";

    @POST("/oauth2/token")
    public void oauth2Token(
        @Header("Authorization") String authHeader,
        @Body TypedInput type,
        Callback<Oauth2TokenResponse> callback
    );

    @GET("/1.1/search/tweets.json")
    public void search(@Query("q") String query, Callback<TweetsResponse> callback);

    @GET("/1.1/search/tweets.json")
    public Observable<TweetsResponse> search(@Query("q") String query);
}
