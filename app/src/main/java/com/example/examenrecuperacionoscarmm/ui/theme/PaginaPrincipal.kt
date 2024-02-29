package com.example.examenrecuperacionoscarmm.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examenrecuperacionoscarmm.data.Asignatura
import com.example.examenrecuperacionoscarmm.data.DataSource
import com.example.examenrecuperacionoscarmm.model.MiViewModel
import com.example.examenrecuperacionoscarmm.model.UiState

@Composable
fun PaginaPrincpal(vm: MiViewModel, estado: UiState, pag2: () -> Unit) {
    Column {
        Text(
            text = "Bienvenido a la academia de Sergio/OscarMM",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(start = 20.dp, top = 10.dp)
                //.weight(0.3f)
        )
        Box(modifier = Modifier.weight(2f)) {
            PanelAsignaturas(vm, estado)
        }

        Box(modifier = Modifier.weight(2f)) {
            SalidaDeTexto(estado)
        }

        Button(onClick = pag2,
            modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(text = "Cambiar de pantalla")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanelAsignaturas(mv: MiViewModel, estado: UiState) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(DataSource.asignaturas) { a ->
                TarjetaAsignatura(a, mv, estado)
            }

        }

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = estado.horasIn,
            onValueChange = { mv.setHorasIn(it) },
            singleLine = true,
            label = { Text(text = "Horas a contratar o eliminar") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
                .weight(0.35f)
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun TarjetaAsignatura(a: Asignatura, mv: MiViewModel, estado: UiState) {
    Card {
        Text(
            text = "Asig: ${a.nombre}",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(20.dp)
        )
        Text(
            text = "€/hora: ${a.precioHora}",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(20.dp)
        )

        Row (horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()){
            Button(onClick = {
                mv.sumarHoras(a, estado.horasIn)
            }) {
                Text(text = "+")
            }
            Button(onClick = { mv.restarHoras(a, estado.horasIn) }) {
                Text(text = "-")
            }
        }
    }
}


@Composable
fun SalidaDeTexto(estado: UiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(30.dp)
    ) {
        Text(
            text = "Ultima accion",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = estado.ultimaAccionn,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = "Resumen",
            modifier = Modifier
                .fillMaxWidth()
        )

        for (ac in estado.resumen) {
            Text(
                text = ac,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }



        Text(
            text = "Total horas: ${estado.totalHoras}",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = "Total precio ${estado.totalPrecio}",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //PaginaPrincpal(null,null,{})

}