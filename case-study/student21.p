
Algoritmo sumadeimpares
principal	
var 
entero: n,sum,cont
inicio
escribir("introduce un numero")
leer(n)
cont<-1
sum<-0
mientras(cont<n)hacer
sum<-sum+cont
cont<-cont+2
fin_mientras
escribir("la suma de los impares entre el numero 1 y ",n,"es", sum)
fin_principal
fin_algoritmo