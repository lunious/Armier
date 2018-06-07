package com.lunioussky.armier.main.mui.relax.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.RelaxDuanziListBean;
import com.lunioussky.armier.util.DateUtil;

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

        helper.setText(R.id.duanzi_author, item.getComment_author());
        helper.setText(R.id.duanzi_date, DateUtil.getTimestampString(DateUtil.string2Date(item.getComment_date(), "yyyy-MM-dd HH:mm:ss")));
        helper.setText(R.id.duanzi_content, item.getText_content());

    }
}
