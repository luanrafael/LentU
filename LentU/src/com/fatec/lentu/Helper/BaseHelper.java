package com.fatec.lentu.Helper;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public abstract class BaseHelper<T> extends OrmLiteSqliteOpenHelper {

	private static final String DB_NAME = "lentu.db";
	private static final Integer DB_VERSION = 1;
	private Dao<T, Integer> modelDao = null;

	@SuppressWarnings("unchecked")
	private Class<T> getTypeClass() {
		return (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public BaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase dataBase, ConnectionSource connection) {
		try {
			TableUtils.createTableIfNotExists(connection, getTypeClass());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void onUpgrade(SQLiteDatabase db, ConnectionSource con,int oldVersion, int newVersion) {
		
	}

	public Dao<T, Integer> getModelDao() throws SQLException {
		if (modelDao == null) {
			modelDao = getDao(getTypeClass());
		}
		return modelDao;
	}
	
}
