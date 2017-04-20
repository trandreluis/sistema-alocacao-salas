package sas.swing.paineis.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sas.swing.fachada.geral.Acoes;
import sas.swing.paineis.JPainelDesfazer;

public class OuvintePainelDesfazer implements ActionListener {

	private JPainelDesfazer painel;
	
	public OuvintePainelDesfazer(JPainelDesfazer painel) {
		this.painel = painel;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getSource() == painel.botaoDesfazer) {
			Acoes.desfazerAcao();
		}
		
	}

}
