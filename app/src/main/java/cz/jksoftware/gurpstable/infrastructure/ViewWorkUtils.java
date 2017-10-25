package cz.jksoftware.gurpstable.infrastructure;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Koudy on 9/23/2017.
 */

public class ViewWorkUtils {

    public static void hideKeyboard(Context context, View view){
        if (context == null){
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
