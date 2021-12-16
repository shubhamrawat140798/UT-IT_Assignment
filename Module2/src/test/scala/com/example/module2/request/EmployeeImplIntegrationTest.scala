package com.example.module2.request
import com.example.module2.db.CompanyReadDto
import com.example.module2.models.Employee
import com.example.module2.validator.{EmailValidator, EmployeeValidator}
import org.scalatest.funsuite.AnyFunSuite

class EmployeeImplIntegrationTest extends AnyFunSuite{
  val companyName = new CompanyReadDto
  val validateEmail = new EmailValidator
  val employeeValidator = new EmployeeValidator(companyName,validateEmail)

  val employeeImpl = new EmployeeImpl(employeeValidator)

  test("user can not created bcz company doent exist"){
    val shubham:Employee = new Employee("Shubham","Rawat",23,12000,"Intern","Google","shubham.singh@knoldus.com")
    val result = employeeImpl.createEmployee(shubham)
    assert(result.isEmpty)
  }

  test("user can not created bcz email is invalid"){
    val shubham:Employee = new Employee("Shubham","Rawat",23,12000,"Intern","Knoldus","shubham.singh@knolduscom")
    val result = employeeImpl.createEmployee(shubham)
    assert(result.isEmpty)
  }
  test("user can not created bcz email invalid and company doesnt exist in DB"){
    val shubham:Employee = new Employee("Shubham","Rawat",23,12000,"Intern","Google","shubham.singh@knolducom")
    val result = employeeImpl.createEmployee(shubham)
    assert(result.isEmpty)
  }
  test("user can be created"){
    val shubham:Employee = new Employee("Shubham","Rawat",23,12000,"Intern","Knoldus","shubham.singh@knoldus.com")
    val result = employeeImpl.createEmployee(shubham)
    assert(result.isDefined)
  }
}