package com.lunioussky.armier.ui.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRed = view.findViewById(R.id.btn_red);
        btnBlue = view.findViewById(R.id.btn_blue);

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
