package com.github.satoshun.android.mypractice.main;

import com.github.satoshun.android.mypractice.RxLifecycleSupplier;
import com.github.satoshun.android.mypractice.data.user.UserRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

  private final MainContract.View view;
  private final RxLifecycleSupplier supplier;
  private final UserRepository repository;

  public MainPresenter(MainContract.View view, RxLifecycleSupplier supplier) {
    this.view = view;
    this.supplier = supplier;
    this.repository = new UserRepository(); // todo use dagger
  }

  public void bind() {
    repository.getUser()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            view::showUser,
            e -> view.showError(e.getMessage()));
  }
}
