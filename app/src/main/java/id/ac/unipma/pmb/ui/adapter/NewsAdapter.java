package id.ac.unipma.pmb.ui.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.News;
import id.ac.unipma.pmb.ui.base.BaseViewHolder;
import id.ac.unipma.pmb.utils.AppLogger;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_LOADING = 2;

    public String BASE_URL;
    private Callback mCallback;
    private OnLoadMoreListener onLoadMoreListener;
    private List<News> news;

    public NewsAdapter(List<News> newsList) {
        this.news = newsList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        onLoadMoreListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (news != null && news.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (news != null && news.size() > 0) {
            return news.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<News> newsList) {
        news.addAll(newsList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onNewsSelected(News news);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }



    public class ViewHolder extends BaseViewHolder {


        @BindView(R.id.title)
        HtmlTextView title;
        @BindView(R.id.image)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final News newss = news.get(position);
            
            if (newss.getTitle() != null) {
                title.setHtml(newss.getTitle());
            }
            if (newss.getImage()!=null) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    imageView.setClipToOutline(true);
                }
                RequestOptions options = new RequestOptions()
                        .centerCrop()
//                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.newsunipma)
                        .priority(Priority.NORMAL);
                Glide.with(itemView.getContext())
                        .load(newss.getImage())
                        .apply(options)
                        .into(imageView);
            }

            itemView.setOnClickListener(v -> {
                if (newss.getLink() != null) {
                    try {
                        mCallback.onNewsSelected(newss);
                    } catch (Exception e) {
                        AppLogger.d("id error");
                    }
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {


        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

//        @OnClick(R.id.btn_retry)
//        void onRetryClick() {
//            if (mCallback != null)
//                mCallback.onBlogEmptyViewRetryClick();
//        }
    }
}