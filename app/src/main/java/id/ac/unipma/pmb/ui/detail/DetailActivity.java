package id.ac.unipma.pmb.ui.detail;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.News;
import id.ac.unipma.pmb.data.network.model.NewsDetail;
import id.ac.unipma.pmb.ui.base.BaseActivity;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity implements DetailView {

    @Inject
    DetailMvpPresenter<DetailView> presenter;

    @BindView(R.id.shimmer) ShimmerFrameLayout mShimmer;
    @BindView(R.id.title) HtmlTextView txtTitle;
    @BindView(R.id.first) HtmlTextView txtFirst;
    @BindView(R.id.center) HtmlTextView txtCenter;
    @BindView(R.id.related_news) RecyclerView mRecyclerView;
    @BindView(R.id.title_toolbar) TextView mToolbar;
    @BindView(R.id.txt_time) HtmlTextView txtDate;
    @BindView(R.id.txt_writer) HtmlTextView txtWriter;
    @BindView(R.id.image) ImageView mImageView;

    private News news;
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.back)
    void back() {
        finish();
    }

    @OnClick(R.id.txt_source)
    void gotoSource() {
        Intent dial = new Intent(Intent.ACTION_VIEW);
        dial.setData(Uri.parse(news.getLink()));
        startActivity(dial);
    }

    @Override
    protected void setUp() {
        typeface = Typeface.createFromAsset(getAssets(), "fonts/google_sans.ttf");
        mToolbar.setText(getString(R.string.news_detail));
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            news = (News) bundle.getSerializable("news");
            if (news!=null) {
                setUpNews(news);
            }
        }
    }

    @Override
    public void showNewsDetail(NewsDetail news) {
        setupNewsDetail(news);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mShimmer!=null) {
            mShimmer.startShimmer();
            mShimmer.startShimmer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mShimmer!=null) {
            mShimmer.startShimmer();
            mShimmer.startShimmer();
        }
    }

    @Override
    public void stopShimmer() {
        if (mShimmer!=null) {
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);
        }
    }

    private void setupNewsDetail(NewsDetail news) {
        txtTitle.setHtml(news.getTitle());
        txtFirst.setTypeface(typeface);
        txtFirst.setHtml(news.getFirst());
        txtCenter.setTypeface(typeface);
        txtCenter.setHtml(news.getCenter());
        txtDate.setHtml(news.getDate());
        txtWriter.setHtml(news.getWriter());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.newsunipma)
                .priority(Priority.NORMAL);
        Glide.with(this)
                .load(news.getImage())
                .apply(options)
                .into(mImageView);
    }

    private void setUpNews(News news) {
        if (news.getLink()!=null) {
            presenter.getNewsDetail(news.getLink());
        }
    }
}
