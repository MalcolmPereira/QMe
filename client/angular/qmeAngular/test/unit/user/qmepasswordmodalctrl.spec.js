
(function () {

    'use strict';

    describe('Controller: QMe Password Modal Controller', function() {

        var ctrl,qmeModelSession;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($controller,_qmeModelSession_) {

            qmeModelSession =  _qmeModelSession_;

            ctrl  = $controller('qmePasswordModalCtrl', {
                qmeModelSession:qmeModelSession,
                currentPassword:"",
                password:"",
                passwordConfirm:"",
                isPasswordError:false
            });

        }));

        it('Should have a QMe Password Modal controller', function() {
            expect(ctrl).toBeDefined();
            expect(qmeModelSession).toBeDefined();
        });

        it('Should have a Default Values', function() {
            expect(ctrl).toBeDefined();
            expect(qmeModelSession).toBeDefined();
            expect(ctrl.currentPassword ).toBe('');
            expect(ctrl.password ).toBe('');
            expect(ctrl.passwordConfirm ).toBe('');
            expect(ctrl.isPasswordError ).toBe(false);
        });

        it('Should have Save Change Password Request', function() {
            expect(ctrl).toBeDefined();
            ctrl.currentPassword = "test";
            ctrl.password = "newtest";
            ctrl.passwordConfirm = "newtest";
            expect(ctrl.currentPassword ).toBe('test');
            expect(ctrl.password ).toBe('newtest');
            expect(ctrl.passwordConfirm ).toBe('newtest');
            expect(ctrl.isPasswordError ).toBe(false);
            var p = qmeModelSession.modalShown();
            p.then(function () {
                console.log("hello");
            });
            console.log('got promise',p);
            ctrl.save();
            console.log('got promise',p);
            ctrl.save();
            ctrl.save();

        });

    });


})();

