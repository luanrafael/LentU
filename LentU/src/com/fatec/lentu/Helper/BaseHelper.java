package com.fatec.lentu.Helper;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fatec.lentu.model.Emprestimo;
import com.fatec.lentu.model.Pertence;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class BaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DB_NAME = "lentu.db";
	private static final Integer DB_VERSION = 1;
	private Dao<Pertence,Long> pertenceDao;
	private Dao<Emprestimo, Long> emprestimoDao;

	public BaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase dataBase, ConnectionSource connection) {
		try {
			TableUtils.createTable(connection, Pertence.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void onUpgrade(SQLiteDatabase db, ConnectionSource con,int oldVersion, int newVersion) {
		
	}
	
	public Dao<Pertence,Long> getPertenceDao() throws SQLException{
		if(pertenceDao == null){
			pertenceDao = getDao(Pertence.class);
		}
		return pertenceDao;
	}
	
	public Dao<Emprestimo, Long> getEmprestimo() throws SQLException {
		if (emprestimoDao == null) {
			emprestimoDao = getDao(Emprestimo.class);
		}
		return emprestimoDao;
	}
	
}
