package class_0423;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work.R;

import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {
    public List<MyBean> myBean;

    public MyRecycleViewAdapter(List<MyBean> beans) {
        myBean = beans;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyBean bean = myBean.get(position);
        if (bean != null) {
            holder.myTextView.setText(bean.getName());
            holder.myImageView.setImageResource(bean.getIcon());
        }

        if(mOnItemClickListener!=null){
            holder.myButton.setOnClickListener(v -> mOnItemClickListener.onItemClick(v,position));
            holder.myImageView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v,position));
            holder.myTextView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v,position));
        }
    }

    @Override
    public int getItemCount() {
        return myBean.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView myImageView;
        TextView myTextView;
        Button myButton;
        MyViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.item_logo);
            myTextView = itemView.findViewById(R.id.item_name_textview);
            myButton = itemView.findViewById(R.id.item_button);
        }
    }
}
