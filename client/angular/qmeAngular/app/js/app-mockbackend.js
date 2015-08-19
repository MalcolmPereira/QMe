(function () {

    'use strict';

    angular.module('qmeApp').run(function($httpBackend) {

        // this is the creation of a new resource
        $httpBackend.whenPOST('/login').respond(function(method, url, data) {

            var params = angular.fromJson(data);
            console.log("got params"+params.username);
            console.log("got params"+params.password);

            var user = {
                "id":1234,
                "name":"test user",
                "role": "admin"
            };

            return [200, user,{}];
        });
        $httpBackend.whenGET(/js\//).passThrough();
    });

})();