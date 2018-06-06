package com.lunioussky.armier.main.mui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
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
import com.lunioussky.armier.constant.JyConstant;
import com.lunioussky.armier.databinding.HomeDuanziListBind;
import com.lunioussky.armier.entity.HomeDuanziListBean;
import com.lunioussky.armier.entity.HomeMeiziListBean;
import com.lunioussky.armier.main.mui.home.adapter.HomeDuanziListAdapter;
import com.lunioussky.armier.main.mui.home.adapter.HomeMeiziListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

/**
 * Author: lunious
 * Date: 2018/6/6 23:16
 * Description:
 */
public class HomeDuanziListFragment extends BaseFragment<HomeDuanziListBind> {

    private HomeDuanziListAdapter duanziListAdapter;
    private ArrayList<HomeDuanziListBean> mDataList = new ArrayList<>();
    private int page = 1;

    @Override
    public int setContent() {
        return R.layout.fragment_home_duanzi_list;
    }


    public static HomeDuanziListFragment getInstance(String title) {
        HomeDuanziListFragment sf = new HomeDuanziListFragment();
        return sf;
    }

    @Override
    public void initData() {
        initRecyclerView();
        initAdapter();
    }

    @Override
    public void initEvent() {
        requestData();
    }

    public void initRecyclerView() {
        bindingView.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                final HomeDuanziListBean data = (HomeDuanziListBean) adapter.getData().get(position);
                final String text = data.getText();

                Toast.makeText(getContext(), "点我干啥\n" + text, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initAdapter() {
        duanziListAdapter = new HomeDuanziListAdapter(R.layout.item_duanzi, mDataList);
        //设置列表动画
        duanziListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        bindingView.recyclerView.setAdapter(duanziListAdapter);
        duanziListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                requestData();
            }
        }, bindingView.recyclerView);
    }

    public void requestData() {

        OkGo.<String>post(JyApi.duanzi)
                .params("showapi_appid", JyConstant.appid)
                .params("showapi_sign", JyConstant.sign)
                .params("type", "29")
                .params("page", page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final String showapi_res_code = object.getString("showapi_res_code");
                        if ("0".equals(showapi_res_code)) {
                            final JSONObject body = object.getJSONObject("showapi_res_body");
                            final String ret_code = body.getString("ret_code");
                            if ("0".equals(ret_code)) {
                                final JSONObject pagebean = body.getJSONObject("pagebean");
                                final JSONArray contentlist = pagebean.getJSONArray("contentlist");
                                if (contentlist.size() > 0) {
                                    for (int i = 0; i < contentlist.size(); i++) {
                                        HomeDuanziListBean listBean = new HomeDuanziListBean();
                                        JSONObject list = contentlist.getJSONObject(i);
                                        listBean.setText(list.getString("text"));
                                        mDataList.add(listBean);
                                    }
                                    duanziListAdapter.loadMoreComplete();
                                    duanziListAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                    }
                });
    }
}
