package com.mehmetbaloglu.jetweatherforecast.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull


@Entity(tableName = "settings_tbl")
data class Unit(
    @PrimaryKey
    @ColumnInfo(name = "unit")
    @Nonnull
    val unit: String
)
