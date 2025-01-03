# Java-Concurrent-Scenarios

This repository contains solutions for the Concurrent Programming (6SENG006W) module coursework for the 2024/25 academic year. The coursework includes implementing concurrent (multi-threaded) systems in Java to solve three distinct scenarios, demonstrating safe and efficient concurrency mechanisms.


Table of Contents

  Scenario 1: Coffee Shop Ordering System
  Scenario 2: Concurrent Banking System
  Scenario 3: University Shared Bathroom Simulation
  Technologies Used


# Scenario 1: Coffee Shop Ordering System
Problem Description

Simulates a coffee shop where multiple customers place orders, and baristas prepare coffee. The system ensures thread-safe access to a shared order queue, which has limited capacity to prevent overloading.

Features :
* Customers place orders into a shared queue. If the queue is full, they wait until space becomes available.
* Baristas process orders from the queue. If the queue is empty, they wait for new orders.
* Thread-safe implementation using Semaphore to manage access to the limited-capacity order queue.
* Ensures mutual exclusion for consistent updates to the queue.



# Scenario 2: Concurrent Banking System
Problem Description

Implements a banking transaction system where multiple threads transfer money between bank accounts. The system ensures transactional safety, avoids deadlocks, and supports concurrent reads.

Features : 
* Transaction Safety: Uses Semaphore to ensure that both accounts involved in a transaction are locked before transferring money.
* Deadlock Avoidance: Implements locking in a safe order to prevent cyclic dependencies.
* Fair Access: Grants threads access to accounts in a first-come-first-served manner.
* Read-Write Optimization: Allows concurrent reads using Semaphore without interfering with ongoing transactions.
* Multiple Transactions: Supports simultaneous transactions for different account pairs.
* Transaction Reversal: Safely reverses a transaction in case of errors.


# Scenario 3: University Shared Bathroom Simulation
Problem Description

Simulates the use of a shared bathroom with limited stalls in a university setting. Ensures fair and efficient stall usage among employees and students.

Features :
* Tracks available stalls and updates them as they are used and vacated.
* Limits the number of simultaneous users using Semaphore to represent available stalls.
* Implements waiting mechanisms for users when all stalls are occupied.
* Ensures no stall is occupied by more than one user at a time.
* Throws and handles exceptions for invalid inputs (e.g., zero stalls, negative users).



Technologies Used

  Java: Programming language for implementing concurrency.
  Multithreading: Core concept used to manage concurrent execution.
  Semaphore: Used to control access to shared resources and manage resource limits.
  Synchronization: Ensures thread-safe access to shared resources.


    
