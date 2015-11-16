module.exports = function(grunt) {

    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        jshint: {
            files: ['app/js/**/*.js', 'test/**/*.js'],
            options: {
                reporter: require('jshint-stylish')
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');

};