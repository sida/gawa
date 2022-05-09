package sida.yasuharu.myapplication.gawa;

import androidx.appcompat.app.AppCompatActivity;
import sida.yasuharu.myapplication.gawa.Util.MyUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean waitingClose = false;

    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.wvContent);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://google.co.jp");
    }

    @Override
    public void onBackPressed() {
        if (webView==null) { return;}
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }

        if (waitingClose) {
            finish();
            return;
        }

        waitingClose = true;
        Toast.makeText(this, "もう一度戻るとアプリを終了します", Toast.LENGTH_LONG).show();
        MyUtil.delay(3000, new MyUtil.delayInterface() {
            @Override
            public void start() {
                waitingClose = false;
            }
        });
    }
}