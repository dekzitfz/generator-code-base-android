# Code Base Generator for Android Project

A Boilerplate code generator for creating Android project. Using Kotlin and MVVM pattern that refer to Android Jetpack. This boilerplate using [PokéAPI](https://pokeapi.co/) for sample data source list.

[![npm version](https://badge.fury.io/js/generator-android-kotlin-mvvm.svg)](https://badge.fury.io/js/generator-android-kotlin-mvvm)
![Dynamic JSON Badge](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fraw.githubusercontent.com%2Fdekzitfz%2Fgenerator-code-base-android%2Fmaster%2Finfo.json&query=%24.minAndroidSDK&label=minimum%20SDK)
![Dynamic JSON Badge](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fraw.githubusercontent.com%2Fdekzitfz%2Fgenerator-code-base-android%2Fmaster%2Finfo.json&query=%24.targetAndroidSDK&label=target%20SDK)
![Dynamic JSON Badge](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fraw.githubusercontent.com%2Fdekzitfz%2Fgenerator-code-base-android%2Fmaster%2Finfo.json&query=%24.kotlin&label=Kotlin%20Version)
![Dynamic JSON Badge](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fraw.githubusercontent.com%2Fdekzitfz%2Fgenerator-code-base-android%2Fmaster%2Finfo.json&query=%24.agp&label=Android%20Gradle%20Plugin)
![Dynamic JSON Badge](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fraw.githubusercontent.com%2Fdekzitfz%2Fgenerator-code-base-android%2Fmaster%2Finfo.json&query=%24.yeoman&label=Yeoman%20Version)



## What's Included:

- [Kotlin](https://kotlinlang.org/)
- [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)
- [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [Dagger 2](https://github.com/google/dagger)
- [Retrofit 2](https://github.com/square/retrofit)
- [Paging Library](https://developer.android.com/topic/libraries/architecture/paging)
- [Room](https://developer.android.com/training/data-storage/room)
- [ReactiveX](https://github.com/ReactiveX/RxAndroid)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide](https://github.com/bumptech/glide)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [Kotlin DSL](https://android-developers.googleblog.com/2023/04/kotlin-dsl-is-now-default-for-new-gradle-builds.html)


## How To Use

Install yeoman using NPM

```bash
npm install -g yo
```

Create your project directory

```bash
mkdir MyNewApp
cd MyNewApp/
```

Install generator using NPM

```bash
npm install -g generator-android-kotlin-mvvm
```

New Node and npm users might run into permissions issues. These issues show up in the form of `EACCESS` errors during installation. Refer to the [npm guide to fix permissions](https://docs.npmjs.com/getting-started/fixing-npm-permissions) if this happens to you.

On Windows, we suggest using a better command line tool such as [cmder](https://cmder.app/) or PowerShell to improve the experience.

Run Generator using Yeoman

```bash
yo android-kotlin-mvvm
```

Once boilerplate generated sucessfully, open it using Android Studio, sync/rebuild your project, and done! Check [Wiki](https://github.com/dekzitfz/generator-code-base-android/wiki) for more information how to use this boilerplate.

## Notes

Make sure you are using java version 17 before try to sync/build the source. Configure it at android studio -> file -> project structure -> SDK location -> gradle settings -> gradle JDK

## Troubleshooting
Most issues can be found by running:

```bash
yo doctor
```

The `doctor` command will diagnose and provide steps to resolve the most common issues.