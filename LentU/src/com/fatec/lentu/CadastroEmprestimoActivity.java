package com.fatec.lentu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fatec.lentu.dao.EmprestimoDao;
import com.fatec.lentu.dao.PertenceDao;
import com.fatec.lentu.model.Pertence;


@ContentView(R.layout.cadastro_emprestimo)
public class CadastroEmprestimoActivity extends RoboActivity  {
	
	@InjectView(R.id.spinner_pertences) Spinner combo; 
	@InjectView(R.id.txt_amigo) EditText amigo;
	
	private EmprestimoDao emprestimoDao;
	private PertenceDao pertenceDao;
	private List<Pertence> pertences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pertenceDao = new PertenceDao(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, obterPertences());
		combo.setAdapter(adapter);
	}
	
	private String[] obterPertences() {
		List<String> retorno = new ArrayList<String>();
		try {
			retorno = pertenceDao.getNames();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (retorno == null){
			Toast.makeText(this, "Nenhum Pertence Cadastrado", Toast.LENGTH_SHORT).show();
		}
		return (String[]) retorno.toArray();
	}
	
//	private void salvar() {
//		emprestimoDao.persist(entidade);
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cadastro_pertence_activity, menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_salvar:
			
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		super.onOptionsMenuClosed(menu);
		
	}
	
//	public void abreListaEmprestimoActivity(){
//		Intent it = new Intent(this, ListaEmprestimoActivity.class);
//		startActivity(it);
//	}

}
