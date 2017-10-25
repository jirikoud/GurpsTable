package cz.jksoftware.gurpstable.database.dao

import com.j256.ormlite.dao.BaseDaoImpl
import cz.jksoftware.gurpstable.database.entity.ArmourEntity

/**
 * Created by Koudy on 10/25/2017.
 */
class ArmourDao(dataClass: Class<ArmourEntity>) : BaseDaoImpl<ArmourEntity, Int>(dataClass){
}