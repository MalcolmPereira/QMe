var qmeApp;

(function () {
    'use strict';

    qmeApp = angular
        .module('qmeApp', ['ngMockE2E','ngRoute'])
        .constant(
            'AUTH_EVENTS', {
                    loginSuccess: 'auth-login-success',
                    loginFailed: 'auth-login-failed',
                    logoutSuccess: 'auth-logout-success',
                    sessionTimeout: 'auth-session-timeout',
                    notAuthenticated: 'auth-not-authenticated',
                    notAuthorized: 'auth-not-authorized'
             }
        )
        .constant(
            'USER_ROLES', {
                admin: 'admin',
                user: 'user'
            }
        )
        .service('qmeSession', function () {
            this.create = function (sessionId, userId, userName, userRole) {
                this.id = sessionId;
                this.userId = userId;
                this.userName = userName;
                this.userRole = userRole;
            };
            this.destroy = function () {
                this.id = null;
                this.userId = null;
                this.userName = null;
                this.userRole = null;
            };
        })
        .config(config);

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