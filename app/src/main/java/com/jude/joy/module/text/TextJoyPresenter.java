package com.jude.joy.module.text;

import android.os.Bundle;

import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.joy.model.JoyModel;
import com.jude.joy.model.bean.TextJoy;
import com.jude.joy.model.bean.TextJoyPage;
import com.jude.joy.model.callback.DataCallback;

/**
 * Created by Mr.Jude on 2015/8/20.
 */
public class TextJoyPresenter extends BeamListFragmentPresenter<TextJoyFragment,TextJoy> {
    int page = 0;
    @Override
    protected void onCreate(TextJoyFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        JoyModel.getInstance().getTextJoy(1, new DataCallback<TextJoyPage>() {
            @Override
            public void success(String info, TextJoyPage data) {
                getAdapter().addAll(data.getContentlist());
            }

            @Override
            public void error(String errorInfo) {
                getView().showError();
            }
        });
    }

    @Override
    public void onLoadMore() {
        JoyModel.getInstance().getTextJoy(page, new DataCallback<TextJoyPage>() {
            @Override
            public void success(String info, TextJoyPage data) {
                getAdapter().addAll(data.getContentlist());
                page++;
            }

            @Override
            public void error(String errorInfo) {
                getAdapter().pauseMore();
            }
        });
    }
}
