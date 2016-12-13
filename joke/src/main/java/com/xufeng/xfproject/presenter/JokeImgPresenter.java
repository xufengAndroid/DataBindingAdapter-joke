package com.xufeng.xfproject.presenter;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.xufeng.xfproject.contract.JokeContract;
import com.xufeng.xfproject.data.repository.JokeRepository;
import com.xufeng.xfproject.data.resq.JokeResp;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xufeng on 2016/12/5.
 */

public class JokeImgPresenter implements JokeContract.Presenter {

    private final JokeContract.View mTasksView;

    private final JokeRepository mJokeRepository;

    private int mPage = 1;

    public JokeImgPresenter(@NonNull JokeRepository jokeRepository, @NonNull JokeContract.View tasksView) {
        this.mTasksView = Preconditions.checkNotNull(tasksView);
        this.mJokeRepository = Preconditions.checkNotNull(jokeRepository);
        mTasksView.setPresenter(this);

    }

    @Override
    public void start() {
        loadFirstPage();
    }


    @Override
    public void loadFirstPage() {
        mPage=1;
        mJokeRepository.getJokeImgList(mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JokeResp>() {
                    @Override
                    public void call(JokeResp jokeResp) {
                        switch (jokeResp.showapi_res_code){
                            case 0:
                                mTasksView.showNextPage(mPage,jokeResp.showapiResBody.maxResult,jokeResp.showapiResBody.allNum,jokeResp.showapiResBody.contentlist);
                                break;
                            default:
                                mTasksView.showPageErrMsg(jokeResp.showapi_res_code+"",jokeResp.showapi_res_error);
                                break;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void loadNextPage() {
        mPage++;
        mJokeRepository.getJokeImgList(mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JokeResp>() {
                    @Override
                    public void call(JokeResp jokeResp) {
                        switch (jokeResp.showapi_res_code){
                            case 0:
                                mTasksView.showNextPage(mPage,jokeResp.showapiResBody.maxResult,jokeResp.showapiResBody.allNum,jokeResp.showapiResBody.contentlist);
                            break;
                            default:
                                mTasksView.showPageErrMsg(jokeResp.showapi_res_code+"",jokeResp.showapi_res_error);
                                break;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }


}
