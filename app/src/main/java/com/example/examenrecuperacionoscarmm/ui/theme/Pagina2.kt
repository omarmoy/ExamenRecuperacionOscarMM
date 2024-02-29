package com.example.examenrecuperacionoscarmm.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Pagina2(volver: ()->Unit){
    Column {
        Text(text = "Soy una pagina vacía")
        Button(onClick = volver) {
            Text(text = "volver")
        }
    }

}