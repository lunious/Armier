package com.lunioussky.armier.main.mui.home.fragment;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.HomeMeiziListBind;
import com.lunioussky.armier.entity.HomeMeiziListBean;
import com.lunioussky.armier.main.mui.home.adapter.HomeMeiziListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;


/**
 * Author: lunious
 * Date: 2018/6/5 15:46
 * Description:
 */
public class HomeMeiziListFragment extends BaseFragment<HomeMeiziListBind> {

    private HomeMeiziListAdapter meiziListAdapter;
    private ArrayList<HomeMeiziListBean> mDataList = new ArrayList<>();
    private int page = 1;

    @Override
    public int setContent() {
        return R.layout.fragment_home_meizi_list;
    }

    public static HomeMeiziListFragment getInstance(String title) {
        HomeMeiziListFragment sf = new HomeMeiziListFragment();
        return sf;
    }

    @Override
    public void initData() {
        initRecyclerView();
        initAdapter();

    }

    public void initRecyclerView() {
        bindingView.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        bindingView.recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                final HomeMeiziListBean data = (HomeMeiziListBean) adapter.getData().get(position);
                final String url = data.getUrl();

                Toast.makeText(getContext(), "点我干啥" + url, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initAdapter() {
        meiziListAdapter = new HomeMeiziListAdapter(R.layout.item_meizi, mDataList);
        //设置列表动画
        meiziListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        bindingView.recyclerView.setAdapter(meiziListAdapter);
        meiziListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                requestData();
            }
        }, bindingView.recyclerView);
    }

    @Override
    public void initEvent() {
        requestData();
    }

    public void requestData() {

        OkGo.<String>get(JyApi.meizi + page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final String error = object.getString("error");
                        if ("false".equals(error)) {
                            final JSONArray results = object.getJSONArray("results");
                            if (results.size() > 0) {
                                for (int i = 0; i < results.size(); i++) {
                                    HomeMeiziListBean listBean = new HomeMeiziListBean();
                                    JSONObject list = results.getJSONObject(i);
                                    listBean.setUrl(list.getString("url"));
                                    mDataList.add(listBean);
                                }
                                meiziListAdapter.loadMoreComplete();
                                meiziListAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
}
