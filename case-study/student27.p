/*
 * Algoritmo: Ejemplo
 * Autor: student27
 */
Algoritmo Suma

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
	entero: n, i, suma
inicio 	

escribir("Introduce el termino a sumar sus impares:")
leer("n")

suma<-0
desde i<-1 hasta n hacer
	si(mod(i,2)!=0)entonces
		suma<-suma+i
	fin_si
i<-i+1
fin_desde

escribir("La suma de los impares es: %i", suma)
fin_principal

fin_algoritmo
