package com.lunioussky.armier.main.oui.lucky;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.lunioussky.armier.R;
import com.lunioussky.armier.databinding.LuckFragmentBind;
import com.lunioussky.armier.base.BaseFragment;
import com.lunioussky.armier.main.view.LuckSpan;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * Author: lunious
 * Date: 2018/6/3 13:36
 * Description:
 */
public class LuckyFragment extends BaseFragment {

    private LuckFragmentBind bind;
    private ImageView mIvStart;
    private LuckSpan mLuckSpan;
    public boolean mIsClickStart; //默认为false避免还没点击开始转动就会提示

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_lucky, container, false);
        return bind.getRoot();
    }

    @Override
    public void initData() {
        mIvStart = bind.ivStart;
        mLuckSpan = bind.lsLucky;
    }

    @Override
    public void initEvent() {
        bind.setOnClick(new OnClick());
        mLuckSpan.setOnSpanRollListener(new LuckSpan.SpanRollListener() {
            @Override
            public void onSpanRollListener(double speed) {
                if (0 == speed) {
                    //已经停止下来了 提示中奖名并释放按钮
                    mIvStart.setEnabled(true);
                    if (mIsClickStart) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "恭喜你中奖了", Toast.LENGTH_SHORT).show();
                                mIsClickStart = false;
                            }
                        });
                    }
                }
            }
        });
    }

    public class OnClick {

        public void onClickToStart(View view) {
            mIvStart.setEnabled(false);
            mIsClickStart = true;
            //传入的参数由后台返回指定中哪个奖项
            mLuckSpan.luckyStart(0);
            //模拟请求网络
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //网络请求6秒
                    SystemClock.sleep(5000);
                    //逐渐停止转盘
                    mLuckSpan.luckStop();
                }
            }).start();
        }

    }
}
