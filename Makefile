main:
	del /s /q *.class
	make gui

gui:
	javac src/controller/gui/GUIDriver.java
	java src/controller/gui/GUIDriver.java

cli:
	del /s /q *.class
	javac src/view/cli/CLIDriver.java
	java src/view/cli/CLIDriver.java

linux:
	find . -name "*.class" -type f -delete
	make gui
