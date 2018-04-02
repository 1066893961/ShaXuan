package com.example.administrator.shaxuan.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.shaxuan.R;
import com.example.administrator.shaxuan.util.DateUtils;
import com.example.administrator.shaxuan.util.SaveDataToFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/31.
 */

public class MemberInfoActivity extends Activity implements View.OnClickListener {
    private int pos;
    private List<Map<String, Object>> maps = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private TextView mNameTv, mPhoneNumberTv, mTotalTv, mYsyTv, mSurplusTv, mDescribeTv;
    private Button mSubtractBtn;
    private int ysyCount = 0;
    private int total;
    private int sy;//剩余次数
    private String describe;
    private String name;
    private String phoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);

        initView();
        getData();
        setData();
    }

    private void initView() {
        mNameTv = (TextView) findViewById(R.id.name_tv);
        mPhoneNumberTv = (TextView) findViewById(R.id.phone_number_tv);
        mTotalTv = (TextView) findViewById(R.id.total_tv);
        mYsyTv = (TextView) findViewById(R.id.yishiyong_tv);
        mSurplusTv = (TextView) findViewById(R.id.surplus_tv);
        mDescribeTv = (TextView) findViewById(R.id.describe_tv);
        mSubtractBtn = (Button) findViewById(R.id.subtract_btn);
        mSubtractBtn.setOnClickListener(this);
    }

    private void getData() {
        pos = getIntent().getIntExtra("position", 0);
        maps = SaveDataToFile.getDataFromSDcard("shaXuan.txt");

        map = maps.get(pos);
        total = Integer.valueOf(map.get("totalCount").toString());
        ysyCount = Integer.valueOf(map.get("ysyCount").toString());

        sy = total - ysyCount;
        describe = map.get("describe").toString();
        name = map.get("name").toString();
        phoneNumber = map.get("phoneNumber").toString();

    }

    private void setData() {
        mNameTv.setText("姓名：" + name);
        mPhoneNumberTv.setText("手机号：" + phoneNumber);
        mTotalTv.setText("总次数：" + total + "次");
        mYsyTv.setText("已使用：" + ysyCount + "次");
        mSurplusTv.setText("剩余" + sy + "次");
        mDescribeTv.setText(describe);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.subtract_btn:
                ysyCount += 1;
                sy = total - ysyCount;
                describe = describe + "\n" + name + "与" + DateUtils.getNowDate() + "使用一次";
                mSurplusTv.setText("剩余" + sy + "次");
                mYsyTv.setText("已使用：" + ysyCount + "次");
                mDescribeTv.setText(describe);

                if (sy == 0) {
                    mSubtractBtn.setClickable(false);
                    mSubtractBtn.setBackgroundColor(R.color.colorPrimary);
                    mSubtractBtn.setText("不可用");
                }
                List<Map<String, Object>> mapList = new ArrayList<>();
                Map<String, Object> newMap = new HashMap<>();
                newMap.put("name", name);
                newMap.put("phoneNumber", phoneNumber);
                newMap.put("totalCount", total);
                newMap.put("ysyCount", ysyCount);
                newMap.put("describe", describe);
                mapList.add(newMap);

//                maps.remove(pos);
//                maps.add(pos, newMap);
                SaveDataToFile.saveDataToSDcard("shaXuan.txt", mapList, newMap, pos);


                break;
        }
    }


}
