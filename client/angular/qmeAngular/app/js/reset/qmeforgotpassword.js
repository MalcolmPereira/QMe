(function () {
    'use strict';

    angular.module(qmeApp)
        .controller('qmeResetPasswordCtrl', QMeResetPasswordController);

    QMeResetPasswordController.$inject = ['$state','qmeFlashService','qmeUserService'];

    function QMeResetPasswordController($state,qmeFlashService,qmeUserService) {

        var qmeReset = this;

        qmeReset.userEmail = "";
        qmeReset.userPassword = "";
        qmeReset.userPasswordConfirm = "";

        qmeReset.validatePasswordFields = function (){
            if(qmeReset.userPassword != qmeReset.userPasswordConfirm){
                qmeFlashService.Error("Password do not match, please confirm password");
                qmeReset.userPasswordConfirm ="";
            }else{
                qmeFlashService.Clear();
            }
        }
        qmeReset.submitReset = function(){
            qmeUserService.resetPassword(qmeReset.userEmail);
        }

        qmeReset.submitResetPassword = function(){
        }
    }

})();