package com.madreain.morepopup.popup;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.madreain.morepopup.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author madreain
 * @date 2020-03-10.
 * module：
 * description：
 */
public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.RecyclerHolder> {


    Context mContext;

    private List<Object> dataList = new ArrayList<>();

    public PopupAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setNewData(List<Object> dataList) {
        if (null != dataList) {
            this.dataList.clear();
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.textView.setText(dataList.get(position).toString());
        holder.textView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(holder.textView, dataList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView;

        private RecyclerHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    /**
     * 增加点击监听
     */
    public void setOnItemListener(OnItemClickListener mListener) {
        this.onItemClickListener = mListener;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Object data, int position);
    }

}
