# OutOfMemory (OOM) error troubleshooting
## Get OOM error
```
    java -jar -Xmx100m heap-1.0.0-SNAPSHOT.jar
```
![img/heap-oom.png](img/heap-oom.png)

## Use jvisualvm to observe OOM
- In jvisualvm connect to our java process
- Go to "Monitor" tab
- Press any key in our application
- Observe how heap grows

![img/heap-jvis-oom.png](img/heap-jvis-oom.png)


## Get heap dump
### Using -XX:+HeapDumpOnOutOfMemoryError option
```
    java -jar -Xmx100m -XX:+HeapDumpOnOutOfMemoryError heap-1.0.0-SNAPSHOT.jar
```

![img/heap-oom-dump.png](img/heap-oom-dump.png)


### [Optional] Using jcmd
```
    jcmd <pid> GC.heap_dump <filename>
```

![img/heap-jcmd-dump.png](img/heap-jcmd-dump.png)


### [Optional] Using jmap
```
    jmap -dump:format=b,file=snapshot.hprof <pid>
```

![img/heap-jmap-dump.png](img/heap-jmap-dump.png)



## Get heap histogram

### Using jcmd
```
    jcmd <pid> GC.class_histogram
```

![img/heap-jcmd-histo.png](img/heap-jcmd-histo.png)


### Using jmap
```
    jmap -histo <pid> 
```

![img/heap-jmap-histo.png](img/heap-jmap-histo.png)



## Analyze heap dump

### Using Java Visual VM
- Open retrieved heap dump in jvisualvm
- Identify memory leak

![img/heap-jvis-identify.png](img/heap-jvis-identify.png)


### OQL
Execute OQL in jvisualvm:
```
    select objs from java.lang.Object[] objs where objs.length > 100
    select referrers(objs) from java.lang.Object[] objs where objs.length > 100
    select referrers(arr) from java.util.ArrayList arr where arr.size > 100
```

- Analyze Object arrays
![img/heap-jvis-oql-obj-array.png](img/heap-jvis-oql-obj-array.png)
- Analyze Object arrays referrers
![img/heap-jvis-oql-ref-obj-array.png](img/heap-jvis-oql-ref-obj-array.png)
- Identify the owner of the largest ArrayList
![img/heap-jvis-oql-ref-array-list.png](img/heap-jvis-oql-ref-array-list.png)

