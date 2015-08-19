# JMXIPBind.jar

Add the ability to bind JMX to a specified IP address.

# LEGAL WARNING

This method of changing JMX behavior might be illegal on the Oracle binary JVM.
The Oracle docs state that using -Xbootclasspath/p to override classes in rt.jar
is in violation of the binary license agreement you agreed to when you clicked on
the website to download it.

It should be OK on OpenJDK but I'm not a lawyer, so ask yours.

# Security

As always, exposing an unauthenticated port to public networks is a security risk. Enabling
authentication and/or SSL is highly recommended. You have been warned.

# Usage

Copy JMXIPBind.jar to the cassandra server. Then modify the JMX section of cassandra-env.sh
to look like the following example. Replace /YOUR/PATH/TO/ with the path to where you
saved JMXIPBind.jar. Set the jmxremote.host to the IP address you want to bind.

```
LOCAL_JMX=no

if [ "$LOCAL_JMX" = "yes" ]; then
  JVM_OPTS="$JVM_OPTS -Dcassandra.jmx.local.port=$JMX_PORT -XX:+DisableExplicitGC"
else
  JVM_OPTS="$JVM_OPTS -Xbootclasspath/p:/YOUR/PATH/TO/JMXIPBind.jar"
  JVM_OPTS="$JVM_OPTS -Dcom.sun.management.jmxremote.host=192.168.1.9" # YOUR IP ADDRESS HERE
  JVM_OPTS="$JVM_OPTS -Dcom.sun.management.jmxremote.port=$JMX_PORT"
  JVM_OPTS="$JVM_OPTS -Dcom.sun.management.jmxremote.rmi.port=$JMX_PORT"
  JVM_OPTS="$JVM_OPTS -Dcom.sun.management.jmxremote.ssl=false"
  JVM_OPTS="$JVM_OPTS -Dcom.sun.management.jmxremote.authenticate=true"
  JVM_OPTS="$JVM_OPTS -Dcom.sun.management.jmxremote.password.file=/etc/cassandra/jmxremote.password"
fi

```

# Credits

Most of the procedure and code came from a
[post on the Apache Cassandra mailing listjby Teijo Holzer](http://mail-archives.apache.org/mod_mbox/cassandra-user/201108.mbox/%3C4E40B802.2060500@wetafx.co.nz%3E).


# License

Apache 2
