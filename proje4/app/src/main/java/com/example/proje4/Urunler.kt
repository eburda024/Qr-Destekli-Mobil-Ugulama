package com.example.proje4

import kotlin.random.Random

data class Urunler(
    var id : Long = getRandomId(),
    var name: String = "",
    var desc: String ="",
) {
    companion object {
        fun getRandomId(): Long {
            var sonuc: Long = 0
            for (i in 1..12) {
                sonuc = (sonuc * 10 + (Random.nextLong() and Long.MAX_VALUE) % 10)
            }
            return sonuc
        }
    }
}