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
                return !!(qmeUserSession && qmeUserSession.isSignedIn());

            };

            qmeNav.isAdmin = function(){
                return !!(qmeUserSession && qmeUserSession.isSignedIn() && qmeUserSession.isAdmin());

            };

            qmeNav.routeHome = function(){
                $state.go('home', {});
            };
        }

})();
