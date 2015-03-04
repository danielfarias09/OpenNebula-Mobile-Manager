package br.com.ufc.opennebula;

import java.io.Serializable;

public class MaquinaVirtual implements Serializable {
	
	private static final long serialVersionUID = -7060210544600464481L; 
	private String id;
	private String nome;
	private String status;
	private String ip;
	private String memoria;
	private String vcpu;

	

	
	public MaquinaVirtual(String id, String nome, String status, String ip,String memoria, String vcpu) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.ip = ip;
		this.memoria = memoria;
		this.vcpu = vcpu;
	}




	@Override
	public String toString(){
		
		return nome;
		//return "Nome: " + nome + "\n" + "Status: " + status + "\n" + "Host: " + host +"\n" + "Ip: " + ip;
	}
	



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}




	public String getIp() {
		return ip;
	}




	public void setIp(String ip) {
		this.ip = ip;
	}




	public String getMemoria() {
		return memoria;
	}




	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}




	public String getVcpu() {
		return vcpu;
	}




	public void setVcpu(String vcpu) {
		this.vcpu = vcpu;
	}

	
	
	

}
