package com.example.examenrecuperacionoscarmm.model

import androidx.lifecycle.ViewModel
import com.example.examenrecuperacionoscarmm.data.Asignatura
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MiViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun setHorasIn(horas: String) {
        _uiState.update { e -> e.copy(horasIn = horas) }
    }


    fun sumarHoras(asignatura: Asignatura, cantidad: String) {

        val horasIn = cantidad.toIntOrNull() ?: 0
        if (horasIn != 0) {
            val accion =
                "Se han añadido $cantidad de ${asignatura.nombre} a ${asignatura.precioHora}€"
            _uiState.update { e -> e.copy(ultimaAccionn = accion) }

            val asignaturas = _uiState.value.asignaturas
            for (a in asignaturas) {
                if (a == asignatura) {
                    a.horas += horasIn
                }
            }
            _uiState.update { e -> e.copy(asignaturas = asignaturas) }

            val resumen = ArrayList<String>()
            for (a in asignaturas) {
                if (a.horas > 0) {
                    val linea =
                        "Asig: ${a.nombre} precio/hora ${a.precioHora} total horas ${a.horas}"
                    resumen.add(linea)
                }
            }
            _uiState.update { e -> e.copy(resumen = resumen) }

            val precio = asignatura.precioHora * horasIn

            var totalH = _uiState.value.totalHoras + horasIn
            var totalP = _uiState.value.totalPrecio + precio


            _uiState.update { e -> e.copy(totalHoras = totalH, totalPrecio = totalP) }
        }
    }

    fun restarHoras(asignatura: Asignatura, cantidad: String) {

        var horasIn = cantidad.toIntOrNull() ?: 0

        if((asignatura.horas-horasIn)<0){
            horasIn = asignatura.horas
        }

        if (horasIn != 0) {
            val accion =
                "Se han eliminado $cantidad de ${asignatura.nombre} a ${asignatura.precioHora}€"
            _uiState.update { e -> e.copy(ultimaAccionn = accion) }

            val asignaturas = _uiState.value.asignaturas
            for (a in asignaturas) {
                if (a == asignatura) {
                    a.horas -= horasIn
                }
            }
            _uiState.update { e -> e.copy(asignaturas = asignaturas) }

            val resumen = ArrayList<String>()
            for (a in asignaturas) {
                if (a.horas > 0) {
                    val linea =
                        "Asig: ${a.nombre} precio/hora ${a.precioHora} total horas ${a.horas}"
                    resumen.add(linea)
                }
            }
            _uiState.update { e -> e.copy(resumen = resumen) }

            val precio = asignatura.precioHora * horasIn

            var totalH = _uiState.value.totalHoras - horasIn
            var totalP = _uiState.value.totalPrecio - precio


            _uiState.update { e -> e.copy(totalHoras = totalH, totalPrecio = totalP) }
        }

    }

}

