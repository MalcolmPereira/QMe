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
              console.log(qmeUpdatePassword.passwordForm) ;
              console.log(qmeUpdatePassword.passwordForm.$error) ;
              console.log(qmeUpdatePassword.passwordForm.$error.invalidPassword   ) ;
              qmeUpdatePassword.passwordConfirm ="";
            }else{
              qmeUpdatePassword.passwordForm.$setValidity("invalidPassword",true);
            }
        };
    }

})();