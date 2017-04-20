package sas.swing.frames.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.operacoes.OperacoesEvento;
import sas.swing.frames.AdicionarEvento;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelEventos;

/**
 * Classe responsavel por ouvir os eventos na interface grafica de Adicionar Evento e trata-los
 * 
 * @author Andre Luis
 *
 */

public class OuvinteAdicionarEvento implements ActionListener {

	AdicionarEvento janelaAdicionar;
	Principal janelaPrincipal;
	OperacoesEvento operacoes = new OperacoesEvento();

	/**
	 * Metodo contrutor do ouvinte
	 * @param janelaAdicionar janela que tera o ouvinte
	 */
	
	public OuvinteAdicionarEvento(AdicionarEvento janelaAdicionar) {
		this.janelaAdicionar = janelaAdicionar;
		this.janelaPrincipal = janelaAdicionar.janelaPrincipal;
	}

	/**
	 * Meotodo que recebe o evento e o direciona
	 */
	
	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == janelaAdicionar.botaoCadastrarEvento) {
			verificaCadastroEDecide();
		}

		else if (evento.getSource() == janelaAdicionar.botaoCancelar) {
			janelaAdicionar.dispose();
		}

		else if (evento.getSource() == janelaAdicionar.seRepetira) {
			eventoComRepeticao();
		}

	}

	/**
	 * Metodo que valida o campo de repeticao ou nao
	 * @return Acao que definira a habilitacao de um campo
	 */
	
	public boolean eventoComRepeticao() {

		if (janelaAdicionar.seRepetira.isSelected()) {
			janelaAdicionar.fieldRepeticoes.setEnabled(true);
			janelaAdicionar.labelRepeticoes.setEnabled(true);

			return true;
		}

		else {
			janelaAdicionar.fieldRepeticoes.setText("");
			janelaAdicionar.fieldRepeticoes.setEnabled(false);
			janelaAdicionar.labelRepeticoes.setEnabled(false);
			return false;
		}

	}

	/**
	 * Meoto que valida a acao delegada
	 */
	
	public void verificaCadastroEDecide() {

		String id = janelaAdicionar.fieldID.getText();
		String nome = janelaAdicionar.fieldNome.getText();
		String inicio = janelaAdicionar.fieldInicio.getText();
		String fim = janelaAdicionar.fieldFim.getText();
		String area = janelaAdicionar.fieldArea.getText();
		String contato = janelaAdicionar.fieldContato.getText();
		String repeticoes = janelaAdicionar.fieldRepeticoes.getText();

		janelaAdicionar.botaoCadastrarEvento.setEnabled(false);
		
		int repeticoesInt = 0;

		if (id == null || id.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Preencha os campos corretamente!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (id == null || id.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Preencha os campos corretamente!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (nome == null || nome.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Preencha os campos corretamente!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (inicio == null || inicio.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Preencha os campos corretamente!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (fim == null || fim.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Preencha os campos corretamente!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (area == null || area.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Preencha os campos corretamente!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (nome == null || nome.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Preencha os campos corretamente!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (contato == null || contato.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"Preencha os campos corretamente!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else {
			if (eventoComRepeticao()) {
				try {
					repeticoesInt = Integer.parseInt(repeticoes);
					cadastrarEvento(id, nome, inicio, fim, area, contato,
							repeticoesInt);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Informe numeros (123) no campo repeticoes!");
					janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
				}
			}

			else {
				try {
					cadastrarEvento(id, nome, inicio, fim, area, contato,
							repeticoesInt);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
				}
			}

		}

	}

	/**
	 * Metodo que ocorrera caso a acao seja validada
	 * @param id ID do evento
	 * @param nome nome do evento
	 * @param inicio inicio do evento
	 * @param fim fim do evento
	 * @param area area do evento
	 * @param contato contato do evento
	 * @param repeticoes repeticoes do evento
	 */
	
	public void cadastrarEvento(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes) {
		try {
			operacoes.adicionarEventoCompleto(id, nome, inicio, fim, area,
					contato, repeticoes);
			JPanelEventos painelEventos = new JPanelEventos();
			painelEventos.montarPainel(janelaPrincipal);
			janelaPrincipal.menus.setComponentAt(1, painelEventos);
			janelaAdicionar.dispose();
		} catch (RoomsAllocationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}
	}

}
