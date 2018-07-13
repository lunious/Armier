package com.lunioussky.armier.main.mui.index.detail;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseActivity;
import com.lunioussky.armier.databinding.IndexDetailBind;

/**
 * Author: lunious
 * Date: 2018/7/13 15:04
 * Description:
 */
@Route(path = "/com/IndexDetailActivity")
public class IndexDetailActivity extends BaseActivity<IndexDetailBind> {
    @Autowired
    String url;

    @Override
    public int setContent() {
        return R.layout.activity_index_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        bindingView.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.d("abshdsadasdasdsa",url);
        Log.d("UABSUHDSADSADSA","哈哈哈哈");
        bindingView.wvIndexDetail.loadUrl(url);
    }
}
