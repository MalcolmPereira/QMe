(function () {
    'use strict';

    angular.module(qmeApp)
        .controller('qmeResetPasswordCtrl', QMeResetPasswordController);

    QMeResetPasswordController.$inject = ['$state','qmeFlashService','qmeUserService'];

    function QMeResetPasswordController($state,qmeFlashService,qmeUserService) {

        var qmeReset = this;

        qmeReset.userEmail = "";

        qmeReset.submitReset = function(){
            qmeUserService.resetPassword(qmeReset.userEmail);
        }

    }

})();