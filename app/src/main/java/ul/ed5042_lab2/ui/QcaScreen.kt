package ul.ed5042_lab2.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ul.ed5042_lab2.R
import ul.ed5042_lab2.data.DataSource
import ul.ed5042_lab2.model.Module
import ul.ed5042_lab2.ui.theme.ED5042_Lab2Theme
import java.util.Locale

/**
 * enum values that represent the screens in the app
 */
enum class QcaAppScreen(@StringRes val title: Int) {
    QcaScreen(title = R.string.app_name),
    AddModule(title = R.string.add_module),
    ChangeModule(title = R.string.change_module)
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QcaAppBar(
    currentScreen: QcaAppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun QcaScreen(
    moduleList: List<Module>,
    onAddButtonClicked: () -> Unit,
    modifier: Modifier) {

    var qpvInput by remember { mutableStateOf(("")) }
    var tmpModuleList = filter(moduleList, qpvInput.toFloatOrNull()?:0.0f)
    val qca = calculateQca(tmpModuleList)

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
            .safeDrawingPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.qca_calculator),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        ModuleCard(
            code = stringResource(R.string.code),
            grade = stringResource(R.string.grade),
            weight = stringResource(R.string.ects),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        ModuleList(
            moduleList = tmpModuleList,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            )
        {
            Button(
                onClick = { onAddButtonClicked() },
                modifier = Modifier
            ) {
                Text(
                    text = stringResource(R.string.add_module),
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            TextField(
                value = qpvInput,
                label = { Text(stringResource(R.string.qpv)) },
                singleLine = true,
                onValueChange = { qpvInput = it },
                modifier = Modifier
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
            Text(
                text = stringResource(R.string.your_qca),
                style = MaterialTheme.typography.displaySmall,
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = modifier.testTag("Result"),
                text = qca,
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

    }
}

@Composable
fun ModuleList(moduleList: List<Module>, modifier: Modifier) {

    LazyColumn(modifier = modifier) {

    }
}


fun filter(moduleList: List<Module>, minQPV: Float): List<Module> {
    val filteredList = mutableListOf<Module>()
    for (module in moduleList) {
        if (lookupQpv(module.grade) >= minQPV) {
            filteredList.add(module)
        }
    }
    return filteredList
}


@Composable
fun ModuleCard(
    code: String,
    grade: String,
    weight: String,
    style: TextStyle,
    modifier: Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp),

//            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = code,
                modifier = modifier.weight(0.5f),
                style = style
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = grade,
                modifier = modifier.weight(0.25f),
                style = style
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = weight,
                modifier = modifier.weight(0.25f),
                style = style
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun QcaScreenPreview() {
    ED5042_Lab2Theme {
        QcaScreen(
            moduleList = DataSource().loadDummyData(),
            onAddButtonClicked = {},
            modifier = Modifier)
    }
}

fun calculateQca(
    moduleList: List<Module>
): String
{
    val qpvSum = {
        moduleList: List<Module> -> moduleList.sumOf { module -> lookupQpv(module.grade)*module.weight}
    }
    val weightSum = {
        moduleList: List<Module> -> moduleList.sumOf { module -> module.weight}
    }
    val result = qpvSum(moduleList)/weightSum(moduleList)
    return String.format("%.2f", result)
}


fun lookupQpv(grade:String): Double {
    val qpv = when (grade) {
        "F" -> 0.0
        "D2" -> 1.2
        "D1" -> 1.6
        "C3" -> 2.0
        "C2" -> 2.4
        "C1" -> 2.6
        "B3" -> 2.8
        "B2" -> 3.0
        "B1" -> 3.2
        "A2" -> 3.6
        "A1" -> 4.0
        else -> 0.0
    }
    return qpv
}

