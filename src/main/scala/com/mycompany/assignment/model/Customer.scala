package com.mycompany.assignment.model

case class Customer(orderTime: Int, cookTime: Int) {
  override def toString(): String =  s"Customer [OrderTime=$orderTime, CookTime=$cookTime]"
}