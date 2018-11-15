# Getting started

## Table of Contents

- [Installation](#Installation)
  - [Windows](#Windows)
  - [Linux and macOS](#Linux-and-macOS)
- [Getting started with CLI](#Getting-started-with-CLI)
  - [Run node](#Run-node)
  - [Transfer coins](#Transfer-coins)
- [Getting started with Pravda Programs](#Getting-started-with-Pravda-Programs)
  - [Importing Project Template](#Importing-Project-Template)
  - [Compiling the Program](#Compiling-the-Program)
  - [Deploying to Testnet](#Deploying-to-Testnet)

## Installation

Ensure that JRE 1.8 or higher is installed in your system. Also ensure that 8080 TCP port is free.

### Windows

Download MSI installer from [releases page](https://github.com/expload/pravda/releases). Double click on it. Currently installer is unsigned. It leads to red alert during installation. Do not afraid: it's OK.

### Linux and macOS

We've prepared universal installation script. Just run this command in your terminal.

```
curl -sL https://git.io/pravda-for-unix | bash
```

## Getting started with CLI

### Run node

Then we need to run local node. First of all lets build initial coin distribution config. 

```bash
pravda gen address -o my-wallet.json
```

This command will generate ED25519 key pair. It's a valid Pravda wallet. Now you can add an address to coin distribution config.

```json
[
  {
    "address": "address from my-wallet.json",
    "amount": 1000000000
  }
]
```

Save this to `my-coin-distribution.json`. Now lets initialize node configuration.

```bash
pravda node init --local --coin-distribution my-coin-distribution.json
```

Congratulations! The configuration is written to `pravda-data/node.conf` directory (also you can chose data directory using `--data-dir` key). Now let's run the node.

```bash
pravda node run
```

Now you have our own Pravda network with one validator, and one billion coins on your account. Check out `http://localhost:8080/ui`. 

### Transfer coins

You are very rich! Now you want to donate a part of your wealth to some poor guy. Let's generate wallet for him.

```bash
pravda gen address -o another-wallet.json
```
  
Wallet for poor guy is created. Now let's copy his address and transfer some coins.

```bash
pravda broadcast transfer \
  --wallet my-wallet.json \
  --to <address-of-poor-guy> \
  --amount 1000000
```

Now the poor guy is not so poor. Great job!

## Getting started with Pravda Programs

### Importing Project Template

To use project template, you will need [.NET Core SDK 2.1](https://www.microsoft.com/net/download/dotnet-core/2.1).  
Create a directory for your project and run inside it:  
```bash
dotnet new -i Expload.PravdaProgramTemplate
dotnet new pravdaprogram
```
Now your directory contains:
 - `GameToken.cs` - main program file. The one in the project template is a sample of a simple token
 - `GameToken.csproj` - project configuration. This includes scripts for compiling and deployment, as well as .NET dependencies & meta-data
 - `README.md` - feel free to consult it whenever you feel confused

### Compiling the Program

To compile C# code into pravda binary run:
```
dotnet publish -c Debug
```
Now you have `Gametoken.pravda` in your project directory.  
You can use [local node](#Getting-started-with-CLI) and [Pravda CLI commands](https://github.com/expload/pravda/blob/master/doc/ref/cli/pravda-broadcast-deploy.md) to deploy to a local node or proceed further to work with Pravda Testnet.

### Deploying to Testnet

Let us generate a Pravda Wallet:
```bash
pravda gen address -o wallet.json
```
If you already have a Pravda Wallet, move it to project folder and 
rename it to `wallet.json`.  
Go to [Testnet Faucet](https://faucet.dev.expload.com/ui) to get some EXP, as you have to pay for deployment transaction.  
  
Finally, run:
```
dotnet publish -c Release
```
Now your program is on the Testchain!  
Your project directory includes `program-wallet.json`, which has your program's address.  
  
For more information on working with the template, checkout `README.md` in your project directory.
