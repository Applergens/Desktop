# APPLERGENS
APPlergens (Desktop) es una aplicación para ordenador para que los restaurantes que forman parte de la plataforma APPLERGENS puedan gestionar sus platos.
## Manual de instalación para instalación en ordenador (Windows y Linux) 
Suponemos que Git lo tenemos instalado en nuestro ordenador.
### Como bajar el proyecto de GitHub.
```
git clone https://github.com/Applergens/Desktop.git [nombre de la carpeta que se creará]
```
### Instalamos todo lo necesario
Descargar Eclipse IDE: https://www.eclipse.org/downloads/ (Necesario tener instalado eclipse)

Importamos el proyecto en Eclipse:
```
File > Import > Existing Projects into Workspace

Y seleccionamos la carpeta del proyecto importado.
```
Para poder ejecutar el proyecto, en Eclipse, hacemos lo siguiente:
```
Botón derecho en el proyecto, Run as > Run Configurations > Arguments

WINDOWS:
--module-path="../libraries/javafx-sdk-win-11.0.2/lib" --add-modules=javafx.controls,javafx.fxml

LINUX:
--module-path="../libraries/javafx-sdk-linux-11.0.2/lib" --add-modules=javafx.controls,javafx.fxml
```
Ahora ya podremos ejecutar el proyecto en Eclipse con total normalidad.
