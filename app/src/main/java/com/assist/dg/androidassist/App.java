package com.assist.dg.androidassist;

import android.app.Application;
import android.text.TextUtils;
import com.squareup.okhttp.OkHttpClient;
import java.security.GeneralSecurityException;
import javax.net.ssl.SSLContext;

/**
 * Created by doyonghoon on 2015. 10. 28..
 */
public class App extends Application {

  private static App singleton;

  private OkHttpClient client;

  private void setHttps(OkHttpClient client) {
    if (!TextUtils.isEmpty(BuildConfig.END_POINT) && BuildConfig.END_POINT.contains("https")) {
      try {
        SSLContext sslContext;
        try {
          sslContext = SSLContext.getInstance("TLS");
          sslContext.init(null, null, null);
        } catch (GeneralSecurityException e) {
          throw new AssertionError(); // The system has no TLS. Just give up.
        }
        client.setSslSocketFactory(sslContext.getSocketFactory());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public App() {

  }

  public static App getInstance() {
    if (singleton == null) {
      singleton = new App();
    }
    return singleton;
  }

  @Override public void onCreate() {
    super.onCreate();
  }
}
