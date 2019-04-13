package id.ac.unipma.pmb.ui.adapter;

import id.ac.unipma.pmb.utils.AppLogger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.Selection;
import id.ac.unipma.pmb.ui.base.BaseViewHolder;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_LOADING = 2;

    public String BASE_URL;
    private Callback mCallback;
    private OnLoadMoreListener onLoadMoreListener;
    private List<Selection> selections;

    public SearchAdapter(List<Selection> selectionList) {
        this.selections = selectionList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        onLoadMoreListener = listener;
    }

    public void clear() {
        this.selections.clear();
        notifyDataSetChanged();
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
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selection, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_search, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (selections != null && selections.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (selections != null && selections.size() > 0) {
            return selections.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Selection> selectionList) {
        selections.addAll(selectionList);
        notifyDataSetChanged();
    }

    public interface Callback {
//        void onSelectionSelected(Selection selection);

        void onEmptyViewRetryClick();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }


    public class ViewHolder extends BaseViewHolder {


        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.prodi)
        TextView prodi;
        @BindView(R.id.no_reg)
        TextView no_reg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final Selection selection = selections.get(position);

            if (selection.getName() != null) {
                name.setText(selection.getName());
            }
            if (selection.getProdi() != null) {
                prodi.setText(selection.getProdi());
            }
            if (selection.getNoReg() != null) {
                no_reg.setText(selection.getNoReg());
            }


            itemView.setOnClickListener(v -> {
                if (selection.getId() != null) {
                    try {
                        //mCallback.onSelectionSelected(selection);
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

        @OnClick(R.id.retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onEmptyViewRetryClick();
        }
    }
}