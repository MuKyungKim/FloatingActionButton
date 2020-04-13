package com.github.sealstudios.fab.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.sealstudios.fab.FloatingActionButton;
import com.github.fab.sample.R;
import com.github.sealstudios.fab.FloatingActionButtonImageToggle;
import com.github.sealstudios.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private ListView mListView;
    private FloatingActionButtonImageToggle mFab;
    private FloatingActionMenu mFab2;
    private int mPreviousVisibleItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.list);
        mFab = view.findViewById(R.id.fab_image_toggle);
        mFab2 = view.findViewById(R.id.fab_menu_standard);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Locale[] availableLocales = Locale.getAvailableLocales();
        List<String> locales = new ArrayList<>();
        for (Locale locale : availableLocales) {
            locales.add(locale.getDisplayName());
        }


        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mFab, "alpha", 1f,0.5f);
        ObjectAnimator alphaAnimation2 = ObjectAnimator.ofFloat(mFab, "alpha", 0.5f,1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mFab, "scaleX", 1f,2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mFab, "scaleY", 1f,2f);
        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(mFab, "scaleX", 2f,1f);
        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(mFab, "scaleY", 2f,1f);

        AnimatorSet set = new AnimatorSet();

        AnimatorSet set2 = new AnimatorSet();

        View menuIconView = mFab.getToggleImageView();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(menuIconView, "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(menuIconView, "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(menuIconView, "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(menuIconView, "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(500);
        scaleOutY.setDuration(500);

        scaleInX.setDuration(1500);
        scaleInY.setDuration(1500);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
//                mFab.getMenuIconView().setImageResource(fabMenu.isOpened()
//                        ? R.drawable.ic_float_cross : R.drawable.ic_float_cards);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
//        set.play(alphaAnimation).with(scaleInY);
//        set.play(alphaAnimation2).with(scaleInY).after(alphaAnimation);
        set.setInterpolator(new OvershootInterpolator(2));

        set2.play(scaleOutX).with(scaleOutY);
        set2.play(scaleInX).with(scaleInY).after(scaleOutX);
//        set.play(alphaAnimation).with(scaleInY);
//        set.play(alphaAnimation2).with(scaleInY).after(alphaAnimation);
        set2.setInterpolator(new OvershootInterpolator(2));

        mFab.setIconToggleAnimatorSet(set);
        mFab2.setIconToggleAnimatorSet(set2);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HOME","animate");
                mFab.animateToggleImageView();
            }
        });

        mFab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFab2.open(true);
            }
        });

        mListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, locales));

        mFab.hide(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFab.show(true);
                mFab.setShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
                mFab.setHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));
            }
        }, 300);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > mPreviousVisibleItem) {
                    mFab.hide(true);
                } else if (firstVisibleItem < mPreviousVisibleItem) {
                    mFab.show(true);
                }
                mPreviousVisibleItem = firstVisibleItem;
            }
        });
    }
}
