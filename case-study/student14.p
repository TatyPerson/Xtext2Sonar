
Algoritmo suma_impares
principal
var		
entero: i, x, suma, n
inicio

i<-0
x<-1
suma <- 0

escribir("Introduce el numero de veces de la suma de los numeros impares")
leer(n)
desde i<-0 hasta i==n hacer
suma<-suma+x
x<-x+2
i<-i+1
fin_desde	
fin_principal
fin_algoritmo
