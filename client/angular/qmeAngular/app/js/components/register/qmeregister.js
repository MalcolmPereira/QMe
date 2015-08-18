(function () {
    'use strict';

    qmeApp.controller('qmeRegister', QMeRegisterController);

    QMeRegisterController.$inject = ['qmeFlashService'];

    function QMeRegisterController(qmeFlashService) {

        var qmeRegister = this;

        qmeRegister.userEmail = "";
        qmeRegister.userName = "";
        qmeRegister.userPassword = "";
        qmeRegister.userPasswordConfirm = "";
        qmeRegister.userFirstName = "";
        qmeRegister.userLastName = "";

        qmeRegister.validatePasswordFields = validatePassword;

        function validatePassword(){
            if(qmeRegister.userPassword != qmeRegister.userPasswordConfirm){
                qmeFlashService.Error("Password do not match, please confirm password");
                qmeRegister.userPasswordConfirm ="";
            }else{
                qmeFlashService.Clear();
            }
        }

        function registerUser(){
            //TODO:
            //Make http call to service and log in user
            //qmeFlashService.Error("testing");
            //qmeHeader.userName = "tocallservice";
        }

    }

})();