(function () {

    'use strict';

    ngQMe

        .controller('qmeUserManagementCtrl', QMeUserManagementController);

        QMeUserManagementController.$inject = ['$state','qmeFlashService','qmeUserService'];

        function QMeUserManagementController($state,qmeFlashService,qmeUserService) {

            var qmeUserManagement = this;

            qmeUserManagement.users;

            listUsers();

            function listUsers(){
                qmeUserService.listUsers()
                    .then(
                    function(res){
                       qmeUserManagement.users = res;
                    },
                    function(error){
                        qmeFlashService.Error("Oops.....Error from service for reset password, please retry in some time.");
                    }
                );
            };

        }

})();