package sas.swing.modelosDeTabela;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sas.objetos.Sala;

/**
 * Modelo de tabela para tabela de salas
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class TableModelSalas extends AbstractTableModel{

	private ArrayList<Sala> linhas;
	private String[] colunas = new String[]{"ID", "Capacidade", "Finalidade", "Tipo", "Apelido", "Aberta"};
	private final int id = 0, capacidade = 1, finalidade = 2, tipo = 3, apelido = 4, aberta = 5;
	
	/**
	 * metodo construtor do modelo
	 * @param linhas array de salas
	 */
	
	public TableModelSalas(ArrayList<Sala> linhas){
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
		
		case id:
			return String.class;
		
		case aberta:
			return Boolean.class;
			
		case apelido:
			return String.class;
			
		case capacidade:
			return int.class;
			
		case finalidade:
			return String.class;
			
		case tipo:
			return String.class;
		
		default:
			throw new IndexOutOfBoundsException("Indice da coluna fora dos limites");
			
		}
		
	}
	
	@Override
	public Object getValueAt(int linha, int coluna) {
		Sala sala = linhas.get(linha);
		
		switch (coluna) {
		case id:
			return sala.getIdSala();
		case aberta:
			return sala.isAberta();
		case apelido:
			return sala.getApelido();
		case capacidade:
			return sala.getCapacidade();
		case finalidade:
			return sala.getFinalidade();
		case tipo:
			return sala.getTipo();
		default:
			throw new IndexOutOfBoundsException("Indice da coluna fora dos limites");
		}
	}
	
	/**
	 * metodo para pegar uma sala
	 * @param indiceLinha inde da sala
	 * @return sala alvo
	 */
	
	public Sala getSala(int indiceLinha){
		return linhas.get(indiceLinha);
	}
	
	/**
	 * metodo para adicionar uma sala
	 * @param sala sla a ser adicionada
	 */
	
	public void addSala(Sala sala){
		
		linhas.add(sala);
		
		int ultimoElemento = getRowCount() - 1;
		
		fireTableRowsInserted(ultimoElemento, ultimoElemento);
	}
	
	/**
	 * metodo para remover uma sala
	 * @param indiceLinha indice da remocao
	 */
	
	public void remover(int indiceLinha){
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}