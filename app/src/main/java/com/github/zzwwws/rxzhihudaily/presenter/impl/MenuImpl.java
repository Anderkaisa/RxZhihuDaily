package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.model.entities.Other;
import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.Topics;
import com.github.zzwwws.rxzhihudaily.presenter.infr.MenuRecyclerView;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.LoadTopicsCase;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zzwwws on 2016/2/20.
 */
public class MenuImpl implements MenuPresenter<MenuRecyclerView> {

    Subscriber<Topics> loadSubscribe;
    MenuRecyclerView menuRecyclerView;
    LoadTopicsCase loadTopicsCase;

    @Override
    public void attachView(MenuRecyclerView view) {
        this.menuRecyclerView = view;
        this.loadTopicsCase = new LoadTopicsCase();
    }

    @Override
    public void detachView() {
        loadSubscribe.unsubscribe();
    }

    @Override
    public void loadTopics() {
        loadSubscribe = new Subscriber<Topics>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Topics topics) {
                menuRecyclerView.bindTopics(topics.getOthers());
            }
        };
        loadTopicsCase.subscribe(loadSubscribe, "");
    }

    @Override
    public void downLoadOffLine() {

    }

    @Override
    public void login() {

    }

    public List<Other> getDefaultTopicList(String[] topics){
        List<Other> topicList = new ArrayList<>();
        for(String topicName : topics){
            Other topic = new Other();
            topic.setName(topicName);
            topicList.add(topic);
        }
        return topicList;
    }

}
