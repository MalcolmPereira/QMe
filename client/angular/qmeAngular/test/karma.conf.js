module.exports = function(config){
    config.set({

        basePath : '../',

        files : [
            'app/lib/angular/angular.js',
            'app/lib/angular-route/angular-route.js',
            'app/lib/angular-resource/angular-resource.js',
            'app/lib/angular-animate/angular-animate.js',
            'app/lib/angular-mocks/angular-mocks.js',
            'app/js/**/*.js',
            'test/unit/**/*.js',
            'app/js/**/*.html'
        ],

        autoWatch : true,

        frameworks: ['jasmine'],

        browsers : ['Chrome', 'Firefox'],

        plugins : [
            'karma-chrome-launcher',
            'karma-jasmine',
            'karma-coverage'
        ],

        junitReporter : {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        },

        preprocessors: {
            'src/*.js': ['coverage'],
            'app/js/**/*.htm;': 'html2js'
        }

    });
};