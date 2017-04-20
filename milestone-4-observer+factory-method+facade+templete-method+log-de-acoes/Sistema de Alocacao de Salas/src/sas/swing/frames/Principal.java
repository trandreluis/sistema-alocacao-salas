package sas.swing.frames;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import sas.inicializar.Sistema;
import sas.swing.fachada.geral.Acoes;
import sas.swing.fachada.geral.FachadaGeralGUI;
import sas.swing.fachada.geral.Log;
import sas.swing.paineis.JPanelAlocacoes;
import sas.swing.paineis.JPanelAvancado;
import sas.swing.paineis.JPanelEventos;
import sas.swing.paineis.JPanelSalas;
import sas.swing.paineis.PaineisCRUD;
import sas.swing.paineis.JPainelDesfazer;

/**
 * Classe principal do sistema, responsavel por fazer as chamadas de inicializacao do banco e por conter todos os paineis
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class Principal extends JFrame {

	public JTabbedPane menus;
	public PaineisCRUD inicio;
	public JPanelSalas salas;
	public JPanelEventos eventos;
	public JPanelAlocacoes alocacoes;
	public JPanelAvancado avancado;
	public FachadaGeralGUI fachada = new FachadaGeralGUI(this);
	
	//teste//
	public Acoes acoes = new Acoes(fachada);
	public JPainelDesfazer painelDesfazer;
	
	
	/**
	 * Metodo construtor da janela Principal
	 */
	
	public Principal() {

		setTitle("Sistema de Alocacao de Salas");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		adicionarMenus();

	}

	/**
	 * Metodo responsavel por adicionar os JPanels ao JFrame principal
	 */
	
	public void adicionarJPanels() {
		salas = new JPanelSalas();
		salas.montarPainel(this);

		eventos = new JPanelEventos();
		eventos.montarPainel(this);
		
		alocacoes = new JPanelAlocacoes();
		alocacoes.montarPainel(this);
		
		avancado = new JPanelAvancado(this);
		
		//teste//
		painelDesfazer = new JPainelDesfazer(this);
		
	}
	
	/**
	 * Metodo responsavel por adicionar os JPanels ao JFrame principal
	 */
	
	public void adicionarMenus() {
		adicionarJPanels();
		menus = new JTabbedPane();
		menus.addTab("", new ImageIcon(Principal.class.getResource("/imagens/68s.png")), salas, "Manipular Salas (adicionar, remover e buscar)");
		menus.addTab("", new ImageIcon(Principal.class.getResource("/imagens/68.png")), eventos, "Manipular Eventos (adicionar, remover e buscar)");
		menus.addTab("", new ImageIcon(Principal.class.getResource("/imagens/alo3.png")), alocacoes, "Manipular Alocacoes (adicionar, remover e buscar)");
		menus.addTab("", new ImageIcon(Principal.class.getResource("/imagens/av11.png")), avancado, "Configuracoes avancadas do sistema");
		
		//teste//
		menus.addTab("Desfazer", null, painelDesfazer, null);
		//fim//
		add(menus);
	}
	

	/**
	 * Metodo principal, onde se inicaliza todo o sistema e se define sua aparencia (LookAndFeel)
	 * @param args Argumentos para inicializacao
	 */
	
	public static void main(String args[]) {
		
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Sistema.inicializarBancoInterfaceGrafica();
				Log.inicializarLog();
				Principal a = new Principal();
				a.setLocationRelativeTo(null);
				a.setVisible(true);
			}
		});
	}

}
