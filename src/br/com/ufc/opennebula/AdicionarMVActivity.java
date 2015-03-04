package br.com.ufc.opennebula;


import java.util.ArrayList;

import org.opennebula.client.template.Template;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import br.com.ufc.tasks.TarefaCriarMv;
import br.com.ufc.tasks.TarefaListarTemplates;
import br.com.ufc.tasks.TemplateInterface;

@SuppressLint("ShowToast")
public class AdicionarMVActivity extends Activity implements TemplateInterface {
	
	private EditText editText1;
	private Spinner spinner;
	private String usuario;
	private String senha;
	private ArrayList<Template> templates;
    private ArrayAdapter<String> adapter;
    private String nomeTemplate;
    private Template templateEscolhido;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_adicionar_mv);
		
		Intent intent = getIntent();
		usuario = intent.getStringExtra("usuario");
		senha = intent.getStringExtra("senha");
		
		TarefaListarTemplates tarefa = new TarefaListarTemplates(this, this);
		tarefa.execute(usuario, senha);
		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		

		
	}
	
	@Override
	public void listarTemplates(ArrayList<Template> listTemplates) {
		templates = listTemplates; 
		carregarSpinner(templates);
		
	}
	
	public void carregarSpinner(final ArrayList<Template> templates){
		
		for(int i=0;i<templates.size();i++){
		//Adiciono os nomes dos templates ao adapter 
		adapter.add(templates.get(i).getName());
		}
	
		
		spinner = (Spinner) findViewById(R.id.escolher_template);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				//Pega o nome do template selecionado na posição que o usuário escolher
				templateEscolhido = templates.get(position);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});

	}
	
	public void criarMV(View v) throws Exception{
		
		editText1 = (EditText) findViewById(R.id.nome);
		nomeTemplate = editText1.getText().toString();
		
		TarefaCriarMv tarefa = new TarefaCriarMv(this, templateEscolhido);
		tarefa.execute(nomeTemplate);
		
		Intent intent = new Intent(this, OpcoesActivity.class);
		startActivity(intent);
		
		
	}

	
	

}
