package com.szkingdom.dialog.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.szkingdom.dialog.R;


/**
 * 可设置最大高度的ScrollView
 *
 * @author gfh
 * @date 2018/11/13
 */
public class ConstraintHeightScrollView extends ScrollView {

    private Context mContext;
    /**
     * 默认100px
     */
    private float mMaxHeight = 100;

    public ConstraintHeightScrollView(Context context) {
        this(context, null);
    }

    public ConstraintHeightScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConstraintHeightScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ConstraintHeightScrollView, 0, defStyleAttr);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int type = array.getIndex(i);
            if (type == R.styleable.ConstraintHeightScrollView_maxHeight) {
                //获得布局中限制的最大高度
                mMaxHeight = array.getDimension(type, -1);
            }
        }
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取ScrollView本身高度
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        //限制高度小于ScrollView高度,设置为限制高度
        if (mMaxHeight <= specSize && mMaxHeight > -1) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Float.valueOf(mMaxHeight).intValue(),
                    MeasureSpec.AT_MOST);
        }
        //重新计算控件高、宽
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
