
Algoritmo test

principal
var 
	entero: n,suma,i
inicio
escribir("Introduce un numero")
leer(n)
suma<-0
i<-1
mientras (i<n) hacer
	si(i mod 2!=0) entonces
		suma<- suma+i
	fin_si
	i<-i+1
fin_mientras
escribir(suma)
fin_principal
fin_algoritmo