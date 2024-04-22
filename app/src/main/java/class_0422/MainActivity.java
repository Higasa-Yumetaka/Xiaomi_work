package class_0422;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements ExampleFragment.IExampleFragmentAction{

    String TAG = "MainActivity";
    private final ExampleFragment2 fragment2 = new ExampleFragment2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button_add_fragment = findViewById(R.id.button_add_fragment);
        button_add_fragment.setOnClickListener(v -> {
            ExampleFragment fragment = new ExampleFragment();
            Bundle args = new Bundle();
            args.putInt("color", 3);
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_frame_container,fragment)
                    .commit();
        });

        Button button_repalce_fragment = findViewById(R.id.button_replace_fragmrnt);
        button_repalce_fragment.setOnClickListener(v -> {
//            ExampleFragment2 fragment = new ExampleFragment2();
            Bundle args = new Bundle();
            Random random = new Random();
            // 生成 3 到 10 之间的随机数
            int randomValue = random.nextInt(8) + 3; // 生成 [0, 7] 范围内的随机数，再加上 3 即可得到 [3, 10] 范围内的随机数
            args.putInt("color", randomValue);
            fragment2.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame_container,fragment2)
                    .commit();
        });


        Bundle args = new Bundle();
        args.putInt("color", 1);
        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view,fragment)
                .add(R.id.fragment_frame_container,fragment2)
//                .hide(fragment)
                .commit();

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_frame_container,fragment2)
//                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }


    @Override
    public void changeAnotherFragment() {
        Log.i(TAG, "changeAnotherFragment() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
        if (fragment2 != null){
            fragment2.changeBackGroundColor();
        }
    }
}