package com.example.liuchangxu.work_0422;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.liuchangxu.R;

public class ButtomFragment_2 extends Fragment {

    TextView textView;

    public interface IButtomFragmentAction_2 {
        void changeViewPagerFragment(int page);
    }

    private IButtomFragmentAction_2 action = null;

    public ButtomFragment_2() {
        super(R.layout.fragment_buttom_2_0422);
    }

    @Override
    public void onAttach(@NonNull android.content.Context context){
        super.onAttach(context);
        if (context instanceof IButtomFragmentAction_2){
            action = (IButtomFragmentAction_2) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textView_2);
        textView.setOnClickListener(v -> {
            if (action != null) {
                Log.i("ButtomFragment_1", "textView_1 clicked_action");
                action.changeViewPagerFragment(1);
            } else {
                Log.i("ButtomFragment_1", "textView_1 clicked_action is null");
            }
        });
    }

    public void TextSelected(Boolean selected) {
        if (selected) {
            textView.setSelected(true);
        } else {
            textView.setSelected(false);
        }
    }

}
