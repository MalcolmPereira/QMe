(function () {

    'use strict';

    ngQMe

        .controller('qmeUserQuizCtrl', QMeUserQuizController);

    QMeUserQuizController.$inject = ['$scope','$timeout','$state','$stateParams','qmeFlashService','qmeQuizService','qmeUserService','qmeCategoryService','qmePageSession','qmeModelSession','QME_CONSTANTS'];

    function QMeUserQuizController($scope,$timeout,$state,$stateParams,qmeFlashService,qmeQuizService,qmeUserService,qmeCategoryService,qmePageSession,qmeModelSession,QME_CONSTANTS) {

        var qmeUserQuizCtrl = this;

        qmeUserQuizCtrl.listQuiz = function() {

        };
    }

})();