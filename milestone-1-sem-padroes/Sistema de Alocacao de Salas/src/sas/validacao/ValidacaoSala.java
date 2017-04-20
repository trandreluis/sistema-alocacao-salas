package sas.validacao;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.excecoes.sala.IdSalaDuplicadaException;
import sas.excecoes.sala.IdentificacaoInvalidaException;
import sas.excecoes.sala.SalaInexistenteException;
import sas.excecoes.sala.TipoInvalidoException;
import sas.operacoes.OperacoesSala;

/**
 * Classe responsavel pela validacao das operacoes que envolvem Salas
 * @author Andre Luis
 *
 */

public class ValidacaoSala {

	OperacoesSala ops;
	
	/**
	 * Metodo responsavel por validar a adicao de Salas no sistema 
	 */
	
	public void adicionarSalaBasico(String id, int capacidade, String finalidade, String tipo) throws RoomsAllocationException {
		ops = new OperacoesSala();
		if(id == null) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		}
		else if(id.equals("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		}
		else if(ops.verificarExistencia(id)) {
			throw new IdSalaDuplicadaException("Ja existe sala com esta identificacao.");
		}
		else if(tipo == null) {
			throw new TipoInvalidoException("Tipo invalido.");
		}
		else if(finalidade.equals("Sala de Aula")) {
			if(!tipo.equals("Normal") && !tipo.equals("Inteligente") && !tipo.equals("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");				
			}
		}
		else if(finalidade.equals("Laboratorio")) {
			if(!tipo.equals("Quimica") && !tipo.equals("quimica") && !tipo.equals("fisica") && !tipo.equals("biologia") && !tipo.equals("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");				
			}
		}
		else if(finalidade.equals("Escritorio")) {
			if(!tipo.equals("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}
		else if(finalidade.equals("Sala de Conferencia")) {
			if(!tipo.equals("Normal") && !tipo.equals("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}
		
	}
	
	/**
	 * Metodo responsavel por validar a adicao de Salas no sistema 
	 */
	
	public void adicionarSalaMedio(String id, int capacidade, String finalidade,
			String tipo, String apelido) throws RoomsAllocationException {
		ops = new OperacoesSala();
		if(id == null) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		}
		else if(id.equals("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		}
		else if(ops.verificarExistencia(id)) {
			throw new IdSalaDuplicadaException("Ja existe sala com esta identificacao.");
		}
		else if(tipo == null) {
			throw new TipoInvalidoException("Tipo invalido.");
		}
		else if(finalidade.equals("Sala de Aula")) {
			if(!tipo.equals("Normal") && !tipo.equals("Inteligente") && !tipo.equals("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");				
			}
		}
		else if(finalidade.equals("Laboratorio")) {
			if(!tipo.equals("Quimica") && !tipo.equals("quimica") && !tipo.equals("fisica") && !tipo.equals("biologia") && !tipo.equals("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");				
			}
		}
		else if(finalidade.equals("Escritorio")) {
			if(!tipo.equals("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}
		else if(finalidade.equals("Sala de Conferencia")) {
			if(!tipo.equals("Normal") && !tipo.equals("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}
		
	}
	
	/**
	 * Metodo responsavel por validar a adicao de Salas no sistema 
	 */
	
	public void adicionarSalaCompleto(String id, int capacidade, String finalidade,
			String tipo, String apelido, boolean aberto) throws RoomsAllocationException {
		ops = new OperacoesSala();
		if(id == null) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		}
		else if(id.equals("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		}
		else if(ops.verificarExistencia(id)) {
			throw new IdSalaDuplicadaException("Ja existe sala com esta identificacao.");
		}
		else if(tipo == null) {
			throw new TipoInvalidoException("Tipo invalido.");
		}
		else if(finalidade.equals("Sala de Aula")) {
			if(!tipo.equals("Normal") && !tipo.equals("Inteligente") && !tipo.equals("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");				
			}
		}
		else if(finalidade.equals("Laboratorio")) {
			if(!tipo.equals("Quimica") && !tipo.equals("quimica") && !tipo.equals("fisica") && !tipo.equals("biologia") && !tipo.equals("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");				
			}
		}
		else if(finalidade.equals("Escritorio")) {
			if(!tipo.equals("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}
		else if(finalidade.equals("Sala de Conferencia")) {
			if(!tipo.equals("Normal") && !tipo.equals("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}
		
	}
	
	/**
	 * Metodo responsavel por validar as entradas que o getAtributo() recebe 
	 */
	
	public void getAtributoSala(String idSala, String atributo) throws RoomsAllocationException {
		ops = new OperacoesSala();
		if(!ops.verificarExistencia(idSala)) {
			throw new SalaInexistenteException("Sala nao existe.");
		}
	}
	
	/**
	 * Metodo responsavel por validar a remocao de Salas no sistema 
	 */
	
	public void removerSala(String idSala) throws RoomsAllocationException {
		
		ops = new OperacoesSala();
		
		if(!ops.verificarExistencia(idSala)) {
			throw new SalaInexistenteException("Sala nao existe.");
		}
		
	}
	
}
