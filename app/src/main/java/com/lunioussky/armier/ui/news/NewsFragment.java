package com.lunioussky.armier.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;

/**
 * Created by 11645 on 2018/3/13.
 */

public class NewsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }
}
