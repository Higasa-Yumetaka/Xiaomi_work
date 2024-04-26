package com.example.work_liuchangxu.work_0425;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_liuchangxu.R;

import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<MyStruct> myBeanList;

    public MyRecycleViewAdapter(List<MyStruct> beans) {

        Log.w("MyRecycleViewAdapter", "MyRecycleViewAdapter: " + beans.size());

        myBeanList = beans;
    }

//    private OnItemClickListener mOnItemClickListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(View view, int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.mOnItemClickListener = onItemClickListener;
//    }

    @Override
    public int getItemViewType(int position) {
        return myBeanList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.w("MyRecycleViewAdapter", "onCreateViewHolder: " + viewType);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == MyStruct.TYPE_TEXT) {
            Log.w("MyRecycleViewAdapter", "onCreateViewHolder: viewType is TYPE_TEXT");
            View view = inflater.inflate(R.layout.item_text_recycle_0425, parent, false);
            return new TextViewHolder(view);
        } else if (viewType == MyStruct.TYPE_IMAGE) {
            Log.w("MyRecycleViewAdapter", "onCreateViewHolder: viewType is TYPE_IMAGE");
            View view = inflater.inflate(R.layout.item_image_recycle_0425, parent, false);
            return new ImageViewHolder(view);
        }
        Log.e("MyRecycleViewAdapter", "onCreateViewHolder: unknown viewType: " + viewType);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.w("MyRecycleViewAdapter", "onBindViewHolder: " + position);
        MyStruct bean = myBeanList.get(position);
        if (holder instanceof TextViewHolder) {
            Log.w("MyRecycleViewAdapter", "onBindViewHolder: holder is TextViewHolder");
            ((TextViewHolder) holder).textView.setText((String)bean.getObject());
//            ((TextViewHolder) holder).textView.setOnClickListener(v -> {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onItemClick(v, holder.getAdapterPosition());
//                }
//            });
        } else if (holder instanceof ImageViewHolder) {
            Log.w("MyRecycleViewAdapter", "onBindViewHolder: holder is ImageViewHolder");
            ((ImageViewHolder) holder).imageView.setImageResource((int)bean.getObject());
//            ((ImageViewHolder) holder).imageView.setOnClickListener(v -> {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onItemClick(v, holder.getAdapterPosition());
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        Log.w("MyRecycleViewAdapter", "getItemCount: " + myBeanList.size());
        return myBeanList.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_textView0425);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_imageView0425);
        }
    }
}
