package com.example.work_liuchangxu.work_0424;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;

import java.util.ArrayList;
import java.util.List;

public class Main0424Activity extends AppCompatActivity {

    private final List<String> List1 = new ArrayList<>();
    private final List<String> List2 = new ArrayList<>();
    private final List<String> List3 = new ArrayList<>();
    private final List<String> List4 = new ArrayList<>();


    int selected = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0424);

        List1.add("忘记账号了，该如何找回？");
        List1.add("忘记密码了，如何重置密码？");
        List1.add("手机号停用了，如何登录或换绑手机号?");
        List1.add("申诉不通过怎么办?");
        List1.add("账号被盗了，怎么办?");
        List1.add("如何退出小米账号?");

        List2.add("如何登录小米账号?");
        List2.add("如何使用第三方账号登录小米账号?");
        List2.add("忘记账号了，该如何找回?");
        List2.add("没有绑定手机号，怎么登录账号?");
        List2.add("账号登录异常的原因?");
        List2.add("如何查看账号下登录的小米设备?");
        List2.add("账号长期不登录，会自动注销吗?");

        List3.add("如何更换安全手机?");
        List3.add("如何更换安全邮箱?");
        List3.add("如何解绑手机号和邮箱?");
        List3.add("如何将手机号换绑到另一账号?");
        List3.add("如何绑定/换绑/解绑第三方账号?");
        List3.add("如何重置密保?");

        List4.add("如何处理修改密码后，提示登录异常?");
        List4.add("忘记密码了，怎么重置密码?");
        List4.add("如何处理账号被绑定他人手机号/邮箱的问题?");
        List4.add("账号被盗了，怎么办?");
        List4.add("账号被他人实名认证了怎么办?");
        List4.add("如何冻结解冻账号?");
        List4.add("为什么账号被自动冻结?");
        List4.add("为什么账号会被封禁?");

        ListView listView = findViewById(R.id.list_view_activity_main0424);

        MyListAdapter adapter = new MyListAdapter(Main0424Activity.this, R.layout.list_item_layout0424, List1);
        listView.setAdapter(adapter);

        View ServerView = findViewById(R.id.service_activity_main0424);
        int numImages = 6; // 定义图片数量
        int[] imageResourceIds = new int[numImages];

        String[] Names = new String[]{"重置密码", "账号申诉", "冻结账号", "解冻账号", "解封账号", "注销账号"};

        for (int i = 0; i < numImages; i++) {
            String imageName = "pic" + (i + 1); // 生成图片资源名称
            imageResourceIds[i] = getResources().getIdentifier(imageName, "drawable", getPackageName());
        }

        for (int i = 0; i < numImages; i++) {
            View PartView = ServerView.findViewById(getResources().getIdentifier("item_" + (i + 1) + "_activity_main0424", "id", getPackageName()));
            int finalI = i;
            PartView.setOnClickListener(v -> {
                Toast.makeText(Main0424Activity.this, Names[finalI], Toast.LENGTH_SHORT).show();
            });
            ImageView imageView = PartView.findViewById(R.id.imageView);
            imageView.setImageResource(imageResourceIds[i]);
            TextView textView = PartView.findViewById(R.id.textView);
            textView.setText(Names[i]);
        }

        int numButtons = 4;
        String[] ButtonStrings = new String[]{"常见问题", "登录", "修改信息", "账号安全"};
        View ButtonGroup = findViewById(R.id.button_group_activity_main0424);
        Button[] buttons = new Button[numImages];
        for (int i = 0; i < numButtons; i++) {
            buttons[i] = ButtonGroup.findViewById(getResources().getIdentifier("button_" + (i + 1), "id", getPackageName()));
            buttons[i].setText(ButtonStrings[i]);
            buttons[i].setSelected(i == 0);
            if (i == 0) {
                buttons[i].setTextColor(0xff4d7dfd);
            } else {
                buttons[i].setTextColor(0xff666666);
            }
            int finalI = i;
            buttons[i].setOnClickListener(v -> {
                selected = finalI;
                for (int j = 0; j < numButtons; j++) {
                    buttons[j].setSelected(false);
                    buttons[j].setTextColor(0xff666666);
                }
                buttons[finalI].setSelected(true);
                buttons[finalI].setTextColor(0xff4d7dfd);
                // 设置List_finalI为adapter参数
                switch (finalI) {
                    case 0:
                        listView.setAdapter(new MyListAdapter(Main0424Activity.this, R.layout.list_item_layout0424, List1));
                        break;
                    case 1:
                        listView.setAdapter(new MyListAdapter(Main0424Activity.this, R.layout.list_item_layout0424, List2));
                        break;
                    case 2:
                        listView.setAdapter(new MyListAdapter(Main0424Activity.this, R.layout.list_item_layout0424, List3));
                        break;
                    case 3:
                        listView.setAdapter(new MyListAdapter(Main0424Activity.this, R.layout.list_item_layout0424, List4));
                        break;
                }
            });
        }



    }


    public static class MyListAdapter extends ArrayAdapter<String> {
        private final int resourceId;

        public MyListAdapter(@NonNull Context context, int resourceId, List<String> objects) {
            super(context, resourceId, objects);
            this.resourceId = resourceId;
        }

        @NonNull
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            String work = getItem(position);
            View view;
            ListItemViewHolder holder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                holder = new ListItemViewHolder();
                holder.Name = view.findViewById(R.id.list_item_name_textview0424);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ListItemViewHolder) view.getTag();
            }
            holder.Name.setText(work);
            return view;
        }

        public static class ListItemViewHolder {
            public TextView Name;
        }

    }

}