package cz.jksoftware.gurpstable.Activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cz.jksoftware.gurpstable.R;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText mTextEditLevel;
    private TextInputEditText mTextEditThauma;
    private TextInputEditText mTextEditRitual;

    private Button mButtonPlus;
    private Button mButtonMinus;

    private Button mButtonSpell;
    private Button mButtonTotal;
    private Button mButtonFatal;


    private int getLevel(){
        int level = 0;
        try {
            level = Integer.parseInt(mTextEditLevel.getText().toString());
        } catch (Exception exc) {
        }
        return level;
    }

    private void updateLevel(int level){
        mTextEditLevel.setText(String.valueOf(level));
        mTextEditThauma.setText(String.valueOf(18 - level));
        mTextEditRitual.setText(String.valueOf(15 - level));
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
        mButtonTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = getLevel();
                level = level - 5;
                if (level < 0) {
                    level = 0;
                }
                updateLevel(level);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextEditLevel = (TextInputEditText) findViewById(R.id.text_edit_level);
        mTextEditThauma = (TextInputEditText) findViewById(R.id.text_edit_thauma);
        mTextEditRitual = (TextInputEditText) findViewById(R.id.text_edit_ritual);

        mButtonPlus = (Button) findViewById(R.id.button_plus);
        mButtonMinus = (Button) findViewById(R.id.button_minus);

        mButtonSpell = (Button) findViewById(R.id.button_spell);
        mButtonTotal = (Button) findViewById(R.id.button_total);
        mButtonFatal = (Button) findViewById(R.id.button_fatal);

        prepareButtons();
        updateLevel(0);
    }
}
