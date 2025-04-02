package com.korit.security1.oauth.provider;

import java.util.HashMap;
import java.util.Map;

public class NaverUserInfo implements OAuthUserInfo{
    Map<String,Object> attribute =new HashMap<>();
    public NaverUserInfo(Map<String,Object> attribute){
        this.attribute =attribute;

    }

    @Override
    public String getProviderId() {
        return (String) attribute.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String) attribute.get("email");
    }

    @Override
    public String getName() {
        return (String) attribute.get("name");
    }
}
