package py.com.active.active_app.ui.composable.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RadioButtonForm(
    title: String = "¿Que nivel de experiencia manejas?",
    options: List<String>,
    indexSelected: Int = 0,
    enableForm: Boolean,
    onRadioButtonSelected: (String, Int) -> Unit = fun(_, _) {},
    ){
    Column( modifier = Modifier.padding(16.dp)){
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        RadioButtonOptions(
            options,
            indexSelected,
            enableForm,
            onRadioButtonSelected
        )
    }
}

@Composable
fun RadioButtonOptions(
    options: List<String>,
    indexSelected: Int,
    enableForm: Boolean,
    onRadioButtonSelected: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
    ) {
        options.forEachIndexed { index, it ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = enableForm,
                        onClick = {
                            if (enableForm) {
                                onRadioButtonSelected(it, index)
                            }
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    enabled = enableForm,
                    selected = indexSelected == index,
                    onClick = { onRadioButtonSelected(it, index) }
                )
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    text = it,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDataCollectorPreview(){
    RadioButtonForm(enableForm = true, options = emptyList())
}