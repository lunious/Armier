package com.lunioussky.armier.main.mui.index.detail;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseActivity;

import butterknife.BindView;

/**
 * Author: lunious
 * Date: 2018/7/13 15:04
 * Description:
 */
@Route(path = "/com/IndexDetailActivity")
public class IndexDetailActivity extends BaseActivity {
    @Autowired
    String url;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.wv_index_detail)
    WebView wvIndexDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_index_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wvIndexDetail.loadUrl("http://"+url);
    }


}
