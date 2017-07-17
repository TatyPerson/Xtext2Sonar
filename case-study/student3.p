/*
 * Algoritmo: Suma de los n términos impares
 * Autor: student3
 */
Algoritmo suma_impares

//Sección para importar módulos
importa
fin_importa

//Sección de definición de variables constantes
const 

//Sección de definición de tipos
tipo

//Sección de declaración de variables globales	
var

//Sección de definición de subalgoritmos: funciones y procedimientos	


//Comienzo del algoritmo (Obligatorio)
principal	
var
	entero: impar, n, suma
inicio 	

	suma <- 0
	
	impar <- 1
	
	
	repetir
	escribir("Introduzca un número mayor que 0")
	leer(n)
	hasta_que(n>0)
	
	mientras (n>=impar) hacer
	
		suma <- suma + impar
	  	impar <- impar + 2
	
	fin_mientras
	escribir(suma)

fin_principal
fin_algoritmo
