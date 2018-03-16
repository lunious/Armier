package com.lunioussky.armier.ui.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lunioussky.armier.R;
import com.lunioussky.armier.base.BaseFragment;
import com.zhy.changeskin.SkinManager;

/**
 * Created by 11645 on 2018/3/13.
 */

public class VideoFragment extends BaseFragment {


    private Button btnRed = null;
    private Button btnBlue = null;


    @Override
    public Object setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {
        btnRed = getView().findViewById(R.id.btn_red);
        btnBlue = getView().findViewById(R.id.btn_blue);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("red");
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("blue");
            }
        });
    }

}
