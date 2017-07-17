/*
 * Algoritmo:Realiza un programa con Vary que escriba la suma de los números impares entre 1 y n, siendo n un número introducido por el usuario. 
 * Adjunta el fichero main.p del proyecto en Vary a esta pregunta. Si necesitas explicar algo usa el espacio disponible abajo.
 * 
 * Autor: student31
 */
Algoritmo suma_impares
principal
	var
		entero: num,sum,i
	inicio
		repetir
			escribir("Escriba un numero mayor que 1")
			leer(num)
		hasta_que(num>1)
		

		desde i<-1 hasta num hacer
			si(i mod 2!=0)entonces
				sum<-sum+i
			fin_si
		fin_desde
		
		escribir("La suma de los numero impares es",sum)
	fin_principal
fin_algoritmo


		

