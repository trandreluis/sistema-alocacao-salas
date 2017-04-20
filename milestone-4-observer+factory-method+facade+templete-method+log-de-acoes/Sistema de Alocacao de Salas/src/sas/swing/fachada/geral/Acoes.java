package sas.swing.fachada.geral;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.objetos.Alocacao;
import sas.objetos.Evento;
import sas.objetos.Sala;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelAlocacoes;
import sas.swing.paineis.JPanelEventos;
import sas.swing.paineis.JPanelSalas;

public class Acoes {

	private static ArrayList<String> acoes = new ArrayList<>();
	private static ArrayList<Object> objetos = new ArrayList<>();
	
	private static ArrayList<String> acoesPossiveis = new ArrayList<>();
	
	private static Principal janela;
	private static FachadaGeralGUI fachada;
	private static DesfazerAcoes desfazer;
	
	static {
		acoesPossiveis.add("Adicionar Sala");
		acoesPossiveis.add("Remover Sala");
		acoesPossiveis.add("Adicionar Evento");
		acoesPossiveis.add("Remover Evento");
		acoesPossiveis.add("Adicionar Alocacao");
		acoesPossiveis.add("Remover Alocacao");
	}
	
	public Acoes(FachadaGeralGUI fachada) {
		
		Acoes.fachada = fachada;
		Acoes.janela = fachada.janelaPrincipal;
		Acoes.desfazer = new DesfazerAcoes(Acoes.fachada);
		
	}
	
	public static void cadastrar(String acao, Object objeto) {
		
		if(acoes.size() == 3 && objetos.size() == 3) {
			removerPrimeiro();
			adicionar(acao, objeto);
		}
		
		else {
			adicionar(acao, objeto);
		}
		
	}
	
	private static void adicionar(String acao, Object objeto) {
		
		if(acoesPossiveis.contains(acao)) {
			acoes.add(acao);
			objetos.add(objeto);
		}
		
	}
	
	private static void removerPrimeiro() {
		acoes.remove(0);
		objetos.remove(0);
	}
	
	public static void desfazerAcao() {

		int tamanho = acoes.size();
		
		Sala sala;
		Evento evento;
		Alocacao alocacao;
		
			if(tamanho > 0 && tamanho <= 3) {
				
				String acaoCadastrada = acoes.get(tamanho-1);
				Object objetoCadastrado = objetos.get(tamanho-1);
				
				if(acaoCadastrada.equals("Adicionar Sala")) {
					sala = (Sala) objetoCadastrado;
					//chamar remover sala
					try {
						desfazer.removerSalaAcoes(sala);
						JOptionPane.showMessageDialog(null, "Acao (adicionar sala) desfeita, ela foi removida: "+sala.getIdSala());
						objetos.remove(tamanho-1);
						acoes.remove(tamanho-1);
						atualizarPaineis();
					} catch (RoomsAllocationException e) {
						JOptionPane.showMessageDialog(null, "Nao existem acoes a serem desfeitas.");
					}
				}
				
				if(acaoCadastrada.equals("Remover Sala")) {
					sala = (Sala) objetoCadastrado;
					//chamar adicionar sala
					try {
						desfazer.adicionarSalaAcoes(sala);
						JOptionPane.showMessageDialog(null, "Acao (remover sala) desfeita, ela foi adicionada novamente: "+sala.getIdSala());
						objetos.remove(tamanho-1);
						acoes.remove(tamanho-1);
						atualizarPaineis();
					} catch (RoomsAllocationException e) {
						JOptionPane.showMessageDialog(null, "Nao existem acoes a serem desfeitas.");
					}
				}
				
				if(acaoCadastrada.equals("Adicionar Evento")) {
					evento = (Evento) objetoCadastrado;
					//chamar remover evento
					try {
						desfazer.removerEventoAcoes(evento);
						JOptionPane.showMessageDialog(null, "Acao (adicionar evento) desfeita, ele foi removido: "+evento.getIdEvento());
						objetos.remove(tamanho-1);
						acoes.remove(tamanho-1);
						atualizarPaineis();
					} catch (RoomsAllocationException e) {
						JOptionPane.showMessageDialog(null, "Nao existem acoes a serem desfeitas.");
					}
				}
				
				if(acaoCadastrada.equals("Remover Evento")) {
					evento = (Evento) objetoCadastrado;
					//chamar adicionar evento
					try {
						desfazer.adicionarEventoAcoes(evento);
						JOptionPane.showMessageDialog(null, "Acao (remover evento) desfeita, ela foi adicionado novamente: "+evento.getIdEvento());
						objetos.remove(tamanho-1);
						acoes.remove(tamanho-1);
						atualizarPaineis();
					} catch (RoomsAllocationException e) {
						JOptionPane.showMessageDialog(null, "Nao existem acoes a serem desfeitas.");
					}
				}
				
				if(acaoCadastrada.equals("Adicionar Alocacao")) {
					alocacao = (Alocacao) objetoCadastrado;
					//chamar remover alocacao
					try {
						desfazer.removerAlocacaoAcoes(alocacao);
						JOptionPane.showMessageDialog(null, "Acao (adicionar alocacao) desfeita, ela foi removida: "+alocacao.getIdSala()+":"+alocacao.getIdEvento());
						objetos.remove(tamanho-1);
						acoes.remove(tamanho-1);
						atualizarPaineis();
					} catch (RoomsAllocationException e) {
						JOptionPane.showMessageDialog(null, "Nao existem acoes a serem desfeitas.");
					}
				}
				
				if(acaoCadastrada.equals("Remover Alocacao")) {
					alocacao = (Alocacao) objetoCadastrado;
					//chamar adicionar alocacao
					try {
						desfazer.adicionarAlocacaoAcoes(alocacao);
						JOptionPane.showMessageDialog(null, "Acao (remover alocacao) desfeita, ela foi adicionada novamente: "+alocacao.getIdSala()+":"+alocacao.getIdEvento());
						objetos.remove(tamanho-1);
						acoes.remove(tamanho-1);
						atualizarPaineis();
					} catch (RoomsAllocationException e) {
						JOptionPane.showMessageDialog(null, "Nao existem acoes a serem desfeitas.");
					}
				}
				
			}
			
			else {
				
				JOptionPane.showMessageDialog(null, "Nao existem acoes a serem desfeitas.");
				
			}
		
	}
	
	private static void atualizarPaineis() {
		JPanelSalas painelSalas = new JPanelSalas();
		painelSalas.montarPainel(janela);
		janela.menus.setComponentAt(0, painelSalas);
		JPanelEventos painelEventos = new JPanelEventos();
		painelEventos.montarPainel(janela);
		janela.menus.setComponentAt(1, painelEventos);
		JPanelAlocacoes painelAlocacoes = new JPanelAlocacoes();
		painelAlocacoes.montarPainel(janela);
		janela.menus.setComponentAt(2, painelAlocacoes);
	}
	
}
