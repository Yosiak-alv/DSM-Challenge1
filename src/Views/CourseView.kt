package Views
import Controllers.CourseController
import java.util.*

class CourseView {
    private var courseController = CourseController()
    fun optionsMenu(){
        println(" ¿Que Desea Hacer ?")
        do{
            println("1. Crear curso")
            println("2. Agregar estudiante a curso")
            println("3. Agregar evaluacion a curso")
            println("4. Agregar calificacion a estudiante")
            println("5. Mostrar cursos")
            println("6. Mostrar Notas de Cursos")
            println("7. Salir")
            print("Ingrese una opcion para continuar: ")
            val option = readlnOrNull()?.toIntOrNull()
            when(option){
                1 -> createCourse()
                2 -> addStudentToCourse()
                3 -> addAssessmentToCourse()
                4 -> addQualificationToStudent()
                5 -> showCourses()
                6 -> showQualifications()
                7 -> println("Saliendo...")
                else -> println("Opcion no valida")
            }
        }while (option != 7)
    }

    fun createCourse(){
        print("Ingrese el nombre del curso: ")
        val name = readlnOrNull()
        print("Ingrese el codigo del curso: ")
        val code = readlnOrNull()
        if(courseController.createCourse(name!!, code!!)){
            println("Curso creado con exito")
        }else{
            println("Error al crear el curso")
        }
    }

    fun addStudentToCourse(){
        for (course in courseController.getCourses()){
            println("ID: ${course.id} - Nombre: ${course.name} - Codigo: ${course.code}")
        }

        print("Seleccione el curso por ID: ")
        val idInput = readlnOrNull()
        do{
            val courseId = idInput
            print("Ingrese el nombre del estudiante: ")
            val studentName = readlnOrNull()

            if(courseController.addStudentToCourse(courseId!!, studentName!!)){
                println("Estudiante agregado con exito")
            }else{
                println("Error al agregar el estudiante")
            }
            print("Desea agregar otro estudiante? (Si/No): ")
            val seguir = readlnOrNull()?.trim()?.equals("Si", ignoreCase = true) == true
        }while(seguir)
    }

    fun addAssessmentToCourse(){
        for (course in courseController.getCourses()){
            println("ID: ${course.id} - Nombre: ${course.name} - Codigo: ${course.code}")
        }
        print("Seleccione el curso por ID: ")
        val idInput = readlnOrNull()
        do{
            val courseId = idInput
            print("Ingrese el nombre de la evaluacion: ")
            val assessmentName = readlnOrNull()
            print("Ingrese el porcentaje de la evaluacion: ")
            val percentage = readlnOrNull()?.toDoubleOrNull()

            if(courseController.addAssessmentToCourse(courseId!!, assessmentName!!, percentage!!)){
                println("Evaluacion agregada con exito")
            }else{
                println("Error al agregar la evaluacion")
            }
            print("Desea agregar otra Evaluacion? (Si/No): ")
            val seguir = readlnOrNull()?.trim()?.equals("Si", ignoreCase = true) == true
        }while(seguir)
    }

    fun addQualificationToStudent(){
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

        if(courseController.addQualificationToStudent(courseId!!, studentId!!, assessmentId!!, qualification!!)) println("Calificacion agregada con exito") else println("Error al agregar la calificacion")
    }

    fun showCourses(){
        courseController.showCourses()
    }
    fun showQualifications(){
        for (course in courseController.getCourses()){
            println("ID: ${course.id} - Nombre: ${course.name} - Codigo: ${course.code}")
        }
        print("Seleccione el curso por ID: ")
        val courseId = readlnOrNull()
        courseController.showQualifications(courseId!!)
    }

}