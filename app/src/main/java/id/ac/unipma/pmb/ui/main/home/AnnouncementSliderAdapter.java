package id.ac.unipma.pmb.ui.main.home;

import id.ac.unipma.pmb.data.network.model.Announcement;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.utils.AppLogger;
import id.ac.unipma.pmb.utils.CommonUtils;

import java.util.List;


public class AnnouncementSliderAdapter extends PagerAdapter {

    private List<Announcement> announcements;
    private LayoutInflater inflater;
    private Context context;
    private Callback callback;
    private Announcement announcement;

    public interface Callback {
        void onAnnouncementSelected(Announcement announcement);
    }

    public void setCallback(Callback call) {
        this.callback = call;
    }

    public AnnouncementSliderAdapter(List<Announcement> announcements, Context context) {
        this.context = context;
        this.announcements=announcements;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return announcements.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View sliderLayout = inflater.inflate(R.layout.item_announcement, view, false);

        assert sliderLayout != null;
//        final CardView cardView = (CardView) sliderLayout.findViewById(R.id.rootcard);
//        cardView.setMinimumWidth(getScreenWidth()-20);
        final ImageView imageView = (ImageView) sliderLayout.findViewById(R.id.image);
        final TextView title = (TextView) sliderLayout.findViewById(R.id.title);
        final TextView date = (TextView) sliderLayout.findViewById(R.id.date);
        final LinearLayout bg = (LinearLayout) sliderLayout.findViewById(R.id.bg);

        Announcement announcement = announcements.get(position);

        if (announcement!=null) {
            if (announcement.getJudul() != null) {
                title.setText(announcement.getJudul());
            }
            if (announcement.getIdBerita()!=null) {
                getScreenWidth();
                if (announcement.getIdBerita()% 2 == 1) {
                    bg.setBackground(sliderLayout.getContext().getResources().getDrawable(R.drawable.gradientannouncement1));
                } else {
                    bg.setBackground(sliderLayout.getContext().getResources().getDrawable(R.drawable.gradientannouncement2));
                }
            }
        }

        if (announcement.getTanggal()!=null) {
            date.setText(CommonUtils.prettyDateFormat(announcement.getTanggal()));
        }

        view.addView(sliderLayout, 0);

        sliderLayout.setOnClickListener(v -> {
            callback.onAnnouncementSelected(announcement);
        });

        return sliderLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public float getPageWidth(int position) {
        return(0.8f);
    }
}
