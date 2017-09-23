package cz.jksoftware.gurpstable.Activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import cz.jksoftware.gurpstable.R;

/**
 * Created by Koudy on 9/24/2017.
 */

public class SpellResultActivity extends AppCompatActivity {

    public static final String EXTRA_LIMIT = "Limit";
    public static final String EXTRA_SPELL_STATE = "SpellState";
    public static final String EXTRA_ENERGY = "Energy";

    public static final int SPELL_STATE_FAIL = 0;
    public static final int SPELL_STATE_SUCCESS = 1;
    public static final int SPELL_STATE_FATAL = 2;

    private final Random mRandom = new Random();

    private View mViewRoll;
    private TextInputEditText mTextViewRoll;

    private int throwDice(int count) {
        int total = 0;
        for (int index = 0; index < count; index++) {
            total += (mRandom.nextInt(6) + 1);
        }
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_result);

        mViewRoll = findViewById(R.id.view_roll);
        mTextViewRoll = (TextInputEditText) findViewById(R.id.text_edit_roll);

        if (getIntent() != null) {
            int limit = getIntent().getIntExtra(EXTRA_LIMIT, 0);
            int spellState = getIntent().getIntExtra(EXTRA_SPELL_STATE, SPELL_STATE_FAIL);
            int energy = getIntent().getIntExtra(EXTRA_ENERGY, 0);
            int roll = throwDice(3);
            if (spellState == SPELL_STATE_FAIL || spellState == SPELL_STATE_SUCCESS) {
                mViewRoll.setVisibility(View.VISIBLE);
                mTextViewRoll.setText(String.valueOf(roll));
            } else {
                mViewRoll.setVisibility(View.GONE);
            }
            if (spellState == SPELL_STATE_FATAL || roll > limit) {

            }
        } else {
            finish();
        }
    }

}
