var qmeApp = "qmeApp";

(function () {

    'use strict';

    angular

        .module(qmeApp, ['ui.router','ngResource','base64','ngMockE2E'])

        .constant(
                'QME_CONSTANTS', {
                      success: 'success',
                      error: 'error',
                      serviceurl: 'http://localhost:8080/qme',
                      //authendpoint: 'http://localhost:8080/qme/user/searchemail/',
                      authendpoint: '/login/',
                      adminrole: 'admin'
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
        })


        //Mock For Testing only
        .run(function($httpBackend) {
                $httpBackend.whenGET('/login/user@user').respond(function(method, url, data) {
                    var user = {
                        "userName": "testuser",
                        "userPassword": null,
                        "userFirstName": "Test",
                        "userLastName": "User",
                        "userEmail": "test.user@gmail.com",
                        "userId": 1,
                        "userRegisteredDate": "2015-28-05 13:35:29",
                        "userUpdateDate": "2015-28-05 13:35:29",
                        "updateUserID": 0,
                        "updateUserName": "",
                        "role": ['user']
                    };
                    return [200, user,{}];
                });
                $httpBackend.whenGET('/login/admin@admin').respond(function(method, url, data) {
                    var adminuser = {
                        "userName": "testadmin",
                        "userPassword": null,
                        "userFirstName": "Test",
                        "userLastName": "Admin",
                        "userEmail": "test.admin@gmail.com",
                        "userId": 1,
                        "userRegisteredDate": "2015-28-05 13:35:29",
                        "userUpdateDate": "2015-28-05 13:35:29",
                        "updateUserID": 0,
                        "updateUserName": "",
                        "role": ['admin','user']
                    };
                    return [200, adminuser,{}];
                });
                $httpBackend.whenGET(/js\//).passThrough();

    });
})();
