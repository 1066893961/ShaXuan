package com.example.administrator.shaxuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.shaxuan.ui.AddMemberActivity;
import com.example.administrator.shaxuan.ui.SelectAllMemberActivity;
import com.example.administrator.shaxuan.util.SaveDataToFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.name;
import static android.R.attr.phoneNumber;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mAddBtn;
    private Button mSelectBtn;
    private Button mDelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mAddBtn = (Button) findViewById(R.id.add_btn);
        mSelectBtn = (Button) findViewById(R.id.select_btn);
        mDelBtn = (Button) findViewById(R.id.del_btn);
        mAddBtn.setOnClickListener(this);
        mSelectBtn.setOnClickListener(this);
        mDelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AddMemberActivity.class);
                this.startActivity(intent);
                break;
            case R.id.select_btn:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), SelectAllMemberActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.del_btn:
                List<Map<String, Object>> list = new ArrayList<>();
                HashMap<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("phoneNumber", phoneNumber);
                map.put("totalCount", 10);
                map.put("ysyCount", 0);
                map.put("describe", "test");
                list.add(map);
                System.out.print(list.get(10));
                SaveDataToFile.delDataToSDcard("shaXuan.txt", list);
                break;
        }

    }
}
