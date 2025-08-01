import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import py.com.active.active_app.ui.composable.common.GradientText
import py.com.active.active_app.ui.theme.ActiveAppExtendedColors
import py.com.active.active_app.ui.theme.ActiveAppTheme
import py.com.active.active_app.ui.theme.PrimaryGradient

// Screen 1: Login Screen (Refactorizada)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        GradientText(
            text = "active.ai",
            gradient = PrimaryGradient
        )
        // Get Started Section
        Text(
            text = "Get Started",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Personalized routines in seconds for people without time.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(
                        text = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Sign In Button
        Button(
            onClick = { /* Handle sign in */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Sign In",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Divider
        Text(
            text = "Or sign in with",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        // Google Button
        OutlinedButton(
            onClick = { /* Handle Google sign in */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 12.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Continue with Google",
                fontSize = 16.sp
            )
        }

        // Apple Button
        OutlinedButton(
            onClick = { /* Handle Apple sign in */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Continue with Apple",
                fontSize = 16.sp
            )
        }

        // Sign Up Link
        Row {
            Text(
                text = "Don't have an account? ",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
            Text(
                text = "Sign Up here",
                color = ActiveAppExtendedColors.hyperlink,
                fontSize = 14.sp
            )
        }
    }
}

// Previews con tema aplicado
@Preview(name = "Login Light", showBackground = true)
@Composable
fun LoginScreenPreview() {
    ActiveAppTheme(darkTheme = false) {
        LoginView()
    }
}

@Preview(name = "Login Dark", showBackground = true)
@Composable
fun LoginScreenDarkPreview() {
    ActiveAppTheme(darkTheme = true) {
        LoginView()
    }
}