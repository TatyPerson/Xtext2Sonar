/*
 * Algoritmo: Ejemplo
 * Autor: student16
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
 entero: n,i,suma,cont
 real: media
inicio 	
	suma <- 0
	media <- 0
	cont <- 0
	escribir("Introduce el numero deseado")
	leer(n)
	
	desde i<-2 hasta i==n-1 hacer
		si (i mod 2 == 1) entonces
			suma <- suma + i	
			cont <- cont + 1
		fin_si
	fin_desde
	
	media <- suma/cont
	
	escribir("La media es: ",media)
	
fin_principal

fin_algoritmo
