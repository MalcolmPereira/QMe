(function () {

	"use strict";

    ngQMe
        .service('qmeModelSession',QMeModalSession);

    QMeModalSession.$inject = ['$q'];

    function QMeModalSession($q) {

        var qmeModelSession = this;

        var deferred = $q.defer();

        initPromise();

        function initPromise() {
            deferred = $q.defer();
        }

        qmeModelSession.init = function() {
            deferred = $q.defer();
        };

        qmeModelSession.init = function() {
            deferred = $q.defer();
        };

        qmeModelSession.modalShown = function(){
            initPromise();
            console.log("got deferred",deferred);
            console.log("got deferred promise",deferred.promise);
            return deferred.promise;
        };

        qmeModelSession.create = function(modaldata) {
            if (deferred) {
                deferred.resolve(modaldata);
            }
        };

        qmeModelSession.destroy = function(){
            if (deferred) {
                deferred.reject();
            }
        };
    }
})();