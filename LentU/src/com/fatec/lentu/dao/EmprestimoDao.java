package com.fatec.lentu.dao;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.fatec.lentu.helper.BaseHelper;
import com.fatec.lentu.model.Emprestimo;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class EmprestimoDao implements LentUDao<Emprestimo> {
	
	private BaseHelper baseHelper;
	private Dao<Emprestimo, Long> dao;
	
	public EmprestimoDao(Context context) {
		baseHelper = OpenHelperManager.getHelper(context, BaseHelper.class);
		try {
			dao = baseHelper.getDao(Emprestimo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void persist(Emprestimo entidade) throws SQLException {
		dao.createOrUpdate(entidade);
	}

	@Override
	public List<Emprestimo> loadAll() throws SQLException {
		List<Emprestimo> retorno = dao.queryForAll();
		return retorno;
	}

	@Override
	public Emprestimo load(Long id) throws SQLException {
		Emprestimo registro = dao.queryForId(id);
		return registro;
	}

	@Override
	public boolean delete(Emprestimo entidade) throws SQLException {
		int resposta = dao.delete(entidade);
		if (resposta > 0) 
			return true;
		return false;
	}

	@Override
	public boolean delete(Long id) throws SQLException {
		int resposta = dao.deleteById(id);
		if (resposta > 0)
			return true;
		return false;
	}

}
