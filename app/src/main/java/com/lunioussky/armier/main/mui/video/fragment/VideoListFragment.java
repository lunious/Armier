package com.lunioussky.armier.main.mui.video.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.classic.common.MultipleStatusView;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.entity.VideoListBean;
import com.lunioussky.armier.main.mui.video.adapter.VideoListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Author: lunious
 * Date: 2018/6/7 20:58
 * Description:
 */
public class VideoListFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.statusView)
    MultipleStatusView statusView;

    private VideoListAdapter videoListAdapter;
    private ArrayList<VideoListBean> mDataList = new ArrayList<>();
    private String mId = null;
    private int page = 1;

    public static VideoListFragment getInstance(String title, String id) {
        VideoListFragment sf = new VideoListFragment();
        sf.mId = id;
        return sf;
    }


    public void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                NiceVideoPlayer niceVideoPlayer = videoListAdapter.niceVideoPlayer;
                if (niceVideoPlayer == NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer()) {
                    NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
                }
            }
        });


    }

    public void initAdapter() {
        videoListAdapter = new VideoListAdapter(R.layout.item_video, mDataList);
        //设置列表动画
        videoListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        recyclerView.setAdapter(videoListAdapter);

        videoListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                requestData(false);
            }
        }, recyclerView);
    }


    public void requestData(boolean showLoading) {
        if (showLoading) {
            statusView.showLoading();
        }


        OkGo.<String>get(JyApi.iFeng + "?listtype=list" + "&typeid=" + mId + "&page=" + page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONArray array = JSON.parseArray(response.body());
                        final JSONObject object = array.getJSONObject(0);
                        final JSONArray jsonArray = object.getJSONArray("item");
                        for (int i = 0; i < jsonArray.size(); i++) {
                            VideoListBean listBean = new VideoListBean();
                            JSONObject list = jsonArray.getJSONObject(i);
                            listBean.setVideoUrl(list.getString("video_url"));
                            listBean.setImageUrl(list.getString("image"));
                            listBean.setTitle(list.getString("title"));
                            listBean.setLength(list.getLong("video_size"));
                            mDataList.add(listBean);
                        }
                        videoListAdapter.loadMoreComplete();
                        videoListAdapter.notifyDataSetChanged();

                        statusView.showContent();
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_list;
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
