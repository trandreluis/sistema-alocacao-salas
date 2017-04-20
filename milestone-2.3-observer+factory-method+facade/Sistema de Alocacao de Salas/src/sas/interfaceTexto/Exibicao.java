package sas.interfaceTexto;

/**
 * Interface responsavel por padronizar as interfaces de exibicao e interacao
 * com o usuario
 * 
 * @author Andre Luis
 *
 */

public interface Exibicao {

	/**
	 * Metodo de Menu de interacoes, escolhas de operacoes
	 */

	public void menu();

	/**
	 * Metodo responsavel por validar a escolha do usuario
	 * 
	 * @param opcao
	 *            Entrada do usuario
	 * @return Enrada valida ou nao
	 */

	public boolean validaEntrada(String opcao);

	/**
	 * Metodo responsavel por delegar a acao que deve ocorrer (chamando os
	 * metodos responsaveis por tal) . De acordo com a entrada, ja validada, do
	 * usuario
	 * 
	 * @param acao
	 *            Entrada validada do usuario, comando de acao
	 */

	public void delegarAcao(int acao);

	/**
	 * Metodo responsavel por adicionar Salas, Eventos ou Alocacoes
	 */

	public void adicionar();

	/**
	 * Metodo responsavel por listar Salas, Eventos ou Alocacoes
	 */

	public void listar();

	/**
	 * Metodo responsavel por remover Salas, Eventos ou Alocacoes
	 */

	public void remover();

}
