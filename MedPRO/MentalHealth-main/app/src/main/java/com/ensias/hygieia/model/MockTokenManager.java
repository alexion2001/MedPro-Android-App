package com.ensias.hygieia.model;

import android.content.Context;
import android.content.SharedPreferences;

public class MockTokenManager {
    private static final String SHARED_PREFS_NAME = "mock_shared_prefs";
    private static final String ACCESS_TOKEN_KEY = "accessToken";
    private static final String REFRESH_TOKEN_KEY = "refreshToken";
    private static final String USER_ROLE_KEY = "userRole";
    private static final String USER_NAME_KEY = "userName";

    private SharedPreferences sharedPreferences;

    public MockTokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveTokens(String accessToken, String refreshToken, String userRole, String userName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN_KEY, accessToken);
        editor.putString(REFRESH_TOKEN_KEY, refreshToken);
        editor.putString(USER_ROLE_KEY, userRole);
        editor.putString(USER_NAME_KEY, userName);
        editor.apply();
    }

    public void clearTokens() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.contains(ACCESS_TOKEN_KEY);
    }

    public String getAccessToken() {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null);
    }

    public String getRefreshToken() {
        return sharedPreferences.getString(REFRESH_TOKEN_KEY, null);
    }

    public String getUserRole() {
        return sharedPreferences.getString(USER_ROLE_KEY, null);
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME_KEY, null);
    }
}
