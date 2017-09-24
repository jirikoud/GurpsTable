package cz.jksoftware.gurpstable.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import cz.jksoftware.gurpstable.Infrastructure.MagicPassageEffectHelper;
import cz.jksoftware.gurpstable.R;

/**
 * Created by Koudy on 9/24/2017.
 */

public class SpellResultActivity extends AppCompatActivity {

    public static final String EXTRA_LIMIT = "Limit";
    public static final String EXTRA_SPELL_STATE = "SpellState";
    public static final String EXTRA_ENERGY = "Energy";
    public static final String EXTRA_IS_EFFECT = "IsEffect";
    public static final String EXTRA_IS_FATAL = "IsFatal";

    public static final int SPELL_STATE_FAIL = 0;
    public static final int SPELL_STATE_SUCCESS = 1;
    public static final int SPELL_STATE_FATAL = 2;

    private final Random mRandom = new Random();
    private boolean mIsEffect;
    private boolean mIsFatal;

    private View mViewRoll;
    private TextInputEditText mTextEditRoll;
    private View mViewEffect;
    private TextInputEditText mTextEditEffect;
    private TextInputEditText mTextEditColumn;
    private TextView mTextViewEffect;

    private Button mButtonClose;

    private int throwDice(int count) {
        int total = 0;
        for (int index = 0; index < count; index++) {
            total += (mRandom.nextInt(6) + 1);
        }
        return total;
    }

    private void closeActivity(){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_EFFECT, mIsEffect);
        intent.putExtra(EXTRA_IS_FATAL, mIsFatal);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void prepareButtons() {
        mButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_result);

        mViewRoll = findViewById(R.id.view_roll);
        mTextEditRoll = (TextInputEditText) findViewById(R.id.text_edit_roll);
        mViewEffect = findViewById(R.id.view_effect);
        mTextEditEffect = (TextInputEditText) findViewById(R.id.text_edit_effect);
        mTextEditColumn = (TextInputEditText) findViewById(R.id.text_edit_column);
        mTextViewEffect = (TextView) findViewById(R.id.text_view_effect);
        mButtonClose = (Button) findViewById(R.id.button_close);

        if (getIntent() != null) {
            int limit = getIntent().getIntExtra(EXTRA_LIMIT, 0);
            int spellState = getIntent().getIntExtra(EXTRA_SPELL_STATE, SPELL_STATE_FAIL);
            mIsFatal = (spellState == SPELL_STATE_FATAL);
            int energy = getIntent().getIntExtra(EXTRA_ENERGY, 0);
            int roll = throwDice(3);
            if (spellState == SPELL_STATE_FAIL || spellState == SPELL_STATE_SUCCESS) {
                mViewRoll.setVisibility(View.VISIBLE);
                mTextEditRoll.setText(String.valueOf(roll));
            } else {
                mViewRoll.setVisibility(View.GONE);
            }
            if (spellState == SPELL_STATE_FATAL || roll > limit) {
                mViewEffect.setVisibility(View.VISIBLE);
                mIsEffect = true;
                int effectRoll = throwDice(3);
                int column = MagicPassageEffectHelper.getInstance(this).getColumnForEnergy(energy, spellState == SPELL_STATE_FATAL);
                mTextEditEffect.setText(String.valueOf(effectRoll));
                mTextEditColumn.setText(String.valueOf(column + 1));
                String effect = MagicPassageEffectHelper.getInstance(this).getEffectForRoll(column, effectRoll);
                mTextViewEffect.setText(effect);
            } else {
                mViewEffect.setVisibility(View.GONE);
            }
        } else {
            finish();
        }
        prepareButtons();
    }

    @Override
    public void onBackPressed() {
        closeActivity();
    }
}
