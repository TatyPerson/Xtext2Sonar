/*
 * Algoritmo: Suma de los numeros impares comprendidos entre 1 y n
 * Autor: student6
 */
Algoritmo suma_impar

principal	
var

entero: suma
entero: n
entero: contador

inicio 	
	contador <- 1
	suma <- 0
	n <- 0

	repetir
		escribir("Introduce un numero entero mayor que 1: ")
		leer(n)
	hasta_que(n >= 2)
	
	mientras(contador <= n) hacer
		suma <- suma + contador		
		contador <- contador + 2
	fin_mientras
	
	escribir("Suma: ", suma)
	

fin_principal

fin_algoritmo
