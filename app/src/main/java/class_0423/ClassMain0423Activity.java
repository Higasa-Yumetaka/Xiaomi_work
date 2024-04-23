package class_0423;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.work.R;

public class ClassMain0423Activity extends AppCompatActivity {

    TextView textView;
    TextView textView3;

    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main0423);

        textView = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,26);
        textView.setText("Hello");
        textView.setTextColor(getColor(R.color.black));
        textView.setTypeface(null, Typeface.BOLD_ITALIC);

        textView3.requestFocus();

        SpannableString spannableString = new SpannableString(getString(R.string.go_to_singleinstance_activity));
        //spannableString.setSpan(new ImageSpan(this, R.drawable.ic_launcher_foreground, ImageSpan.ALIGN_BASELINE), 0, 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getColor(R.color.purple_200)), 6, 20, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView4.setText(spannableString);
    }



}