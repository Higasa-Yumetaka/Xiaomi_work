package class_0422;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.work.R;

public class ExampleFragment2 extends Fragment {

    String TAG = "ExampleFragment";

    public ExampleFragment2() {
        super(R.layout.layout_example_fragment2);
    }

    public void changeBackGroundColor(){
        if (getView() != null){
            getView().setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    int color = -1;

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.i(TAG, "onAttachFragment() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
        Bundle args = getArguments();
        if (args != null) {
            color = args.getInt("color");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
        if (color == 1){
            view.setBackgroundResource(R.color.black);
        } else if (color == 2) {
            view.setBackgroundResource(R.color.white);
        } else if (color == 3) {
            view.setBackgroundResource(R.color.red);
        } else if (color == 4) {
            view.setBackgroundResource(R.color.green);
        } else if (color == 5) {
            view.setBackgroundResource(R.color.blue);
        } else if (color == 6) {
            view.setBackgroundResource(R.color.yellow);
        } else if (color == 7) {
            view.setBackgroundResource(R.color.purple_200);
        } else if (color == 8) {
            view.setBackgroundResource(R.color.orange);
        } else if (color == 9) {
            view.setBackgroundResource(R.color.orange);
        } else if (color == 10) {
            view.setBackgroundResource(R.color.grey);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i(TAG, "onViewStateRestored() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }



}
