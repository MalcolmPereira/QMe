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
            var _currentGroup   = null;
            var _pages         = [];

            qmePageSession.create = function (totalRecCount){
                _totalRecCount  = totalRecCount ;
                _recPerPage     = QME_CONSTANTS.rowsperpage;
                _pagesPerPage   = QME_CONSTANTS.pagesperpage;
                _currentPage    = 0;
                _lastPage       = Math.ceil(_totalRecCount / _recPerPage);
                _pages          = [];
                if (_lastPage <= _pagesPerPage) {
                    for (var i = 1; i < _lastPage + 1; i++) {
                        _pages.push(i);
                    }
                    _currentGroup = undefined;
                } else {
                    for (var j = 1; j < _pagesPerPage + 1; j++) {
                        _pages.push(j);
                    }
                    _currentGroup = _pagesPerPage;
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
            };

            qmePageSession.pages = function(){
                return _pages;
            };

            qmePageSession.getFirst = function(){
                if(! qmePageSession.isFirstPage() && qmePageSession.requiresPagination()){
                    _currentPage  = 0;
                    _pages = [];
                    if (_lastPage <= _pagesPerPage) {
                        for (var i = 1; i < _lastPage + 1; i++) {
                            _pages.push(i);
                        }
                        _currentGroup = undefined;
                    } else {
                        for (var j = 1; j < _pagesPerPage + 1; j++) {
                            _pages.push(j);
                        }
                        _currentGroup = _pagesPerPage;
                    }
                }
                return _currentPage;
            };

            qmePageSession.getLast = function(){
                if(! qmePageSession.isLastPage() && qmePageSession.requiresPagination()){
                    _currentPage  = _lastPage - 1;
                    _currentGroup = _lastPage;
                    var startPage =  (_currentGroup + (_pagesPerPage - (Math.ceil(_currentGroup  % _pagesPerPage)))) - _pagesPerPage ;
                    startPage    =   startPage + 1;
                    _pages = [];
                    for (var i = startPage; i < _currentGroup + 1; i++) {
                        _pages.push(i);
                    }
                }
                return _currentPage;
            };

            qmePageSession.setPrevious = function(){
                if(! qmePageSession.isFirstPage() && qmePageSession.requiresPagination()){
                    --_currentPage;
                    if(_pages.indexOf(_currentPage + 1) === -1){
                        qmePageSession.getPreviousGroup();
                    }
                }
            };

            qmePageSession.setNext = function(){
                if(! qmePageSession.isLastPage() && qmePageSession.requiresPagination()){
                    ++_currentPage;
                    if(_pages.indexOf(_currentPage + 1) === -1){
                        qmePageSession.getNextGroup();
                    }
                }
            };

            qmePageSession.getPage = function(){
                return _currentPage;
            };

            qmePageSession.setPage = function(pageNumber){
                return _currentPage = pageNumber;
            };

            qmePageSession.isCurrentPage = function(pageNumber){
                return (pageNumber === _currentPage );
            };

            qmePageSession.isFirstPage = function(){
                return (_currentPage === 0);
            };

            qmePageSession.isLastPage = function(){
                return ( _currentPage === _lastPage - 1);
            };

            qmePageSession.showPreviousGroup = function(){
                return ((!qmePageSession.isFirstPage()) && (_currentGroup) && (_currentGroup > _pagesPerPage))
            };

            qmePageSession.getPreviousGroup = function(){
                if(! qmePageSession.isFirstPage() && qmePageSession.requiresPagination() && qmePageSession.requiresPageGroupings()) {
                    if (_currentGroup >= _lastPage) {
                        _currentGroup = _currentGroup + (_pagesPerPage - (Math.ceil(_currentGroup % _pagesPerPage)));
                    }
                    _currentGroup = _currentGroup - _pagesPerPage;
                    //TODO:Validate cases under which this may occur
                    //FIXME: Delete this after tests
                    /*
                    if (_currentGroup <= 0) {
                        _currentGroup = _pagesPerPage;
                    }
                    */
                    var startPage = (_currentGroup - _pagesPerPage) + 1;
                    //TODO:Validate cases under which this may occur
                    //FIXME: Delete this after tests
                    /*
                    if (startPage <= 0) {
                        startPage = 1;
                    }
                    */

                    _pages = [];
                    for (var i = startPage; i < _currentGroup + 1; i++) {
                        _pages.push(i);
                    }
                    _currentPage = _currentGroup - 1;
                }
                return _currentPage;
            };

            qmePageSession.showNextGroup = function(){
                if(qmePageSession.isLastPage()){
                    return false;
                }
                if(!_currentGroup){
                    return false;
                }
                return ((!qmePageSession.isLastPage()) && (_currentGroup) && (_currentGroup > 0 && _currentGroup < _lastPage -1))
            };

            qmePageSession.getNextGroup = function(){
                if(! qmePageSession.isLastPage() && qmePageSession.requiresPagination() && qmePageSession.requiresPageGroupings()) {
                    var startPage = _currentGroup + 1;
                    _currentGroup = _currentGroup + _pagesPerPage;
                    if (_currentGroup > _lastPage - 1) {
                        _currentGroup = _lastPage;
                    }
                    _pages = [];
                    for (var i = startPage; i <= _currentGroup; i++) {
                        _pages.push(i);
                    }
                    _currentPage = startPage - 1;
                }
                return _currentPage;
            };

            qmePageSession.requiresPagination = function(){
                return (_totalRecCount > _recPerPage);
            }

            qmePageSession.requiresPageGroupings = function(){
                return (_lastPage > _pagesPerPage);
            }
        })

        .controller('qmePageCtrl',QMePageController);
        QMePageController.$inject = ['$scope', 'qmePageSession'];
        function QMePageController($scope,qmePageSession) {
            var qmePage = this;

            qmePage.qmeTotalcount = $scope.qmeTotalcount;

            qmePage.functionCall  = $scope.qmePagingfunction;

            qmePage.qmePageSession = qmePageSession.destroy();

            qmePage.qmePageSession = qmePageSession.create($scope.qmeTotalcount);

            qmePage.goPage = function(pageNumber){
                qmePage.qmePageSession.setPage(pageNumber);
                $scope.qmePagingfunction()(pageNumber);
            };

            qmePage.isCurrentPage = function(pageNumber){
                return qmePage.qmePageSession.isCurrentPage(pageNumber);
            };

            qmePage.isFirstPage = function(){
                return qmePage.qmePageSession.isFirstPage();
            };

            qmePage.isLastPage = function(){
                return  qmePage.qmePageSession.isLastPage();
            };

            qmePage.goPrevious = function(){
                if(!qmePage.isFirstPage()){
                    qmePage.qmePageSession.setPrevious();
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getPage());
                }
            };

            qmePage.goFirst = function(){
                if(!qmePage.isFirstPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getFirst());
                }
            };

            qmePage.goNext = function(){
                if(!qmePage.isLastPage()){
                    qmePage.qmePageSession.setNext();
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getPage());
                    console.log("qmePage.qmePageSession._currentGroup",qmePage.qmePageSession._currentGroup);
                }
            };

            qmePage.goLast = function(){
                if(!qmePage.isLastPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getLast());
                }
            };

            qmePage.showPreviousGroup = function(){
                return qmePage.qmePageSession.showPreviousGroup();
            };

            qmePage.getPreviousGroup = function(){
                if(!qmePage.isFirstPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getPreviousGroup());
                }
            };

            qmePage.showNextGroup = function(){
                return qmePage.qmePageSession.showNextGroup ();
            };

            qmePage.getNextGroup = function(){
                if(!qmePage.isLastPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getNextGroup());
                }
            };

            qmePage.requiresPagination = function(){
                return qmePage.qmePageSession.requiresPagination();
            }
        }
})();