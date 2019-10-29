package com.example.comic.app.data.api.cache;

import com.example.comic.app.data.entity.AnimeBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

public interface AnimeCache {

    //    @Encrypt
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<AnimeBean>>> getAllData(Observable<List<AnimeBean>> data, EvictProvider evictProvider);

}
