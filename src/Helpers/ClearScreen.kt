package Helpers

fun clearScreen() {
    println("\n".repeat(50))
    if (System.getProperty("os.name").contains("Windows")) {
        ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor()
    } else {
        Runtime.getRuntime().exec("clear")
    }
}