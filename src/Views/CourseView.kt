package Views
import Controllers.CourseController
import Controllers.StudentController
import Helpers.RegistryObject
import Helpers.clearScreen
import Helpers.pressAnyKey
import java.util.*

class CourseView {
    private var courseController = CourseController()
    fun courseMenu(){
        do{
            clearScreen()
            println("1. Crear curso")
            println("2. Agregar estudiante a curso")
            println("3. Agregar evaluacion a curso")
            println("4. Agregar calificacion a estudiante")
            println("5. Mostrar cursos")
            println("6. Mostrar Notas de Cursos")
            println("7. Mostrar Notas de un Estudiante")
            println("8. Salir")
            print("Ingrese una opcion para continuar: ")
            val option = readlnOrNull()?.toIntOrNull()
            when(option){
                1 -> createCourse()
                2 -> addStudentToCourse()
                3 -> addAssessmentToCourse()
                4 -> addQualificationToStudent()
                5 -> showCourses()
                6 -> showQualifications()
                7 -> showStudent()
                8 -> println("Saliendo...")
                else -> println("Opcion no valida")
            }
        }while (option != 8)
    }

    fun createCourse(){
        clearScreen()
        print("Ingrese el nombre del curso: ")
        val name = readlnOrNull().toString()
        print("Ingrese el codigo del curso: ")
        val code = readlnOrNull().toString()
        if(courseController.createCourse(name, code)){
            println("Curso creado con exito")
        }else{
            println("Error al crear el curso")
        }
        pressAnyKey()
    }

    fun addStudentToCourse(){
        clearScreen()
        for (course in courseController.getCourses()){
            println("ID: ${course.id} - Nombre: ${course.name} - Codigo: ${course.code}")
        }

        print("Seleccione el curso por ID: ")
        val userCourseId = readlnOrNull()
        for (student in RegistryObject.students){
            println("ID: ${student.id} - Nombre: ${student.name} ")
        }

        do{
            print("Seleccione el Estudiante a Inscribir por su ID: ")
            val studentId = readlnOrNull().toString()
            val courseId = userCourseId.toString()
            if(courseController.addStudentToCourse(courseId, studentId)){
                println("Estudiante agregado con exito")
            }else{
                println("El estudiante ya esta inscrito en el curso o no existe")
            }
            print("Desea agregar otro estudiante? (Si/No): ")
            val seguir = readlnOrNull()?.trim()?.equals("Si", ignoreCase = true) == true
        }while(seguir)
        pressAnyKey()
    }

    fun addAssessmentToCourse(){
        clearScreen()
        for (course in courseController.getCourses()){
            println("ID: ${course.id} - Nombre: ${course.name} - Codigo: ${course.code}")
        }
        print("Seleccione el curso por ID: ")
        val idInput = readlnOrNull()
        do{
            val courseId = idInput
            print("Ingrese el nombre de la evaluacion: ")
            val assessmentName = readlnOrNull()
            print("Ingrese el porcentaje de la evaluacion (MAX 100 -> Ej: 20): ")
            val percentage = readlnOrNull()?.toDoubleOrNull()

            if (percentage != null && assessmentName != null && courseId != null) {
                if(courseController.addAssessmentToCourse(courseId.toString(), assessmentName.toString(), percentage.toDouble())){
                    println("Evaluacion agregada con exito")
                }else{
                    println("No existe el curso asociado o el porcentaje de evaluaciones supera el 100%")
                }
            }else
            {
                println("No puede agregar una evaluacion sin nombre, porcentaje o curso asociado")
            }
            print("Desea agregar otra Evaluacion? (Si/No): ")
            val seguir = readlnOrNull()?.trim()?.equals("Si", ignoreCase = true) == true
        }while(seguir)
        pressAnyKey()
    }

    fun addQualificationToStudent(){
        clearScreen()
        for (course in courseController.getCourses()){
            println("ID: ${course.id} - Nombre: ${course.name} - Codigo: ${course.code}")
        }
        print("Seleccione el curso por ID: ")
        val courseId = readlnOrNull()
        for (course in courseController.getCourses()){
            if(course.id == courseId){
                for (student in course.students){
                    println("ID: ${student.id} - Nombre: ${student.name}")
                }
            }
        }
        print("Seleccione el Estudiante por ID: ")
        val studentId = readlnOrNull()
        for(course in courseController.getCourses()){
            if(course.id == courseId){
                for(assessment in course.assessments){
                    println("ID: ${assessment.id} - Nombre: ${assessment.name} - Porcentaje: ${assessment.percentage}")
                }
            }
        }
        print("Seleccione la Evaluacion por ID: ")
        val assessmentId = readlnOrNull()
        print("Digite la nota : ")
        val qualification = readlnOrNull()?.toDoubleOrNull()

        if (qualification != null && courseId != null && studentId != null && assessmentId != null) {
            if(courseController.addQualificationToStudent(courseId.toString(), studentId.toString(), assessmentId.toString(), qualification.toDouble())) println("Calificacion agregada con exito") else println("Error al agregar la calificacion")
        }else {
            println("No puede agregar una calificacion sin nota, curso, estudiante o evaluacion asociada")
        }
        pressAnyKey()
    }

    fun showCourses(){
        clearScreen()
        courseController.showCourses()
        pressAnyKey()
    }
    fun showQualifications(){
        clearScreen()
        for (course in courseController.getCourses()){
            println("ID: ${course.id} - Nombre: ${course.name} - Codigo: ${course.code}")
        }
        print("Seleccione el curso por ID: ")
        val courseId = readlnOrNull()
        courseController.showQualifications(courseId.toString())
        pressAnyKey()
    }

    fun showStudent(){ //TODO revisar
        clearScreen()
        StudentView().showStudentHistory()
        pressAnyKey()
    }

}