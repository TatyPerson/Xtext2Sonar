/*
 * Algoritmo: Suma de n�meros impares hasta N
 * Soluci�n base.
 */
Algoritmo sumaNumerosImpares

principal	
var
	entero: numero,suma,contador
inicio 	
	escribir("Introduzca el n�mero hasta el que desea sumar: ")
	leer(numero)
	
	suma<-0
	
	desde contador <- 1 hasta numero hacer
		//Si el n�mero es impar lo sumamos
		si (contador mod 2 != 0) entonces
			suma<-suma+contador
		fin_si
	fin_desde
	
	escribir("La suma resultante es: ", suma)
	
fin_principal
fin_algoritmo