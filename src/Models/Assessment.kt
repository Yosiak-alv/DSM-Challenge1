package Models

data class Assessment(val id:String, val name: String, val percentage: Double, val students: MutableList<Student> = mutableListOf())
