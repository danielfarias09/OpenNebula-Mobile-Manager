package br.com.ufc.opennebula;
import java.util.List;

import org.opennebula.client.Client;

import br.com.ufc.tasks.TarefaListarMvs;
import br.com.ufc.tasks.TarefaListarMvsInterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



@SuppressLint({ "ShowToast", "NewApi" })
public class ListarVMActivity extends ActionBarActivity implements TarefaListarMvsInterface {
	private List<MaquinaVirtual> mvs;
	private ArrayAdapter<String> adapter;
	private int adapterLayout = android.R.layout.simple_list_item_1;
	private ListView listView;
	private String usuario;
	private String senha;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_listar_vm);
		
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		listView = (ListView) findViewById(R.id.lista);
		
		Intent intent = getIntent();
		usuario = intent.getStringExtra("usuario");
		senha = intent.getStringExtra("senha");
		
		TarefaListarMvs tarefa = new TarefaListarMvs(this,this);
		tarefa.execute(usuario, senha);

	}
	
	@Override
	public void atualizaTela(List<MaquinaVirtual> listMvs) {
		mvs = listMvs;
		carregarMVs(mvs);
			
	}
	
	
	public void carregarMVs(final List<MaquinaVirtual> mvs){
		
		
		/*Para criarmos o nosso ArrayAdapter tivemos que informar os seguintes dados:
		- Um Context, no caso o this da própria Activity.
		- Um layout, representa o layout de cada item da lista, nesse caso usamos um disponível na própria API do Android o android.R.layout.simple_list_item_1.
		- Por último e mais importante, os dados, nesse caso a lista  já preenchida.
		*/
		
		adapter = new ArrayAdapter<String>(this,adapterLayout);
		for(int i=0; i<mvs.size();i++){
		adapter.add(mvs.get(i).getNome());
		}
		listView.setAdapter(adapter);		
		
	

	
	listView.setOnItemClickListener(new OnItemClickListener() {
		  @Override
		  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			  
			 String nome = mvs.get(position).getNome();
			 String status = mvs.get(position).getStatus();
			 String idMv = mvs.get(position).getId();
			 String ip = mvs.get(position).getIp();
			 String memoria = mvs.get(position).getMemoria();
			 String vcpu = mvs.get(position).getVcpu();
			 
	
			 
			  Intent intent = new Intent(view.getContext(), InfoMVActivity.class);
			  intent.putExtra("usuario", usuario);
			  intent.putExtra("senha", senha);
			  intent.putExtra("nome", nome);
			  intent.putExtra("status", status);
			  intent.putExtra("id", idMv);
			  intent.putExtra("ip", ip);
			  intent.putExtra("memoria", memoria);
			  intent.putExtra("vcpu", vcpu);
			  startActivity(intent);
			  
			  		  }
		});

}
	
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.listar_vm , menu);
    	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

	

	
}
