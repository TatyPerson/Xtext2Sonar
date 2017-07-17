/*
 * Algoritmo: Cuestionario2
 * Autor: student30
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
	entero: n,suma,i
inicio 	
	repetir
	escribir("introduzca un valor de n: ")
	leer(n)
	hasta_que (n>1)
	suma<-0
	i<-1
	mientras(i<=n)hacer
		suma<-suma+i
		i<-i+2
	fin_mientras
	escribir("la suma de los impares desde 1 hasta",n,"es",suma)
	
fin_principal

fin_algoritmo
