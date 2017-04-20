package sas.swing.frames;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;

import sas.swing.frames.ouvintes.OuvinteAdicionarEvento;
import sas.swing.frames.ouvintes.validadorTextField.IeValidator;

import java.awt.Label;

import javax.swing.JCheckBox;

import java.awt.Color;

import javax.swing.JComboBox;

/**
 * 
 * Classe da inteface grafica.
 * Esta classe e responsavel por receber os atributos de uma evento e permitir seu cadastro 
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class AdicionarEvento extends JFrame {
	
	private JPanel contentPane;
	public JTextField fieldID;
	public JTextField fieldNome;
	public JFormattedTextField fieldInicio;
	public JFormattedTextField fieldFim;
	public JTextField fieldArea;
	public JTextField fieldContato;
	public JTextField fieldRepeticoes;
	private MaskFormatter mascara;
	public Principal janelaPrincipal;
	public JButton botaoCadastrarEvento;
	public JButton botaoCancelar;
	public OuvinteAdicionarEvento ouvinte;
	public JCheckBox seRepetira;
	public JLabel labelRepeticoes;
	public JComboBox<String> comboTipoRepeticao;
	public JLabel labelTipo;
	
	/**
	 * Metodo construtor da janela de Adicionar Evento
	 * @param janelaPrincipal JFrame Principal que chamara esta janela
	 */
	
	public AdicionarEvento(Principal janelaPrincipal) {
		
		this.janelaPrincipal = janelaPrincipal;
		
		setTitle("Adicionar Evento");
		setBounds(100, 100, 463, 501);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelID = new JLabel("ID");
		labelID.setBounds(52, 56, 62, 22);
		contentPane.add(labelID);

		JLabel labelNome = new JLabel("Nome");
		labelNome.setBounds(52, 89, 62, 22);
		contentPane.add(labelNome);

		JLabel labelInicio = new JLabel("Inicio");
		labelInicio.setBounds(52, 145, 62, 22);
		contentPane.add(labelInicio);

		JLabel labelFim = new JLabel("Fim");
		labelFim.setBounds(52, 189, 62, 22);
		contentPane.add(labelFim);

		JLabel labelArea = new JLabel("Area");
		labelArea.setBounds(52, 222, 62, 22);
		contentPane.add(labelArea);

		JLabel labelContato = new JLabel("Contato");
		labelContato.setBounds(52, 255, 62, 22);
		contentPane.add(labelContato);

		labelRepeticoes = new JLabel("Repeticoes");
		labelRepeticoes.setEnabled(false);
		labelRepeticoes.setBounds(52, 303, 71, 22);
		contentPane.add(labelRepeticoes);

		fieldID = new JTextField();
		fieldID.setBounds(124, 54, 62, 28);
		contentPane.add(fieldID);
		fieldID.setColumns(10);

		fieldNome = new JTextField();
		fieldNome.setColumns(10);
		fieldNome.setBounds(124, 87, 222, 28);
		contentPane.add(fieldNome);

		try {
			mascara = new MaskFormatter("##/##/#### ##:##");
			mascara.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Ocorreu um erro durante a conversao da data!\nPor favor, verifique a data informada");
		}

		fieldInicio = new JFormattedTextField(mascara);

		fieldInicio.setBounds(124, 144, 127, 28);

		contentPane.add(fieldInicio);

		fieldFim = new JFormattedTextField(mascara);

		fieldFim.setBounds(124, 190, 127, 28);

		contentPane.add(fieldFim);

		fieldArea = new JTextField();
		fieldArea.setColumns(10);
		fieldArea.setBounds(124, 222, 189, 28);
		contentPane.add(fieldArea);

		fieldContato = new JTextField();
		fieldContato.setColumns(10);
		fieldContato.setBounds(124, 253, 222, 28);
		contentPane.add(fieldContato);

		fieldRepeticoes = new JTextField();
		((AbstractDocument) fieldRepeticoes.getDocument()).setDocumentFilter(new IeValidator(4));
		fieldRepeticoes.setEnabled(false);
		fieldRepeticoes.setColumns(10);
		fieldRepeticoes.setBounds(124, 300, 45, 28);
		contentPane.add(fieldRepeticoes);

		JLabel lblInformeOsSeguintes = new JLabel(
				"Informe os seguintes campos:");
		lblInformeOsSeguintes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformeOsSeguintes.setBounds(52, 11, 199, 25);
		contentPane.add(lblInformeOsSeguintes);

		JLabel lblData = new JLabel("data e hora");
		lblData.setBounds(148, 169, 81, 20);
		contentPane.add(lblData);

		JLabel lblDataEHora = new JLabel("data e hora");
		lblDataEHora.setBounds(148, 122, 81, 20);
		contentPane.add(lblDataEHora);
		
		ouvinte = new OuvinteAdicionarEvento(this);

		botaoCadastrarEvento = new JButton(new ImageIcon(AdicionarEvento.class.getResource("/imagens/ok.png")));
		botaoCadastrarEvento.setBounds(124, 399, 50, 50);
		botaoCadastrarEvento.setBorderPainted(false);  
		botaoCadastrarEvento.setContentAreaFilled(false);  
		botaoCadastrarEvento.setFocusPainted(true);
		botaoCadastrarEvento.setToolTipText("Cadastrar evento");
		botaoCadastrarEvento.addActionListener(ouvinte);
		contentPane.add(botaoCadastrarEvento);

		botaoCancelar = new JButton(new ImageIcon(AdicionarEvento.class.getResource("/imagens/cancelar.png")));
		botaoCancelar.setBounds(296, 399, 50, 50);
		botaoCancelar.setBorderPainted(false);  
		botaoCancelar.setContentAreaFilled(false);  
		botaoCancelar.setFocusPainted(true);
		botaoCancelar.setToolTipText("Cancelar e voltar");
		botaoCancelar.addActionListener(ouvinte);
		contentPane.add(botaoCancelar);

		JLabel lblEx = new JLabel("ex: (25/10/1996 12:55)");
		lblEx.setBounds(272, 145, 138, 23);
		contentPane.add(lblEx);

		JLabel lblEx_1 = new JLabel("ex: (01/04/2002 15:13)");
		lblEx_1.setBounds(272, 188, 138, 25);
		contentPane.add(lblEx_1);
		
		Label label = new Label("(ex.: EV-08)");
		label.setBounds(200, 56, 74, 22);
		contentPane.add(label);
		
		seRepetira = new JCheckBox("O evento se repetira?");
		seRepetira.setBounds(200, 303, 146, 23);
		seRepetira.addActionListener(ouvinte);
		contentPane.add(seRepetira);
		
		comboTipoRepeticao = new JComboBox<String>();
		comboTipoRepeticao.setEnabled(false);
		comboTipoRepeticao.setBounds(124, 346, 222, 28);
		preencherComboTipo(comboTipoRepeticao);
		contentPane.add(comboTipoRepeticao);
		
		labelTipo = new JLabel("Tipo");
		labelTipo.setEnabled(false);
		labelTipo.setBounds(52, 351, 62, 18);
		contentPane.add(labelTipo);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	/**
	 * Metodo responsavel por preencher o JComboBox dos tipos de repeticoes
	 * @param comboTipo JComboBox que sera preenchido
	 */
	
	public void preencherComboTipo(JComboBox<String> comboTipo) {
		comboTipoRepeticao.addItem("--- selecione o tipo de repeticao ---");
		comboTipoRepeticao.addItem("Diario");
		comboTipoRepeticao.addItem("Semanal");
		comboTipoRepeticao.addItem("Mensal");
	}
	
}
