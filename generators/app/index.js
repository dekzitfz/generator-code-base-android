const Generator = require('yeoman-generator');
const mkdirp = require('mkdirp');

module.exports = class extends Generator {
    constructor(args, opts){
        super(args, opts);
    }

    initializing(){
        this.props = {};
        this.log("initializing...");
    }

    async prompting() {
        this.answers = await this.prompt([
          {
            type: "input",
            name: "name",
            message: "What Is Your Application Name?",
            default: this.appname // Default to current folder name
          },
          {
            type: "input",
            name: "package",
            message: "Your Package Name",
            default: "com.example.myapp"
          },
          {
            type: "input",
            name: "targetSDK",
            message: "Your Target SDK",
            default: 29
          },
          {
            type: "input",
            name: "minSDK",
            message: "Your Minimum SDK",
            default: 23
          }
        ]);
    
        // this.log("app name", answers.name);
        // this.log("cool feature", answers.cool);
      }
     
      writing() {
        this.log("app name", this.answers.name);
        this.log("package name", this.answers.package);
        this.log("target SDK", this.answers.targetSDK);
        this.log("minimum SDK", this.answers.minSDK);

        //generate package app folder path
        var packageDir = this.answers.package.replace(/\./g, '/');

        //generate directory project
        var rootDir = this.answers.name.replace(/\s/g, '');

        //generate root folder
        mkdirp(rootDir);

        /*----------start generate files in root directory ----------*/
        //build.gradle (project)
        this.fs.copy(
          this.templatePath('build.gradle'),
          this.destinationPath(rootDir + '/build.gradle')
        );

        //gradle.properties
        this.fs.copy(
          this.templatePath('gradle.properties'),
          this.destinationPath(rootDir + '/gradle.properties')
        );

        //gradlew
        this.fs.copy(
          this.templatePath('gradlew'),
          this.destinationPath(rootDir + '/gradlew')
        );

        //gradlew.bat
        this.fs.copy(
          this.templatePath('gradlew.bat'),
          this.destinationPath(rootDir + '/gradlew.bat')
        );

        //settings.gradle
        this.fs.copyTpl(
          this.templatePath('settings.gradle'),
          this.destinationPath(rootDir + '/settings.gradle'),
          {app_name: this.answers.name.replace(/\s/g, '')}
        );

        // /app
        mkdirp(rootDir + '/app'); //root/app


        /*----------start generate files in root/app/ ----------*/
        //app/libs
        mkdirp(rootDir + '/app/libs');

        //app/src
        mkdirp(rootDir + '/app/src');

        //app/build.gradle
        this.fs.copyTpl(
          this.templatePath('app/build.gradle'),
          this.destinationPath(rootDir + '/app/build.gradle'),
          {package: this.answers.package}
        );

        //app/proguard-glide.pro
        this.fs.copy(
          this.templatePath('app/proguard-glide.pro'),
          this.destinationPath(rootDir + '/app/proguard-glide.pro')
        );

        //app/proguard-gson.pro
        this.fs.copyTpl(
          this.templatePath('app/proguard-gson.pro'),
          this.destinationPath(rootDir + '/app/proguard-gson.pro'),
          {package: this.answers.package}
        );

        //app/proguard-retrofit.pro
        this.fs.copy(
          this.templatePath('app/proguard-retrofit.pro'),
          this.destinationPath(rootDir + '/app/proguard-retrofit.pro')
        );

        //app/proguard-rules.pro
        this.fs.copy(
          this.templatePath('app/proguard-rules.pro'),
          this.destinationPath(rootDir + '/app/proguard-rules.pro')
        );


        /*----------start generate files in root/app/src ----------*/
        //app/src/androidTest/java/YOUR_PACKAGE_NAME
        mkdirp(rootDir + '/app/src/androidTest/java/' + packageDir);
      
        //app/src/test/java/YOUR_PACKAGE_NAME
        mkdirp(rootDir + '/app/src/test/java/' + packageDir);

        //app/src/main/
        mkdirp(rootDir + '/app/src/main');


        /*----------start generate files in root/app/src/main ----------*/
        //app/src/main/AndroidManifest.xml
        this.fs.copyTpl(
          this.templatePath('app/src/main/AndroidManifest.xml'),
          this.destinationPath(rootDir + '/app/src/main/AndroidManifest.xml'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH
        mkdirp(rootDir + '/app/src/main/java/' + packageDir);


        /*----------start generate files in root/app/src/main/YOUR_PACKAGE_PATH ----------*/
        //app/src/main/java/YOUR_PACKAGE_PATH/App.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/App.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/App.kt'),
          {package: this.answers.package}
        );


        /*----------start generate files in root/app/src/main/YOUR_PACKAGE_PATH/base ----------*/
        //app/src/main/java/YOUR_PACKAGE_PATH/base
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/base');

        //app/src/main/java/YOUR_PACKAGE_PATH/base/AppViewModelFactory.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/base/AppViewModelFactory.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/base/AppViewModelFactory.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/base/BaseActivity.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/base/BaseActivity.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/base/BaseActivity.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/base/BaseFragment.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/base/BaseFragment.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/base/BaseFragment.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/base/BaseFragmentViewModel.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/base/BaseFragmentViewModel.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/base/BaseFragmentViewModel.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/base/BaseViewModel.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/base/BaseViewModel.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/base/BaseViewModel.kt'),
          {package: this.answers.package}
        );


        /*----------start generate files in root/app/src/main/YOUR_PACKAGE_PATH/data ----------*/
        //app/src/main/java/YOUR_PACKAGE_PATH/data
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/data');

        //app/src/main/java/YOUR_PACKAGE_PATH/data/DataManager.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/data/DataManager.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/data/DataManager.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/data/PreferencesHelper.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/data/PreferencesHelper.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/data/PreferencesHelper.kt'),
          {package: this.answers.package}
        );


        /*----------start generate files in root/app/src/main/YOUR_PACKAGE_PATH/di ----------*/
        //app/src/main/java/YOUR_PACKAGE_PATH/di
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/di');

        //app/src/main/java/YOUR_PACKAGE_PATH/di/component/AppComponent.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/component/AppComponent.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/component/AppComponent.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/di/modules/AppModule.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/modules/AppModule.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/modules/AppModule.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/di/modules/BuildersModule.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/modules/BuildersModule.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/modules/BuildersModule.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/di/modules/FragmentBuildersModule.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/modules/FragmentBuildersModule.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/modules/FragmentBuildersModule.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/di/modules/NetworkModule.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/modules/NetworkModule.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/modules/NetworkModule.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/di/modules/ViewModelModule.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/modules/ViewModelModule.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/modules/ViewModelModule.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/di/scopes/ActivityContext.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/scopes/ActivityContext.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/scopes/ActivityContext.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/di/scopes/ApplicationContext.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/scopes/ApplicationContext.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/scopes/ApplicationContext.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/di/scopes/ViewModelKey.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/di/scopes/ViewModelKey.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/di/scopes/ViewModelKey.kt'),
          {package: this.answers.package}
        );


        /*----------start generate files in root/app/src/main/YOUR_PACKAGE_PATH/feature ----------*/
        //app/src/main/java/YOUR_PACKAGE_PATH/feature
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/feature');

        //app/src/main/java/YOUR_PACKAGE_PATH/feature/listpokemon
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/feature/listpokemon');

        //app/src/main/java/YOUR_PACKAGE_PATH/feature/listpokemon/ListPokemonActivity.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/feature/listpokemon/ListPokemonActivity.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/feature/listpokemon/ListPokemonActivity.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/feature/listpokemon/ListPokemonAdapter.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/feature/listpokemon/ListPokemonAdapter.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/feature/listpokemon/ListPokemonAdapter.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/feature/listpokemon/ListPokemonDataFactory.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/feature/listpokemon/ListPokemonDataFactory.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/feature/listpokemon/ListPokemonDataFactory.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/feature/listpokemon/ListPokemonDataSource.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/feature/listpokemon/ListPokemonDataSource.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/feature/listpokemon/ListPokemonDataSource.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/feature/listpokemon/ListPokemonViewModel.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/feature/listpokemon/ListPokemonViewModel.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/feature/listpokemon/ListPokemonViewModel.kt'),
          {package: this.answers.package}
        );


        /*----------start generate files in root/app/src/main/YOUR_PACKAGE_PATH/model ----------*/
        //app/src/main/java/YOUR_PACKAGE_PATH/model/api
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/model/api');

        //app/src/main/java/YOUR_PACKAGE_PATH/model/api/pokemon
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/model/api/pokemon');

        //app/src/main/java/YOUR_PACKAGE_PATH/model/api/pokemon/Pokemon.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/model/api/pokemon/Pokemon.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/model/api/pokemon/Pokemon.kt'),
          {package: this.answers.package}
        );

        //app/src/main/java/YOUR_PACKAGE_PATH/model/api/pokemon/PokemonResponse.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/model/api/pokemon/PokemonResponse.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/model/api/pokemon/PokemonResponse.kt'),
          {package: this.answers.package}
        );


        /*----------start generate files in root/app/src/main/YOUR_PACKAGE_PATH/network ----------*/
        //app/src/main/java/YOUR_PACKAGE_PATH/network
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/network');

        //app/src/main/java/YOUR_PACKAGE_PATH/network/ApiService.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/network/ApiService.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/network/ApiService.kt'),
          {package: this.answers.package}
        );


        /*----------start generate files in root/app/src/main/YOUR_PACKAGE_PATH/util ----------*/
        //app/src/main/java/YOUR_PACKAGE_PATH/util
        mkdirp(rootDir + '/app/src/main/java/' + packageDir + '/util');

        //app/src/main/java/YOUR_PACKAGE_PATH/util/NetworkState.kt
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/util/NetworkState.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/util/NetworkState.kt'),
          {package: this.answers.package}
        );

        
        /*----------start generate files in root/app/src/main/res ----------*/

        /*----------start generate files in root/app/src/main/res/drawable ----------*/
        //app/src/main/res/drawable
        mkdirp(rootDir + '/app/src/main/res');

        //app/src/main/res/drawable/ic_launcher_background.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/drawable/ic_launcher_background.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/drawable/ic_launcher_background.xml')
        );


        /*----------start generate files in root/app/src/main/res/drawable-v24 ----------*/
        //app/src/main/res/drawable-v24
        mkdirp(rootDir + '/app/src/main/res/drawable-v24');

        //app/src/main/res/drawable-v24/ic_launcher_foreground.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/drawable-v24/ic_launcher_foreground.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/drawable-v24/ic_launcher_foreground.xml')
        );


        /*----------start generate files in root/app/src/main/res/layout ----------*/
        //app/src/main/res/layout
        mkdirp(rootDir + '/app/src/main/res/layout');

        //app/src/main/res/layout/activity_list_pokemon.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/layout/activity_list_pokemon.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/layout/activity_list_pokemon.xml')
        );

        //app/src/main/res/layout/item_pokemon.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/layout/item_pokemon.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/layout/item_pokemon.xml')
        );


        /*----------start generate files in root/app/src/main/res/mipmap-anydpi-v26 ----------*/
        //app/src/main/res/mipmap-anydpi-v26
        mkdirp(rootDir + '/app/src/main/res/mipmap-anydpi-v26');

        //app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml')
        );

        //app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml')
        );


        /*----------start generate files in root/app/src/main/res/mipmap-hdpi ----------*/
        //app/src/main/res/mipmap-hdpi
        mkdirp(rootDir + '/app/src/main/res/mipmap-hdpi');

        //app/src/main/res/mipmap-hdpi/ic_launcher_round.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-hdpi/ic_launcher_round.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-hdpi/ic_launcher_round.xml')
        );

        //app/src/main/res/mipmap-hdpi/ic_launcher.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-hdpi/ic_launcher.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-hdpi/ic_launcher.xml')
        );


        /*----------start generate files in root/app/src/main/res/mipmap-mdpi ----------*/
        //app/src/main/res/mipmap-mdpi
        mkdirp(rootDir + '/app/src/main/res/mipmap-mdpi');

        //app/src/main/res/mipmap-mdpi/ic_launcher_round.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-mdpi/ic_launcher_round.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-mdpi/ic_launcher_round.xml')
        );

        //app/src/main/res/mipmap-mdpi/ic_launcher.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-mdpi/ic_launcher.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-mdpi/ic_launcher.xml')
        );


        /*----------start generate files in root/app/src/main/res/mipmap-xhdpi ----------*/
        //app/src/main/res/mipmap-xhdpi
        mkdirp(rootDir + '/app/src/main/res/mipmap-xhdpi');

        //app/src/main/res/mipmap-xhdpi/ic_launcher_round.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-xhdpi/ic_launcher_round.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-xhdpi/ic_launcher_round.xml')
        );

        //app/src/main/res/mipmap-xhdpi/ic_launcher.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-xhdpi/ic_launcher.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-xhdpi/ic_launcher.xml')
        );


        /*----------start generate files in root/app/src/main/res/mipmap-xxhdpi ----------*/
        //app/src/main/res/mipmap-xxhdpi
        mkdirp(rootDir + '/app/src/main/res/mipmap-xxhdpi');

        //app/src/main/res/mipmap-xxhdpi/ic_launcher_round.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-xxhdpi/ic_launcher_round.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.xml')
        );

        //app/src/main/res/mipmap-xxhdpi/ic_launcher.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-xxhdpi/ic_launcher.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-xxhdpi/ic_launcher.xml')
        );


        /*----------start generate files in root/app/src/main/res/mipmap-xxxhdpi ----------*/
        //app/src/main/res/mipmap-xxxhdpi
        mkdirp(rootDir + '/app/src/main/res/mipmap-xxxhdpi');

        //app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.xml')
        );

        //app/src/main/res/mipmap-xxxhdpi/ic_launcher.xml
        this.fs.copy(
          this.templatePath('app/src/main/res/mipmap-xxxhdpi/ic_launcher.xml'),
          this.destinationPath(rootDir + '/app/src/main/res/mipmap-xxxhdpi/ic_launcher.xml')
        );



      }
};