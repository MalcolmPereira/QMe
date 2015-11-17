(function () {

    'use strict';

    ngQMe

        .controller('qmeUserManagementCtrl', QMeUserManagementController);

    QMeUserManagementController.$inject = ['$state','$stateParams','qmeFlashService','qmeUserService','qmePageSession','QME_CONSTANTS'];

    function QMeUserManagementController($state,$stateParams,qmeFlashService,qmeUserService,qmePageSession,QME_CONSTANTS) {

        var qmeUserManagement = this;

        qmeUserManagement.users = undefined;
        qmeUserManagement.usercount = 0;
        qmeUserManagement.currentpage = 0;
        qmeUserManagement.sortasc = true;
        qmeUserManagement.sortfields = "USERNAME";

        qmeUserManagement.updateUserForm = undefined;
        qmeUserManagement.userId = undefined;
        qmeUserManagement.userEmail = undefined;
        qmeUserManagement.userName = undefined;
        qmeUserManagement.userPassword = undefined;
        qmeUserManagement.userFirstName = undefined;
        qmeUserManagement.userLastName = undefined;
        qmeUserManagement.userRoles  = undefined;
        qmeUserManagement.userRole = undefined;
        qmeUserManagement.reviewerRole = undefined;
        qmeUserManagement.moderatorRole = undefined;

        qmeUserManagement.listUsers = function(){

            if($stateParams.sortasc === undefined ||  $stateParams.sortasc === null) {
                qmeUserManagement.sortasc = true;
            }else{
                qmeUserManagement.sortasc = $stateParams.sortasc;
            }

            if($stateParams.sortfields &&  $stateParams.sortfields !== null) {
                qmeUserManagement.sortfields = $stateParams.sortfields;
            }else{
                qmeUserManagement.sortfields = "USERNAME";
            }

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
                        if($stateParams.currentpage &&  $stateParams.currentpage !== null){
                            qmeUserManagement.pageUsers($stateParams.currentpage);
                            qmePageSession.setPageState($stateParams.currentpage);
                        }
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
            $state.go('updateuser',{
                    currentuser:qmeuser,
                    currentpage:qmeUserManagement.currentpage,
                    sortasc:qmeUserManagement.sortasc,
                    sortfields:qmeUserManagement.sortfields
                }
            );
        };

        qmeUserManagement.submitUpdateUser = function(){
            var updatedUserRoles =  [];
            if(qmeUserManagement.userRole && qmeUserManagement.userRole !== ""){
                updatedUserRoles.push(QME_CONSTANTS.userrole);
            }
            if(qmeUserManagement.reviewerRole && qmeUserManagement.reviewerRole !== ""){
                updatedUserRoles.push(QME_CONSTANTS.reviewerrole);
            }
            if(qmeUserManagement.moderatorRole && qmeUserManagement.moderatorRole !== ""){
                updatedUserRoles.push(QME_CONSTANTS.moderatorrole);
            }
            var updateUser = {
                "userId": qmeUserManagement.userId,
                "userName": qmeUserManagement.userName,
                "userEmail": qmeUserManagement.userEmail,
                "userFirstName": qmeUserManagement.userFirstName,
                "userLastName": qmeUserManagement.userLastName,
                "userRoles": updatedUserRoles
            };
            qmeUserService
                .updateUser(updateUser,qmeUserManagement.userId)
                .then(
                    function(res){
                        qmeFlashService.Success("User profile update successful.",false);
                    },
                    function(error){
                        if(error && error.status && error.status == 404){
                            qmeFlashService.Error("Oops.....Invalid request for user update, user not found.");

                        }else if(error && error.status && error.status == 403){
                            qmeFlashService.Error("Oops.....User not authorized to update user update.");

                        }else if(error && error.status && error.status == 400){
                            qmeFlashService.Error("Oops.....Invalid request for user update.");

                        }else{
                            qmeFlashService.Error("Oops.....Error registering new user, please retry in some time.");
                        }
                    }
                );
        };

        qmeUserManagement.cancelUserUpdate = function(){
            $state.go('listusers', {
                    currentpage:qmeUserManagement.currentpage,
                    sortasc:qmeUserManagement.sortasc,
                    sortfields:qmeUserManagement.sortfields
                }
            );
        };

        qmeUserManagement.selectedUser = function(){
            qmeUserManagement.userId = $stateParams.currentuser.userId;
            qmeUserManagement.userEmail = $stateParams.currentuser.userEmail;
            qmeUserManagement.userName = $stateParams.currentuser.userName;
            qmeUserManagement.userPassword = $stateParams.currentuser.userName;
            qmeUserManagement.userFirstName = $stateParams.currentuser.userFirstName;
            qmeUserManagement.userLastName = $stateParams.currentuser.userLastName;
            qmeUserManagement.userRoles = $stateParams.currentuser.userRoles;
            if(qmeUserManagement.userRoles.indexOf(QME_CONSTANTS.userrole) > -1){
                qmeUserManagement.userRole = QME_CONSTANTS.userrole;
            }else{
                qmeUserManagement.userRole = "";
            }
            if(qmeUserManagement.userRoles.indexOf(QME_CONSTANTS.reviewerrole) > -1){
                qmeUserManagement.reviewerRole = QME_CONSTANTS.reviewerrole;
            }else{
                qmeUserManagement.reviewerRole = "";
            }
            if(qmeUserManagement.userRoles.indexOf(QME_CONSTANTS.moderatorrole) > -1){
                qmeUserManagement.moderatorRole = QME_CONSTANTS.moderatorrole;
            }else{
                qmeUserManagement.moderatorRole = "";
            }
            qmeUserManagement.currentpage= $stateParams.currentpage;
            if($stateParams.sortasc === undefined ||  $stateParams.sortasc === null) {
                qmeUserManagement.sortasc = true;
            }else{
                qmeUserManagement.sortasc = $stateParams.sortasc;
            }
            if($stateParams.sortfields &&  $stateParams.sortfields !== null) {
                qmeUserManagement.sortfields = $stateParams.sortfields;
            }
        };
    }

})();