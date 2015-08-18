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

        function validatePassword(){
            //TODO:
            //Make http call to service and log in user
            //qmeFlashService.Error("testing");
            qmeHeader.userName = "tocallservice";
        }

        function registerUser(){
            //TODO:
            //Make http call to service and log in user
            //qmeFlashService.Error("testing");
            //qmeHeader.userName = "tocallservice";
        }

    }

})();