package ul.ed5042_lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ul.ed5042_lab2.data.DataSource
import ul.ed5042_lab2.model.Module
import ul.ed5042_lab2.ui.ModuleBox
import ul.ed5042_lab2.ui.QcaAppBar
import ul.ed5042_lab2.ui.QcaAppScreen
import ul.ed5042_lab2.ui.QcaScreen
import ul.ed5042_lab2.ui.theme.ED5042_Lab2Theme


class MainActivity : ComponentActivity() {

    val DEBUG = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ED5042_Lab2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var moduleList = mutableListOf<Module>()
                    if (DEBUG) {
                        moduleList = DataSource().loadDummyData()
                    }
                    QcaApp(modules = moduleList, modifier = Modifier)
                }
            }
        }
    }
}


@Composable
fun QcaApp(modules: MutableList<Module>,
           navController: NavHostController = rememberNavController(),
           modifier: Modifier
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = QcaAppScreen.valueOf(
        backStackEntry?.destination?.route ?: QcaAppScreen.QcaScreen.name
    )
    var moduleList = remember { mutableStateListOf(*modules.toTypedArray()) }

    Scaffold(
        topBar = {
            QcaAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        //val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = QcaAppScreen.QcaScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = QcaAppScreen.QcaScreen.name) {
                QcaScreen(
                    moduleList = moduleList,
                    onAddButtonClicked = {
                        navController.navigate(QcaAppScreen.AddModule.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = QcaAppScreen.AddModule.name) {
                ModuleBox(
                    module=null,
                    onActionClicked = {
                        navController.navigate(QcaAppScreen.QcaScreen.name)
                        moduleList.add(it)
                    },
                    buttonText = stringResource(R.string.add_module),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

        }
    }
}






@Preview(showBackground = true)
@Composable
fun QcaAppPreview() {
    ED5042_Lab2Theme {
        QcaApp(modules = DataSource().loadDummyData(), modifier = Modifier)
    }
}