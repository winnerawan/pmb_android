package id.ac.unipma.pmb.ui.adapter;

import id.ac.unipma.pmb.data.network.model.Prestation;
import id.ac.unipma.pmb.ui.base.BaseViewHolder;
import id.ac.unipma.pmb.utils.AppLogger;
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
import id.ac.unipma.pmb.R;

import java.util.List;


public class PrestationAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_LOADING = 2;

    public String BASE_URL;
    private Callback mCallback;
    private OnLoadMoreListener onLoadMoreListener;
    private List<Prestation> prestations;

    public PrestationAdapter(List<Prestation> prestationList) {
        this.prestations = prestationList;
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
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prestation, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prestation, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (prestations != null && prestations.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (prestations != null && prestations.size() > 0) {
            return prestations.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Prestation> prestationList) {
        prestations.addAll(prestationList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onPrestationSelected(Prestation prestation);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }



    public class ViewHolder extends BaseViewHolder {


        @BindView(R.id.title)
        TextView title;
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

            final Prestation prestation = prestations.get(position);
            
            if (prestation.getTitle() != null) {
                title.setText(prestation.getTitle());
            }
            if (prestation.getImage()!=null) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    imageView.setClipToOutline(true);
                }
                Glide.with(itemView.getContext())
                        .load(prestation.getImage()).into(imageView);
            }

            itemView.setOnClickListener(v -> {
                if (prestation.getId() != null) {
                    try {
                        //mCallback.onPrestationSelected(prestation);
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