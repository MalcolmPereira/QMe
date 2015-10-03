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

            user[ "confirmURL" ] = QME_CONSTANTS.stageconfirmurl;

            var stageUserPromise = $q.defer();

            pleaseWait.showPleaseWait();

            qmeUserResource.userStageResource()

                .save(user
                ,
                function(res){
                    pleaseWait.hidePleaseWait();
                    stageUserPromise.resolve(res);
                }
                ,
                function(error){
                    pleaseWait.hidePleaseWait();
                    stageUserPromise.reject(error);
                });
            return stageUserPromise.promise;
        };

        qmeUserService.confirmUser = function(stagingtoken){

            var confirmUserPromise = $q.defer();

            qmeUserResource.userConfirmResource()

                .save(stagingtoken
                ,
                function(res){
                    confirmUserPromise.resolve(res);
                }
                ,
                function(error){
                    confirmUserPromise.reject(error);
                });
            return confirmUserPromise.promise;
        };


        qmeUserService.register = function(user){

           var registeredUserPromise = $q.defer();

           qmeUserResource.userRegisterResource()

                .save(user
                ,
                function(res){
                    registeredUserPromise.resolve(res);
                }
                ,
                function(error){
                    registeredUserPromise.reject(error);
                });
            return registeredUserPromise.promise;
        };

        qmeUserService.resetPassword = function(useremail){
           var resetPasswordUserPromise = $q.defer();


            qmeUserResource.userForgotPasswordResource(useremail)

                .reset({}, QME_CONSTANTS.reseturl
                ,function(res){
                    resetPasswordUserPromise.resolve(res);
                },
                function(error){
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
                .resetpassword({}, resetrequest
                ,function(res){
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
                },
                function(error){
                    resetPasswordUserPromise.reject(error);
                }
            );
            return resetPasswordUserPromise.promise;
        };
    }
})();