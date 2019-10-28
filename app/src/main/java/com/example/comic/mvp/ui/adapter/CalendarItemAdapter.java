package com.example.comic.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.comic.R;
import com.example.comic.app.data.entity.AnimeBean;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

public class CalendarItemAdapter extends BaseQuickAdapter<AnimeBean.ItemsBean, BaseViewHolder> {

    private AppComponent mAppComponent;

    public CalendarItemAdapter(List<AnimeBean.ItemsBean> data) {
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
            // 暂时代替图片
            String url = "http://img0.imgtn.bdimg.com/it/u=546853275,1657072749&fm=15&gp=0.jpg";
            mAppComponent.imageLoader().loadImage(mContext, ImageConfigImpl.builder().placeholder(R.mipmap.img_on_load).imageView(helper.getView(R.id.iv_img)).url(url).build());
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
        String score = "";
        if (item.getRating() != null) {
            score = item.getRating().getScore();
        } else {
            score = "???";
        }
        helper.setText(R.id.tv_calendar_outline, "评分: " + score + " / 排名: " + rank);


    }
}