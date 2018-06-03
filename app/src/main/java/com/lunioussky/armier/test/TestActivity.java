package com.lunioussky.armier.test;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunioussky.armier.R;
import com.lunioussky.armier.databinding.TestBind;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

@Route(path = "/com/TestActivity")
public class TestActivity extends AppCompatActivity {

    TestBind bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_test);
        bind.setMyHandler(new MyHandler());

    }


    public class MyHandler {
        public void onClickTo(View view) {
//            ARouter.getInstance().build("/com/TestActivity").navigation();
            Toast.makeText(view.getContext(), "AKBSDJSBADJASDASDA", Toast.LENGTH_LONG).show();
        }
    }

    //测试网络请求后的数据返回样式
    private void testUrl() {
        String host = "http://toutiao-ali.juheapi.com";
        String path = "/toutiao/index";
        String appcode = "af6ca24b0b064607accf65b6ec5914bc";

        OkGo.<String>get(host + path)
                //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
                .headers("Authorization", "APPCODE " + appcode)
                .params("type", "keji")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final JSONObject object = JSON.parseObject(response.body());
                        final JSONObject result = object.getJSONObject("result");
                        final String status = result.getString("stat");
                        final JSONArray data = result.getJSONArray("data");

                        for (int i = 0; i < data.size(); i++) {
                            final JSONObject dataList = data.getJSONObject(i);
                            final String date = dataList.getString("date");
                            final String author_name = dataList.getString("author_name");
                            final String title = dataList.getString("title");


                        }

                    }
                });
    }

}
