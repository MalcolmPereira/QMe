var qmeApp;

(function () {
    'use strict';

    qmeApp = angular.module('qmeApp', ['ngRoute']).config(config);

    config.$inject = ['$routeProvider', '$locationProvider'];

    function config($routeProvider, $locationProvider) {
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
            .otherwise({ redirectTo: '/home' });
    }

})();