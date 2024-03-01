package Views

import Helpers.clearScreen

class MainView {

    fun mainMenu(){
        do{
            clearScreen()
            println(" ¿Que Desea Hacer ?")
            println("1. Opciones Curso")
            println("2. Opciones Estudiante")
            println("3. Salir")
            print("Ingrese una opcion para continuar: ")
            val option = readlnOrNull()?.toIntOrNull()
            when(option){
                1 -> CourseView().courseMenu()
                2 -> StudentView().studentMenu()
                3 -> println("Saliendo...")
                else -> println("Opcion no valida")
            }
        }while (option != 3)
    }
}