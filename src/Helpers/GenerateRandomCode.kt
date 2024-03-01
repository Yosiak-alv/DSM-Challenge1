package Helpers

import kotlin.random.Random

fun generateRandomCode(): String {
    val charPool : List<Char> = ('a'..'z') + ('0'..'9') // Define the character pool
    return (1..4)
        .map { Random.nextInt(0, charPool.size) } // Generate random indices
        .map(charPool::get) // Map the indices to characters from the pool
        .joinToString("") // Join the characters to form a string
}