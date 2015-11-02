(function () {

    'use strict';

    ngQMe

        .controller('qmeUserManagementCtrl', QMeUserManagementController);

        QMeUserManagementController.$inject = ['$state','$stateParams','qmeFlashService','qmeUserService','qmePageSession'];

        function QMeUserManagementController($state,$stateParams,qmeFlashService,qmeUserService,qmePageSession) {

            var qmeUserManagement = this;

            qmeUserManagement.users;
            qmeUserManagement.usercount = 0;
            qmeUserManagement.currentpage = 0;
            qmeUserManagement.sortasc = true;
            qmeUserManagement.sortfields = "USERNAME";

            qmeUserManagement.updateUserForm;
            qmeUserManagement.userId;
            qmeUserManagement.userEmail;
            qmeUserManagement.userName;
            qmeUserManagement.userPassword;
            qmeUserManagement.userFirstName;
            qmeUserManagement.userLastName;
            qmeUserManagement.userRoles;

            qmeUserManagement.listUsers = function(){
                if(qmeUserManagement.usercount === 0){
                    qmeUserService.countUsers()
                        .then(
                        function(res){
                            qmeUserManagement.usercount = res.data.content;
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else {
                                qmeFlashService.Error("Oops.....Error from service for getting user count, please retry in some time.");
                            }
                            qmeUserManagement.usercount = -1;
                        }
                    );
                }
                qmeUserService.listUsersPaged(0,qmeUserManagement.sortasc,qmeUserManagement.sortfields)
                    .then(
                    function(res){
                       qmeUserManagement.users = res;
                    },
                    function(error){
                        if(error && error.status && error.status == 403) {
                            qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                        }else {
                            qmeFlashService.Error("Oops.....Error from service getting user lists, please retry in some time.");
                        }
                    }
                );
            };

            qmeUserManagement.setSortField = function(field){
                qmeUserManagement.sortasc = true;
                qmeUserManagement.sortfields = field;
                qmePageSession.create(qmeUserManagement.usercount);
                qmeUserManagement.pageUsers(0);
            };

            qmeUserManagement.isSortAsc = function(field){
                return (qmeUserManagement.sortasc &&  qmeUserManagement.sortfields === field);
            };

            qmeUserManagement.isSortDesc = function(field){
                return (!qmeUserManagement.sortasc &&  qmeUserManagement.sortfields === field);
            };


            qmeUserManagement.sortAsc = function(field){
                qmeUserManagement.sortasc = true;
                qmeUserManagement.sortfields = field;
                qmePageSession.create(qmeUserManagement.usercount);
                qmeUserManagement.pageUsers(0);
            };

            qmeUserManagement.sortDesc = function(field){
                qmeUserManagement.sortasc = false;
                qmeUserManagement.sortfields = field;
                qmePageSession.create(qmeUserManagement.usercount);
                qmeUserManagement.pageUsers(0);
            };

            qmeUserManagement.recordsLoaded = function(){
                return (qmeUserManagement.usercount > 0 );
            };

            qmeUserManagement.totalRecords = function(){
                return qmeUserManagement.usercount;
            };

           qmeUserManagement.pageUsers = function(pageNumber){
                qmeUserManagement.currentpage = pageNumber;
                qmeUserService.listUsersPaged(pageNumber, qmeUserManagement.sortasc, qmeUserManagement.sortfields)
                    .then(
                    function(res){
                        qmeUserManagement.users = res;
                    },
                    function(error){
                        if(error && error.status && error.status == 403) {
                            qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                        }else {
                            qmeFlashService.Error("Oops.....Error from service getting user lists, please retry in some time.");
                        }
                    }
                );
           };

           qmeUserManagement.updateUser = function(qmeuser){
                $state.go('updateuser', {currentuser:qmeuser});
           };

           qmeUserManagement.selectedUser = function(){
               qmeUserManagement.userId = $stateParams.currentuser.userId;
               qmeUserManagement.userEmail = $stateParams.currentuser.userEmail;
               qmeUserManagement.userName = $stateParams.currentuser.userName;
               qmeUserManagement.userPassword = $stateParams.currentuser.userName;
               qmeUserManagement.userFirstName = $stateParams.currentuser.userFirstName;
               qmeUserManagement.userLastName = $stateParams.currentuser.userLastName;
               qmeUserManagement.userRoles = $stateParams.currentuser.userRoles;
           }
        }

})();