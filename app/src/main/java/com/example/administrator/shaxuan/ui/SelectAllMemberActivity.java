package com.example.administrator.shaxuan.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.shaxuan.R;
import com.example.administrator.shaxuan.adapter.AllMemberAdapter;
import com.example.administrator.shaxuan.util.SaveDataToFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/30.
 */

public class SelectAllMemberActivity extends Activity {

    private RecyclerView mRey;
    private AllMemberAdapter allMemberAdapter;
    private List<Map<String, Object>> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_all_member);

        mRey = (RecyclerView) findViewById(R.id.all_member_rey);

//        if (SaveDataToFile.fileIsExists("shaXuan.txt")) {
        list = SaveDataToFile.getDataFromSDcard("shaXuan.txt");
//        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRey.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRey.setItemAnimator(new DefaultItemAnimator());

        allMemberAdapter = new AllMemberAdapter(getApplicationContext(), list);
        mRey.setAdapter(allMemberAdapter);
        //…Ë÷√∑÷∏Ùœﬂ
//        mRey.addItemDecoration( new DividerGridItemDecoration(this));

        allMemberAdapter.setOnItemClicklistener(new AllMemberAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MemberInfoActivity.class);
                intent.putExtra("position", postion);
                startActivity(intent);
            }
        });
    }
}
