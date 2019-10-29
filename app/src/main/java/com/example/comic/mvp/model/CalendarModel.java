package com.example.comic.mvp.model;

import android.app.Application;

import com.example.comic.app.data.api.cache.AnimeCache;
import com.example.comic.app.data.api.service.AnimeService;
import com.example.comic.app.data.entity.AnimeBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.example.comic.mvp.contract.CalendarContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/25/2019 11:03
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class CalendarModel extends BaseModel implements CalendarContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public CalendarModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<List<AnimeBean>> getAllData(boolean update) {
//        return mRepositoryManager.obtainRetrofitService(AnimeService.class).getAllData();
        Observable<List<AnimeBean>> data = mRepositoryManager.obtainRetrofitService(AnimeService.class).getAllData();
        return mRepositoryManager
                .obtainCacheService(AnimeCache.class)
                .getAllData(data,new EvictProvider(update))
                .map(new Function<Reply<List<AnimeBean>>, List<AnimeBean>>() {
                    @Override
                    public List<AnimeBean> apply(Reply<List<AnimeBean>> listReply) throws Exception {
                        return listReply.getData();
                    }
                });
    }
}