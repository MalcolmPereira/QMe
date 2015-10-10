
(function () {

    'use strict';

    describe('Controller: QMe Password Modal Controller', function() {

        var rootScope,scope, ctrl,qmeModelSession;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,$controller,_qmeModelSession_) {

            rootScope = $rootScope;

            qmeModelSession =  _qmeModelSession_;

            ctrl  = $controller('qmePasswordModalCtrl', {
                qmeModelSession:qmeModelSession,
                currentPassword:"",
                password:"",
                passwordConfirm:"",
                isPasswordError:false,
            });

            ctrl.passwordForm = {
                $setPristine: function(){},
                $setValidity: function(){}
            }

        }));

        it('Should have a QMe Password Modal controller', function() {
            expect(ctrl).toBeDefined();
            expect(qmeModelSession).toBeDefined();
        });

        it('Should have a Default Values', function() {
            expect(ctrl).toBeDefined();
            expect(qmeModelSession).toBeDefined();
            expect(ctrl.passwordForm ).toBeDefined();
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
            p.then(function (data) {
                expect(data.currentPassword ).toBe('test');
                expect(data.password ).toBe('newtest');
                expect(data.passwordConfirm ).toBe('newtest');
                expect(ctrl.isPasswordError ).toBe(false);
            });
            ctrl.save();
            rootScope.$digest();
        });

        it('Should have Cancel Save Password Request', function() {
            expect(ctrl).toBeDefined();
            ctrl.currentPassword = "test";
            ctrl.password = "newtest";
            ctrl.passwordConfirm = "newtest";
            expect(ctrl.currentPassword ).toBe('test');
            expect(ctrl.password ).toBe('newtest');
            expect(ctrl.passwordConfirm ).toBe('newtest');
            expect(ctrl.isPasswordError ).toBe(false);
            var p = qmeModelSession.modalShown();
            p.then(function (data) {
                console.log("data",data);
            },function(error){
                expect(ctrl.passwordForm ).toBeDefined();
                expect(ctrl.currentPassword ).toBe('');
                expect(ctrl.password ).toBe('');
                expect(ctrl.passwordConfirm ).toBe('');
                expect(ctrl.isPasswordError ).toBe(false);
            });
            ctrl.cancel();
            rootScope.$digest();
        });

        it('Should have Validate Password Fields on valid password', function() {
            expect(ctrl).toBeDefined();
            ctrl.currentPassword = "test";
            ctrl.password = "newtest";
            ctrl.passwordConfirm = "newtest";
            expect(ctrl.currentPassword ).toBe('test');
            expect(ctrl.password ).toBe('newtest');
            expect(ctrl.passwordConfirm ).toBe('newtest');
            expect(ctrl.isPasswordError ).toBe(false);
            expect(ctrl.showPasswordError()).toBe(false);
            ctrl.validatePasswordFields();
            expect(ctrl.currentPassword ).toBe('test');
            expect(ctrl.password ).toBe('newtest');
            expect(ctrl.passwordConfirm ).toBe('newtest');
            expect(ctrl.isPasswordError ).toBe(false);
            expect(ctrl.showPasswordError()).toBe(false);
        });

        it('Should have Validate Password Fields on non matching password', function() {
            expect(ctrl).toBeDefined();
            ctrl.currentPassword = "test";
            ctrl.password = "newtest1";
            ctrl.passwordConfirm = "newtest2";
            expect(ctrl.currentPassword ).toBe('test');
            expect(ctrl.password ).toBe('newtest1');
            expect(ctrl.passwordConfirm ).toBe('newtest2');
            expect(ctrl.isPasswordError ).toBe(false);
            expect(ctrl.showPasswordError()).toBe(false);
            ctrl.validatePasswordFields();
            expect(ctrl.currentPassword ).toBe('test');
            expect(ctrl.password ).toBe('newtest1');
            expect(ctrl.passwordConfirm ).toBe('');
            expect(ctrl.isPasswordError ).toBe(true);
            expect(ctrl.showPasswordError()).toBe(true);
            rootScope.$digest();
        });

        it('Should have Check for Valid Password Form', function() {
            expect(ctrl).toBeDefined();
            ctrl.currentPassword = "test";
            ctrl.password = "newtest";
            ctrl.passwordConfirm = "newtest";
            expect(ctrl.currentPassword ).toBe('test');
            expect(ctrl.password ).toBe('newtest');
            expect(ctrl.passwordConfirm ).toBe('newtest');
            expect(ctrl.isPasswordError ).toBe(false);
            expect(ctrl.isInValidForm()).toBe(false);
            ctrl.currentPassword = "";
            ctrl.password = "newtest";
            ctrl.passwordConfirm = "newtest";
            expect(ctrl.currentPassword ).toBe('');
            expect(ctrl.password ).toBe('newtest');
            expect(ctrl.passwordConfirm ).toBe('newtest');
            expect(ctrl.isPasswordError ).toBe(false);
            expect(ctrl.isInValidForm()).toBe(true);

        });

    });


})();

