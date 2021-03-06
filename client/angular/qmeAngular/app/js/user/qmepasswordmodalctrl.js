(function () {

    'use strict';

    ngQMe
        .controller('qmePasswordModalCtrl', QMePasswordModalController);

    QMePasswordModalController.$inject = ['qmeModelSession'];

    function QMePasswordModalController(qmeModelSession) {

        var qmeUpdatePassword = this;

        qmeUpdatePassword.passwordForm = undefined;
        qmeUpdatePassword.currentPassword = "";
        qmeUpdatePassword.password = "";
        qmeUpdatePassword.passwordConfirm = "";
        qmeUpdatePassword.isPasswordError = false;

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
            qmeUpdatePassword.passwordForm.$setPristine();
            qmeUpdatePassword.currentPassword = "";
            qmeUpdatePassword.password = "";
            qmeUpdatePassword.passwordConfirm = "";
            qmeUpdatePassword.isPasswordError = false;
            $('#changePasswordModal').modal('hide');
            qmeModelSession.destroy();
        };

        qmeUpdatePassword.validatePasswordFields = function (){
            if(qmeUpdatePassword.password != qmeUpdatePassword.passwordConfirm){
              qmeUpdatePassword.isPasswordError = true;
              qmeUpdatePassword.passwordForm.$setValidity("invalidPassword",false,{});
              qmeUpdatePassword.passwordConfirm ="";
              return false;
            }else{
              qmeUpdatePassword.isPasswordError = false;
              qmeUpdatePassword.passwordForm.$setValidity();
              return true;
            }
        };

        qmeUpdatePassword.showPasswordError = function(){
            return qmeUpdatePassword.isPasswordError;
        };

        qmeUpdatePassword.isInValidForm = function(){
            return !(qmeUpdatePassword.currentPassword &&
                    qmeUpdatePassword.currentPassword.length > 0 &&
                qmeUpdatePassword.password &&
                qmeUpdatePassword.password.length > 0 &&
                qmeUpdatePassword.passwordConfirm &&
                qmeUpdatePassword.passwordConfirm.length > 0 &&
                qmeUpdatePassword.validatePasswordFields()
            );
        };
    }

})();