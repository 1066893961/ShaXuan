package com.example.administrator.shaxuan.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.shaxuan.R;
import com.example.administrator.shaxuan.util.DateUtils;
import com.example.administrator.shaxuan.util.Isephone;
import com.example.administrator.shaxuan.util.SaveDataToFile;
import com.example.administrator.shaxuan.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/30.
 */

public class AddMemberActivity extends Activity implements View.OnClickListener {

    private static final int TOTAL_COUNT = 10;
    private EditText mNameEdt;
    private EditText mPhoneNumberEdt;
    private Button mAddMemberBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        initView();
    }

    private void initView() {
        mNameEdt = (EditText) findViewById(R.id.name_edt);
        mPhoneNumberEdt = (EditText) findViewById(R.id.phone_number_edt);
        mAddMemberBtn = (Button) findViewById(R.id.add_member_btn);

        mAddMemberBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_member_btn:
                //验证手机号  姓名
                String name = mNameEdt.getText().toString();
                String phoneNumber = mPhoneNumberEdt.getText().toString();
                checkData(name, phoneNumber);
                break;
        }
    }

    private void checkData(String name, String phoneNumber) {
        if (name.equals("")) {
            ToastUtil.showShort(getApplicationContext(), "请填写会员姓名！");
        } else if (phoneNumber.equals("")) {
            ToastUtil.showShort(getApplicationContext(), "请填写会员手机号！");
        } else if (!Isephone.getInstance().isPhone(phoneNumber)) {
            ToastUtil.showShort(getApplicationContext(), "请填写正确的手机号！");
        } else {
            addData(name, phoneNumber);
        }
    }

    private void addData(String name, String phoneNumber) {

        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("phoneNumber", phoneNumber);
        map.put("totalCount", TOTAL_COUNT);
        map.put("ysyCount", 0);
        map.put("describe", name + "与" + DateUtils.getNowDate() + "成功办理会员卡一张！");

        list.add(map);

        boolean isSucess = SaveDataToFile.saveDataToSDcard("shaXuan.txt", list,null, -1);

        if (isSucess) {
            ToastUtil.showShort(getApplicationContext(), "保存成功");
        } else {
            ToastUtil.showShort(getApplicationContext(), "保存失败");
        }
        finish();

    }
}
