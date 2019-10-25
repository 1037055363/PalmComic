package com.example.comic.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.comic.R;
import com.example.comic.app.data.entity.AnimeBean;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

public class HomeItemAdapter extends BaseQuickAdapter<AnimeBean.ItemsBean, BaseViewHolder> {

    private AppComponent mAppComponent;

    public HomeItemAdapter(int layoutResId, List<AnimeBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnimeBean.ItemsBean item) {
        if (mAppComponent == null) {
            mAppComponent = ArmsUtils.obtainAppComponentFromContext(mContext);
        }
        if (item.getImages() == null) {
            helper.setImageResource(R.id.iv_img, R.mipmap.img_on_load);
        } else {
            mAppComponent.imageLoader().loadImage(mContext, ImageConfigImpl.builder().imageView(helper.getView(R.id.iv_img)).url(item.getImages().getLarge()).build());
        }
        if (item.getName_cn() == null || item.getName_cn().equals("")) {
            helper.setText(R.id.tv_name, item.getName());
        } else {
            helper.setText(R.id.tv_name, item.getName_cn());
        }

    }
}