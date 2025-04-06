package org.example.project

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList

class LogicPuzzle(imagen: Int) {

    private val puzzlesDisponibles = mapOf(
        R.drawable.mario to listOf(
            R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4,
            R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8,
            R.drawable.m9, R.drawable.m10, R.drawable.m11, R.drawable.m12,
            R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16
        ),
        R.drawable.batman to listOf(
            R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4,
            R.drawable.b5, R.drawable.b6, R.drawable.b7, R.drawable.b8,
            R.drawable.b9, R.drawable.b10, R.drawable.b11, R.drawable.b12,
            R.drawable.b13, R.drawable.b14, R.drawable.b15, R.drawable.b16
        ),
        R.drawable.numeros to listOf(
            R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4,
            R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8,
            R.drawable.n9, R.drawable.n10, R.drawable.n11, R.drawable.n12,
            R.drawable.n13, R.drawable.n14, R.drawable.n15, R.drawable.n16
        ),
        R.drawable.ldr to listOf(
            R.drawable.l16, R.drawable.l15, R.drawable.l14, R.drawable.l13,
            R.drawable.l12, R.drawable.l11, R.drawable.l10, R.drawable.l9,
            R.drawable.l8, R.drawable.l7, R.drawable.l6, R.drawable.l5,
            R.drawable.l4, R.drawable.l3, R.drawable.l2, R.drawable.l1
        ),
        R.drawable.cmbyn to listOf(
            R.drawable.c16, R.drawable.c15, R.drawable.c14, R.drawable.c13,
            R.drawable.c12, R.drawable.c11, R.drawable.c10, R.drawable.c9,
            R.drawable.c8, R.drawable.c7, R.drawable.c6, R.drawable.c5,
            R.drawable.c4, R.drawable.c3, R.drawable.c2, R.drawable.c1
        ),
        R.drawable.superman to listOf(
            R.drawable.sm16, R.drawable.sm15, R.drawable.sm14, R.drawable.sm13,
            R.drawable.sm12, R.drawable.sm11, R.drawable.sm10, R.drawable.sm9,
            R.drawable.sm8, R.drawable.sm7, R.drawable.sm6, R.drawable.sm5,
            R.drawable.sm4, R.drawable.sm3, R.drawable.sm2, R.drawable.sm1
        ),

    )

    private val piezasOriginales = puzzlesDisponibles[imagen] ?: emptyList()
    val piezas = mutableStateOf(piezasOriginales.toList())
    private val seleccionada = mutableStateOf<Int?>(null)
    private val haSidoMezclado = mutableStateOf(false)
    private val juegoGanado = mutableStateOf(false)

    fun mezclarPiezas() {
        if (!juegoGanado.value) {
            val listaMezclada = piezas.value.toMutableList()
            listaMezclada.shuffle()
            piezas.value = listaMezclada
            seleccionada.value = null
            haSidoMezclado.value = true
            juegoGanado.value = false
        }
    }

    fun seleccionarPieza(indice: Int) {
        if (juegoGanado.value) return
        val indexSeleccionado = seleccionada.value
        if (indexSeleccionado == null) {
            seleccionada.value = indice
        } else {
            if (indexSeleccionado != indice) {
                intercambiarPiezas(indexSeleccionado, indice)
                seleccionada.value = null
            }
        }
        if (yaGano()) {
            juegoGanado.value = true
        }
    }

    fun intercambiarPiezas(indice1: Int, indice2: Int) {
        val listActual = piezas.value.toMutableStateList()
        val temp = listActual[indice1]
        listActual[indice1] = listActual[indice2]
        listActual[indice2] = temp
        piezas.value = listActual
    }

    fun yaGano(): Boolean {
        if (!haSidoMezclado.value) return false
        return piezas.value.zip(piezasOriginales).all { (actual, original) -> actual == original }
    }


}
