(function () {

    "use strict";

    angular.module(qmeApp)
        .service('qmeUserService',QMeUserService);


    QMeUserService.$inject = ['$q','$http','$resource','qmeAuthService','QME_CONSTANTS'];


    function QMeUserService($q,$http,$resource,qmeAuthService,QME_CONSTANTS) {

        var qmeUserService = this;

        qmeUserService.register = function(user){

           var registeredUserPromise = $q.defer();

            $http.defaults.headers.common.Authorization = undefined;

            $resource(QME_CONSTANTS.serviceurl+QME_CONSTANTS.userapi+"register")

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

            $http.defaults.headers.common.Authorization = undefined;

            $resource(QME_CONSTANTS.serviceurl+QME_CONSTANTS.userapi+"reset/forgotpassword/"+useremail,null,{'reset':{method:'PUT'}})
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

            $http.defaults.headers.common.Authorization = undefined;

            var resetrequest = {
                    token:usertoken,
                    userName:username,
                    userPassword:userpassword
            };
            $resource(QME_CONSTANTS.serviceurl+QME_CONSTANTS.userapi+"reset/resetpassword/"+useremail,{},{'resetpassword':{method:'PUT'}})
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