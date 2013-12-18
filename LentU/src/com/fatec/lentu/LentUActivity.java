package com.fatec.lentu;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@ContentView(R.layout.activity_lent_u)
public class LentUActivity extends RoboActivity {

    @InjectView(R.id.btnNovoEmprestimo) Button btnNovoEmprestimo;
    @InjectView(R.id.btnListarEmprestimos) Button btnListarEmprestos;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        btnListarEmprestos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abreIntent(ListaEmprestimoActivity.class);
			}
		});
		
        btnNovoEmprestimo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abreIntent(CadastroEmprestimoActivity.class);
			}
		});
    }


  
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lent_u, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    	switch (item.getItemId()) {
		case R.id.action_novo_pertence:
			abreIntent(CadastroPertencesActivity.class);
			break;

		case R.id.action_listar_pertences:
			abreIntent(ListaPertencesActivity.class);
		default:
			break;
		}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    public void abreIntent(Class classe){
    	Intent intent = new Intent(this,classe);
    	startActivity(intent);
    }
    
}
