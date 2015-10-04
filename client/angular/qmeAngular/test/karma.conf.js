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
            'karma-ng-html2js-preprocessor'
        ],

        files : [
            'app/lib/jquery/dist/jquery.min.js',
            'app/lib/bootstrap/dist/js/bootstrap.min.js',
            'app/lib/angular/angular.min.js',
            'app/lib/angular-ui-router/release/angular-ui-router.min.js',
            'app/lib/angular-resource/angular-resource.min.js',
            'app/lib/angular-base64/angular-base64.min.js',
            'app/lib/angular-mocks/angular-mocks.js',
            'app/js/qme.js',
            'app/js/**/*.js',
            'app/js/**/*.html',
            'test/unit/**/*.js',

        ],

        exclude: [],

        reporters: ['progress', 'coverage'],

        ngHtml2JsPreprocessor: {
            stripPrefix: 'app/',
            moduleName: 'qmeApp.templates'
        },

        preprocessors: {
            'app/js/**/*.js': ['coverage'],
            'app/js/**/*.html': ['ng-html2js']
        },

        coverageReporter: {
            type : 'html',
            dir : 'coverage/',
            subdir: 'report'
        }
        ,
        junitReporter : {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        },

        colors: true,

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