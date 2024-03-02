package Views

import Helpers.clearScreen

class MainView {

    fun mainMenu(){
        do{
            clearScreen()
            println(" ¿Que Desea Hacer ?")
            println("1. Opciones Estudiante")
            println("2. Opciones Curso")
            println("3. Salir")
            print("Ingrese una opcion para continuar: ")
            val option = readlnOrNull()?.toIntOrNull()
            when(option){
                1 -> StudentView().studentMenu()
                2 -> CourseView().courseMenu()
                3 -> println("Saliendo...")
                else -> println("Opcion no valida")
            }
        }while (option != 3)
    }
}