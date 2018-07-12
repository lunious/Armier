package com.lunioussky.armier.main.mui.index.fragment;

import android.util.Log;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.IndexFragmentBind;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;


/**
 * Created by 11645 on 2018/3/13.
 */

public class IndexFragment extends BaseFragment<IndexFragmentBind> {
    private final ArrayList<String> mList = new ArrayList<String>();
    private IndexFragmentAdapter mAdapter;

    @Override
    public int setContent() {
        return R.layout.fragment_index;
    }

    @Override
    public void initData() {
        requestData();
    }
    public void requestData() {

        OkGo.<String>get(JyApi.indexTab)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final JSONArray array = object.getJSONArray("results");
                        if (mList.size() > 0) {
                            mList.clear();
                        }
                        for (int i = 0; i < array.size(); i++) {
                            final JSONObject jsonObject = array.getJSONObject(i);
                            final String name = jsonObject.getString("tabName");
                            mList.add(name);
                        }


                        Log.d("JASHJDSADSADSA",mList.toString());

                        mAdapter = new IndexFragmentAdapter(mList,getFragmentManager());
                        bindingView.vpIndex.setAdapter(mAdapter);
                        bindingView.tabIndex.setupWithViewPager(bindingView.vpIndex);


                    }

                });


    }

    @Override
    public void initEvent() {
        bindingView.setOnClick(new OnClick());
    }
    public class OnClick {

        public void onClickToUser(View view) {
            ARouter.getInstance().build("/com/UserActivity").navigation();
        }

    }


}
