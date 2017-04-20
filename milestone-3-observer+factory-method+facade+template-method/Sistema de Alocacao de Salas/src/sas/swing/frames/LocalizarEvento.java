package sas.swing.frames;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sas.swing.frames.ouvintes.OuvinteLocalizarEvento;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * Classe da interface grafica.
 * Classe responsavel por fornecer uma interface grafica que permitira realizar uma busca nos eventos alocados no sistema
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class LocalizarEvento extends JFrame {

	private JPanel contentPane;
	public JComboBox<String> comboAtributos;
	public Principal janelaPrincipal;
	public OuvinteLocalizarEvento ouvinte;
	public JButton botaoBuscar;
	public JButton botaoCancelar;
	public JTextField fieldValor;
	public JLabel comValorDe;
	public JLabel labelAtributoPesquisado;
	private JLabel lblEssaFuncaoSomente;
	
	/**
	 * Metodo construtor da classe Localizar Evento
	 * @param janelaPrincipal Frame principal que chamara esta janela
	 */
	
	public LocalizarEvento(Principal janelaPrincipal) {
		
		this.janelaPrincipal = janelaPrincipal;
		
		setTitle("Localizar Eventos");
		setBounds(100, 100, 450, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ouvinte = new OuvinteLocalizarEvento(this);
		
		comboAtributos = new JComboBox<String>();
		preencherComboBoxAtributos(comboAtributos);
		comboAtributos.addActionListener(ouvinte);
		comboAtributos.setBounds(26, 40, 169, 28);
		contentPane.add(comboAtributos);
		
		botaoBuscar = new JButton();
		botaoBuscar.setIcon(new ImageIcon(LocalizarEvento.class.getResource("/imagens/buscar.png")));
		botaoBuscar.setBorderPainted(false);  
		botaoBuscar.setContentAreaFilled(false);  
		botaoBuscar.setFocusPainted(true);
		botaoBuscar.setToolTipText("Buscar Eventos com essas caracteristicas");
		botaoBuscar.addActionListener(ouvinte);
		botaoBuscar.setBounds(108, 220, 40, 40);
		contentPane.add(botaoBuscar);
		
		botaoCancelar = new JButton();
		botaoCancelar.setIcon(new ImageIcon(LocalizarEvento.class.getResource("/imagens/cancelar.png")));
		botaoCancelar.setBorderPainted(false);  
		botaoCancelar.setContentAreaFilled(false);  
		botaoCancelar.setFocusPainted(true);
		botaoCancelar.setToolTipText("Cancelar e voltar");
		botaoCancelar.addActionListener(ouvinte);
		botaoCancelar.setBounds(288, 215, 50, 50);
		contentPane.add(botaoCancelar);
		
		fieldValor = new JTextField();
		fieldValor.setEnabled(false);
		fieldValor.setBounds(235, 40, 160, 28);
		contentPane.add(fieldValor);
		fieldValor.setColumns(10);
		
		comValorDe = new JLabel("Com valor de");
		comValorDe.setEnabled(false);
		comValorDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		comValorDe.setBounds(271, 11, 104, 29);
		contentPane.add(comValorDe);
		
		labelAtributoPesquisado = new JLabel("Atributo pesquisado");
		labelAtributoPesquisado.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelAtributoPesquisado.setBounds(50, 11, 125, 29);
		contentPane.add(labelAtributoPesquisado);
		
		JLabel lblOProgramaDiferencia = new JLabel("O programa diferencia maiusculas e minusculas*");
		lblOProgramaDiferencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOProgramaDiferencia.setForeground(Color.RED);
		lblOProgramaDiferencia.setBounds(79, 119, 296, 23);
		contentPane.add(lblOProgramaDiferencia);
		
		lblEssaFuncaoSomente = new JLabel("Essa funcao somente realiza buscas de eventos ja alocados*");
		lblEssaFuncaoSomente.setForeground(Color.RED);
		lblEssaFuncaoSomente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEssaFuncaoSomente.setBounds(50, 153, 369, 23);
		contentPane.add(lblEssaFuncaoSomente);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	/**
	 * Metodo responsavel por preencher o comboBox com todos os atributos dos eventos cadastrados no sistema
	 * @param comboBox JComboBox dos atributos dos eventos
	 */
	
	public void preencherComboBoxAtributos(JComboBox<String> comboBox) {
		
		comboBox.addItem("-- selecione o Atributo --");

		comboBox.addItem("Nome");
		comboBox.addItem("Inicio");
		comboBox.addItem("Fim");
		comboBox.addItem("Area");
		comboBox.addItem("Contato");
		comboBox.addItem("Repeticoes");

	}
}
