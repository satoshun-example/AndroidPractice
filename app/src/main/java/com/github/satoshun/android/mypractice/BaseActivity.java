package com.github.satoshun.android.mypractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

public abstract class BaseActivity extends AppCompatActivity implements RxLifecycleSupplier {

  private final BehaviorSubject<ActivityEvent> lifecycle = BehaviorSubject.create();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    lifecycle.onNext(ActivityEvent.CREATE);
  }

  @Override protected void onStart() {
    super.onStart();
    lifecycle.onNext(ActivityEvent.START);
  }

  @Override protected void onResume() {
    super.onResume();
    lifecycle.onNext(ActivityEvent.RESUME);
  }

  @Override protected void onPause() {
    lifecycle.onNext(ActivityEvent.PAUSE);
    super.onPause();
  }

  @Override protected void onStop() {
    lifecycle.onNext(ActivityEvent.STOP);
    super.onStop();
  }

  @Override protected void onDestroy() {
    lifecycle.onNext(ActivityEvent.DESTROY);
    super.onDestroy();
  }

  @Override public <R> ObservableTransformer<R, R> bindRelease() {
    return bindUntilEvent(ActivityEvent.DESTROY);
  }

  protected  <T> ObservableTransformer<T, T> bindUntilEvent(ActivityEvent event) {
    return RxLifecycle.bindUntilEvent(lifecycle, event);
  }
}
