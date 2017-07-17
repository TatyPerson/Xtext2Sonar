/*
 * Algoritmo: Suma de los numeros impares entre 1 y n
 * Autor: student29
 */
Algoritmo suma
principal	
var
entero: n,cont,s
inicio 	
cont <- 0
s <- 0
escribir("Introduzca el valor de n")
leer(n)
mientras (cont<=n) hacer
cont <- cont+1
si (cont mod 2 == 1) entonces
s <- s+cont
fin_si
fin_mientras
escribir("La suma de los numeros impares es",s)
fin_principal

fin_algoritmo
