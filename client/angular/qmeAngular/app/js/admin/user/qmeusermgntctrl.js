(function () {

    'use strict';

    ngQMe

        .controller('qmeUserManagementCtrl', QMeUserManagementController);

        QMeUserManagementController.$inject = ['$state','qmeFlashService','qmeUserService','qmePageSession'];

        function QMeUserManagementController($state,qmeFlashService,qmeUserService,qmePageSession) {

            var qmeUserManagement = this;

            qmeUserManagement.users;
            qmeUserManagement.usercount = 0;
            qmeUserManagement.currentpage = 0;
            qmeUserManagement.sortasc = true;
            qmeUserManagement.sortfields = "USERNAME";

            listUsers();

            function listUsers(){
                if(qmeUserManagement.usercount === 0){
                    qmeUserService.countUsers()
                        .then(
                        function(res){
                            qmeUserManagement.usercount = res.data;
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
                        qmeFlashService.Error("Oops.....Error from service getting user lists, please retry in some time.");
                    }
                );
            };

            qmeUserManagement.setSortField = function(field){
                qmeUserManagement.sortasc = true;
                qmeUserManagement.sortfields = field;
                qmePageSession.create(qmeUserManagement.usercount);
                qmeUserManagement.pageUsers(0);
            }

            qmeUserManagement.isSortAsc = function(field){
                return (qmeUserManagement.sortasc &&  qmeUserManagement.sortfields === field);
            }

            qmeUserManagement.isSortDesc = function(field){
                return (!qmeUserManagement.sortasc &&  qmeUserManagement.sortfields === field);
            }


            qmeUserManagement.sortAsc = function(field){
                qmeUserManagement.sortasc = true;
                qmeUserManagement.sortfields = field;
                qmePageSession.create(qmeUserManagement.usercount);
                qmeUserManagement.pageUsers(0);
            }

            qmeUserManagement.sortDesc = function(field){
                qmeUserManagement.sortasc = false;
                qmeUserManagement.sortfields = field;
                qmePageSession.create(qmeUserManagement.usercount);
                qmeUserManagement.pageUsers(0);
            }

            qmeUserManagement.recordsLoaded = function(){
                return (qmeUserManagement.usercount > 0 );
            }

            qmeUserManagement.totalRecords = function(){
                return qmeUserManagement.usercount;
            }

           qmeUserManagement.pageUsers = function(pageNumber){
                qmeUserManagement.currentpage = pageNumber;
                qmeUserService.listUsersPaged(pageNumber, qmeUserManagement.sortasc, qmeUserManagement.sortfields)
                    .then(
                    function(res){
                        qmeUserManagement.users = res;
                    },
                    function(error){
                        qmeFlashService.Error("Oops.....Error from service getting user lists, please retry in some time.");
                    }
                );
            }

        }

})();