var qmeApp = "qmeApp";
var ngQMe  = angular.module(qmeApp, ['ui.router','ngResource','base64']);

(function () {

    'use strict';

     ngQMe
        .constant(
                'QME_CONSTANTS', {
                      qmeservice: 'http://localhost:8002/qme',
                      reseturl: 'http://localhost:8000/app/#/resetpassword/',
                      stageconfirmurl: 'http://localhost:8000/app/#/confirmuser/',
                      qme_auth_header:'QME-AUTH-TOKEN',
                      success: 'success',
                      error: 'error',
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
                    url: "/confirmuser/:stagetoken",
                    templateUrl: 'js/home/qmehome.tmpl.html',
                    controller: 'qmeHomeCtrl',
                    controllerAs: 'qmeHomeCtrl',
                    onEnter: function($stateParams,qmeFlashService,qmeUserService){
                        console.log("can we do someting",qmeUserService);
                        console.log("can we do someting", $stateParams.stagetoken);
                        qmeFlashService.Success(" I think we can so someting with token "+$stateParams.stagetoken,true);


                    }
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


var pleaseWait = (function () {

    'use strict';

    var pleaseWaitDiv = $(
        '<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">'
        +
            '<div class="modal-dialog modal-m">'
        +
                    '<div style="text-align:center;color:#32d39b">'
        +
                            '<i class="glyphicon glyphicon-hourglass"></i>Processing..'
        +
                    '</div>'
        +
            '</div>'
        +
        '</div>'
    );
    return {
        showPleaseWait: function() {
            pleaseWaitDiv.modal();
        },
        hidePleaseWait: function () {
            pleaseWaitDiv.modal('hide');
        },
    };
})();
