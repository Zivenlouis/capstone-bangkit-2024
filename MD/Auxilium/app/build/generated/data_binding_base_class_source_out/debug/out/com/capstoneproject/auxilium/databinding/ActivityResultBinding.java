// Generated by view binder compiler. Do not edit!
package com.capstoneproject.auxilium.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstoneproject.auxilium.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityResultBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final MaterialButton btnAddWishlist;

  @NonNull
  public final ImageFilterButton btnStar1;

  @NonNull
  public final ImageFilterButton btnStar2;

  @NonNull
  public final ImageFilterButton btnStar3;

  @NonNull
  public final ImageFilterButton btnStar4;

  @NonNull
  public final ImageFilterButton btnStar5;

  @NonNull
  public final MaterialCardView cvResultPhone;

  @NonNull
  public final ImageView ivPhoneImages;

  @NonNull
  public final RecyclerView rvRecommended;

  @NonNull
  public final NestedScrollView scrollTableSpecs;

  @NonNull
  public final TableLayout tableSpesifications;

  @NonNull
  public final TextView tvBattery;

  @NonNull
  public final TextView tvBatteryLabel;

  @NonNull
  public final TextView tvBrand;

  @NonNull
  public final TextView tvBrandLabel;

  @NonNull
  public final TextView tvCharging;

  @NonNull
  public final TextView tvChargingLabel;

  @NonNull
  public final TextView tvChipset;

  @NonNull
  public final TextView tvChipsetLabel;

  @NonNull
  public final TextView tvColors;

  @NonNull
  public final TextView tvColorsLabel;

  @NonNull
  public final TextView tvEarphoneJack;

  @NonNull
  public final TextView tvEarphoneJackLabel;

  @NonNull
  public final TextView tvMainCamera1;

  @NonNull
  public final TextView tvMainCamera1Label;

  @NonNull
  public final TextView tvMainCamera2;

  @NonNull
  public final TextView tvMainCamera2Label;

  @NonNull
  public final TextView tvMainCamera3;

  @NonNull
  public final TextView tvMainCamera3Label;

  @NonNull
  public final TextView tvMemory;

  @NonNull
  public final TextView tvMemoryLabel;

  @NonNull
  public final TextView tvNfc;

  @NonNull
  public final TextView tvNfcLabel;

  @NonNull
  public final TextView tvPhoneNames;

  @NonNull
  public final TextView tvPhoneOs;

  @NonNull
  public final TextView tvPrice;

  @NonNull
  public final TextView tvPriceLabel;

  @NonNull
  public final TextView tvRam;

  @NonNull
  public final TextView tvRamLabel;

  @NonNull
  public final TextView tvRatePhones;

  @NonNull
  public final TextView tvReleasedDate;

  @NonNull
  public final TextView tvReleasedDateLabel;

  @NonNull
  public final TextView tvResolution;

  @NonNull
  public final TextView tvResolutionLabel;

  @NonNull
  public final TextView tvResultTitle;

  @NonNull
  public final TextView tvResultTitle2;

  @NonNull
  public final TextView tvResultTitle3;

  @NonNull
  public final TextView tvSelfieCamera;

  @NonNull
  public final TextView tvSelfieCameraLabel;

  @NonNull
  public final TextView tvWeight;

  @NonNull
  public final TextView tvWeightLabel;

  private ActivityResultBinding(@NonNull NestedScrollView rootView,
      @NonNull MaterialButton btnAddWishlist, @NonNull ImageFilterButton btnStar1,
      @NonNull ImageFilterButton btnStar2, @NonNull ImageFilterButton btnStar3,
      @NonNull ImageFilterButton btnStar4, @NonNull ImageFilterButton btnStar5,
      @NonNull MaterialCardView cvResultPhone, @NonNull ImageView ivPhoneImages,
      @NonNull RecyclerView rvRecommended, @NonNull NestedScrollView scrollTableSpecs,
      @NonNull TableLayout tableSpesifications, @NonNull TextView tvBattery,
      @NonNull TextView tvBatteryLabel, @NonNull TextView tvBrand, @NonNull TextView tvBrandLabel,
      @NonNull TextView tvCharging, @NonNull TextView tvChargingLabel, @NonNull TextView tvChipset,
      @NonNull TextView tvChipsetLabel, @NonNull TextView tvColors, @NonNull TextView tvColorsLabel,
      @NonNull TextView tvEarphoneJack, @NonNull TextView tvEarphoneJackLabel,
      @NonNull TextView tvMainCamera1, @NonNull TextView tvMainCamera1Label,
      @NonNull TextView tvMainCamera2, @NonNull TextView tvMainCamera2Label,
      @NonNull TextView tvMainCamera3, @NonNull TextView tvMainCamera3Label,
      @NonNull TextView tvMemory, @NonNull TextView tvMemoryLabel, @NonNull TextView tvNfc,
      @NonNull TextView tvNfcLabel, @NonNull TextView tvPhoneNames, @NonNull TextView tvPhoneOs,
      @NonNull TextView tvPrice, @NonNull TextView tvPriceLabel, @NonNull TextView tvRam,
      @NonNull TextView tvRamLabel, @NonNull TextView tvRatePhones,
      @NonNull TextView tvReleasedDate, @NonNull TextView tvReleasedDateLabel,
      @NonNull TextView tvResolution, @NonNull TextView tvResolutionLabel,
      @NonNull TextView tvResultTitle, @NonNull TextView tvResultTitle2,
      @NonNull TextView tvResultTitle3, @NonNull TextView tvSelfieCamera,
      @NonNull TextView tvSelfieCameraLabel, @NonNull TextView tvWeight,
      @NonNull TextView tvWeightLabel) {
    this.rootView = rootView;
    this.btnAddWishlist = btnAddWishlist;
    this.btnStar1 = btnStar1;
    this.btnStar2 = btnStar2;
    this.btnStar3 = btnStar3;
    this.btnStar4 = btnStar4;
    this.btnStar5 = btnStar5;
    this.cvResultPhone = cvResultPhone;
    this.ivPhoneImages = ivPhoneImages;
    this.rvRecommended = rvRecommended;
    this.scrollTableSpecs = scrollTableSpecs;
    this.tableSpesifications = tableSpesifications;
    this.tvBattery = tvBattery;
    this.tvBatteryLabel = tvBatteryLabel;
    this.tvBrand = tvBrand;
    this.tvBrandLabel = tvBrandLabel;
    this.tvCharging = tvCharging;
    this.tvChargingLabel = tvChargingLabel;
    this.tvChipset = tvChipset;
    this.tvChipsetLabel = tvChipsetLabel;
    this.tvColors = tvColors;
    this.tvColorsLabel = tvColorsLabel;
    this.tvEarphoneJack = tvEarphoneJack;
    this.tvEarphoneJackLabel = tvEarphoneJackLabel;
    this.tvMainCamera1 = tvMainCamera1;
    this.tvMainCamera1Label = tvMainCamera1Label;
    this.tvMainCamera2 = tvMainCamera2;
    this.tvMainCamera2Label = tvMainCamera2Label;
    this.tvMainCamera3 = tvMainCamera3;
    this.tvMainCamera3Label = tvMainCamera3Label;
    this.tvMemory = tvMemory;
    this.tvMemoryLabel = tvMemoryLabel;
    this.tvNfc = tvNfc;
    this.tvNfcLabel = tvNfcLabel;
    this.tvPhoneNames = tvPhoneNames;
    this.tvPhoneOs = tvPhoneOs;
    this.tvPrice = tvPrice;
    this.tvPriceLabel = tvPriceLabel;
    this.tvRam = tvRam;
    this.tvRamLabel = tvRamLabel;
    this.tvRatePhones = tvRatePhones;
    this.tvReleasedDate = tvReleasedDate;
    this.tvReleasedDateLabel = tvReleasedDateLabel;
    this.tvResolution = tvResolution;
    this.tvResolutionLabel = tvResolutionLabel;
    this.tvResultTitle = tvResultTitle;
    this.tvResultTitle2 = tvResultTitle2;
    this.tvResultTitle3 = tvResultTitle3;
    this.tvSelfieCamera = tvSelfieCamera;
    this.tvSelfieCameraLabel = tvSelfieCameraLabel;
    this.tvWeight = tvWeight;
    this.tvWeightLabel = tvWeightLabel;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityResultBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityResultBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_result, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityResultBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_wishlist;
      MaterialButton btnAddWishlist = ViewBindings.findChildViewById(rootView, id);
      if (btnAddWishlist == null) {
        break missingId;
      }

      id = R.id.btn_star1;
      ImageFilterButton btnStar1 = ViewBindings.findChildViewById(rootView, id);
      if (btnStar1 == null) {
        break missingId;
      }

      id = R.id.btn_star2;
      ImageFilterButton btnStar2 = ViewBindings.findChildViewById(rootView, id);
      if (btnStar2 == null) {
        break missingId;
      }

      id = R.id.btn_star3;
      ImageFilterButton btnStar3 = ViewBindings.findChildViewById(rootView, id);
      if (btnStar3 == null) {
        break missingId;
      }

      id = R.id.btn_star4;
      ImageFilterButton btnStar4 = ViewBindings.findChildViewById(rootView, id);
      if (btnStar4 == null) {
        break missingId;
      }

      id = R.id.btn_star5;
      ImageFilterButton btnStar5 = ViewBindings.findChildViewById(rootView, id);
      if (btnStar5 == null) {
        break missingId;
      }

      id = R.id.cv_result_phone;
      MaterialCardView cvResultPhone = ViewBindings.findChildViewById(rootView, id);
      if (cvResultPhone == null) {
        break missingId;
      }

      id = R.id.iv_phone_images;
      ImageView ivPhoneImages = ViewBindings.findChildViewById(rootView, id);
      if (ivPhoneImages == null) {
        break missingId;
      }

      id = R.id.rvRecommended;
      RecyclerView rvRecommended = ViewBindings.findChildViewById(rootView, id);
      if (rvRecommended == null) {
        break missingId;
      }

      id = R.id.scroll_table_specs;
      NestedScrollView scrollTableSpecs = ViewBindings.findChildViewById(rootView, id);
      if (scrollTableSpecs == null) {
        break missingId;
      }

      id = R.id.table_spesifications;
      TableLayout tableSpesifications = ViewBindings.findChildViewById(rootView, id);
      if (tableSpesifications == null) {
        break missingId;
      }

      id = R.id.tv_battery;
      TextView tvBattery = ViewBindings.findChildViewById(rootView, id);
      if (tvBattery == null) {
        break missingId;
      }

      id = R.id.tv_battery_label;
      TextView tvBatteryLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvBatteryLabel == null) {
        break missingId;
      }

      id = R.id.tv_brand;
      TextView tvBrand = ViewBindings.findChildViewById(rootView, id);
      if (tvBrand == null) {
        break missingId;
      }

      id = R.id.tv_brand_label;
      TextView tvBrandLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvBrandLabel == null) {
        break missingId;
      }

      id = R.id.tv_charging;
      TextView tvCharging = ViewBindings.findChildViewById(rootView, id);
      if (tvCharging == null) {
        break missingId;
      }

      id = R.id.tv_charging_label;
      TextView tvChargingLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvChargingLabel == null) {
        break missingId;
      }

      id = R.id.tv_chipset;
      TextView tvChipset = ViewBindings.findChildViewById(rootView, id);
      if (tvChipset == null) {
        break missingId;
      }

      id = R.id.tv_chipset_label;
      TextView tvChipsetLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvChipsetLabel == null) {
        break missingId;
      }

      id = R.id.tv_colors;
      TextView tvColors = ViewBindings.findChildViewById(rootView, id);
      if (tvColors == null) {
        break missingId;
      }

      id = R.id.tv_colors_label;
      TextView tvColorsLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvColorsLabel == null) {
        break missingId;
      }

      id = R.id.tv_earphone_jack;
      TextView tvEarphoneJack = ViewBindings.findChildViewById(rootView, id);
      if (tvEarphoneJack == null) {
        break missingId;
      }

      id = R.id.tv_earphone_jack_label;
      TextView tvEarphoneJackLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvEarphoneJackLabel == null) {
        break missingId;
      }

      id = R.id.tv_main_camera1;
      TextView tvMainCamera1 = ViewBindings.findChildViewById(rootView, id);
      if (tvMainCamera1 == null) {
        break missingId;
      }

      id = R.id.tv_main_camera1_label;
      TextView tvMainCamera1Label = ViewBindings.findChildViewById(rootView, id);
      if (tvMainCamera1Label == null) {
        break missingId;
      }

      id = R.id.tv_main_camera2;
      TextView tvMainCamera2 = ViewBindings.findChildViewById(rootView, id);
      if (tvMainCamera2 == null) {
        break missingId;
      }

      id = R.id.tv_main_camera2_label;
      TextView tvMainCamera2Label = ViewBindings.findChildViewById(rootView, id);
      if (tvMainCamera2Label == null) {
        break missingId;
      }

      id = R.id.tv_main_camera3;
      TextView tvMainCamera3 = ViewBindings.findChildViewById(rootView, id);
      if (tvMainCamera3 == null) {
        break missingId;
      }

      id = R.id.tv_main_camera3_label;
      TextView tvMainCamera3Label = ViewBindings.findChildViewById(rootView, id);
      if (tvMainCamera3Label == null) {
        break missingId;
      }

      id = R.id.tv_memory;
      TextView tvMemory = ViewBindings.findChildViewById(rootView, id);
      if (tvMemory == null) {
        break missingId;
      }

      id = R.id.tv_memory_label;
      TextView tvMemoryLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvMemoryLabel == null) {
        break missingId;
      }

      id = R.id.tv_nfc;
      TextView tvNfc = ViewBindings.findChildViewById(rootView, id);
      if (tvNfc == null) {
        break missingId;
      }

      id = R.id.tv_nfc_label;
      TextView tvNfcLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvNfcLabel == null) {
        break missingId;
      }

      id = R.id.tv_phone_names;
      TextView tvPhoneNames = ViewBindings.findChildViewById(rootView, id);
      if (tvPhoneNames == null) {
        break missingId;
      }

      id = R.id.tv_phone_os;
      TextView tvPhoneOs = ViewBindings.findChildViewById(rootView, id);
      if (tvPhoneOs == null) {
        break missingId;
      }

      id = R.id.tv_price;
      TextView tvPrice = ViewBindings.findChildViewById(rootView, id);
      if (tvPrice == null) {
        break missingId;
      }

      id = R.id.tv_price_label;
      TextView tvPriceLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvPriceLabel == null) {
        break missingId;
      }

      id = R.id.tv_ram;
      TextView tvRam = ViewBindings.findChildViewById(rootView, id);
      if (tvRam == null) {
        break missingId;
      }

      id = R.id.tv_ram_label;
      TextView tvRamLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvRamLabel == null) {
        break missingId;
      }

      id = R.id.tv_rate_phones;
      TextView tvRatePhones = ViewBindings.findChildViewById(rootView, id);
      if (tvRatePhones == null) {
        break missingId;
      }

      id = R.id.tv_released_date;
      TextView tvReleasedDate = ViewBindings.findChildViewById(rootView, id);
      if (tvReleasedDate == null) {
        break missingId;
      }

      id = R.id.tv_released_date_label;
      TextView tvReleasedDateLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvReleasedDateLabel == null) {
        break missingId;
      }

      id = R.id.tv_resolution;
      TextView tvResolution = ViewBindings.findChildViewById(rootView, id);
      if (tvResolution == null) {
        break missingId;
      }

      id = R.id.tv_resolution_label;
      TextView tvResolutionLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvResolutionLabel == null) {
        break missingId;
      }

      id = R.id.tv_result_title;
      TextView tvResultTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvResultTitle == null) {
        break missingId;
      }

      id = R.id.tv_result_title2;
      TextView tvResultTitle2 = ViewBindings.findChildViewById(rootView, id);
      if (tvResultTitle2 == null) {
        break missingId;
      }

      id = R.id.tv_result_title3;
      TextView tvResultTitle3 = ViewBindings.findChildViewById(rootView, id);
      if (tvResultTitle3 == null) {
        break missingId;
      }

      id = R.id.tv_selfie_camera;
      TextView tvSelfieCamera = ViewBindings.findChildViewById(rootView, id);
      if (tvSelfieCamera == null) {
        break missingId;
      }

      id = R.id.tv_selfie_camera_label;
      TextView tvSelfieCameraLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvSelfieCameraLabel == null) {
        break missingId;
      }

      id = R.id.tv_weight;
      TextView tvWeight = ViewBindings.findChildViewById(rootView, id);
      if (tvWeight == null) {
        break missingId;
      }

      id = R.id.tv_weight_label;
      TextView tvWeightLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvWeightLabel == null) {
        break missingId;
      }

      return new ActivityResultBinding((NestedScrollView) rootView, btnAddWishlist, btnStar1,
          btnStar2, btnStar3, btnStar4, btnStar5, cvResultPhone, ivPhoneImages, rvRecommended,
          scrollTableSpecs, tableSpesifications, tvBattery, tvBatteryLabel, tvBrand, tvBrandLabel,
          tvCharging, tvChargingLabel, tvChipset, tvChipsetLabel, tvColors, tvColorsLabel,
          tvEarphoneJack, tvEarphoneJackLabel, tvMainCamera1, tvMainCamera1Label, tvMainCamera2,
          tvMainCamera2Label, tvMainCamera3, tvMainCamera3Label, tvMemory, tvMemoryLabel, tvNfc,
          tvNfcLabel, tvPhoneNames, tvPhoneOs, tvPrice, tvPriceLabel, tvRam, tvRamLabel,
          tvRatePhones, tvReleasedDate, tvReleasedDateLabel, tvResolution, tvResolutionLabel,
          tvResultTitle, tvResultTitle2, tvResultTitle3, tvSelfieCamera, tvSelfieCameraLabel,
          tvWeight, tvWeightLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
