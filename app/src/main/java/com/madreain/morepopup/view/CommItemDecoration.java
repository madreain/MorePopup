package com.madreain.morepopup.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author madreain
 * @date 2020-03-12. 自定义分割线（方向、颜色、分割线大小、最后一个是否显示分割线）
 * module：
 * description：
 */
public class CommItemDecoration extends RecyclerView.ItemDecoration {
    private static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private int mSpace = 1;
    //最后一个是否显示
    private boolean lastShow;
    private Rect mRect = new Rect(0, 0, 0, 0);
    private Paint mPaint = new Paint();

    private int mOrientation;

    public CommItemDecoration(int orientation, @ColorInt int color, int space, boolean lastShow) {
        mOrientation = orientation;
        this.lastShow=lastShow;
        if (space > 0) {
            mSpace = space;
        }
        mPaint.setColor(color);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount;
        if (lastShow) {
            childCount = parent.getChildCount();
        } else {
            childCount = parent.getChildCount() - 1;
        }
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mSpace;
            mRect.set(left, top, right, bottom);
            c.drawRect(mRect, mPaint);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount;
        if (lastShow) {
            childCount = parent.getChildCount();
        } else {
            childCount = parent.getChildCount() - 1;
        }
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getRight() + params.rightMargin;
            final int right = left + mSpace;
            mRect.set(left, top, right, bottom);
            c.drawRect(mRect, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mSpace);
        } else {
            outRect.set(0, 0, mSpace, 0);
        }
    }

}
