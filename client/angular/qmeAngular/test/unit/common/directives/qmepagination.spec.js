(function () {

	"use strict";

    describe('Unit: QMe Pagination Directive', function () {

        var qmePagination,compile,rootScope,qmePageCtrl,qmePageSession, mockPageFunc;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($compile, $rootScope, $controller,_qmePageSession_) {
            compile = $compile;
            rootScope = $rootScope;
            qmePageSession = _qmePageSession_;
            qmePageCtrl  = $controller('qmePageCtrl', {
                $scope: rootScope,
                qmePageSession:qmePageSession
            });
            mockPageFunc - function(){};
        }));

        it('Should have a QMe Pagination Directive', function() {
            expect(compile).toBeDefined();
            expect(rootScope).toBeDefined();
            qmePagination = compile("<qme-page qme-totalcount='200' qme-pagingfunction='mockPageFunc'></qme-page>")(rootScope)
            rootScope.$digest();
            expect(qmePagination.html()).toContain("qmePageCtrl.goNext()");
        });

        //No Pagination
        it('Should have a QMe Page Session and return valid page and page group states when total records is 0', function() {
            expect(qmePageSession).toBeDefined();

            qmePageSession.create(0);
            expect(qmePageSession.requiresPagination()).toBe(false);
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setPrevious();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setNext();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setPage(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(false);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.getPreviousGroup()).toBe(0);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.getNextGroup()).toBe(0);
        });

        //No Pagination
        it('Should have a QMe Page Session and return valid page and page group states when total records is 5 ', function() {
            expect(qmePageSession).toBeDefined();

            qmePageSession.create(5);
            expect(qmePageSession.requiresPagination()).toBe(false);
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(1);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setPrevious();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setNext();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setPage(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(true);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.getPreviousGroup()).toBe(0);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.getNextGroup()).toBe(0);
        });

        //Pagination 120 Records 50 Per Page so 3 Pages (50/50/20) [No Page Groupings]
        it('Should have a QMe Page Session and return valid page and page group states when total records is 120 ', function() {
            expect(qmePageSession).toBeDefined();

            qmePageSession.create(120);
            expect(qmePageSession.requiresPagination()).toBe(true);
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(3);
            expect(qmePageSession.getPage()).toBe(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(2);
            expect(qmePageSession.getPage()).toBe(2);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(false);

            qmePageSession.getFirst();
            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(1);
            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(2);
            qmePageSession.getLast();
            expect(qmePageSession.getPage()).toBe(2);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(false);
        });

        //Pagination 826 Records 50 Pere Page so 16 Pages (50/50/50/50/50/50/50/50/50/50/50/50/50/50/50/50) [Two Groupings 1 - 10 and 11 - 16]
        it('Should have a QMe Page Session and return valid page and page group states when total records is 826 ', function() {
            expect(qmePageSession).toBeDefined();

            qmePageSession.create(826);
            expect(qmePageSession.requiresPagination()).toBe(true);
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(10);
            expect(qmePageSession.getPage()).toBe(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(false);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.getLast()).toBe(16);
            expect(qmePageSession.getPage()).toBe(16);
            expect(qmePageSession.isCurrentPage(16)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(true);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);

            qmePageSession.getFirst();
            expect(qmePageSession.getPage()).toBe(0);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(1);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(1)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(2);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(2)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(3);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(3)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(4);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(4)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(5);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(5)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(6);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(6)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(7);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(7)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);


            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(8);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(8)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(9);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(9)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(10);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(10)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(11);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(11)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(12);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(12)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(13);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(13)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(14);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(14)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(15);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(15)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(16);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(16)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(true);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(15);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(15)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(14);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(14)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(13);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(13)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(12);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(12)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(11);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(11)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(10);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.isCurrentPage(10)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(9);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(9)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(8);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(8)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(7);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(7)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(6);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(6)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(5);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(5)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(4);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(4)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(3);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(3)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(2);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(2)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(1);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(1)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(false);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.setPrevious();
            expect(qmePageSession.getPage()).toBe(0);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.getFirst();
            expect(qmePageSession.getPage()).toBe(0);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(false);

            qmePageSession.getNextGroup();
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(7);
            expect(qmePageSession.showPreviousGroup()).toBe(true);
            expect(qmePageSession.showNextGroup()).toBe(false);

            qmePageSession.getPreviousGroup();
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(10);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.showNextGroup()).toBe(true);
        });
    });

})();