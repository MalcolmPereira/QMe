var qmeApp = "qmeApp";
var ngQMe  = angular.module(qmeApp, ['ui.router','ngResource','ngMessages','base64', 'flow']);

(function () {

    'use strict';

    ngQMe
        .constant(
                'QME_CONSTANTS', {
                      qmeservice: 'http://10.85.22.37:8002/qme',
                      reseturl: 'http://localhost:8000/app/#/resetpassword/',
                      stageconfirmurl: 'http://localhost:8000/app/#/confirmuser/',
                      qme_auth_header:'QME-AUTH-TOKEN',
                      password_mask:'**********',
                      rowsperpage:50,
                      pagesperpage:10,
                      success: 'success',
                      error: 'error',
                      userrole: 'USER',
                      reviewerrole: 'REVIEWER',
                      moderatorrole: 'MODERATOR',
                      adminrole: 'ADMIN'
                }
        )
        .run(function($rootScope,$window,$state,qmeUserSession){
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
            $rootScope.$on('$stateChangeStart',
                function(event, toState){
                    if(toState.authenticate && !qmeUserSession.isSignedIn()){
                        event.preventDefault();
                    }
                    if(toState.authenticate && toState.admin && qmeUserSession.isSignedIn() &&
                        !qmeUserSession.isAdmin()){
                            event.preventDefault();
                    }
                }
            );
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
                    controllerAs: 'qmeHomeCtrl',
                    authenticate: false,
                    admin: false
                })

                //User Routing
                .state('stage', {
                    url: "/stage",
                    templateUrl: 'js/user/qmestageuser.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl',
                    authenticate: false,
                    admin: false
                })
                .state('confirmuser', {
                    url: "/confirmuser/:stagetoken",
                    templateUrl: 'js/home/qmehome.tmpl.html',
                    controller: 'qmeHomeCtrl',
                    controllerAs: 'qmeHomeCtrl',
                    authenticate: false,
                    admin: false,
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
                    controllerAs: 'qmeUserCtrl',
                    authenticate: true,
                    admin: false
                })
               .state('register', {
                    url: "/register",
                    templateUrl: 'js/user/qmeregister.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl',
                    authenticate: true,
                    admin: true
                })
                .state('forgotpassword', {
                    url: "/forgotpassword",
                    templateUrl: 'js/user/qmeforgotpassword.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl',
                    authenticate: false,
                    admin: false
                })
                .state('resetpassword', {
                    url: "/resetpassword/:token/:username",
                    templateUrl: 'js/user/qmeresetpassword.tmpl.html',
                    controller: 'qmeUserCtrl',
                    controllerAs: 'qmeUserCtrl',
                    authenticate: false,
                    admin: false
                })
                .state('listusers', {
                    url: "/listusers",
                    templateUrl: 'js/admin/user/qmeuserlist.tmpl.html',
                    controller: 'qmeUserManagementCtrl',
                    controllerAs: 'qmeUserManagementCtrl',
                    authenticate: true,
                    admin: true,
                    params: {
                        currentpage: null,
                        sortasc:null,
                        sortfields:null
                    }
                })
                .state('adduser', {
                    url: "/adduser",
                    templateUrl: 'js/admin/user/qmeadduser.tmpl.html',
                    controller: 'qmeUserManagementCtrl',
                    controllerAs: 'qmeUserManagementCtrl',
                    authenticate: true,
                    admin: true,
                })
                .state('updateuser', {
                    url: "/updateuser",
                    templateUrl: 'js/admin/user/qmeupdateuser.tmpl.html',
                    controller: 'qmeUserManagementCtrl',
                    controllerAs: 'qmeUserManagementCtrl',
                    authenticate: true,
                    admin: true,
                    params: {
                        currentuser: null,
                        currentpage: null,
                        sortasc:null,
                        sortfields:null
                    }
                })

                //Category Routing
                .state('listcategories', {
                    url: "/listcategories",
                    templateUrl: 'js/admin/category/qmecategorylist.tmpl.html',
                    controller: 'qmeCategoryManagementCtrl',
                    controllerAs: 'qmeCategoryManagementCtrl',
                    authenticate: true,
                    admin: true
                })

                //Question Routing
                .state('listquestions', {
                    url: "/listquestions",
                    templateUrl: 'js/admin/question/qmequestionlist.tmpl.html',
                    controller: 'qmeQuestionManagementCtrl',
                    controllerAs: 'qmeQuestionManagementCtrl',
                    authenticate: true,
                    admin: true
                })
                .state('addquestion', {
                    url: "/addquestion",
                    templateUrl: 'js/admin/question/qmeaddquestion.tmpl.html',
                    controller: 'qmeQuestionManagementCtrl',
                    controllerAs: 'qmeQuestionManagementCtrl',
                    authenticate: true,
                    admin: true
                })
                .state('updatequestion', {
                    url: "/updatequestion",
                    templateUrl: 'js/admin/question/qmeupdatequestion.tmpl.html',
                    controller: 'qmeQuestionManagementCtrl',
                    controllerAs: 'qmeQuestionManagementCtrl',
                    authenticate: true,
                    admin: true,
                    params: {
                        currentQuestion: null,
                        currentpage: null,
                        sortasc:null,
                        sortfields:null
                    }
                })

                //Quiz Routing Quizzes
                .state('listquizzes', {
                    url: "/listquizzes",
                    templateUrl: 'js/admin/quiz/qmequizlist.tmpl.html',
                    controller: 'qmeQuizManagementCtrl',
                    controllerAs: 'qmeQuizManagementCtrl',
                    authenticate: true,
                    admin: true
                })
                .state('addquiz', {
                    url: "/addquiz",
                    templateUrl: 'js/admin/quiz/qmeaddquiz.tmpl.html',
                    controller: 'qmeQuizManagementCtrl',
                 controllerAs: 'qmeQuizManagementCtrl',
                    authenticate: true,
                    admin: true
                 })
                .state('updatequiz', {
                    url: "/updatequiz",
                    templateUrl: 'js/admin/quiz/qmeupdatequiz.tmpl.html',
                    controller: 'qmeQuizManagementCtrl',
                    controllerAs: 'qmeQuizManagementCtrl',
                    authenticate: true,
                    admin: true
                });

        });

})();


var pleaseWait = (function () {

    'use strict';

    var pleaseWaitDiv = $(
        '<div id="pleaseWaitDivID" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">'+'<div class="modal-dialog modal-m">'+'<div style="text-align:center;color:#1bc2d3">'+'<i class="glyphicon glyphicon-hourglass"></i>Processing..'+'</div>'+'</div>'+'</div>'
        );

    return {
        showPleaseWait: function() {
            pleaseWaitDiv.modal();
        },
        hidePleaseWait: function () {
            pleaseWaitDiv.modal('hide');
            $('.modal-backdrop').remove();
        },
    };
})();
