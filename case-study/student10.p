/*
 * Algoritmo: Suma Impares
 * Autor: student10
 */
Algoritmo suma_impares

	//Sección para importar módulos
	importa
	fin_importa

	//Sección de definición de variables constantes
	const 

	//Sección de definición de tipos
	tipo

	//Sección de declaración de variables globales	
	var 
		real: suma_impares
	
	//Sección de definición de subalgoritmos: funciones y procedimientos	
	
	
	//Comienzo del algoritmo (Obligatorio)
	principal	
	
		var
			entero: num
			entero: n
		inicio 	
			leer(n)
			
			desde num <-1 hasta num < n hacer num <- num + 1
				si (num mod 2 != 0) entonces
					suma_impares <- suma_impares + num
				fin_si
			fin_desde
			
			escribir(suma_impares)
	fin_principal

fin_algoritmo
