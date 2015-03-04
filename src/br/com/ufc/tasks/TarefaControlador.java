package br.com.ufc.tasks;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;
import org.opennebula.client.vm.VirtualMachinePool;

import br.com.ufc.opennebula.OpcoesActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class TarefaControlador extends AsyncTask <String, String, String>{
	
	private Context contexto;
	private String action;
	private String mensagem;
	private String idMV;
	
	public TarefaControlador(Context contexto, String action, String mensagem, String idMV){
		this.contexto = contexto;
		this.action = action;
		this.mensagem = mensagem;
		this.idMV = idMV;
		
	}


	@Override
	protected String doInBackground(String... params) {
		
		String usuario = params[0];
		String senha = params[1];
		
    	Client cliente;
		try {
			cliente = new Client(usuario + ":" + senha , "http://200.129.39.103:2633/RPC2");
			VirtualMachinePool pool = new VirtualMachinePool(cliente);
			pool.infoMine();
			pool.getById(Integer.parseInt(idMV)).finalizeVM();
			
		} catch (ClientConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
