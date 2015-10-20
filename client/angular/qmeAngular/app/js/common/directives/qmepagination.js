(function () {

    'use strict';

    ngQMe

        .directive('qmePage', function (){
            return {
                restrict: 'E',
                templateUrl: 'js/common/directives/qmepagination.tmpl.html',
                controller: 'qmePageCtrl',
                controllerAs: 'qmePageCtrl',
                scope: {
                    qmeTotalcount: '=',
                    qmePagingfunction: '&'
                }
            };
        })

        .service('qmePageSession', function (QME_CONSTANTS) {

            var qmePageSession  = this;

            var _totalRecCount  = null;
            var _recPerPage     = null;
            var _pagesPerPage   = null;
            var _currentPage    = null;
            var _lastPage       = null;
            var _pages         = [];

            qmePageSession.create = function (totalRecCount){
                _totalRecCount  = totalRecCount ;
                _recPerPage     = QME_CONSTANTS.rowsperpage;
                _pagesPerPage   = QME_CONSTANTS.pagesperpage;
                _currentPage    = 0;
                _lastPage       = Math.ceil(_totalRecCount / _recPerPage);
                _pages          = [];

                for (var i = 1; i <  _lastPage + 1; i++) {
                    _pages.push(i);
                }
                return qmePageSession;
            };

            qmePageSession.destroy = function () {
                _totalRecCount  = null;
                _recPerPage     = null;
                _currentPage    = null;
                _lastPage       = null;
                _pages          = [];
                return undefined;
            }

            qmePageSession.pages = function(){
                return _pages;
            }

            qmePageSession.getFirst = function(){
                if(! qmePageSession.isFirstPage() ){
                    _currentPage = 0;
                }
                return _currentPage;
            }

            qmePageSession.getLast = function(){
                if(! qmePageSession.isLastPage() ){
                    _currentPage = _lastPage - 1;
                }
                return _currentPage;
            }

            qmePageSession.setPrevious = function(){
                if(! qmePageSession.isFirstPage() ){
                    --_currentPage;
                }
            }

            qmePageSession.setNext = function(){
                if(! qmePageSession.isLastPage() ){
                    ++_currentPage;
                }
            }

            qmePageSession.getPage = function(){
                return _currentPage;
            }

            qmePageSession.setPage = function(pageNumber){
                return _currentPage = pageNumber;
            }

            qmePageSession.isCurrentPage = function(pageNumber){
                return (pageNumber === _currentPage );
            };

            qmePageSession.isFirstPage = function(){
                return (_currentPage === 0);
            }

            qmePageSession.isLastPage = function(){
                return ( _currentPage === _lastPage - 1);
            }

            qmePageSession.isExceedPagesPerPage = function(){
                return (_lastPage > QME_CONSTANTS.pagesperpage)
            }
        })

        .controller('qmePageCtrl',QMePageController);
        QMePageController.$inject = ['$scope', 'qmePageSession'];
        function QMePageController($scope,qmePageSession) {
            var qmePage = this;

            qmePage.functionCall  = $scope.qmePagingfunction;

            qmePage.qmePageSession = qmePageSession.destroy();

            qmePage.qmePageSession = qmePageSession.create($scope.qmeTotalcount);

            qmePage.goPage = function(pageNumber){
                qmePage.qmePageSession.setPage(pageNumber);
                $scope.qmePagingfunction()(pageNumber);
            }

            qmePage.isCurrentPage = function(pageNumber){
                return qmePage.qmePageSession.isCurrentPage(pageNumber);
            };

            qmePage.isFirstPage = function(){
                return qmePage.qmePageSession.isFirstPage();
            }

            qmePage.isLastPage = function(){
                return  qmePage.qmePageSession.isLastPage();
            }

            qmePage.goPrevious = function(){
                if(!qmePage.isFirstPage()){
                    qmePage.qmePageSession.setPrevious();
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getPage());
                }
            }

            qmePage.goFirst = function(){
                if(!qmePage.isFirstPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getFirst());
                }
            }

            qmePage.goNext = function(){
                if(!qmePage.isLastPage()){
                    qmePage.qmePageSession.setNext();
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getPage());
                }
            }

            qmePage.goLast = function(){
                if(!qmePage.isLastPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getLast());
                }
            }

            qmePage.isExceedPagesPerPage = function(){
                return qmePage.qmePageSession.isExceedPagesPerPage();
            }

            qmePage.showPreviousGroup = function(){
                return false;
            }

            qmePage.showNextGroup = function(){
                return false;
            }
        }
})();