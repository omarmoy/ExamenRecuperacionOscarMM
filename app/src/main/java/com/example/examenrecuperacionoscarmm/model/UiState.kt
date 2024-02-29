package com.example.examenrecuperacionoscarmm.model

import com.example.examenrecuperacionoscarmm.data.Asignatura
import com.example.examenrecuperacionoscarmm.data.DataSource

data class UiState(
    val ultimaAccionn: String = "No has hecho ninguna accion",
    val resumen: ArrayList<String> = arrayListOf<String>("No hay nada que mostrar (defecto)"),
    val asignaturas: ArrayList<Asignatura> = DataSource.asignaturas,
    val horasContradas: Int = 0,
    val totalHoras: Int = 0,
    val totalPrecio: Int = 0,
    val horasIn: String = ""
)
