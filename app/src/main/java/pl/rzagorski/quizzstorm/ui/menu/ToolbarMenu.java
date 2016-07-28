package pl.rzagorski.quizzstorm.ui.menu;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import pl.rzagorski.quizzstorm.R;


/**
 * Created by Robert Zagorski on 2015-09-25.
 */
public class ToolbarMenu {
    private Activity mActivity;
    private Toolbar mToolbar;
    private AppCompatTextView mTitle;

    public ToolbarMenu(Activity activity) {
        mActivity = activity;
        //mToolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
        if (mToolbar == null) {
            return;
        }
        mTitle = (AppCompatTextView) mToolbar.findViewById(R.id.title);
        ((AppCompatActivity) mActivity).setSupportActionBar(mToolbar);
        //setTitle(null);
    }

    public void setTitle(CharSequence title) {
        if (mTitle == null) {
            return;
        }
        //((AppCompatActivity) mActivity).getSupportActionBar().setTitle(title);
        String stringTitle = title.toString();
        mTitle.setText(stringTitle.toUpperCase());
    }

    public void showBackArrow() {
        ((AppCompatActivity) mActivity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((AppCompatActivity) mActivity).getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_back);
    }

    public void setMenuLeftIcon(@DrawableRes int drawableRes) {
        ((AppCompatActivity) mActivity).getSupportActionBar().setHomeAsUpIndicator(drawableRes);
        ((AppCompatActivity) mActivity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hideBackArrow() {
        ((AppCompatActivity) mActivity).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void changeToolbarVisibility(int visibility) {
        if (visibility != View.VISIBLE && visibility != View.INVISIBLE && visibility != View.GONE) {
            return;
        }
        if (mToolbar == null) {
            return;
        }
        if (mToolbar.getVisibility() == visibility) {
            return;
        }
        mToolbar.setVisibility(visibility);
    }
}
