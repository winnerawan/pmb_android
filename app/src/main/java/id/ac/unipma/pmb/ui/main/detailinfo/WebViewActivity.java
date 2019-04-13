package id.ac.unipma.pmb.ui.main.detailinfo;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import id.ac.unipma.pmb.R;

public class WebViewActivity extends AppCompatActivity {
    public static final String EXTRA_TABLE_HTML = "EXTRA_TABLE_HTML";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);
        String tableHtml = getIntent().getStringExtra(EXTRA_TABLE_HTML);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadData(tableHtml, "text/html", "UTF-8");
    }
}