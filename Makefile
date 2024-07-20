main:
	del /s /q *.class
	make gui

gui:
	javac src/view/gui/GUIDriver.java
	java src/view/gui/GUIDriver.java

cli:
	del /s /q *.class
	javac src/view/cli/CLIDriver.java
	java src/view/cli/CLIDriver.java

linux:
	rm -rf *.class
	make gui
