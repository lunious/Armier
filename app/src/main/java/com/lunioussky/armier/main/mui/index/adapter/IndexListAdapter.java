package com.lunioussky.armier.main.mui.index.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.IndexListBean;
import com.lunioussky.armier.main.view.GlideApp;

import java.util.List;

/**
 * Author: lunious
 * Date: 2018/7/13 12:23
 * Description:
 */
public class IndexListAdapter extends BaseQuickAdapter<IndexListBean,BaseViewHolder> {

    public IndexListAdapter(int layoutResId, @Nullable List<IndexListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexListBean item) {
        helper.setText(R.id.tv_title,item.getzTitle());
        helper.setText(R.id.tv_ly,item.getzSubtitle());


        String imageUrl = item.getsSubImageLink();
        if (!TextUtils.isEmpty(imageUrl)){
            helper.setVisible(R.id.relax_item,true);
            GlideApp.with(mContext)
                    .load("http://"+item.getsSubImageLink())
                    .placeholder(R.mipmap.img_default_meizi)
                    .error(R.mipmap.img_default_meizi)
                    .fitCenter()
                    .into((ImageView) helper.getView(R.id.relax_item));
        }

    }
}
