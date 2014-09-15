package vovkab.tagged.twitter.api;

import retrofit.RequestInterceptor;
import vovkab.tagged.twitter.api.auth.Authorization;

public class ApiHeaders implements RequestInterceptor {
    private Authorization mAuth;

    public ApiHeaders(Authorization authorization) {
        mAuth = authorization;
    }

    @Override public void intercept(RequestFacade request) {
        if (mAuth.isEnabled()) {
            request.addHeader("Authorization", mAuth.getHeader());
        }
    }
}
