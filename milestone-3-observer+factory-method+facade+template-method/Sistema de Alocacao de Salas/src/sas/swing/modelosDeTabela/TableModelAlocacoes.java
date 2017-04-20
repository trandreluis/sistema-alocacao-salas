package sas.swing.modelosDeTabela;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sas.objetos.Alocacao;

/**
 * Modelo de tabela para Alocacoes
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class TableModelAlocacoes extends AbstractTableModel{

	private ArrayList<Alocacao> linhas;
	private String[] colunas = new String[]{"ID - Sala", "ID - Evento"};
	private final int idSala = 0, idEvento = 1;
	
	/**
	 * Modelo
	 * @param linhas quantidade de linhas
	 */
	
	public TableModelAlocacoes(ArrayList<Alocacao> linhas){
		this.linhas = linhas;
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public Class<?> getColumnClass(int indiceColuna) {
		
		switch(indiceColuna){
		
		case idSala:
			return String.class;
		
		case idEvento:
			return String.class;
			
		default:
			throw new IndexOutOfBoundsException("Indice da coluna fora dos limites");
			
		}
		
	}
	
	@Override
	public Object getValueAt(int linha, int coluna) {
		
		Alocacao alocacao = linhas.get(linha);
		
		switch (coluna) {
		case idSala:
			return alocacao.getIdSala();
		case idEvento:
			return alocacao.getIdEvento();
		default:
			throw new IndexOutOfBoundsException("Indice da coluna fora dos limites");
		}
	}
	
	/**
	 * metodo que pegara a alocacao selecionada
	 * @param indiceLinha indice da linha
	 * @return alocacao selecionada
	 */
	
	public Alocacao getAlocacao(int indiceLinha){
		return linhas.get(indiceLinha);
	}
	
	/**
	 * metodo para adicionar
	 * @param alocacao Alocacao recebida
	 */
	
	public void addAlocacao(Alocacao alocacao){
		
		linhas.add(alocacao);
		
		int ultimoElemento = getRowCount() - 1;
		
		fireTableRowsInserted(ultimoElemento, ultimoElemento);
	}
	
	/**
	 * metodo que remove
	 * @param indiceLinha indice da remocao
	 */
	
	public void remover(int indiceLinha){
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}