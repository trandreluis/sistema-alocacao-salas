package sas.swing.paineis;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import sas.swing.frames.Principal;
import sas.swing.paineis.ouvintes.OuvintePainelAvancado;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JPanelAvancado extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public Principal janelaPrincipal;
	public JButton botaoApagarTodasSalas;
	public JButton botaoApagarTodosEventos;
	public JButton botaoZerarSistema;
	public JButton removerTodaAlocacoes;
	public JCheckBox opcoesMaisAvancadas;
	private OuvintePainelAvancado ouvinte;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblEscolhaComCuidado;
	private JTextField textField;
	
	
	public JPanelAvancado(Principal janela) {
		
		this.janelaPrincipal = janela;
		
		setLayout(null);
		
		ouvinte = new OuvintePainelAvancado(janela, this);
		
		botaoApagarTodasSalas = new JButton("Apagar todas as Salas");
		botaoApagarTodasSalas.setBounds(309, 197, 172, 34);
		botaoApagarTodasSalas.addActionListener(ouvinte);
		add(botaoApagarTodasSalas);
		
		botaoApagarTodosEventos = new JButton("Apagar todos os Eventos");
		botaoApagarTodosEventos.setBounds(309, 112, 172, 34);
		botaoApagarTodosEventos.addActionListener(ouvinte);
		add(botaoApagarTodosEventos);
		
		
		removerTodaAlocacoes = new JButton("Desalocar todos os Eventos");
		removerTodaAlocacoes.setBounds(296, 281, 199, 34);
		removerTodaAlocacoes.addActionListener(ouvinte);
		add(removerTodaAlocacoes);
		
		opcoesMaisAvancadas = new JCheckBox("Exibir opcoes mais avancadas");
		opcoesMaisAvancadas.setBounds(309, 364, 199, 43);
		opcoesMaisAvancadas.addActionListener(ouvinte);
		add(opcoesMaisAvancadas);
		
		botaoZerarSistema = new JButton("Zerar Sistema");
		botaoZerarSistema.setEnabled(false);
		botaoZerarSistema.setVisible(false);
		botaoZerarSistema.addActionListener(ouvinte);
		botaoZerarSistema.setBounds(319, 436, 151, 34);
		add(botaoZerarSistema);
		
		setSize(794, 481);
		
		lblNewLabel = new JLabel("ATENCAO:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(171, 25, 67, 50);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("As alteracoes feitas nessa parte dos sitema nao poderao mais ser desfeitas,");
		lblNewLabel_1.setBounds(248, 33, 427, 34);
		add(lblNewLabel_1);
		
		lblEscolhaComCuidado = new JLabel("escolha com cuidado o que quer.");
		lblEscolhaComCuidado.setBounds(248, 51, 359, 50);
		add(lblEscolhaComCuidado);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFocusable(false);
		textField.setBounds(152, 25, 536, 69);
		add(textField);
		textField.setColumns(10);

	}
}
