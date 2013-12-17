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

import com.fatec.lentu.adapter.PertenceAdapter;
import com.fatec.lentu.dao.PertenceDao;
import com.fatec.lentu.model.Pertence;

public class ListaPertencesActivity extends RoboListActivity{

	private PertenceDao pertenceDao;
	private List<Pertence> pertences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pertences = new ArrayList<Pertence>();
		registerForContextMenu(getListView());
		pertenceDao = new PertenceDao(this);
		atualizaLista();
	}
	
	private void atualizaLista() {
		try {
			pertences = pertenceDao.loadAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setListAdapter(new PertenceAdapter(this, pertences));
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

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		Pertence pertence = pertences.get(info.position);
		switch (item.getItemId()) {
		case R.id.action_deletar:
			deletar(pertence);
			break;
		case R.id.action_editar:
			editar(pertence);
		default:
			break;
		}
		
		return super.onContextItemSelected(item);
	}

	
	public void deletar(Pertence p){
		try {
			pertenceDao.delete(p);
			Toast.makeText(this, "Pertence Deletado Com Sucesso! =) ", Toast.LENGTH_SHORT).show();
			atualizaLista();
		} catch (SQLException e) {
			e.printStackTrace();
			Toast.makeText(this, "Ops, ocorreu um durante a execu��o tente novamente =(", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void editar(Pertence p){
		Intent it = new Intent(this,CadastroPertencesActivity.class);
		it.putExtra("pertenceToEdit", p.getId());
		startActivity(it);
	}
}
