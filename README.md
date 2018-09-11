# MCFoodRegen
This Bukkit plugin replaces Minecraft default food mechanics. Hunger and natural health generation are now disabled and food regenerates health.

## Comparision to similiar plugins
X|MCFoodRegen | OldDays | RPG Config
-|------------|---------|-----------
Configurable|❌ (for now)|✔️|✔️
Aditional features|❌|✔️|✔️
Uses PlayerItemConsume event instead of PlayerInteractEvent|✔️|❌|❌
Works with all vanilla foods without configuring|✔️|❌|❌
Works right out of the box (download, install and play)|✔️|❌|❌
Configurable|❌|✔️|✔️

## Compiling

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

You can also download pre-compiled artifacts by [clicking here](https://github.com/SigmaRS/MCFoodRegen/releases).

### Prerequisites

* JDK 1.8 or higher (which is not Java Runtime Environment).
* Git
* Maven

1. Grab a copy of the source, either by downloading it as a ZIP file or by using Git.
```
git clone https://github.com/SigmaRS/MCFoodRegen.git
cd MCFoodRegen
```

2. Compile

```
mvn clean package
```

### Installing

1. In the "target" folder you'll have a file named "MCFoodRegen-1.0-SNAPSHOT.jar" after compiling the project with Maven.
2. Extract it to your server plugins' folder.
3. Start the server.

* If you don't want to compile, you can also download pre-compiled artifacts by [clicking here](https://github.com/SigmaRS/MCFoodRegen/releases).

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **SigmaRS** - *Initial work*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
