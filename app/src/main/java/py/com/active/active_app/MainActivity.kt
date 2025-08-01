package py.com.active.active_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import py.com.active.active_app.ui.composable.onboarding.OnboardingViewModel
import py.com.active.active_app.ui.composable.onboarding.WelcomeView
import py.com.active.active_app.ui.theme.ActiveAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: OnboardingViewModel = OnboardingViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActiveAppTheme {
                val state = viewModel.uiState.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WelcomeView(
                        modifier = Modifier.padding(innerPadding),
                        state = state.value,
                        onNameChanged = viewModel::updateName,
                        onNext = viewModel::nextStep,
                        onPrevious = viewModel::previousStep
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ActiveAppTheme {
        Greeting("Android")
    }
}