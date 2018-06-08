package com.lunioussky.armier.main.mui.video.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lunioussky.armier.R;
import com.lunioussky.armier.api.JyApi;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.databinding.videoListFragmentBind;
import com.lunioussky.armier.entity.VideoListBean;
import com.lunioussky.armier.main.mui.video.adapter.VideoListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.ArrayList;


/**
 * Author: lunious
 * Date: 2018/6/7 20:58
 * Description:
 */
public class VideoListFragment extends BaseFragment<videoListFragmentBind> {
    private VideoListAdapter videoListAdapter;
    private ArrayList<VideoListBean> mDataList = new ArrayList<>();
    private String mId = null;
    private int page = 1;

    public static VideoListFragment getInstance(String title, String id) {
        VideoListFragment sf = new VideoListFragment();
        sf.mId = id;
        return sf;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_video_list;
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
        bindingView.recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
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
        bindingView.recyclerView.setAdapter(videoListAdapter);

        videoListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
                            listBean.setVideo_url(list.getString("video_url"));
                            listBean.setImage(list.getString("image"));
                            mDataList.add(listBean);
                        }
                        videoListAdapter.loadMoreComplete();
                        videoListAdapter.notifyDataSetChanged();

                        bindingView.statusView.showContent();
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

}
