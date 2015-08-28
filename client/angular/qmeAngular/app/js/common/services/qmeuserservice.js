(function () {

    'use strict';

    angular.module(qmeApp)
        .service('qmeUserService', QMeUserService);

    QMeUserService.$inject = ['$http','$resource','$base64','qmeFlashService','qmeUserSession','QME_CONSTANTS'];

    function QMeUserService($http,$resource,$base64,qmeFlashService,qmeUserSession,QME_CONSTANTS) {

        var qmeUserService = this;

    }
});