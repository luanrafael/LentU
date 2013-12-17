package com.fatec.lentu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.fatec.lentu.dao.EmprestimoDao;
import com.fatec.lentu.model.Emprestimo;
import com.fatec.lentu.model.Pertence;

public class ListaEmprestimoActivity extends RoboListActivity {
	
	private EmprestimoDao emprestimoDao;
	List<Emprestimo> emprestimos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		emprestimoDao = new EmprestimoDao(this);
		emprestimos = new ArrayList<Emprestimo>();
		super.onCreate(savedInstanceState);
		this.atualizaLista();
	}
	
	public void atualizaLista() {
		try {
			emprestimos = emprestimoDao.loadAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// pegar item selecionado pelo cidadão ¬¬
	public void deletar(Emprestimo entidade) {
		try {
			emprestimoDao.delete(entidade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// não deve passar o emprestimo populado ao editar!? acho que é o "bundle"
	public void editar(Emprestimo emprestimo) {
		Intent intent = new Intent(this, CadastroEmprestimoActivity.class);
		intent.putExtra("id", emprestimo.getId());
		startActivity(intent);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_list_activity, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		//obtendo item selecionado pelo user
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		Emprestimo emprestimo = emprestimos.get(info.position);
		
		switch (item.getItemId()) {
		case R.id.action_editar:
			this.editar(emprestimo);
			break;
		case R.id.action_deletar:
			this.deletar(emprestimo);
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

}
