# Active.ai 🏋️‍♂️

> **Rutinas inteligentes con IA para personas sin tiempo**

Active.ai es una aplicación móvil Android que utiliza inteligencia artificial para crear rutinas de ejercicio personalizadas en segundos. Diseñada específicamente para personas con horarios ocupados que buscan mantenerse activas de manera eficiente.

## ✨ Características

- **🤖 IA Conversacional**: Chat inteligente que recopila tus datos de forma natural
- **⚡ Rutinas Instantáneas**: Genera planes de ejercicio personalizados en segundos
- **📊 Personalización Completa**: Considera tu nivel, objetivos, restricciones y disponibilidad
- **🎯 Objetivos Específicos**: Pérdida de grasa, ganancia muscular, mejora de resistencia
- **🚫 Manejo de Restricciones**: Adapta rutinas según lesiones o limitaciones
- **📱 Interfaz Moderna**: Diseño intuitivo con Material Design 3

## 🛠️ Tecnologías

- **Kotlin** - Lenguaje principal
- **Jetpack Compose** - UI moderna y declarativa
- **Material Design 3** - Sistema de diseño
- **ViewModel & StateFlow** - Arquitectura MVVM reactiva
- **Kotlinx Serialization** - Serialización de datos
- **Gradle Version Catalogs** - Gestión de dependencias

## 📋 Arquitectura

```
app/
├── data/                          # Modelos de datos
│   └── WorkoutDataRequest.kt      # Entidades para solicitudes de rutina
├── ui/
│   ├── composable/
│   │   ├── auth/                  # Pantallas de autenticación
│   │   ├── chat/                  # Sistema de chat con IA
│   │   ├── onboarding/            # Proceso de configuración inicial
│   │   └── common/                # Componentes reutilizables
│   └── theme/                     # Tema y colores personalizados
└── MainActivity.kt                # Actividad principal
```

### Patrones Implementados

- **MVVM**: Separación clara entre UI y lógica de negocio
- **State Management**: StateFlow para manejo reactivo del estado
- **Composition**: Componentes Compose reutilizables y modulares

## 🚀 Instalación

### Requisitos Previos

- Android Studio Hedgehog | 2023.1.1 o superior
- JDK 11 o superior
- Android SDK API 24+ (Android 7.0)

### Clonar y Ejecutar

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/active-app.git

# Abrir en Android Studio
cd active-app
# Archivo > Open > Seleccionar la carpeta del proyecto

# Sincronizar dependencias
./gradlew build

# Ejecutar en dispositivo/emulador
./gradlew installDebug
```

## 🎯 Flujo de Usuario

### 1. Onboarding
- Bienvenida personalizada
- Recopilación de datos básicos (nombre, edad, medidas)
- Configuración de objetivos y restricciones

### 2. Chat Inteligente
- Interfaz conversacional natural
- Formularios dinámicos (radio buttons, checkboxes, input de texto)
- Recopilación progresiva de información

### 3. Generación de Rutina
- Procesamiento inteligente de datos
- Creación de rutina personalizada
- Pantalla de carga con feedback motivacional

## 📱 Pantallas Principales

### Chat View
```kotlin
// Pantalla principal de interacción con IA
ChatView(
    viewModel = chatViewModel,
    state = uiState
)
```

### Onboarding
```kotlin
// Proceso de configuración inicial
WelcomeView(
    state = onboardingState,
    onNameChanged = { name -> viewModel.updateName(name) },
    onNext = { viewModel.nextStep() }
)
```

## 🎨 Diseño

### Tema de Colores
- **Primary**: Rojo (#F44336) - Energía y motivación
- **Secondary**: Púrpura (#9B59B6) - Tecnología e innovación
- **Accent**: Ámbar (#FFC107) - Destacados y logros

### Gradientes
```kotlin
val PrimaryGradient = Brush.verticalGradient(
    colors = listOf(Primary, Secondary)
)
```

## 🔧 Configuración del Proyecto

### build.gradle.kts (Module: app)
```kotlin
dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    // ... más dependencias
}
```

### Plugins Requeridos
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}
```

## 📊 Gestión de Estado

### ChatViewModel
```kotlin
class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()
    
    fun handleQuickAction(action: QuickAction) { /* ... */ }
    fun sendMessage() { /* ... */ }
    fun onRadioButtonSelected(data: String, index: Int, messageId: String) { /* ... */ }
}
```

### Estados de UI
- `ChatUiState`: Maneja mensajes, formularios y flujo de conversación
- `OnboardingUiState`: Controla el proceso de configuración inicial
- `WorkoutDataRequestEntity`: Almacena datos del usuario para generación de rutinas

## 🔄 Flujo de Datos

1. **Recopilación**: El chat recopila datos del usuario mediante formularios dinámicos
2. **Validación**: Los datos se validan y almacenan en `WorkoutDataRequestEntity`
3. **Procesamiento**: La IA procesa la información para crear rutinas personalizadas
4. **Presentación**: Se muestra la rutina generada con retroalimentación visual

## 🎯 Próximas Características

- [ ] Integración con API de IA para generación real de rutinas
- [ ] Sistema de seguimiento de progreso
- [ ] Notificaciones de recordatorio
- [ ] Integración con wearables
- [ ] Modo offline
- [ ] Compartir rutinas en redes sociales

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 🙋‍♂️ Soporte

¿Necesitas ayuda? Contáctanos:

- 📧 Email: support@active-ai.com
- 💬 Discord: [Active.ai Community](https://discord.gg/activeai)
- 🐛 Issues: [GitHub Issues](https://github.com/tu-usuario/active-app/issues)

---

**Hecho con ❤️ para hacer el fitness más accesible para todos**