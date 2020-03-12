package com.madreain.morepopup.util;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.madreain.morepopup.popup.MorePopup;
import com.madreain.morepopup.popup.PopupAdapter;
import com.madreain.morepopup.view.CommItemDecoration;
import com.madreain.morepopup.R;
import com.madreain.morepopup.view.MaxHeightRecyclerView;

import java.util.List;


/**
 * @author madreain
 * @date 2020-03-10.
 * module：popop工具类
 * description：
 */
public class PopupUtil {

    private volatile static PopupUtil popupUtil;

    private PopupUtil() {

    }

    public static PopupUtil getInstance() {
        if (popupUtil == null) {
            synchronized (PopupUtil.class) {
                if (popupUtil == null) {
                    popupUtil = new PopupUtil();
                }
            }
        }
        return popupUtil;
    }

    /**
     *
     * @param mContext
     * @param anchorView
     * @param stringList 支持范型的展示 只需要重写toString方法，toString返回的数据用于展示
     * @param onPopupClickListener
     */
    public void listPop(final Context mContext, View anchorView, List<Object> stringList, OnPopupClickListener onPopupClickListener) {
        //弹窗
        MorePopup.create(mContext)
                .setContentView(R.layout.popup_list)
                .setOnViewListener((view, isNeedShowUp, isNeedShowLeft, morePopup) -> {
                    LinearLayout layout = view.findViewById(R.id.layout);
                    //上
                    if (isNeedShowUp) {
                        //左
                        if (isNeedShowLeft) {
                            layout.setBackgroundResource(R.drawable.down);
                            //右
                        } else {
                            layout.setBackgroundResource(R.drawable.down_right);
                        }
                        //下
                    } else {
                        //左
                        if (isNeedShowLeft) {
                            layout.setBackgroundResource(R.drawable.up);
                            //右
                        } else {
                            layout.setBackgroundResource(R.drawable.up_right);
                        }
                    }
                    MaxHeightRecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                    recyclerView.addItemDecoration(new CommItemDecoration(DividerItemDecoration.VERTICAL, mContext.getResources().getColor(R.color.m042F2F39), ScreenUtils.dip2px(mContext, 1),false));
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
                    PopupAdapter popupAdapter = new PopupAdapter(mContext);
                    recyclerView.setAdapter(popupAdapter);
                    popupAdapter.setNewData(stringList);
                    popupAdapter.setOnItemListener((view1, data, position) -> {
                        if (onPopupClickListener != null) {
                            onPopupClickListener.onPopup(data, view, position, morePopup);
                        }
                    });
                })
                .setAnchorView(anchorView)
                .postInvalidate();
    }


    public interface OnPopupClickListener {

        void onPopup(Object data, View view, int position, MorePopup morePopup);

    }


}
