package cz.jksoftware.gurpstable.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import cz.jksoftware.gurpstable.dialog.SpellEnergyDialog;
import cz.jksoftware.gurpstable.dialog.SpellFatalDialog;
import cz.jksoftware.gurpstable.R;

public class MainActivity
    extends
        AppCompatActivity
    implements
        SpellEnergyDialog.ResultListener,
        SpellFatalDialog.ResultListener {

    public static final int REQUEST_SPELL_RESULT = 100;

    private TextInputEditText mTextEditLevel;
    private TextInputEditText mTextEditThaumatology;
    private TextInputEditText mTextEditRitual;

    private Button mButtonPlus;
    private Button mButtonMinus;

    private RadioButton mButtonThaumatology;
    private RadioButton mButtonRitual;

    private Button mButtonSpell;
    private Button mButtonTotal;
    private Button mButtonFatal;


    private int getLevel() {
        int level = 0;
        try {
            level = Integer.parseInt(mTextEditLevel.getText().toString());
        } catch (Exception exc) {
        }
        return level;
    }

    private int getThaumatologyLevel() {
        return (18 - getLevel());
    }

    private int getRitualLevel() {
        return (15 - getLevel());
    }

    private void updateLevel(int level) {
        mTextEditLevel.setText(String.valueOf(level));
        mTextEditThaumatology.setText(String.valueOf(getThaumatologyLevel()));
        mTextEditRitual.setText(String.valueOf(getRitualLevel()));
    }

    private void prepareButtons() {
        mButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = getLevel();
                level++;
                updateLevel(level);
            }
        });
        mButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = getLevel();
                if (level > 0) {
                    level--;
                }
                updateLevel(level);
            }
        });
        mButtonThaumatology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonRitual.setChecked(false);
            }
        });
        mButtonRitual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonThaumatology.setChecked(false);
            }
        });
        mButtonTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = getLevel();
                level -= level;
                if (level < 0) {
                    level = 0;
                }
                updateLevel(level);
            }
        });
        mButtonSpell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpellEnergyDialog dialog = SpellEnergyDialog.newInstance(MainActivity.this);
                dialog.show(getSupportFragmentManager(), "energy");
            }
        });
        mButtonFatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpellFatalDialog dialog = SpellFatalDialog.newInstance(MainActivity.this);
                dialog.show(getSupportFragmentManager(), "energy");
            }
        });
    }

    @Override
    public void onSpellFailed() {
        Intent intent = new Intent(this, SpellResultActivity.class);
        intent.putExtra(SpellResultActivity.EXTRA_LIMIT, mButtonThaumatology.isChecked() ? getThaumatologyLevel() : getRitualLevel());
        intent.putExtra(SpellResultActivity.EXTRA_SPELL_STATE, SpellResultActivity.SPELL_STATE_FAIL);
        startActivityForResult(intent, REQUEST_SPELL_RESULT);
    }

    @Override
    public void onSpellSuccess(int energy) {
        Intent intent = new Intent(this, SpellResultActivity.class);
        intent.putExtra(SpellResultActivity.EXTRA_LIMIT, mButtonThaumatology.isChecked() ? getThaumatologyLevel() : getRitualLevel());
        intent.putExtra(SpellResultActivity.EXTRA_SPELL_STATE, SpellResultActivity.SPELL_STATE_SUCCESS);
        intent.putExtra(SpellResultActivity.EXTRA_ENERGY, energy);
        startActivityForResult(intent, REQUEST_SPELL_RESULT);
    }

    @Override
    public void onSpellFatal(int energy) {
        Intent intent = new Intent(this, SpellResultActivity.class);
        intent.putExtra(SpellResultActivity.EXTRA_LIMIT, mButtonThaumatology.isChecked() ? getThaumatologyLevel() : getRitualLevel());
        intent.putExtra(SpellResultActivity.EXTRA_SPELL_STATE, SpellResultActivity.SPELL_STATE_FATAL);
        intent.putExtra(SpellResultActivity.EXTRA_ENERGY, energy);
        startActivityForResult(intent, REQUEST_SPELL_RESULT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextEditLevel = (TextInputEditText) findViewById(R.id.text_edit_level);
        mTextEditThaumatology = (TextInputEditText) findViewById(R.id.text_edit_thauma);
        mTextEditRitual = (TextInputEditText) findViewById(R.id.text_edit_ritual);

        mButtonPlus = (Button) findViewById(R.id.button_plus);
        mButtonMinus = (Button) findViewById(R.id.button_minus);

        mButtonThaumatology = (RadioButton) findViewById(R.id.button_thaumatology);
        mButtonRitual = (RadioButton) findViewById(R.id.button_ritual);

        mButtonSpell = (Button) findViewById(R.id.button_spell);
        mButtonTotal = (Button) findViewById(R.id.button_total);
        mButtonFatal = (Button) findViewById(R.id.button_fatal);

        prepareButtons();
        updateLevel(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SPELL_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                boolean isFatal = data.getBooleanExtra(SpellResultActivity.EXTRA_IS_FATAL, false);
                if (!isFatal) {
                    int level = getLevel();
                    boolean isEffect = data.getBooleanExtra(SpellResultActivity.EXTRA_IS_EFFECT, false);
                    if (isEffect) {
                        level -= 5;
                        if (level < 0) {
                            level = 0;
                        }
                    } else {
                        level++;
                    }
                    updateLevel(level);
                }
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
