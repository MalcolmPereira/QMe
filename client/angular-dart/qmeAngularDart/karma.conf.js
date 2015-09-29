module.exports = function(config) {
  config.set({
      basePath: '.',
      frameworks: ['dart-unittest'],

      files: [
        'test/qme_test.dart',
        {pattern: '**/*.dart', watched: false, included: false, served: true},
        {pattern: '**/*.html', watched: false, included: false, served: true},
        {pattern: 'packages/browser/dart.js', watched: false, included: true, served: true},
      ],

      autoWatch: true,

      plugins: [
        'karma-dart',
        'karma-chrome-launcher'
      ],

      browsers: ['Dartium']
  });
};