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
        };

        qmeRegister.registerUser = function (){
            qmeFlashService.Clear();

            var user = {
                "userName": qmeRegister.userName,
                "userPassword": qmeRegister.userPassword ,
                "userFirstName": qmeRegister.userFirstName,
                "userLastName": qmeRegister.userLastName,
                "userEmail": qmeRegister.userEmail
            }
            qmeUserService
                .register(user)
                .then(
                function(res){
                    $state.go('home', {});
                },
                function(error){
                    console.log("got error",error);
                    if(error && error.status && error.status == 400){
                        qmeFlashService.Error("Oops.....Invalid request for user registration, please make sure all required fields are valid.");

                    }else if(error && error.status && error.status == 409){
                        qmeFlashService.Error("Oops...User with same email address already exists please enter valid unique email address.");

                    }else{
                        qmeFlashService.Error("Oops.....Error registering new user, please retry in some time.");
                    }
                }
            );
        };
    }
})();