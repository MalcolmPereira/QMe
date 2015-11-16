(function () {

    'use strict';

    describe('Service: QMe User Service', function() {

        var scope,
            state,
            qmeUserService,
            qmeUserSession,
            httpBackend,
            qmeContants,
            userEndPoint,
            userPagedEndPoint,
            userCountEndPoint,
            userAuthEndPoint,
            userStagingEndpoint,
            userConfirmEndpoint,
            userRegisterEndpoint,
            logoutUserEndPoint,
            userForgotPaswordEndpoint,
            userResetPaswordEndpoint;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,$state, _qmeUserService_, _qmeUserSession_,$httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            state = $state;
            qmeUserService = _qmeUserService_;
            qmeUserSession = _qmeUserSession_;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            userAuthEndPoint =  qmeContants.qmeservice+"/login";
            userEndPoint     = qmeContants.qmeservice+"/user";
            userPagedEndPoint = qmeContants.qmeservice+"/user/paged";
            userCountEndPoint  = qmeContants.qmeservice+"/user/count";
            userStagingEndpoint = qmeContants.qmeservice+"/user/stage";
            userConfirmEndpoint = qmeContants.qmeservice+"/user/confirm";
            userRegisterEndpoint = qmeContants.qmeservice+"/user/register";
            userForgotPaswordEndpoint = qmeContants.qmeservice+"/user/reset/forgotpassword/";
            userResetPaswordEndpoint = qmeContants.qmeservice+"/user/reset/resetpassword/";
            logoutUserEndPoint =  qmeContants.qmeservice+"/logout";
        }));

        it('Should have a QMe User Service', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should have a No Signed in User Service', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            expect(qmeUserService.currentUser()).toBe(null);
        });

        it('Should have QMe User for valid User login ', function() {
            expect(qmeUserService).toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            var user = {
                "authToken": "someauthtoken",
                "userID": 1,
                "userName": "testuser",
                "userPassword": null,
                "userFirstName": "Test",
                "userLastName": "User",
                "userEmail": "test.user@gmail.com",
                "userLastLoginDate": "2015-28-05 13:35:29",
                "userRoles": ['USER']
            };
            expect(qmeUserService.currentUser()).toBe(null);
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(200,user);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .login(credentials)
                .then(
                function(){
                    expect(qmeUserService.currentUser()).toBeDefined();
                    expect(qmeUserService.currentUser().isSignedIn()).toBe(true);
                    expect(qmeUserService.currentUser().isAdmin()).toBe(false);
                    expect(qmeUserService.currentUser().username()).toBe('testuser');
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should have QMe User for valid admin login ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            var user = {
                "authToken": "someauthtoken",
                "userID": 1,
                "userName": "testadmin",
                "userPassword": null,
                "userFirstName": "Test",
                "userLastName": "User",
                "userEmail": "test.user@gmail.com",
                "userLastLoginDate": "2015-28-05 13:35:29",
                "userRoles": ['ADMIN','USER']
            };
            expect(qmeUserService.currentUser()).toBe(null);
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(200,user);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .login(credentials)
                .then(
                function(){
                    expect(qmeUserService.currentUser()).toBeDefined();
                    expect(qmeUserService.currentUser().isSignedIn()).toBe(true);
                    expect(qmeUserService.currentUser().isAdmin()).toBe(true);
                    expect(qmeUserService.currentUser().username()).toBe('testadmin');
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should have No QMe User for valid logout ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            var user = {
                "authToken": "someauthtoken",
                "userID": 1,
                "userName": "testadmin",
                "userPassword": null,
                "userFirstName": "Test",
                "userLastName": "User",
                "userEmail": "test.user@gmail.com",
                "userLastLoginDate": "2015-28-05 13:35:29",
                "userRoles": ['ADMIN','USER']
            };
            expect(qmeUserService.currentUser()).toBe(null);
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(200,user);
            httpBackend.whenGET(/js\//).respond(200,{});
            httpBackend.expectPOST(logoutUserEndPoint).respond(200,user);
            qmeUserService
                .login(credentials)
                .then(
                function(){
                    expect(qmeUserService.currentUser()).toBeDefined();
                    expect(qmeUserService.currentUser().isSignedIn()).toBe(true);
                    expect(qmeUserService.currentUser().isAdmin()).toBe(true);
                    expect(qmeUserService.currentUser().username()).toBe('testadmin');
                    qmeUserService.logout();
                    expect(qmeUserService.currentUser()).toBe(null);
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should have valid error for server error ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .login(credentials)
                .then(
                function(res){
                },
                function(error){
                    expect(qmeUserService.currentUser()).toBe(null);
                    expect(error.status).toBe(500);
                }
            );
            httpBackend.flush();
        });

        it('Should have valid error for user not found error ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(404,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .login(credentials)
                .then(
                function(res){

                },
                function(error){
                    expect(qmeUserService.currentUser()).toBe(null);
                    expect(error.status).toBe(404);
                }
            );
            httpBackend.flush();
        });

        it('Should have valid error for user not authorized error ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(403,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .login(credentials)
                .then(
                function(res){

                },
                function(error){
                    expect(qmeUserService.currentUser()).toBe(null);
                    expect(error.status).toBe(403);
                }
            );
            httpBackend.flush();
        });

        it('Should throw invalid error for wrong data on User Staging ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(userStagingEndpoint,user).respond(400,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .stageUser(user)
                .then(
                function(res){
                },
                function(error){
                    expect(error.status).toBe(400);
                }
            );
            httpBackend.flush();
        });


        it('Should throw conflict error for duplicate data on User Staging ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(userStagingEndpoint,user).respond(409,{});
            httpBackend.whenGET(/js\//).respond(200,{});

            qmeUserService
                .stageUser(user)
                .then(
                function(res){
                },
                function(error){
                    expect(error.status).toBe(409);
                }
            );
            httpBackend.flush();
        });


        it('Should throw server error for server data on User Staging', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(userStagingEndpoint,user).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .stageUser(user)
                .then(
                function(res){
                },
                function(error){
                    expect(error.status).toBe(500);
                }
            );
            httpBackend.flush();
        });


        it('Should stage user for valid data on User Staging', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'testuser',
                "userPassword": 'somepassword' ,
                "userFirstName": 'Test',
                "userLastName": 'User',
                "userEmail": 'test.user@gmail.com'
            };
            httpBackend.expectPOST(userStagingEndpoint,user).respond(200,"sometoken");
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .stageUser(user)
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should Confirm User for valid User Staging Confirmation', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectPOST(userConfirmEndpoint,"sometoken").respond(200,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .confirmUser("sometoken")
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should Handle Error on Staging Confirmation Exception ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectPOST(userConfirmEndpoint,"sometoken").respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .confirmUser("sometoken")
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                    expect(error).toBeDefined();
                    expect(error.status).toBeDefined();
                    expect(error.status).toBe(500);
                }
            );
            httpBackend.flush();
        });

        it('Should throw invalid error for wrong data on User Registration ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(userRegisterEndpoint,user).respond(400,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .register(user)
                    .then(
                    function(res){
                    },
                    function(error){
                        expect(error.status).toBe(400);
                    }
            );
            httpBackend.flush();
        });

        it('Should throw conflict error for duplicate data on User Registration ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(userRegisterEndpoint,user).respond(409,{});
            httpBackend.whenGET(/js\//).respond(200,{});

            qmeUserService
                .register(user)
                    .then(
                        function(res){
                        },
                        function(error){
                            expect(error.status).toBe(409);
                        }
            );
            httpBackend.flush();
        });


        it('Should throw server error for server data on User Registration', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(userRegisterEndpoint,user).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .register(user)
                    .then(
                        function(res){
                        },
                        function(error){
                            expect(error.status).toBe(500);
                        }
            );
            httpBackend.flush();
        });


        it('Should register user for valid data on User Registration', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'testuser',
                "userPassword": 'somepassword' ,
                "userFirstName": 'Test',
                "userLastName": 'User',
                "userEmail": 'test.user@gmail.com'
            };
            var registereduser = {
                "userName": "testuser",
                "userPassword": null,
                "userFirstName": "Test",
                "userLastName": "User",
                "userEmail": "test.user@gmail.com",
                "userId": 1,
                "userRegisteredDate": "2015-28-05 13:35:29",
                "userUpdateDate": "2015-28-05 13:35:29",
                "updateUserID": 0,
                "updateUserName": "",
                "userRoles": []
            };
            httpBackend.expectPOST(userRegisterEndpoint,user).respond(200,registereduser);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .register(user)
                    .then(
                        function(res){
                            expect(res).toBeDefined();
                        },
                        function(error){
                        }
            );
            httpBackend.flush();
        });


        it('Should submit reset request with url for password reset ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var useremail = "some-email";
            httpBackend.expectPUT(userForgotPaswordEndpoint+useremail,qmeContants.reseturl).respond(200,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .resetPassword(useremail)
                .then(
                    function(res){
                        expect(res).toBeDefined();
                    },
                    function(error){
                    }
            );
            httpBackend.flush();
        });



        it('Should return user not found error 404 on password reset ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var useremail = "some-email";
            httpBackend.expectPUT(userForgotPaswordEndpoint+useremail,qmeContants.reseturl).respond(404,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .resetPassword(useremail)
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                    expect(error.status).toBe(404);
                }
            );
            httpBackend.flush();
        });


        it('Should return internal server error 500  on password reset ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var useremail = "some-email";
            httpBackend.expectPUT(userForgotPaswordEndpoint+useremail,qmeContants.reseturl).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .resetPassword(useremail)
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                    expect(error.status).toBe(500);
                }
            );
            httpBackend.flush();
        });

        it('Should submit password reset request ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var resetrequest = {
                token:"sometoken",
                userName:"someusername",
                userPassword:"somepassword"
            };
            var user = {
                "authToken": "someauthtoken",
                "userID": 1,
                "userName": "testadmin",
                "userPassword": null,
                "userFirstName": "Test",
                "userLastName": "User",
                "userEmail": "test.user@gmail.com",
                "userLastLoginDate": "2015-28-05 13:35:29",
                "userRoles": ['ADMIN','USER']
            };
            var useremail = "some-email";
            httpBackend.expectPUT(userResetPaswordEndpoint+useremail,resetrequest).respond(200,user);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .submitResetPassword("sometoken","someusername", useremail, "somepassword")
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should handle submit password request error ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var resetrequest = {
                token:"sometoken",
                userName:"someusername",
                userPassword:"somepassword"
            };
            var useremail = "some-email";
            httpBackend.expectPUT(userResetPaswordEndpoint+useremail,resetrequest).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .submitResetPassword("sometoken","someusername", useremail, "somepassword")
                .then(
                function(res){
                },
                function(error){
                    expect(error).toBeDefined();
                }
            );
            httpBackend.flush();
        });

        it('Should have valid Registration States ', function() {
            expect(qmeUserService).toBeDefined();
            expect(qmeUserService.isRegistering()).toBe(false);
            qmeUserService.startRegistering();
            expect(qmeUserService.isRegistering()).toBe(true);
            qmeUserService.endRegistering();
            expect(qmeUserService.isRegistering()).toBe(false);
        });

        it('Should have valid Resetting States ', function() {
            expect(qmeUserService).toBeDefined();
            expect(qmeUserService.isResetting()).toBe(false);
            qmeUserService.startResetting();
            expect(qmeUserService.isResetting()).toBe(true);
            qmeUserService.endResetting();
            expect(qmeUserService.isResetting()).toBe(false);
        });

        it('Should handle valid 500 Server Error for count user request ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectGET(userCountEndPoint).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .countUsers()
                .then(
                function(res){
                },
                function(error){
                    expect(error).toBeDefined();
                }
            );
            httpBackend.flush();
        });

        it('Should handle valid count user  request ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var countresponse =  {
                                    "content":"264",
                                    "links":[
                                        {
                                         "rel":"self",
                                         "href":"http://localhost:8002/qme/user/count"
                                         },
                                         {
                                            "rel": "Get_Paged_User_list - (sortfields:USERNAME,EMAIL,FIRSTNAME,LASTNAME,REGISTERDATE,LOGINDATE)",
                                            "href": "http://localhost:8002/qme/user/paged?page=0&pagesize=1&sorttype=true&sortfields=USERNAME"
                                         }
                                    ]
            };
            httpBackend.expectGET(userCountEndPoint).respond(200,countresponse);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .countUsers()
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                }
            );
            httpBackend.flush();
        });


        it('Should handle valid 500 Server Error for list user request ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectGET(userEndPoint).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .listUsers()
                .then(
                function(res){
                },
                function(error){
                    expect(error).toBeDefined();
                }
            );
            httpBackend.flush();
        });

        it('Should handle valid list user request ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            var userList = [
                {
                    "userID": 1,
                    "userName": "testuser1",
                    "userPassword": null,
                    "userFirstName": "Test1",
                    "userLastName": "User1",
                    "userEmail": "test1.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                },
                {
                    "userID": 2,
                    "userName": "testuser2",
                    "userPassword": null,
                    "userFirstName": "Test2",
                    "userLastName": "User2",
                    "userEmail": "test2.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                },
                {
                    "userID": 3,
                    "userName": "testuser3",
                    "userPassword": null,
                    "userFirstName": "Test3",
                    "userLastName": "User3",
                    "userEmail": "test3.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                }
            ];
            httpBackend.expectGET(userEndPoint).respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .listUsers()
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should handle valid 500 Server Error for list user paged request ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .listUsersPaged(0)
                .then(
                function(res){
                },
                function(error){
                    expect(error).toBeDefined();
                }
            );
            httpBackend.flush();
        });

        it('Should handle valid list user paged request ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            var userList = [
                {
                    "userID": 1,
                    "userName": "testuser1",
                    "userPassword": null,
                    "userFirstName": "Test1",
                    "userLastName": "User1",
                    "userEmail": "test1.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                },
                {
                    "userID": 2,
                    "userName": "testuser2",
                    "userPassword": null,
                    "userFirstName": "Test2",
                    "userLastName": "User2",
                    "userEmail": "test2.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                },
                {
                    "userID": 3,
                    "userName": "testuser3",
                    "userPassword": null,
                    "userFirstName": "Test3",
                    "userLastName": "User3",
                    "userEmail": "test3.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                }
            ];
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage).respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .listUsersPaged(0)
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should handle valid 500 Server Error for list user paged request with sort fields ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .listUsersPaged(0,true,"USERNAME")
                .then(
                function(res){
                },
                function(error){
                    expect(error).toBeDefined();
                }
            );
            httpBackend.flush();
        });

        it('Should handle valid list user paged request ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            var userList = [
                {
                    "userID": 1,
                    "userName": "testuser1",
                    "userPassword": null,
                    "userFirstName": "Test1",
                    "userLastName": "User1",
                    "userEmail": "test1.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                },
                {
                    "userID": 2,
                    "userName": "testuser2",
                    "userPassword": null,
                    "userFirstName": "Test2",
                    "userLastName": "User2",
                    "userEmail": "test2.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                },
                {
                    "userID": 3,
                    "userName": "testuser3",
                    "userPassword": null,
                    "userFirstName": "Test3",
                    "userLastName": "User3",
                    "userEmail": "test3.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                }
            ];
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .listUsersPaged(0,true,"USERNAME")
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                }
            );
            httpBackend.flush();
        });

        it('Should handle valid QMe User Update Request for 500 Server Error', function() {
            expect(qmeUserService).toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            var user = {
                "authToken": "someauthtoken",
                "userID": 1,
                "userName": "testuser",
                "userPassword": qmeContants.password_mask,
                "userFirstName": "Test",
                "userLastName": "User",
                "userEmail": "test.user@gmail.com",
                "userLastLoginDate": "2015-28-05 13:35:29",
                "userRoles": ['USER']
            };
            expect(qmeUserService.currentUser()).toBe(null);
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(200,user);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .login(credentials)
                .then(
                function(){
                    expect(qmeUserService.currentUser()).toBeDefined();
                    expect(qmeUserService.currentUser().isSignedIn()).toBe(true);
                    expect(qmeUserService.currentUser().isAdmin()).toBe(false);
                    expect(qmeUserService.currentUser().username()).toBe('testuser');
                },
                function(error){
                }
            );
            httpBackend.flush();
            httpBackend.expectPUT(userEndPoint+"/1").respond(500,user);
            qmeUserService
                .updateUser(user)
                .then(
                function(res){
                },
                function(error){
                    expect(error).toBeDefined();
                }
            );
            httpBackend.flush();
        });

        it('Should handle valid QMe User Update Request', function() {
            expect(qmeUserService).toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            var user = {
                "authToken": "someauthtoken",
                "userID": 1,
                "userName": "testuser",
                "userPassword": qmeContants.password_mask,
                "userFirstName": "Test",
                "userLastName": "User",
                "userEmail": "test.user@gmail.com",
                "userLastLoginDate": "2015-28-05 13:35:29",
                "userRoles": ['USER']
            };
            expect(qmeUserService.currentUser()).toBe(null);
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(200,user);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .login(credentials)
                .then(
                function(){
                    expect(qmeUserService.currentUser()).toBeDefined();
                    expect(qmeUserService.currentUser().isSignedIn()).toBe(true);
                    expect(qmeUserService.currentUser().isAdmin()).toBe(false);
                    expect(qmeUserService.currentUser().username()).toBe('testuser');
                },
                function(error){
                }
            );
            httpBackend.flush();
            httpBackend.expectPUT(userEndPoint+"/1").respond(200,user);
            qmeUserService
                .updateUser(user)
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                }
            );
            httpBackend.flush();
        });
    });
})();
