
Algoritmo suma
//Comienzo del algoritmo (Obligatorio)
principal	
var
	entero: n,i,suma
inicio 	
	escribir("Introduzca un entero")
	leer(n)
	i <- 1
	suma <- 0
	mientras (i <- n) hacer
	suma <- suma+i
	i <- i+2
	fin_mientras
	escribir("La suma es ", suma)
	
fin_principal

fin_algoritmo
