package com.madreain.morepopup.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.LayoutRes;

import com.madreain.morepopup.util.ScreenUtils;


/**
 * @author madreain
 * @date 2020-03-10.
 * module：点击更多的弹窗，根据点击的位置判断是向上/向下，和点击位置左对齐/右对齐
 * description：
 */
public class MorePopup {

    //PopupWindow对象
    private PopupWindow mPopupWindow;
    //context
    private Context mContext;
    //contentView
    private View mContentView;
    //布局id
    private int mLayoutId;//布局必须是固定高度的，要不然calculatePopWindowPos方法计算的高度不准确
    //mAnchorView
    private View mAnchorView;
    //宽高
    private int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    //点击外部区域是否消失
    private boolean mFocusAndOutsideEnable = true;
    //背景
    private Drawable mDrawable = new ColorDrawable();
    //箭头靠左还是靠右
    boolean isNeedShowLeft;

    /**
     * 初始化
     *
     * @param context
     */
    private MorePopup(Context context) {
        this.mContext = context;
    }

    /**
     * 创建
     *
     * @param context
     * @return
     */
    public static MorePopup create(Context context) {
        return new MorePopup(context);
    }

    /**
     * 设置宽高
     *
     * @param width
     * @param height
     * @return
     */
    public MorePopup setWidthHeight(int width, int height) {
        mWidth = width;
        mHeight = height;
        return this;
    }

    /**
     * 设置点击外部区域是否消失
     *
     * @param focusAndOutsideEnable
     * @return
     */
    public MorePopup setFocusAndOutsideEnable(boolean focusAndOutsideEnable) {
        mFocusAndOutsideEnable = focusAndOutsideEnable;
        return this;
    }

    /**
     * 背景
     *
     * @param drawable
     * @return
     */
    public MorePopup setBackgroundDrawable(Drawable drawable) {
        mDrawable = drawable;
        return this;
    }

    /**
     * 设置显示的view
     *
     * @param layoutId
     * @return
     */
    public MorePopup setContentView(@LayoutRes int layoutId) {
        mLayoutId = layoutId;
        if (mContentView == null) {
            mContentView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
        }
        //设置
        mPopupWindow = new PopupWindow(mContentView,
                mWidth, mHeight, mFocusAndOutsideEnable);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(mDrawable);
        return this;
    }

    int windowPos[];

    /**
     * 设置相关联的点击view
     *
     * @param anchorView
     * @return
     */
    public MorePopup setAnchorView(View anchorView) {
        if (mContentView == null) {
            Toast.makeText(mContext, "请设置需要显示的view", Toast.LENGTH_SHORT).show();
            return this;
        }
        mAnchorView = anchorView;
        // 设置好参数之后再show
        windowPos = calculatePopWindowPos(mAnchorView, mContentView);
//        mPopupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
        return this;
    }


    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是方向就在anchorView的左面和右面对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        final int anchorWidth = anchorView.getWidth();
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        // 测量contentView
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        //View在屏幕上的左上角坐标 判断是否和anchorView还是右对齐
        isNeedShowLeft = (screenWidth - anchorLoc[0] > windowWidth);
        //和点击anchorView左对齐
        if (isNeedShowLeft) {
            windowPos[0] = anchorLoc[0];
        } else {
            windowPos[0] = anchorLoc[0] + anchorWidth - windowWidth;
        }
        //向上展示
        if (isNeedShowUp) {
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        //设置是向上还是向下
        if (mOnViewListener != null) {
            mOnViewListener.showUp(mContentView, isNeedShowUp, isNeedShowLeft, this);
        }
        return windowPos;
    }

    /**
     * 刷新布局 用于动态计算布局
     */
    public MorePopup postInvalidate() {
        // 设置好参数之后再show
        windowPos = calculatePopWindowPos(mAnchorView, mContentView);
        int xOff = 40; // 可以自己调整偏移,这里设置偏移，主要是因为.9.png有阴影，可自行替换
        if (isNeedShowLeft) {
            windowPos[0] -= xOff;
        } else {
            windowPos[0] += xOff;
        }
        mPopupWindow.showAtLocation(mAnchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
        return this;
    }

    /**
     * 获取显示的view，用于获取设置的mContentView里的子view的点击事件
     *
     * @return
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * 消失
     */
    public void dismiss() {
        mPopupWindow.dismiss();
    }

    /**
     * 相关监听
     */
    private OnViewListener mOnViewListener;

    public MorePopup setOnViewListener(OnViewListener listener) {
        this.mOnViewListener = listener;
        return this;
    }

    /**
     * 展示的回调返回
     */
    public interface OnViewListener {
        /**
         * @param view
         * @param isNeedShowUp   向上向下展示
         * @param isNeedShowLeft 向左向右展示
         * @param morePopup      this
         */
        void showUp(View view, boolean isNeedShowUp, boolean isNeedShowLeft, MorePopup morePopup);
    }

}
