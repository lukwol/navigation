# cm-navigation

[![check](https://github.com/lukwol/cm-navigation/actions/workflows/check.yml/badge.svg)](https://github.com/lukwol/cm-navigation/actions/workflows/check.yml)
[![](https://jitpack.io/v/lukwol/cm-navigation.svg)](https://jitpack.io/#lukwol/cm-navigation)

Tiny library for easy navigation in [Compose Multiplatform](https://github.com/JetBrains/compose-jb/)
applications.

Provides:
 * Screens navigation - desktop and web (and hopefully iOS soon)
 * Windows navigation - desktop only
 * Optional `ViewModel` with work cancellation support

## Installation

`cm-navigation` is published on jitpack.io.

To get a Git project into your build:

Step 1. Add the JitPack repository to `build.gradle.kts` at the end of repositories:

```kotlin
allprojects {
    repositories {
        // ...
        maven("https://jitpack.io")
    }
}
```

Step 2. Add the dependency

```kotlin
dependencies {
    // Screens navigation - multiplatform
    implementation("com.github.lukwol.cm-navigation:screens-navigation:0.1.1")
    // Screens navigation with ViewModel support - multiplatform (native disabled for now)
    implementation("com.github.lukwol.cm-navigation:viewmodel-screens-navigation:0.1.1")
    // Windows navigation - jvm only, for desktop application
    implementation("com.github.lukwol.cm-navigation:windows-navigation:0.1.1")
    // ViewModel without navigation - multiplatform (native disabled for now)
    implementation("com.github.lukwol.cm-navigation:viewmodel:0.1.1")
}
```

## Usage

Check out [examples](https://github.com/lukwol/cm-navigation/tree/main/examples/) for actual usage in the app.

### Build screens navigation

Basic screens navigation:

```kotlin
ScreensNavigation(
    startRoute = AppRoutes.FirstScreenRoute
) {
    screen(AppRoutes.FirstScreenRoute) {
        FirstScreen()
    }
    
    screen(AppRoutes.SecondScreenRoute) { args ->
        SecondScreen(args as String)
    }
}
```

Screens navigation with `ViewModel`:

```kotlin
ScreensNavigation(
    startRoute = AppRoutes.FirstScreenRoute
) {
    screen(
        route = AppRoutes.FirstScreenRoute,
        viewModelFactory = { FirstScreenViewModel() }
    ) { viewModel ->
        FirstScreen(viewModel)
    }
    
    screen(
        route = AppRoutes.SecondScreenRoute,
        viewModelFactory = { args -> SecondScreenViewModel(args as String) }
    ) { viewModel ->
        SecondScreen(viewModel)
    }
}
```

### Build windows navigation if needed (desktop)

```kotlin
WindowsNavigation(
    startRoute = AppRoutes.FirstWindowRoute
) {
    window(
        route = AppRoutes.FirstWindowRoute,
        title = "First Window"
    ) {
        ScreensNavigation(
            startRoute = AppRoutes.FirstScreenRoute
        ) {
            // ...
        }
    }

    window(
        route = AppRoutes.SecondWindowRoute,
        title = "Second Window"
    ) {
        ScreensNavigation(
            startRoute = AppRoutes.SecondScreenRoute
        ) {
            // ...
        }
    }
}
```

### Navigate to screen

```kotlin
// Obtain LocalScreensController in your view
val screensController = LocalScreensController.current

// Push screen
screensController.push(AppRoutes.SecondScreenRoute)

// Optionally pass arguments if needed
screensController.push(AppRoutes.SecondScreenRoute, SomeArguments)

// Pop screen to navigate back
screensController.pop()

// Optionally pass route, up to which screens should be dismissed
screensController.pop(AppRoutes.FirstScreenRoute)
```

### Navigate to window (desktop)

```kotlin
// Obtain LocalWindowController in your view
val windowsController = LocalWindowController.current

// Open window
windowsController.open(AppRoutes.SecondWindowRoute)

// Optionally pass arguments if needed
windowsController.open(AppRoutes.SecondWindowRoute, SomeArguments)

// Close window
windowsController.close(AppRoutes.SecondWindowRoute)
```

## Documentation

API Reference is available at https://lukwol.github.io/cm-navigation/ 

## Licensing

Project is available under [MIT](https://github.com/lukwol/cm-navigation/blob/main/LICENSE) License.
