import challenges.Day01
import challenges.Inputs

fun main() {
    val inputFactory = Inputs()
    val day01 = Day01(inputFactory.getInputDay1())

    println("Day01 P1: ${day01.getPart1()}")
}
