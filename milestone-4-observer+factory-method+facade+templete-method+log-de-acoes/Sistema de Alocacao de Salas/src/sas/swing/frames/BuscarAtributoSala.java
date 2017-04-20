package sas.swing.frames;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sas.objetos.Sala;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.ouvintes.OuvinteBuscarAtributoSala;
import java.awt.Color;

/**
 * Classe da interface grafica.
 * Classe responsavel por fornecer uma interface grafica que permitira realizar uma busca nos atributos das salas cadastradas
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class BuscarAtributoSala extends JFrame {

	private JPanel contentPane;
	public JTextField labelValorAtributo;
	public JLabel labelAtributo;
	public JComboBox<String> comboID;
	public JComboBox<String> comboAtributo;
	public Principal janelaPrincipal;
	public OuvinteBuscarAtributoSala ouvinte;
	public JButton botaoBuscar;
	public JButton botaoCancelar;
	
	/**
	 * Metodo construtor da classe Buscar Atributo Sala
	 * @param janelaPrincipal Frame principal que chamara esta janela
	 */
	public BuscarAtributoSala(Principal janelaPrincipal) {
		
		this.janelaPrincipal = janelaPrincipal;
		
		setTitle("Buscar Atributo de Salas");
		setResizable(false);
		setBounds(100, 100, 450, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ouvinte = new OuvinteBuscarAtributoSala(this);
		
		comboID = new JComboBox<String>();
		preencherComboBoxIDSala(comboID);
		comboID.addActionListener(ouvinte);
		comboID.setBounds(10, 49, 152, 28);
		contentPane.add(comboID);
		
		comboAtributo = new JComboBox<String>();
		preencherComboBoxAtributos(comboAtributo);
		comboAtributo.addActionListener(ouvinte);
		comboAtributo.setBounds(256, 49, 168, 28);
		contentPane.add(comboAtributo);
		
		labelValorAtributo = new JTextField();
		labelValorAtributo.setFocusable(false);
		labelValorAtributo.setEnabled(true);
		labelValorAtributo.setEditable(false);
		labelValorAtributo.setBounds(125, 128, 299, 28);
		contentPane.add(labelValorAtributo);
		
		labelAtributo = new JLabel("");
		labelAtributo.setText("Atributo:");
		labelAtributo.setBounds(56, 123, 129, 39);
		contentPane.add(labelAtributo);
		
		botaoBuscar = new JButton();
		botaoBuscar.setIcon(new ImageIcon(BuscarAtributoSala.class.getResource("/imagens/buscar.png")));
		botaoBuscar.setBorderPainted(false);  
		botaoBuscar.setContentAreaFilled(false);  
		botaoBuscar.setFocusPainted(true);
		botaoBuscar.setToolTipText("Buscar valor do atributo");
		botaoBuscar.addActionListener(ouvinte);
		botaoBuscar.setBounds(99, 206, 40, 40);
		contentPane.add(botaoBuscar);
		
		botaoCancelar = new JButton();
		botaoCancelar.setIcon(new ImageIcon(BuscarAtributoSala.class.getResource("/imagens/cancelar.png")));
		botaoCancelar.setBorderPainted(false);  
		botaoCancelar.setContentAreaFilled(false);  
		botaoCancelar.setFocusPainted(true);
		botaoCancelar.setToolTipText("Cancelar e voltar");
		botaoCancelar.addActionListener(ouvinte);
		botaoCancelar.setBounds(288, 200, 50, 50);
		contentPane.add(botaoCancelar);
		
		JLabel lblAposSelecionarO = new JLabel("Apos selecionar a sala e o atributo clique no botao pesquisar*");
		lblAposSelecionarO.setForeground(Color.RED);
		lblAposSelecionarO.setBounds(66, 167, 358, 28);
		contentPane.add(lblAposSelecionarO);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	/**
	 * Metodo responsavel por preencher o comboBox com todas as salas cadastradas no sistema
	 * @param comboBox JComboBox das salas
	 */
	public void preencherComboBoxIDSala(JComboBox<String> comboBox) {

		OperacoesSala op = new OperacoesSala();
		ArrayList<Sala> salas = new ArrayList<>();
		salas = op.listarSalas();
		comboBox.addItem("-- selecione o ID --");

		for (Sala s : salas) {
			comboBox.addItem(s.getIdSala());
		}

	}
	
	/**
	 * Metodo responsavel por preencher o comboBox com todos os atributos das sala cadastradas no sistema
	 * @param comboBox JComboBox dos atributos das salas
	 */
	
	public void preencherComboBoxAtributos(JComboBox<String> comboBox) {

		comboBox.addItem("-- selecione o Atributo --");

		comboBox.addItem("Capacidade");
		comboBox.addItem("Finalidade");
		comboBox.addItem("Tipo");
		comboBox.addItem("Apelido");
		comboBox.addItem("Aberta?");
		
	}
}
