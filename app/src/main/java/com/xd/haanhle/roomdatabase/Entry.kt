package com.xd.haanhle.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "entry_table")
data class Entry (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "input_type")
    var inputType: Int = -1,
    @ColumnInfo(name = "activity_input")
    var activityInput: Int = -1,

    @ColumnInfo(name = "time")
    var time: String = "",
    @ColumnInfo(name = "date")
    var date: String = "",
    @ColumnInfo(name = "duration")
    var duration: Double = 0.0,       // Exercise duration in seconds
    @ColumnInfo(name = "distance")
    var distance: Double = 0.0,      // Distance traveled. Either in meters or feet.
    @ColumnInfo(name = "calories")
    var calories: Double = 0.0,        // Calories burnt
    @ColumnInfo(name = "heart_rate")
    var heartRate: Double = 0.0,       // Heart rate
    @ColumnInfo(name = "comment")
    var comment: String = "",       // Comments

////    var locationList: ArrayList<LatLng>,  // Location list
//    @ColumnInfo(name = "avgPace")
//    var avgPace: Double,       // Average pace
//    @ColumnInfo(name = "avgSpeed")
//    var avgSpeed: Double,     // Average speed
//    @ColumnInfo(name = "climb")
//    var climb: Double,         // Climb. Either in meters or feet.

)