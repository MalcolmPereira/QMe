var qmeApp = "qmeApp";
var ngQMe  = angular.module(qmeApp, ['ui.router','ngResource','ngMessages','base64']);

(function () {

    'use strict';

    ngQMe
        .constant(
                'QME_CONSTANTS', {
                      qmeservice: 'http://localhost:8002/qme',
                      reseturl: 'http://localhost:8000/app/#/resetpassword/',
                      stageconfirmurl: 'http://localhost:8000/app/#/confirmuser/',
                      qme_auth_header:'QME-AUTH-TOKEN',
                      password_mask:'**********',
                      rowsperpage:50,
                      pagesperpage:10,
                      success: 'success',
                      error: 'error',
                      adminrole: 'ADMIN',
                      userrole: 'USER'
                }
        )
        .run(function($window,$state,qmeUserSession){
            $window.onbeforeunload = function (event) {
                if (typeof event === 'undefined') {
                    event = window.event;
                }
                if(qmeUserSession.isSignedIn()){
                    event.preventDefault = true;
                    event.cancelBubble = true;
                    event.returnValue = 'You are exiting out from the qme application.';
                }
            };
            $window.onload = function (event) {
                $state.go('home', {});
                qmeUserSession.destroy();
            };
        })
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

                //User Routing
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
                        if($stateParams.stagetoken && $stateParams.stagetoken.length > 0){

                            qmeUserService
                                .confirmUser($stateParams.stagetoken)
                                .then(
                                function(res){
                                    qmeFlashService.Success("User registration completed successfully, please login using your credentials, Thank you.",true);
                                },
                                function(error){
                                    qmeFlashService.Error("Oops.....Error confirming user registration, registration token invalid/expired, please re-try request or register.");
                                });
                        }
                    }
                })
                .state('userprofile', {
                    url: "/userprofile",
                    templateUrl: 'js/user/qmeuserprofile.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl'
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
                .state('listusers', {
                    url: "/listusers",
                    templateUrl: 'js/admin/user/qmeuserlist.tmpl.html',
                    controller: 'qmeUserManagementCtrl',
                    controllerAs: 'qmeUserManagementCtrl',
                })

                //Category Routing
                .state('listcategories', {
                    url: "/listcategories",
                    templateUrl: 'js/admin/category/qmecategorylist.tmpl.html',
                    controller: 'qmeCategoryManagementCtrl',
                    controllerAs: 'qmeCategoryManagementCtrl'
                })

                //Question Routing
                .state('listquestions', {
                    url: "/listquestions",
                    templateUrl: 'js/admin/question/qmequestionlist.tmpl.html',
                    controller: 'qmeQuestionManagementCtrl',
                    controllerAs: 'qmeQuestionManagementCtrl'
                })

                //Quiz Routing Quizzes
                .state('listquizzes', {
                    url: "/listquizzes",
                    templateUrl: 'js/admin/quiz/qmequizlist.tmpl.html',
                    controller: 'qmeQuizManagementCtrl',
                    controllerAs: 'qmeQuizManagementCtrl'
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
                    '<div style="text-align:center;color:#1bc2d3">'
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
