# Code Base Generator for Android Project

A Boilerplate code generator for creating Android project. Using fully Kotlin and MVVM pattern that refer to Android Jetpack. Because Im tired to setup Dagger and other stuffs everytime initializing a new project. This boilerplate using [PokéAPI](https://pokeapi.co/) for sample list.

[![npm version](https://badge.fury.io/js/generator-android-kotlin-mvvm.svg)](https://badge.fury.io/js/generator-android-kotlin-mvvm)


## What's Included:

- [Kotlin](https://kotlinlang.org/)
- [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)
- [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [Dagger 2](https://github.com/google/dagger)
- [Retrofit 2](https://github.com/square/retrofit)
- [Paging Library](https://developer.android.com/topic/libraries/architecture/paging)
- [ReactiveX](https://github.com/ReactiveX/RxAndroid)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide](https://github.com/bumptech/glide)


## TODO List:

- [x] Offline First (using Room and paging lib)
- [x] Add Example Usage of Fragment
- [ ] Create Documentation Code Snippet
- [ ] Add example for using Coroutines
- [ ] Create Unit Test


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

Run Generator using Yeoman

```bash
yo android-kotlin-mvvm
```

Open project using Android Studio, build, and done!

## Testing Local Database Migration

This boilerplate using Room to store Local Database, to handle each migration you can refer to existing sample. In this sample we add new column in existing table.

- After first project build, make sure database schemas generated for 1st version, it should be located in ```YOUR_PROJECT_DIR\app\schemas\YOUR_PACKAGE_PATH.data.AppDatabase\1.json```, if its not exist, try to rebuild the project.

- do changes on this 3 files:

1. ```AppDatabase.kt```

change version number from 1 to 2. (Migration query has been included, ```MIGRATION_1_2```)

2. ```LocalPokemon.kt```

since we adding column, we need update the entity with the new column.

3. ```AppModule.kt```

- add migration query to the database builder, for details you can also check the TODOs.

- then rebuild the project, new schema version should be generated, ```2.json```

- go to ```MigrationTest.kt``` and run test using device or emulator.