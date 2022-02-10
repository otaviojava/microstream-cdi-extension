# Microstream CDI extension

The Microstream CDI extension is an open-source project to integrate the Jakarta EE/MicroProfile world with the Microstream persistence solution.

This project has two minimum requirements:

* A [CDI](https://jakarta.ee/specifications/cdi/) 2.0 implementation or higher
* An [Eclipse MicroProfile Config](https://github.com/eclipse/microprofile-config) 2.0 implementation or higher

## Features

### StorageManager

You're enabled to inject the ```StorageManager``` easily using MicroProfile Config to read the properties.

```java
@Inject
private StorageManager manager;
```

The CDI will create an instance application-scoped, and it will close automatically.

### Storage

The Storage annotation allows injecting an entity from Microstream.

```java
@Storage
public class NameStorage {
    //...
}
```

It will create/load this annotation using CDI.

```java
@Inject
private NameStorage nameStorage;
```

### Cache

You can use Microsctream as a cache as well, thanks to the ```StorageCache``` annotation.

```java
@Inject
@StorageCache
private Cache<String, Integer> counter;
```

You have the option to declare more than one cache from the same configuration from the name.

```java
@Inject
@StorageCache("jcache2")
private Cache<String, Integer> counter;
```

You also have the option to inject both ```CachingProvider``` and ```CacheManager``` using CDI.

```java
@Inject
@StorageCache
private CachingProvider provider;

@Inject
@StorageCache
private CacheManager cacheManager;
```

### Store

This annotation indicates the operation that will be stored using Microstream automatically.
It is a high-level implementation to save either the Iterable and Map instances or the root itself, where you can set by StoreType. 
By default, it is lazy, and using the EAGER only is extremely necessary.


```java
@Inject
private Items items;

@Override
@Store
public Item save(Item item) {
    this.items.add(item);
    return item;
}

``

## Eclipse MicroProfile Configuration

The integration allows receiving all information from the Eclipse MicroProfile Config instead of either a programmatic
configuration or a single file.
Thus, you can overwrite any properties following the good practices in the Market, such as [the Twelve-Factor App](https://12factor.net/).

By default, Eclipse Microprofile will read all the properties and do a parser to Microstream, with the properties parses below. Furthermore,  you can read the properties directly as the Microstream way.

It will use the Eclipse Microprofile to read/parse the properties.

```java
@Inject
private NameStorage nameStorage;
```

This injection will look in the ```microprofile-config.properties``` file to the property that will be a file to load directly by Micrscrostream with the ``EmbeddedStorageConfiguration.load(value);`` method.

```java
@Inject
@ConfigProperty(name = "microstream.ini")
private NameStorage nameStorage;
```




### Core

The relation with the properties from [Microstream docs](https://docs.microstream.one/manual/storage/configuration/properties.html):

* ```microstream.storage.directory```: storage-directory; The base directory of the storage in the file system. Default is "storage" in the working directory.
* ```microstream.storage.filesystem```: storage-filesystem; The live file system configuration. See storage targets configuration.
* ```microstream.deletion.directory```: deletion-directory; If configured, the storage will not delete files. Instead of deleting a file it will be moved to this directory.
* ```microstream.truncation.directory```: truncation-directory; If configured, files that will get truncated are copied into this directory.
* ```microstream.backup.directory```: backup-directory; The backup directory.
* ```microstream.backup.filesystem```: backup-filesystem; The backup file system configuration. See storage targets configuration.
* ```microstream.channel.count```: channel-count; The number of threads and number of directories used by the storage engine. Every thread has exclusive access to its directory. Default is 1.
* ```microstream.channel.directory.prefix```: channel-directory-prefix; Name prefix of the subdirectories used by the channel threads. Default is "channel_".
* ```microstream.data.file.prefix```: data-file-prefix; Name prefix of the storage files. Default is "channel_".
* ```microstream.data.file.suffix```: data-file-suffix; Name suffix of the storage files. Default is ".dat".
* ```microstream.transaction.file.prefix```: transaction-file-prefix; Name prefix of the storage transaction file. Default is "transactions_".
* ```microstream.transaction.file.suffix```: transaction-file-suffix; Name suffix of the storage transaction file. Default is ".sft".
* ```microstream.type.dictionary.file.name```: type-dictionary-file-name; The name of the dictionary file. Default is "PersistenceTypeDictionary.ptd".
* ```microstream.rescued.file.suffix```: rescued-file-suffix; Name suffix of the storage rescue files. Default is ".bak".
* ```microstream.lock.file.name```: lock-file-name; Name of the lock file. Default is "used.lock".
* ```microstream.housekeeping.interval```: housekeeping-interval; Interval for the housekeeping. This is work like garbage collection or cache checking. In combination with houseKeepingNanoTimeBudget the maximum processor time for housekeeping work can be set. Default is 1 second.
* ```microstream.housekeeping.time.budget```: housekeeping-time-budget; Number of nanoseconds used for each housekeeping cycle. Default is 10 milliseconds = 0.01 seconds.
* ```microstream.entity.cache.threshold```: entity-cache-threshold; Abstract threshold value for the lifetime of entities in the cache. Default is 1000000000.
* ```microstream.entity.cache.timeout```: entity-cache-timeout; Timeout in milliseconds for the entity cache evaluator. If an entity wasn’t accessed in this timespan it will be removed from the cache. Default is 1 day.
* ```microstream.data.file.minimum.size```: data-file-minimum-size; Minimum file size for a data file to avoid cleaning it up. Default is 1024^2 = 1 MiB.
* ```microstream.data.file.maximum.size```: data-file-maximum-size; Maximum file size for a data file to avoid cleaning it up. Default is 1024^2*8 = 8 MiB.
* ```microstream.data.file.minimum.use.ratio```: data-file-minimum-use-ratio; The ratio (value in ]0.0;1.0]) of non-gap data contained in a storage file to prevent the file from being dissolved. Default is 0.75 (75%).
* ```microstream.data.file.cleanup.head.file```: data-file-cleanup-head-file; A flag defining whether the current head file (the only file actively written to) shall be subjected to file cleanups as well.
* ```microstream.property```: llow custom properties in through Microprofile, using this prefix. E.g.: If you want to include the "custom.test" property, you will set it as "microstream.property.custom.test"

### Cache

The relation with the properties from [Microstream docs](https://docs.microstream.one/manual/cache/configuration/properties.html):

There is a list of properties in the ```CacheProperties``` enum.

The primary purpose of this configuration is to allow you to explore the Configuration of Cache through Eclipse MicroProfile.
     
* ```microstream.cache.loader.factory```: cacheLoaderFactory - A CacheLoader should be configured for "Read Through" caches to load values when a cache miss occurs.
* ```microstream.cache.writer.factory```: cacheWriterFactory - A CacheWriter is used for write-through to an external resource.
* ```microstream.cache.expires.factory```: expiryPolicyFactory - Determines when cache entries will expire based on creation, access and modification operations.
* ```microstream.cache.read.through```: readThrough - When in "read-through" mode, cache misses that occur due to cache entries not existing as a result of performing a "get" will appropriately cause the configured CacheLoader to be invoked.
* ```microstream.cache.write.through```: writeThrough - When in "write-through" mode, cache updates that occur as a result of performing "put" operations will appropriately cause the configured CacheWriter to be invoked. 
* ```microstream.cache.store.value```: storeByValue - When a cache is storeByValue, any mutation to the key or value does not affect the key of value stored in the cache.
* ```microstream.cache.statistics```: statisticsEnabled - Checks whether statistics collection is enabled in this cache. 
* ```microstream.cache.management```: managementEnabled - Checks whether management is enabled on this cache. 

## Microstream

MicroStream Data-Store is a native Java object graph storage engine. From a technical point of view it serves one purpose only:

**To fully or partially persist and restore a Java object graph in the simplest way possible for the user.**

## Links

* [Website](https://microstream.one/)
* [Documentation](https://docs.microstream.one/manual/intro/welcome.html)
* [Videos](https://www.youtube.com/c/MicroStream)


