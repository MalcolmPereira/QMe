(function () {

    "use strict";

    angular.module(qmeApp)
        .service('qmeUserService',QMeUserService);


    QMeUserService.$inject = ['$q','$resource','qmeFlashService','qmeAuthService','QME_CONSTANTS'];


    function QMeUserService($q,$resource,qmeFlashService,qmeAuthService,QME_CONSTANTS) {

        var qmeUserService = this;

        qmeUserService.register = function(user){

            qmeFlashService.Clear();

            var registeredUserPromise = $q.defer();

            $resource(QME_CONSTANTS.serviceurl+QME_CONSTANTS.userapi+"register")

                .save(user
                ,
                function(res){
                    res.userPassword = user.userPassword
                    qmeAuthService.registeredUser(res);
                    registeredUserPromise.resolve(res);
                }
                ,
                function(error){
                    if(error && error.status && error.status == 400){
                        qmeFlashService.Error("Oops.....Invalid request for user registration, please make sure all required fields are valid.");

                    }else if(error && error.status && error.status == 409){
                        qmeFlashService.Error("Oops...User with same email address already exists please enter valid unique email address.");

                    }else{
                        qmeFlashService.Error("Oops.....Error registering new user, please retry in some time.");
                    }
                    registeredUserPromise.reject({});

                });

            return registeredUserPromise.promise;
        }

    }
})();