package id.ac.unipma.pmb.ui.adapter;

import id.ac.unipma.pmb.data.network.model.Announcement;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.ui.base.BaseViewHolder;
import id.ac.unipma.pmb.utils.AppLogger;
import id.ac.unipma.pmb.utils.CommonUtils;

import java.util.List;


public class AnnouncementAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_LOADING = 2;

    public String BASE_URL;
    private Callback mCallback;
    private OnLoadMoreListener onLoadMoreListener;
    private List<Announcement> announcements;

    public AnnouncementAdapter(List<Announcement> announcementList) {
        this.announcements = announcementList;
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
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_announcement, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_announcement, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (announcements != null && announcements.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (announcements != null && announcements.size() > 0) {
            return announcements.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Announcement> announcementList) {
        announcements.addAll(announcementList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onAnnouncementSelected(Announcement announcement);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }



    public class ViewHolder extends BaseViewHolder {


        @BindView(R.id.title) TextView title;
        @BindView(R.id.bg) LinearLayout bg;
        @BindView(R.id.date) TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final Announcement announcement = announcements.get(position);
            
            if (announcement.getJudul() != null) {
                title.setText(announcement.getJudul());
            }
            if (announcement.getIdBerita()!=null) {
                if (announcement.getIdBerita()% 2 == 1) {
                    bg.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.gradient0));
                } else {
                    bg.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.gradient3));
                }
            }
            if (announcement.getTanggal()!=null) {
                date.setText(CommonUtils.prettyDateFormat(announcement.getTanggal()));
            }

            itemView.setOnClickListener(v -> {
                if (announcement.getIdBerita() != null) {
                    try {
                        mCallback.onAnnouncementSelected(announcement);
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