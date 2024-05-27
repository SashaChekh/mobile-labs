package com.example.Lab9

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [HealthTracker::class], version = 1)
abstract class HealthTrackerDB: RoomDatabase() {
    abstract fun trackerDao(): HealthTrackerDao
}

@Entity(tableName = "trackers")
data class HealthTracker(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "steps") val steps: Int?,
    @ColumnInfo(name = "calories") val calories: Int?
)

@Dao
interface HealthTrackerDao {
    @Query("SELECT * FROM trackers")
    fun getAll(): List<HealthTracker>

    @Insert
    fun insertAll(vararg healthTrackers: HealthTracker)

    @Delete
    fun delete (healthTracker: HealthTracker)

    @Query("DELETE FROM trackers WHERE id = :trackerId")
    fun deleteById(trackerId: Int)
}
