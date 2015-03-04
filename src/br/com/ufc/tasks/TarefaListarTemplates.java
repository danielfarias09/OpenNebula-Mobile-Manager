package br.com.ufc.tasks;

import java.util.ArrayList;
import java.util.Iterator;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;
import org.opennebula.client.Pool;
import org.opennebula.client.template.Template;
import org.opennebula.client.template.TemplatePool;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class TarefaListarTemplates extends AsyncTask <String, String, ArrayList<Template> >{

	private Context context;
	private TemplateInterface ti;
	private ProgressDialog dialog;
	
	public TarefaListarTemplates(Context context, TemplateInterface ti){
		this.context = context;
		this.ti = ti;
	}


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(context, "Aguarde",
				"Carregando dados");
	}


@Override
  protected ArrayList<Template> doInBackground(String... params) {
	String usuario = params[0];
	String senha = params[1];
	Client cliente = null;
	ArrayList<Template> templates = null;
				
				try {
					cliente = new Client(usuario + ":" + senha , "http://200.129.39.103:2633/RPC2");
					TemplatePool pool = new TemplatePool(cliente,Pool.ALL);
					Iterator<Template> iterator = pool.iterator();
					templates = new ArrayList<Template>();
					pool.infoAll();
					
					while(iterator.hasNext()){
						
						Template template = iterator.next();
						//Guardo uma lista com os templates que ficam nas mesma
						//posição da lista de nomes
						templates.add(template);
					}

				} catch (ClientConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	
	
	return templates;
}

//Recebe como parâmetro o retorno do doInbackground
  protected void onPostExecute(ArrayList<Template> templates) {
	ti.listarTemplates(templates);
	dialog.dismiss();

}


}
