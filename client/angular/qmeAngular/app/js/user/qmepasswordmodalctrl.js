(function () {

    'use strict';

    ngQMe
        .controller('qmePasswordModalCtrl', QMePasswordModalController);

    QMePasswordModalController.$inject = ['qmeModelSession'];

    function QMePasswordModalController(qmeModelSession) {

        var qmeUpdatePassword = this;

        qmeUpdatePassword.currentPassword = "";
        qmeUpdatePassword.password = "";
        qmeUpdatePassword.passwordConfirm = "";

        qmeUpdatePassword.save = function(){
            $('#changePasswordModal').modal('hide');
            qmeModelSession.create({
                    "currentPassword":qmeUpdatePassword.currentPassword,
                    "password":qmeUpdatePassword.password,
                    "passwordConfirm":qmeUpdatePassword.passwordConfirm
                }
            );
        };

        qmeUpdatePassword.cancel = function(){
            $('#changePasswordModal').modal('hide');
            qmeModelSession.destroy();
        };

        qmeUpdatePassword.validatePasswordFields = function (){
            if(qmeUpdatePassword.password != qmeUpdatePassword.passwordConfirm){
              qmeUpdatePassword.passwordForm.$setValidity("invalidPassword",false,{});
              qmeUpdatePassword.passwordConfirm ="";
            }
        };
    }

})();