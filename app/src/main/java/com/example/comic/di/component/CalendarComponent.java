package com.example.comic.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.comic.di.module.CalendarModule;
import com.example.comic.mvp.contract.CalendarContract;

import com.jess.arms.di.scope.FragmentScope;
import com.example.comic.mvp.ui.fragment.CalendarFragment;


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
@Component(modules = CalendarModule.class, dependencies = AppComponent.class)
public interface CalendarComponent {
    void inject(CalendarFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CalendarComponent.Builder view(CalendarContract.View view);

        CalendarComponent.Builder appComponent(AppComponent appComponent);

        CalendarComponent build();
    }
}