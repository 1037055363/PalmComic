package com.example.comic.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.comic.di.module.GameModule;
import com.example.comic.mvp.contract.GameContract;

import com.jess.arms.di.scope.FragmentScope;
import com.example.comic.mvp.ui.fragment.GameFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/23/2019 17:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = GameModule.class, dependencies = AppComponent.class)
public interface GameComponent {
    void inject(GameFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        GameComponent.Builder view(GameContract.View view);

        GameComponent.Builder appComponent(AppComponent appComponent);

        GameComponent build();
    }
}