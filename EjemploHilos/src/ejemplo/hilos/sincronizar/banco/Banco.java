package ejemplo.hilos.sincronizar.banco;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
	
	private final double[] cuentas;
	
	private ReentrantLock bloqueBanco = new ReentrantLock();
	
	private Condition saldoSuficiente;
	
	public Banco() {
		
		//Creamos 100 cuentas
		cuentas= new double[100];
		
		//Cada cuenta con 2000�
		for (int i = 0; i < cuentas.length; i++) {
			cuentas[i] = 2000;
		}
		
		//Al instanciar el banco ya establecemos la condici�n de bloque en las transferencias
		//saldoSuficiente = bloqueBanco.newCondition();
		
	}
	
	public void transferencia2(int cuentaOrigen, int cuentaDestino, double cantidad) throws InterruptedException {
		
		bloqueBanco.lock();
		
		try {
			/*if (cuentas[cuentaOrigen] >= cantidad) {
				
				System.out.println("---------------- CANTIDAD OK: " + cuentaOrigen);
				
				cuentas[cuentaOrigen] -= cantidad;
				cuentas[cuentaDestino] += cantidad;
				
				System.out.println("Transferencia en el " + Thread.currentThread());
				System.out.printf("%10.2f de %d para %d", cantidad, cuentaOrigen, cuentaDestino);
				System.out.printf(" Saldo total: %10.2f%n", getSaldoTotal());
				
			} else {
				System.out.println("---------------- CANTIDAD INSUFICIENTE: " + cuentaOrigen +  " .....SALDO: " + cuentas[cuentaOrigen] + " ......CANTIDAD: " + cantidad);
			}*/
			
			while (cuentas[cuentaOrigen] < cantidad ) {
				//Si no se cumple la condici�n ponemos el hilo a la espera y adem�s libera el c�digo sin necesidad de hacer el unlock
				saldoSuficiente.await();
			}
			
			System.out.println("---------------- CANTIDAD OK: " + cuentaOrigen);
			
			cuentas[cuentaOrigen] -= cantidad;
			cuentas[cuentaDestino] += cantidad;
			
			System.out.println("Transferencia en el " + Thread.currentThread());
			System.out.printf("%10.2f de %d para %d", cantidad, cuentaOrigen, cuentaDestino);
			System.out.printf(" Saldo total: %10.2f%n", getSaldoTotal());
			
			//Con esta instrucci�n avisamos a los hilos que est�n a la espera para que retomen la l�gica (esto
			//por si acaso un hilo ha realizado algo que satisfaga la condici�n del hilo que est� a la espera).
			saldoSuficiente.signalAll();

		} finally {
			bloqueBanco.unlock();
		}
		
	}
	
	public synchronized void transferencia(int cuentaOrigen, int cuentaDestino, double cantidad) throws InterruptedException {		
			
		while (cuentas[cuentaOrigen] < cantidad ) {
			//Si no se cumple la condici�n ponemos el hilo a la espera y adem�s libera el c�digo
			wait();
		}
		
		System.out.println("---------------- CANTIDAD OK: " + cuentaOrigen);
		
		cuentas[cuentaOrigen] -= cantidad;
		cuentas[cuentaDestino] += cantidad;
		
		System.out.println("Transferencia en el " + Thread.currentThread());
		System.out.printf("%10.2f de %d para %d", cantidad, cuentaOrigen, cuentaDestino);
		System.out.printf(" Saldo total: %10.2f%n", getSaldoTotal());
		
		//Con esta instrucci�n avisamos a los hilos que est�n a la espera para que retomen la l�gica (esto
		//por si acaso un hilo ha realizado algo que satisfaga la condici�n del hilo que est� a la espera).
		notifyAll();
		
	}	
	
	public double getSaldoTotal() {
		
		double saldoTotal = 0;
		
		for (int i = 0; i < cuentas.length; i++) {
			saldoTotal += cuentas[i];
		}
		
		return saldoTotal;
	}
	

}
