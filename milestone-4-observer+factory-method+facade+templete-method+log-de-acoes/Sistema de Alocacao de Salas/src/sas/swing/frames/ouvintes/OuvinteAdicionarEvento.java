package sas.swing.frames.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.swing.frames.AdicionarEvento;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelEventos;

/**
 * Classe responsavel por ouvir os eventos na interface grafica de Adicionar
 * Evento e trata-los
 * 
 * @author Andre Luis
 *
 */

public class OuvinteAdicionarEvento implements ActionListener {

	AdicionarEvento janelaAdicionar;
	Principal janelaPrincipal;

	/**
	 * Metodo contrutor do ouvinte
	 * 
	 * @param janelaAdicionar
	 *            janela que tera o ouvinte
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
	 * 
	 * @return Acao que definira a habilitacao de um campo
	 */

	public boolean eventoComRepeticao() {

		if (janelaAdicionar.seRepetira.isSelected()) {
			janelaAdicionar.fieldRepeticoes.setEnabled(true);
			janelaAdicionar.labelRepeticoes.setEnabled(true);
			janelaAdicionar.comboTipoRepeticao.setEnabled(true);
			janelaAdicionar.labelTipo.setEnabled(true);

			return true;
		}

		else {
			janelaAdicionar.fieldRepeticoes.setText("");
			janelaAdicionar.fieldRepeticoes.setEnabled(false);
			janelaAdicionar.labelRepeticoes.setEnabled(false);
			janelaAdicionar.comboTipoRepeticao.setEnabled(false);
			janelaAdicionar.labelTipo.setEnabled(false);
			janelaAdicionar.fieldRepeticoes.setText("");
			janelaAdicionar.comboTipoRepeticao.setSelectedIndex(0);
			return false;
		}

	}

	/**
	 * Metodo que valida os campos. Evitando que informacoes comecem ou terminem com espacos
	 * 
	 * @param valor Valor do campo
	 * @return Verdadeiro se comeca ou termina com espacos
	 */
	
	public boolean comecaOuTerminaComEspaco(String valor) {

		int tamanho = valor.length();

		if (tamanho > 0) {
			String inicio = Character.toString(valor.charAt(0));
			String fim = Character.toString(valor.charAt(tamanho - 1));

			if (inicio.equals(" ") || fim.equals(" ")) {
				return true;
			}

		}

		return false;
	}

	/**
	 * Metodo que valida a acao delegada
	 */

	public void verificaCadastroEDecide() {

		String id = janelaAdicionar.fieldID.getText();
		String nome = janelaAdicionar.fieldNome.getText();
		String inicio = janelaAdicionar.fieldInicio.getText();
		String fim = janelaAdicionar.fieldFim.getText();
		String area = janelaAdicionar.fieldArea.getText();
		String contato = janelaAdicionar.fieldContato.getText();
		String repeticoes = janelaAdicionar.fieldRepeticoes.getText();
		String tipoRepeticao = (String) janelaAdicionar.comboTipoRepeticao
				.getSelectedItem();
		boolean selecionado = janelaAdicionar.seRepetira.isSelected();

		janelaAdicionar.botaoCadastrarEvento.setEnabled(false);

		int repeticoesInt = 0;

		if (!selecionado) {
			tipoRepeticao = "Sem repeticao";
		}

		if (selecionado
				&& tipoRepeticao
						.equals("--- selecione o tipo de repeticao ---")) {
			JOptionPane.showMessageDialog(null, "Informe o tipo de repeticao!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (id == null || id.trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Informe um ID!");
			janelaAdicionar.fieldID.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (comecaOuTerminaComEspaco(id)
				&& !id.trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"O ID nao deve comcecar ou terminar com espacos!");
			janelaAdicionar.fieldID.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (nome == null || nome.trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Informe o nome!");
			janelaAdicionar.fieldNome.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (comecaOuTerminaComEspaco(nome)
				&& !nome.trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"O nome nao deve comcecar ou terminar com espacos!");
			janelaAdicionar.fieldNome.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (inicio == null
				|| inicio.trim().equalsIgnoreCase("__/__/____ __:__")
				|| inicio.equals("") || comecaOuTerminaComEspaco(inicio)) {
			JOptionPane.showMessageDialog(null, "Informe o inicio!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (fim == null || fim.trim().equalsIgnoreCase("__/__/____ __:__")
				|| fim.equals("") || comecaOuTerminaComEspaco(fim)) {
			JOptionPane.showMessageDialog(null, "Informe o fim!");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (area == null || area.trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Informe a area!");
			janelaAdicionar.fieldArea.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (comecaOuTerminaComEspaco(area)
				&& !area.trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"A area nao deve comcecar ou terminar com espacos!");
			janelaAdicionar.fieldArea.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (contato == null || contato.trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Informe o contato!");
			janelaAdicionar.fieldContato.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (comecaOuTerminaComEspaco(contato)
				&& !contato.trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,
					"O contato nao deve comcecar ou terminar com espacos!");
			janelaAdicionar.fieldContato.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else if (eventoComRepeticao() && repeticoes.equals("")) {
			JOptionPane
					.showMessageDialog(
							null,
							"Voce definiu que o Evento ira se repetir,\nInforme as repeticoes ou desmarque a caixa!");
			janelaAdicionar.fieldRepeticoes.setText("");
			janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
		}

		else {
			if (eventoComRepeticao()) {
				try {
					repeticoesInt = Integer.parseInt(repeticoes);

					if (repeticoesInt <= 0) {
						JOptionPane
								.showMessageDialog(
										null,
										"Voce definiu que o Evento ira se repetir,\nLogo, as repeticoes nao podem ser menor ou igual a 0 (zero)!");
						janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
					}

					else {
						cadastrarEvento(id, nome, inicio, fim, area, contato,
								repeticoesInt, tipoRepeticao);
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Informe numeros (123) no campo repeticoes!");
					janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
				}
			}

			else {
				try {
					cadastrarEvento(id, nome, inicio, fim, area, contato,
							repeticoesInt, tipoRepeticao);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					janelaAdicionar.botaoCadastrarEvento.setEnabled(true);
				}
			}

		}

	}

	/**
	 * Metodo que ocorrera caso a acao seja validada
	 * 
	 * @param id
	 *            ID do evento
	 * @param nome
	 *            nome do evento
	 * @param inicio
	 *            inicio do evento
	 * @param fim
	 *            fim do evento
	 * @param area
	 *            area do evento
	 * @param contato
	 *            contato do evento
	 * @param repeticoes
	 *            repeticoes do evento
	 */

	public void cadastrarEvento(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes,
			String tipoRepeticao) {
		try {
			janelaPrincipal.fachada.adicionarEvento(id, nome, inicio, fim,
					area, contato, repeticoes, tipoRepeticao);
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
