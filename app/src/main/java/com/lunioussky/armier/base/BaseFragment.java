package com.lunioussky.armier.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zhy.changeskin.SkinManager;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 11645 on 2018/3/15.
 */

public class BaseFragment extends Fragment implements ISupportFragment {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //换肤功能页面注册
        SkinManager.getInstance().register(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //换肤功能注销
        SkinManager.getInstance().unregister(getActivity());
    }

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return null;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return null;
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onEnterAnimationEnd(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onSupportVisible() {

    }

    @Override
    public void onSupportInvisible() {

    }

    @Override
    public boolean isSupportVisible() {
        return false;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return null;
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return null;
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {

    }

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {

    }

    @Override
    public void onNewBundle(Bundle args) {

    }

    @Override
    public void putNewBundle(Bundle newBundle) {

    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }
}
