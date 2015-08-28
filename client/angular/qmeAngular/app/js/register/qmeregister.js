(function () {
    'use strict';

    angular.module(qmeApp)
        .controller('qmeRegisterCtrl', QMeRegisterController);

    QMeRegisterController.$inject = ['$state','qmeFlashService','qmeUserService'];

    function QMeRegisterController($state,qmeFlashService,qmeUserService) {

        var qmeRegister = this;

        qmeRegister.userEmail = "";
        qmeRegister.userName = "";
        qmeRegister.userPassword = "";
        qmeRegister.userPasswordConfirm = "";
        qmeRegister.userFirstName = "";
        qmeRegister.userLastName = "";

        qmeRegister.validatePasswordFields = function (){
            if(qmeRegister.userPassword != qmeRegister.userPasswordConfirm){
                qmeFlashService.Error("Password do not match, please confirm password");
                qmeRegister.userPasswordConfirm ="";
            }else{
                qmeFlashService.Clear();
            }
        }

        qmeRegister.registerUser = function (){
            var user = {
                "userName": qmeRegister.userName,
                "userPassword": qmeRegister.userPassword ,
                "userFirstName": qmeRegister.userFirstName,
                "userLastName": qmeRegister.userLastName,
                "userEmail": qmeRegister.userEmail
            }
            qmeUserService.register(user);
        }

    }

})();