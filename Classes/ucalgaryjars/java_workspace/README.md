# Java Workspace

<p>To compile java applets files run the following command while in powershell:</p>

**Tip: pressing shift + right click will allow you to open powershell in current directory**

```
javac -cp "./../../../../../../*;." *.java
```

<p>This command will throw file not found if your are not in the working directory</p>

_Working directories:_ <br>
**Angle:** `Java-Applet\Classes\ucalgaryjars\java_workspace\ca\ucalgary\phas\map\angle` <br>
**Page Browser:** `Java-Applet\Classes\ucalgaryjars\java_workspace\ca\ucalgary\phas\map\contentNavigator` <br>
**Vector:** `Java-Applet\Classes\ucalgaryjars\java_workspace\ca\ucalgary\phas\map\vector`

_New Directories will be added as we go_
<br>

---

Once the class have been made, navigate too `Java-Applet\Classes\ucalgaryjars\java_workspace`

While in that directory compile the class files to a jar using the following command:

```
jar cf ..\..\MRUupdatedjars\angle.jar .\ca\ucalgary\phas\map\<AppletToCompile>\*.class
```

_Make sure that `<AppletToCompile>` represents the working directory._

For example on angle you would do something like this:

```
jar cf ..\..\MRUupdatedjars\angle.jar .\ca\ucalgary\phas\map\angle\*.class
```

The new complied jar will be placed into `Java-Applet\Classes\MRUupdatedjars`
