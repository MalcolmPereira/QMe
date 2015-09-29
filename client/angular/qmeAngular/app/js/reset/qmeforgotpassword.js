(function () {
    'use strict';

    ngQMe
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
            qmeUserService
                .resetPassword(qmeReset.userEmail)
                .then(
                    function (res){
                        $state.go('home', {});
                        qmeFlashService.Success("User password reset request submitted successfully, please validate your email address to complete reset.");
                    },
                    function (error){
                        if(error && error.status && error.status == 404){
                            qmeFlashService.Error("Entered user email not found. Please ener valid existing user email.");

                        }else{
                            qmeFlashService.Error("Oops.....Error connecting to service for reset password, please retry in some time.");
                        }
                    }
                );
        };

        qmeReset.submitResetPassword = function(){
            qmeUserService
                   .submitResetPassword($stateParams.token,$stateParams.username,qmeReset.userEmail,qmeReset.userPassword)
                   .then(
                        function (res){
                            $state.go('home', {});
                        },
                        function (error){
                            if(error && error.status && error.status == 404) {
                                qmeFlashService.Error("Entered user email not found. Please ener valid existing user email.");

                            }else if(error && error.status && error.status == 400){
                                 qmeFlashService.Error("Reset token invalid, Please ener valid reset token.");

                            }else{
                                qmeFlashService.Error("Oops.....Error connecting to service for reset password, please retry in some time.");
                            }
                        }
                    );
            console.log("$stateParams.token",$stateParams.token);
            console.log("$stateParams.username",$stateParams.username);
        };
    }

})();