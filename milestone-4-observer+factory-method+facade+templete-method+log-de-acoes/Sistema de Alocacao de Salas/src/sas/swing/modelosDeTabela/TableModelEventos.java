package sas.swing.modelosDeTabela;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sas.objetos.Evento;

/**
 * Modelo de tablea para Eventos
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class TableModelEventos extends AbstractTableModel{

	private ArrayList<Evento> linhas;
	private String[] colunas = new String[]{"ID", "Nome", "Inicio", "Fim", "Area", "Contato", "Repeticoes", "Tipo"};
	private final int id = 0, nome = 1, inicio = 2, fim = 3, area = 4, contato = 5, repeticoes = 6, tipo = 7;
	
	/**
	 * metodo contrutor
	 * @param linhas array de eventos
	 */
	
	public TableModelEventos(ArrayList<Evento> linhas){
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
			
		case nome:
			return String.class;
			
		case inicio:
			return String.class;
			
		case fim:
			return String.class;
			
		case area:
			return String.class;
			
		case contato:
			return String.class;
			
		case repeticoes:
			return int.class;
		case tipo:
			return String.class;
		
		default:
			throw new IndexOutOfBoundsException("Índice da coluna fora dos limites");
			
		}
		
	}
	
	@Override
	public Object getValueAt(int linha, int coluna) {
		Evento evento = linhas.get(linha);
		
		switch (coluna) {
		case id:
			return evento.getIdEvento();
		case nome:
			return evento.getNome();
		case inicio:
			return evento.getInicio();
		case fim:
			return evento.getFim();
		case area:
			return evento.getArea();
		case contato:
			return evento.getContato();
		case repeticoes:
			return evento.getRepeticoes();
		case tipo:
			return evento.getTipoRepeticao();
		default:
			throw new IndexOutOfBoundsException("Indice da coluna fora dos limites");
		}
	}
	
	/**
	 * Metodo que pega um evento
	 * @param indiceLinha linha do evento alvo
	 * @return Evento alvo
	 */
	
	public Evento getEvento(int indiceLinha){
		return linhas.get(indiceLinha);
	}
	
	/**
	 * Metodo que adiciona um evento
	 * @param evento evento recebido
	 */
	
	public void addEvento(Evento evento){
		linhas.add(evento);
		
		int ultimoElemento = getRowCount() - 1;
		
		fireTableRowsInserted(ultimoElemento, ultimoElemento);
	}
	
	/**
	 * metodo que remove uma alocacao
	 * @param indiceLinha indice da remocao
	 */
	
	public void remover(int indiceLinha){
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}