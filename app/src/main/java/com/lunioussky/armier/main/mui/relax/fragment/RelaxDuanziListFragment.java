package com.lunioussky.armier.main.mui.relax.fragment;

import android.support.v7.widget.LinearLayoutManager;
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
import com.lunioussky.armier.databinding.DuanziListBind;
import com.lunioussky.armier.entity.RelaxDuanziListBean;
import com.lunioussky.armier.main.mui.relax.adapter.RelaxDuanziListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

/**
 * Author: lunious
 * Date: 2018/6/6 23:16
 * Description:
 */
public class RelaxDuanziListFragment extends BaseFragment<DuanziListBind> {

    private RelaxDuanziListAdapter duanziListAdapter;
    private ArrayList<RelaxDuanziListBean> mDataList = new ArrayList<>();
    private int page = 1;

    @Override
    public int setContent() {
        return R.layout.fragment_duanzi_list;
    }


    public static RelaxDuanziListFragment getInstance(String title) {
        RelaxDuanziListFragment sf = new RelaxDuanziListFragment();
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
                Toast.makeText(getContext(), "点我干啥\n", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initAdapter() {
        duanziListAdapter = new RelaxDuanziListAdapter(R.layout.item_duanzi, mDataList);
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

        OkGo.<String>post(JyApi.jandan)
                .params("oxwlxojflwblxbsapi", "jandan.get_duan_comments")
                .params("page", page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final String status = object.getString("status");
                        if ("ok".equals(status)) {
                            final JSONArray comments = object.getJSONArray("comments");
                            if (comments.size() > 0) {
                                for (int i = 0; i < comments.size(); i++) {
                                    RelaxDuanziListBean listBean = new RelaxDuanziListBean();
                                    JSONObject list = comments.getJSONObject(i);
                                    listBean.setComment_author(list.getString("comment_author"));
                                    listBean.setComment_date(list.getString("comment_date"));
                                    listBean.setText_content(list.getString("text_content"));
                                    mDataList.add(listBean);
                                }
                                duanziListAdapter.loadMoreComplete();
                                duanziListAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                });
    }
}
