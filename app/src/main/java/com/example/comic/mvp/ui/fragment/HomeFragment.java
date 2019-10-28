package com.example.comic.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.comic.R;
import com.example.comic.app.Tag.MainTag;
import com.example.comic.app.base.MySupportFragment;
import com.example.comic.di.component.DaggerHomeComponent;
import com.example.comic.mvp.contract.HomeContract;
import com.example.comic.mvp.presenter.HomePresenter;
import com.example.comic.mvp.ui.adapter.CalendarAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/23/2019 17:13
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HomeFragment extends MySupportFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_content)
    ViewPager vp_content;
    @BindView(R.id.tab)
    SlidingTabLayout st_tab;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initToolBar();
        initTabFragment();
    }

    private void initToolBar() {
//        ((AppCompatActivity) _mActivity).setSupportActionBar(toolbar);
//        ((AppCompatActivity) _mActivity).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("首页");
        toolbar.inflateMenu(R.menu.main_home_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MainTag(), "openDrawer");
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.main_search) {
                    showMessage("搜索...");
                }
                return false;
            }
        });
    }

    private void initTabFragment() {
        String[] tabNameArray = {getString(R.string.monday), getString(R.string.tuesday)
                , getString(R.string.wednesday), getString(R.string.thursday)
                , getString(R.string.friday), getString(R.string.saturday)
                , getString(R.string.sunday)};

        CalendarAdapter adapter = new CalendarAdapter(getChildFragmentManager());
        for (int i = 0; i < tabNameArray.length; i++) {
            adapter.addFragment(CalendarFragment.newInstance(i), tabNameArray[i]);
        }
        vp_content.setAdapter(adapter);
        st_tab.setViewPager(vp_content);
        // 设置tab选项卡的默认选项
        st_tab.setCurrentTab(0);
    }

    @Override
    public void setData(@Nullable Object data) {

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

    }
}
