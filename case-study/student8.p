/*
 * Algoritmo: Ejemplo
 * Autor: student8
 */
Algoritmo suma

//Secci—n para importar m—dulos
importa
fin_importa

//Secci—n de definici—n de variables constantes
const 

//Secci—n de definici—n de tipos
tipo

//Secci—n de declaraci—n de variables globales	
var

//Secci—n de definici—n de subalgoritmos: funciones y procedimientos	


//Comienzo del algoritmo (Obligatorio)
principal	
var 
entero: n, cont, suma, a
inicio
suma <- 0
cont <- 0
repetir 
escribir("Introduzca el valor de la variable n:")
leer(n)
hasta_que (n > 0)
a <- 1
suma <- a
n <- n-1
mientras(cont < n)
hacer
	a <- a + 2
	suma <- suma + a
	cont <- cont + 1
fin_mientras
escribir("La suma es ",suma)
  	
fin_principal

fin_algoritmo
