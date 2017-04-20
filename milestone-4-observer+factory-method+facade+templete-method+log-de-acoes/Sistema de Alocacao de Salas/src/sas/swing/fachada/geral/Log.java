package sas.swing.fachada.geral;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Log {

	private static String arquivoDeLog = "log-de-acoes-sas.log";
	private static FileWriter log;
	private static PrintWriter manipuladorDeGravacao;

	public static void inicializarLog() {

		if (!verificaExistenciaLog()) {

			try {

				log = new FileWriter("log-de-acoes-sas.log");

				manipuladorDeGravacao = new PrintWriter(log);

				manipuladorDeGravacao
						.printf("Data e Hora: "
								+ pegarDataHoraAtual()
								+ " - Log de acoes no Sistema de Alocacao de Salas%n%n");
				log.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Falha ao criar o log.");
			}
		}

	}

	public static void gravarNoLog(String valor) {

		if(verificaExistenciaLog()) {
			try {
				
				log = new FileWriter("log-de-acoes-sas.log", true);
				
				manipuladorDeGravacao = new PrintWriter(log);
				manipuladorDeGravacao.printf("Data e Hora: " + pegarDataHoraAtual()
						+ " - " + valor + "%n");
				log.close();
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Falha ao fechar o log.");
			}
			
		}
		
		else  {
			inicializarLog();
			gravarNoLog(valor);
		}

	}

	private static boolean verificaExistenciaLog() {
		if (new File(arquivoDeLog).exists()) {
			return true;
		} else {
			return false;
		}
	}

	private static String pegarDataHoraAtual() {
		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date data = new Date();
		return formatoData.format(data);
	}

}
