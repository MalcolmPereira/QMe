var qmeApp;

(function () {
    'use strict';

    qmeApp = angular.module('qmeApp', ['ngRoute']).config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
            .when('/register', {
                controller: 'qmeRegister',
                templateUrl: 'js/components/register/qmeregister.html',
                controllerAs: 'qmeRegister'
            })
            .when('/home', {
                controller: 'qmeHome',
                templateUrl: 'js/components/home/qmemain.html',
                controllerAs: 'qmeHome'
            })
            .when('/reset', {
                controller: 'qmeResetPassword',
                templateUrl: 'js/components/reset/qmeforgotpassword.html',
                controllerAs: 'qmeResetPassword'
            })
            .otherwise({ redirectTo: '/home' });
    }

})();