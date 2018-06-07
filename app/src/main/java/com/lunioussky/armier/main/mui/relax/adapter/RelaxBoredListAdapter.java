package com.lunioussky.armier.main.mui.relax.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lunioussky.armier.R;
import com.lunioussky.armier.entity.RelaxBoredListBean;
import com.lunioussky.armier.main.view.GlideApp;
import com.lunioussky.armier.util.DateUtil;

import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/7 14:15
 * Description:
 */
public class RelaxBoredListAdapter extends BaseQuickAdapter<RelaxBoredListBean, BaseViewHolder> {

    public RelaxBoredListAdapter(int layoutResId, @Nullable List<RelaxBoredListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RelaxBoredListBean item) {
        helper.setText(R.id.duanzi_author, item.getComment_author());
        helper.setText(R.id.duanzi_date, DateUtil.getTimestampString(DateUtil.string2Date(item.getComment_date(), "yyyy-MM-dd HH:mm:ss")));
        helper.setText(R.id.duanzi_content, item.getText_content());

        // 加载网络图片
        GlideApp.with(mContext)
                .load(item.getPics().get(0))
                .placeholder(R.mipmap.img_default_meizi)
                .error(R.mipmap.img_default_meizi)
                .fitCenter()
                .into((ImageView) helper.getView(R.id.meizi_item));
    }
}
