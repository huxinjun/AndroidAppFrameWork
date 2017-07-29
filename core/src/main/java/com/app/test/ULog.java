
package com.app.test;

import android.util.Log;

public class ULog {

    public static boolean debug = true;

    static public void out(Object message) {
        if (debug) {
            StackTraceElement ste = new Throwable().getStackTrace()[1];
            Log.i("xinjun", ste.getFileName() + ": Line " + ste.getLineNumber()
                    + "---result------------------------->" + (message==null?"":message.toString()));
        }
    }
}
