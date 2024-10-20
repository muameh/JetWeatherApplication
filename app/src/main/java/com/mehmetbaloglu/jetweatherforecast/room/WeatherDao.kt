package com.mehmetbaloglu.jetweatherforecast.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mehmetbaloglu.jetweatherforecast.data.model.FavoriteCity
import com.mehmetbaloglu.jetweatherforecast.data.model.Unit
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    //favorite cities table
    @Query("SELECT * FROM favorite_cities_tbl")
    fun getFavorites(): Flow<List<FavoriteCity>>

    @Query("SELECT * FROM favorite_cities_tbl WHERE city_name = :city")
    suspend fun getFavById(city: String): FavoriteCity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteCity: FavoriteCity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favoriteCity: FavoriteCity)

    @Query("DELETE FROM favorite_cities_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favoriteCity: FavoriteCity)

    //units table
    @Query("SELECT * FROM settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query("DELETE FROM settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: Unit)
}