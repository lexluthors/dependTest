package com.szkingdom.dialog.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.szkingdom.dialog.DialogManager;
import com.szkingdom.dialog.R;
import com.szkingdom.dialog.callback.OnClickButtonListener;

/**
 * 自定义dialog的model业务处理类
 *
 * @author gfh
 * @date 2018/11/14
 */
public class DialogModel implements View.OnClickListener {

    private Context mContext;
    /**
     * 需要弹出的弹框类型,根据传进来的类型进行加载初始化布局
     */
    private int type;
    /**
     * 顶部的图片
     */
    private Drawable titleImageDrawable;
    /**
     * 主标题内容
     */
    private String mainHeadStr;
    /**
     * 副标题内容
     */
    private String subTitleStr;
    /**
     * 正文内容
     */
    private String messageStr;
    /**
     * 左边按钮文字内容
     */
    private String leftButStr;
    /**
     * 右边按钮文字内容
     */
    private String rightButStr;
    /**
     * 左边按钮点击事件监听
     */
    private OnClickButtonListener mOnClickLeftButtonListener;
    /**
     * 右边按钮点击事件监听
     */
    private OnClickButtonListener mOnClickRightButtonListener;
    /**
     * 关闭按钮点击事件监听
     */
    private OnClickButtonListener mOnClickCloseButtonListener;
    /**
     * 是否隐藏dialog
     */
    private boolean isDismissDialog = true;

    private ScrollView messageScrollRoot;
    private LinearLayout messageCenterRoot;
    private ImageView headImage;
    private TextView messageContent;
    private TextView mLeftButton;
    private TextView mRightButton;
    private ImageView mCloseButton;
    private TextView dialogMainHead;
    private TextView dialogSubhead;
    private int contentResId;
    public DialogModel(Context context) {
        this.mContext = context;
    }

    /**
     * 初始化只有两个文字按钮的控件
     *
     * @param view
     */
    public void initTwoButView(View view) {
        mLeftButton = (TextView) view.findViewById(R.id.dialog_left_button);
        mRightButton = (TextView) view.findViewById(R.id.dialog_right_button);
        mLeftButton.setText(leftButStr);
        mRightButton.setText(rightButStr);

        setOnClickLeftButtonListener(mOnClickLeftButtonListener);
        setOnClickRightButtonListener(mOnClickRightButtonListener);
    }

    public void initTotalCustomContent(View view){
        LinearLayout contentRoot = view.findViewById(R.id.dialog_message_ll_root);
        View content = LayoutInflater.from(mContext).inflate(contentResId,contentRoot,true);
        view.findViewById(R.id.dialog_close_icon).setVisibility(View.GONE);
    }

    /**
     * 初始化关闭按钮
     *
     * @param view
     */
    public void initCloseButView(View view) {
        mCloseButton = (ImageView) view.findViewById(R.id.dialog_close_icon);
        mCloseButton.setVisibility(View.VISIBLE);
        setOnClickCloseButtonListener(mOnClickCloseButtonListener);
    }

    /**
     * 初始化顶部带图片的dialog的图片空间
     *
     * @param view
     */
    public void initTitleImageView(View view) {
        if (titleImageDrawable != null) {
            headImage = (ImageView) view.findViewById(R.id.dialog_main_head_image);
            headImage.setImageDrawable(titleImageDrawable);
        }
    }

    /**
     * 初始化正文内容的View
     *
     * @param view
     */
    public void initContentInfoView(View view) {
        messageScrollRoot = (ScrollView) view.findViewById(R.id.dialog_message_scroll_view);
        messageContent = (TextView) view.findViewById(R.id.dialog_center_message);
        if (messageStr == null) {
            messageScrollRoot.setVisibility(View.GONE);
            messageContent.setText("");
        } else {
            messageScrollRoot.setVisibility(View.VISIBLE);
            messageContent.setText(messageStr);
        }
    }

    /**
     * 初始化正文内容的View：当出现多条正文内容时使用
     *
     * @param view
     */
    /**
     * 初始化内容区域View
     *
     * @param view
     */
    public void initCenterView(View view) {
        messageScrollRoot = (ScrollView) view.findViewById(R.id.dialog_message_scroll_view);
        messageCenterRoot = (LinearLayout) view.findViewById(R.id.dialog_message_ll_root);
    }

    /**
     * 初始化顶部的View
     *
     * @param view
     */
    public void initTitleView(View view) {
        dialogMainHead = (TextView) view.findViewById(R.id.dialog_main_head);
        dialogSubhead = (TextView) view.findViewById(R.id.dialog_subhead_title);
        if (mainHeadStr == null) {
            dialogMainHead.setVisibility(View.GONE);
            dialogMainHead.setText("");
        } else {
            dialogMainHead.setVisibility(View.VISIBLE);
            dialogMainHead.setText(mainHeadStr);
        }
        if (subTitleStr == null) {
            dialogSubhead.setVisibility(View.GONE);
            dialogSubhead.setText("");
        } else {
            dialogSubhead.setVisibility(View.VISIBLE);
            dialogSubhead.setText(subTitleStr);
        }
    }

    /**
     * 获取内容布局容器
     *
     * @return
     */
    public View getMessageCenterRoot() {
        return messageCenterRoot;
    }

    /**
     * 获取内容布局ScrollView容器
     *
     * @return
     */
    public View getScrollRoot() {
        return messageScrollRoot;
    }

    /**
     * 设置正文文字字体颜色
     *
     * @param color
     */
    public void setMessageTextColor(int color) {
        if (messageContent != null) {
            messageContent.setTextColor(color);
        }
    }

    /**
     * 设置副标题文字字体颜色
     *
     * @param color
     */
    public void setSubTitleTextColor(int color) {
        if (dialogSubhead != null) {
            dialogSubhead.setTextColor(color);
        }
    }

    /**
     * 设置主标题文字字体颜色
     *
     * @param color
     */
    public void setMainHeadTextColor(int color) {
        if (dialogMainHead != null) {
            dialogMainHead.setTextColor(color);
        }
    }

    /**
     * 设置底部两个按钮文字字体颜色
     *
     * @param leftTextColor
     * @param rightTextColor
     */
    public void setButtonTextColor(int leftTextColor, int rightTextColor) {
        if (mLeftButton != null) {
            mLeftButton.setTextColor(leftTextColor);
        }
        if (mRightButton != null) {
            mRightButton.setTextColor(rightTextColor);
        }
    }

    /**
     * 设置左边按钮监听
     *
     * @param listener
     */
    public void setOnClickLeftButtonListener(OnClickButtonListener listener) {
        mOnClickLeftButtonListener = listener;
        if (mLeftButton != null) {
            if (mOnClickLeftButtonListener != null) {
                mLeftButton.setVisibility(View.VISIBLE);
                mLeftButton.setOnClickListener(this);
                if (mOnClickRightButtonListener == null) {
                    mLeftButton.setBackgroundResource(R.drawable.one_button_view_bg);
                } else {
                    if (mRightButton != null) {
                        mRightButton.setBackgroundResource(R.drawable.right_button_view_bg);
                    }
                    mLeftButton.setBackgroundResource(R.drawable.left_button_view_bg);
                }
            } else {
                mLeftButton.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置右边按钮监听
     *
     * @param listener
     */
    public void setOnClickRightButtonListener(OnClickButtonListener listener) {
        mOnClickRightButtonListener = listener;
        if (mRightButton != null) {
            if (mOnClickRightButtonListener != null) {
                mRightButton.setVisibility(View.VISIBLE);
                mRightButton.setOnClickListener(this);
                if (mOnClickLeftButtonListener == null) {
                    mRightButton.setBackgroundResource(R.drawable.one_button_view_bg);
                } else {
                    if (mLeftButton != null) {
                        mLeftButton.setBackgroundResource(R.drawable.left_button_view_bg);
                    }
                    mRightButton.setBackgroundResource(R.drawable.right_button_view_bg);
                }
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置变比按钮监听
     *
     * @param listener
     */
    public void setOnClickCloseButtonListener(OnClickButtonListener listener) {
        mOnClickCloseButtonListener = listener;
        if (mCloseButton != null) {
            if (listener != null) {
                mCloseButton.setVisibility(View.VISIBLE);
                mCloseButton.setOnClickListener(this);
            } else {
                mCloseButton.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置是否隐藏dialog
     *
     * @param flag
     */
    public void setOnClickDismissDialog(boolean flag) {
        isDismissDialog = flag;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_left_button) {
            if (mOnClickLeftButtonListener != null) {
                mOnClickLeftButtonListener.onClickButton(view);
            }
        } else if (view.getId() == R.id.dialog_right_button) {
            if (mOnClickRightButtonListener != null) {
                mOnClickRightButtonListener.onClickButton(view);
            }
        } else if (view.getId() == R.id.dialog_close_icon) {
            if (mOnClickCloseButtonListener != null) {
                mOnClickCloseButtonListener.onClickButton(view);
            }
        }
        if (isDismissDialog) {
            DialogManager.getInstance().dismiss();
        }
    }

    /**
     * @param context        上下文
     * @param showType       需要弹出的弹框类型
     * @param mainHeadString 主标题内容
     * @param subheadString  副标题内容
     * @param message        正文内容
     * @param leftButString  左边按钮文字
     * @param rightButString 右边按钮文字
     * @param leftButton     左边按钮点击监听
     * @param rightButton    右边按钮点击监听
     * @param closeButton    关闭按钮点击监听
     */
    public void setDialogInfo(Context context, int showType, String mainHeadString, String subheadString,
                              String message, String leftButString, String rightButString,
                              OnClickButtonListener leftButton, OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        mContext = context;
        type = showType;
        mainHeadStr = mainHeadString;
        subTitleStr = subheadString;
        messageStr = message;
        leftButStr = leftButString;
        rightButStr = rightButString;
        mOnClickLeftButtonListener = leftButton;
        mOnClickRightButtonListener = rightButton;
        mOnClickCloseButtonListener = closeButton;
    }

    /**
     * @param context        上下文
     * @param titleDrawable  顶部图片
     * @param showType       需要弹出的弹框类型
     * @param mainHeadString 主标题内容
     * @param subheadString  副标题内容
     * @param message        正文内容
     * @param leftButString  左边按钮文字
     * @param rightButString 右边按钮文字
     * @param leftButton     左边按钮点击监听
     * @param rightButton    右边按钮点击监听
     * @param closeButton    关闭按钮点击监听
     */
    public void setDialogInfo(Context context, int showType, Drawable titleDrawable, String mainHeadString, String subheadString,
                              String message, String leftButString, String rightButString,
                              OnClickButtonListener leftButton, OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        mContext = context;
        type = showType;
        titleImageDrawable = titleDrawable;
        mainHeadStr = mainHeadString;
        subTitleStr = subheadString;
        messageStr = message;
        leftButStr = leftButString;
        rightButStr = rightButString;
        mOnClickLeftButtonListener = leftButton;
        mOnClickRightButtonListener = rightButton;
        mOnClickCloseButtonListener = closeButton;
    }

    /**
     * @param context        上下文
     * @param showType       需要弹出的弹框类型
     * @param mainHeadString 主标题内容
     * @param subheadString  副标题内容
     * @param leftButString  左边按钮文字
     * @param rightButString 右边按钮文字
     * @param leftButton     左边按钮点击监听
     * @param rightButton    右边按钮点击监听
     * @param closeButton    关闭按钮点击监听
     */
    public void setDialogInfo(Context context, int showType, String mainHeadString, String subheadString,
                              String leftButString, String rightButString,
                              OnClickButtonListener leftButton, OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        mContext = context;
        type = showType;
        mainHeadStr = mainHeadString;
        subTitleStr = subheadString;
        leftButStr = leftButString;
        rightButStr = rightButString;
        mOnClickLeftButtonListener = leftButton;
        mOnClickRightButtonListener = rightButton;
        mOnClickCloseButtonListener = closeButton;
    }


    /**
     * @param context        上下文
     * @param showType       需要弹出的弹框类型
     * @param resId          layout文件
     * @param leftButString  左边按钮文字
     * @param rightButString 右边按钮文字
     * @param leftButton     左边按钮点击监听
     * @param rightButton    右边按钮点击监听
     * @param closeButton    关闭按钮点击监听
     */
    public void setDialogInfo(Context context, int showType, int resId,
                              String leftButString, String rightButString,
                              OnClickButtonListener leftButton, OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        mContext = context;
        type = showType;
        contentResId = resId;
        leftButStr = leftButString;
        rightButStr = rightButString;
        mOnClickLeftButtonListener = leftButton;
        mOnClickRightButtonListener = rightButton;
        mOnClickCloseButtonListener = closeButton;
    }
}
