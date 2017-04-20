package sas.swing.frames;

import java.awt.Font;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sas.objetos.Evento;
import sas.objetos.Sala;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.ouvintes.OuvinteAdicionarAlocacao;

import java.awt.Color;

/**
 * Classe da inteface grafica.
 * Esta classe e responsavel por permitir a escolha da sala e evento que sera alocado (criando assim, uma nova alocacao) 
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class AdicionarAlocacao extends JFrame {

	private JPanel panePrincipal;

	public JButton botaoCadastrarAlocacao;
	public JButton botaoCancelar;
	public Label labelPrincipal;
	public AdicionarAlocacao adicionar;
	public Principal janelaPrincipal;
	public JTextField fieldCapacidadeSala;
	public JTextField fieldFinalidadeSala;
	public JTextField fieldTipoSala;
	public JTextField fieldApelidoSala;
	public JTextField fieldRepeticoesEvento;
	public JTextField fieldNomeEvento;
	public JTextField fieldInicioEvento;
	public JTextField fieldFimEvento;
	public JTextField fieldAreaEvento;
	public JTextField fieldContatoEvento;
	public JComboBox<String> comboIDSala;
	public JComboBox<String> comboIDEvento;
	public OuvinteAdicionarAlocacao ouvinte;
	private JLabel lblAberta;
	public JTextField fieldIsAbertaSala;


	/**
	 * Metodo construtor da janela de Adicionar Alocacao
	 * @param janelaPrincipal JFrame Principal que chamara esta janela
	 */
	
	public AdicionarAlocacao(Principal janelaPrincipal) {

		this.janelaPrincipal = janelaPrincipal;
		

		setTitle("Alocar Evento em Sala");
		setBounds(100, 100, 727, 477);
		panePrincipal = new JPanel();
		panePrincipal.setBackground(Color.LIGHT_GRAY);
		panePrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panePrincipal);
		panePrincipal.setLayout(null);

		labelPrincipal = new Label("Informe os seguintes campos:");
		labelPrincipal.setFont(new Font("Dialog", Font.BOLD, 12));
		labelPrincipal.setBounds(33, 10, 186, 22);
		panePrincipal.add(labelPrincipal);

		ouvinte = new OuvinteAdicionarAlocacao(this);

		botaoCadastrarAlocacao = new JButton(new ImageIcon(AdicionarAlocacao.class.getResource("/imagens/ok.png")));

		botaoCadastrarAlocacao.setBorderPainted(false);  
		botaoCadastrarAlocacao.setContentAreaFilled(false);  
		botaoCadastrarAlocacao.setFocusPainted(true);
		botaoCadastrarAlocacao.setToolTipText("Cadastrar evento");		

		botaoCadastrarAlocacao.addActionListener(ouvinte);

		botaoCadastrarAlocacao.setBounds(208, 365, 50, 50);
		panePrincipal.add(botaoCadastrarAlocacao);

		botaoCancelar = new JButton(new ImageIcon(AdicionarAlocacao.class.getResource("/imagens/cancelar.png")));
		botaoCancelar.setBorderPainted(false);  
		botaoCancelar.setContentAreaFilled(false);  
		botaoCancelar.setFocusPainted(true);
		botaoCancelar.setToolTipText("Cadastrar evento");
		botaoCancelar.addActionListener(ouvinte);
		botaoCancelar.setBounds(408, 365, 50, 50);
		panePrincipal.add(botaoCancelar);

		comboIDSala = new JComboBox<String>();
		preencherComboBoxSala(comboIDSala);
		
		comboIDSala.addActionListener(ouvinte);

		comboIDSala.setBounds(33, 69, 150, 28);
		panePrincipal.add(comboIDSala);

		Label label = new Label("ID da Sala");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(70, 41, 70, 22);
		panePrincipal.add(label);

		comboIDEvento = new JComboBox<String>();
		preencherComboBoxEvento(comboIDEvento);
		
		comboIDEvento.addActionListener(ouvinte);
		
		comboIDEvento.setBounds(490, 69, 150, 28);
		panePrincipal.add(comboIDEvento);

		Label label_1 = new Label("ID do Evento");
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1.setBounds(530, 41, 89, 22);
		panePrincipal.add(label_1);

		JLabel lblCapacidade = new JLabel("Capacidade:");
		lblCapacidade.setBounds(10, 111, 70, 22);
		panePrincipal.add(lblCapacidade);

		JLabel lblFinalidade = new JLabel("Finalidade:");
		lblFinalidade.setBounds(10, 157, 70, 22);
		panePrincipal.add(lblFinalidade);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 199, 70, 22);
		panePrincipal.add(lblTipo);

		JLabel lblApelido = new JLabel("Apelido:");
		lblApelido.setBounds(10, 238, 70, 22);
		panePrincipal.add(lblApelido);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(388, 108, 70, 22);
		panePrincipal.add(lblNome);

		JLabel lblInicio = new JLabel("Inicio:");
		lblInicio.setBounds(388, 146, 70, 22);
		panePrincipal.add(lblInicio);

		JLabel lblFim = new JLabel("Fim:");
		lblFim.setBounds(388, 188, 70, 22);
		panePrincipal.add(lblFim);

		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(388, 227, 63, 22);
		panePrincipal.add(lblArea);

		JLabel lblContato = new JLabel("Contato:");
		lblContato.setBounds(388, 269, 70, 22);
		panePrincipal.add(lblContato);

		JLabel lblRepeticoes = new JLabel("Repeticoes:");
		lblRepeticoes.setBounds(388, 308, 70, 22);
		panePrincipal.add(lblRepeticoes);

		fieldCapacidadeSala = new JTextField();
		fieldCapacidadeSala.setFocusable(false);
		fieldCapacidadeSala.setEditable(false);
		fieldCapacidadeSala.setBounds(85, 108, 44, 28);
		panePrincipal.add(fieldCapacidadeSala);
		fieldCapacidadeSala.setColumns(10);

		fieldFinalidadeSala = new JTextField();
		fieldFinalidadeSala.setFocusable(false);
		fieldFinalidadeSala.setEditable(false);
		fieldFinalidadeSala.setColumns(10);
		fieldFinalidadeSala.setBounds(85, 154, 219, 28);
		panePrincipal.add(fieldFinalidadeSala);

		fieldTipoSala = new JTextField();
		fieldTipoSala.setFocusable(false);
		fieldTipoSala.setEditable(false);
		fieldTipoSala.setColumns(10);
		fieldTipoSala.setBounds(85, 196, 219, 28);
		panePrincipal.add(fieldTipoSala);

		fieldApelidoSala = new JTextField();
		fieldApelidoSala.setFocusable(false);
		fieldApelidoSala.setEditable(false);
		fieldApelidoSala.setColumns(10);
		fieldApelidoSala.setBounds(85, 235, 253, 28);
		panePrincipal.add(fieldApelidoSala);

		fieldRepeticoesEvento = new JTextField();
		fieldRepeticoesEvento.setFocusable(false);
		fieldRepeticoesEvento.setEditable(false);
		fieldRepeticoesEvento.setColumns(10);
		fieldRepeticoesEvento.setBounds(465, 305, 44, 28);
		panePrincipal.add(fieldRepeticoesEvento);

		fieldNomeEvento = new JTextField();
		fieldNomeEvento.setFocusable(false);
		fieldNomeEvento.setEditable(false);
		fieldNomeEvento.setColumns(10);
		fieldNomeEvento.setBounds(466, 108, 235, 28);
		panePrincipal.add(fieldNomeEvento);

		fieldInicioEvento = new JTextField();
		fieldInicioEvento.setFocusable(false);
		fieldInicioEvento.setEditable(false);
		fieldInicioEvento.setColumns(10);
		fieldInicioEvento.setBounds(466, 146, 130, 28);
		panePrincipal.add(fieldInicioEvento);

		fieldFimEvento = new JTextField();
		fieldFimEvento.setFocusable(false);
		fieldFimEvento.setEditable(false);
		fieldFimEvento.setColumns(10);
		fieldFimEvento.setBounds(466, 185, 130, 28);
		panePrincipal.add(fieldFimEvento);

		fieldAreaEvento = new JTextField();
		fieldAreaEvento.setFocusable(false);
		fieldAreaEvento.setEditable(false);
		fieldAreaEvento.setColumns(10);
		fieldAreaEvento.setBounds(466, 224, 186, 28);
		panePrincipal.add(fieldAreaEvento);

		fieldContatoEvento = new JTextField();
		fieldContatoEvento.setFocusable(false);
		fieldContatoEvento.setEditable(false);
		fieldContatoEvento.setColumns(10);
		fieldContatoEvento.setBounds(466, 266, 203, 28);
		panePrincipal.add(fieldContatoEvento);
		
		lblAberta = new JLabel("Aberta?");
		lblAberta.setBounds(10, 284, 70, 22);
		panePrincipal.add(lblAberta);
		
		fieldIsAbertaSala = new JTextField();
		fieldIsAbertaSala.setFocusable(false);
		fieldIsAbertaSala.setEditable(false);
		fieldIsAbertaSala.setColumns(10);
		fieldIsAbertaSala.setBounds(85, 281, 44, 28);
		panePrincipal.add(fieldIsAbertaSala);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * Metodo responsavel por preencher o JComboBox das salas com as existentes no sistema
	 * @param comboBox JComboBox das salas
	 */
	
	public void preencherComboBoxSala(JComboBox<String> comboBox) {

		OperacoesSala op = new OperacoesSala();
		ArrayList<Sala> salas = new ArrayList<>();
		salas = op.listarSalas();
		comboBox.addItem("-- selecione o ID --");

		for (Sala s : salas) {
			comboBox.addItem(s.getIdSala());
		}

	}

	/**
	 * Metodo responsavel por preencher o JComboBox dos eventos com os existentes no sistema
	 * @param comboBox JComboBox dos eventos
	 */
	
	public void preencherComboBoxEvento(JComboBox<String> comboBox) {

		OperacoesEvento op = new OperacoesEvento();
		ArrayList<Evento> eventos = new ArrayList<>();
		eventos = op.listarEventos();
		comboBox.addItem("-- selecione o ID --");

		for (Evento s : eventos) {
			comboBox.addItem(s.getIdEvento());
		}

	}

}
