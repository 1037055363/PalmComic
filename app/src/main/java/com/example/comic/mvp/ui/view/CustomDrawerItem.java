package com.example.comic.mvp.ui.view;

import com.example.comic.R;
import com.mikepenz.materialdrawer.model.AbstractBadgeableDrawerItem;

public class CustomDrawerItem extends AbstractBadgeableDrawerItem<CustomDrawerItem> {

    @Override
    public int getLayoutRes() {
        return R.layout.item_material_custom;
    }
}
