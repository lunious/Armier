package com.lunioussky.armier.main.mui.home.adapter;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.HomeMeiziListBean;

import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/6 16:05
 * Description:
 */
public class HomeMeiziListAdapter extends BaseQuickAdapter<HomeMeiziListBean, BaseViewHolder> {

    public HomeMeiziListAdapter(int layoutResId, @Nullable List<HomeMeiziListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMeiziListBean item) {


        // 加载网络图片
        Glide.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.meizi_item));
    }
}
