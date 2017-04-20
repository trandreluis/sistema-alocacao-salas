package sas.swing.paineis;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.Principal;
import sas.swing.paineis.ouvintes.OuvintePainel;

/**
 * Aqui se aplica o padrao de projeto Templat Method
 * 
 * Classe de interface que define um modelo para outros paineis
 * @author André Luís
 *
 */

@SuppressWarnings("serial")
public abstract class PaineisCRUD extends JPanel {

	public JTable tabela;
	public JButton botaoAdicionar;
	public JButton botaoRemover;
	public JButton botaoBuscar;
	public JScrollPane scrollTabela;
	public OuvintePainel ouvinte;
	public Principal principal;
	public final int tamanhoBotao = 60;
	public final int posicaoYBotao = 18;
	
	OperacoesSala operacoesSala = new OperacoesSala();
	OperacoesEvento operacoesEvento = new OperacoesEvento();
	OperacoesAlocacao operacoesAlocacao = new OperacoesAlocacao(operacoesSala, operacoesEvento);
	
	public void montarPainel(Principal principal) {
		this.principal = principal;
		inicializarOuvinte();
		adicionarBotoes();
		inicializarTabela();
		inicializarScroll();
		setLayout(null);
	}
	
	public void inicializarScroll() {
		scrollTabela = new JScrollPane(tabela);
		scrollTabela.setBounds(0, 90, 794, 399);
		add(scrollTabela);
	}
	
	public void adicionarBotoes() {
		botaoAdicionar = new JButton();
		botaoAdicionar.setIcon(new ImageIcon(PaineisCRUD.class.getResource("/imagens/adicionar.png")));
		botaoAdicionar.setBounds(200, posicaoYBotao, tamanhoBotao, tamanhoBotao);
		botaoAdicionar.setBorderPainted(false);  
		botaoAdicionar.setContentAreaFilled(false);  
		botaoAdicionar.setFocusPainted(true);
		botaoAdicionar.setToolTipText("Cadastrar");
		botaoAdicionar.addActionListener(ouvinte);
		add(botaoAdicionar);
		
		botaoRemover = new JButton();
		botaoRemover.setIcon(new ImageIcon(PaineisCRUD.class.getResource("/imagens/remover.png")));
		botaoRemover.setBounds(350, posicaoYBotao, tamanhoBotao, tamanhoBotao);
		botaoRemover.setBorderPainted(false);  
		botaoRemover.setContentAreaFilled(false);  
		botaoRemover.setFocusPainted(true);
		botaoRemover.setToolTipText("Apagar");
		botaoRemover.addActionListener(ouvinte);
		add(botaoRemover);	
		
		botaoBuscar = new JButton();
		botaoBuscar.setIcon(new ImageIcon(PaineisCRUD.class.getResource("/imagens/buscar.png")));
		botaoBuscar.setBounds(500, posicaoYBotao, tamanhoBotao, tamanhoBotao);
		botaoBuscar.setBorderPainted(false);  
		botaoBuscar.setContentAreaFilled(false);  
		botaoBuscar.setFocusPainted(true);
		botaoBuscar.setToolTipText("Buscar");
		botaoBuscar.addActionListener(ouvinte);
		add(botaoBuscar);
	}
	
	public abstract void inicializarTabela();
	
	public abstract void inicializarOuvinte();

}
