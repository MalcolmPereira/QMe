(function () {
    'use strict';

    angular.module(qmeApp)
        .service('qmeUserService', QMeUserService);

    QMeUserService.$inject = ['$resource','qmeFlashService','qmeUserSession','QME_CONSTANTS'];

    function QMeUserService($resource,qmeFlashService,qmeUserSession,QME_CONSTANTS) {

    }
});