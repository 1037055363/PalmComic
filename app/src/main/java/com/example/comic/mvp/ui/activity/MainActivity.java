package com.example.comic.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import com.example.comic.app.Tag.MainTag;
import com.example.comic.app.base.MySupportActivity;
import com.example.comic.mvp.ui.fragment.GameFragment;
import com.example.comic.mvp.ui.fragment.HomeFragment;
import com.example.comic.mvp.ui.fragment.NewsFragment;
import com.example.comic.mvp.ui.view.CustomDrawerItem;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.example.comic.di.component.DaggerMainComponent;
import com.example.comic.mvp.contract.MainContract;
import com.example.comic.mvp.presenter.MainPresenter;

import com.example.comic.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;


import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportFragment;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/23/2019 15:57
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends MySupportActivity<MainPresenter> implements MainContract.View {

    Drawer result;
    private SupportFragment[] mFragments = new SupportFragment[3];

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initDrawer(savedInstanceState);
        initFragment();
    }

    private void initDrawer(@Nullable Bundle savedInstanceState) {
        result = new DrawerBuilder()
                .withActivity(this)
                .withDrawerWidthDp(280)
                .withHeader(R.layout.nav_header_main)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new CustomDrawerItem().withName(R.string.nav_home).withIcon(FontAwesome.Icon.faw_home),
                        new CustomDrawerItem().withName(R.string.nav_game).withIcon(FontAwesome.Icon.faw_gamepad),
                        new CustomDrawerItem().withName(R.string.nav_news).withIcon(FontAwesome.Icon.faw_eye),
                        new SectionDrawerItem().withName("联系我们"),
                        new CustomDrawerItem().withName(R.string.nav_about).withIcon(FontAwesome.Icon.faw_bullhorn),
                        new CustomDrawerItem().withName(R.string.nav_setting).withIcon(FontAwesome.Icon.faw_cog)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            if (drawerItem instanceof Nameable) {
                                String name = ((Nameable) drawerItem).getName().getText(MainActivity.this);
                                switch (name) {
                                    case "首页":
                                        showHideFragment(mFragments[0]);
                                        break;
                                    case "游戏":
                                        showHideFragment(mFragments[1]);
                                        break;
                                    case "新闻":
                                        showHideFragment(mFragments[2]);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        return false;
                    }
                })
                .build();
    }

    private void initFragment() {
        SupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = GameFragment.newInstance();
            mFragments[2] = NewsFragment.newInstance();
            loadMultipleRootFragment(R.id.frame_content, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2]
            );
        } else {
            mFragments[0] = firstFragment;
            mFragments[1] = GameFragment.newInstance();
            mFragments[2] = NewsFragment.newInstance();
        }
    }

    @Subscriber(tag = "openDrawer")
    public void openDrawer(MainTag mainTag) {
        result.openDrawer();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //handle the click on the back arrow click
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                if (result != null && result.isDrawerOpen()) {
//                    result.closeDrawer();
//                } else {
//                    super.onBackPressed();
//                }
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
