package com.assist.dg.androidassist;

import android.app.Application;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by doyonghoon on 2015. 10. 28..
 */
public class App extends Application {

  private static App singleton;
  private Gson gson;

  private OkHttpClient client;
  private Retrofit retrofit;

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
    singleton = this;
    getRetrofit();
  }

  private Retrofit createRetrofit(OkHttpClient client) {
    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl(BuildConfig.END_POINT)
        .addConverterFactory(GsonConverterFactory.create(getInstance().getGson()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .callbackExecutor(createExecutor())
        .client(client);
    return builder.build();
  }

  private Executor createExecutor() {
    SynchronousQueue<Runnable> queue = new SynchronousQueue<>();
    return new ThreadPoolExecutor(5, 30, 15, TimeUnit.SECONDS, queue);
  }

  public static Retrofit getRetrofit() {
    if (getInstance().client == null) {
      getInstance().client = getInstance().createHttpClient();
    }
    if (getInstance().retrofit == null) {
      getInstance().retrofit = getInstance().createRetrofit(getInstance().client);
    }
    return getInstance().retrofit;
  }

  public com.google.gson.Gson getGson() {
    if (gson == null) {
      gson = new com.google.gson.GsonBuilder().serializeNulls()
          .setDateFormat("yyyy-MM-dd'T'HH:mm:ss+09:00")
          .create();
    }
    return gson;
  }

  private OkHttpClient createHttpClient() {
    client = new OkHttpClient();
    try {
      int cacheSize = 5 * 1024 * 1024; // 10 MiB
      File cacheDirectory = new File(getCacheDir().getAbsolutePath(), "HttpCache");
      Cache cache = new Cache(cacheDirectory, cacheSize);
      client.setCache(cache);
    } catch (Exception e) {
      e.printStackTrace();
    }
    setHttps(client);
    return client;
  }

  private void setHttps(OkHttpClient client) {
    if (!TextUtils.isEmpty(BuildConfig.END_POINT) && BuildConfig.END_POINT.contains("https")) {
      try {
        SSLContext sslContext;
        try {
          sslContext = SSLContext.getInstance("TLS");
          sslContext.init(null, null, null);
        } catch (GeneralSecurityException e) {
          // The system has no TLS. Just give up.
          throw new AssertionError();
        }
        client.setSslSocketFactory(sslContext.getSocketFactory());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
