package com.practice.qifan.domain.usecase;

/**
 * Created by qifan on 2018/2/1.
 */

import com.practice.qifan.domain.executor.PostExecutionThread;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * that will execute its job in a background thread and will post the result in the UI thread.
 */

public abstract class UseCase<T> {

    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    protected UseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable<T> buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable()} ()} method.
     */
    public void execute(DisposableObserver<T> observer) {
        dispose();
        if (observer != null) {
            final Observable<T> observable = this.buildUseCaseObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(postExecutionThread.getScheduler());
            addDisposable(observable.subscribeWith(observer));
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.clear();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        if (disposable != null) {
            disposables.add(disposable);
        }

    }
}