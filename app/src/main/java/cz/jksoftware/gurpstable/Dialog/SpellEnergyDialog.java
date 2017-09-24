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

public class SpellEnergyDialog extends DialogFragment {

    protected TextInputEditText mTextEditEnergy;

    protected Button mButtonSuccess;
    protected Button mButtonFailed;

    private ResultListener mListener;

    public interface ResultListener {
        void onSpellSuccess(int energy);
        void onSpellFailed();
    }

    protected void initialize(ResultListener listener) {
        mListener = listener;
    }

    public static SpellEnergyDialog newInstance(ResultListener listener) {
        SpellEnergyDialog dialog = new SpellEnergyDialog();
        dialog.initialize(listener);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_spell_energy, container, false);

        mTextEditEnergy = (TextInputEditText) view.findViewById(R.id.text_edit_energy);

        mButtonSuccess = (Button) view.findViewById(R.id.button_success);
        mButtonSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int energy = 0;
                try {
                    energy = Integer.parseInt(mTextEditEnergy.getText().toString());
                }
                catch (Exception exception){

                }
                if (mListener != null) {
                    mListener.onSpellSuccess(energy);
                    mListener = null;
                }
                ViewWorkUtils.hideKeyboard(getContext(), v);
                dismiss();
            }
        });
        mButtonFailed = (Button) view.findViewById(R.id.button_failed);
        mButtonFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onSpellFailed();
                        mListener = null;
                    }
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
        super.onDismiss(dialog);
    }

}
