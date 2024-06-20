// Generated by view binder compiler. Do not edit!
package com.capstoneproject.auxilium.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstoneproject.auxilium.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnAboutUs;

  @NonNull
  public final MaterialButton btnBackProfile;

  @NonNull
  public final MaterialButton btnEditProfile;

  @NonNull
  public final MaterialButton btnLanguage;

  @NonNull
  public final MaterialButton btnLogout;

  @NonNull
  public final MaterialButton btnResetPassword;

  @NonNull
  public final View dividerGuest;

  @NonNull
  public final FrameLayout fragmentContainer;

  @NonNull
  public final CircleImageView imageAvatar;

  @NonNull
  public final ImageView imageDateJoined;

  @NonNull
  public final ImageView imageEmail;

  @NonNull
  public final MaterialCardView profileCardView;

  @NonNull
  public final TextView textCopyright;

  @NonNull
  public final TextView textDateJoined;

  @NonNull
  public final TextView textEmail;

  @NonNull
  public final TextView textName;

  @NonNull
  public final TextView tvLanguage;

  private ActivityProfileBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnAboutUs, @NonNull MaterialButton btnBackProfile,
      @NonNull MaterialButton btnEditProfile, @NonNull MaterialButton btnLanguage,
      @NonNull MaterialButton btnLogout, @NonNull MaterialButton btnResetPassword,
      @NonNull View dividerGuest, @NonNull FrameLayout fragmentContainer,
      @NonNull CircleImageView imageAvatar, @NonNull ImageView imageDateJoined,
      @NonNull ImageView imageEmail, @NonNull MaterialCardView profileCardView,
      @NonNull TextView textCopyright, @NonNull TextView textDateJoined,
      @NonNull TextView textEmail, @NonNull TextView textName, @NonNull TextView tvLanguage) {
    this.rootView = rootView;
    this.btnAboutUs = btnAboutUs;
    this.btnBackProfile = btnBackProfile;
    this.btnEditProfile = btnEditProfile;
    this.btnLanguage = btnLanguage;
    this.btnLogout = btnLogout;
    this.btnResetPassword = btnResetPassword;
    this.dividerGuest = dividerGuest;
    this.fragmentContainer = fragmentContainer;
    this.imageAvatar = imageAvatar;
    this.imageDateJoined = imageDateJoined;
    this.imageEmail = imageEmail;
    this.profileCardView = profileCardView;
    this.textCopyright = textCopyright;
    this.textDateJoined = textDateJoined;
    this.textEmail = textEmail;
    this.textName = textName;
    this.tvLanguage = tvLanguage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_about_us;
      MaterialButton btnAboutUs = ViewBindings.findChildViewById(rootView, id);
      if (btnAboutUs == null) {
        break missingId;
      }

      id = R.id.btn_back_profile;
      MaterialButton btnBackProfile = ViewBindings.findChildViewById(rootView, id);
      if (btnBackProfile == null) {
        break missingId;
      }

      id = R.id.btn_edit_profile;
      MaterialButton btnEditProfile = ViewBindings.findChildViewById(rootView, id);
      if (btnEditProfile == null) {
        break missingId;
      }

      id = R.id.btn_language;
      MaterialButton btnLanguage = ViewBindings.findChildViewById(rootView, id);
      if (btnLanguage == null) {
        break missingId;
      }

      id = R.id.btn_logout;
      MaterialButton btnLogout = ViewBindings.findChildViewById(rootView, id);
      if (btnLogout == null) {
        break missingId;
      }

      id = R.id.btn_reset_password;
      MaterialButton btnResetPassword = ViewBindings.findChildViewById(rootView, id);
      if (btnResetPassword == null) {
        break missingId;
      }

      id = R.id.divider_guest;
      View dividerGuest = ViewBindings.findChildViewById(rootView, id);
      if (dividerGuest == null) {
        break missingId;
      }

      id = R.id.fragment_container;
      FrameLayout fragmentContainer = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainer == null) {
        break missingId;
      }

      id = R.id.image_avatar;
      CircleImageView imageAvatar = ViewBindings.findChildViewById(rootView, id);
      if (imageAvatar == null) {
        break missingId;
      }

      id = R.id.image_date_joined;
      ImageView imageDateJoined = ViewBindings.findChildViewById(rootView, id);
      if (imageDateJoined == null) {
        break missingId;
      }

      id = R.id.image_email;
      ImageView imageEmail = ViewBindings.findChildViewById(rootView, id);
      if (imageEmail == null) {
        break missingId;
      }

      id = R.id.profile_card_view;
      MaterialCardView profileCardView = ViewBindings.findChildViewById(rootView, id);
      if (profileCardView == null) {
        break missingId;
      }

      id = R.id.text_copyright;
      TextView textCopyright = ViewBindings.findChildViewById(rootView, id);
      if (textCopyright == null) {
        break missingId;
      }

      id = R.id.text_date_joined;
      TextView textDateJoined = ViewBindings.findChildViewById(rootView, id);
      if (textDateJoined == null) {
        break missingId;
      }

      id = R.id.text_email;
      TextView textEmail = ViewBindings.findChildViewById(rootView, id);
      if (textEmail == null) {
        break missingId;
      }

      id = R.id.text_name;
      TextView textName = ViewBindings.findChildViewById(rootView, id);
      if (textName == null) {
        break missingId;
      }

      id = R.id.tv_language;
      TextView tvLanguage = ViewBindings.findChildViewById(rootView, id);
      if (tvLanguage == null) {
        break missingId;
      }

      return new ActivityProfileBinding((ConstraintLayout) rootView, btnAboutUs, btnBackProfile,
          btnEditProfile, btnLanguage, btnLogout, btnResetPassword, dividerGuest, fragmentContainer,
          imageAvatar, imageDateJoined, imageEmail, profileCardView, textCopyright, textDateJoined,
          textEmail, textName, tvLanguage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
