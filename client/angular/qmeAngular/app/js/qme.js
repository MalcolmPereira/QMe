var qmeApp;

(function () {

    'use strict';

    qmeApp = angular
        .module('qmeApp', ['ui.router','ngMockE2E'])

        .constant(
                'QME_CONSTANTS', {
                        success: 'success',
                        error: 'error',
                        authendpoint: '/login',
                        adminrole: 'admin'
                }
        )
        .service('qmeUserSession', function () {
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
        .config(function($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise('/home');

            $stateProvider

                .state('register', {
                    url: "/register",
                    templateUrl: 'js/register/qmeregister.tmpl.html',
                    controller: 'qmeRegisterCtrl',
                    controllerAs: 'qmeRegister'
                })
                .state('home', {
                    url: "/home",
                    templateUrl: 'js/home/qmehome.tmp.html',
                    controller: 'qmeHomeCtrl',
                    controllerAs: 'qmeHome'
                })
                .state('reset', {
                    url: "/reset",
                    templateUrl: 'js/reset/qmeforgotpassword.tmpl.html',
                    controller: 'qmeResetPasswordCtrl',
                    controllerAs: 'qmeReset'
                })
        })

        //Mock For Testing only
        .run(function($httpBackend) {

                //Dummy Login Method
                $httpBackend.whenPOST('/login').respond(function(method, url, data) {
                        var params = angular.fromJson(data);
                        console.log("got username in mock service",params.username);
                        console.log("got password in mock service",params.password);
                        var user = {
                            "id":1234,
                            "name":"test user",
                            "role": "user"
                        };
                        var adminuser = {
                            "id":1234,
                            "name":"admin user",
                            "role": "admin"
                        };

                        if(params.username.indexOf('admin') > -1){
                            return [200, adminuser,{}];
                        }else{
                            return [200, user,{}];
                        }

                });
                $httpBackend.whenGET(/js\//).passThrough();
    });

})();
