(function () {

    'use strict';

    ngQMe
        .controller('qmeUserCtrl', QMeUserController);

    QMeUserController.$inject = ['$state','$stateParams','qmeFlashService','qmeUserService'];

    function QMeUserController($state,$stateParams,qmeFlashService,qmeUserService) {

        var qmeUser = this;

        qmeUser.userEmail = "";
        qmeUser.userName = "";
        qmeUser.userPassword = "";
        qmeUser.userPasswordConfirm = "";
        qmeUser.userFirstName = "";
        qmeUser.userLastName = "";

        qmeUser.isSignedIn = function(){
            if(qmeUserService.currentUser() && qmeUserService.currentUser().isSignedIn()){
                return true;
            }
            return false;
        };

        qmeUser.isAdmin = function(){
            if(qmeUserService.currentUser() && qmeUserService.currentUser().isSignedIn() && qmeUserService.currentUser().isAdmin()){
                return true;
            }
            return false;
        };

        qmeUser.userNameDisplay = function (){
            if(qmeUser.isSignedIn()){
                if(qmeUserService.currentUser().userfirstname() && qmeUserService.currentUser().userfirstname().length > 0
                    && qmeUserService.currentUser().userlastname() && qmeUserService.currentUser().userlastname().length > 0
                ){
                    return qmeUserService.currentUser().userfirstname() + " "+ qmeUserService.currentUser().userlastname();

                }else if(qmeUserService.currentUser().userfirstname() && qmeUserService.currentUser().userfirstname().length > 0){
                    return qmeUserService.currentUser().userfirstname();

                }else{
                    return qmeUserService.currentUser().username();
                }
            }
            return "";
        };


        qmeUser.performSignIn = function (){

            var credentials;
            qmeFlashService.Clear();

            credentials = {
                "userName": qmeUser.userEmail,
                "userPassword": qmeUser.userPassword
            };

            qmeUserService.login(credentials)

                .then(

                function(res){
                    $state.go('home', {});
                },
                function(error){
                    if(error && error.status && error.status == 401){
                        qmeFlashService.Error("Oops.....User not authorized, please register or click on forgot password.");
                    }else if(error && error.status && error.status == 403) {
                        qmeFlashService.Error("Oops.....User not authorized, please register or click on forgot password.");
                    }else if(error && error.status && error.status == 404){
                        qmeFlashService.Error("Error Connecting to service, entered user credential not found.");
                    }else{
                        qmeFlashService.Error("Oops.....Error Connecting to service, please retry in some time.");
                    }
                }
            );
        };

        qmeUser.logout = function (){
            qmeUserService.logout();
            qmeUserService.endRegistering();
            qmeUserService.endResetting();
            qmeUser.userEmail = "";
            qmeUser.userPassword = "";
            qmeUser.signInForm.$setPristine()
            $state.go('home', {});
        };

        qmeUser.routeUserProfile = function (){
            $state.go('userprofile', {});
        };

        qmeUser.routeRegistration = function (){
            qmeUserService.endRegistering();
            qmeUserService.endResetting();
            $state.go('register', {});
        };

        qmeUser.routeStaging = function (){
            qmeUserService.startRegistering();
            $state.go('stage', {});
        };

        qmeUser.routeResetPassword = function (){
            qmeUserService.startResetting();
            $state.go('forgotpassword', {});
        };

        qmeUser.cancelResetRegistration = function (){
            qmeUserService.endRegistering();
            qmeUserService.endResetting();
            qmeUser.signInForm.$setPristine()
            $state.go('home', {});
        };

        qmeUser.isRegistering = function(){
            return  qmeUserService.isRegistering();
        }

        qmeUser.isResetingPassword = function(){
            return  qmeUserService.isResetting();
        }

        qmeUser.validatePasswordFields = function (){
            if(qmeUser.userPassword != qmeUser.userPasswordConfirm){
                qmeFlashService.Error("Password do not match, please confirm password");
                qmeUser.userPasswordConfirm ="";
            }else{
                qmeFlashService.Clear();
            }
        };

        qmeUser.stageUser = function (){

            qmeFlashService.Clear();

            var user = {
                "userName": qmeUser.userName,
                "userPassword": qmeUser.userPassword ,
                "userFirstName": qmeUser.userFirstName,
                "userLastName": qmeUser.userLastName,
                "userEmail": qmeUser.userEmail
            }

            qmeUserService
                .stageUser(user)
                .then(
                function(res){
                    qmeFlashService.Success("User registration submitted, please check your email and complete steps to confirm registration, Thank you.",true);
                    $state.go('home', {});
                },
                function(error){
                    if(error && error.status && error.status == 400){
                        qmeFlashService.Error("Oops.....Invalid request for user registration, please make sure all required fields are valid.");

                    }else if(error && error.status && error.status == 409){
                        qmeFlashService.Error("Oops...User with same email address already exists please enter valid unique email address.");

                    }else{
                        qmeFlashService.Error("Oops.....Error registering new user, please retry in some time.");
                    }
                }
            );
        };

        qmeUser.updateUser = function(){

            qmeFlashService.Clear();


        };

        qmeUser.registerUser = function (){

            qmeFlashService.Clear();

            var user = {
                "userName": qmeUser.userName,
                "userPassword": qmeUser.userPassword ,
                "userFirstName": qmeUser.userFirstName,
                "userLastName": qmeUser.userLastName,
                "userEmail": qmeUser.userEmail
            }

            qmeUserService
                .register(user)
                .then(
                function(res){
                    qmeFlashService.Success("User registration successful.",true);
                    $state.go('home', {});
                },
                function(error){
                    if(error && error.status && error.status == 400){
                        qmeFlashService.Error("Oops.....Invalid request for user registration, please make sure all required fields are valid.");

                    }else if(error && error.status && error.status == 409){
                        qmeFlashService.Error("Oops...User with same email address already exists please enter valid unique email address.");

                    }else{
                        qmeFlashService.Error("Oops.....Error registering new user, please retry in some time.");
                    }
                }
            );
        };

        qmeUser.submitReset = function(){

            qmeUserService
                .resetPassword(qmeUser.userEmail)
                .then(
                function (res){
                    $state.go('home', {});
                    qmeFlashService.Success("User password reset request submitted successfully, please validate your email address to complete reset.");
                },
                function (error){
                    if(error && error.status && error.status == 404){
                        qmeFlashService.Error("Entered user email not found. Please ener valid existing user email.");

                    }else{
                        qmeFlashService.Error("Oops.....Error connecting to service for reset password, please retry in some time.");
                    }
                }
            );
        };

        qmeUser.submitResetPassword = function(){
            qmeUserService
                .submitResetPassword($stateParams.token,$stateParams.username,qmeUser.userEmail,qmeUser.userPassword)
                .then(
                function (res){
                    $state.go('home', {});
                },
                function (error){
                    if(error && error.status && error.status == 404) {
                        qmeFlashService.Error("Entered user email not found. Please ener valid existing user email.");

                    }else if(error && error.status && error.status == 400){
                        qmeFlashService.Error("Reset token invalid, Please ener valid reset token.");

                    }else{
                        qmeFlashService.Error("Oops.....Error from service for reset password, please retry in some time.");
                    }
                }
            );
        };
    }
})();