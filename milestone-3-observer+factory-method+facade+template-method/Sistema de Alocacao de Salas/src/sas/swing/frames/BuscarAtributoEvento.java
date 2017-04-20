package sas.swing.frames;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sas.objetos.Evento;
import sas.operacoes.OperacoesEvento;
import sas.swing.frames.ouvintes.OuvinteBuscarAtributoEvento;

import javax.swing.ImageIcon;

/**
 * Classe da interface grafica.
 * Classe responsavel por fornecer uma interface grafica que permitira realizar uma busca nos atributos dos eventos cadastrados
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class BuscarAtributoEvento extends JFrame {

	private JPanel contentPane;
	public JTextField labelValorAtributo;
	public JLabel labelAtributo;
	public JComboBox<String> comboID;
	public JComboBox<String> comboAtributo;
	public Principal janelaPrincipal;
	public OuvinteBuscarAtributoEvento ouvinte;
	public JButton botaoBuscar;
	public JButton botaoCancelar;
	
	/**
	 * Metodo construtor da classe Buscar Atributo Evento
	 * @param janelaPrincipal Frame principal que chamara esta janela
	 */
	
	public BuscarAtributoEvento(Principal janelaPrincipal) {
		
		this.janelaPrincipal = janelaPrincipal;
		
		setTitle("Buscar Atributo de Eventos");
		setBounds(100, 100, 450, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ouvinte = new OuvinteBuscarAtributoEvento(this);
		
		comboID = new JComboBox<String>();
		preencherComboBoxIDEvento(comboID);
		comboID.addActionListener(ouvinte);
		comboID.setBounds(10, 49, 152, 28);
		contentPane.add(comboID);
		
		comboAtributo = new JComboBox<String>();
		preencherComboBoxAtributos(comboAtributo);
		comboAtributo.addActionListener(ouvinte);
		comboAtributo.setBounds(256, 49, 168, 28);
		contentPane.add(comboAtributo);
		
		labelValorAtributo = new JTextField();
		labelValorAtributo.setEnabled(true);
		labelValorAtributo.setEditable(false);
		labelValorAtributo.setBounds(131, 128, 293, 28);
		contentPane.add(labelValorAtributo);
		
		labelAtributo = new JLabel("");
		labelAtributo.setText("Atributo:");
		labelAtributo.setBounds(61, 123, 129, 39);
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
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	/**
	 * Metodo responsavel por preencher o comboBox com todos os eventos cadastrados no sistema
	 * @param comboBox JComboBox dos eventos
	 */
	
	public void preencherComboBoxIDEvento(JComboBox<String> comboBox) {

		OperacoesEvento op = new OperacoesEvento();
		ArrayList<Evento> eventos = new ArrayList<>();
		eventos = op.listarEventos();
		comboBox.addItem("-- selecione o ID --");

		for (Evento e : eventos) {
			comboBox.addItem(e.getIdEvento());
		}

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
