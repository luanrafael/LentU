package com.fatec.lentu;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

@ContentView(R.layout.activity_cadastro)
public class Cadastro_activity extends RoboActivity {

	@InjectView(R.id.categorias) Spinner combo;
	private static final String[] CATEGORIAS= new String[]{"ELETRONICOS","DINHEIRO","DVD","JOGO","LIVRO","ROUPA","OUTROS"};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,CATEGORIAS);
		combo.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_activity, menu);
		return true;
	}

}
