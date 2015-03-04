package br.com.ufc.opennebula;


import org.opennebula.client.Client;
import org.opennebula.client.user.UserPool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText editText1;
	private EditText editText2;
	private String usuario;
	private String senha;
	private static final String LOGS = "logs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);		
		
	}
	
	@SuppressLint({ "ShowToast"})
	public void autenticar(View v){
		
		editText1 = (EditText) findViewById(R.id.usuario);
		editText2 = (EditText) findViewById(R.id.senha);
		
		usuario = editText1.getText().toString();
		senha = editText2.getText().toString();
		
		System.out.println(usuario);
		System.out.println(senha);
		
		/*try {
			cliente = new Client(usuario + ":" + senha , "http://200.129.39.103:2633/RPC2");
			
			userPool = new UserPool(cliente);
			Iterator<User> iterator = userPool.iterator();
			
			while(iterator.hasNext()){
				User usuario = iterator.next();
				Log.v(LOGS, "Usuario: " + usuario.getName());
				
			}
		} catch (ClientConfigurationException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}*/
		
		
		Intent intent = new Intent(this,OpcoesActivity.class);
		intent.putExtra("usuario", usuario);
		intent.putExtra("senha", senha);
		startActivity(intent);
		
		
		}

}
