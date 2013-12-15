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
	
	private static final String[] CATEGORIAS = new String[]{"SELECIONE","ELETRONICOS","DINHEIRO","DVD","JOGO","LIVRO","ROUPA","OUTROS"};
	private PertenceDao pertenceDao;
	private Long id = null;
	private ArrayAdapter<String> adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,CATEGORIAS);
		combo.setAdapter(adapter);	
		
		pertenceDao = new PertenceDao(this);
		
		if(getIntent().getExtras() != null){
			carregaPertence((Long) getIntent().getExtras().get("pertenceToEdit"));
		}
	}

	public void salvar(){
		Pertence p = new Pertence();
		String nome = this.nome.getText().toString();
		String categoria = combo.getSelectedItem().toString();
		if(nome.equals(" ") || nome.equals("")){
			Toast.makeText(this, "Preencha o campo Nome!", Toast.LENGTH_SHORT).show();
		}
		else if(categoria.equals("SELECIONE")){
			Toast.makeText(this, "Selecione um categoria Valida! ", Toast.LENGTH_SHORT).show();
		}
		else {			
			p.setCategoria(categoria);
			p.setNome(nome);
			if(id != null){
				p.setId(id);
			}
			try {
				pertenceDao.persist(p);
				Toast.makeText(this, "Salvo =D", Toast.LENGTH_SHORT).show();
			} catch (SQLException e) {
				e.printStackTrace();
				Toast.makeText(this, "Ops, ocorreu um durante a execução tente novamente =(", Toast.LENGTH_SHORT).show();
			}
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cadastro_pertence_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_salvar:
			salvar();
			break;
		case R.id.action_limpar:
			nome.setText("");
			combo.setSelection(0);
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
	
	public void carregaPertence(Long id){
		try {
			Pertence pertence = pertenceDao.load(id);
			nome.setText(pertence.getNome());
			combo.setSelection(adapter.getPosition(pertence.getCategoria()));
			this.id = pertence.getId();
		} catch (SQLException e) {
			e.printStackTrace();
			Toast.makeText(this, "Erro ao buscar id", Toast.LENGTH_SHORT).show();
		}
	}
	
}
