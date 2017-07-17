
Algoritmo suma

principal

var entero: suma, i, n

inicio

i <- 1

suma<- 0
escribir("introduzca el ultimo numero")
leer(n)

mientras (i<n ) hacer
	si (i mod 2==1) entonces
     suma<- suma + i

	fin_si
	i<-i+1
fin_mientras

fin_principal

fin_algoritmo