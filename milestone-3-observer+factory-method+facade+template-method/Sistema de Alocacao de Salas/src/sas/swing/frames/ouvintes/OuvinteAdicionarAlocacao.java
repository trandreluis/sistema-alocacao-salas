package sas.swing.frames.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.AdicionarAlocacao;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelAlocacoes;

/**
 * Classe responsavel por ouvir os eventos na interface grafica de Adicionar Alocacao e trata-los
 * 
 * @author Andre Luis
 *
 */

public class OuvinteAdicionarAlocacao implements ActionListener {

	AdicionarAlocacao janelaAdicionar;
	Principal janelaPrincipal;
	OperacoesSala opSala = new OperacoesSala();
	OperacoesEvento opEvento = new OperacoesEvento();
	OperacoesAlocacao opAlocacao = new OperacoesAlocacao(opSala, opEvento);

	/**
	 * Metodo contrutor do ouvinte
	 * @param janelaAdicionar janela que tera o ouvinte
	 */
	
	public OuvinteAdicionarAlocacao(AdicionarAlocacao janelaAdicionar) {
		this.janelaAdicionar = janelaAdicionar;
		this.janelaPrincipal = janelaAdicionar.janelaPrincipal;
	}
	
	/**
	 * Metodo que receb o evento
	 */
	
	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == janelaAdicionar.botaoCadastrarAlocacao) {
			verificaCadastroEDecide();
		}

		else if (evento.getSource() == janelaAdicionar.botaoCancelar) {
			janelaAdicionar.dispose();
		}

		else if (evento.getSource() == janelaAdicionar.comboIDEvento) {
			comboEventoAlterado();
		}

		else if (evento.getSource() == janelaAdicionar.comboIDSala) {
			comboSalaAlterada();
		}

	}

	/**
	 * Metodo que tratara o evento ocorrido no comboBox
	 */
	
	public void comboSalaAlterada() {

		String idSala = (String) janelaAdicionar.comboIDSala.getSelectedItem();

		String apelido = "";
		String capacidade = "";
		String finalidade = "";
		String tipo = "";
		String aberta = "";
		
		if (!idSala.equals("-- selecione o ID --")) {
			try {
				
				apelido = opSala.getAtributo(idSala, "apelido");
				
				if(apelido.equals("")) {
					apelido = "-- Este atributo nao foi definido --";
				}
				
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			try {
				capacidade = opSala.getAtributo(idSala, "capacidade");
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			try {
				finalidade = opSala.getAtributo(idSala, "finalidade");
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			try {
				tipo = opSala.getAtributo(idSala, "tipo");
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			try {
				aberta = opSala.getAtributo(idSala, "aberta");
				
				if(aberta.equals("false")) {
					aberta = "Nao";
				}
				
				else if(aberta.equals("true")) {
					aberta = "Sim";
				}
				
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			janelaAdicionar.fieldApelidoSala.setText(apelido);
			janelaAdicionar.fieldCapacidadeSala.setText(capacidade);
			janelaAdicionar.fieldFinalidadeSala.setText(finalidade);
			janelaAdicionar.fieldTipoSala.setText(tipo);
			janelaAdicionar.fieldIsAbertaSala.setText(aberta);
		}
		
		else {
			janelaAdicionar.fieldApelidoSala.setText("");
			janelaAdicionar.fieldCapacidadeSala.setText("");
			janelaAdicionar.fieldFinalidadeSala.setText("");
			janelaAdicionar.fieldTipoSala.setText("");
			janelaAdicionar.fieldIsAbertaSala.setText("");
		}

		
	}

	/**
	 * Metodo que tratara o evento ocorrido no comboBox
	 */
	
	
	public void comboEventoAlterado() {

		String idEvento = (String) janelaAdicionar.comboIDEvento.getSelectedItem();

		String nome = "";
		String inicio = "";
		String fim = "";
		String area = "";
		String contato = "";
		String repeticoes = "0";
		
		if (!idEvento.equals("-- selecione o ID --")) {
			try {
				
				nome = opEvento.getAtributo(idEvento, "nome");
				
				if(nome.equals("")) {
					nome = "-- Este atributo nao foi definido --";
				}
				
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			try {
				inicio = opEvento.getAtributo(idEvento, "inicio");
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			try {
				fim = opEvento.getAtributo(idEvento, "fim");
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			try {
				area = opEvento.getAtributo(idEvento, "area");
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			try {
				contato = opEvento.getAtributo(idEvento, "contato");
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			try {
				repeticoes = opEvento.getAtributo(idEvento, "repeticoes");
			} catch(RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			janelaAdicionar.fieldNomeEvento.setText(nome);
			janelaAdicionar.fieldInicioEvento.setText(inicio);
			janelaAdicionar.fieldFimEvento.setText(fim);
			janelaAdicionar.fieldAreaEvento.setText(area);
			janelaAdicionar.fieldContatoEvento.setText(contato);
			janelaAdicionar.fieldRepeticoesEvento.setText(repeticoes);
		}
		
		else {
			janelaAdicionar.fieldNomeEvento.setText("");
			janelaAdicionar.fieldInicioEvento.setText("");
			janelaAdicionar.fieldFimEvento.setText("");
			janelaAdicionar.fieldAreaEvento.setText("");
			janelaAdicionar.fieldContatoEvento.setText("");
			janelaAdicionar.fieldRepeticoesEvento.setText("");
		}
		
	}
	
	/**
	 * Metodo que tratara a acao e a validadra ou nao
	 */

	public void verificaCadastroEDecide() {

		String idSala = (String) janelaAdicionar.comboIDSala.getSelectedItem();
		String idEvento = (String) janelaAdicionar.comboIDEvento
				.getSelectedItem();

		if (idSala.equals("-- selecione o ID --") && idEvento.equals("-- selecione o ID --")) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione um Evento e uma Sala!");
		}

		else if (idEvento.equals("-- selecione o ID --")) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione um Evento!");
		}
		
		else if (idSala.equals("-- selecione o ID --")) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione uma Sala!");
		}

		else {
			cadastrarAlocacao(idSala, idEvento);
		}

	}
	
	/**
	 * Metodo que realizara o intuito da janel caso o evento seja validado
	 * @param idSala ID da sala
	 * @param idEvento ID do evento
	 */

	public void cadastrarAlocacao(String idSala, String idEvento) {
		try {
			opAlocacao.alocarEvento(idEvento, idSala);
			JPanelAlocacoes painelAlocacoes = new JPanelAlocacoes();
			painelAlocacoes.montarPainel(janelaPrincipal);
			janelaPrincipal.menus.setComponentAt(2, painelAlocacoes);
			janelaAdicionar.dispose();
		} catch (RoomsAllocationException e) {
			janelaAdicionar.comboIDEvento.setSelectedIndex(0);
			janelaAdicionar.comboIDSala.setSelectedIndex(0);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
