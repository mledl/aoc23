import challenges.Day01
import challenges.Day02
import challenges.Day03
import challenges.Inputs

fun main() {
    val inputFactory = Inputs()

   /* val day01 = Day01(inputFactory.getInputDay1())
    println("Day01 P1: ${day01.getPart1()}")
    println("Day01 P2: ${day01.getPart2()}")

    val day02 = Day02(inputFactory.getInputDay02())
    println("Day02 P1: ${day02.getPart1()}")
    println("Day02 P2: ${day02.getPart2()}")*/

    val day03 = Day03(inputFactory.getInputDay03())
    // println("Day03 P1: ${day03.getPart1()}")
    println("Day03 P2: ${day03.getPart2()}")
}
