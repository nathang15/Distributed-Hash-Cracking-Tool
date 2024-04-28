<!-- ABOUT THE PROJECT -->
## About The Project

This project proposes a distributed system architecture to efficiently crack SHA-256 hashes using a multi-threaded CPU approach. The system aims to mitigate the time-consuming process of password recovery by leveraging a server-client-worker model over TCP connection.
The client initiates requests to the server for password hashes to be cracked. Upon receiving a request, the server orchestrates the distribution of tasks among multiple workers. Each worker operates concurrently, employing non-overlapping brute force techniques to crack passwords.
The communication is done with Java RMI and aim to migrate to gRPC in the future.

### Built With Java and Java RMI

<!-- GETTING STARTED -->
## Getting Started

Below are the steps to run the project. This is assuming you have JDK 21 and JRE available on your machine.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* npm
  ```sh
  npm install npm@latest -g
  ```

### Installation and Run

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._
1. Clone the repo
   ```sh
   git clone https://github.com/nathang15/Distributed-Hash-Cracking-Tool.git
   ```
2. Run SMain.java
   ```sh
   java SMain.java
   ```
3. Run as much Workers as you like by running multiple WMain.java
   ```js
   java WMain.java
   ```
4. Run Client
   ```js
   java CMain.java
   ```
<!-- USAGE EXAMPLES -->
## Usage

This is only use for education purpose and was created as the final project for the course Comp352 - Computer Networks at Dickinson College

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.
