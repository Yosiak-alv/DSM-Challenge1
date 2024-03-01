package Views

import Controllers.CourseController
import Controllers.StudentController
import Helpers.RegistryObject
import Helpers.clearScreen
import Helpers.pressAnyKey

class StudentView {

    fun studentMenu(){
        do{
            clearScreen()
            println("1. Crear Estudiante")
            println("2. Mostrar Estudiantes")
            println("3. Mostrar Expediente de Estudiante")
            println("4. Salir")
            print("Ingrese una opcion para continuar: ")
            val option = readlnOrNull()?.toIntOrNull()
            when(option){
                1 -> createStudents()
                2 -> showStudents()
                3 -> showStudentHistory()
                4 -> println("Saliendo...")
                else -> println("Opcion no valida")
            }
        }while (option != 4)
    }
    private fun createStudents() {
        clearScreen()
        do{
            print("Ingrese el nombre del Estudiante: ")
            val name = readlnOrNull().toString()

            if(StudentController().addStudent(name)){
                println("Estudiante agregado con exito")
            }else{
                println("Error al agregar el Estudiante")
            }
            print("Desea agregar otro Estudiante? (Si/No): ")
            val seguir = readlnOrNull()?.trim()?.equals("Si", ignoreCase = true) == true
        }while(seguir)
    }

    private fun showStudents() {
        clearScreen()
        StudentController().viewStudents()
        pressAnyKey()
    }
    private fun showStudentHistory() {
        clearScreen()
        StudentController().viewStudents()
        print("Seleccione el Estudiante por su ID: ")
        val userStudentId = readlnOrNull()
        val student = RegistryObject.students.find { it.id == userStudentId }
        if (student != null) {
            for (course in student.courses){
                println("ID: ${course.id} - Nombre: ${course.name} - Codigo: ${course.code}")
            }
        }
        print("Seleccione el curso por ID: ")
        val courseId = readlnOrNull()
        StudentController().viewStudent(userStudentId.toString(),courseId.toString())
        pressAnyKey()
    }
}