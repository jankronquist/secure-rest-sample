angular.module('secureRestSample', []).
    config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(false);

        $routeProvider
            .when('/', { templateUrl: '/templates/home.html'})
            .when('/home', { templateUrl: '/templates/home.html'})
            .when('/restricted', { templateUrl: '/templates/restricted.html', controller: HomeCtrl })
            .when('/login', { templateUrl: '/templates/login.html', controller: RestrictedCtrl });
    })

function RouteCtrl($scope, $http, $location) {
    $scope.isAuthenticated = false;
    $scope.user = undefined;

    function setAuthenticationStatus(isAuthenticated, user) {
        $scope.isAuthenticated = isAuthenticated;
        $scope.user = user;
    }

    function checkUserAuthentication() {
        $http.get("/api/authentication/status")
            .success(function (data) {
                setAuthenticationStatus(data.authenticated, data.user);
            })
            .error(function () {
                setAuthenticationStatus(false, undefined);
            });
    }

    checkUserAuthentication();
}


function HomeCtrl($scope, $http) {
    $scope.developers = undefined;

    $http.get("/api/sample/developers")
        .success(function (data, status) {
            $scope.developers = data;
        })
}
function RestrictedCtrl($scope, $http, $location) {
    $scope.greet = undefined;
    $scope.secretMessage = undefined;

    $http.get("/api/sample/greet")
        .success(function (data) {
            $scope.greet = data;
        })
        .error(function (data, status) {
            if(status == 403) $location.path("/login")
        });
    $http.get("/api/sample/secret")
        .success(function (data, status) {
            console.log(status);
            if(status == 200) $scope.secretMessage = data;
        })
}
