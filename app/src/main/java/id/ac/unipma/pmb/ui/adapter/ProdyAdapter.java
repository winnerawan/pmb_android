package id.ac.unipma.pmb.ui.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.Faculty;
import id.ac.unipma.pmb.data.network.model.Faculty;
import id.ac.unipma.pmb.data.network.model.Faculty;
import id.ac.unipma.pmb.data.network.model.Program;
import id.ac.unipma.pmb.ui.base.BaseViewHolder;
import id.ac.unipma.pmb.utils.AppLogger;
import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;


public class ProdyAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int UNSELECTED = -1;
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_LOADING = 2;
    private int selectedItem = UNSELECTED;
    public String BASE_URL;
    private Callback mCallback;
    private OnLoadMoreListener onLoadMoreListener;
    private List<Faculty> facultys;
    private RecyclerView recyclerView;

    public ProdyAdapter(RecyclerView recycler, List<Faculty> infoList) {
        this.facultys = infoList;
        this.recyclerView = recycler;
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
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (facultys != null && facultys.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (facultys != null && facultys.size() > 0) {
            return facultys.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Faculty> faculties) {
        facultys.addAll(faculties);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onFacultySelected(Faculty info);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }


    public class ViewHolder extends BaseViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {


        @BindView(R.id.faculty) TextView title;
        @BindView(R.id.expandable_layout) ExpandableLayout mExpandableLayout;
        @BindView(R.id.container_program) LinearLayout mContainerProgram;
        @BindView(R.id.container_) LinearLayout mContainer;
        @BindView(R.id.ic_faculty) ImageView icFaculty;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mExpandableLayout.setInterpolator(new OvershootInterpolator());
            mExpandableLayout.setOnExpansionUpdateListener(this);
            mContainer.setOnClickListener(this);
        }

        protected void clear() {

        }

        @Override
        public void onClick(View v) {
            ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.title.setSelected(false);
                holder.mExpandableLayout.collapse();
            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                title.setSelected(true);
                mExpandableLayout.expand();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition(getAdapterPosition());
            }
        }

        public void onBind(int position) {
            super.onBind(position);
            int pos = getAdapterPosition();
            boolean isSelected = pos == selectedItem;

            title.setSelected(isSelected);
            mExpandableLayout.setExpanded(isSelected, false);
            final Faculty faculty = facultys.get(position);
            
            if (faculty.getName() != null) {
                title.setText(faculty.getName());
                if (faculty.getId()==1) {
                    icFaculty.setImageDrawable(itemView.getResources().getDrawable(R.mipmap.ic_teacher));
                }
                if (faculty.getId()==2) {
                    icFaculty.setImageDrawable(itemView.getResources().getDrawable(R.mipmap.ic_businessman));
                }
                if (faculty.getId()==3) {
                    icFaculty.setImageDrawable(itemView.getResources().getDrawable(R.mipmap.ic_doctor));
                }
                if (faculty.getId()==4) {
                    icFaculty.setImageDrawable(itemView.getResources().getDrawable(R.mipmap.ic_designer));
                }
            }
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/ride_rewrite_neo_sans_medium.ttf");

            if (faculty.getPrograms()!=null) {
               List<Program> programs = faculty.getPrograms();
               for(Program program: programs) {
                   TextView textProgram = new TextView(itemView.getContext());
                   textProgram.setTypeface(typeface);
                   textProgram.setTextSize(16);
                   textProgram.setPadding(0, 32, 16, 32);
                   textProgram.setText(program.getName());

                   mContainerProgram.addView(textProgram);

                   textProgram.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Toast.makeText(itemView.getContext(), program.getName(), Toast.LENGTH_SHORT).show();
                       }
                   });
               }

//                LinearLayout.LayoutParams lpParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//                for (Program p: programs) {
//                   View view = new View(itemView.getContext());
//                    view.setLayoutParams(lpParams);
//
//                   mContainerDivider.addView(view);
//               }
            }


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