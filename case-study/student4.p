/*
 * Algoritmo: Ejemplo
 * Autor: student4
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
var entero: n
entero: contador
entero: suma
inicio 
contador <- 0
suma <- 0 
escribir("Introduce un numero")
leer(n)
mientras (contador<=n) hacer
si (contador mod 2 != 0) entonces
suma <- suma + contador
fin_si
contador <-contador+1

fin_mientras
escribir(suma)

fin_principal

fin_algoritmo
