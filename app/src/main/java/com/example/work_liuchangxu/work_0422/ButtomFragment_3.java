package com.example.work_liuchangxu.work_0422;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.work_liuchangxu.R;

public class ButtomFragment_3 extends Fragment {

    TextView textView;

    public interface IButtomFragmentAction_3 {
        void changeViewPagerFragment(int page);
    }

    private IButtomFragmentAction_3 action = null;

    public ButtomFragment_3() {
        super(R.layout.fragment_buttom_3_0422);
    }

    @Override
    public void onAttach(@NonNull android.content.Context context){
        super.onAttach(context);
        if (context instanceof IButtomFragmentAction_3){
            action = (IButtomFragmentAction_3) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textView_3);
        textView.setOnClickListener(v -> {
            if (action != null) {
                Log.i("ButtomFragment_1", "textView_1 clicked_action");
                action.changeViewPagerFragment(2);
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
