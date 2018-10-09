package com.example.administrator.shaxuan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.shaxuan.ui.AddMemberActivity;
import com.example.administrator.shaxuan.ui.SelectAllMemberActivity;
import com.example.administrator.shaxuan.util.SaveDataToFile;
import com.example.administrator.shaxuan.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mAddBtn;
    private Button mSelectBtn;
    private Button mDelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            this.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

        }
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
//                HashMap<String, Object> map = new HashMap<>();
//                map.put("name", name);
//                map.put("phoneNumber", phoneNumber);
//                map.put("totalCount", 10);
//                map.put("ysyCount", 0);
//                map.put("describe", "test");
//                list.add(map);
                SaveDataToFile.delDataToSDcard("shaXuan.txt", list);
                ToastUtil.showShort(getApplicationContext(), "删除成功");
                break;
            default:
                break;
        }

    }
}
