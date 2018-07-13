package com.lunioussky.armier.main.mui.relax.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.lunioussky.armier.databinding.relaxListFragmentBind;
import com.lunioussky.armier.entity.RelaxListBean;
import com.lunioussky.armier.main.mui.relax.adapter.RelaxListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

/**
 * Author: lunious
 * Date: 2018/7/13 10:26
 * Description:
 */
public class RelaxListFragment extends BaseFragment<relaxListFragmentBind> {

    private String mTitle = null;
    private RelaxListAdapter mAdapter;
    private ArrayList<RelaxListBean> mDataList = new ArrayList<>();
    private int page = 1;

    public static RelaxListFragment getInstance(String title) {
        RelaxListFragment sf = new RelaxListFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    public int setContent() {
        return R.layout.fragment_relax_list;
    }

    @Override
    public void initData() {
        initRecyclerView();
        initAdapter();
    }

    public void initRecyclerView() {
        bindingView.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "暂时别点", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initAdapter() {
        mAdapter = new RelaxListAdapter(R.layout.item_relax, mDataList);
        //设置列表动画
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        bindingView.recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                requestData(false);
            }
        }, bindingView.recyclerView);
    }

    @Override
    public void initEvent() {
        requestData(true);
    }

    public void requestData(Boolean showLoading) {
        if (showLoading) {
            bindingView.statusView.showLoading();
        }
        OkGo.<String>get(JyApi.indexList + mTitle + "&page=" + page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final JSONArray array = object.getJSONArray("results");
                        if (array.size() > 0) {
                            for (int i = 0; i < array.size(); i++) {
                                RelaxListBean bean = new RelaxListBean();
                                JSONObject list = array.getJSONObject(i);
                                bean.setzTitle(list.getString("zTitle"));
                                bean.setzSubtitle(list.getString("zSubtitle"));
                                bean.setsSubImageLink(list.getString("sSubImageLink"));
                                bean.setzDetailLink(list.getString("zDetailLink"));
                                bean.setzType(list.getString("zType"));
                                mDataList.add(bean);

                            }
                            mAdapter.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();

                            bindingView.statusView.showContent();
                        }
                    }
                });
    }
}
