package Controllers
import Models.Course
import Models.Student
import Models.Assessment
import kotlin.random.Random

class CourseController {
    private var courses: MutableList<Course> = mutableListOf()

    fun createCourse(name: String, code:String) : Boolean{
        if (name.isEmpty() || code.isEmpty()) return false
        val newCourse = Course(generateRandomCode(), name, code)
        courses.add(newCourse)
        return true
    }

    fun addStudentToCourse(courseId: String, studentName: String): Boolean {
        val course = courses.find { it.id == courseId }
        if (course != null) {
            val newStudent = Student(generateRandomCode(), studentName)
            course.students.add(newStudent)
            return true
        }
        return false
    }

    fun addAssessmentToCourse(courseId: String, assessmentName: String, percentage: Double): Boolean {
        val course = courses.find { it.id == courseId }
        if (course != null) {
            val newAssessment = Assessment(generateRandomCode(), assessmentName, percentage)
            course.assessments.add(newAssessment)
            return true
        }
        return false
    }

    fun addQualificationToStudent(courseId: String, studentId: String, assessmentId: String, qualification: Double): Boolean {
        val course = courses.find { it.id == courseId }
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
                    return true
                }
            }
        }
        return false
    }

    fun showCourses(){
        //return (courses[0].students)
        for (course in courses){
            println("Curso: ${course.name}")
            println("Codigo: ${course.code}")
            for (student in course.students){
                println("Estudiante: ${student.name}")
            }
            for(assessment in course.assessments){
                println("Evaluacion: ${assessment.name} - Porcentaje: ${assessment.percentage}")
            }
        }
    }

    fun showQualifications(courseId: String){
        val course = courses.find { it.id == courseId }
        // println("Curso: ${course.name}")
        if (course != null) {
            for (student in course.students){
                println("Estudiante: ${student.name}")
                for (qualification in student.qualifications){
                    println("Curso: ${qualification.first} Evaluacion: ${qualification.second} - Nota: ${qualification.third}")
                }
            }
        }

    }

    fun getCourses(): MutableList<Course>{
        return courses
    }

    private fun generateRandomCode(): String {
        val charPool : List<Char> = ('a'..'z') + ('0'..'9') // Define the character pool
        return (1..4)
            .map { Random.nextInt(0, charPool.size) } // Generate random indices
            .map(charPool::get) // Map the indices to characters from the pool
            .joinToString("") // Join the characters to form a string
    }
}