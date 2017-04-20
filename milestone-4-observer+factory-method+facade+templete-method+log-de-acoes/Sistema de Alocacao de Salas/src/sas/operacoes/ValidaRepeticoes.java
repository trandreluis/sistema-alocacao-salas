package sas.operacoes;

import java.util.Calendar;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.excecoes.alocacao.SalaIndisponivelException;
import sas.validacao.ValidacaoAlocacao;

public class ValidaRepeticoes {

	private ValidacaoAlocacao validaAlocacao;
	
	public ValidaRepeticoes(ValidacaoAlocacao validaAlocacao) {
		this.validaAlocacao = validaAlocacao;
	}
	
public void calcularRepeticoes(Calendar inicioRepete, Calendar fimRepete, Calendar inicioNaoRepete, Calendar fimNaoRepete, int repeticoes, String tipo) throws RoomsAllocationException{
		
		Calendar auxInicio = inicioRepete;
		Calendar auxFim = fimRepete;
		
		validaAlocacao.validarConflitoDeHorario(auxInicio, auxFim, inicioNaoRepete, fimNaoRepete);
		
		if(tipo.equalsIgnoreCase("diario")){
			
			for(int i = 1; i <= repeticoes; i++){
				
				auxInicio.add(Calendar.DAY_OF_MONTH, 1);
				auxFim.add(Calendar.DAY_OF_MONTH, 1);
				
				if(auxInicio.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
					auxInicio.add(Calendar.DAY_OF_MONTH, 1);
					auxFim.add(Calendar.DAY_OF_MONTH, 1);
				}
				
				if((auxInicio.get(Calendar.DAY_OF_MONTH) == inicioNaoRepete.get(Calendar.DAY_OF_MONTH)) && 
						(auxInicio.get(Calendar.MONTH) == inicioNaoRepete.get(Calendar.MONTH)) &&
						(auxInicio.get(Calendar.YEAR) == inicioNaoRepete.get(Calendar.YEAR)) &&
						(auxInicio.get(Calendar.HOUR) == inicioNaoRepete.get(Calendar.HOUR)) &&
						(auxInicio.get(Calendar.MINUTE) == inicioNaoRepete.get(Calendar.MINUTE))){
					throw new SalaIndisponivelException("A sala nao esta disponivel neste horario.");
				}
				
				else{
					validaAlocacao.validarConflitoDeHorario(auxInicio, auxFim, inicioNaoRepete, fimNaoRepete);
				}
			}
		}
		
		else if(tipo.equalsIgnoreCase("semanal")){
			
			for(int i= 1; i <= repeticoes; i++){
				
				auxInicio.add(Calendar.DAY_OF_MONTH, 7);
				auxFim.add(Calendar.DAY_OF_MONTH, 7);
				
				if(auxInicio.compareTo(inicioNaoRepete) == 0){
					throw new SalaIndisponivelException("A sala nao esta disponivel neste horario.");
				}
				
				else{
					validaAlocacao.validarConflitoDeHorario(auxInicio, auxFim, inicioNaoRepete, fimNaoRepete);
				}
			}
		}

		else if(tipo.equalsIgnoreCase("mensal")){
			
			for(int i = 1; i <= repeticoes; i++){
				
				auxInicio.add(Calendar.MONTH, 1);
				auxFim.add(Calendar.MONTH, 1);
				
				if(auxInicio.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
					auxFim.add(Calendar.DAY_OF_MONTH, 1);
					auxInicio.add(Calendar.DAY_OF_MONTH, 1);
				}
				
				if(auxInicio.compareTo(inicioNaoRepete) == 0){
					throw new SalaIndisponivelException("A sala nao esta disponivel neste horario.");
				}
				
				validaAlocacao.validarConflitoDeHorario(auxInicio, auxFim, inicioNaoRepete, fimNaoRepete);
			}
		}
	}
	
}
