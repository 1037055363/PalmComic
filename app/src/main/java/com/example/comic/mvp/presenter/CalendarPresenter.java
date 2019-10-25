package com.example.comic.mvp.presenter;

import android.app.Application;

import com.example.comic.R;
import com.example.comic.app.data.entity.AnimeBean;
import com.example.comic.mvp.ui.adapter.HomeItemAdapter;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import timber.log.Timber;

import javax.inject.Inject;

import com.example.comic.mvp.contract.CalendarContract;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;


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
public class CalendarPresenter extends BasePresenter<CalendarContract.Model, CalendarContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    private HomeItemAdapter mAdapter;

    @Inject
    public CalendarPresenter(CalendarContract.Model model, CalendarContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void refreshAll(int position) {
        mModel.getAllData()
                // 当请求失败时重新请求  3为次数  2为间隔s
                .retryWhen(new RetryWithDelay(3, 2))
                // 订阅的线程
                .subscribeOn(Schedulers.io())
                // 观察的线程
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mRootView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mRootView.hideLoading();
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<AnimeBean>>(mErrorHandler) {
                    @Override
                    public void onNext(List<AnimeBean> animeBeans) {
                        setHomeAdapter(animeBeans.get(position));
                    }
                });
    }

    private void setHomeAdapter(AnimeBean animeBean) {
//        if (mAdapter == null) {
            mAdapter = new HomeItemAdapter(R.layout.item_home, animeBean.getItems());
            mRootView.setHomeAdapter(mAdapter);
//        } else {
//            Timber.d(animeBean.getItems().get(0).getName_cn());
//            mAdapter.setNewData(animeBean.getItems());
//        }
    }
}
