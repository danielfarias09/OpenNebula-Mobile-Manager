package br.com.ufc.opennebula;

import org.opennebula.client.ClientConfigurationException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class OpcoesActivity extends ActionBarActivity {
	
	private String usuario;
	private String senha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_opcoes);


			
			Intent intent = getIntent();
		    usuario = intent.getStringExtra("usuario");
		    senha = intent.getStringExtra("senha");			
		
	}
	

	
	public void addMV(View v) throws ClientConfigurationException{
		
		
		Intent intent = new Intent(this, AdicionarMVActivity.class);
		intent.putExtra("usuario", usuario);
		intent.putExtra("senha", senha);
		startActivity(intent);
		
		
	}
	

	
	public void listarMVs(View v) throws ClientConfigurationException{
		 	
		Intent intent = new Intent(this,ListarVMActivity.class);
		intent.putExtra("usuario", usuario);
		intent.putExtra("senha", senha);
		startActivity(intent);		
	}

	
	public void sair(View v){
		finish();
		
	}
	
		

	

}
