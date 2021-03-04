Author: Tim Lindquist (Tim.Lindquist@asu.edu)
        Software Engineering, CIDSE, IAFSE, Arizona State University Polytechnic
Version: April 2020
SER335 Updates: Kevin Gary, March 2021

Purpose: Sample Java Applications demonstrating security with the Java platform.

This program is has been tested on Windows and MacOS. The purpose of this program
is to show how to use the security manager and a policy file to control the
system resources that an executing java program can access. This example
shows three different forms of excution:
  1 without a security manager,
  2 with the security manager, but without a policy file, and
  3 with the security manager and with an appropriate policy file.
There are targets in the ant build.xml file supporting execution of each of
the three forms.

*** You may or may not need this step.
*** Also, you can execute the commands at the command prompt pretty easily

To execute Ant using the build.xml in this directory, you will need to
copy the file: antlibs.jar from the lib directory to your home directory:
cp lib/antlibs.jar ~
or
cp lib/antlibs.jar $HOME/
Note that ~ (tilde) is a shortcut for $HOME
then extract the antlibs.jar file:
pushd ~
jar xvf antlibs.jar
pushd -0
The pushd commands manipulate a stack of directories for switching your
bash's current directory. The first pushd pushes home onto the stack and
switches the current directory to home. The second pushd takes you
back to whatever directory you were in before the first.

The genjar target generates an executable jar file for the program placed in the
lib directory: lib/PrintKeyStore.jar. This program opens the keystore passed to
it via the first command line argument, using the second command line argument as
the keystore password. It lists all of the keys contained in the keystore.

To execute the program without the security manager (scenario 1 from above):

ant execute

This should print: .../Data/ser335Store contains a key for ser335
If it does not, the the keystore has likely expired. You can re-create it with:
keytool -genkey -alias ser335 -keypass ser335Security -validity 720 -keystore Data/ser335Store
executing this command will prompt for personal information. If you enter the string
ser335Security as the password then the example should run independent of any other information
that you enter.

To execute the program with the security manager (scenario 2 from above):

ant executeWithSecMgr

This should generate a java.security.AccessControlException since the program
has not been provided java.io.FilePermission (the program is trying to access
a file outside of the files/subdirectories below its codebase (lib directory).

To execute the program with the security manager and a policy file (scenario 3 from above):
edit the policy file changing the folders specified to be the absolute path to the point
where you extracted this example's jar file. Then execute:

ant executeWithPolicy
or (if you are on Unix)
chmod u+x ./invokeJarWithPolicy.sh
./invokeJarWithPolicy.sh
This should generate the same output as executing without the security manager:
   .../Data/ser335Store contains a key for ser335

To clean the project (remove the .class files) execute:
ant clean
