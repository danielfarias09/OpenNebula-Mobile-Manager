package br.com.ufc.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opennebula.client.Client;
import org.opennebula.client.ClientConfigurationException;
import org.opennebula.client.vm.VirtualMachine;
import org.opennebula.client.vm.VirtualMachinePool;

import br.com.ufc.opennebula.MaquinaVirtual;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TarefaListarMvs extends AsyncTask <String, String, List<MaquinaVirtual> >{
	
	private Context context;
	private TarefaListarMvsInterface tl;
	private ProgressDialog dialog;
	
	public TarefaListarMvs(Context context, TarefaListarMvsInterface tl){
		this.context = context;
		this.tl = tl;
	}


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(context, "Aguarde",
				"Carregando dados");
	}


@Override
  protected List<MaquinaVirtual> doInBackground(String... params) {
	String usuario = params[0];
	String senha = params[1];
	Client cliente = null;
	List<MaquinaVirtual> mvs = null;
	
		try {
			cliente = new Client(usuario + ":" + senha , "http://200.129.39.103:2633/RPC2");
			VirtualMachinePool pool = new VirtualMachinePool(cliente);
			Iterator<VirtualMachine> iterator = pool.iterator();
		    mvs = new ArrayList<MaquinaVirtual>();
			//Array do objeto MaquinaVirtual criado por mim para setar as informações das mvs
			pool.infoMine();
			
			while(iterator.hasNext()){
				//Recebe a vm
				VirtualMachine vm = iterator.next();
				//Log.v("LOGS","xpath: " + vm.info().getMessage());
				Log.v("LOGS","MEMÓRIA: " + vm.xpath("MEMORY"));
				Log.v("LOGS","IP: " + vm.xpath("TEMPLATE/NIC/IP"));
				Log.v("LOGS","VCPU: " + vm.xpath("TEMPLATE/VCPU"));
				mvs.add(new MaquinaVirtual(vm.getId(),vm.getName(),vm.status(),vm.xpath("TEMPLATE/NIC/IP"), vm.xpath("MEMORY"), vm.xpath("TEMPLATE/VCPU")));
		}
		} catch (ClientConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return mvs;
}

//Recebe como parâmetro o retorno do doInbackground
  protected void onPostExecute(List<MaquinaVirtual> mvs) {
	tl.atualizaTela(mvs);
	dialog.dismiss();

}

}