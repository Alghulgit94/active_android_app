package py.com.active.active_app.ui.composable.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckBoxForm(
    title: String = "¿Cuál es tu disponibilidad semanal para entrenar?",
    options: List<String>,
    enableForm: Boolean,
    selectedOptions: Set<Int> = setOf(),
    onCheckBoxSelected: (String, Int) -> Unit = { _, _ -> },
    onConfirmCheckboxSelection: (() -> Unit)? = null
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        CheckBoxOptions(
            options = options,
            enableForm = enableForm,
            selectedOptions = selectedOptions, // Usar directamente el estado del ViewModel
            onCheckBoxSelected = onCheckBoxSelected // Pasar directamente el callback
        )

        // Botón de confirmación si hay callback y el formulario está habilitado
        if (enableForm && onConfirmCheckboxSelection != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    println("🔍 DEBUG - Botón confirmar presionado")
                    onConfirmCheckboxSelection.invoke()
                },
                enabled = selectedOptions.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar selección")
            }
        }
    }
}

@Composable
fun CheckBoxOptions(
    options: List<String>,
    enableForm: Boolean,
    selectedOptions: Set<Int>,
    onCheckBoxSelected: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = enableForm,
                        onClick = {
                            if (enableForm) {
                                onCheckBoxSelected(option, index)
                            }
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    enabled = enableForm,
                    checked = selectedOptions.contains(index),
                    onCheckedChange = {
                        if (enableForm) {
                            onCheckBoxSelected(option, index)
                        }
                    }
                )
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    text = option,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 12.sp,
                    color = if (enableForm)
                        MaterialTheme.colorScheme.onSurfaceVariant
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f) // Texto más tenue cuando está deshabilitado
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxFormPreview() {
    CheckBoxForm(
        enableForm = true,
        options = listOf(),
        selectedOptions = setOf(0, 2), // Ejemplo: Lunes y Miércoles seleccionados
        onConfirmCheckboxSelection = {
            // Acción de confirmación
        }
    )
}