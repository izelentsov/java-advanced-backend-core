# Module 3. Monitoring and Troubleshooting the Java Application

[TOC]

## OutOfMemory (OOM) error troubleshooting
### Get OOM error
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


### Get heap dump
#### Using -XX:+HeapDumpOnOutOfMemoryError option
```
    java -jar -Xmx100m -XX:+HeapDumpOnOutOfMemoryError heap-1.0.0-SNAPSHOT.jar
```

![img/heap-oom-dump.png](img/heap-oom-dump.png)


#### [Optional] Using jcmd
```
    jcmd <pid> GC.heap_dump <filename>
```

![img/heap-jcmd-dump.png](img/heap-jcmd-dump.png)


#### [Optional] Using jmap
```
    jmap -dump:format=b,file=snapshot.hprof <pid>
```

![img/heap-jmap-dump.png](img/heap-jmap-dump.png)



### Get heap histogram

#### Using jcmd
```
    jcmd <pid> GC.class_histogram
```

![img/heap-jcmd-histo.png](img/heap-jcmd-histo.png)


#### Using jmap
```
    jmap -histo <pid> 
```

![img/heap-jmap-histo.png](img/heap-jmap-histo.png)



### Analyze heap dump

#### Using Java Visual VM
- Open retrieved heap dump in jvisualvm
- Identify memory leak

![img/heap-jvis-dump.png](img/heap-jvis-dump.png)

![img/heap-jvis-identify.png](img/heap-jvis-identify.png)


#### OQL
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




## Deadlock troubleshooting
### Get deadlock
- Execute java application that simulates deadlock:
```
    java -jar deadlock-1.0.0-SNAPSHOT.jar
```

![img/deadlock-run.png](img/deadlock-run.png)

![img/deadlock-jps.png](img/deadlock-jps.png)


### Get thread dump

#### jstack
```
    jstack -l 89379
```

![img/deadlock-jstack.png](img/deadlock-jstack.png)


#### kill -3
```
    kill -3 <pid>
```
Executing the kill command:
![img/deadlock-kill-cmd.png](img/deadlock-kill-cmd.png)

Result in the running console:
![img/deadlock-kill-output.png](img/deadlock-kill-output.png)


#### jvisualvm

![img/deadlock-jvis-alert.png](img/deadlock-jvis-alert.png)

![img/deadlock-jvis-thread-dump.png](img/deadlock-jvis-thread-dump.png)


#### jcmd
```
    jcmd <pid> Thread.print
```

![img/deadlock-jcmd-cmd.png](img/deadlock-jcmd-cmd.png)

![img/deadlock-jcmd-output.png](img/deadlock-jcmd-output.png)



## Remote JVM profiling
Using [JMX Technology](https://docs.oracle.com/javase/8/docs/technotes/guides/management/agent.html)

```
    java -jar -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=7890 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false simple-1.0.0-SNAPSHOT.jar
```
Connect to JVM using jconsole:
```
    jconsole localhost:7890
```

![img/profiling-jconsole.png](img/profiling-jconsole.png)

Example of an MBean: 
![img/profiling-jconsole-class-loaded.png](img/profiling-jconsole-class-loaded.png)


