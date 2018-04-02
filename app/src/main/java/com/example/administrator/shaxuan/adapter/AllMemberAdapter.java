package com.example.administrator.shaxuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.shaxuan.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/30.
 */

public class AllMemberAdapter extends RecyclerView.Adapter<AllMemberAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<Map<String, Object>> datas;
    MyItemClickListener clickListener;

    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }

    public AllMemberAdapter(Context context, List<Map<String, Object>> mapList) {
        this.context = context;
        this.datas = mapList;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClicklistener(MyItemClickListener clicklistener) {
        this.clickListener = clicklistener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.all_member_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView.setText(datas.get(position).get("name") + ":" + datas.get(position).get("phoneNumber"));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(view, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item_member_tv);
        }
    }
}
