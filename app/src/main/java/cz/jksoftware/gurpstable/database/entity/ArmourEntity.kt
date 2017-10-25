package cz.jksoftware.gurpstable.database.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import cz.jksoftware.gurpstable.database.dao.ArmourDao

/**
 * Created by Koudy on 10/25/2017.
 */
@DatabaseTable(tableName = ArmourEntity.TABLE_NAME, daoClass = ArmourDao::class)
class ArmourEntity {
    companion object {
        const val TABLE_NAME = "armour"
        const val FIELD_ID = "id"
        const val FIELD_TITLE = "title"
        const val FIELD_DESCRIPTION = "description"
    }

    @DatabaseField(generatedId = true, columnName = FIELD_ID)
    var id: Int = 0

    @DatabaseField(columnName = FIELD_TITLE)
    lateinit var title: String

    @DatabaseField(columnName = FIELD_DESCRIPTION)
    lateinit var description: String

    constructor(title: String, description: String) {
        this.title = title
        this.description = description
    }
}