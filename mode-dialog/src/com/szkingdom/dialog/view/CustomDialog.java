package com.szkingdom.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.szkingdom.dialog.DialogManager;
import com.szkingdom.dialog.R;
import com.szkingdom.dialog.callback.OnClickButtonListener;
import com.szkingdom.dialog.model.DialogModel;

/**
 * 自定义dialog
 *
 * @author gfh
 * @date 2018/11/12
 */
public class CustomDialog extends Dialog {

    public Context mContext;
    private int type;
    private DialogModel mModel;
    private DialogManager.IOnDialogDismiss iOnDialogDismiss;
    public CustomDialog(Context context) {
        this(context, R.style.Theme_CustomDialog);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        mModel = new DialogModel(mContext);
    }

    protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_root_layout);
        initView();
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }


    /**
     * 初始化View
     */
    private void initView() {
        //类型
        switch (type) {
            case DialogManager.SYSTEM_WHOLE_DIALOG:
                //系统公告弹框：文字双按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
                View mSystemNoticeView = findViewById(R.id.system_whole_dialog);
                mSystemNoticeView.setVisibility(View.VISIBLE);
                mModel.initTitleView(mSystemNoticeView);
                mModel.initContentInfoView(mSystemNoticeView);
                mModel.initTwoButView(mSystemNoticeView);
                mModel.initCloseButView(mSystemNoticeView);
                //modify by ykw for 点击系统公告以外的区域，不应关闭系统公告 BBDD-1953
                setCanceledOnTouchOutside(false);
                break;
            case DialogManager.COMMEN_HINT_TWO_BUTTON_DIALOG:
                //普通提示弹框：文字双按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
                View mTwoCommonHintView = findViewById(R.id.commen_hint_dialog);
                mTwoCommonHintView.setVisibility(View.VISIBLE);
                mModel.initTitleView(mTwoCommonHintView);
                mModel.initContentInfoView(mTwoCommonHintView);
                mModel.initTwoButView(mTwoCommonHintView);
                mModel.initCloseButView(mTwoCommonHintView);
                break;
            case DialogManager.COMMEN_HINT_ONE_BUTTON_DIALOG:
                //普通提示弹框：文字单按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
                View mOneCommonHintView = findViewById(R.id.commen_hint_dialog);
                mOneCommonHintView.setVisibility(View.VISIBLE);
                mModel.initTitleView(mOneCommonHintView);
                mModel.initContentInfoView(mOneCommonHintView);
                mModel.initTwoButView(mOneCommonHintView);
                mModel.initCloseButView(mOneCommonHintView);
                break;
            case DialogManager.VERSION_UPGRADLE_TWO_BUTTON_DIALOG:
                //版本升级弹框：文字双按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
                View mTwoButVersionUpgradleView = findViewById(R.id.version_upgradle_dialog);
                mTwoButVersionUpgradleView.setVisibility(View.VISIBLE);
                mModel.initTitleView(mTwoButVersionUpgradleView);
                mModel.initTitleImageView(mTwoButVersionUpgradleView);
                mModel.initContentInfoView(mTwoButVersionUpgradleView);
                mModel.initTwoButView(mTwoButVersionUpgradleView);
                mModel.initCloseButView(mTwoButVersionUpgradleView);
                break;
            case DialogManager.VERSION_UPGRADLE_ONE_BUTTON_DIALOG:
                //版本升级弹框：文字单按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
                View mOneButVersionUpgradleView = findViewById(R.id.version_upgradle_dialog);
                mOneButVersionUpgradleView.setVisibility(View.VISIBLE);
                mModel.initTitleView(mOneButVersionUpgradleView);
                mModel.initTitleImageView(mOneButVersionUpgradleView);
                mModel.initContentInfoView(mOneButVersionUpgradleView);
                mModel.initTwoButView(mOneButVersionUpgradleView);
                mModel.initCloseButView(mOneButVersionUpgradleView);
                break;
            case DialogManager.CENTER_CONTENT_CUSTOM_BUTTON_DIALOG:
                //版本升级弹框：文字单按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
                View mContentCustomView = findViewById(R.id.content_custom_dialog);
                mContentCustomView.setVisibility(View.VISIBLE);
                mModel.initTitleView(mContentCustomView);
                mModel.initCenterView(mContentCustomView);
                mModel.initTwoButView(mContentCustomView);
                mModel.initCloseButView(mContentCustomView);
                break;
            case DialogManager.TOTAL_CONTENT_CUSTOM_BUTTON_DIALOG:
                View mTotalCustomView = findViewById(R.id.total_custom_dialog);
                mTotalCustomView.setVisibility(View.VISIBLE);
                mModel.initTwoButView(mTotalCustomView);
                mModel.initTotalCustomContent(mTotalCustomView);
                break;
            case DialogManager.DOWNLOAD_TWO_BUTTON_DIALOG:
                //下载进度条弹框，底部有按钮，内容自定义 zq BBDD-1930
                View mTwoBtnDownloadView = findViewById(R.id.download_two_btn_dialog);
                mTwoBtnDownloadView.setVisibility(View.VISIBLE);
                mModel.initTitleImageView(mTwoBtnDownloadView);
                mModel.initCenterView(mTwoBtnDownloadView);
                mModel.initTwoButView(mTwoBtnDownloadView);
                break;
            case DialogManager.DOWNLOAD_NO_BUTTON_DIALOG:
                //下载进度条弹框，底部无按钮，内容自定义 zq BBDD-1930
                View mOneBtnDownloadView = findViewById(R.id.download_no_btn_dialog);
                mOneBtnDownloadView.setVisibility(View.VISIBLE);
                mModel.initTitleImageView(mOneBtnDownloadView);
                mModel.initCenterView(mOneBtnDownloadView);
                break;
            default:
                break;
        }
    }

    /**
     * 设置是否隐藏dialog
     *
     * @param flag
     */
    public void setOnClickDismissDialog(boolean flag) {
        if (mModel != null) {
            mModel.setOnClickDismissDialog(flag);
        }
    }

    /**
     * 设置正文文字字体颜色
     *
     * @param color
     */
    public void setMessageTextColor(int color) {
        if (mModel != null) {
            mModel.setMessageTextColor(color);
        }
    }

    /**
     * 设置副标题文字字体颜色
     *
     * @param color
     */
    public void setSubTitleTextColor(int color) {
        if (mModel != null) {
            mModel.setSubTitleTextColor(color);
        }
    }

    /**
     * 设置主标题文字字体颜色
     *
     * @param color
     */
    public void setMainHeadTextColor(int color) {
        if (mModel != null) {
            mModel.setMainHeadTextColor(color);
        }
    }

    /**
     * 设置底部两个按钮文字字体颜色
     *
     * @param leftTextColor
     * @param rightTextColor
     */
    public void setButtonTextColor(int leftTextColor, int rightTextColor) {
        if (mModel != null) {
            mModel.setButtonTextColor(leftTextColor, rightTextColor);
        }
    }

    /**
     * 获取内容布局容器
     *
     * @return
     */
    public View getMessageCenterRoot() {
        if (mModel != null) {
            return mModel.getMessageCenterRoot();
        }
        return null;
    }

    /**
     * 获取内容布局ScrollView容器
     *
     * @return
     */
    public View getScrollRoot() {
        if (mModel != null) {
            return mModel.getScrollRoot();
        }
        return null;
    }

    /**
     * 设置左边按钮监听
     *
     * @param listener
     */
    public void setOnClickLeftButtonListener(OnClickButtonListener listener) {
        if (mModel != null) {
            mModel.setOnClickLeftButtonListener(listener);
        }
    }

    /**
     * 设置右边按钮监听
     *
     * @param listener
     */
    public void setOnClickRightButtonListener(OnClickButtonListener listener) {
        if (mModel != null) {
            mModel.setOnClickRightButtonListener(listener);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(iOnDialogDismiss != null){
            iOnDialogDismiss.onBackPress();
        }
    }

    /**
     * 设置关闭按钮监听
     *
     * @param listener
     */
    public void setOnClickCloseButtonListener(OnClickButtonListener listener) {
        if (mModel != null) {
            mModel.setOnClickCloseButtonListener(listener);
        }
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        // 将对话框的大小按屏幕大小的百分比设置
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        //设置宽度
        lp.width = (int) (display.getWidth() * 0.75);
        window.setAttributes(lp);
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
    public CustomDialog(Context context, int showType, String mainHeadString, String subheadString,
                        String message, String leftButString, String rightButString,
                        OnClickButtonListener leftButton, OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        this(context, R.style.Theme_CustomDialog);
        this.type = showType;
        mModel.setDialogInfo(context, showType, mainHeadString, subheadString,
                message, leftButString, rightButString,
                leftButton, rightButton, closeButton);
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
    public CustomDialog(Context context, int showType, Drawable titleDrawable, String mainHeadString, String subheadString,
                        String message, String leftButString, String rightButString,
                        OnClickButtonListener leftButton, OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        this(context, R.style.Theme_CustomDialog);
        this.type = showType;
        mModel.setDialogInfo(context, showType, titleDrawable, mainHeadString, subheadString,
                message, leftButString, rightButString,
                leftButton, rightButton, closeButton);

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
    public CustomDialog(Context context, int showType, String mainHeadString, String subheadString,
                        String leftButString, String rightButString,
                        OnClickButtonListener leftButton, OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        this(context, R.style.Theme_CustomDialog);
        this.type = showType;
        mModel.setDialogInfo(context, showType, mainHeadString, subheadString,
                leftButString, rightButString,
                leftButton, rightButton, closeButton);
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
     * @param iOnDialogDismiss   消失监听
     */
    public CustomDialog(Context context, int showType, int resId,
                        String leftButString, String rightButString,
                        OnClickButtonListener leftButton, OnClickButtonListener rightButton, OnClickButtonListener closeButton, DialogManager.IOnDialogDismiss iOnDialogDismiss) {
        this(context, R.style.Theme_CustomDialog);
        this.type = showType;
        this.iOnDialogDismiss = iOnDialogDismiss;
        mModel.setDialogInfo(context, showType, resId,
                leftButString, rightButString,
                leftButton, rightButton, closeButton);
    }
}
