package com.fatec.lentu;

import java.sql.SQLException;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fatec.lentu.dao.PertenceDao;
import com.fatec.lentu.model.Pertence;

@ContentView(R.layout.activity_cadastro)
public class CadastroPertencesActivity extends RoboActivity {

	@InjectView(R.id.categorias) Spinner combo;
	@InjectView(R.id.nome) EditText nome;
	
	private static final String[] CATEGORIAS = new String[]{"ELETRONICOS","DINHEIRO","DVD","JOGO","LIVRO","ROUPA","OUTROS"};
	
	private PertenceDao pertenceDao;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,CATEGORIAS);
		combo.setAdapter(adapter);	
		
		pertenceDao = new PertenceDao(this);
		
	}

	public void salvar(){
		Pertence p = new Pertence();
		p.setCategoria(combo.getSelectedItem().toString());
		p.setNome(nome.getText().toString());
		try {
			pertenceDao.persist(p);
			Toast.makeText(this, "Salvo =D", Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {
			e.printStackTrace();
			Toast.makeText(this, "Ops, ocorreu um durante a execução tente novamente =(", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_salvar:
			salvar();
			break;
		case R.id.action_editar:
			salvar();
			break;
		case R.id.action_deletar:
			Toast.makeText(this, "deletar", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_listar:
			abreListaPertencesActivity();
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void abreListaPertencesActivity(){
		Intent it = new Intent(this, ListaPertencesActivity.class);
		startActivity(it);
	}
	
}
