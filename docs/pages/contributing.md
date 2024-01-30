# Contributing

This is not written in rock like you must be guessing so write us and let's make everyone's life a little easier.

Remember to always follow these **[guidelines](https://sites.google.com/mercadolibre.com/mobile/nuevos-proyectos/requerimientos-para-prod)**.

For more information about in house distribution please check [the wiki](https://sites.google.com/mercadolibre.com/mobile/arquitectura/in-house-distribution-mds)

## Developing and contribuing

### Running checks

We run [SCA](https://github.com/Monits/static-code-analysis-plugin) checks for static code analysis and some built in Android lints among all tests. To run everything:

``` bash
./gradlew check
```

and if you want to check an specific module you can:

``` bash
./gradlew module:check
```

### Getting coverage report

If you want to get a full Jacoco coverage report, you can run the following task:

``` bash
./gradlew jacocoFullReport
```

### Compiling locally

#### Testapp

For testing your module locally you can run the integrated **testapp**. If you want to build it:

``` bash
./gradlew testapp:assembleDebug
```

or if you have already a device connected to **adb**:

``` bash
./gradlew testapp:installDebug
```

#### Pre-release versions

For testing your module in other application (such as the main application or other testapp) you can publish your module locally:

```bash
./gradlew nativesdk:publishLocal
```

or if you want to be published for everyone:

```bash
./gradlew nativesdk:publishExperimental
```