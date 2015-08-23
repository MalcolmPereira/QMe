module.exports = function(config){
    config.set({

        basePath : '../',

        frameworks: ['jasmine'],

        browsers : ['Chrome'],

        autoWatch : true,

        plugins : [
            'karma-chrome-launcher',
            'karma-jasmine',
            'karma-coverage',
            'karma-ng-html2js-preprocessor',
            'ng-html2js'
        ],

        files : [
            'app/lib/angular/angular.js',
            'app/lib/angular-ui-router/release/angular-ui-router.min.js',
            'app/lib/angular-resource/angular-resource.js',
            'app/lib/angular-mocks/angular-mocks.js',
            'app/js/qme.js',
            'app/js/**/*.js',
            'app/js/**/*.html',
            'test/unit/**/*.js',

        ],

        exclude: [],

        ngHtml2JsPreprocessor: {
            moduleName: 'qmeApp'
        },

        preprocessors: {
            'src/**/*.js': ['coverage'],
            'app/**/*.html': ['ng-html2js']
        },

        colors: true,

        reporters: ['progress', 'coverage'],

        junitReporter : {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        },

        // level of logging
        // config.LOG_DISABLE ||
        // config.LOG_ERROR ||
        // config.LOG_WARN ||
        // config.LOG_INFO ||
        // config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        singleRun: false


    });
};