package sas.swing.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import sas.swing.frames.ouvintes.OuvinteAdicionarSala;
import sas.swing.frames.ouvintes.validadorTextField.IeValidator;

/**
 * 
 * Classe da inteface grafica.
 * Esta classe e responsavel por receber os atributos de uma sala e permitir seu cadastro 
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class AdicionarSala extends JFrame {

	private JPanel panePrincipal;

	public JTextField fieldID;
	public JFormattedTextField fieldCapacidade;
	public JComboBox<String> comboTipo;
	public JComboBox<String> comboFinalidade;
	public JTextField fieldApelido;

	public JCheckBox checkAberta;

	public JButton botaoCadastrarSala;
	public JButton botaoCancelar;

	public JLabel labelDica;
	public Label labelOpcional2;
	public Label labelPrincipal;
	public Label labelApelido;
	public Label labelTipo;
	public Label labelFinalidade;
	private Label labelCapacidade;
	private Label labelID;
	private Label labelOpcional1;
	private Label labelExSala;
	public AdicionarSala adicionar;
	public Principal janelaPrincipal;
	private OuvinteAdicionarSala ouvinte;

	/**
	 * Metodo construtor da janela de Adicionar Sala
	 * @param janelaPrincipal JFrame Principal que chamara esta janela
	 */
	
	public AdicionarSala(Principal janelaPrincipal) {
		
		this.janelaPrincipal = janelaPrincipal;
		
		ouvinte = new OuvinteAdicionarSala(this);
		
		setTitle("Adicionar Sala");
		setBounds(100, 100, 441, 401);
		setResizable(false);
		panePrincipal = new JPanel();
		panePrincipal.setBackground(Color.LIGHT_GRAY);
		panePrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panePrincipal);
		panePrincipal.setLayout(null);

		fieldID = new JTextField();
		fieldID.setToolTipText("O ID da sala deve ser unico e seguir o seguinte padrao (SA-02, SC-77 e etc)");
		fieldID.setBounds(131, 40, 67, 25);
		panePrincipal.add(fieldID);
		fieldID.setColumns(10);


		fieldCapacidade = new JFormattedTextField();
		
		((AbstractDocument) fieldCapacidade.getDocument()).setDocumentFilter(new IeValidator(4));
		
		fieldCapacidade
				.setToolTipText("informe a quantidade de pessoas que esta sala suporta");
		fieldCapacidade.setBounds(131, 76, 36, 29);
		panePrincipal.add(fieldCapacidade);
		fieldCapacidade.setColumns(10);

		comboFinalidade = new JComboBox<>();
		comboFinalidade
				.setToolTipText("informe a finalidade (sala de aula, laboratorio, sala de conferencia e etc)");
		comboFinalidade.setBounds(131, 116, 238, 29);
		comboFinalidade.addActionListener(ouvinte);
		preencherComboFinalidade(comboFinalidade);
		panePrincipal.add(comboFinalidade);

		comboTipo = new JComboBox<>();
		comboTipo
				.setToolTipText("informe o tipo (inteligente, normal, quimica, biologia e etc)");
		comboTipo.setBounds(131, 156, 238, 29);
		preencherComboTipo(comboTipo);
		panePrincipal.add(comboTipo);

		fieldApelido = new JTextField();
		fieldApelido
				.setToolTipText("informe o apelido desta sala (como a sala e mais conhecida)");
		fieldApelido.setBounds(131, 196, 168, 29);
		panePrincipal.add(fieldApelido);
		fieldApelido.setColumns(10);

		checkAberta = new JCheckBox("Aberta?");
		checkAberta.setToolTipText("Por padrao a sala sera fechada");
		checkAberta.setBounds(133, 227, 74, 23);
		panePrincipal.add(checkAberta);

		labelExSala = new Label("(ex.: SA-02)");
		labelExSala.setBounds(210, 40, 74, 22);
		panePrincipal.add(labelExSala);

		labelOpcional1 = new Label("(opcional)");
		labelOpcional1.setBounds(307, 196, 62, 22);
		panePrincipal.add(labelOpcional1);

		labelID = new Label("ID");
		labelID.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelID.setBounds(52, 42, 62, 22);
		panePrincipal.add(labelID);

		labelCapacidade = new Label("Capacidade");
		labelCapacidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelCapacidade.setBounds(52, 83, 73, 22);
		panePrincipal.add(labelCapacidade);

		labelFinalidade = new Label("Finalidade");
		labelFinalidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelFinalidade.setBounds(52, 123, 62, 22);
		panePrincipal.add(labelFinalidade);

		labelTipo = new Label("Tipo");
		labelTipo.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelTipo.setBounds(52, 163, 62, 22);
		panePrincipal.add(labelTipo);

		labelApelido = new Label("Apelido");
		labelApelido.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelApelido.setBounds(52, 203, 62, 22);
		panePrincipal.add(labelApelido);

		labelPrincipal = new Label("Informe os seguintes campos:");
		labelPrincipal.setFont(new Font("Dialog", Font.BOLD, 12));
		labelPrincipal.setBounds(53, 10, 186, 22);
		panePrincipal.add(labelPrincipal);

		labelOpcional2 = new Label("(opcional)");
		labelOpcional2.setBounds(210, 226, 62, 22);
		panePrincipal.add(labelOpcional2);

		labelDica = new JLabel(
				"Obs: Deixe o cursor em cima dos campos e veja dicas *");
		labelDica.setForeground(Color.RED);
		labelDica.setBackground(Color.WHITE);
		labelDica.setBounds(52, 249, 317, 23);
		panePrincipal.add(labelDica);
		
		botaoCadastrarSala = new JButton(new ImageIcon(AdicionarSala.class.getResource("/imagens/ok.png")));
		botaoCadastrarSala.setBorderPainted(false);  
	    botaoCadastrarSala.setContentAreaFilled(false);  
	    botaoCadastrarSala.setFocusPainted(true);
	    botaoCadastrarSala.setToolTipText("Cadastrar Sala");
		botaoCadastrarSala.addActionListener(ouvinte);

		botaoCadastrarSala.setBounds(99, 290, 50, 50);
		panePrincipal.add(botaoCadastrarSala);

		botaoCancelar = new JButton(new ImageIcon(AdicionarSala.class.getResource("/imagens/cancelar.png")));
		botaoCancelar.setBorderPainted(false);  
		botaoCancelar.setContentAreaFilled(false);  
		botaoCancelar.setFocusPainted(true);
		botaoCancelar.setToolTipText("Cancelar e voltar");
		botaoCancelar.addActionListener(ouvinte);
		botaoCancelar.setBounds(251, 290, 118, 50);
		panePrincipal.add(botaoCancelar);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * Metodo responsavel por preencher o JComboBox das finalidades de sala existentes no sistema
	 * @param combo JComboBox das finalidades das salas
	 */
	
	public void preencherComboFinalidade(JComboBox<String> combo) {
		
		combo.addItem("-- selecione a finalidade da sala --");
		
		combo.addItem("Sala de Aula");
		combo.addItem("Sala de conferencia");
		combo.addItem("Laboratorio");
		combo.addItem("Escritorio");
		
	}
	
	/**
	 * Metodo responsavel por preencher o JComboBox dos tipos de sala existentes no sistema
	 * @param combo JComboBox dos tipo das salas
	 */
	
	public void preencherComboTipo(JComboBox<String> combo) {
		
		combo.addItem("-- selecione o tipo da sala --");
		
		combo.addItem("Normal");
		combo.addItem("Inteligente");
		combo.addItem("Videoconferencia");
		combo.addItem("Quimica");
		combo.addItem("Fisica");
		combo.addItem("Biologia");
		combo.addItem("Computacao");

	}
	
}
