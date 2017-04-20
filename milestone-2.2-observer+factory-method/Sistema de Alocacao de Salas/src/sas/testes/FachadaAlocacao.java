package sas.testes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sas.dao.AlocacaoDao;
import sas.dao.factoryMethod.conexoes.derby.BancoDerby;
import sas.excecoes.alocacao.RoomsAllocationException;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;
import easyaccept.EasyAcceptFacade;

/**
 * Classe responsavel pelos testes no Sistema de Alocacao de Salas
 * 
 * @author Andre Luis
 *
 */

public class FachadaAlocacao implements IFacadeRoomsAllocation {

	AlocacaoDao alocaDao = new AlocacaoDao();
	OperacoesSala staticOpSala = new OperacoesSala();
	OperacoesEvento staticOpEvento = new OperacoesEvento();
	OperacoesAlocacao staticOpAlocacao = new OperacoesAlocacao(staticOpSala, staticOpEvento);
	

	public static void main(String[] args) {
		
		BancoDerby banco = new BancoDerby();
		try {
			System.out.println("Conectando/criando banco... (por favor, espere, este processo pode demorar um pouco.)");
			banco.criarBanco();
			System.out.println();
			System.out.println("Banco criado.");
			System.out.println();
			System.out.println("Iniciando Testes de Aceitacao");
			System.out.println();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("Banco de Dados encontrado.");
			System.out.println();
			System.out.println("Iniciando Testes de Aceitacao");
			System.out.println();
		}
		
		List<String> arquivos = new ArrayList<String>();
		// adicionar arquivos dos scripts de teste da divisao
		arquivos.add("script-testes/US1.txt");
		arquivos.add("script-testes/US2.txt");
		arquivos.add("script-testes/US3.txt");
		arquivos.add("script-testes/US4.txt");
		arquivos.add("script-testes/US5.txt");
		arquivos.add("script-testes/US6.txt");
		arquivos.add("script-testes/US7.txt");

		FachadaAlocacao fachada = new FachadaAlocacao();
		// instanciar fachada do EasyAccept
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(fachada, arquivos);
		// executar os testes
		eaFacade.executeTests();
		// imprimir
		System.out.println(eaFacade.getCompleteResults());

	}

	@Override
	public void zerarSistema() {
		// Feito, testado: sucesso
		alocaDao.zerarSistema();

	}

	@Override
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo) throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpSala.adicionarSalaBasico(id, capacidade, finalidade, tipo);
		
	}

	@Override
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo, String apelido) throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpSala.adicionarSalaMedio(id, capacidade, finalidade, tipo, apelido);
	}

	@Override
	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo, String apelido, boolean aberto)
			throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpSala.adicionarSalaCompleto(id, capacidade, finalidade, tipo, apelido, aberto);
	}

	@Override
	public String getAtributoSala(String idSala, String atributo)
			throws RoomsAllocationException {
		// Feito, testado: sucesso
		
		return staticOpSala.getAtributo(idSala, atributo);
	}

	@Override
	public void adicionarEvento(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes)
			throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpEvento.adicionarEventoCompleto(id, nome, inicio, fim, area, contato, repeticoes);

	}

	@Override
	public void adicionarEvento(String id, String nome, String inicio,
			String fim, String area, String contato)
			throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpEvento.adicionarEventoBasico(id, nome, inicio, fim, area, contato);

	}

	@Override
	public Object getAtributoEvento(String idEvento, String atributo)
			throws RoomsAllocationException {
		// Feito, testado: sucesso
		return staticOpEvento.getAtributo(idEvento, atributo);
	}

	@Override
	public void alocarEvento(String idEvento, String idSala)
			throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpAlocacao.alocarEvento(idEvento, idSala);
		
	}

	@Override
	public String localizarEvento(String atributo, String valor)
			throws RoomsAllocationException {
		// Feito, testado: sucesso
		return staticOpAlocacao.localizarEvento(atributo, valor);
	}

	@Override
	public void desalocarEvento(String idEvento)
			throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpAlocacao.desalocarEvento(idEvento);
	}

	@Override
	public void cancelarEvento(String idEvento) throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpEvento.cancelarEvento(idEvento);
	}

	@Override
	public void removerSala(String idSala) throws RoomsAllocationException {
		// Feito, testado: sucesso
		staticOpSala.removerSala(idSala);
	}

}
