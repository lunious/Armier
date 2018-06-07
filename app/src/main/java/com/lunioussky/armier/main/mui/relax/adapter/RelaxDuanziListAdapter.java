package com.lunioussky.armier.main.mui.relax.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.RelaxDuanziListBean;

import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/6 16:05
 * Description:
 */
public class RelaxDuanziListAdapter extends BaseQuickAdapter<RelaxDuanziListBean, BaseViewHolder> {

    public RelaxDuanziListAdapter(int layoutResId, @Nullable List<RelaxDuanziListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RelaxDuanziListBean item) {

        helper.setText(R.id.duanzi_item, item.getText());

    }
}
