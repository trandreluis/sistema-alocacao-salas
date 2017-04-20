package sas.swing.paineis.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.Principal;
import sas.swing.paineis.PaineisCRUD;

public abstract class OuvintePainel implements ActionListener {

	Principal janela;
	PaineisCRUD painel;
	OperacoesSala operacoesSala = new OperacoesSala();
	OperacoesEvento operacoesEvento = new OperacoesEvento();
	OperacoesAlocacao operacoesAlocacao = new OperacoesAlocacao(operacoesSala,
			operacoesEvento);
	
	public OuvintePainel(Principal janela, PaineisCRUD painel) {
		this.janela = janela;
		this.painel = painel;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == painel.botaoAdicionar) {
			botaoAdicionar();
		}
		else if (evento.getSource() == painel.botaoRemover) {
			botaoRemover();
		}
		else if (evento.getSource() == painel.botaoBuscar) {
			botaoBuscar();
		}
	}
	
	public abstract void botaoAdicionar();
	
	public abstract void botaoBuscar();
	
	public abstract void botaoRemover();
	
}
