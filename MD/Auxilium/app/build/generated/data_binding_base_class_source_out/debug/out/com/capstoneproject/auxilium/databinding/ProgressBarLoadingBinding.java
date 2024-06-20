// Generated by view binder compiler. Do not edit!
package com.capstoneproject.auxilium.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.capstoneproject.auxilium.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ProgressBarLoadingBinding implements ViewBinding {
  @NonNull
  private final ProgressBar rootView;

  @NonNull
  public final ProgressBar progressBarLoading;

  private ProgressBarLoadingBinding(@NonNull ProgressBar rootView,
      @NonNull ProgressBar progressBarLoading) {
    this.rootView = rootView;
    this.progressBarLoading = progressBarLoading;
  }

  @Override
  @NonNull
  public ProgressBar getRoot() {
    return rootView;
  }

  @NonNull
  public static ProgressBarLoadingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ProgressBarLoadingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.progress_bar_loading, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ProgressBarLoadingBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    ProgressBar progressBarLoading = (ProgressBar) rootView;

    return new ProgressBarLoadingBinding((ProgressBar) rootView, progressBarLoading);
  }
}
