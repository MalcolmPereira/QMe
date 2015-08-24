(function () {
    'use strict';

    describe('Controller: QMe Header Controller', function() {

        var scope,  ctrl;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, $controller) {
            scope = $rootScope.$new();
            ctrl  = $controller('qmeHeaderCtrl', {
                $scope: scope
            });
        }));

        it('Should have a qmeHeader controller', function() {
            expect(ctrl).toBeDefined();
        });


        describe('QMeHeader Defaults', function(){
            it('Ensure user is not signed - signedIn is false', function() {
                expect(ctrl.isSignedIn() ).toBe(false);
            });

            it('Ensure user is not registering - isRegistering  is false', function() {
                expect(ctrl.isRegistering ).toBe(false);
            });
            it('Ensure user is not resetting password  - isResetingPassword is false', function() {
                expect(ctrl.isResetingPassword ).toBe(false);
            });
            it('Ensure user email is empty - userEmail is empty ', function() {
                expect(ctrl.userEmail ).toBe('');
            });
            it('Ensure user password is empty - userPassword is empty ', function() {
                expect(ctrl.userPassword ).toBe('');
            });
        });

       describe('QMeHeader Perform SignIn With Non Admin User', function(){

           var authServiceMock ;

           beforeEach(function(){
               authServiceMock = {
                   login:function(){
                       return;
                   }
                   ,
                   isSignedIn:function(){
                       return true;
                   }
                   ,
                   isAdmin:function(){
                       return false;
                   },
                   logout:function (){
                       return;
                   }
                   ,
                   user:function(){
                       return {};
                   }
                   ,
                   username: function(){
                       return "test user";
                   }
               };
           });

           beforeEach(inject(function($rootScope, $controller) {
               scope = $rootScope.$new();
               ctrl  = $controller('qmeHeaderCtrl', {
                   $scope: scope,
                   qmeAuthService: authServiceMock
               });
           }));


           it('Ensure user sign-on is valid ', function() {

               spyOn(authServiceMock, 'login').and.callThrough();

               ctrl.userEmail = "testuser@test.com";
               ctrl.userPassword = "testpassword";
               ctrl.performSignIn();
               expect(authServiceMock.login).toHaveBeenCalled();
               expect(ctrl.userEmail ).not.toBe('');
               expect(ctrl.userEmail ).toBe('testuser@test.com');
               expect(ctrl.userPassword ).not.toBe('');
               expect(ctrl.userPassword ).toBe('testpassword');
               expect(ctrl.isSignedIn() ).toBe(true);
               expect(ctrl.isAdmin() ).toBe(false);
           });

        });

        describe('QMeHeader Perform SignIn With Admin User', function(){

            var authServiceMock ;

            beforeEach(function(){
                authServiceMock = {
                    login:function(){
                        return;
                    }
                    ,
                    isSignedIn:function(){
                        return true;
                    }
                    ,
                    isAdmin:function(){
                        return true;
                    },
                    logout:function (){
                        return;
                    }
                    ,
                    user:function(){
                        return {};
                    }
                    ,
                    username: function(){
                        return "test user";
                    }
                };
            });

            beforeEach(inject(function($rootScope, $controller) {
                scope = $rootScope.$new();
                ctrl  = $controller('qmeHeaderCtrl', {
                    $scope: scope,
                    qmeAuthService: authServiceMock
                });
            }));


            it('Ensure user sign-on is valid ', function() {

                spyOn(authServiceMock, 'login').and.callThrough();

                ctrl.userEmail = "testuser@test.com";
                ctrl.userPassword = "testpassword";
                ctrl.performSignIn();
                expect(authServiceMock.login).toHaveBeenCalled();
                expect(ctrl.userEmail ).not.toBe('');
                expect(ctrl.userEmail ).toBe('testuser@test.com');
                expect(ctrl.userPassword ).not.toBe('');
                expect(ctrl.userPassword ).toBe('testpassword');
                expect(ctrl.isSignedIn() ).toBe(true);
                expect(ctrl.isAdmin() ).toBe(true);
            });

        });

    });


})();