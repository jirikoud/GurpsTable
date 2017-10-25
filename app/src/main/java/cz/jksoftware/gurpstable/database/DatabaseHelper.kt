package cz.jksoftware.gurpstable.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import cz.jksoftware.gurpstable.database.entity.ArmourEntity
import java.sql.SQLException
import java.util.logging.Logger

/**
 * Created by Koudy on 10/25/2017.
 */

class DatabaseHelper : OrmLiteSqliteOpenHelper {

    companion object {
        const val DATABASE_NAME = "gurps.db"
        const val DATABASE_VERSION = 1
        val LOG = Logger.getLogger(DatabaseHelper::class.java.name)
    }

    constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        LOG.info("creating tables")
        try {
            TableUtils.createTableIfNotExists(connectionSource, ArmourEntity.javaClass)
        } catch (e: SQLException) {
            LOG.severe(Log.getStackTraceString(e))
        }
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        LOG.info("upgrading tables from version $oldVersion to $newVersion")
    }
}