package Controllers
import Helpers.RegistryObject
import Helpers.generateRandomCode
import Models.Course
import Models.Student
import Models.Assessment
import kotlin.random.Random

class CourseController {
    //private var courses: MutableList<Course> = mutableListOf()

    fun createCourse(name: String, code:String) : Boolean{
        if (name.isEmpty() || code.isEmpty()) return false
        val newCourse = Course(generateRandomCode(), name, code)
        RegistryObject.courses.add(newCourse)
        return true
    }

    fun addStudentToCourse(courseId: String, studentId: String): Boolean { //TODO Error cuando no se selecciona curso en el do while
        val course = RegistryObject.courses.find { it.id == courseId }
        val selectedStudent = RegistryObject.students.find { it.id == studentId }
        if (course != null && selectedStudent != null) {
            if(course.students.contains(selectedStudent)){
                return false
            }
            selectedStudent.courses.add(course)
            course.students.add(selectedStudent)
            return true
        }
        return false
    }

    fun addAssessmentToCourse(courseId: String, assessmentName: String, percentage: Double): Boolean { //TODO Error cuando no se selecciona curso en el do while
        val course = RegistryObject.courses.find { it.id == courseId }
        if (course != null && percentage in 0.0..100.0) {
            //check all assessment sum is less than 100
            val sum = course.assessments.sumOf { it.percentage }
            if (sum + percentage > 100) return false
            val newAssessment = Assessment(generateRandomCode(), assessmentName, percentage)
            course.assessments.add(newAssessment)
            return true
        }
        return false
    }

    fun addQualificationToStudent(courseId: String, studentId: String, assessmentId: String, qualification: Double): Boolean {
        val course = RegistryObject.courses.find { it.id == courseId }
        if (course != null) {
            val student = course.students.find { it.id == studentId }
            if (student != null) {
                val assessment = course.assessments.find { it.id == assessmentId }
                if (assessment != null) {
                    if (student.qualifications.any { it.first == course.name && it.second == assessment.name }) //self update
                    {
                        student.qualifications.removeIf { it.first == course.name && it.second == assessment.name }
                    }
                    student.qualifications.add(Triple(course.name, assessment.name, qualification))
                    StudentController().addQualificationToStudent(studentId, courseId, assessmentId, qualification)
                    return true
                }
            }
        }
        return false
    }

    fun showCourses(){
        //return (courses[0].students)

        for (course in RegistryObject.courses){
            println("-----------------------------------")
            println("Curso: ${course.name}")
            println("Codigo: ${course.code}")
            for (student in course.students){
                println("Estudiante: ${student.name}")
            }
            for(assessment in course.assessments){
                println("Evaluacion: ${assessment.name} - Porcentaje: ${assessment.percentage}")
            }
        }
        println("-----------------------------------")
    }

    fun showQualifications(courseId: String){
        val course = RegistryObject.courses.find { it.id == courseId }
        // println("Curso: ${course.name}")
        if (course != null) {
            for (student in course.students){
                println("-----------------------------------")
                println("Estudiante: ${student.name}")
                for (assessment in course.assessments){
                    for (qualification in student.qualifications){
                        if (qualification.first == course.name && qualification.second == assessment.name){
                            println("Evaluacion: ${qualification.second} - Nota: ${qualification.third}")
                        }
                    }
                }
            }
            println("-----------------------------------")
        }
    }

    fun getCourses(): MutableList<Course>{
        return RegistryObject.courses
    }
}