# Distributed File Store

## Project Overview

This is a distributed file storage system built with Java. The system stores files across multiple nodes to provide reliability and fault tolerance. It uses a client-server architecture where a central server manages metadata and coordinates file operations across storage nodes.

## Technical Highlights

- **Distributed Architecture**: Implements a client-server model with support for multiple storage nodes
- **Fault Tolerance**: Includes replication and redundancy mechanisms to ensure data availability
- **Concurrent Operations**: Thread-safe design that supports multiple simultaneous client requests
- **Network Communication**: Uses socket-based communication for file transfers
- **Metadata Management**: Central server tracks file locations across the distributed system
- **Modular Design**: Separate components for server, client, and storage functionality

## Features

- **File Upload**: Store files across distributed nodes with load balancing
- **File Download**: Retrieve files from any available node with failover support
- **File Replication**: Configurable replication factor for redundancy
- **Node Discovery**: Automatic detection and registration of storage nodes
- **Health Monitoring**: Track node availability and status
- **CLI Interface**: Command-line interface for file operations

## Setup Instructions

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

## Usage

### Step 1: Start the Server

```bash
java Server 8080 3
```

This starts the central server on port 8080 with a replication factor of 3.

### Step 2: Start Storage Nodes

In separate terminals, start multiple storage nodes:

```bash
# Terminal 1
java StorageNode localhost 8080 9001

# Terminal 2
java StorageNode localhost 8080 9002

# Terminal 3
java StorageNode localhost 8080 9003
```

Each storage node connects to the server and registers itself.

### Step 3: Upload a File

```bash
# Create a test file
echo "Hello, Distributed File Store!" > test.txt

# Upload the file
java Client upload localhost 8080 test.txt
```

The file will be distributed across the available storage nodes.

### Step 4: List Files

```bash
java Client list localhost 8080
```

This displays all files currently stored in the system.

### Step 5: Download a File

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

## Verification

The system has been tested with the following scenarios:

- File upload and download across multiple nodes
- Node failure and recovery
- Concurrent client requests
- Replication and data consistency

You can verify functionality by following the usage steps above and confirming that files are properly stored and retrieved even when individual storage nodes are unavailable.

## Architecture

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

## Contributing

Contributions are welcome. Please submit issues and pull requests.

## License

This project is available for educational and research purposes.

---

Distributed Systems Implementation
