Asper - Esper for Android
=====

Work in progress - towards upgrade to Esper 7.1.0

Dependencies to resolve:
- ANTLR runtime library version 4.7 (antlr-runtime-4.7.jar)
- CGLIB version 3.2.5 (cglib-nodep-3.2.5.jar)
- SLF4J 1.7.25 (slf4j-api-1.7.25.jar)

Test & Package.

---

Esper is an open sourced, complex event processing engine for the Java, and .NET plattform.
Complex event processing delivers high-speed processing of many events across all the layers of an organization, identifying the most meaningful 
events within the event cloud, analyzing their impact, and taking subsequent action in real time.
Esper offers a SQL like language for processing events and enables dealing with high frequency time-based event data.

Some typical examples of applications are:
- Business process management and automation (process monitoring, BAM, reporting exceptions, operational intelligence)
- Finance (algorithmic trading, fraud detection, risk management)
- Network and application monitoring (intrusion detection, SLA monitoring)
- Sensor network applications (RFID reading, scheduling and control of fabrication lines, air traffic)

It has until now been difficult to utilize Esper on Mobile devices due to 
dependency restrictions from missing java packages and classes in Android.
We have resolved these dependencies by utilizing packages from the OpenJDK project and by modifying
existing JavaBeans classes to fit within our context without the need for java.awt.*

This version is based upon Esper 4.8.0, released in March, 2013. 
Visit http://esper.codehaus.org for more information.

Licensed under The GNU General Public License (GPL-2.0)

http://opensource.org/licenses/gpl-2.0.php
