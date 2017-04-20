package sas.swing.paineis;

import javax.swing.JButton;
import javax.swing.JPanel;

import sas.swing.frames.Principal;
import sas.swing.paineis.ouvintes.OuvintePainelDesfazer;

@SuppressWarnings("serial")
public class JPainelDesfazer extends JPanel {
	
	public JButton botaoDesfazer;
	public Principal janela;
	
	public JPainelDesfazer(Principal janela) {
		
		this.janela = janela;
		
		setLayout(null);
		
		botaoDesfazer = new JButton("Desfazer");
		botaoDesfazer.setBounds(341, 192, 110, 30);
		botaoDesfazer.addActionListener(new OuvintePainelDesfazer(this));
		add(botaoDesfazer);

	}
}
