package br.com.ufc.opennebula;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;
import org.opennebula.client.vm.VirtualMachinePool;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import br.com.ufc.opennebula.R.id;

@SuppressLint("ResourceAsColor") public class InfoMVActivity extends ActionBarActivity {
	
	private String usuario;
	private String senha;
	private Client cliente;
	private String nome;
	private String status;
	private String idMV;
	private String ip;
	private String memoria;
	private String vcpu;
	private View view2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_info_mv);
		
		Intent intent = getIntent();
		
		usuario = intent.getStringExtra("usuario");
		senha = intent.getStringExtra("senha");
		
	
		nome = intent.getStringExtra("nome");
		status = intent.getStringExtra("status");
		idMV = intent.getStringExtra("id");
		ip = intent.getStringExtra("ip");
		memoria = intent.getStringExtra("memoria");
		vcpu = intent.getStringExtra("vcpu");
		
		
		TextView textview1 = (TextView) findViewById(R.id.textView1);
		TextView textview2 = (TextView) findViewById(R.id.textView2);
		TextView textview3 = (TextView) findViewById(R.id.textView3);
		TextView textview4 = (TextView) findViewById(R.id.textView4);
		TextView textview5 = (TextView) findViewById(R.id.textView5);
		TextView textview6 = (TextView) findViewById(R.id.textView6);
		
		if(status == "Runn"){
			textview4.setTextColor(R.color.verde);
		}else if(status == "Stopped"){
			textview4.setTextColor(R.color.vermelho);
		}
		
		textview1.setText("ID: " + idMV);
		textview2.setText("Nome: " + nome);
		textview3.setText("Status: " + status);
		textview4.setText("IP: " + ip);
		textview5.setText("Memória: " + memoria);
		textview6.setText("VCPU: " + vcpu);


		
	}
	
//Captura os itens criados por mim no arquivo xml do menu
	@Override 
	public boolean onCreateOptionsMenu(Menu menu){ 
	    MenuInflater menuInflater = getMenuInflater();
	    menuInflater.inflate(R.menu.menu_opcoes, menu);
	    return super.onCreateOptionsMenu(menu); 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
	//Realizar um case pelo “Id” dos itens e logo em seguida mostrar uma mensagem ao usuário
	  switch (item.getItemId()) 
	  {      
	   case id.parar:
		   try {
			pararMV(view2);
		} catch (ClientConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   break;
	   case id.resumir: 
		   try {
			resumirMV(view2);
		} catch (ClientConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   break;
	   case id.desligar:    
		   try {
			desligarMV(view2);
			
			Toast.makeText(getApplicationContext(), "Máquina Virtual " + nome + " Desligada", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(getApplicationContext(),OpcoesActivity.class);
			intent.putExtra("usuario", usuario);
			intent.putExtra("senha", senha);
			startActivity(intent);
		} catch (ClientConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	   break;     
	  case id.reiniciar:    
		   try {
			reiniciarMV(view2);
		} catch (ClientConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   break; 
	  }
	
	   //Retornar a classe pai
	   return super.onOptionsItemSelected(item);
	}
	
	public void desligarMV(View view) throws ClientConfigurationException {
	    	
	    	cliente = new Client(usuario + ":" + senha , "http://200.129.39.103:2633/RPC2");
			VirtualMachinePool pool = new VirtualMachinePool(cliente);
			pool.infoMine();
			pool.getById(Integer.parseInt(idMV)).finalizeVM();
			
			Toast.makeText(getApplicationContext(), "Máquina Virtual " + nome + " Desligada", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(this,OpcoesActivity.class);
			intent.putExtra("usuario", usuario);
			intent.putExtra("senha", senha);
			startActivity(intent);

	}
	
	public void pararMV(View view) throws ClientConfigurationException{
		
    	cliente = new Client(usuario + ":" + senha , "http://200.129.39.103:2633/RPC2");
		VirtualMachinePool pool = new VirtualMachinePool(cliente);
		pool.infoMine();
		pool.getById(Integer.parseInt(idMV)).stop();
		
		Toast.makeText(getApplicationContext(), "Máquina Virtual " + nome + " Parando...", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(this,OpcoesActivity.class);
		intent.putExtra("usuario", usuario);
		intent.putExtra("senha", senha);
		startActivity(intent);

		
		
		
	}
	
	
	public void resumirMV(View view) throws ClientConfigurationException{
		
	   	    cliente = new Client(usuario + ":" + senha , "http://200.129.39.103:2633/RPC2");
			VirtualMachinePool pool = new VirtualMachinePool(cliente);
			pool.infoMine();
			pool.getById(Integer.parseInt(idMV)).resume();
			
			Toast.makeText(getApplicationContext(), "Máquina Virtual " + nome + " Reiniciando...", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(this,OpcoesActivity.class);
			intent.putExtra("usuario", usuario);
			intent.putExtra("senha", senha);
			startActivity(intent);

		
		
	}
	
	public void reiniciarMV(View view) throws ClientConfigurationException{
		
   	    cliente = new Client(usuario + ":" + senha , "http://200.129.39.103:2633/RPC2");
		VirtualMachinePool pool = new VirtualMachinePool(cliente);
		pool.infoMine();
		pool.getById(Integer.parseInt(idMV)).restart();
		
		Toast.makeText(getApplicationContext(), "Máquina Virtual " + nome + " sendo Reiniciada", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(this,OpcoesActivity.class);
		intent.putExtra("usuario", usuario);
		intent.putExtra("senha", senha);
		startActivity(intent);
		
		
	}
	
}
