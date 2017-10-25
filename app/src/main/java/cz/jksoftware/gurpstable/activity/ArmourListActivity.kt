package cz.jksoftware.gurpstable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.j256.ormlite.android.apptools.OpenHelperManager
import cz.jksoftware.gurpstable.R
import cz.jksoftware.gurpstable.database.DatabaseHelper

class ArmourListActivity : AppCompatActivity() {

    private var databaseHelper: DatabaseHelper? = null

    fun getDbHelper(): DatabaseHelper? {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper::class.java)
        }
        return databaseHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_armour_list)
    }
}
