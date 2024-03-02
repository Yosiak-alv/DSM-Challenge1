package Controllers

import Helpers.RegistryObject
import Helpers.generateRandomCode
import Models.Student

class StudentController {

    fun addStudent(name:String): Boolean {
        if (name.isEmpty()) return false
        RegistryObject.students.add(Student(generateRandomCode(), name))
        return true
    }

    fun addQualificationToStudent(studentId: String, courseId: String, assessmentId: String, qualification: Double): Boolean {
        val student = RegistryObject.students.find { it.id == studentId }
        val course = RegistryObject.courses.find { it.id == courseId }
        val assessment = course?.assessments?.find { it.id == assessmentId }

        if (student != null && course != null && assessment != null) {
            if (student.qualifications.any { it.first == course.name && it.second == assessment.name }) //self update
            {
                student.qualifications.removeIf { it.first == course.name && it.second == assessment.name }
            }
            student.qualifications.add(Triple(course.name, assessment.name, qualification))
            return true
        }
        return false
    }

    fun viewStudents() {
        for (student in RegistryObject.students) {
            println("ID: ${student.id} - Nombre: ${student.name}")
        }
    }

    fun viewStudent(studentId: String, courseId: String):Boolean {
        val student = RegistryObject.students.find { it.id == studentId }
        val course = RegistryObject.courses.find { it.id == courseId }
        val assessments = course?.assessments
        var average = 0.0

        if (course != null && assessments != null && student != null){
            //add assessment to student
            println("-----------------------------------")
            println("Estudiante: ${student.name}")
            for(assessment in assessments) {
                println("-----------------------------------")
                println("Evaluacion: ${assessment.name} - Porcentaje: ${assessment.percentage}")
                for (qualification in student.qualifications) {
                    if (assessment.name == qualification.second) {
                        println("Nota: ${qualification.third}")
                        // average
                        val sum = (qualification.third * assessment.percentage) / 100
                        average += sum
                    }
                }
            }
            println("-----------------------------------")
            println("Promedio: $average")
            println("-----------------------------------")
            return true
        }
        return false
    }

}