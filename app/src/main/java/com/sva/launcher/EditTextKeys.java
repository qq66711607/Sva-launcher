package com.sva.launcher;

import android.app.Activity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/**
 * 单行输入框的上下键放行。
 *
 * 为什么需要：EditText 内部把 DPAD_UP/DOWN 当光标移动处理并消化掉，
 * 于是遥控器一旦把焦点送进输入框，就再也出不来 —— 后面的控件全部够不到。
 * 设置弹窗里那套处理器管不到独立 Activity，所以放到 MotionActivity 里统一拦。
 * 多行输入框保持原样（上下键确实用来换行/移动光标）。
 */
final class EditTextKeys {
    private EditTextKeys() { }

    /** 返回 true 表示这次按键已被消费（焦点已移走）。 */
    static boolean handOff(Activity activity, int keyCode) {
        if (keyCode != KeyEvent.KEYCODE_DPAD_DOWN && keyCode != KeyEvent.KEYCODE_DPAD_UP) return false;
        View focused = activity.getCurrentFocus();
        if (!(focused instanceof EditText)) return false;
        boolean multiline = (((EditText) focused).getInputType() & InputType.TYPE_TEXT_FLAG_MULTI_LINE) != 0;
        if (multiline) return false;
        int direction = keyCode == KeyEvent.KEYCODE_DPAD_DOWN ? View.FOCUS_DOWN : View.FOCUS_UP;
        View next = focused.focusSearch(direction);
        return next != null && next != focused && next.requestFocus();
    }
}
