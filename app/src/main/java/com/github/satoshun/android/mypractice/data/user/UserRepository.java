package com.github.satoshun.android.mypractice.data.user;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class UserRepository {

  public Observable<User> getUser() {
    return Observable.fromCallable(() -> new User("test:" + System.currentTimeMillis()))
        .delay(3, TimeUnit.SECONDS);
  }
}
