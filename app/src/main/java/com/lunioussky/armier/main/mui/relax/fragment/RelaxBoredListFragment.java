package com.lunioussky.armier.main.mui.relax.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.BoredListBind;
import com.lunioussky.armier.entity.RelaxBoredListBean;
import com.lunioussky.armier.main.mui.relax.adapter.RelaxBoredListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lunious
 * Date: 2018/6/7 14:14
 * Description:
 */
public class RelaxBoredListFragment extends BaseFragment<BoredListBind> {

    private RelaxBoredListAdapter relaxBoredListAdapter;
    private ArrayList<RelaxBoredListBean> mDataList = new ArrayList<>();
    private int page = 1;


    public static RelaxBoredListFragment getInstance(String title) {
        RelaxBoredListFragment sf = new RelaxBoredListFragment();
        return sf;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_bored_list;
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

            }
        });
    }

    public void initAdapter() {
        relaxBoredListAdapter = new RelaxBoredListAdapter(R.layout.item_bored, mDataList);
        //设置列表动画
        relaxBoredListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        bindingView.recyclerView.setAdapter(relaxBoredListAdapter);
        relaxBoredListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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

        OkGo.<String>post(JyApi.meizi)
                .params("oxwlxojflwblxbsapi", "jandan.get_pic_comments")
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
                                    RelaxBoredListBean listBean = new RelaxBoredListBean();
                                    JSONObject list = comments.getJSONObject(i);
                                    List pics = list.getJSONArray("pics");
                                    listBean.setComment_author(list.getString("comment_author"));
                                    listBean.setComment_date(list.getString("comment_date"));
                                    listBean.setText_content(list.getString("text_content"));
                                    listBean.setPics(pics);
                                    mDataList.add(listBean);
                                }
                                relaxBoredListAdapter.loadMoreComplete();
                                relaxBoredListAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                });

    }

}