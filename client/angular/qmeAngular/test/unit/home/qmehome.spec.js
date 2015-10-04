(function () {

    'use strict';

    describe('Controller: QMe Home Controller', function() {

        var scope, state, stateParams,ctrl,qmeUserSession;

       beforeEach(module('qmeApp'));

       beforeEach(inject(function($rootScope,$state, $stateParams, $controller,_qmeUserSession_) {

            scope = $rootScope.$new();

            state = $state;

            stateParams = $stateParams;

            qmeUserSession = _qmeUserSession_;

            //spyOn(mockDataSvc,'save').andCallThrough();

            ctrl  = $controller('qmeHomeCtrl', {
                $scope: scope,
                $state: state,
                $stateParams:stateParams,
                qmeUserSession:qmeUserSession
            });
        }));

        it('Should have a QMe Home controller', function() {
            expect(ctrl).toBeDefined();
            expect(qmeUserSession).toBeDefined();
        });

        it('Should have a QMe Home controller Logged in User', function() {
            qmeUserSession.create(
                "sometoken",
                "1L" ,
                "someusername",
                "somefirstname",
                "somelastname",
                "someemail",
                "somelastlogin",
                "['USER']");
            expect(ctrl.userdetails() ).toBe("someusername");

        });

    });
})();


