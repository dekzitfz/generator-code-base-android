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
        mkdirp(rootDir);

        //generate app folder and its subfolder(s)
        mkdirp(rootDir + '/app'); //root/app
        mkdirp(rootDir + '/app/libs'); //root/app/libs
        mkdirp(rootDir + '/app/src'); //root/app/src
        mkdirp(rootDir + '/app/src/androidTest/java/' + packageDir); //root/app/src/androidTest/java/YOUR_PACKAGE_NAME
        mkdirp(rootDir + '/app/src/main');
        mkdirp(rootDir + '/app/src/main/java/' + packageDir); //NOTE put kotlin file(s) inside this directory

        // generate kt file(s)
        this.fs.copyTpl(
          this.templatePath('app/src/main/java/com/example/app/MainActivity.kt'),
          this.destinationPath(rootDir + '/app/src/main/java/' + packageDir + '/MainActivity.kt'),
          {package: this.answers.package}
        );
        

        // this.fs.copy(
        //   this.templatePath('file.txt'),
        //   this.destinationPath(packageDir +'/file.txt')
        // );
      }
};