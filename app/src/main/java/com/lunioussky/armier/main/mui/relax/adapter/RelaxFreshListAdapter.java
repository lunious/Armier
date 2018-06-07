package com.lunioussky.armier.main.mui.relax.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.RelaxFreshListBean;
import com.lunioussky.armier.main.view.GlideApp;

import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/7 14:15
 * Description:
 */
public class RelaxFreshListAdapter extends BaseQuickAdapter<RelaxFreshListBean, BaseViewHolder> {

    public RelaxFreshListAdapter(int layoutResId, @Nullable List<RelaxFreshListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RelaxFreshListBean item) {
        helper.setText(R.id.fresh_title, item.getTitle());
        helper.setText(R.id.fresh_author, item.getAuthor());
        // 加载网络图片
        GlideApp.with(mContext)
                .load(item.getPics())
                .placeholder(R.mipmap.img_default_meizi)
                .error(R.mipmap.img_default_meizi)
                .fitCenter()
                .into((ImageView) helper.getView(R.id.fresh_item));
    }
}
