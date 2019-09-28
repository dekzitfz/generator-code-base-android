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




        //generate app folder and its subfolder(s)
        mkdirp(rootDir + '/app/src/main/java/' + packageDir); //NOTE put kotlin file(s) inside this directory

        //AndroidManifest
        this.fs.copyTpl(
          this.templatePath('app/src/main/AndroidManifest.xml'),
          this.destinationPath(rootDir + '/app/src/main/AndroidManifest.xml'),
          {package: this.answers.package}
        );

        // generate kt file(s)
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/App.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/App.kt'),
          {package: this.answers.package}
        );
        

        // this.fs.copy(
        //   this.templatePath('file.txt'),
        //   this.destinationPath(packageDir +'/file.txt')
        // );
      }
};