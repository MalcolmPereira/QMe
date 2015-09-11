(function () {

    "use strict";

    angular.module(qmeApp)
        .service('qmeUserService',QMeUserService);


    QMeUserService.$inject = ['$q','$resource','qmeAuthService','QME_CONSTANTS'];


    function QMeUserService($q,$resource,qmeAuthService,QME_CONSTANTS) {

        var qmeUserService = this;

        qmeUserService.register = function(user){

           var registeredUserPromise = $q.defer();

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

    }
})();