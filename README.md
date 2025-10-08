# Distributed File Store

## 🚀 Project Overview

A high-performance distributed file storage system built with Java, designed to provide reliable, scalable, and fault-tolerant file storage across multiple nodes. This system implements distributed computing principles to handle file operations efficiently across a network of servers.

## ✨ Technical Highlights

- **Distributed Architecture**: Implements a client-server model with support for multiple storage nodes
- **Fault Tolerance**: Built-in replication and redundancy mechanisms ensure data availability
- **Concurrent Operations**: Thread-safe design supporting multiple simultaneous client requests
- **Network Communication**: Efficient socket-based communication protocol for file transfers
- **Metadata Management**: Centralized metadata tracking for distributed file locations
- **Modular Design**: Clean separation of concerns with dedicated server, client, and storage components

## 📋 Features

- **File Upload**: Store files across distributed nodes with automatic load balancing
- **File Download**: Retrieve files from any available node with automatic failover
- **File Replication**: Configurable replication factor for data redundancy
- **Node Discovery**: Automatic detection and registration of storage nodes
- **Health Monitoring**: Real-time monitoring of node availability and status
- **CLI Interface**: Simple command-line interface for all file operations

## 🛠️ Setup Instructions

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven (optional, for dependency management)
- Network connectivity between nodes

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/alankritamalhotra/distributed-file-store.git
   cd distributed-file-store
   ```

2. **Compile the project**:
   ```bash
   # Navigate to the source directory
   cd src
   
   # Compile all Java files
   javac *.java
   ```

3. **Configure nodes**:
   - Edit configuration files to set up node addresses and ports
   - Configure replication factor and storage paths

## 💻 Usage Example

### Starting the Server

```bash
# Start the master server node
java Server <port_number>

# Example:
java Server 8080
```

### Starting Storage Nodes

```bash
# Start individual storage nodes
java StorageNode <server_address> <server_port> <node_port>

# Example:
java StorageNode localhost 8080 8081
```

### Client Operations

```bash
# Upload a file
java Client upload <server_address> <server_port> <file_path>

# Download a file
java Client download <server_address> <server_port> <file_name> <destination_path>

# List files
java Client list <server_address> <server_port>

# Example:
java Client upload localhost 8080 /path/to/myfile.txt
java Client download localhost 8080 myfile.txt /path/to/destination/
```

## ✅ Quick Verification Guide

Follow these steps to verify the system works correctly:

### Step 1: Start the Master Server

```bash
cd src
javac *.java
java Server 8080
```

You should see output indicating the server has started and is listening for connections.

### Step 2: Start Storage Nodes (in separate terminals)

```bash
# Terminal 2
java StorageNode localhost 8080 8081

# Terminal 3
java StorageNode localhost 8080 8082
```

Each node should register successfully with the master server.

### Step 3: Upload a Test File

```bash
# Create a test file
echo "Hello, Distributed File Store!" > test.txt

# Upload the file
java Client upload localhost 8080 test.txt
```

Verify that the upload completes successfully and note the confirmation message.

### Step 4: List Stored Files

```bash
java Client list localhost 8080
```

You should see `test.txt` in the list of stored files.

### Step 5: Download the File

```bash
# Download to a new location
java Client download localhost 8080 test.txt downloaded_test.txt

# Verify the content
cat downloaded_test.txt
```

The downloaded file should contain: "Hello, Distributed File Store!"

### Step 6: Test Fault Tolerance (Optional)

```bash
# Stop one storage node (Ctrl+C in its terminal)
# Try downloading the file again
java Client download localhost 8080 test.txt recovered_test.txt
```

The download should still succeed if replication is configured, demonstrating fault tolerance.

## 🏗️ Architecture

```
┌──────────────┐
│   Client     │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│    Server    │
│  (Metadata)  │
└──────┬───────┘
       │
   ┌───┴───┬───────┬───────┐
   ▼       ▼       ▼       ▼
┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐
│Node1│ │Node2│ │Node3│ │Node4│
└─────┘ └─────┘ └─────┘ └─────┘
(Storage Nodes)
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit issues and pull requests.

## 📄 License

This project is available for educational and research purposes.

---

**Built with ☕ and Java** | Distributed Systems Implementation
