package class_0424;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Class0423Layout2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);

        FrameLayout frameLayout1 = new FrameLayout(this);
        frameLayout1.setBackgroundColor(Color.BLACK);
        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(800, 1200 );
        frameLayout1.setLayoutParams(layoutParams1);

        FrameLayout frameLayout2 = new FrameLayout(this);
        frameLayout2.setBackgroundColor(Color.RED);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(600, 1000 );
        layoutParams2.gravity = Gravity.CENTER;
        frameLayout2.setLayoutParams(layoutParams2);

        FrameLayout frameLayout3 = new FrameLayout(this);
        frameLayout3.setBackgroundColor(Color.GREEN);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 600 );
        layoutParams3.leftMargin = 100;
        layoutParams3.rightMargin = 100;
        layoutParams3.topMargin = 100;
        layoutParams3.bottomMargin = 100;
        frameLayout3.setLayoutParams(layoutParams3);

        FrameLayout frameLayout4 = new FrameLayout(this);
        frameLayout4.setBackgroundColor(Color.WHITE);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(200, 300 );
        layoutParams4.gravity = Gravity.CENTER;
        frameLayout4.setLayoutParams(layoutParams4);

        LinearLayout linearLayout0 = new LinearLayout(this);
        linearLayout0.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1);
        layoutParams0.setMargins(100,0,100,100);
        linearLayout0.setLayoutParams(layoutParams0);

        LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
        linearLayout1.setLayoutParams(layoutParams5);

        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setBackgroundColor(Color.BLACK);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1);
        linearLayout2.setLayoutParams(layoutParams6);

        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setOrientation(LinearLayout.VERTICAL);
        linearLayout3.setBackgroundColor(Color.RED);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1);
        linearLayout3.setLayoutParams(layoutParams7);

        LinearLayout linearLayout4 = new LinearLayout(this);
        linearLayout4.setOrientation(LinearLayout.VERTICAL);
        linearLayout4.setBackgroundColor(Color.GREEN);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1);
        linearLayout4.setLayoutParams(layoutParams8);


        linearLayout1.addView(linearLayout2);
        linearLayout1.addView(linearLayout3);
        linearLayout0.addView(linearLayout1);
        linearLayout0.addView(linearLayout4);

        frameLayout1.addView(frameLayout2);

        frameLayout3.addView(frameLayout4);

        linearLayout.addView(frameLayout1);

        linearLayout.addView(frameLayout3);

        linearLayout.addView(linearLayout0);

        setContentView(linearLayout);

    }
}
