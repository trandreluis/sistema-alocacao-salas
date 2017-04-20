package sas.objetos;

/**
 * Classe que representa uma Sala
 * 
 * @author Andre Luis
 *
 */

public class Sala {

	private String idSala;
	private int capacidade;
	private String finalidade;
	private String tipo;
	private String apelido = "";
	private boolean isAberta = false;

	public String getIdSala() {
		return idSala;
	}

	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isAberta() {
		return isAberta;
	}

	public void setAberta(boolean isAberta) {
		this.isAberta = isAberta;
	}

}
