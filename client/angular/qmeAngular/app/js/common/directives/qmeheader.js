(function () {

    'use strict';

    ngQMe

        .directive('qmeHeader', function() {
            return {
                restrict: 'E',
                controller: 'qmeUserCtrl',
                controllerAs: 'qmeUserCtrl',
                templateUrl: 'js/common/directives/qmeheader.tmpl.html'
            };
        })

        .controller('qmeNavCtrl', QMeNavigationController);

        QMeNavigationController.$inject = ['$state','qmeUserSession'];

        function QMeNavigationController($state,qmeUserSession) {

            var qmeNav = this;

            qmeNav.isSignedIn = function(){
                if(qmeUserSession.isSignedIn() === null){
                    return false;
                }
                return (qmeUserSession && qmeUserSession.isSignedIn() && !qmeUserSession.isUpdating());
            };

            qmeNav.isAdmin = function(){
                if(qmeUserSession.isAdmin() === null){
                    return false;
                }
                return (qmeUserSession && qmeUserSession.isSignedIn() && qmeUserSession.isAdmin());
            };

            qmeNav.routeHome = function(){
                $state.go('home', {});
            };

            qmeNav.routeUsers = function(){
                $state.go('listusers', {});
            };

            qmeNav.routeCategories = function(){
                $state.go('listcategories', {});
            };

            qmeNav.routeQuestions = function(){
                $state.go('listquestions', {});
            };

            qmeNav.routeQuizzes = function(){
                $state.go('listquizzes', {});
            };

            qmeNav.isActive = function(currentState){
               return (currentState.indexOf($state.current.name) > -1);
            };
        }

})();
