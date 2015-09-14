var qmeApp = "qmeApp";

(function () {

    'use strict';

    angular

        .module(qmeApp, ['ui.router','ngResource','base64'])

        .constant(
                'QME_CONSTANTS', {
                      success: 'success',
                      error: 'error',
                      reseturl: 'http://localhost:8000/app/#/resetpassword/',
                      serviceurl: 'http://localhost:8080/qme/',
                      userapi: 'user/',
                      authendpoint: 'http://localhost:8080/qme/user/searchemail/',
                      adminrole: 'ADMIN'
                }
        )
        .config(function($stateProvider, $urlRouterProvider) {

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
