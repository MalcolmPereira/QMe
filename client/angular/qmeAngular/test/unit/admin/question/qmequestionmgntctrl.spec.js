(function () {

    'use strict';

    describe('Controller: QMe Admin Question Management Controller', function() {

        var rootScope,
            scope,
            state,
            stateParams,
            httpBackend,
            qmeContants
        ;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($rootScope,$state,$stateParams, $controller,$httpBackend,_QME_CONSTANTS_,_qmeQuestionService_,_qmePageSession_, _qmeFlashService_,_$timeout_) {
            rootScope = $rootScope;
            scope = $rootScope.$new();
            state = $state;
            stateParams = $stateParams;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;

        }));


        });

})();