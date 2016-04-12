# Xtext2Sonar
Xtext2Sonar project based on M2T transformation.

## Tutorial (V1.0)

Para poder utilizar la aplicación, en primer lugar, debemos descargar el plug-in encargado de realizar la transformación (Plug-in transformación M2T) y el plug-in encargado de lanzar la ejecución (Plug-in launcher para M2T) proporcionados en la sección de descargas.

Posteriormente, ambos plug-ins deben ser colocados en el directorio "plugins" de Eclipse, como se muestra en la siguiente imagen (Xtext2SonarM2T_1.0.0 y Xtext2SonarM2T_launcher_1.0.0):

[[Archivo:Plugins_Eclipse.png]]

Una vez realizado el paso anterior, abrimos Eclipse y nos dirigimos al fichero .xmi dentro del directorio "src-gen" de nuestro proyecto Xtext y hacemos clic derecho. Seguidamente aparecerán una lista de opciones y debemos elegir la última, en este caso:  Acceleo Model to Text > Generate SonarQube Plugin.

[[Archivo:Opcion_Generate_SonarQube.png]]

Posteriormente, obtendremos 5 nuevos proyectos en nuestro workspace:

[[Archivo:Proyectos_generados.png]]

En un inicio, los proyectos contendrán errores de compilación, ya que no se descargan las dependencias de Maven correctamente, para solucionarlos se debe hacer clic derecho en alguno de ellos y seleccionar la opción: Maven > Update project.

Seguidamente, debemos dirigirnos al workspace y debemos copiar los proyectos terminados en: -plugin, -toolkit, -checks y -squid dentro del proyecto restante.  A continuación, desde la terminal debemos situarnos en el proyecto donde acabamos de realizar la copia de los demás y realizar el siguiente comando: mvn package. Este comando generará un archivo .jar dentro del directorio target del proyecto finalizado en -plugin.

[[Archivo:Plugin_Sonar_Jar.png]]

Posteriormente, debemos copiar el fichero .jar obtenido en el directorio "plugins" de SonarQube.

[[Archivo:Directorio_plugins_sonar.png]]

A continuación, desde la terminal debemos lanzar la ejecución del servidor de SonarQube mediante el siguiente comando: sonar start. Y seguidamente, debemos ejecutar la orden "sonar-runner" en el directorio del proyecto que queremos analizar. En este caso, analizaremos un fichero del plugin correspondiente al DSL que se ha utilizado como prueba para la transformación.

Finalmente, accediendo a localhost:9000, podremos visualizar la interfaz de la web de SonarQube y observaremos el proyecto analizado:

[[Archivo:Sonar_interfaz.png]]

[[Archivo:Sonar_analisis_fichero.png]]


## Descargas


[https://www.assembla.com/spaces/cplie/documents/cTkgZqXp8r5yoXacwqjQXA/download/cTkgZqXp8r5yoXacwqjQXA Plug-in transformación M2T - (Faltan pruebas)]

[https://www.assembla.com/spaces/cplie/documents/cVDHAAXp8r5zZcdmr6CpXy/download/cVDHAAXp8r5zZcdmr6CpXy Plug-in launcher para M2T - (Con actualización manual).]
