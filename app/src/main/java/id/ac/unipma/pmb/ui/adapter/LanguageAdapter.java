package id.ac.unipma.pmb.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.ui.base.BaseViewHolder;

public class LanguageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private String[] values;
    private Context context;
    private LocaleListener mListener;

    public LanguageAdapter(Context mContext,String[] mValues) {
        context = mContext;
        values = mValues;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_language, parent, false));
    }

    @Override
    public int getItemCount() {
        return values.length;
    }

    public void setLocaleListener(LocaleListener listener) {
        mListener = listener;
    }

    public interface LocaleListener {
        void setLocale(String values);
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.language)
        TextView txtLanguage;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void clear() {

        }

        public void onBind(int position) {
            txtLanguage.setText(values[position]);

            itemView.setOnClickListener(v -> {
                txtLanguage.setText(values[position]);
                if (position==0 && mListener!=null) {
                    mListener.setLocale("EN");
                }
                if (position==1) {
                    mListener.setLocale("ID");
                }
            });
        }
    }

}
