package com.fatec.lentu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.fatec.lentu.Helper.BaseHelper;
import com.fatec.lentu.model.Pertence;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class PertenceDao implements LentUDao<Pertence> {

	private BaseHelper helper;
	private Dao<Pertence, Long> dao;

	public PertenceDao(Context context) {
		helper = OpenHelperManager.getHelper(context, BaseHelper.class);
		try {
			dao = helper.getDao(Pertence.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtém a lista com o nome dos pertences.
	 * */
	public List<String> getNames() throws SQLException {
		List<String> retorno = new ArrayList<String>();
		List<Pertence> pertences = this.loadAll();
		retorno.add("Selecione");
		for (Pertence pertence : pertences) {
			retorno.add(pertence.getNome());
		}
		return retorno; 
	}

	public void persist(Pertence p) throws SQLException {
		dao.createOrUpdate(p);
	}

	public List<Pertence> loadAll() throws SQLException {
		List<Pertence> encontrados = dao.queryForAll();
		return encontrados;
	}

	public Pertence load(Long id) throws SQLException {
		Pertence pertence = dao.queryForId(id);
		return pertence;
	}

	public boolean delete(Pertence pertence) throws SQLException {
		int retorno = dao.delete(pertence);
		if (retorno > 0) {
			return true;
		}
		return false;
	}

	public boolean delete(Long id) throws SQLException {
		int retorno = dao.deleteById(id);
		if (retorno > 0) {
			return true;
		}
		return false;
	}
}
