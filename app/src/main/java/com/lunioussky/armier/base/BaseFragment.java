package com.lunioussky.armier.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: lunious
 * Date: 2018/7/14 18:19
 * Description:
 */
public abstract class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();
    private Unbinder unbinder;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initData(savedInstanceState);
        initEnvent(savedInstanceState);
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initEnvent(Bundle savedInstanceState);
}
