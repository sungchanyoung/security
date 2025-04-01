package com.korit.security1.oauth.provider;

import java.util.HashMap;
import java.util.Map;

public class GoogleUserInfo implements OAuthUserInfo{
    Map<String,Object> attribute =new HashMap<>();

    public GoogleUserInfo(Map<String,Object> attribute){
        this.attribute =attribute;

    }
    @Override
    public String getProviderId() {
        return (String) attribute.get("sub") ;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String) attribute.get("email") ;
    }

    @Override
    public String getName() {
        return (String) attribute.get("name") ;
    }
}
