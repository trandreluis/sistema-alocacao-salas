package sas.objetos;

/**
 * Classe que representa um Evento
 * 
 * @author Andre Luis
 *
 */

public class Evento {

	private String idEvento;
	private String nome;
	private String inicio;
	private String fim;
	private String area;
	private String contato;
	private String tipoRepeticao = "Semanal";
	private int repeticoes = 0;

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public int getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}

	public String getTipoRepeticao() {
		return tipoRepeticao;
	}

	public void setTipoRepeticao(String tipoRepeticao) {
		this.tipoRepeticao = tipoRepeticao;
	}

}
