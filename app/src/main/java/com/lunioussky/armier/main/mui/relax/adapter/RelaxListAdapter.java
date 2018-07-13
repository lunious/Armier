package com.lunioussky.armier.main.mui.relax.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.RelaxListBean;
import com.lunioussky.armier.main.view.GlideApp;

import java.util.List;

/**
 * Author: lunious
 * Date: 2018/7/13 12:23
 * Description:
 */
public class RelaxListAdapter extends BaseQuickAdapter<RelaxListBean,BaseViewHolder> {

    public RelaxListAdapter(int layoutResId, @Nullable List<RelaxListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RelaxListBean item) {
        helper.setText(R.id.tv_title,item.getzTitle());
        helper.setText(R.id.tv_ly,item.getzSubtitle());

        GlideApp.with(mContext)
                .load(item.getsSubImageLink())
                .placeholder(R.mipmap.img_default_meizi)
                .error(R.mipmap.img_default_meizi)
                .fitCenter()
                .into((ImageView) helper.getView(R.id.relax_item));
    }
}
