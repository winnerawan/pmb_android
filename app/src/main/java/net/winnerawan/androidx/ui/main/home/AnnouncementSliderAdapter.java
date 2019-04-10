package net.winnerawan.androidx.ui.main.home;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import net.winnerawan.androidx.R;
import net.winnerawan.androidx.data.network.model.Announcement;
import net.winnerawan.androidx.utils.AppLogger;

import java.util.ArrayList;
import java.util.List;


public class AnnouncementSliderAdapter extends PagerAdapter {

    private List<Announcement> announcements;
    private LayoutInflater inflater;
    private Context context;

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
        final LinearLayout bg = (LinearLayout) sliderLayout.findViewById(R.id.bg);

        Announcement announcement = announcements.get(position);

        if (announcement!=null) {
            if (announcement.getTitle() != null) {
                title.setText(announcement.getTitle());
            }
            if (announcement.getId()!=null) {
                getScreenWidth();
                if (announcement.getId()% 2 == 1) {
                    bg.setBackground(sliderLayout.getContext().getResources().getDrawable(R.drawable.gradient0));
                } else {
                    bg.setBackground(sliderLayout.getContext().getResources().getDrawable(R.drawable.gradient1));
                }
            }
        }

        view.addView(sliderLayout, 0);

        sliderLayout.setOnClickListener(v -> {


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
        AppLogger.e("SCREEN WIDTH = " + Resources.getSystem().getDisplayMetrics().densityDpi);
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
