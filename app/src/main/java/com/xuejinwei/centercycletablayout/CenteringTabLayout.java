package com.xuejinwei.centercycletablayout;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xuejinwei on 16/9/9.
 * Email:xuejinwei@outlook.com
 * app:tabMode="scrollable" 该属性必须如此设置，否则内存溢出
 * 选中的tab永远居中显示
 */
public class CenteringTabLayout extends TabLayout {

    public CenteringTabLayout(Context context) {
        this(context, null);
    }

    public CenteringTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CenteringTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View firstTab = ((ViewGroup) getChildAt(0)).getChildAt(0);
        View lastTab = ((ViewGroup) getChildAt(0)).getChildAt(((ViewGroup) getChildAt(0)).getChildCount() - 1);
        ViewCompat.setPaddingRelative(getChildAt(0), (getWidth() / 2) - (firstTab.getWidth() / 2), 0, (getWidth() / 2) - (lastTab.getWidth() / 2), 0);
    }
}
