package com.fatec.lentu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Toast;

import com.fatec.lentu.adapter.EmprestimoAdapter;
import com.fatec.lentu.dao.EmprestimoDao;
import com.fatec.lentu.model.Emprestimo;
import com.fatec.lentu.utils.Utils;

public class ListaEmprestimoActivity extends RoboListActivity {

	private EmprestimoDao emprestimoDao;
	List<Emprestimo> emprestimos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		emprestimoDao = new EmprestimoDao(this);
		emprestimos = new ArrayList<Emprestimo>();
		registerForContextMenu(getListView());
		this.atualizaLista();
	}

	public void atualizaLista() {
		try {
			emprestimos = emprestimoDao.loadAll();
			Utils.logInf("Lista Emprestimo Atualizada");
		} catch (SQLException e) {
			e.printStackTrace();
			Utils.logErr(e.toString());
		}
		setListAdapter(new EmprestimoAdapter(this, emprestimos));
	}

	// pegar item selecionado pelo cidadão
	public void deletar(Emprestimo entidade) {
		try {
			emprestimoDao.delete(entidade);
			Utils.logInf("Emprestimo Deletado - " + entidade.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			Utils.logErr(e.toString());
		}
		atualizaLista();
	}

	// nÃ£o deve passar o emprestimo populado ao editar!? acho que Ã© o "bundle"
	public void editar(Emprestimo emprestimo) {
		Intent intent = new Intent(this, CadastroEmprestimoActivity.class);
		intent.putExtra("id", emprestimo.getId());
		startActivity(intent);
		Utils.logInf("StartActivity - CadastroEmprestimoActivity.class");
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

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Emprestimo emprestimo = emprestimos.get(info.position);

		switch (item.getItemId()) {
		case R.id.action_enviar_sms:
			this.crobrarEmprestimo(emprestimo);
			break;
		case R.id.action_deletar:
			this.deletar(emprestimo);
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	public void crobrarEmprestimo(final Emprestimo emprestimo){
		final String mensagem = "Oi " + emprestimo.getAmigo() + " Você tem uma pendência comigo: " + 
				emprestimo.getPertence().getCategoria() + ": " + 
				emprestimo.getPertence().getNome();
	
		Runnable run = new Runnable() {
			
			private String numero = emprestimo.getTelefone();
			private String sms = mensagem; 

			@Override
			public void run() {
				Utils.enviaSms(numero, sms);
			}
		};
		
		new Thread(run).start();
		Toast.makeText(this, "SMS Enviado", Toast.LENGTH_SHORT).show();
		Utils.logInf("SMS Enviado");
	}
	
}
