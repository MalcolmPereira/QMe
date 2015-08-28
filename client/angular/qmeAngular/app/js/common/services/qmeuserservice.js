(function () {

    "use strict";

    angular.module(qmeApp)
        .service('qmeUserService',QMeUserService);


    QMeUserService.$inject = ['$http','$resource','$base64','qmeFlashService','qmeUserSession','QME_CONSTANTS'];


    function QMeUserService($http,$resource,$base64,qmeFlashService,qmeUserSession,QME_CONSTANTS) {

        var qmeUserService = this;

        qmeUserService.register = function(user){

            qmeFlashService.Clear();

            $resource(QME_CONSTANTS.serviceurl+QME_CONSTANTS.userapi+"register")

                .save(user
                ,
                function(res){

                    var authToken = 'Basic ' + $base64.encode(user.userName + ':' + user.userPassword);

                    qmeUserSession.create(
                        'sessionId123',
                        authToken,
                        res.userId,
                        res.userName,
                        res.userFirstName,
                        res.userLastName,
                        res.userEmail,
                        res.userRoles
                    );
                }
                ,
                function(error){
                    console.log("got some error",error);

                    if(error && error.status && error.status == 400){
                        qmeFlashService.Error("Oops.....Invalid request for user registration, please make sure all required fields are valid.");

                    }else if(error && error.status && error.status == 409){
                        qmeFlashService.Error("Oops...User with same email address already exists please enter valid unique email address.");

                    }else{
                        qmeFlashService.Error("Oops.....Error registering new user, please retry in some time.");
                    }

                });
        }

    }
})();