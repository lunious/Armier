package com.lunioussky.armier.main.mui.home.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.HomeDuanziListBean;

import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/6 16:05
 * Description:
 */
public class HomeDuanziListAdapter extends BaseQuickAdapter<HomeDuanziListBean, BaseViewHolder> {

    public HomeDuanziListAdapter(int layoutResId, @Nullable List<HomeDuanziListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDuanziListBean item) {

        helper.setText(R.id.duanzi_item, item.getText());

    }
}
