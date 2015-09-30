var qmeApp = "qmeApp";
var ngQMe  = angular.module(qmeApp, ['ui.router','ngResource','ngCookies','base64']);

(function () {

    'use strict';

    ngQMe
        .constant(
                'QME_CONSTANTS', {
                      success: 'success',
                      error: 'error',
                      qmeservice: 'http://localhost:8002/qme',
                      reseturl: 'http://localhost:8000/app/#/resetpassword/',
                      stageconfirmurl: 'http://localhost:8000/app/#/confirmuser/',
                      adminrole: 'ADMIN',
                      userrole: 'USER'
                }
        )
        .config(function($stateProvider, $urlRouterProvider,$httpProvider) {
            $httpProvider.defaults.withCredentials = true;
            $httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
            $httpProvider.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';
            $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

            $urlRouterProvider.otherwise('/home');

            $stateProvider

                .state('home', {
                    url: "/home",
                    templateUrl: 'js/home/qmehome.tmpl.html',
                    controller: 'qmeHomeCtrl',
                    controllerAs: 'qmeHomeCtrl'
                })
                .state('stage', {
                    url: "/stage",
                    templateUrl: 'js/user/qmestageuser.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl'
                })
                .state('confirmuser', {
                    url: "/confirmuser/:token/:stagetoken",
                    templateUrl: 'js/home/qmehome.tmpl.html',
                    controller: 'qmeHomeCtrl',
                    controllerAs: 'qmeHomeCtrl'
                })
                .state('register', {
                    url: "/register",
                    templateUrl: 'js/user/qmeregister.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl'
                })
                .state('forgotpassword', {
                    url: "/forgotpassword",
                    templateUrl: 'js/user/qmeforgotpassword.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl'
                })
                .state('resetpassword', {
                    url: "/resetpassword/:token/:username",
                    templateUrl: 'js/user/qmeresetpassword.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl'
                })
        })

})();
