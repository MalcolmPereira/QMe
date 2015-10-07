(function () {

    'use strict';

    ngQMe
        .controller('qmePasswordModalCtrl', QMePasswordModalController);

    QMePasswordModalController.$inject = ['qmeModelSession'];

    function QMePasswordModalController(qmeModelSession) {

        var qmeUpdatePassword = this;

        qmeUpdatePassword.passwordForm;
        qmeUpdatePassword.currentPassword = "";
        qmeUpdatePassword.password = "";
        qmeUpdatePassword.passwordConfirm = "";

        initPasswordForm();

        function initPasswordForm() {
            qmeUpdatePassword.currentPassword = "";
            qmeUpdatePassword.password = "";
            qmeUpdatePassword.passwordConfirm = "";
        }

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
            qmeUpdatePassword.currentPassword = "";
            qmeUpdatePassword.password = "";
            qmeUpdatePassword.passwordConfirm = "";
            qmeUpdatePassword.passwordForm.$setPristine();
            qmeUpdatePassword.passwordForm.$setUntouched();
            qmeUpdatePassword.passwordForm.$setValidity();
            qmeUpdatePassword.passwordForm.$setValidity("invalidPassword",true,null);
            qmeUpdatePassword.passwordForm.$dirty = false;
            console.log(qmeUpdatePassword.passwordForm.$error);
        };

        qmeUpdatePassword.validatePasswordFields = function (){
            if(qmeUpdatePassword.password != qmeUpdatePassword.passwordConfirm){
              qmeUpdatePassword.passwordForm.$setValidity("invalidPassword",false,{});
              qmeUpdatePassword.passwordConfirm ="";
            }else{
              qmeUpdatePassword.passwordForm.$setValidity("invalidPassword",true,null);
            }
        };
    }

})();