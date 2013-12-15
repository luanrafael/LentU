package com.fatec.lentu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fatec.lentu.dao.PertenceDao;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


@ContentView(R.layout.cadastro_emprestimo)
public class CadastroEmprestimoActivity extends RoboActivity  {
	
	@InjectView(R.id.txt_amigo) EditText amigo;
	private Spinner pertences;
	private static final String[] CATEGORIAS = new String[]{"SELECIONE","ELETRONICOS","DINHEIRO","DVD","JOGO","LIVRO","ROUPA","OUTROS"};
	
	private PertenceDao pertenceDao;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pertenceDao = new PertenceDao(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		List<String> nomes = obterNomes();
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomes);
		pertences = (Spinner) findViewById(R.id.spinnerPertences);
		this.pertences.setAdapter(adaptador);
	}
	
	public List<String> obterNomes(){
		List<String> retorno = new ArrayList<String>();
		try {
			retorno = pertenceDao.getNames();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cadastro_pertence_activity, menu);
		return true;
		
	}
	
}
