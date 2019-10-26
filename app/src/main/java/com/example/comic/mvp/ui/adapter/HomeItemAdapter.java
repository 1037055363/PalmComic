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

    public HomeItemAdapter(List<AnimeBean.ItemsBean> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnimeBean.ItemsBean item) {
        if (mAppComponent == null) {
            mAppComponent = ArmsUtils.obtainAppComponentFromContext(mContext);
        }
        // 图片
        if (item.getImages() != null) {
            mAppComponent.imageLoader().loadImage(mContext, ImageConfigImpl.builder().placeholder(R.mipmap.img_on_load).imageView(helper.getView(R.id.iv_img)).url(item.getImages().getLarge()).build());
        } else {
            helper.setImageResource(R.id.iv_img, R.mipmap.img_on_load);
        }
        // 标题
        if (item.getName_cn() == null || item.getName_cn().equals("")) {
            helper.setText(R.id.tv_calendar_name, item.getName());
        } else {
            helper.setText(R.id.tv_calendar_name, item.getName_cn());
        }
        // 评分以及排名
        String rank = "";
        if (item.getRank() != 0 && !" ".equals(item.getRank())) {
            rank = item.getRank() + "";
        } else {
            rank = "???";
        }
        helper.setText(R.id.tv_calendar_outline, "评分: 5.4 / 排名: " + rank);


    }
}