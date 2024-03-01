package Models

data class Course(val id: String, val name: String, val code: String,
                  val students : MutableList<Student> = mutableListOf(),
                  val assessments: MutableList<Assessment> = mutableListOf())

