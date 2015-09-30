var qmeApp = "qmeApp";
var ngQMe  = angular.module(qmeApp, ['ui.router','ngResource','base64']);

(function () {

    'use strict';

    ngQMe
        .constant(
                'QME_CONSTANTS', {
                      success: 'success',
                      error: 'error',
                      qmeservice: 'http://localhost:8080/qme',
                      reseturl: 'http://localhost:8000/app/#/resetpassword/',
                      adminrole: 'ADMIN',
                      userrole: 'USER'
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
        .service('qmeUserResource',function($http,$resource,QME_CONSTANTS){
            var userAPI                     = QME_CONSTANTS.qmeservice+"/user";
            var userAuthEndPoint            = userAPI+"/searchemail/";
            var userRegisterEndpoint        = userAPI+"/register";
            var userForgotPaswordEndpoint   = userAPI+"/reset/forgotpassword/";
            var userResetPaswordEndpoint    = userAPI+"/reset/resetpassword/";
            var userLogoutEndpoint          = QME_CONSTANTS.qmeservice+"/logout";

            this.userAuthResource = function(authToken,userEmail){
                $http.defaults.headers.common.Authorization = authToken;
                return $resource(userAuthEndPoint+userEmail);
            };
            this.userRegisterResource = function(){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userRegisterEndpoint);
            };
            this.userForgotPasswordResource = function(userEmail){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userForgotPaswordEndpoint+userEmail,null,{'reset':{method:'PUT'}});
            };
            this.userResetPasswordResource = function(userEmail){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userResetPaswordEndpoint+userEmail,{},{'resetpassword':{method:'PUT'}});
            };
            this.logoutResource = function(){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userLogoutEndpoint);
            }
        })
})();
