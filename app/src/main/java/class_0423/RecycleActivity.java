package class_0423;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecycleActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        RecyclerView myRecyclerView = findViewById(R.id.recycle_view);

        List<MyBean> beanList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            MyBean bean = new MyBean();
            bean.setName("Game"+(i + 1));
            bean.setIcon(getResources().getIdentifier("logo" + (i % 12 + 1), "drawable", getPackageName()));
            beanList.add(bean);
        }
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(beanList);
        adapter.setOnItemClickListener(onButtonClickListener);
        myRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);

        findViewById(R.id.add_button).setOnClickListener(v -> {
            Log.i("FloatingActionButton", "Game添加");
            Log.i("RecycleActivity", "Game " + Objects.requireNonNull(myRecyclerView.getAdapter()).getItemCount() + " 号  添加");
            MyBean newBean = new MyBean();
            newBean.setName("Game" + (myRecyclerView.getAdapter().getItemCount() + 1));
            newBean.setIcon(getResources().getIdentifier("logo" + (myRecyclerView.getAdapter().getItemCount() % 12 + 1), "drawable", getPackageName()));
            beanList.add(newBean);
            adapter.notifyItemChanged(myRecyclerView.getAdapter().getItemCount() - 1);
        });

    }
//    private final MyRecycleViewAdapter.OnItemClickListener onButtonClickListener = (view, position) -> Log.i("RecycleActivity", "第 " + (position + 1) + " 个原神，启动 ！");
    private final MyRecycleViewAdapter.OnItemClickListener onButtonClickListener = new MyRecycleViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(android.view.View view, int position) {
            if (view.getId() == R.id.item_logo) {
                Log.i("RecycleActivity", "Game " + (position + 1) + " 号  ICON");
            } else if (view.getId() == R.id.item_name_textview) {
                Log.i("RecycleActivity", "Game " + (position + 1) + " 号  Textview");
            } else if (view.getId() == R.id.item_button) {
                Log.i("RecycleActivity", "Game " + (position + 1) + " 号  启动");
            }
        }
    };
}