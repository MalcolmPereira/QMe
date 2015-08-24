(function () {

    'use strict';

    describe('Service: QMe Flash Service', function() {
        var scope,qmeFlashService;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, _qmeFlashService_) {
            scope = $rootScope.$new();
            qmeFlashService = _qmeFlashService_;
        }));

        it('Should have a QMe Flash Service', function() {
            expect(qmeFlashService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should have a No Messages', function() {
            expect(qmeFlashService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            qmeFlashService.Clear();
            expect(scope.flash).not.toBeDefined();

        });

        it('Should have a Success Messages', function() {
            expect(qmeFlashService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            qmeFlashService.Clear();
            expect(scope.flash).not.toBeDefined();
            qmeFlashService.Success("Some Test Message");
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('success');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Some Test Message');
            expect(scope.flash.keepAfterLocationChange).not.toBeDefined();
            qmeFlashService.Success("Some Test Message",false);
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('success');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Some Test Message');
            expect(scope.flash.keepAfterLocationChange).toBeDefined();
            expect(scope.flash.keepAfterLocationChange).toBe(false);
        });

        it('Should have a Error Messages', function() {
            expect(qmeFlashService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            qmeFlashService.Clear();
            expect(scope.flash).not.toBeDefined();
            qmeFlashService.Error("Some Error Message");
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Some Error Message');
            expect(scope.flash.keepAfterLocationChange).not.toBeDefined();
            qmeFlashService.Error("Some Error Message",false);
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Some Error Message');
            expect(scope.flash.keepAfterLocationChange).toBeDefined();
            expect(scope.flash.keepAfterLocationChange).toBe(false);
        });
    });
})();

