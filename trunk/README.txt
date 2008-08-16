== CONTENT ==
	1. Introduction
		<<todo>>
	2. License
		<<todo>>
	3. Install instructions
		<<todo>>
	4. Execution instructions
		4.1 VERY important notes
	5. Reference
		<<todo>>
	6. Bibliography
		<<todo>>

== Chapter 1: Introduction ==

	<<todo>>
	
== Chapter 2: License ==

	<<todo>
	
== Chapter 3: Install instructions ==

	3.1	Get latest Java release (www.java.com)
		You need at least Java version 1.5, Java version 1.6 is recommended.
		
	3.2	Get latest Java3D release (Google: java 3d)
		You need at least version 1.5.1 (this is/was our development version).
		Thus means, with 1.5.1 it works and we have tested it.
		
	3.3 Install in the following order:
		- Java JDK / JRE
		- Java3D
		- Test it by launching the Simulator with GUI
		- If no error, you're finished, otherwise continue at 3.4
	
	3.4 It seems it did not work to install Java3D properly, here some issues
		we could find out and are aware of.
		
		3.4.1 java.lang.UnsatisfiedLinkError: no j3dcore-ogl-chk in java.library.path
			If you see this exception at Std.err the java3d dll files are missing for
			your execution JRE. To fix this do:
				- open your Java3D installation directory
				- open your Java installation directory
				- copy every file from
					<Java3D installation directory>
				  to
				  	<Java installation directory>
				  respectively (if existing - usually for JDK installation) to
				  	<Java installation directory>/jre
				- this should fix the problem, otherwise ask google the might and wise ;-)
		
== Chapter 4: Execution instructions ==

	4.1 VERY important notes
	
		4.1.1 Usage of build.xml
		
			4.1.1.1 Task 'launch-vis'
				The working directory is ./data! This means, every path
				you may use in modules should be relative to this one.
				
			4.1.1.2 Task 'compile'
				This task compiles the current classes incrementally.

== Chapter 5: Reference ==

	<<todo>

== Chapter 6: Bibliography ==

	<<todo>