package com.mycompany.assignment.app

import com.mycompany.assignment.component.CustomerServeComponent
import com.mycompany.assignment.model.Customer

import scala.collection.mutable

object MinimumAverageTime {

    def main(args: Array[String]) {
        val customers = mutable.ListBuffer.empty[Customer]
        //val customersList:List[Customer] = List()
        val n = scala.io.StdIn.readInt()

        1 to n foreach { _ =>
            val in = scala.io.StdIn.readLine()
            val customer = Customer(in.split(" ").head.toInt, in.split(" ").last.toInt)
            customers += customer
            //customer :: customersList
            //customersList :+ customer  //append
        }

        println(s"${CustomerServeComponent.averageWaitTime(customers.toList)}")
        //println(s"${CustomerServeComponent.averageWaitTime(customersList)}")
    }

}