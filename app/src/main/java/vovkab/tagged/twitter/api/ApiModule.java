package vovkab.tagged.twitter.api;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;
import vovkab.tagged.twitter.api.auth.AuthManager;
import vovkab.tagged.twitter.api.auth.Authorization;
import vovkab.tagged.twitter.api.auth.BearerAuthorization;
import vovkab.tagged.twitter.api.auth.EmptyAuthorization;
import vovkab.tagged.twitter.preference.StringPreference;

@Module(
    complete = false,
    library = true
)
public final class ApiModule {
    public static final String ENDPOINT = "https://api.twitter.com";

    @Provides @Singleton Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(ENDPOINT);
    }

    @Provides @Singleton Client provideClient() {
        return new OkClient();
    }

    @Provides @Singleton AuthManager provideAuthManager(@BearerToken StringPreference bearerToken) {
        Authorization auth = TextUtils.isEmpty(bearerToken.get()) ?
            EmptyAuthorization.getInstance() : new BearerAuthorization(bearerToken.get());
        return new AuthManager(auth);
    }

    @Provides @Singleton ApiHeaders provideApiHeaders(AuthManager authManager) {
        return new ApiHeaders(authManager);
    }

    @Provides @Singleton ApiErrorHandler provideApiErrorHandler(AuthManager authManager) {
        return new ApiErrorHandler(authManager);
    }

    @Provides @Singleton ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Provides @Singleton Converter provideConverter(ObjectMapper objectMapper) {
        return new JacksonConverter(objectMapper);
    }

    @Provides @Singleton RestAdapter provideRestAdapter(Endpoint endpoint,
                                                        Client client,
                                                        ApiHeaders apiHeaders,
                                                        ApiErrorHandler errorHandler,
                                                        Converter converter) {
        return new RestAdapter.Builder()
            .setClient(client)
            .setEndpoint(endpoint)
            .setRequestInterceptor(apiHeaders)
            .setConverter(converter)
            .setErrorHandler(errorHandler)
            .build();
    }

    @Provides @Singleton TwitterClient provideTwitterClient(RestAdapter restAdapter) {
        return restAdapter.create(TwitterClient.class);
    }

    @Provides @BearerToken StringPreference provideBearerToken(SharedPreferences preferences) {
        return new StringPreference(preferences, "twitter_bearer_token");
    }
}
