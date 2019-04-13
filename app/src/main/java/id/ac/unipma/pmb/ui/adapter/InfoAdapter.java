package id.ac.unipma.pmb.ui.adapter;

import id.ac.unipma.pmb.data.network.model.Info;
import id.ac.unipma.pmb.ui.base.BaseViewHolder;
import id.ac.unipma.pmb.utils.AppLogger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.unipma.pmb.R;

import java.util.List;


public class InfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_LOADING = 2;

    public String BASE_URL;
    private Callback mCallback;
    private OnLoadMoreListener onLoadMoreListener;
    private List<Info> infos;

    public InfoAdapter(List<Info> infoList) {
        this.infos = infoList;
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
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info2, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info2, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (infos != null && infos.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (infos != null && infos.size() > 0) {
            return infos.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Info> infoList) {
        infos.addAll(infoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onInfoSelected(Info info);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }



    public class ViewHolder extends BaseViewHolder {


        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final Info info = infos.get(position);
            
            if (info.getTitle() != null) {
                title.setText(info.getTitle());
            }

            if (info.getId()!=null) {
                if (info.getId()==1) {
                    title.setCompoundDrawablePadding(16);
                    title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_dates, 0,0,0);
                }
                if (info.getId()==2) {
                    title.setCompoundDrawablePadding(16);
                    title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_jalur, 0,0,0);
                }
                if (info.getId()==3) {
                    title.setCompoundDrawablePadding(16);
                    title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_dollar, 0,0,0);
                }
                if (info.getId()==4) {
                    title.setCompoundDrawablePadding(16);
                    title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_toga, 0,0,0);
                }
            }

            itemView.setOnClickListener(v -> {
                if (info.getId() != null) {
                    try {
                        mCallback.onInfoSelected(info);
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