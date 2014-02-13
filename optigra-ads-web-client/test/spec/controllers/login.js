'use strict';

describe('Controller: LoginCtrl', function() {

    // load the controller's module
    beforeEach(module('optigraAdsWebClientApp'));

    var LoginCtrl,
        scope;

    // Initialize the controller and a mock scope
    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        LoginCtrl = $controller('LoginCtrl', {
            $scope: scope
        });
    }));

    it('should attach a login to the scope', function() {
        expect(scope.login).toBe("");
    });

    it('should attach a password to the scope', function() {
        expect(scope.password).toBe("");
    });
});