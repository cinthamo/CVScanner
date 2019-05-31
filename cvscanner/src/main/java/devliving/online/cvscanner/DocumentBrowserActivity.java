package devliving.online.cvscanner;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DocumentBrowserActivity extends Activity {
    private TextView mNumbersTextView;
    private View mFiltersPanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser_content);

        Button retakeButton = findViewById(R.id.retake);
        mNumbersTextView = findViewById(R.id.numbers);
        mFiltersPanel = findViewById(R.id.filtersPanel);
        ImageButton colorButton = findViewById(R.id.color);
        ImageButton grayscaleButton = findViewById(R.id.grayscale);
        ImageButton blackWhiteButton = findViewById(R.id.blackWhite);
        ImageButton photoButton = findViewById(R.id.photo);
        ImageButton cropButton = findViewById(R.id.crop);
        ImageButton filtersButton = findViewById(R.id.filters);
        ImageButton rotateButton = findViewById(R.id.rotate);
        ImageButton eraseButton = findViewById(R.id.erase);
    }

    public void onDoneClick(View v) {
        finish();
    }
}
