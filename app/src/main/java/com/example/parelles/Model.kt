package com.example.parelles


class Model {

    // Las 3 parejas
    val cartas = mutableListOf(
        R.drawable.chiquito,
        R.drawable.eugenio,
        R.drawable.faemino,
        R.drawable.chiquito,
        R.drawable.eugenio,
        R.drawable.faemino
    )

    // Cartas giradas
    val visibles = mutableListOf<Int>()

    // Parejas encontradas
    val acertadas = mutableListOf<Int>()

    init {
        cartas.shuffle()
    }

    fun girarCarta(posicion: Int): Boolean {

        if (posicion in visibles) return false
        if (posicion in acertadas) return false
        if (visibles.size >= 2) return false

        visibles.add(posicion)

        return true
    }

    fun validar(): Boolean {

        if (visibles.size != 2) return false

        val carta1 = visibles[0]
        val carta2 = visibles[1]

        val iguales = cartas[carta1] == cartas[carta2]

        if (iguales) {
            acertadas.add(carta1)
            acertadas.add(carta2)
        }

        visibles.clear()

        return iguales
    }

    fun estaVisible(posicion: Int): Boolean {
        return posicion in visibles ||
                posicion in acertadas
    }

    fun juegoTerminado(): Boolean {
        return acertadas.size == 6
    }

    fun quedanDosCartas(): Boolean {
        return visibles.size == 2
    }
}
