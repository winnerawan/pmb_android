package id.ac.unipma.pmb.ui.main.detailinfo;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PmbWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //hide some part
        view.loadUrl("javascript:document.getElementsByClassName('page-banner')[0].style.display = 'none';");
//        view.loadUrl("javascript:document.getElementsByClassName('top-bar')[0].style.display = 'none';");
//        view.loadUrl("javascript:document.getElementsByClassName('navbar-collapse')[0].style.display = 'none';");
        view.loadUrl("javascript:document.getElementsByClassName('footer-widgets')[0].style.display = 'none';");

    }
}

