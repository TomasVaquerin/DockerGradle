package dev.tomas

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun main() {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    while (true) {
        val currentTime = LocalTime.now().format(formatter)
        println("Hora actual: $currentTime")
        Thread.sleep(1000)
    }
}
