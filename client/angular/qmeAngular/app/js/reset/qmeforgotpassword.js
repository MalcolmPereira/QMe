(function () {
    'use strict';

    angular.module(qmeApp)
        .controller('qmeResetPasswordCtrl', QMeResetPasswordController);

    QMeResetPasswordController.$inject = ['$state','$stateParams','qmeFlashService','qmeAuthService','qmeUserService'];

    function QMeResetPasswordController($state,$stateParams,qmeFlashService,qmeAuthService,qmeUserService) {

        var qmeReset = this;

        //qmeReset.token = "";
        qmeReset.userEmail = "";
        qmeReset.userPassword = "";
        qmeReset.userPasswordConfirm = "";
        qmeAuthService.startResetting();

        qmeReset.validatePasswordFields = function (){
            if(qmeReset.userPassword != qmeReset.userPasswordConfirm){
                qmeFlashService.Error("Password do not match, please confirm password");
                qmeReset.userPasswordConfirm ="";
            }else{
                qmeFlashService.Clear();
            }
        };

        qmeReset.submitReset = function(){
            qmeUserService.resetPassword(qmeReset.userEmail);
        };

        qmeReset.submitResetPassword = function(){
            console.log("$stateParams.token",$stateParams.token);
        };
    }

})();