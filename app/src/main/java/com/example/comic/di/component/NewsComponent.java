package com.example.comic.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.comic.di.module.NewsModule;
import com.example.comic.mvp.contract.NewsContract;

import com.jess.arms.di.scope.FragmentScope;
import com.example.comic.mvp.ui.fragment.NewsFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/23/2019 16:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = NewsModule.class, dependencies = AppComponent.class)
public interface NewsComponent {
    void inject(NewsFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NewsComponent.Builder view(NewsContract.View view);

        NewsComponent.Builder appComponent(AppComponent appComponent);

        NewsComponent build();
    }
}