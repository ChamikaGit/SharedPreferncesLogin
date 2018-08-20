package com.chamika.fidenz.sharedlogin.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.chamika.fidenz.sharedlogin.Actvities.Home;
import com.chamika.fidenz.sharedlogin.models.Student;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

/**
 * Created by fidenz on 2/6/18.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    // Fields

    public static final String DB_NAME = "student_manager.db";
    private static final int DB_VERSION = 1;

    // Public methods

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
        try {

            // Create Table with given table name with columnName
            TableUtils.createTable(cs, Student.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {

    }

    public List getAll(Class clazz) throws SQLException, java.sql.SQLException {
        Dao<Student, ?> dao = null;
        try {
            dao = getDao(clazz);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

            return dao.queryForAll();

    }

    public  Student getById(Class clazz, Object aId) throws SQLException, java.sql.SQLException {
        Dao<Student, Object> dao = null;
        try {
            dao = getDao(clazz);
        } catch (java.sql.SQLException e){
            e.printStackTrace();
        }

            return dao.queryForId(aId);

    }

    public Dao.CreateOrUpdateStatus createOrUpdate(Student obj) throws SQLException, java.sql.SQLException {
        Dao<Student, ?> dao = null;
        try {
            dao = (Dao<Student, ?>) getDao(obj.getClass());
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

            return dao.createOrUpdate(obj);

    }

    public  int deleteById(Class clazz, Object aId) throws SQLException, java.sql.SQLException {
        Dao<Student, Object> dao = null;
        try {
            dao = getDao(clazz);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

            return dao.deleteById(aId);

    }


}