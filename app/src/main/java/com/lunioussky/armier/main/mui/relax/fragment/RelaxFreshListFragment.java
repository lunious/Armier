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
import com.lunioussky.armier.databinding.FreshListBind;
import com.lunioussky.armier.entity.RelaxFreshListBean;
import com.lunioussky.armier.main.mui.relax.adapter.RelaxFreshListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import java.util.ArrayList;


/**
 * Author: lunious
 * Date: 2018/6/7 14:32
 * Description:
 */
public class RelaxFreshListFragment extends BaseFragment<FreshListBind> {

    private RelaxFreshListAdapter freshListAdapter;
    private ArrayList<RelaxFreshListBean> mDataList = new ArrayList<>();
    private int page = 1;


    public static RelaxFreshListFragment getInstance(String title) {
        RelaxFreshListFragment sf = new RelaxFreshListFragment();
        return sf;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_fresh_list;
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
        freshListAdapter = new RelaxFreshListAdapter(R.layout.item_fresh, mDataList);
        //设置列表动画
        freshListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        bindingView.recyclerView.setAdapter(freshListAdapter);
        freshListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
        bindingView.statusView.showLoading();
        OkGo.<String>post(JyApi.jandan)
                .params("oxwlxojflwblxbsapi", "get_recent_posts")
                .params("page", page)
                .params("include", "url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields")
                .params("custom_fields", "thumb_c,views")
                .params("dev", "1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final String status = object.getString("status");
                        if ("ok".equals(status)) {
                            final JSONArray posts = object.getJSONArray("posts");
                            if (posts.size() > 0) {
                                for (int i = 0; i < posts.size(); i++) {
                                    final JSONObject jsonObject = posts.getJSONObject(i);
                                    final JSONObject author = jsonObject.getJSONObject("author");
                                    final JSONObject custom_fields = jsonObject.getJSONObject("custom_fields");
                                    final JSONArray thumb_c = custom_fields.getJSONArray("thumb_c");
                                    RelaxFreshListBean listBean = new RelaxFreshListBean();
                                    listBean.setTitle(jsonObject.getString("title"));
                                    listBean.setAuthor(author.getString("nickname"));
                                    listBean.setPics(thumb_c.get(0).toString());
                                    mDataList.add(listBean);
                                }
                                freshListAdapter.loadMoreComplete();
                                freshListAdapter.notifyDataSetChanged();
                                bindingView.statusView.showContent();
                            }

                        }
                    }
                });

    }
}
