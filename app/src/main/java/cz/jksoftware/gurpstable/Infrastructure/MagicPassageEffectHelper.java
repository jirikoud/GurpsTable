package cz.jksoftware.gurpstable.Infrastructure;

import android.content.Context;

import java.util.ArrayList;

import cz.jksoftware.gurpstable.R;

/**
 * Created by Koudy on 9/24/2017.
 */

public class MagicPassageEffectHelper {
    private ArrayList<ArrayList<String>> effectList;

    private static MagicPassageEffectHelper gInstance;

    public int getColumnForEnergy(int energy, boolean isFatal) {
        if (energy < 5) {
            return (isFatal ? 1 : 0);
        }
        if (energy < 16) {
            return (isFatal ? 2 : 1);
        }
        return 2;
    }

    public String getEffectForRoll(int column, int roll) {
        String effectString = effectList.get(column).get(roll - 3);
        return effectString;
    }

    private MagicPassageEffectHelper(Context context){
        effectList = new ArrayList<>();
        ArrayList<String> column = new ArrayList<>();
        column.add(context.getString(R.string.magic_passage_1_3));
        column.add(context.getString(R.string.magic_passage_1_4));
        column.add(context.getString(R.string.magic_passage_1_5));
        column.add(context.getString(R.string.magic_passage_1_6));
        column.add(context.getString(R.string.magic_passage_1_7));
        column.add(context.getString(R.string.magic_passage_1_8));
        column.add(context.getString(R.string.magic_passage_1_9));
        column.add(context.getString(R.string.magic_passage_1_10));
        column.add(context.getString(R.string.magic_passage_1_11));
        column.add(context.getString(R.string.magic_passage_1_12));
        column.add(context.getString(R.string.magic_passage_1_13));
        column.add(context.getString(R.string.magic_passage_1_14));
        column.add(context.getString(R.string.magic_passage_1_15));
        column.add(context.getString(R.string.magic_passage_1_16));
        column.add(context.getString(R.string.magic_passage_1_17));
        column.add(context.getString(R.string.magic_passage_1_18));
        effectList.add(column);
        column = new ArrayList<>();
        column.add(context.getString(R.string.magic_passage_2_3));
        column.add(context.getString(R.string.magic_passage_2_4));
        column.add(context.getString(R.string.magic_passage_2_5));
        column.add(context.getString(R.string.magic_passage_2_6));
        column.add(context.getString(R.string.magic_passage_2_7));
        column.add(context.getString(R.string.magic_passage_2_8));
        column.add(context.getString(R.string.magic_passage_2_9));
        column.add(context.getString(R.string.magic_passage_2_10));
        column.add(context.getString(R.string.magic_passage_2_11));
        column.add(context.getString(R.string.magic_passage_2_12));
        column.add(context.getString(R.string.magic_passage_2_13));
        column.add(context.getString(R.string.magic_passage_2_14));
        column.add(context.getString(R.string.magic_passage_2_15));
        column.add(context.getString(R.string.magic_passage_2_16));
        column.add(context.getString(R.string.magic_passage_2_17));
        column.add(context.getString(R.string.magic_passage_2_18));
        effectList.add(column);
        column = new ArrayList<>();
        column.add(context.getString(R.string.magic_passage_3_3));
        column.add(context.getString(R.string.magic_passage_3_4));
        column.add(context.getString(R.string.magic_passage_3_5));
        column.add(context.getString(R.string.magic_passage_3_6));
        column.add(context.getString(R.string.magic_passage_3_7));
        column.add(context.getString(R.string.magic_passage_3_8));
        column.add(context.getString(R.string.magic_passage_3_9));
        column.add(context.getString(R.string.magic_passage_3_10));
        column.add(context.getString(R.string.magic_passage_3_11));
        column.add(context.getString(R.string.magic_passage_3_12));
        column.add(context.getString(R.string.magic_passage_3_13));
        column.add(context.getString(R.string.magic_passage_3_14));
        column.add(context.getString(R.string.magic_passage_3_15));
        column.add(context.getString(R.string.magic_passage_3_16));
        column.add(context.getString(R.string.magic_passage_3_17));
        column.add(context.getString(R.string.magic_passage_3_18));
        effectList.add(column);
    }

    public static MagicPassageEffectHelper getInstance(Context context){
        if (gInstance == null){
            gInstance = new MagicPassageEffectHelper(context);
        }
        return gInstance;
    }
}
