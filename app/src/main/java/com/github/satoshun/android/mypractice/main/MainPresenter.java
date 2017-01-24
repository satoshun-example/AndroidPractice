package com.github.satoshun.android.mypractice.main;

import com.github.satoshun.android.mypractice.RxLifecycleSupplier;

public class MainPresenter implements MainContract.Presenter {

  private final MainContract.View view;
  private final RxLifecycleSupplier supplier;

  public MainPresenter(MainContract.View view, RxLifecycleSupplier supplier) {
    this.view = view;
    this.supplier = supplier;
  }

  public void bind() {
  }

  public void unbind() {
  }
}
