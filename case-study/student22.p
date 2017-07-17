/*
 * Algoritmo: Ejemplo
 * Autor: student22
 */
Algoritmo Suma_de_impares_entre_1_y_n

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
	mientras(mod(i,2)!=0)hacer
		suma<-suma+i
		i<-i+1
	fin_mientras
fin_desde
	
fin_principal

fin_algoritmo
