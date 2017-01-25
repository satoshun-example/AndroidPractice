package com.github.satoshun.android.mypractice.main;

import com.github.satoshun.android.mypractice.data.user.User;

public interface MainContract {

  interface View {
    void showUser(User user);

    void showError(String message);
  }

  interface Presenter {
  }
}
