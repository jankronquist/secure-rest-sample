function AppCtrl($scope, $http) {

}

function NavCtrl($scope, $http) {
    $scope.isAuthenticated = false;
    $scope.user = undefined;

    function setAuthenticationStatus(isAuthenticated, user) {
        $scope.isAuthenticated = isAuthenticated;
        $scope.user = user;
    }

    function checkUserAuthentication() {
        $http.get("/api/authentication/status")
            .success(function (data) {
                console.log(data);
                setAuthenticationStatus(data.authenticated, data.user);
            })
            .error(function (data, status) {
                setNotLoggedIn();
            });
    }

    checkUserAuthentication();
}