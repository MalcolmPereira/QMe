(function () {

    'use strict';

    ngQMe

        .controller('qmeUserManagementCtrl', QMeUserManagementController);

        QMeUserManagementController.$inject = ['$state','qmeFlashService','qmeUserService'];

        function QMeUserManagementController($state,qmeFlashService,qmeUserService) {

            var qmeUserManagement = this;

            qmeUserManagement.users;
            qmeUserManagement.usercount = 0;
            qmeUserManagement.currentpage = 0;

            listUsers();

            function listUsers(){
                if(qmeUserManagement.usercount === 0){
                    qmeUserService.countUsers()
                        .then(
                        function(res){
                            qmeUserManagement.usercount = res.data;
                        },
                        function(error){
                            qmeFlashService.Error("Oops.....Error from service for getting user count, please retry in some time.");
                        }
                    );
                }
                qmeUserService.listUsersPaged(0)
                    .then(
                    function(res){
                       qmeUserManagement.users = res;
                    },
                    function(error){
                        qmeFlashService.Error("Oops.....Error from service getting user lists, please retry in some time.");
                    }
                );
            };

            qmeUserManagement.pageUsers = function(pageNumber){
                qmeUserManagement.currentpage = pageNumber;
                qmeUserService.listUsersPaged(pageNumber)
                    .then(
                    function(res){
                        qmeUserManagement.users = res;
                    },
                    function(error){
                        qmeFlashService.Error("Oops.....Error from service getting user lists, please retry in some time.");
                    }
                );
            }

            qmeUserManagement.isCurrentUserPage = function(pageNumber){
                return (pageNumber === qmeUserManagement.currentpage );
            };

        }

})();