package com.starcapital.collectapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.starcapital.collectapp.models.CardType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CardType.class}, version = 1)
@TypeConverters(MyTypeConverters.class)
public abstract class SaverightDatabase extends RoomDatabase {
    public abstract AccountsDao accountsDao();

    private static volatile SaverightDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SaverightDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SaverightDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaverightDatabase.class, "saveright_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
