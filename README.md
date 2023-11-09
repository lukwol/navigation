# navigation

[![maven-central](https://img.shields.io/badge/Maven-Central-download.svg?style=flat-square)](https://central.sonatype.com/namespace/io.github.lukwol)
[![check](https://github.com/lukwol/navigation/actions/workflows/check.yml/badge.svg)](https://github.com/lukwol/navigation/actions/workflows/check.yml)

Tiny library for easy navigation
in [Compose Multiplatform](https://github.com/JetBrains/compose-jb/)
applications.

Provides:

* Screens navigation - multiplatform
* Optional `ViewModel` with work cancellation support - multiplatform
* Windows navigation - desktop only

## Versions

For each version of `navigation` specific version of Compose Multiplatform is required

| navigation | compose-multiplatform |
|:----------:|:---------------------:|
|   1.3.0    |        1.5.10         |
|   1.2.0    |         1.5.3         |
|   1.1.0    |         1.5.2         |
|   1.0.0    |         1.5.1         |

## Installation

Add `mavenCentral` and `google` repositories:

```kotlin
repositories {
    mavenCentral()
    google()
}
```

Declare dependencies in `build.gradle.kts`:

```kotlin
dependencies {
    // Screens navigation - multiplatform
    implementation("io.github.lukwol:navigation-screens:1.3.0")
    // Screens navigation with ViewModel support - multiplatform
    implementation("io.github.lukwol:navigation-screens-viewmodel:1.3.0")
    // Windows navigation - desktop application only
    implementation("io.github.lukwol:navigation-windows:1.3.0")
}
```

## Usage

Bootstrap new project with handy [`app-template`](https://github.com/lukwol/app-template/).

### Build screens navigation

Basic screens navigation:

```kotlin
ScreensNavigation(
    startRoute = AppRoutes.FirstScreenRoute,
) {
    screen(AppRoutes.FirstScreenRoute) {
        FirstScreen()
    }

    screen(AppRoutes.SecondScreenRoute) { args: String? ->
        SecondScreen(args)
    }
}
```

Screens navigation with `ViewModel` and custom animations:

```kotlin
ScreensNavigation(
    startRoute = AppRoutes.FirstScreenRoute,
    enterTransition = {
        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
    },
    exitTransition = {
        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
    },
    popEnterTransition = {
        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
    },
    popExitTransition = {
        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
    },
) {
    screen(
        route = AppRoutes.FirstScreenRoute,
        viewModelFactory = {
            FirstScreenViewModel()
        }
    ) { viewModel ->
        FirstScreen(viewModel)
    }

    screen(
        route = AppRoutes.SecondScreenRoute,
        viewModelWithArgs = { args: SomeArgs? ->
            SecondScreenViewModel(args)
        }
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

API Reference is available at https://lukwol.github.io/navigation/

## Licensing

Project is available under [MIT](https://github.com/lukwol/navigation/blob/main/LICENSE) License.
