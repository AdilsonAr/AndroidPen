package com.example.pen.dao.utilities;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pen.activity.SchoolSchedule;
import com.example.pen.dao.AppDb;

/**
 * Solo mantiene una instancia de la coneccion de base de datosRoom penDb
 */
public final class PenDb {
    private final String dbName = "db_pen";
    private Context appContext;
    private AppDb db;
    private static PenDb instance;

    //region CONSTRUCTORES
    public static AppDb getInstance(Context context){
        if(instance == null || !instance.db.isOpen()){
            instance = new PenDb();
            instance.db = Room.databaseBuilder(context,
                    AppDb.class, instance.dbName).build();
        }

        return instance.db;
    }

    private PenDb() {
    }

    //endregion CONSTRUCTORES
}
