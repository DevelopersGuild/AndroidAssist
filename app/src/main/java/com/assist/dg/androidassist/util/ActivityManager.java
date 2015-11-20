package com.assist.dg.androidassist.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IntDef;
import com.assist.dg.androidassist.BuildConfig;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.activity.BaseActivity;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public class ActivityManager {

  @IntDef({
      LOAD_COLLEGE_LIST_REQUEST,
  }) public @interface RequestCode {
  }

  public static final int LOAD_COLLEGE_LIST_REQUEST = 0x01;

  private static ActivityManager sStarter;
  public static String TASK = BuildConfig.APPLICATION_ID + ".Task";
  public static String BUNDLE = BuildConfig.APPLICATION_ID + ".Bundle";

  private Context mContext;
  private Intent mIntent;
  private Bundle mBundle;
  private AnimationType mAnimType = AnimationType.SLIDE_HORIZONTAL;

  private int mRequestCode;

  public enum AnimationType {
    NOTHING(Open.NOTHING, Close.NOTHING),
    NONE(null, null),
    SLIDE_HORIZONTAL(Open.SLIDE_LEFT, Close.SLIDE_RIGHT),
    SLIDE_UP(Open.SLIDE_UP, Close.SLIDE_DOWN);

    private Open open;
    private Close close;

    AnimationType(Open open, Close close) {
      this.open = open;
      this.close = close;
    }

    public Open getOpenAnimation() {
      return open;
    }

    public Close getCloseAnimation() {
      return close;
    }

    public enum Open {
      NOTHING(0, 0),
      SLIDE_UP(R.anim.anim_window_in, R.anim.anim_window_out),
      SLIDE_LEFT(R.anim.anim_window_in_horizontal, R.anim.anim_window_out_horizontal);

      private int in, out;

      Open(@AnimRes int in, @AnimRes int out) {
        this.in = in;
        this.out = out;
      }

      @AnimRes public int getInAnimationId() {
        return in;
      }

      @AnimRes public int getOutAnimationId() {
        return out;
      }
    }

    public enum Close {
      NOTHING(0, 0),
      SLIDE_DOWN(R.anim.anim_window_close_in, R.anim.anim_window_close_out),
      SLIDE_RIGHT(R.anim.anim_window_close_in_horizontal, R.anim.anim_window_close_out_horizontal);

      private int in, out;

      Close(@AnimRes int in, @AnimRes int out) {
        this.in = in;
        this.out = out;
      }

      @AnimatorRes public int getInAnimationId() {
        return in;
      }

      @AnimatorRes public int getOutAnimationId() {
        return out;
      }
    }
  }

  public static ActivityManager with(Context context, FragmentType task) {
    if (sStarter == null) {
      synchronized (ActivityManager.class) {
        if (sStarter == null) {
          sStarter = new ActivityManager(context, task);
        }
      }
    } else {
      sStarter.newStarter(context, task);
    }
    return sStarter;
  }

  public static ActivityManager with(Context context, String uri) {
    if (sStarter == null) {
      synchronized (ActivityManager.class) {
        if (sStarter == null) {
          sStarter = new ActivityManager(context, uri);
        }
      }
    } else {
      sStarter.newStarter(context, uri);
    }
    return sStarter;
  }

  public ActivityManager(Context context, FragmentType task) {
    newStarter(context, task);
  }

  public ActivityManager(Context context, String uri) {
    newStarter(context, uri);
  }

  // SET ALL TO DEFAULT
  private void newStarter(Context context, FragmentType task) {
    setContext(context);
    setTask(task);
    clearBundle();
    clearAnimation();
  }

  // SET ALL TO DEFAULT
  private void newStarter(Context context, String uri) {
    setContext(context);
    setTask(uri);
    clearBundle();
    clearAnimation();
  }

  // SET CONTEXT
  private void setContext(Context context) {
    mContext = context;
  }

  // SET TASK
  private ActivityManager setTask(FragmentType task) {
    WLog.d(task.name() + " loaded by Activity Manager");
    mRequestCode = task.getValue();
    mIntent = new Intent(mContext, BaseActivity.class);
    mIntent.putExtra(TASK, task);
    return this;
  }

  private ActivityManager setTask(String uri) {
    WLog.d(uri + " loaded by Activity Manager");
    mIntent = new Intent(Intent.ACTION_VIEW);
    mIntent.setData(Uri.parse(uri));
    return this;
  }

  public ActivityManager setRequestCode(@RequestCode int code) {
    mRequestCode = code;
    return this;
  }

  // SET BUNDLE
  public ActivityManager addBundle(Bundle bundle) {
    if (mBundle == null) {
      mBundle = new Bundle();
    }

    mBundle.putAll(bundle);

    return this;
  }

  private void clearBundle() {
    if (mBundle == null) {
      mBundle = new Bundle();
    } else {
      mBundle.clear();
    }
  }

  public ActivityManager setAnimationType(AnimationType type) {
    mAnimType = type;
    return this;
  }

  private void clearAnimation() {
    mAnimType = AnimationType.SLIDE_HORIZONTAL;
  }

  public void go() {
    if (mBundle == null) {
      mBundle = new Bundle();
    }
    mBundle.putSerializable(BundleSet.TRANSITION_ANIMATION_TYPE, mAnimType);
    mIntent.putExtra(BUNDLE, mBundle);

    Bundle options = null;

    if (mContext instanceof BaseActivity) {
      ((BaseActivity) mContext).startActivityForResult(mIntent, mRequestCode, options);
    } else {
      mContext.startActivity(mIntent, options);
    }

    if (mContext instanceof Activity && mAnimType != AnimationType.NONE) {
      ((Activity) mContext).overridePendingTransition(
          mAnimType.getOpenAnimation().getInAnimationId(),
          mAnimType.getOpenAnimation().getOutAnimationId());
    }

    mBundle = null;
    mContext = null;
    mIntent = null;
    mRequestCode = 0;
    mAnimType = AnimationType.SLIDE_HORIZONTAL;
  }
}
