(function () {

    "use strict";

    ngQMe
        .service('qmeUserService',QMeUserService);


    QMeUserService.$inject = ['$q','qmeUserResource','qmeUserSession','QME_CONSTANTS'];


    function QMeUserService($q,qmeUserResource,qmeUserSession,QME_CONSTANTS) {

        var qmeUserService = this;

        qmeUserService.registering = false;

        qmeUserService.resetting   = false;

        qmeUserService.startRegistering = function(){
            qmeUserService.registering = true;
        };

        qmeUserService.endRegistering = function(){
            qmeUserService.registering = false;
        };

        qmeUserService.isRegistering = function(){
            return qmeUserService.registering;
        };

        qmeUserService.startResetting = function(){
            qmeUserService.resetting  = true;
        };

        qmeUserService.endResetting = function(){
            qmeUserService.resetting   = false;
        };

        qmeUserService.isResetting = function(){
            return qmeUserService.resetting;
        };

        qmeUserService.currentUser = function(){
            if(qmeUserSession && qmeUserSession.isSignedIn()){
                return qmeUserSession;
            }
            return null;
        };

        qmeUserService.login = function (credentials) {

            var loginPromise = $q.defer();

            qmeUserResource.userAuthResource()

                .save(credentials,

                function(res){

                    qmeUserSession.create(
                        res.authToken,
                        res.userID,
                        res.userName,
                        res.userFirstName,
                        res.userLastName,
                        res.userEmail,
                        res.userLastLoginDate,
                        res.userRoles
                    );

                    loginPromise.resolve(res);
                },
                function(error){
                    loginPromise.reject(error);
                });

            return loginPromise.promise;
        };

        qmeUserService.logout = function (){
            qmeUserResource.logoutResource(qmeUserSession.authtoken()).save();
            qmeUserSession.destroy();
        };

        qmeUserService.stageUser = function(user){

            user.confirmURL = QME_CONSTANTS.stageconfirmurl;

            var stageUserPromise = $q.defer();

            qmeUserResource.userStageResource()

                .save(user,function(res){
                    stageUserPromise.resolve(res);
                },function(error){
                    stageUserPromise.reject(error);
                });
            return stageUserPromise.promise;
        };

        qmeUserService.confirmUser = function(stagingtoken){

            var confirmUserPromise = $q.defer();

            qmeUserResource.userConfirmResource()
                .save(stagingtoken,function(res){
                    confirmUserPromise.resolve(res);
                },function(error){
                    confirmUserPromise.reject(error);
                });
            return confirmUserPromise.promise;
        };


        qmeUserService.register = function(user){

           var registeredUserPromise = $q.defer();

           qmeUserResource.userRegisterResource(qmeUserSession.authtoken())

                .save(user,function(res){
                    registeredUserPromise.resolve(res);
                },function(error){
                    registeredUserPromise.reject(error);
                });
            return registeredUserPromise.promise;
        };

        qmeUserService.updateUserProfile = function(updatedUser){
            if(updatedUser.userPassword === QME_CONSTANTS.password_mask ){
                updatedUser.userPassword = "";
                updatedUser.updatedUserPassword = "";
            }
            updatedUser.userId = qmeUserSession.userid();
            updatedUser.userName = qmeUserSession.username();
            updatedUser.userEmail = qmeUserSession.useremail();

            var updateUserPromise = $q.defer();

            qmeUserResource.userUpdateResource(qmeUserSession.authtoken(),qmeUserSession.userid())
                .updateUser({}, updatedUser,function(res){
                    qmeUserSession.setUserFirstname(res.userFirstName);
                    qmeUserSession.setUserLastname(res.userLastName);
                    updateUserPromise.resolve(res);
                },function(error){
                    updateUserPromise.reject(error);
                }
            );
            return updateUserPromise.promise;
        };

        qmeUserService.updateUser = function(updatedUser, userId){
            var updateUserPromise = $q.defer();

            qmeUserResource.userUpdateResource(qmeUserSession.authtoken(),userId)
                .updateUser({}, updatedUser,function(res){
                    updateUserPromise.resolve(res);
                 },function(error){
                    updateUserPromise.reject(error);
                 }
                );
            return updateUserPromise.promise;
        };

        qmeUserService.deleteUser = function(userId){

            var deleteUserPromise = $q.defer();

            qmeUserResource.userDeleteResource(qmeUserSession.authtoken(),userId)
                    .deleteUser({}, {},function(res){
                        deleteUserPromise.resolve(res);
                    },function(error){
                        deleteUserPromise.reject(error);
                    }
                );
            return deleteUserPromise.promise;
        };

        qmeUserService.resetPassword = function(useremail){
           var resetPasswordUserPromise = $q.defer();

            qmeUserResource.userForgotPasswordResource(useremail)

                .reset({}, QME_CONSTANTS.reseturl,function(res){
                    resetPasswordUserPromise.resolve(res);
                },function(error){
                    resetPasswordUserPromise.reject(error);
                }
            );
            return resetPasswordUserPromise.promise;
        };

        qmeUserService.submitResetPassword = function(usertoken,username, useremail, userpassword){
            var resetPasswordUserPromise = $q.defer();

            var resetrequest = {
                    token:usertoken,
                    userName:username,
                    userPassword:userpassword
            };

            qmeUserResource.userResetPasswordResource(useremail)
                .resetpassword({}, resetrequest,function(res){

                    qmeUserSession.create(
                        res.authToken,
                        res.userID,
                        res.userName,
                        res.userFirstName,
                        res.userLastName,
                        res.userEmail,
                        res.userLastLoginDate,
                        res.userRoles
                    );
                    resetPasswordUserPromise.resolve(res);
                },function(error){
                    resetPasswordUserPromise.reject(error);
                }
            );
            return resetPasswordUserPromise.promise;
        };

        qmeUserService.countUsers = function(){
           return qmeUserResource.userCountResource(qmeUserSession.authtoken());
        };

        qmeUserService.listUsers = function(){
           var listUserPromise = $q.defer();

           qmeUserResource.userResource(qmeUserSession.authtoken())
                .query(
                function(res){
                    listUserPromise.resolve(res);
                },
                function(error){
                    listUserPromise.reject(error);
                }
            );
            return listUserPromise.promise;
        };

        qmeUserService.listUsersPaged = function(currentPage,sorttype,sortfields){
            var listUserPagedPromise = $q.defer();

            qmeUserResource.userPagedResource(qmeUserSession.authtoken(),currentPage,QME_CONSTANTS.rowsperpage,sorttype,sortfields)
                .query(
                function(res){
                    listUserPagedPromise.resolve(res);
                },
                function(error){
                    listUserPagedPromise.reject(error);
                }
            );
            return listUserPagedPromise.promise;
        };
    }
})();