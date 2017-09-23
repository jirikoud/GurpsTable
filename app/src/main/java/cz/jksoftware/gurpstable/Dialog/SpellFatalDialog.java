package cz.jksoftware.gurpstable.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import cz.jksoftware.gurpstable.Infrastructure.ViewWorkUtils;
import cz.jksoftware.gurpstable.R;

/**
 * Created by Koudy on 9/23/2017.
 */

public class SpellFatalDialog extends DialogFragment {

    protected TextInputEditText mTextEditEnergy;

    protected Button mButtonOk;

    private ResultListener mListener;

    public interface ResultListener {
        void onSpellFatal(int energy);
    }

    protected void initialize(ResultListener listener) {
        mListener = listener;
    }

    public static SpellFatalDialog newInstance(ResultListener listener) {
        SpellFatalDialog dialog = new SpellFatalDialog();
        dialog.initialize(listener);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_spell_fatal, container, false);

        mTextEditEnergy = (TextInputEditText) view.findViewById(R.id.text_edit_energy);

        mButtonOk = (Button) view.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int energy = 0;
                try {
                    energy = Integer.parseInt(mTextEditEnergy.getText().toString());
                }
                catch (Exception exception){

                }
                if (mListener != null) {
                    mListener.onSpellFatal(energy);
                    mListener = null;
                }
                ViewWorkUtils.hideKeyboard(getContext(), v);
                dismiss();
            }
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (getContext() != null) {
            ViewWorkUtils.hideKeyboard(getContext(), getView());
        }
        super.onDismiss(dialog);
    }

}
