package com.lunioussky.armier.main.mui.index.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.classic.common.MultipleStatusView;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.entity.IndexListBean;
import com.lunioussky.armier.main.mui.index.adapter.IndexListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Author: lunious
 * Date: 2018/7/13 10:26
 * Description:
 */
public class IndexListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.statusView)
    MultipleStatusView statusView;

    private String mTitle = null;
    private IndexListAdapter mAdapter;
    private ArrayList<IndexListBean> mDataList = new ArrayList<>();
    private int page = 1;

    public static IndexListFragment getInstance(String title) {
        IndexListFragment sf = new IndexListFragment();
        sf.mTitle = title;
        return sf;
    }


    public void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                IndexListBean bean = (IndexListBean) adapter.getData().get(position);
                String url = bean.getzDetailLink();
                ARouter.getInstance().build("/com/IndexDetailActivity").withString("url",url).navigation();

            }
        });
    }

    public void initAdapter() {
        mAdapter = new IndexListAdapter(R.layout.item_relax, mDataList);
        //设置列表动画
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                requestData(false);
            }
        }, recyclerView);
    }


    public void requestData(Boolean showLoading) {
        if (showLoading) {
            statusView.showLoading();
        }
        OkGo.<String>get(JyApi.indexList + mTitle + "&page=" + page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final JSONArray array = object.getJSONArray("results");
                        if (array.size() > 0) {
                            for (int i = 0; i < array.size(); i++) {
                                IndexListBean bean = new IndexListBean();
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

                            statusView.showContent();
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_relax_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initRecyclerView();
        initAdapter();
    }

    @Override
    protected void initEnvent(Bundle savedInstanceState) {
        requestData(true);
    }

}
