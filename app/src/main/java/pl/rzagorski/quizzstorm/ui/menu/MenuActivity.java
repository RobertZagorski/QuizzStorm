package pl.rzagorski.quizzstorm.ui.menu;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.ui.base.BaseActivity;

public abstract class MenuActivity extends BaseActivity {
    private ToolbarMenu mToolbarMenu;
    private Menu mMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mToolbarMenu = new ToolbarMenu(MenuActivity.this);
    }

    protected void changeToolbarVisibility(int visibility) {
        mToolbarMenu.changeToolbarVisibility(visibility);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle("");
        mToolbarMenu.setTitle(title);
    }

    public abstract int getContentViewId();

    public Menu getMenu() {
        return mMenu;
    }

    public int getMenuLayout() {
        return R.menu.default_menu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenuLayout(), menu);
        mMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    public void showBackArrow() {
        mToolbarMenu.showBackArrow();
    }

    public void hideBackArrow() {
        mToolbarMenu.hideBackArrow();
    }

    public void changeBackArrow(@DrawableRes int drawableRes) {
        mToolbarMenu.setMenuLeftIcon(drawableRes);
    }

    @Override
    public void onBackPressed() {
        View rootView = getWindow().getDecorView().getRootView();
        if (!(rootView instanceof CoordinatorLayout)) {
            finish();
            return;
        }
        ViewGroup.LayoutParams params = rootView.getLayoutParams();
        if (params == null || !(params instanceof CoordinatorLayout.LayoutParams)) {
            finish();
            return;
        }

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) params;
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            if (((BottomSheetBehavior) behavior).getState() == BottomSheetBehavior.STATE_EXPANDED) {
                ((BottomSheetBehavior) behavior).setState(BottomSheetBehavior.STATE_COLLAPSED);
                return;
            }
        }
        finish();
    }
}
