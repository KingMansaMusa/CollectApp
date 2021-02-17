package com.starcapital.collectapp.database;

import androidx.room.TypeConverter;

import java.util.UUID;

public class MyTypeConverters {

    @TypeConverter
    public static String fromUUID(UUID uuid) {
        return uuid == null ? null : String.valueOf(uuid);
    }

    @TypeConverter
    public static UUID toUUID(String uuid) {
        return uuid == null ? null : UUID.fromString(uuid);
    }

}
