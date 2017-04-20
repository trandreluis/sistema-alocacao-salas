package sas.swing.frames.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.LocalizarEvento;
import sas.swing.frames.Principal;

/**
 * Classe responsavel por ouvir os eventos na interface grafica de Localizar Evento e trata-los
 * 
 * @author Andre Luis
 *
 */

public class OuvinteLocalizarEvento implements ActionListener {

	LocalizarEvento janelaBuscar;
	Principal janelaPrincipal;
	OperacoesEvento opEventos = new OperacoesEvento();
	OperacoesSala opSalas = new OperacoesSala();
	OperacoesAlocacao operacoes = new OperacoesAlocacao(opSalas, opEventos);

	/**
	 * Metodo contrutor do ouvinte
	 * @param janelaBuscar janela que tera o ouvinte
	 */
	
	public OuvinteLocalizarEvento(LocalizarEvento janelaBuscar) {
		this.janelaBuscar = janelaBuscar;
		this.janelaPrincipal = janelaBuscar.janelaPrincipal;
	}

	/**
	 * Metodo que recebe e trata a acao
	 */
	
	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == janelaBuscar.comboAtributos) {
			mudancaCombo();
		}

		else if (evento.getSource() == janelaBuscar.botaoBuscar) {
			verificaCadastroEDecide();
		}

		else if (evento.getSource() == janelaBuscar.botaoCancelar) {
			janelaBuscar.dispose();
		}

	}

	/**
	 * Metodo que validara a acao
	 */
	
	public void verificaCadastroEDecide() {

		String atributo = (String) janelaBuscar.comboAtributos
				.getSelectedItem();
		String valor = janelaBuscar.fieldValor.getText();
		
		if (atributo.equalsIgnoreCase("-- selecione o Atributo --")
				&& valor.equalsIgnoreCase("")) {
			JOptionPane
					.showMessageDialog(null,
							"Por favor, selecione um Atributo e escolha um Valor a ser pesquisado!");
		}

		else if (atributo.equalsIgnoreCase("-- selecione o Atributo --")) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione um Atributo!");
		}

		else if (atributo.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Ppor favor, escolha um Valor a ser pesquisado!");
		}

		else if(valor.equalsIgnoreCase("Repeticoes")) {
			try{
				Integer.parseInt(valor);
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Para pesquisar por 'Repeticoes' informe numeros no campo de busca!");
			}
		}
		
		else {
			realizarBusca(atributo, valor);
		}

	}

	/**
	 * Metodo que sera chamado caso Verifica e Decide valide de forma correta a acao
	 * @param atributo Atributo a ser pesquisado
	 * @param valor Valor buscado para o atributo passado
	 */
	
	public void realizarBusca(String atributo, String valor) {

		String valorAtributo = "";

		try {
			valorAtributo = operacoes.localizarEvento(atributo, valor);
		} catch (RoomsAllocationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		if (valorAtributo.equals("") || valorAtributo == null) {
			JOptionPane.showMessageDialog(null,
					"Nao foram encontrados eventos com essa caraterisca.");
		}

		else {
			JOptionPane.showMessageDialog(null, valorAtributo);
		}
		
	}
	
	/**
	 * Alteracao dos campos
	 */
	
	public void mudancaCombo() {
		String atributo = (String) janelaBuscar.comboAtributos
				.getSelectedItem();
		if(!atributo.equals("-- selecione o Atributo --")) {
			janelaBuscar.fieldValor.setEnabled(true);
			janelaBuscar.comValorDe.setEnabled(true);
		}
		else if(atributo.equals("-- selecione o Atributo --")) {
			janelaBuscar.fieldValor.setEnabled(false);
			janelaBuscar.fieldValor.setText("");
			janelaBuscar.comValorDe.setEnabled(false);
		}
	}

}









