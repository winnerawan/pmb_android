package id.ac.unipma.pmb.ui.main.detailinfo;

import butterknife.OnClick;
import id.ac.unipma.pmb.data.network.model.Announcement;
import id.ac.unipma.pmb.data.network.model.ContentInfo;
import id.ac.unipma.pmb.data.network.model.Info;
import id.ac.unipma.pmb.ui.base.BaseActivity;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.unipma.pmb.utils.AppLogger;
import im.delight.android.webview.AdvancedWebView;
import id.ac.unipma.pmb.R;

import javax.inject.Inject;

public class DetailInfoActivity extends BaseActivity implements DetailInfoView, AdvancedWebView.Listener {

    @Inject
    DetailInfoMvpPresenter<DetailInfoView> presenter;

    @BindView(R.id.title_toolbar) TextView txtToolbar;
    @BindView(R.id.htmlview) AdvancedWebView mHtmlView;

    private Info info;
    private Announcement announcement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailinfo);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_left, R.anim.anim_push_left);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.back)
    void back() {
        finishAction();
    }

    @Override
    protected void setUp() {
        mHtmlView.setListener(this, this);
//        mHtmlView.setWebViewClient(new PmbWebViewClient());
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            info = (Info) bundle.getSerializable("info");
            announcement = (Announcement) bundle.getSerializable("announcement");
            setupView();
        }
    }

    private void setupView() {
        if (info!=null) {
            if (info.getTitle()!=null) {
                txtToolbar.setText(info.getTitle());
                decideContent(info.getUrl());
            }
        } else {
            txtToolbar.setText(getString(R.string.announcement));
            decideContent(announcement.getGambar());
        }
    }

    private void decideContent(String link) {
        AppLogger.e("link: "+link);
        switch (link) {
            case "http://pmb.unipma.ac.id/jadwal":
                presenter.getContentSchedule(link);
                break;
            case "http://pmb.unipma.ac.id/biaya":
                presenter.getContentCost(link);
                break;
            case "http://pmb.unipma.ac.id/jalur":
                presenter.getContentTrack(link);
                break;
            case "http://pmb.unipma.ac.id/prodi":
                presenter.getContentStudy(link);
                break;
                default:
                    mHtmlView.loadUrl("https://docs.google.com/gview?embedded=true&url="+link);
                    break;
        }
    }

    @Override
    public void showContentCost(ContentInfo content) {
        mHtmlView.loadHtml(content.getContent());
    }

    @Override
    public void showContentTrack(ContentInfo content) {
        mHtmlView.loadHtml(content.getContent());

    }

    @Override
    public void showContentStudy(ContentInfo content) {
        mHtmlView.loadHtml(content.getContent());
    }

    @Override
    public void showContentSchedule(ContentInfo content) {
        mHtmlView.loadHtml(content.getContent());
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mHtmlView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mHtmlView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mHtmlView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) { }

    @Override
    public void onPageFinished(String url) { }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) { }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) { }


    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_right, R.anim.anim_push_right);
    }

    public void onBackPressed() {
        finishAction();
    }
}
