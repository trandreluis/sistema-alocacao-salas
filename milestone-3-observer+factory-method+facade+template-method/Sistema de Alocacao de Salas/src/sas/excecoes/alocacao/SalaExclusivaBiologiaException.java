package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tenta alocar, em uma sala que nao e de
 * biologia, um evento que nao e da area de biologia
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class SalaExclusivaBiologiaException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public SalaExclusivaBiologiaException(String msg) {
		super(msg);
	}

}
