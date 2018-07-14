package com.lunioussky.armier.base;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lunioussky.armier.R;

/**
 * Created by 11645 on 2018/3/13.
 */

@Route(path = "/com/MainActivity")
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fragment_container, new MainFragment());
        }

    }


    @Override
    protected void initEvent(Bundle savedInstanceState) {

    }

}
