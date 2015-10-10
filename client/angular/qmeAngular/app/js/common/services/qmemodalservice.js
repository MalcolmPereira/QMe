(function () {

	"use strict";

    ngQMe
        .service('qmeModelSession',QMeModalSession);

    QMeModalSession.$inject = ['$q'];

    function QMeModalSession($q) {

        var qmeModelSession = this;

        var modalPromise;

        qmeModelSession.modalShown = function(){
            modalPromise = $q.defer();
            return modalPromise.promise;
        };

        qmeModelSession.create = function(modaldata) {
            if (modalPromise) {
                modalPromise.resolve(modaldata);
            }
        };

        qmeModelSession.destroy = function(){
            if (modalPromise) {
                modalPromise.reject();
            }
        };
    }
})();