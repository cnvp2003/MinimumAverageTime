package com.mycompany.assignment.component

import com.mycompany.assignment.model.Customer

import scala.collection.mutable
import scala.collection.mutable.ListBuffer


trait CustomerServe{
  def getTotalTime(customers: mutable.ListBuffer[Customer]): Int
  def averageWaitTime(customers: mutable.ListBuffer[Customer]): Int
}

object CustomerServeComponent extends CustomerServe {
  val customerPriority = new mutable.PriorityQueue[Customer]()(Ordering.by[Customer, Int](_.cookTime).reverse)

  override def getTotalTime(customers: ListBuffer[Customer]): Int = {
    var totalTime = 0
    var currentTime = 0
    val customersInTimeOrder = customers.sortWith(_.orderTime < _.orderTime)

    while (customersInTimeOrder.nonEmpty || customerPriority.nonEmpty) {
      while (customersInTimeOrder.nonEmpty && (customerPriority.isEmpty || customersInTimeOrder.head.orderTime <= currentTime)) {
        val customer = customersInTimeOrder.head
        customersInTimeOrder -= customer
        customerPriority.enqueue(customer)
        currentTime = Math.max(currentTime, customer.orderTime)
      }

      val customer = customerPriority.dequeue()
      currentTime += customer.cookTime
      totalTime += currentTime - customer.orderTime
    }
    totalTime
  }

  override def averageWaitTime(customers: ListBuffer[Customer]): Int ={
    getTotalTime(customers) / customers.length
  }


  /*override def getTotalTime(customers: mutable.ListBuffer[Customer]): Long = {
    val customerPriority = mutable.ListBuffer.empty[Customer]
    var totalTime = 0L
    var currentTime = 0L
    val customersInTimeOrder = customers.sortWith(_.orderTime < _.orderTime)

    while (customersInTimeOrder.nonEmpty || customerPriority.nonEmpty) {
      while (customersInTimeOrder.nonEmpty && (customerPriority.isEmpty || customersInTimeOrder.head.orderTime <= currentTime)) {
        val customer = customersInTimeOrder.head
        customersInTimeOrder -= customer
        customerPriority += customer
        currentTime = Math.max(currentTime, customer.orderTime)
      }

      customerPriority.sortWith(_.cookTime < _.cookTime).map { customer =>
        currentTime += customer.cookTime
        totalTime += currentTime - customer.orderTime
      }
      customerPriority.clear()
    }
    totalTime
  }*/
}