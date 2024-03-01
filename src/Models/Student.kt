package Models

data class Student(val id: String, val name: String, val qualifications: MutableList<Triple<String,String,Double>> = mutableListOf())
