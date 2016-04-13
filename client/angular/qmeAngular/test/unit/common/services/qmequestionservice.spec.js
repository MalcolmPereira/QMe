(function () {

	"use strict";

    describe('Service: QMe Question Service', function() {

        var scope,
            state,
            qmeQuestionService,
            qmeUserSession,
            httpBackend,
            qmeContants,
            questionEndPoint,
            questionPagedEndPoint,
            questionCountEndPoint
            ;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,$state, _qmeQuestionService_, _qmeUserSession_,$httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            state = $state;
            qmeQuestionService = _qmeQuestionService_;
            qmeUserSession = _qmeUserSession_;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            questionEndPoint     = qmeContants.qmeservice+"/question";
            questionPagedEndPoint = qmeContants.qmeservice+"/question/paged";
            questionCountEndPoint  = qmeContants.qmeservice+"/question/count";
            qmeUserSession.create("sometoken", 1 , "some", "some", "last", "someemail", "2015-14-11 19:31:32", ["USER","ADMIN"]);
         }));

        it('Should have a QMe Question Service', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should handle valid count question request ', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            var countresponse =  {
                "content":"264",
                "links":[
                    {
                        "rel":"self",
                        "href":"http://localhost:8002/qme/question/count"
                    }
                ]
            };

            httpBackend.expectGET(questionCountEndPoint).respond(200,countresponse);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeQuestionService
                .countQuestions()
                .then(
                    function(res){
                        expect(res).toBeDefined();
                    },
                    function(error){
                    }
                );
            httpBackend.flush();

        });

        it('Should handle valid 500 Server Error for count question request ', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            httpBackend.expectGET(questionCountEndPoint).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});

            qmeQuestionService
                .countQuestions()
                .then(
                    function(res){
                    },
                    function(error){
                        expect(error).toBeDefined();
                    }
                );
            httpBackend.flush();

        });

        it('Should handle valid 500 Server Error for list questions request ', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            httpBackend.expectGET(questionEndPoint).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});

            qmeQuestionService
                .listQuestions()
                .then(
                    function(res){
                    },
                    function(error){
                        expect(error).toBeDefined();
                    }
                );

            httpBackend.flush();
        });

        it('Should handle valid list questions request ', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            var questionList = [
                {
                    "questionId": 1,
                    "categoryId": 1,
                    "questionText": "Some Question Text 1",
                    "answer": "Some Answer",
                    "questionPoint": 1,
                    "likes": 0,
                    "questionCreateDate": "2015-24-12 09:41:45",
                    "createUserID": 1,
                    "questionUpdateDate": "2015-24-12 09:41:45",
                    "updateUserID": 1,
                    "answerReferenceMediaList": null,
                    "answerOptionList": null
                },
                {
                    "questionId": 2,
                    "categoryId": 1,
                    "questionText": "Some Question Text 2",
                    "answer": "Some Answer",
                    "questionPoint": 1,
                    "likes": 0,
                    "questionCreateDate": "2015-24-12 09:41:45",
                    "createUserID": 1,
                    "questionUpdateDate": "2015-24-12 09:41:45",
                    "updateUserID": 1,
                    "answerReferenceMediaList": null,
                    "answerOptionList": null
                }
            ];

            httpBackend.expectGET(questionEndPoint).respond(200,questionList);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeQuestionService
                .listQuestions()
                .then(
                    function(res){
                        expect(res).toBeDefined();
                        expect(res.length).toBe(2);
                    },
                    function(error){
                    }
                );
            httpBackend.flush();
        });

        it('Should handle valid 500 Server Error for list question paged request ', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            httpBackend.expectGET(questionPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeQuestionService
                .listQuestionsPaged(0)
                .then(
                    function(res){
                    },
                    function(error){
                        expect(error).toBeDefined();
                    }
                );
            httpBackend.flush();

        });

        it('Should handle valid list question paged request ', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            var questionList = [
                {
                    "questionId": 1,
                    "categoryId": 1,
                    "questionText": "Some Question Text 1",
                    "answer": "Some Answer",
                    "questionPoint": 1,
                    "likes": 0,
                    "questionCreateDate": "2015-24-12 09:41:45",
                    "createUserID": 1,
                    "questionUpdateDate": "2015-24-12 09:41:45",
                    "updateUserID": 1,
                    "answerReferenceMediaList": null,
                    "answerOptionList": null
                },
                {
                    "questionId": 2,
                    "categoryId": 1,
                    "questionText": "Some Question Text 2",
                    "answer": "Some Answer",
                    "questionPoint": 1,
                    "likes": 0,
                    "questionCreateDate": "2015-24-12 09:41:45",
                    "createUserID": 1,
                    "questionUpdateDate": "2015-24-12 09:41:45",
                    "updateUserID": 1,
                    "answerReferenceMediaList": null,
                    "answerOptionList": null
                }
            ];

            httpBackend.expectGET(questionPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage).respond(200,questionList);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeQuestionService
                .listQuestionsPaged(0)
                .then(
                    function(res){
                        expect(res).toBeDefined();
                        expect(res.length).toBe(2);
                    },
                    function(error){
                    }
                );
            httpBackend.flush();
        });

        it('Should handle valid 500 Server Error for list question paged request with sort fields ', function() {

            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            httpBackend.expectGET(questionPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=QUESTION").respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeQuestionService
                .listQuestionsPaged(0,true,"QUESTION")
                .then(
                    function(res){
                    },
                    function(error){
                        expect(error).toBeDefined();
                    }
                );
            httpBackend.flush();
        });

        it('Should handle valid list questions paged request ', function() {

            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            var questionList = [
                {
                    "questionId": 1,
                    "categoryId": 1,
                    "questionText": "Some Question Text 1",
                    "answer": "Some Answer",
                    "questionPoint": 1,
                    "likes": 0,
                    "questionCreateDate": "2015-24-12 09:41:45",
                    "createUserID": 1,
                    "questionUpdateDate": "2015-24-12 09:41:45",
                    "updateUserID": 1,
                    "answerReferenceMediaList": null,
                    "answerOptionList": null
                },
                {
                    "questionId": 2,
                    "categoryId": 1,
                    "questionText": "Some Question Text 2",
                    "answer": "Some Answer",
                    "questionPoint": 1,
                    "likes": 0,
                    "questionCreateDate": "2015-24-12 09:41:45",
                    "createUserID": 1,
                    "questionUpdateDate": "2015-24-12 09:41:45",
                    "updateUserID": 1,
                    "answerReferenceMediaList": null,
                    "answerOptionList": null
                }
            ];

            httpBackend.expectGET(questionPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=QUESTION").respond(200,questionList);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeQuestionService
                .listQuestionsPaged(0,true,"QUESTION")
                .then(
                    function(res){
                        expect(res).toBeDefined();
                        expect(res.length).toBe(2);
                    },
                    function(error){
                    }
                );
            httpBackend.flush();
        });
    });

})();