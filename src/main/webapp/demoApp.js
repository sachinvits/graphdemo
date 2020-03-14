var app = angular.module('app',[]);

app.controller('EmployeeController', ['$scope','EmployeeService', function ($scope,EmployeeService) {
	  
    $scope.update = function () {
        EmployeeService.updateEmployee($scope.employee.empId, $scope.employee.name)
          .then(function success(response){
              $scope.message = 'Employee data updated!';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = 'Error updating employee!';
              $scope.message = '';
          });
    }
    
    $scope.getEmployee = function () {
        var empId = $scope.employee.empId;
        EmployeeService.getEmployee(empId)
          .then(function success(response){
              $scope.employee = response.data;
              $scope.employee.empId = empId;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'Employee not found!';
              }
              else {
                  $scope.errorMessage = "Error getting employee!";
              }
          });
    }
    
    $scope.addEmployee = function () {
        if ($scope.employee != null && $scope.employee.name) {
            EmployeeService.addEmployee($scope.employee.empId, $scope.employee.name)
              .then (function success(response){
                  $scope.message = 'Employee added!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error adding employee!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
    }
    
    $scope.deleteEmployee = function () {
        EmployeeService.deleteEmployee($scope.employee.empId)
          .then (function success(response){
              $scope.message = 'Employee deleted!';
              $scope.employee = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error deleting employee!';
              $scope.message='';
          })
    }
    
    $scope.getAllEmployees = function () {
        EmployeeService.getAllEmployees()
          .then(function success(response){
              $scope.employees = response.data.employees;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error getting Employees!';
          });
    }

}]);

app.service('EmployeeService',['$http', function ($http) {
	var basePath = '/graphdemo/v1/employee';
	
    this.getEmployee = function getEmployee(empId){
        return $http({
          method: 'GET',
          url: '/'+ empId
        });
	}
	
    this.addEmployee = function addEmployee(empId, name){
        return $http({
          method: 'POST',
          url: basePath + '/add-emplyee',
          data: {empId:empId, name:name}
        });
    }
	
    this.deleteEmployee = function deleteEmployee(empId){
        return $http({
          method: 'DELETE',
          url: basePath + '/delete-emplyee/' + empId
        })
    }
	
    this.updateEmployee = function updateEmployee(empId, name){
        return $http({
          method: 'PUT',
          url: basePath + '/update-emplyee/' + empId,
          data: {empId:empId, name:name}
        })
    }
	
    this.getAllEmployees = function getAllEmployees(){
        return $http({
          method: 'GET',
          url: basePath + '/all'
        });
    }

}]);