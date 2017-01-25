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

  @Override
  public void bind() {
    repository.getUser()
        .compose(supplier.bindRelease())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(s -> view.showProgress())
        .subscribe(
            u -> {
              view.showUser(u);
              view.hideProgress();
            },
            e -> {
              view.showError(e.getMessage());
              view.hideProgress();
            });
  }
}
