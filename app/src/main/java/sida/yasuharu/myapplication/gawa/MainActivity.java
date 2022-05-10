package sida.yasuharu.myapplication.gawa;

import androidx.appcompat.app.AppCompatActivity;
import sida.yasuharu.myapplication.gawa.Util.MyUtil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean waitingClose = false;
    String homeUrl = "https://google.co.jp";

    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btSetting)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        webView = findViewById(R.id.wvContent);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(homeUrl);
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

    private void openDialog() {
        final EditText editText = new EditText(this);
        editText.setHint("https://example.co.jp");
        editText.setText(webView.getUrl());
        new AlertDialog.Builder(this)
                .setView(editText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        homeUrl = editText.getText().toString();
                        webView.loadUrl(homeUrl);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}