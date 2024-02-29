package com.example.examenrecuperacionoscarmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenrecuperacionoscarmm.model.MiViewModel
import com.example.examenrecuperacionoscarmm.ui.theme.ExamenRecuperacionOscarMMTheme
import com.example.examenrecuperacionoscarmm.ui.theme.Pagina2
import com.example.examenrecuperacionoscarmm.ui.theme.PaginaPrincpal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenRecuperacionOscarMMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

enum class Paginas {
    PRINCIPAL, PAG2
}


@Composable
fun App(navController: NavHostController = rememberNavController(), vm: MiViewModel = viewModel()) {
    val estado by vm.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = Paginas.PRINCIPAL.name
    ) {
        composable(route = Paginas.PRINCIPAL.name) {
            PaginaPrincpal(vm, estado,{navController.navigate(Paginas.PAG2.name)})
        }
        composable(route = Paginas.PAG2.name) {
            Pagina2({navController.navigate(Paginas.PRINCIPAL.name)})
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExamenRecuperacionOscarMMTheme {
        //PaginaPrincpal()
    }
}

