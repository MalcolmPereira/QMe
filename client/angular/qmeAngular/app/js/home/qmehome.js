(function () {
    'use strict';

    ngQMe
        .controller('qmeHomeCtrl', QMeHomeController);

    QMeHomeController.$inject = ['$state','$stateParams','qmeFlashService','qmeAuthService','qmeUserService'];


    function QMeHomeController($state,$stateParams,qmeFlashService,qmeAuthService,qmeUserService) {

        var qmeHome = this;

        qmeHome.confirmUser = function(){
            console.log("stagingToken",$stateParams.stagetoken);
        }

    }

})();
