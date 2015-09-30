(function () {

    "use strict";

    ngQMe
        .service('qmeUserService',QMeUserService);


    QMeUserService.$inject = ['$q','qmeUserResource','qmeAuthService','QME_CONSTANTS'];


    function QMeUserService($q,qmeUserResource,qmeAuthService,QME_CONSTANTS) {

        var qmeUserService = this;


        qmeUserService.stageUser = function(user){

            var stageUserPromise = $q.defer();

            qmeUserResource.userStageResource()

                .save(user
                ,
                function(res){
                    res.userPassword = user.userPassword;
                    qmeAuthService.registeredUser(res);
                    stageUserPromise.resolve(res);
                }
                ,
                function(error){
                    stageUserPromise.reject(error);
                });
            return stageUserPromise.promise;
        };


        qmeUserService.register = function(user){

           var registeredUserPromise = $q.defer();

           qmeUserResource.userRegisterResource()

                .save(user
                ,
                function(res){
                    res.userPassword = user.userPassword;
                    qmeAuthService.registeredUser(res);
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
                    res.userPassword = userpassword;
                    qmeAuthService.registeredUser(res);
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