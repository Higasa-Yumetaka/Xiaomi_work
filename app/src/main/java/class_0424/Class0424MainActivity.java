package class_0424;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.work.R;

public class Class0424MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_class0424_main);

        //FrameLayout frameLayout = findViewById(R.id.main_frame_layout);

        FrameLayout layout = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);
        layout.setId(R.id.main_frame_layout);

        TextView textViewA = new TextView(this);
        textViewA.setText("AA");
        textViewA.setBackgroundColor(Color.LTGRAY);
        FrameLayout.LayoutParams textViewParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                600
        );
        textViewParams.gravity = Gravity.BOTTOM;
        textViewA.setLayoutParams(textViewParams);

        TextView textViewB = new TextView(this);
        textViewB.setText("BB");
        textViewB.setBackgroundColor(Color.GRAY);
        textViewB.setGravity(Gravity.END | Gravity.TOP);
        FrameLayout.LayoutParams textViewParamsB = new FrameLayout.LayoutParams(
               400,
                400
        );
        textViewParamsB.gravity = Gravity.BOTTOM | Gravity.END;
        textViewB.setLayoutParams(textViewParamsB);


        layout.addView(textViewA);
        layout.addView(textViewB);
        setContentView(layout);
    }
}