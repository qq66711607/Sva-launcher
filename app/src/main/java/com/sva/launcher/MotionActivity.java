package com.sva.launcher;

import android.app.Activity;
import android.os.Bundle;

/** Window transitions are limited to UI screens; video and game surfaces retain their own lifecycle. */
public abstract class MotionActivity extends Activity {
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        overridePendingTransition(state == null && Motion.enabled() ? R.anim.motion_enter : 0, 0);
    }
    @Override public void finish() {
        super.finish();
        overridePendingTransition(0, Motion.enabled() ? R.anim.motion_exit : 0);
    }
    /** 独立 Activity 里的单行输入框同样会吞掉上下键，统一在这里放行。 */
    @Override public boolean dispatchKeyEvent(android.view.KeyEvent event) {
        if (event.getAction() == android.view.KeyEvent.ACTION_DOWN
                && EditTextKeys.handOff(this, event.getKeyCode())) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
    @Override protected void onDestroy() {
        Motion.cancelTree(getWindow().getDecorView()); super.onDestroy();
    }
}
