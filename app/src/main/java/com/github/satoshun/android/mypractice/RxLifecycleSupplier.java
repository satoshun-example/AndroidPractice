package com.github.satoshun.android.mypractice;

import io.reactivex.ObservableTransformer;

public interface RxLifecycleSupplier {

  <R> ObservableTransformer<R, R> bindRelease();
}
