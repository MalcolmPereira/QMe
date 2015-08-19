(function () {
    'use strict';

    qmeApp.controller('qmeHeader', QMeHeaderController);

    QMeHeaderController.$inject = ['qmeFlashService','qmeAuthService','$location','$scope','$rootScope','USER_ROLES','AUTH_EVENTS'];

    function QMeHeaderController(qmeFlashService,qmeAuthService,$location,$scope,$rootScope,USER_ROLES,AUTH_EVENTS) {

        $scope.currentUser = null;
        $scope.userRoles = USER_ROLES;
        $scope.isAuthorized = qmeAuthService.isAuthorized;
        $scope.setCurrentUser = function (user) {
            $scope.currentUser = user;
        };

        var qmeHeader = this;
        qmeHeader.isRegistering = false;
        qmeHeader.isResetingPassword = false;
        qmeHeader.signedIn = false;
        qmeHeader.userEmail = "";
        qmeHeader.userPassword = "";
        qmeHeader.userName = "";

        qmeHeader.performSignIn = function (){
            var credentials = {
                "username": qmeHeader.userEmail,
                "password": qmeHeader.userPassword
            };
            qmeAuthService.login(credentials).then(function (user) {
                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                $scope.setCurrentUser(user);
                qmeHeader.signedIn = true;
                qmeHeader.isRegistering = false;
                qmeHeader.isResetingPassword = false;
                qmeHeader.userName = user.name;

            }, function () {
                $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
                $scope.currentUser = null;
                qmeHeader.signedIn = false;
                qmeHeader.isRegistering = false;
                qmeHeader.isResetingPassword = false;
                qmeFlashService.Error("Error connecting to QMe service, please validate credentials and retry request");
            });
        }

        qmeHeader.routeRegistration = function (){
            qmeHeader.isRegistering = true;
            $location.path( '/register' );
        }

        qmeHeader.routeResetPassword = function (){
            qmeHeader.isResetingPassword = true;
            $location.path( '/reset' );
        }

        qmeHeader.cancelResetRegistration = function (){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            $location.path( '/' );
        }

        qmeHeader.logout = function (){
            qmeHeader.isRegistering = false;
            qmeHeader.isResetingPassword = false;
            qmeHeader.signedIn = false;
            qmeHeader.userEmail = "";
            qmeHeader.userPassword = "";
            qmeFlashService.Clear();
        }
    }

})();