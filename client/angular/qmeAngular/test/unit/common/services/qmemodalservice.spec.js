(function () {

    'use strict';

    describe('Service: QMe Modal Service', function() {

        var rootScope,httpBackend,qmeModelSession;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,$httpBackend,_qmeModelSession_) {
            rootScope = $rootScope;
            httpBackend = $httpBackend;
            qmeModelSession = _qmeModelSession_;
        }));

        it('Should have a QMe Modal Service', function() {
            expect(qmeModelSession).toBeDefined();
        });

        it('Should allow to return and maintain modal window state', function() {
            httpBackend.whenGET(/js\//).respond(200,{});
            expect(qmeModelSession).toBeDefined();
            var modalPromise = qmeModelSession.modalShown();
            expect(modalPromise).toBeDefined();
            modalPromise.then(function (data) {
                expect(data.test ).toBe('test');
            });
            qmeModelSession.create({"test":"test"}) ;
            rootScope.$digest();
            modalPromise = qmeModelSession.modalShown();
            expect(modalPromise).toBeDefined();
            qmeModelSession.destroy();
            modalPromise.then(function (data) {},function(error){
                expect(error).not.toBeDefined();
            });
        });

    });

})();