package com.loginzalo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zing.zalo.zalosdk.oauth.LoginVia;
import com.zing.zalo.zalosdk.oauth.OAuthCompleteListener;
import com.zing.zalo.zalosdk.oauth.OauthResponse;
import com.zing.zalo.zalosdk.oauth.ZaloOpenAPICallback;
import com.zing.zalo.zalosdk.oauth.ZaloSDK;


import org.json.JSONObject;

import javax.annotation.Nonnull;

public class RNLoginZaloModule extends ReactContextBaseJavaModule {

    public RNLoginZaloModule(@Nullable ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "RNLoginZalo";
    }

    @ReactMethod
    public void login(Callback onSuccess) {
        ZaloSDK.Instance.authenticate(getCurrentActivity(), LoginVia.APP_OR_WEB,new OAuthCompleteListener(){
            @Override
            public void onGetOAuthComplete(OauthResponse response) {
                super.onGetOAuthComplete(response);
               try {
                    ZaloSDK.Instance.getProfile(getReactApplicationContext(), new ZaloOpenAPICallback() {
                        @Override
                        public void onResult(JSONObject jSONObject) {
                            onSuccess.invoke(jSONObject.toString());
                        }
                    }, new String[]{"id", "birthday", "gender", "picture", "name"});
                } catch (Exception e) {
                    onSuccess.invoke(e);
                }
            }

            @Override
            public void onAuthenError(int errorCode, String message) {
                onSuccess.invoke(message);
                super.onAuthenError(errorCode, message);
            }

        });
    }
}