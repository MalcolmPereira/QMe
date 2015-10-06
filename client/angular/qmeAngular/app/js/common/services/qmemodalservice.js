(function () {

	"use strict";

    ngQMe
        .service('qmeModelSession',QMeModalSession);

    QMeModalSession.$inject = ['$q'];

    function QMeModalSession($q) {

        var qmeModelSession = this;

        var deferred ;


        qmeModelSession.modalShown = function(){
            deferred = $q.defer()
            return deferred.promise;
        };

        qmeModelSession.create = function(modaldata){
            deferred.resolve(modaldata);
        };

        qmeModelSession.destroy = function(){
            deferred.reject();
        };
    }
})();