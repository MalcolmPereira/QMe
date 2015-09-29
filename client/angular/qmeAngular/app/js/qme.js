var qmeApp = "qmeApp";
var ngQMe  = angular.module(qmeApp, ['ui.router','ngResource','base64']);

(function () {

    'use strict';

    ngQMe
        .constant(
                'QME_CONSTANTS', {
                      success: 'success',
                      error: 'error',
                      reseturl: 'http://localhost:8000/app/#/resetpassword/',
                      serviceurl: 'http://localhost:8080/qme/',
                      userapi: 'user/',
                      authendpoint: 'http://localhost:8080/qme/user/searchemail/',
                      logoutendpoint: 'http://localhost:8080/qme/logout',
                      adminrole: 'ADMIN'
                }
        )
        .config(function($stateProvider, $urlRouterProvider,$httpProvider) {

            $httpProvider.defaults.xsrfCookieName = 'csrftoken';
            $httpProvider.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';
            $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

            $urlRouterProvider.otherwise('/home');

            $stateProvider

                .state('home', {
                    url: "/home",
                    templateUrl: 'js/home/qmehome.tmpl.html',
                    controller: 'qmeHomeCtrl',
                    controllerAs: 'qmeHome'
                })
                .state('register', {
                    url: "/register",
                    templateUrl: 'js/register/qmeregister.tmpl.html',
                    controller: 'qmeRegisterCtrl',
                    controllerAs: 'qmeRegister'
                })
                .state('reset', {
                    url: "/reset",
                    templateUrl: 'js/reset/qmeforgotpassword.tmpl.html',
                    controller: 'qmeResetPasswordCtrl',
                    controllerAs: 'qmeReset'
                })
                .state('resetpassword', {
                    url: "/resetpassword/:token/:username",
                    templateUrl: 'js/reset/qmeresetpassword.tmpl.html',
                    controller: 'qmeResetPasswordCtrl',
                    controllerAs: 'qmeReset'
                })
        })
})();
