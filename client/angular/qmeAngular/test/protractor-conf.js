exports.config = {

    allScriptsTimeout: 11000,

    capabilities: {
        'browserName': 'chrome'
    },

    chromeOnly: true,

    baseUrl: 'http://localhost:8000/app',

    seleniumAddress: 'http://localhost:4444/wd/hub',

    framework: 'jasmine',

    jasmineNodeOpts: {
        defaultTimeoutInterval: 30000
    },

    specs: [
        'e2e/*.js'
    ]
};
