package com.sva.launcher;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 文本控件替换层：所有写进控件的文字先经过 {@link Lang#t(CharSequence)}。
 * 未命中词表时原样显示，因此动态内容（频道名、节目名、时间、地址）不受影响。
 */
class TTextView extends TextView {
    TTextView(Context c) { super(c); }
    TTextView(Context c, AttributeSet a) { super(c, a); }
    TTextView(Context c, AttributeSet a, int style) { super(c, a, style); }
    @Override public void setText(CharSequence text, BufferType type) { super.setText(Lang.t(text), type); }
}

class TButton extends Button {
    TButton(Context c) { super(c); }
    TButton(Context c, AttributeSet a) { super(c, a); }
    TButton(Context c, AttributeSet a, int style) { super(c, a, style); }
    @Override public void setText(CharSequence text, BufferType type) { super.setText(Lang.t(text), type); }
}

class TSwitch extends Switch {
    TSwitch(Context c) { super(c); }
    TSwitch(Context c, AttributeSet a) { super(c, a); }
    TSwitch(Context c, AttributeSet a, int style) { super(c, a, style); }
    @Override public void setText(CharSequence text, BufferType type) { super.setText(Lang.t(text), type); }
}

class TEditText extends EditText {
    TEditText(Context c) { super(c); }
    TEditText(Context c, AttributeSet a) { super(c, a); }
    TEditText(Context c, AttributeSet a, int style) { super(c, a, style); }
    // setHint 在 TextView 里是 final，无法重写，改由调用点走 Lang.t()
}

/** Toast 的替换入口，保持与 Toast.makeText 相同的调用写法。 */
final class TToast {
    private TToast() { }
    static Toast makeText(Context c, CharSequence text, int duration) {
        return Toast.makeText(c, Lang.t(text), duration);
    }
}
