# Scalable_and_Anonymous_PubSub_in_Kotlin
CS 6376 Foundations of Hybrid and Embedded Systems Final Project

## About Member

#### Young-jae Moon
* M.Sc. in computer science and Engineering Graduate Fellowship recipient at Vanderbilt University (January 2023 - December 2024).
* Email: youngjae.moon@Vanderbilt.Edu

## Advisor

#### Professor Abhishek Dubey
* Associate Professor in the Computer Science department and an affiliated faculty member in the Electrical and Computer Engineering department at Vanderbilt University
* Email: abhishek.dubey@Vanderbilt.edu

## Acknowledgements
* Thanks to Professor Abhishek Dubey for teaching me CS 6376 Foundations of Hybrid and Embedded Systems.
* Thanks to Professor Aniruddha Gokhale for teaching me CS 6381 Distributed Systems Principles.
* Thanks to Professor Taylor Johnson for teaching me CS 6315 Automated Verification.

## About the project
### Goals of the project
1. Create a Publish/Subscribe architecture using Petri nets and Kotlin, incorporating Apache Kafka and RabbitMQ. Use Kafka's distributed streaming capabilities along with the Raft consensus algorithm, as implemented in Kafka's KRaft mode, to ensure robust and scalable message delivery. Integreate Kafka to provide a high-throughput, fault-tolerant messaging system, enhancing the overall performance and reliability of the architecture.
2.  Use Petri nets to model key components of the software system. It focus on the interactions and coordination between Kafka and RabbitMQ within the Publish/Subscribe paradigm. Verify the model's correctness and ensure the system's functionality.
3. Generate the initial, unoptimised Kotlin code that uses the Remote Procedure Call (RPC) design pattern. This codebase will lay the foundation for the system, emphasizing a clear separation of concerns. The RPC pattern, combined with Kafka's streaming and RabbitMQ's messaging capabilities, will enhance anonymity in communications and provide robust fault tolerance mechanisms.

### Future work
* More complex testing methods (e.g., writing unit tests, setting up Docker, Kubernetes) can also be implemented during the winter break.

## Motivation of the project
* Programming in Java takes a lot of time than other JVM languages.
* I have realized that it is possible to code differently, resulting in a code that is as efficient as Java.
* My SWE methodology is different, though it is inspired from Xiaoming Liu et al.'s "Building reliable, high-performance communication systems from components" paper.
* Have chosen the Raft consensus algorithm in distributed systems, as implementing consensus algorithms for distributed systems is difficult in programming languages such as Java.
* Apache Kafka uses KRaft consensus algorithm which is a variant of Raft.

## Technologies Used
1. Petri-nets
2. Kotlin
3. RabbitMQ
4. JSON
5. Apache Kafka
6. IntelliJ IDEA

## Instructions for checking out the latest stable version

### Method 1:
1. Open the main page for our GitHub repository: https://github.com/Pingumaniac/Scalable_and_Anonymous_PubSub_in_Kotlin
2. Click the following button: <img src = "https://user-images.githubusercontent.com/63883314/115416097-69ade280-a232-11eb-8401-8c41362ab4c2.png" width="44" height="14">
3. Click the 'Download ZIP' option.
4. Unzip the folder.

### Method 2:
1.  Copy the web URL to your clipboard.
2.  Open 'Git Bash' from your local computer. You must have installed Git from the following page to do this: https://git-scm.com/downloads
3.  Move to the preferred directory.
4.  Next, type the following:
```
git clone https://github.com/Pingumaniac/Scalable_and_Anonymous_PubSub_in_Kotlin.git
```
5. All the codes and documents in the repository can be accessed.

Note: For Method 2, if you have already cloned the repository before, you can skip the first two steps. And type this instead for step 4:
```
git type
```

## How to set up for running this software

### 1. Please make sure you have downloaded IntelliJ.
* Please visit the following website for more detailed instructions: https://www.jetbrains.com/idea/

### 2. Install Erlang on your computer.
* As RabbitMQ is implemented in Erlang, you must download Erlang to your computer.
* Please download the latest stable version of Erlang from the following website: https://www.erlang.org/downloads

### 3. Install RabbitMQ on your computer.

#### Windows
* First, install Chocolatey from the following website: https://chocolatey.org/install
* Or you can type the following in the Powershell:
```
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```
* Second, install RabbitMQ by typing the following command in the Powershell:
```
choco install rabbitmq
```
* Third, visit this site: https://www.rabbitmq.com/install-windows.html#installer. Then, install the RabbitMQ server by downloading the following file:
```
rabbitmq-server-3.12.10.exe
```
This will install the RabbitMQ server on Windows.

#### macOS
* Follow the instructions from the following website: https://www.rabbitmq.com/install-homebrew.html
* Or open the terminal and enter the following commands:
```
brew update
brew upgrade
brew install rabbitmq
```
If that directory is not in PATH, it is recommended to append it.
* For macOS Intel, enter the following command in the terminal:
```
export PATH=$PATH:/usr/local/sbin
```
* For Apple Silicon, enter the following command in the terminal:
```
export PATH=$PATH:/opt/homebrew/sbin
```

## Bug tracking

* All users can view and report a bug in the "GitHub Issues" of our repository.
* Here is the URL for viewing and reporting a list of bugs: https://github.com/Pingumaniac/Scalable_and_Anonymous_PubSub_in_Kotlin/issues
