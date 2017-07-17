/*
 * Algoritmo: Cuestionario
 * Autor: student9
 */
Algoritmo suma

//Comienzo del algoritmo (Obligatorio)

principal	
var
entero: num,i,suma
inicio
suma<-0
escribir("Introduzca un nœmero")
leer(num)
desde i <- 1 hasta num hacer
si (num mod 2 != 0) entonces
suma<-suma+i
fin_si
fin_desde
escribir(num)
fin_principal

fin_algoritmo
