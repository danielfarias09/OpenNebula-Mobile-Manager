package br.com.ufc.tasks;

import java.util.ArrayList;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;
import org.opennebula.client.OneResponse;
import org.opennebula.client.template.Template;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class TarefaCriarMv extends AsyncTask <String, String, String>{
	
	private Context contexto;
	private Template templateEscolhido;
	private ProgressDialog dialog;
	
	public TarefaCriarMv(Context contexto, Template template){
		this.contexto = contexto;
		this.templateEscolhido = template;
		
		
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(contexto, "Aguarde",
				"Carregando dados");
	}

	@SuppressLint("ShowToast") protected String doInBackground(String... params) {
		
		String nomeTemplate = params[0];

			OneResponse rc = templateEscolhido.instantiate(nomeTemplate);
			
			if(rc.isError()){
				Toast.makeText(contexto, "Erro: " + rc.getErrorMessage(), Toast.LENGTH_LONG);
							
			}else{
				Toast.makeText(contexto, "Máquina Virtual Instanciada com Sucesso", Toast.LENGTH_LONG);		
			}



		return null;
	}
	
	  protected void onPostExecute(ArrayList<Template> templates) {
			dialog.dismiss();

		}


}
