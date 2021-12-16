package com.example.module2.request

import com.example.module2.models.Employee
import com.example.module2.validator.EmployeeValidator
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.funsuite.AnyFunSuite

class EmployeeImplTest extends AnyFunSuite{
  val employeeValidator = mock[EmployeeValidator]
  val shubham:Employee = new Employee("Shubham","Rawat",23,12000,"Intern","Knoldus","shubham.singh@knoldus.com")
  val userImpl = new EmployeeImpl(employeeValidator)

  test("User can be ceated"){
    when(employeeValidator.employeeIsValid(shubham)) thenReturn(true)
    val result = userImpl.createEmployee(shubham)
    assert(result.isDefined)
  }
  test("User can not be created"){
    when(employeeValidator.employeeIsValid(shubham)) thenReturn(false)
    val result = userImpl.createEmployee(shubham)
    assert(result.isEmpty)
  }
}