# DeltaAPI

## Maven Repo

### Repository
```xml
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
```
### Dependency
```xml
        <dependency>
            <groupId>com.github.Delta-Development</groupId>
            <artifactId>DeltaAPI</artifactId>
            <version>VERSION</version>
            <scope>provided</scope>
        </dependency>
```
![JitPack](https://img.shields.io/jitpack/v/github/Delta-Development/DeltaAPI?style=for-the-badge)

## 🖊 Basics of the API
### Main class
In order for this API to work, you will need to setup your Main class as follows:
```java
@Override
    public void onEnable() {
        // Plugin startup logic
        setInstance(this);
```
This tells the API what your main class. From here, you can use any part of the API in your plugin!
### Message Util
In DeltaAPI, there is a message util that makes sending messages easier. No more using `ChatColor.translateAlternateColorCodes`. 
Here are some examples of the util being used:
```java
// To send a message to a player
Player p = (Player) sender;
new Message("&cHello &lworld").send(p); // Sends the message to the player with colour and formatting codes translated.

// To broadcast a message to the entire server
new Message("&4&lTHIS IS A BROADCAST").broadcast(); // Sends a message to everyone on the server with colour and formatting.
```
### ICommand 
# Info coming soon
