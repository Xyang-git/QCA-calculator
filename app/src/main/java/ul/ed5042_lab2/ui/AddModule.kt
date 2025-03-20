package ul.ed5042_lab2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ul.ed5042_lab2.R
import ul.ed5042_lab2.model.Module
import ul.ed5042_lab2.ui.theme.ED5042_Lab2Theme


@Composable
fun ModuleBox(
    module: Module?,
    buttonText: String,
    onActionClicked: (Module)->Unit,
    modifier: Modifier = Modifier
) {

    var codeInput by remember { mutableStateOf(module?.code?:"") }
    var nameInput by remember { mutableStateOf(module?.name?:"") }
    var gradeInput by remember { mutableStateOf(module?.grade?:"") }
    var weightInput by remember { mutableStateOf((module?.weight?:"").toString()) }


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Text(
                text = stringResource(R.string.add_module),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            TextField(
                value = codeInput,
                label = { Text(stringResource(R.string.module_code)) },
                singleLine = true,
                onValueChange = { codeInput = it },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            TextField(
                value = nameInput,
                label = { Text(stringResource(R.string.module_name)) },
                maxLines = 2,
                onValueChange = { nameInput = it },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            TextField(
                value = gradeInput,
                label = { Text(stringResource(R.string.module_grade)) },
                maxLines = 2,
                onValueChange = { gradeInput = it },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            TextField(
                value = weightInput,
                label = { Text(stringResource(R.string.module_weight)) },
                singleLine = true,
                onValueChange = { weightInput = it },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            Button(
                onClick = {onActionClicked(Module(name = nameInput, code = codeInput, grade= gradeInput, weight = weightInput.toIntOrNull() ?: 0)) },
                modifier = Modifier
            ) {
                Text(text = buttonText, fontSize = 20.sp, color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ModuleBoxPreview() {
    ED5042_Lab2Theme {
        ModuleBox(
            module = Module(code = "ED5042",
                name = "Module title",
                grade = "A1",
                weight = 6
            ),
            buttonText = stringResource(R.string.add),
            onActionClicked = { } ,
            modifier = Modifier
        )
    }
}

