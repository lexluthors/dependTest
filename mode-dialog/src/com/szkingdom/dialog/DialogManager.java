package com.szkingdom.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.szkingdom.dialog.callback.OnClickButtonListener;
import com.szkingdom.dialog.view.CustomDialog;

/**
 * 自定义diaolg的管理类
 *
 * @author gfh
 * @date 2018/11/14
 */
public class DialogManager {

    /**
     * 完整的弹框类型：文字双按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
     */
    public final static int SYSTEM_WHOLE_DIALOG = 0;
    /**
     * 普通提示弹框类型：文字双按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
     */
    public final static int COMMEN_HINT_TWO_BUTTON_DIALOG = 1;
    /**
     * 普通提示弹框类型：文字单按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
     */
    public final static int COMMEN_HINT_ONE_BUTTON_DIALOG = 2;
    /**
     * 版本升级弹框类型：文字双按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
     */
    public final static int VERSION_UPGRADLE_TWO_BUTTON_DIALOG = 3;
    /**
     * 版本升级弹框类型：文字单按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示
     */
    public final static int VERSION_UPGRADLE_ONE_BUTTON_DIALOG = 4;
    /**
     * 内容布局自定义弹框类型：文字双按钮和一个图片关闭弹框按钮，关闭按钮可隐藏或者显示，内容布局在外部填充
     */
    public final static int CENTER_CONTENT_CUSTOM_BUTTON_DIALOG = 5;
    /**
     * 内容布局完全自定义弹框
     */
    public final static int TOTAL_CONTENT_CUSTOM_BUTTON_DIALOG = 6;
    /**
     * 下载进度弹框类型：文字双按钮
     */
    public final static int DOWNLOAD_TWO_BUTTON_DIALOG = 7;
    /**
     * 下载进度弹框类型：无按钮
     */
    public final static int DOWNLOAD_NO_BUTTON_DIALOG = 8;

    private CustomDialog mDialog;
    private static DialogManager mInstance;

    public DialogManager() {
    }

    public static DialogManager getInstance() {
        if (mInstance == null) {
            mInstance = new DialogManager();
        }
        return mInstance;
    }

    /**
     * 显示弹框
     */
    private void show() {
        if (mDialog != null) {
            if (!mDialog.isShowing()) {
                mDialog.show();
            }
        }
    }

    /**
     * 隐藏弹框
     */
    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 显示两个文字按钮信息弹框
     *
     * @param context        上下文
     * @param showType       需要弹出的弹框类型：参数对应：1
     * @param mainHeadString 主标题内容
     * @param message        正文内容
     * @param leftButString  左边按钮文字
     * @param rightButString 右边按钮文字
     * @param leftButton     左边按钮点击监听
     * @param rightButton    右边按钮点击监听
     * @return
     */
    public CustomDialog showTwoButHintDialog(
            Context context, int showType, String mainHeadString,
            String message, String leftButString, String rightButString,
            OnClickButtonListener leftButton,
            OnClickButtonListener rightButton) {
        mDialog = new CustomDialog(context, showType,
                mainHeadString, null, message, leftButString, rightButString,
                leftButton, rightButton, null);
        show();
        return mDialog;
    }

    /**
     * 显示一个文字按钮信息弹框
     *
     * @param context        上下文
     * @param showType       需要弹出的弹框类型：参数对应：2
     * @param mainHeadString 主标题内容
     * @param message        正文内容
     * @param rightButString 右边按钮文字
     * @param rightButton    右边按钮点击监听
     * @return
     */
    public CustomDialog showOneButHintDialog(
            Context context, int showType, String mainHeadString,
            String message, String rightButString, OnClickButtonListener rightButton) {
        mDialog = new CustomDialog(context, showType,
                mainHeadString, null, message, null, rightButString,
                null, rightButton, null);
        show();
        return mDialog;
    }

    /**
     * add by huangkai for BBDD-3129
     * 显示主副标题，一个按钮的信息弹框
     *
     * @param context        上下文
     * @param showType       需要弹出的弹框类型：参数对应：2
     * @param mainHeadString 主标题内容
     * @param subheadString 副标题内容
     * @param message        正文内容
     * @param rightButString 右边按钮文字
     * @param rightButton    右边按钮点击监听
     * @return
     */
    public CustomDialog showTwoTitleOneBtnDialog(
            Context context, int showType, String mainHeadString,String subheadString,
            String message, String rightButString, OnClickButtonListener rightButton) {
        mDialog = new CustomDialog(context, showType,
                mainHeadString, subheadString, message, null, rightButString,
                null, rightButton, null);
        show();
        return mDialog;
    }
    /**
     * 显示完整的信息弹框
     *
     * @param context        上下文
     * @param showType       需要弹出的弹框类型 ：参数对应：0
     * @param mainHeadString 主标题内容
     * @param subheadString  副标题内容
     * @param message        正文内容
     * @param leftButString  左边按钮文字
     * @param rightButString 右边按钮文字
     * @param leftButton     左边按钮点击监听
     * @param rightButton    右边按钮点击监听
     * @param closeButton    关闭按钮点击监听
     * @return
     */
    public CustomDialog showCustomDialog(
            Context context, int showType, String mainHeadString,
            String subheadString, String message, String leftButString,
            String rightButString, OnClickButtonListener leftButton,
            OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        mDialog = new CustomDialog(context, showType,
                mainHeadString, subheadString, message, leftButString, rightButString,
                leftButton, rightButton, closeButton);
        show();
        return mDialog;
    }

    /**
     * 显示有两个文字按钮的版本升级弹框
     *
     * @param context        上下文
     * @param showType       需要弹出的弹框类型：参数对应：3
     * @param titleDrawable  顶部图片
     * @param mainHeadString 主标题内容
     * @param subheadString  副标题内容
     * @param message        正文内容
     * @param leftButString  左边按钮文字
     * @param rightButString 右边按钮文字
     * @param leftButton     左边按钮点击监听
     * @param rightButton    右边按钮点击监听
     * @param closeButton    关闭按钮点击监听
     * @return
     */
    public CustomDialog showTwoButUpgradleDialog(
            Context context, int showType, Drawable titleDrawable, String mainHeadString, String subheadString,
            String message, String leftButString, String rightButString, OnClickButtonListener leftButton,
            OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        mDialog = new CustomDialog(context, showType,
                titleDrawable, mainHeadString, subheadString, message, leftButString,
                rightButString, leftButton, rightButton, closeButton);
        show();
        return mDialog;
    }

    /**
     * 显示只有一个文字按钮的版本升级弹框
     *
     * @param context        上下文
     * @param showType       需要弹出的弹框类型：参数对应：4
     * @param titleDrawable  顶部图片
     * @param mainHeadString 主标题内容
     * @param subheadString  副标题内容
     * @param message        正文内容
     * @param rightButString 右边按钮文字
     * @param rightButton    右边按钮点击监听
     * @param closeButton    关闭按钮点击监听
     */
    public CustomDialog showOneButUpgradleDialog(
            Context context, int showType, Drawable titleDrawable, String mainHeadString,
            String subheadString, String message, String rightButString,
            OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        mDialog = new CustomDialog(context, showType,
                titleDrawable, mainHeadString, subheadString, message, null,
                rightButString, null, rightButton, closeButton);
        show();
        return mDialog;
    }

    /**
     * 显示完整的信息弹框，正文布局在外部填充
     *
     * @param context        上下文
     * @param showType       需要弹出的弹框类型 ：参数对应：0
     * @param mainHeadString 主标题内容
     * @param subheadString  副标题内容
     * @param leftButString  左边按钮文字
     * @param rightButString 右边按钮文字
     * @param leftButton     左边按钮点击监听
     * @param rightButton    右边按钮点击监听
     * @param closeButton    关闭按钮点击监听
     * @return
     */
    public CustomDialog showCustomDialog(
            Context context, int showType, String mainHeadString,
            String subheadString, String leftButString,
            String rightButString, OnClickButtonListener leftButton,
            OnClickButtonListener rightButton, OnClickButtonListener closeButton) {
        mDialog = new CustomDialog(context, showType,
                mainHeadString, subheadString, leftButString, rightButString,
                leftButton, rightButton, closeButton);
        //modify by ykw for 优化首页弹框逻辑，避免崩溃 ITE-442
        if (context != null && !((Activity)context).isFinishing()  && !((Activity)context).isDestroyed()){
            show();
        }
        return mDialog;
    }


    public CustomDialog showTotalCustomDialog(
            Context context, int showType,
            int resId, String rightButString, OnClickButtonListener rightButton,IOnDialogDismiss iOnDialogDismiss) {
        mDialog = new CustomDialog(context, showType,
                resId, null, rightButString,
                null, rightButton, null,iOnDialogDismiss);
        show();
        return mDialog;
    }


    public interface IOnDialogDismiss{
//        public void onDismiss();
        public void onBackPress();
    }
}
