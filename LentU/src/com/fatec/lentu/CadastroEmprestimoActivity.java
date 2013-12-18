package com.fatec.lentu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fatec.lentu.dao.EmprestimoDao;
import com.fatec.lentu.dao.PertenceDao;
import com.fatec.lentu.model.Emprestimo;
import com.fatec.lentu.model.Pertence;
import com.fatec.lentu.utils.Utils;


@ContentView(R.layout.cadastro_emprestimo)
public class CadastroEmprestimoActivity extends RoboActivity  {
	
	private EditText amigo;
	private EditText telefone;
	private Spinner pertences;
	
	private PertenceDao pertenceDao;
	private EmprestimoDao emprestimoDao;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pertenceDao = new PertenceDao(this);
		emprestimoDao = new EmprestimoDao(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		List<String> nomes = obterNomes();
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomes);
		pertences = (Spinner) findViewById(R.id.spinnerPertences);
		amigo = (EditText) findViewById(R.id.edt_amigo);
		telefone = (EditText) findViewById(R.id.edt_telefone);
		
		this.pertences.setAdapter(adaptador);
	}
	
	public List<String> obterNomes(){
		List<String> retorno = new ArrayList<String>();
		try {
			retorno = pertenceDao.getNames();
		} catch (SQLException e) {
			e.printStackTrace();
			Utils.logErr(e.toString());
		}
		return retorno;
	}
	
	public void salvar() {
		Emprestimo emprestimo = new Emprestimo();
		
		String nome = this.pertences.getSelectedItem().toString();
		try {
			Pertence pertence = pertenceDao.buscarPorNome(nome);
			pertence.setIsEmprestado(Boolean.TRUE);
			pertenceDao.persist(pertence);
			
			emprestimo.setAmigo(amigo.getText().toString());
			emprestimo.setPertence(pertence);
			emprestimo.setTelefone(telefone.getText().toString());
			emprestimoDao.persist(emprestimo);
			
			
			Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_LONG).show();
		} catch (SQLException e) {
			Toast.makeText(this, "Erro ao salvar :(!", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			Utils.logErr(e.toString());
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_salvar:
			this.salvar();
			break;
		case R.id.action_limpar:
			amigo.setText("");
			telefone.setText("");
			pertences.setSelection(0);
			break;
		case R.id.action_listar:
			abreListaEmprestimo();
			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	public void abreListaEmprestimo() {
		Intent intent = new Intent(this, ListaEmprestimoActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cadastro_activity, menu);
		return true;
	}
	

}
